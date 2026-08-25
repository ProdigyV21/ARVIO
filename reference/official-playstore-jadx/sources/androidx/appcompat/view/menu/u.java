package androidx.appcompat.view.menu;

import android.view.ActionProvider;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class u extends androidx.core.view.e implements ActionProvider.VisibilityListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ActionProvider f1390a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y f1391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a8.e f1392c;

    public u(y yVar, ActionProvider actionProvider) {
        this.f1391b = yVar;
        this.f1390a = actionProvider;
    }

    @Override // androidx.core.view.e
    public final boolean a() {
        return this.f1390a.isVisible();
    }

    @Override // androidx.core.view.e
    public final View b(t tVar) {
        return this.f1390a.onCreateActionView(tVar);
    }

    @Override // androidx.core.view.e
    public final boolean c() {
        return this.f1390a.overridesItemVisibility();
    }

    @Override // androidx.core.view.e
    public final void d(a8.e eVar) {
        this.f1392c = eVar;
        this.f1390a.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z) {
        a8.e eVar = this.f1392c;
        if (eVar != null) {
            q qVar = ((t) eVar.f183l).f1379n;
            qVar.f1349h = true;
            qVar.p(true);
        }
    }
}
