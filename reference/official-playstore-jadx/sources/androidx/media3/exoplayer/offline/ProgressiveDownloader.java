package androidx.media3.exoplayer.offline;

import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.offline.Downloader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class ProgressiveDownloader implements Downloader {
    private final CacheWriter cacheWriter;
    private final CacheDataSource dataSource;
    final DataSpec dataSpec;
    private volatile RunnableFutureTask<Void, IOException> downloadRunnable;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final PriorityTaskManager priorityTaskManager;
    private Downloader.ProgressListener progressListener;

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new androidx.credentials.a(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgress(long j10, long j11, long j12) {
        if (this.progressListener == null) {
            return;
        }
        float fPercentFloat = (j10 == -1 || j10 == 0) ? -1.0f : Util.percentFloat(j11, j10);
        Downloader.ProgressListener progressListener = this.progressListener;
        progressListener.getClass();
        progressListener.onProgress(j10, j11, fPercentFloat);
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        this.isCanceled = true;
        RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
        if (runnableFutureTask != null) {
            runnableFutureTask.cancel(true);
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void download(Downloader.ProgressListener progressListener) throws InterruptedException, IOException {
        this.progressListener = progressListener;
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null) {
            priorityTaskManager.add(-4000);
        }
        boolean z = false;
        while (!z) {
            try {
                if (this.isCanceled) {
                    break;
                }
                this.downloadRunnable = new RunnableFutureTask<Void, IOException>() { // from class: androidx.media3.exoplayer.offline.ProgressiveDownloader.1
                    @Override // androidx.media3.common.util.RunnableFutureTask
                    public void cancelWork() {
                        ProgressiveDownloader.this.cacheWriter.cancel();
                    }

                    @Override // androidx.media3.common.util.RunnableFutureTask
                    public Void doWork() throws IOException {
                        ProgressiveDownloader.this.cacheWriter.cache();
                        return null;
                    }
                };
                PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
                if (priorityTaskManager2 != null) {
                    priorityTaskManager2.proceed(-4000);
                }
                this.executor.execute(this.downloadRunnable);
                try {
                    this.downloadRunnable.get();
                    z = true;
                } catch (ExecutionException e5) {
                    Throwable cause = e5.getCause();
                    cause.getClass();
                    if (!(cause instanceof PriorityTaskManager.PriorityTooLowException)) {
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        Util.sneakyThrow(cause);
                    }
                }
            } catch (Throwable th) {
                RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
                runnableFutureTask.getClass();
                runnableFutureTask.blockUntilFinished();
                PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
                if (priorityTaskManager3 != null) {
                    priorityTaskManager3.remove(-4000);
                }
                throw th;
            }
        }
        RunnableFutureTask<Void, IOException> runnableFutureTask2 = this.downloadRunnable;
        runnableFutureTask2.getClass();
        runnableFutureTask2.blockUntilFinished();
        PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
        if (priorityTaskManager4 != null) {
            priorityTaskManager4.remove(-4000);
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void remove() {
        this.dataSource.getCache().removeResource(this.dataSource.getCacheKeyFactory().buildCacheKey(this.dataSpec));
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, long j10, long j11) {
        this(mediaItem, factory, new androidx.credentials.a(0), j10, j11);
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, factory, executor, 0L, -1L);
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor, long j10, long j11) {
        executor.getClass();
        this.executor = executor;
        mediaItem.localConfiguration.getClass();
        DataSpec dataSpecBuild = new DataSpec.Builder().setUri(mediaItem.localConfiguration.uri).setKey(mediaItem.localConfiguration.customCacheKey).setFlags(4).setPosition(j10).setLength(j11).build();
        this.dataSpec = dataSpecBuild;
        CacheDataSource cacheDataSourceCreateDataSourceForDownloading = factory.createDataSourceForDownloading();
        this.dataSource = cacheDataSourceCreateDataSourceForDownloading;
        this.cacheWriter = new CacheWriter(cacheDataSourceCreateDataSourceForDownloading, dataSpecBuild, null, new b(this));
        this.priorityTaskManager = factory.getUpstreamPriorityTaskManager();
    }
}
