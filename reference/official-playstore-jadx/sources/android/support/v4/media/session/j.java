package android.support.v4.media.session;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f882a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(l lVar, Looper looper) {
        super(looper);
        this.f882a = lVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        m mVar;
        l lVar;
        j jVar;
        if (message.what == 1) {
            synchronized (this.f882a.mLock) {
                mVar = this.f882a.mSessionImpl.get();
                lVar = this.f882a;
                jVar = lVar.mCallbackHandler;
            }
            if (mVar == null || lVar != mVar.getCallback() || jVar == null) {
                return;
            }
            mVar.a((androidx.media.d) message.obj);
            this.f882a.handleMediaPlayPauseIfPendingOnHandler(mVar, jVar);
            mVar.a(null);
        }
    }
}
