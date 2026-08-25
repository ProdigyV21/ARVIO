package androidx.activity;

import android.window.OnBackInvokedCallback;
import androidx.appcompat.app.s0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g0 implements OnBackInvokedCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f945b;

    public /* synthetic */ g0(Object obj, int i10) {
        this.f944a = i10;
        this.f945b = obj;
    }

    public final void onBackInvoked() {
        switch (this.f944a) {
            case 0:
                ((f0) this.f945b).invoke();
                break;
            case 1:
                ((s0) this.f945b).F();
                break;
            default:
                ((Runnable) this.f945b).run();
                break;
        }
    }
}
