package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.ExoPlayerImplInternal;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3585i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3586l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3587m;

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f3585i = i10;
        this.f3587m = obj;
        this.f3586l = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3585i) {
            case 0:
                ((DefaultSuitableOutputChecker.ImplApi23) this.f3587m).lambda$enable$1((Context) this.f3586l);
                break;
            case 1:
                ((DefaultSuitableOutputChecker.ImplApi35) this.f3587m).lambda$enable$1((Context) this.f3586l);
                break;
            case 2:
                ((ExoPlayerImpl) this.f3587m).lambda$new$1((ExoPlayerImplInternal.PlaybackInfoUpdate) this.f3586l);
                break;
            default:
                ((ExoPlayerImplInternal) this.f3587m).lambda$sendMessageToTargetThread$3((PlayerMessage) this.f3586l);
                break;
        }
    }
}
