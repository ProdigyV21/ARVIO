package androidx.media3.exoplayer.upstream;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class Loader implements LoaderErrorThrower {
    private static final int ACTION_TYPE_DONT_RETRY = 2;
    private static final int ACTION_TYPE_DONT_RETRY_FATAL = 3;
    private static final int ACTION_TYPE_RETRY = 0;
    private static final int ACTION_TYPE_RETRY_AND_RESET_ERROR_COUNT = 1;
    public static final LoadErrorAction DONT_RETRY;
    public static final LoadErrorAction DONT_RETRY_FATAL;
    public static final LoadErrorAction RETRY;
    public static final LoadErrorAction RETRY_RESET_ERROR_COUNT;
    private static final String THREAD_NAME_PREFIX = "ExoPlayer:Loader:";
    private LoadTask<? extends Loadable> currentTask;
    private final ReleasableExecutor downloadExecutor;
    private IOException fatalError;

    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t2, long j10, long j11, boolean z);

        void onLoadCompleted(T t2, long j10, long j11);

        LoadErrorAction onLoadError(T t2, long j10, long j11, IOException iOException, int i10);

        void onLoadStarted(T t2, long j10, long j11, int i10);
    }

    public static final class LoadErrorAction {
        private final long retryDelayMillis;
        private final int type;

        public boolean isRetry() {
            int i10 = this.type;
            return i10 == 0 || i10 == 1;
        }

        private LoadErrorAction(int i10, long j10) {
            this.type = i10;
            this.retryDelayMillis = j10;
        }
    }

    public final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private static final int MSG_FATAL_ERROR = 4;
        private static final int MSG_FINISH = 2;
        private static final int MSG_IO_EXCEPTION = 3;
        private static final int MSG_START = 1;
        private static final String TAG = "LoadTask";
        private Callback<T> callback;
        private boolean canceled;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Looper looper, T t2, Callback<T> callback, int i10, long j10) {
            super(looper);
            this.loadable = t2;
            this.callback = callback;
            this.defaultMinRetryCount = i10;
            this.startTimeMs = j10;
        }

        private void execute() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j10 = jElapsedRealtime - this.startTimeMs;
            Callback<T> callback = this.callback;
            callback.getClass();
            callback.onLoadStarted(this.loadable, jElapsedRealtime, j10, this.errorCount);
            this.currentError = null;
            ReleasableExecutor releasableExecutor = Loader.this.downloadExecutor;
            LoadTask loadTask = Loader.this.currentTask;
            loadTask.getClass();
            releasableExecutor.execute(loadTask);
        }

        private void finish() {
            Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return Math.min((this.errorCount - 1) * 1000, 5000);
        }

        public void cancel(boolean z) {
            this.released = z;
            this.currentError = null;
            if (hasMessages(1)) {
                this.canceled = true;
                removeMessages(1);
                if (!z) {
                    sendEmptyMessage(2);
                }
            } else {
                synchronized (this) {
                    try {
                        this.canceled = true;
                        this.loadable.cancelLoad();
                        Thread thread = this.executorThread;
                        if (thread != null) {
                            thread.interrupt();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            if (z) {
                finish();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                Callback<T> callback = this.callback;
                callback.getClass();
                callback.onLoadCanceled(this.loadable, jElapsedRealtime, jElapsedRealtime - this.startTimeMs, true);
                this.callback = null;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.released) {
                return;
            }
            int i10 = message.what;
            if (i10 == 1) {
                execute();
                return;
            }
            if (i10 == 4) {
                throw ((Error) message.obj);
            }
            finish();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j10 = jElapsedRealtime - this.startTimeMs;
            Callback<T> callback = this.callback;
            callback.getClass();
            if (this.canceled) {
                callback.onLoadCanceled(this.loadable, jElapsedRealtime, j10, false);
                return;
            }
            int i11 = message.what;
            if (i11 == 2) {
                try {
                    callback.onLoadCompleted(this.loadable, jElapsedRealtime, j10);
                    return;
                } catch (RuntimeException e5) {
                    Log.e(TAG, "Unexpected exception handling load completed", e5);
                    Loader.this.fatalError = new UnexpectedLoaderException(e5);
                    return;
                }
            }
            if (i11 != 3) {
                return;
            }
            IOException iOException = (IOException) message.obj;
            this.currentError = iOException;
            int i12 = this.errorCount + 1;
            this.errorCount = i12;
            LoadErrorAction loadErrorActionOnLoadError = callback.onLoadError(this.loadable, jElapsedRealtime, j10, iOException, i12);
            if (loadErrorActionOnLoadError.type == 3) {
                Loader.this.fatalError = this.currentError;
            } else if (loadErrorActionOnLoadError.type != 2) {
                if (loadErrorActionOnLoadError.type == 1) {
                    this.errorCount = 1;
                }
                start(loadErrorActionOnLoadError.retryDelayMillis != C.TIME_UNSET ? loadErrorActionOnLoadError.retryDelayMillis : getRetryDelayMillis());
            }
        }

        public void maybeThrowError(int i10) throws IOException {
            IOException iOException = this.currentError;
            if (iOException != null && this.errorCount > i10) {
                throw iOException;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            try {
                synchronized (this) {
                    z = this.canceled;
                    this.executorThread = Thread.currentThread();
                }
                if (!z) {
                    TraceUtil.beginSection("load:".concat(this.loadable.getClass().getSimpleName()));
                    try {
                        this.loadable.load();
                        TraceUtil.endSection();
                    } catch (Throwable th) {
                        TraceUtil.endSection();
                        throw th;
                    }
                }
                synchronized (this) {
                    this.executorThread = null;
                    Thread.interrupted();
                }
                if (this.released) {
                    return;
                }
                sendEmptyMessage(2);
            } catch (IOException e5) {
                if (this.released) {
                    return;
                }
                obtainMessage(3, e5).sendToTarget();
            } catch (Exception e6) {
                if (this.released) {
                    return;
                }
                Log.e(TAG, "Unexpected exception loading stream", e6);
                obtainMessage(3, new UnexpectedLoaderException(e6)).sendToTarget();
            } catch (OutOfMemoryError e10) {
                if (this.released) {
                    return;
                }
                Log.e(TAG, "OutOfMemory error loading stream", e10);
                obtainMessage(3, new UnexpectedLoaderException(e10)).sendToTarget();
            } catch (Error e11) {
                if (!this.released) {
                    Log.e(TAG, "Unexpected error loading stream", e11);
                    obtainMessage(4, e11).sendToTarget();
                }
                throw e11;
            }
        }

        public void start(long j10) {
            ac.b.s(Loader.this.currentTask == null);
            Loader.this.currentTask = this;
            if (j10 > 0) {
                sendEmptyMessageDelayed(1, j10);
            } else {
                execute();
            }
        }
    }

    public interface Loadable {
        void cancelLoad();

        void load() throws IOException;
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    public static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.callback.onLoaderReleased();
        }
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable th) {
            String str;
            StringBuilder sb2 = new StringBuilder("Unexpected ");
            sb2.append(th.getClass().getSimpleName());
            if (th.getMessage() != null) {
                str = ": " + th.getMessage();
            } else {
                str = "";
            }
            sb2.append(str);
            super(sb2.toString(), th);
        }
    }

    static {
        long j10 = C.TIME_UNSET;
        RETRY = createRetryAction(false, C.TIME_UNSET);
        RETRY_RESET_ERROR_COUNT = createRetryAction(true, C.TIME_UNSET);
        DONT_RETRY = new LoadErrorAction(2, j10);
        DONT_RETRY_FATAL = new LoadErrorAction(3, j10);
    }

    public Loader(String str) {
        this(ReleasableExecutor.CC.a(Util.newSingleThreadExecutor(THREAD_NAME_PREFIX + str), new c()));
    }

    public static LoadErrorAction createRetryAction(boolean z, long j10) {
        return new LoadErrorAction(z ? 1 : 0, j10);
    }

    public void cancelLoading() {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        loadTask.getClass();
        loadTask.cancel(false);
    }

    public void clearFatalError() {
        this.fatalError = null;
    }

    public boolean hasFatalError() {
        return this.fatalError != null;
    }

    public boolean isLoading() {
        return this.currentTask != null;
    }

    @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
    public void maybeThrowError() throws IOException {
        maybeThrowError(Integer.MIN_VALUE);
    }

    public void release() {
        release(null);
    }

    public <T extends Loadable> long startLoading(T t2, Callback<T> callback, int i10) {
        Looper looperMyLooper = Looper.myLooper();
        looperMyLooper.getClass();
        this.fatalError = null;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask(looperMyLooper, t2, callback, i10, jElapsedRealtime).start(0L);
        return jElapsedRealtime;
    }

    @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
    public void maybeThrowError(int i10) throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            if (i10 == Integer.MIN_VALUE) {
                i10 = loadTask.defaultMinRetryCount;
            }
            loadTask.maybeThrowError(i10);
        }
    }

    public void release(ReleaseCallback releaseCallback) {
        LoadTask<? extends Loadable> loadTask = this.currentTask;
        if (loadTask != null) {
            loadTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutor.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutor.release();
    }

    public Loader(ReleasableExecutor releasableExecutor) {
        this.downloadExecutor = releasableExecutor;
    }
}
