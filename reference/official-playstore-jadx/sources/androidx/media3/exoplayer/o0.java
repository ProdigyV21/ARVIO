package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3716i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f3717l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Pair f3718m;

    public /* synthetic */ o0(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, int i10) {
        this.f3716i = i10;
        this.f3717l = forwardingEventListener;
        this.f3718m = pair;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3716i) {
            case 0:
                this.f3717l.lambda$onDrmKeysRemoved$10(this.f3718m);
                break;
            case 1:
                this.f3717l.lambda$onDrmKeysRestored$9(this.f3718m);
                break;
            default:
                this.f3717l.lambda$onDrmSessionReleased$11(this.f3718m);
                break;
        }
    }
}
