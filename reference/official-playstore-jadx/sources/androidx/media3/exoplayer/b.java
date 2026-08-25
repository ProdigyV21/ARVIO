package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.SuitableOutputChecker;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements BackgroundThreadStateHandler.StateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3579i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SuitableOutputChecker.Callback f3580l;

    public /* synthetic */ b(SuitableOutputChecker.Callback callback, int i10) {
        this.f3579i = i10;
        this.f3580l = callback;
    }

    @Override // androidx.media3.common.util.BackgroundThreadStateHandler.StateChangeListener
    public final void onStateChanged(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        Boolean bool2 = (Boolean) obj2;
        switch (this.f3579i) {
            case 0:
                DefaultSuitableOutputChecker.ImplApi23.lambda$enable$0(this.f3580l, bool, bool2);
                break;
            default:
                DefaultSuitableOutputChecker.ImplApi35.lambda$enable$0(this.f3580l, bool, bool2);
                break;
        }
    }
}
