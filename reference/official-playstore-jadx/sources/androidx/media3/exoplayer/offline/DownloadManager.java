package androidx.media3.exoplayer.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.offline.Downloader;
import androidx.media3.exoplayer.scheduler.Requirements;
import androidx.media3.exoplayer.scheduler.RequirementsWatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class DownloadManager {
    public static final int DEFAULT_MAX_PARALLEL_DOWNLOADS = 3;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    public static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1);
    private static final int MSG_ADD_DOWNLOAD = 7;
    private static final int MSG_CONTENT_LENGTH_CHANGED = 11;
    private static final int MSG_DOWNLOAD_UPDATE = 3;
    private static final int MSG_INITIALIZE = 1;
    private static final int MSG_INITIALIZED = 1;
    private static final int MSG_PROCESSED = 2;
    private static final int MSG_RELEASE = 13;
    private static final int MSG_REMOVE_ALL_DOWNLOADS = 9;
    private static final int MSG_REMOVE_DOWNLOAD = 8;
    private static final int MSG_SET_DOWNLOADS_PAUSED = 2;
    private static final int MSG_SET_MAX_PARALLEL_DOWNLOADS = 5;
    private static final int MSG_SET_MIN_RETRY_COUNT = 6;
    private static final int MSG_SET_NOT_MET_REQUIREMENTS = 3;
    private static final int MSG_SET_STOP_REASON = 4;
    private static final int MSG_TASK_STOPPED = 10;
    private static final int MSG_UPDATE_PROGRESS = 12;
    private static final String TAG = "DownloadManager";
    private int activeTaskCount;
    private final Handler applicationHandler;
    private final Context context;
    private final WritableDownloadIndex downloadIndex;
    private List<Download> downloads;
    private boolean downloadsPaused;
    private boolean initialized;
    private final InternalHandler internalHandler;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int maxParallelDownloads;
    private int minRetryCount;
    private int notMetRequirements;
    private int pendingMessages;
    private final RequirementsWatcher.Listener requirementsListener;
    private RequirementsWatcher requirementsWatcher;
    private boolean waitingForRequirements;

    public static final class DownloadUpdate {
        public final Download download;
        public final List<Download> downloads;
        public final Exception finalException;
        public final boolean isRemove;

        public DownloadUpdate(Download download, boolean z, List<Download> list, Exception exc) {
            this.download = download;
            this.isRemove = z;
            this.downloads = list;
            this.finalException = exc;
        }
    }

    public interface Listener {
        void onDownloadChanged(DownloadManager downloadManager, Download download, Exception exc);

        void onDownloadRemoved(DownloadManager downloadManager, Download download);

        void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z);

        void onIdle(DownloadManager downloadManager);

        void onInitialized(DownloadManager downloadManager);

        void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i10);

        void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z);
    }

    public static class Task extends Thread implements Downloader.ProgressListener {
        private long contentLength;
        private final DownloadProgress downloadProgress;
        private final Downloader downloader;
        private Exception finalException;
        private volatile InternalHandler internalHandler;
        private volatile boolean isCanceled;
        private final boolean isRemove;
        private final int minRetryCount;
        private final DownloadRequest request;

        private static int getRetryDelayMillis(int i10) {
            return Math.min((i10 - 1) * 1000, 5000);
        }

        public void cancel(boolean z) {
            if (z) {
                this.internalHandler = null;
            }
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            this.downloader.cancel();
            interrupt();
        }

        @Override // androidx.media3.exoplayer.offline.Downloader.ProgressListener
        public void onProgress(long j10, long j11, float f10) {
            this.downloadProgress.bytesDownloaded = j11;
            this.downloadProgress.percentDownloaded = f10;
            if (j10 != this.contentLength) {
                this.contentLength = j10;
                InternalHandler internalHandler = this.internalHandler;
                if (internalHandler != null) {
                    internalHandler.obtainMessage(11, (int) (j10 >> 32), (int) j10, this).sendToTarget();
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (this.isRemove) {
                    this.downloader.remove();
                } else {
                    long j10 = -1;
                    int i10 = 0;
                    while (!this.isCanceled) {
                        try {
                            this.downloader.download(this);
                            break;
                        } catch (IOException e5) {
                            if (!this.isCanceled) {
                                long j11 = this.downloadProgress.bytesDownloaded;
                                if (j11 != j10) {
                                    i10 = 0;
                                    j10 = j11;
                                }
                                i10++;
                                if (i10 > this.minRetryCount) {
                                    throw e5;
                                }
                                Thread.sleep(getRetryDelayMillis(i10));
                            }
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Exception e6) {
                this.finalException = e6;
            }
            InternalHandler internalHandler = this.internalHandler;
            if (internalHandler != null) {
                internalHandler.obtainMessage(10, this).sendToTarget();
            }
        }

        private Task(DownloadRequest downloadRequest, Downloader downloader, DownloadProgress downloadProgress, boolean z, int i10, InternalHandler internalHandler) {
            this.request = downloadRequest;
            this.downloader = downloader;
            this.downloadProgress = downloadProgress;
            this.isRemove = z;
            this.minRetryCount = i10;
            this.internalHandler = internalHandler;
            this.contentLength = -1L;
        }
    }

    public DownloadManager(Context context, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory, Executor executor) {
        this(context, new DefaultDownloadIndex(databaseProvider), new DefaultDownloaderFactory(new CacheDataSource.Factory().setCache(cache).setUpstreamDataSourceFactory(factory), executor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMainMessage(Message message) {
        int i10 = message.what;
        if (i10 == 1) {
            onInitialized((List) message.obj);
        } else if (i10 == 2) {
            onMessageProcessed(message.arg1, message.arg2);
        } else {
            if (i10 != 3) {
                throw new IllegalStateException();
            }
            onDownloadUpdate((DownloadUpdate) message.obj);
        }
        return true;
    }

    public static Download mergeRequest(Download download, DownloadRequest downloadRequest, int i10, long j10) {
        int i11 = download.state;
        long j11 = (i11 == 5 || download.isTerminalState()) ? j10 : download.startTimeMs;
        int i12 = 7;
        if (i11 != 5 && i11 != 7) {
            i12 = i10 != 0 ? 1 : 0;
        }
        return new Download(download.request.copyWithMergedRequest(downloadRequest), i12, j11, j10, -1L, i10, 0);
    }

    private void notifyWaitingForRequirementsChanged() {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onWaitingForRequirementsChanged(this, this.waitingForRequirements);
        }
    }

    private void onDownloadUpdate(DownloadUpdate downloadUpdate) {
        this.downloads = Collections.unmodifiableList(downloadUpdate.downloads);
        Download download = downloadUpdate.download;
        boolean zUpdateWaitingForRequirements = updateWaitingForRequirements();
        if (downloadUpdate.isRemove) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadRemoved(this, download);
            }
        } else {
            Iterator<Listener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().onDownloadChanged(this, download, downloadUpdate.finalException);
            }
        }
        if (zUpdateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onInitialized(List<Download> list) {
        this.initialized = true;
        this.downloads = Collections.unmodifiableList(list);
        boolean zUpdateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onInitialized(this);
        }
        if (zUpdateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onMessageProcessed(int i10, int i11) {
        this.pendingMessages -= i10;
        this.activeTaskCount = i11;
        if (isIdle()) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onIdle(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i10) {
        Requirements requirements = requirementsWatcher.getRequirements();
        if (this.notMetRequirements != i10) {
            this.notMetRequirements = i10;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(3, i10, 0).sendToTarget();
        }
        boolean zUpdateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRequirementsStateChanged(this, requirements, i10);
        }
        if (zUpdateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void setDownloadsPaused(boolean z) {
        if (this.downloadsPaused == z) {
            return;
        }
        this.downloadsPaused = z;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        boolean zUpdateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDownloadsPausedChanged(this, z);
        }
        if (zUpdateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private boolean updateWaitingForRequirements() {
        boolean z;
        if (this.downloadsPaused || this.notMetRequirements == 0) {
            z = false;
        } else {
            for (int i10 = 0; i10 < this.downloads.size(); i10++) {
                if (this.downloads.get(i10).state == 0) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        boolean z5 = this.waitingForRequirements != z;
        this.waitingForRequirements = z;
        return z5;
    }

    public void addDownload(DownloadRequest downloadRequest) {
        addDownload(downloadRequest, 0);
    }

    public void addListener(Listener listener) {
        listener.getClass();
        this.listeners.add(listener);
    }

    public Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    public List<Download> getCurrentDownloads() {
        return this.downloads;
    }

    public DownloadIndex getDownloadIndex() {
        return this.downloadIndex;
    }

    public boolean getDownloadsPaused() {
        return this.downloadsPaused;
    }

    public int getMaxParallelDownloads() {
        return this.maxParallelDownloads;
    }

    public int getMinRetryCount() {
        return this.minRetryCount;
    }

    public int getNotMetRequirements() {
        return this.notMetRequirements;
    }

    public Requirements getRequirements() {
        return this.requirementsWatcher.getRequirements();
    }

    public boolean isIdle() {
        return this.activeTaskCount == 0 && this.pendingMessages == 0;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isWaitingForRequirements() {
        return this.waitingForRequirements;
    }

    public void pauseDownloads() {
        setDownloadsPaused(true);
    }

    public void release() {
        synchronized (this.internalHandler) {
            try {
                InternalHandler internalHandler = this.internalHandler;
                if (internalHandler.released) {
                    return;
                }
                internalHandler.sendEmptyMessage(13);
                boolean z = false;
                while (true) {
                    InternalHandler internalHandler2 = this.internalHandler;
                    if (internalHandler2.released) {
                        break;
                    }
                    try {
                        internalHandler2.wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
                if (z) {
                    Thread.currentThread().interrupt();
                }
                this.applicationHandler.removeCallbacksAndMessages(null);
                this.requirementsWatcher.stop();
                this.downloads = Collections.EMPTY_LIST;
                this.pendingMessages = 0;
                this.activeTaskCount = 0;
                this.initialized = false;
                this.notMetRequirements = 0;
                this.waitingForRequirements = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeAllDownloads() {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(9).sendToTarget();
    }

    public void removeDownload(String str) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(8, str).sendToTarget();
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void resumeDownloads() {
        setDownloadsPaused(false);
    }

    public void setMaxParallelDownloads(int i10) {
        ac.b.j(i10 > 0);
        if (this.maxParallelDownloads == i10) {
            return;
        }
        this.maxParallelDownloads = i10;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(5, i10, 0).sendToTarget();
    }

    public void setMinRetryCount(int i10) {
        ac.b.j(i10 >= 0);
        if (this.minRetryCount == i10) {
            return;
        }
        this.minRetryCount = i10;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(6, i10, 0).sendToTarget();
    }

    public void setRequirements(Requirements requirements) {
        if (requirements.equals(this.requirementsWatcher.getRequirements())) {
            return;
        }
        this.requirementsWatcher.stop();
        RequirementsWatcher requirementsWatcher = new RequirementsWatcher(this.context, this.requirementsListener, requirements);
        this.requirementsWatcher = requirementsWatcher;
        onRequirementsStateChanged(this.requirementsWatcher, requirementsWatcher.start());
    }

    public void setStopReason(String str, int i10) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(4, i10, 0, str).sendToTarget();
    }

    public void addDownload(DownloadRequest downloadRequest, int i10) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(7, i10, 0, downloadRequest).sendToTarget();
    }

    public DownloadManager(Context context, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory) {
        this.context = context.getApplicationContext();
        this.downloadIndex = writableDownloadIndex;
        this.maxParallelDownloads = 3;
        this.minRetryCount = 5;
        this.downloadsPaused = true;
        this.downloads = Collections.EMPTY_LIST;
        this.listeners = new CopyOnWriteArraySet<>();
        Handler handlerCreateHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper(new d(this, 1));
        this.applicationHandler = handlerCreateHandlerForCurrentOrMainLooper;
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadManager");
        handlerThread.start();
        InternalHandler internalHandler = new InternalHandler(handlerThread, writableDownloadIndex, downloaderFactory, handlerCreateHandlerForCurrentOrMainLooper, this.maxParallelDownloads, this.minRetryCount, this.downloadsPaused);
        this.internalHandler = internalHandler;
        b bVar = new b(this);
        this.requirementsListener = bVar;
        RequirementsWatcher requirementsWatcher = new RequirementsWatcher(context, bVar, DEFAULT_REQUIREMENTS);
        this.requirementsWatcher = requirementsWatcher;
        int iStart = requirementsWatcher.start();
        this.notMetRequirements = iStart;
        this.pendingMessages = 1;
        internalHandler.obtainMessage(1, iStart, 0).sendToTarget();
    }

    public static final class InternalHandler extends Handler {
        private static final int UPDATE_PROGRESS_INTERVAL_MS = 5000;
        private int activeDownloadTaskCount;
        private final HashMap<String, Task> activeTasks;
        private final WritableDownloadIndex downloadIndex;
        private final DownloaderFactory downloaderFactory;
        private final ArrayList<Download> downloads;
        private boolean downloadsPaused;
        private boolean hasActiveRemoveTask;
        private final Handler mainHandler;
        private int maxParallelDownloads;
        private int minRetryCount;
        private int notMetRequirements;
        public boolean released;
        private final HandlerThread thread;

        public InternalHandler(HandlerThread handlerThread, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory, Handler handler, int i10, int i11, boolean z) {
            super(handlerThread.getLooper());
            this.thread = handlerThread;
            this.downloadIndex = writableDownloadIndex;
            this.downloaderFactory = downloaderFactory;
            this.mainHandler = handler;
            this.maxParallelDownloads = i10;
            this.minRetryCount = i11;
            this.downloadsPaused = z;
            this.downloads = new ArrayList<>();
            this.activeTasks = new HashMap<>();
        }

        private void addDownload(DownloadRequest downloadRequest, int i10) {
            Download download = getDownload(downloadRequest.id, true);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (download != null) {
                putDownload(DownloadManager.mergeRequest(download, downloadRequest, i10, jCurrentTimeMillis));
            } else {
                putDownload(new Download(downloadRequest, i10 == 0 ? 0 : 1, jCurrentTimeMillis, jCurrentTimeMillis, -1L, i10, 0));
            }
            syncTasks();
        }

        private boolean canDownloadsRun() {
            return !this.downloadsPaused && this.notMetRequirements == 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int compareStartTimes(Download download, Download download2) {
            return Long.compare(download.startTimeMs, download2.startTimeMs);
        }

        private static Download copyDownloadWithState(Download download, int i10, int i11) {
            return new Download(download.request, i10, download.startTimeMs, System.currentTimeMillis(), download.contentLength, i11, 0, download.progress);
        }

        private Download getDownload(String str, boolean z) {
            int downloadIndex = getDownloadIndex(str);
            if (downloadIndex != -1) {
                return this.downloads.get(downloadIndex);
            }
            if (!z) {
                return null;
            }
            try {
                return this.downloadIndex.getDownload(str);
            } catch (IOException e5) {
                Log.e(DownloadManager.TAG, "Failed to load download: " + str, e5);
                return null;
            }
        }

        private int getDownloadIndex(String str) {
            for (int i10 = 0; i10 < this.downloads.size(); i10++) {
                if (this.downloads.get(i10).request.id.equals(str)) {
                    return i10;
                }
            }
            return -1;
        }

        private void initialize(int i10) {
            this.notMetRequirements = i10;
            DownloadCursor downloads = null;
            try {
                try {
                    this.downloadIndex.setDownloadingStatesToQueued();
                    downloads = this.downloadIndex.getDownloads(0, 1, 2, 5, 7);
                    while (downloads.moveToNext()) {
                        this.downloads.add(downloads.getDownload());
                    }
                } catch (IOException e5) {
                    Log.e(DownloadManager.TAG, "Failed to load index.", e5);
                    this.downloads.clear();
                }
                this.mainHandler.obtainMessage(1, new ArrayList(this.downloads)).sendToTarget();
                syncTasks();
            } finally {
                Util.closeQuietly(downloads);
            }
        }

        private void onContentLengthChanged(Task task, long j10) {
            Download download = getDownload(task.request.id, false);
            download.getClass();
            if (j10 == download.contentLength || j10 == -1) {
                return;
            }
            putDownload(new Download(download.request, download.state, download.startTimeMs, System.currentTimeMillis(), j10, download.stopReason, download.failureReason, download.progress));
        }

        private void onDownloadTaskStopped(Download download, Exception exc) {
            Download download2 = new Download(download.request, exc == null ? 3 : 4, download.startTimeMs, System.currentTimeMillis(), download.contentLength, download.stopReason, exc == null ? 0 : 1, download.progress);
            this.downloads.remove(getDownloadIndex(download2.request.id));
            try {
                this.downloadIndex.putDownload(download2);
            } catch (IOException e5) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e5);
            }
            this.mainHandler.obtainMessage(3, new DownloadUpdate(download2, false, new ArrayList(this.downloads), exc)).sendToTarget();
        }

        private void onRemoveTaskStopped(Download download) {
            if (download.state == 7) {
                int i10 = download.stopReason;
                putDownloadWithState(download, i10 == 0 ? 0 : 1, i10);
                syncTasks();
            } else {
                this.downloads.remove(getDownloadIndex(download.request.id));
                try {
                    this.downloadIndex.removeDownload(download.request.id);
                } catch (IOException unused) {
                    Log.e(DownloadManager.TAG, "Failed to remove from database");
                }
                this.mainHandler.obtainMessage(3, new DownloadUpdate(download, true, new ArrayList(this.downloads), null)).sendToTarget();
            }
        }

        private void onTaskStopped(Task task) {
            String str = task.request.id;
            this.activeTasks.remove(str);
            boolean z = task.isRemove;
            if (z) {
                this.hasActiveRemoveTask = false;
            } else {
                int i10 = this.activeDownloadTaskCount - 1;
                this.activeDownloadTaskCount = i10;
                if (i10 == 0) {
                    removeMessages(12);
                }
            }
            if (task.isCanceled) {
                syncTasks();
                return;
            }
            Exception exc = task.finalException;
            if (exc != null) {
                Log.e(DownloadManager.TAG, "Task failed: " + task.request + ", " + z, exc);
            }
            Download download = getDownload(str, false);
            download.getClass();
            int i11 = download.state;
            if (i11 == 2) {
                ac.b.s(!z);
                onDownloadTaskStopped(download, exc);
            } else {
                if (i11 != 5 && i11 != 7) {
                    throw new IllegalStateException();
                }
                ac.b.s(z);
                onRemoveTaskStopped(download);
            }
            syncTasks();
        }

        private Download putDownload(Download download) {
            int i10 = download.state;
            ac.b.s((i10 == 3 || i10 == 4) ? false : true);
            int downloadIndex = getDownloadIndex(download.request.id);
            if (downloadIndex == -1) {
                this.downloads.add(download);
                Collections.sort(this.downloads, new e());
            } else {
                boolean z = download.startTimeMs != this.downloads.get(downloadIndex).startTimeMs;
                this.downloads.set(downloadIndex, download);
                if (z) {
                    Collections.sort(this.downloads, new e());
                }
            }
            try {
                this.downloadIndex.putDownload(download);
            } catch (IOException e5) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e5);
            }
            this.mainHandler.obtainMessage(3, new DownloadUpdate(download, false, new ArrayList(this.downloads), null)).sendToTarget();
            return download;
        }

        private Download putDownloadWithState(Download download, int i10, int i11) {
            ac.b.s((i10 == 3 || i10 == 4) ? false : true);
            return putDownload(copyDownloadWithState(download, i10, i11));
        }

        private void release() {
            Iterator<Task> it = this.activeTasks.values().iterator();
            while (it.hasNext()) {
                it.next().cancel(true);
            }
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
            } catch (IOException e5) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e5);
            }
            this.downloads.clear();
            this.thread.quit();
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        }

        private void removeAllDownloads() {
            ArrayList arrayList = new ArrayList();
            try {
                DownloadCursor downloads = this.downloadIndex.getDownloads(3, 4);
                while (downloads.moveToNext()) {
                    try {
                        arrayList.add(downloads.getDownload());
                    } finally {
                    }
                }
                downloads.close();
            } catch (IOException unused) {
                Log.e(DownloadManager.TAG, "Failed to load downloads.");
            }
            for (int i10 = 0; i10 < this.downloads.size(); i10++) {
                ArrayList<Download> arrayList2 = this.downloads;
                arrayList2.set(i10, copyDownloadWithState(arrayList2.get(i10), 5, 0));
            }
            for (int i11 = 0; i11 < arrayList.size(); i11++) {
                this.downloads.add(copyDownloadWithState((Download) arrayList.get(i11), 5, 0));
            }
            Collections.sort(this.downloads, new e());
            try {
                this.downloadIndex.setStatesToRemoving();
            } catch (IOException e5) {
                Log.e(DownloadManager.TAG, "Failed to update index.", e5);
            }
            ArrayList arrayList3 = new ArrayList(this.downloads);
            for (int i12 = 0; i12 < this.downloads.size(); i12++) {
                this.mainHandler.obtainMessage(3, new DownloadUpdate(this.downloads.get(i12), false, arrayList3, null)).sendToTarget();
            }
            syncTasks();
        }

        private void removeDownload(String str) {
            Download download = getDownload(str, true);
            if (download != null) {
                putDownloadWithState(download, 5, 0);
                syncTasks();
            } else {
                Log.e(DownloadManager.TAG, "Failed to remove nonexistent download: " + str);
            }
        }

        private void setDownloadsPaused(boolean z) {
            this.downloadsPaused = z;
            syncTasks();
        }

        private void setMaxParallelDownloads(int i10) {
            this.maxParallelDownloads = i10;
            syncTasks();
        }

        private void setMinRetryCount(int i10) {
            this.minRetryCount = i10;
        }

        private void setNotMetRequirements(int i10) {
            this.notMetRequirements = i10;
            syncTasks();
        }

        private void setStopReason(String str, int i10) {
            if (str == null) {
                for (int i11 = 0; i11 < this.downloads.size(); i11++) {
                    setStopReason(this.downloads.get(i11), i10);
                }
                try {
                    this.downloadIndex.setStopReason(i10);
                } catch (IOException e5) {
                    Log.e(DownloadManager.TAG, "Failed to set manual stop reason", e5);
                }
            } else {
                Download download = getDownload(str, false);
                if (download != null) {
                    setStopReason(download, i10);
                } else {
                    try {
                        this.downloadIndex.setStopReason(str, i10);
                    } catch (IOException e6) {
                        Log.e(DownloadManager.TAG, "Failed to set manual stop reason: ".concat(str), e6);
                    }
                }
            }
            syncTasks();
        }

        private void syncDownloadingDownload(Task task, Download download, int i10) {
            ac.b.s(!task.isRemove);
            if (!canDownloadsRun() || i10 >= this.maxParallelDownloads) {
                putDownloadWithState(download, 0, 0);
                task.cancel(false);
            }
        }

        private Task syncQueuedDownload(Task task, Download download) {
            if (task != null) {
                ac.b.s(!task.isRemove);
                task.cancel(false);
                return task;
            }
            if (!canDownloadsRun() || this.activeDownloadTaskCount >= this.maxParallelDownloads) {
                return null;
            }
            Download downloadPutDownloadWithState = putDownloadWithState(download, 2, 0);
            Task task2 = new Task(downloadPutDownloadWithState.request, this.downloaderFactory.createDownloader(downloadPutDownloadWithState.request), downloadPutDownloadWithState.progress, false, this.minRetryCount, this);
            this.activeTasks.put(downloadPutDownloadWithState.request.id, task2);
            int i10 = this.activeDownloadTaskCount;
            this.activeDownloadTaskCount = i10 + 1;
            if (i10 == 0) {
                sendEmptyMessageDelayed(12, 5000L);
            }
            task2.start();
            return task2;
        }

        private void syncRemovingDownload(Task task, Download download) {
            if (task != null) {
                if (task.isRemove) {
                    return;
                }
                task.cancel(false);
            } else {
                if (this.hasActiveRemoveTask) {
                    return;
                }
                Task task2 = new Task(download.request, this.downloaderFactory.createDownloader(download.request), download.progress, true, this.minRetryCount, this);
                this.activeTasks.put(download.request.id, task2);
                this.hasActiveRemoveTask = true;
                task2.start();
            }
        }

        private void syncStoppedDownload(Task task) {
            if (task != null) {
                ac.b.s(!task.isRemove);
                task.cancel(false);
            }
        }

        private void syncTasks() {
            int i10 = 0;
            for (int i11 = 0; i11 < this.downloads.size(); i11++) {
                Download download = this.downloads.get(i11);
                Task taskSyncQueuedDownload = this.activeTasks.get(download.request.id);
                int i12 = download.state;
                if (i12 == 0) {
                    taskSyncQueuedDownload = syncQueuedDownload(taskSyncQueuedDownload, download);
                } else if (i12 == 1) {
                    syncStoppedDownload(taskSyncQueuedDownload);
                } else if (i12 == 2) {
                    taskSyncQueuedDownload.getClass();
                    syncDownloadingDownload(taskSyncQueuedDownload, download, i10);
                } else {
                    if (i12 != 5 && i12 != 7) {
                        throw new IllegalStateException();
                    }
                    syncRemovingDownload(taskSyncQueuedDownload, download);
                }
                if (taskSyncQueuedDownload != null && !taskSyncQueuedDownload.isRemove) {
                    i10++;
                }
            }
        }

        private void updateProgress() {
            for (int i10 = 0; i10 < this.downloads.size(); i10++) {
                Download download = this.downloads.get(i10);
                if (download.state == 2) {
                    try {
                        this.downloadIndex.putDownload(download);
                    } catch (IOException e5) {
                        Log.e(DownloadManager.TAG, "Failed to update index.", e5);
                    }
                }
            }
            sendEmptyMessageDelayed(12, 5000L);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i10 = 0;
            switch (message.what) {
                case 1:
                    initialize(message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 2:
                    setDownloadsPaused(message.arg1 != 0);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 3:
                    setNotMetRequirements(message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 4:
                    setStopReason((String) message.obj, message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 5:
                    setMaxParallelDownloads(message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 6:
                    setMinRetryCount(message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 7:
                    addDownload((DownloadRequest) message.obj, message.arg1);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 8:
                    removeDownload((String) message.obj);
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 9:
                    removeAllDownloads();
                    i10 = 1;
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 10:
                    onTaskStopped((Task) message.obj);
                    this.mainHandler.obtainMessage(2, i10, this.activeTasks.size()).sendToTarget();
                    return;
                case 11:
                    onContentLengthChanged((Task) message.obj, Util.toLong(message.arg1, message.arg2));
                    return;
                case 12:
                    updateProgress();
                    return;
                case 13:
                    release();
                    return;
                default:
                    throw new IllegalStateException();
            }
        }

        private void setStopReason(Download download, int i10) {
            if (i10 == 0) {
                if (download.state == 1) {
                    putDownloadWithState(download, 0, 0);
                }
            } else if (i10 != download.stopReason) {
                int i11 = download.state;
                if (i11 == 0 || i11 == 2) {
                    i11 = 1;
                }
                putDownload(new Download(download.request, i11, download.startTimeMs, System.currentTimeMillis(), download.contentLength, i10, 0, download.progress));
            }
        }
    }
}
