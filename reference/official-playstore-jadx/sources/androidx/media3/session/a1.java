package androidx.media3.session;

import android.os.Handler;
import android.os.Message;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaControllerImplLegacy;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a1 implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3918i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3919l;

    public /* synthetic */ a1(Object obj, int i10) {
        this.f3918i = i10;
        this.f3919l = obj;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.f3918i) {
            case 0:
                return ((MediaControllerImplBase.FlushCommandQueueHandler) this.f3919l).handleMessage(message);
            default:
                return ((MediaControllerImplLegacy.ControllerCompatCallback) this.f3919l).lambda$new$0(message);
        }
    }
}
