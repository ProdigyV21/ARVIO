package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: classes3.dex */
public final class r1 extends androidx.core.view.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RecyclerView f5038i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final q1 f5039l;

    public r1(RecyclerView recyclerView) {
        this.f5038i = recyclerView;
        q1 q1Var = this.f5039l;
        if (q1Var != null) {
            this.f5039l = q1Var;
        } else {
            this.f5039l = new q1(this);
        }
    }

    @Override // androidx.core.view.b
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || this.f5038i.I()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().Z(accessibilityEvent);
        }
    }

    @Override // androidx.core.view.b
    public final void onInitializeAccessibilityNodeInfo(View view, q0.k kVar) {
        super.onInitializeAccessibilityNodeInfo(view, kVar);
        RecyclerView recyclerView = this.f5038i;
        if (recyclerView.I() || recyclerView.getLayoutManager() == null) {
            return;
        }
        z0 layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.f5114b;
        layoutManager.a0(recyclerView2.f4837m, recyclerView2.f4846q0, kVar);
    }

    @Override // androidx.core.view.b
    public final boolean performAccessibilityAction(View view, int i10, Bundle bundle) {
        if (super.performAccessibilityAction(view, i10, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.f5038i;
        if (recyclerView.I() || recyclerView.getLayoutManager() == null) {
            return false;
        }
        z0 layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.f5114b;
        return layoutManager.r0(i10, recyclerView2.f4837m, recyclerView2.f4846q0);
    }
}
