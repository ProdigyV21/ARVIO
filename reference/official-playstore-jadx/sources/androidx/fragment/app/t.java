package androidx.fragment.app;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c0 f2842a;

    public t(c0 c0Var) {
        this.f2842a = c0Var;
    }

    @Override // androidx.fragment.app.z
    public final void a() {
        c0 c0Var = this.f2842a;
        c0Var.mSavedStateRegistryController.a();
        androidx.lifecycle.z0.d(c0Var);
    }
}
