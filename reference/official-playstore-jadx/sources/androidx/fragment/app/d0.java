package androidx.fragment.app;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d0 implements b2.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2706b;

    public /* synthetic */ d0(Object obj, int i10) {
        this.f2705a = i10;
        this.f2706b = obj;
    }

    @Override // b2.d
    public final Bundle a() {
        switch (this.f2705a) {
            case 0:
                h0 h0Var = (h0) this.f2706b;
                h0Var.markFragmentsCreated();
                h0Var.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_STOP);
                return new Bundle();
            default:
                return ((b1) this.f2706b).S();
        }
    }
}
