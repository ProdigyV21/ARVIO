package androidx.fragment.app;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 extends m0 implements androidx.core.content.k, androidx.core.content.l, androidx.core.app.q1, androidx.core.app.s1, androidx.lifecycle.j1, androidx.activity.n0, androidx.activity.result.l, b2.g, g1, androidx.core.view.c0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ h0 f2731o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(h0 h0Var) {
        super(h0Var);
        this.f2731o = h0Var;
    }

    @Override // androidx.fragment.app.g1
    public final void a(c0 c0Var) {
        this.f2731o.onAttachFragment(c0Var);
    }

    @Override // androidx.core.view.c0
    public final void addMenuProvider(androidx.core.view.i0 i0Var) {
        this.f2731o.addMenuProvider(i0Var);
    }

    @Override // androidx.core.content.k
    public final void addOnConfigurationChangedListener(p0.a aVar) {
        this.f2731o.addOnConfigurationChangedListener(aVar);
    }

    @Override // androidx.core.app.q1
    public final void addOnMultiWindowModeChangedListener(p0.a aVar) {
        this.f2731o.addOnMultiWindowModeChangedListener(aVar);
    }

    @Override // androidx.core.app.s1
    public final void addOnPictureInPictureModeChangedListener(p0.a aVar) {
        this.f2731o.addOnPictureInPictureModeChangedListener(aVar);
    }

    @Override // androidx.core.content.l
    public final void addOnTrimMemoryListener(p0.a aVar) {
        this.f2731o.addOnTrimMemoryListener(aVar);
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i10) {
        return this.f2731o.findViewById(i10);
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        Window window = this.f2731o.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    @Override // androidx.activity.result.l
    public final androidx.activity.result.k getActivityResultRegistry() {
        return this.f2731o.getActivityResultRegistry();
    }

    @Override // androidx.lifecycle.y
    public final androidx.lifecycle.r getLifecycle() {
        return this.f2731o.mFragmentLifecycleRegistry;
    }

    @Override // androidx.activity.n0
    public final androidx.activity.l0 getOnBackPressedDispatcher() {
        return this.f2731o.getOnBackPressedDispatcher();
    }

    @Override // b2.g
    public final b2.e getSavedStateRegistry() {
        return this.f2731o.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.j1
    public final androidx.lifecycle.i1 getViewModelStore() {
        return this.f2731o.getViewModelStore();
    }

    @Override // androidx.core.view.c0
    public final void removeMenuProvider(androidx.core.view.i0 i0Var) {
        this.f2731o.removeMenuProvider(i0Var);
    }

    @Override // androidx.core.content.k
    public final void removeOnConfigurationChangedListener(p0.a aVar) {
        this.f2731o.removeOnConfigurationChangedListener(aVar);
    }

    @Override // androidx.core.app.q1
    public final void removeOnMultiWindowModeChangedListener(p0.a aVar) {
        this.f2731o.removeOnMultiWindowModeChangedListener(aVar);
    }

    @Override // androidx.core.app.s1
    public final void removeOnPictureInPictureModeChangedListener(p0.a aVar) {
        this.f2731o.removeOnPictureInPictureModeChangedListener(aVar);
    }

    @Override // androidx.core.content.l
    public final void removeOnTrimMemoryListener(p0.a aVar) {
        this.f2731o.removeOnTrimMemoryListener(aVar);
    }
}
