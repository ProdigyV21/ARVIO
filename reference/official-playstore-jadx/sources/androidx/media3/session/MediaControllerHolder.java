package androidx.media3.session;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaController;

/* JADX INFO: loaded from: classes3.dex */
class MediaControllerHolder<T extends MediaController> extends com.google.common.util.concurrent.p implements MediaController.ConnectionCallback {
    private boolean accepted;
    private T controller;
    private final Handler handler;

    public MediaControllerHolder(Looper looper) {
        this.handler = new Handler(looper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setController$0(MediaController mediaController) {
        if (isCancelled()) {
            mediaController.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setController$1(Runnable runnable) {
        Util.postOrRun(this.handler, runnable);
    }

    private void maybeSetException() {
        setException(new SecurityException("Session rejected the connection request."));
    }

    private void maybeSetFutureResult() {
        T t2 = this.controller;
        if (t2 == null || !this.accepted) {
            return;
        }
        set(t2);
    }

    @Override // androidx.media3.session.MediaController.ConnectionCallback
    public void onAccepted() {
        this.accepted = true;
        maybeSetFutureResult();
    }

    @Override // androidx.media3.session.MediaController.ConnectionCallback
    public void onRejected() {
        maybeSetException();
    }

    public void setController(T t2) {
        this.controller = t2;
        maybeSetFutureResult();
        addListener(new m(this, t2, 1), new o(this, 0));
    }
}
