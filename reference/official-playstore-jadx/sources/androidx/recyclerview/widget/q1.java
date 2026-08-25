package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class q1 extends androidx.core.view.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r1 f5013i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final WeakHashMap f5014l = new WeakHashMap();

    public q1(r1 r1Var) {
        this.f5013i = r1Var;
    }

    @Override // androidx.core.view.b
    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        return bVar != null ? bVar.dispatchPopulateAccessibilityEvent(view, accessibilityEvent) : super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // androidx.core.view.b
    public final q0.l getAccessibilityNodeProvider(View view) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        return bVar != null ? bVar.getAccessibilityNodeProvider(view) : super.getAccessibilityNodeProvider(view);
    }

    @Override // androidx.core.view.b
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            bVar.onInitializeAccessibilityEvent(view, accessibilityEvent);
        } else {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // androidx.core.view.b
    public final void onInitializeAccessibilityNodeInfo(View view, q0.k kVar) {
        r1 r1Var = this.f5013i;
        RecyclerView recyclerView = r1Var.f5038i;
        RecyclerView recyclerView2 = r1Var.f5038i;
        if (recyclerView.I() || recyclerView2.getLayoutManager() == null) {
            super.onInitializeAccessibilityNodeInfo(view, kVar);
            return;
        }
        recyclerView2.getLayoutManager().b0(view, kVar);
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            bVar.onInitializeAccessibilityNodeInfo(view, kVar);
        } else {
            super.onInitializeAccessibilityNodeInfo(view, kVar);
        }
    }

    @Override // androidx.core.view.b
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            bVar.onPopulateAccessibilityEvent(view, accessibilityEvent);
        } else {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // androidx.core.view.b
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(viewGroup);
        return bVar != null ? bVar.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent) : super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // androidx.core.view.b
    public final boolean performAccessibilityAction(View view, int i10, Bundle bundle) {
        r1 r1Var = this.f5013i;
        RecyclerView recyclerView = r1Var.f5038i;
        RecyclerView recyclerView2 = r1Var.f5038i;
        if (recyclerView.I() || recyclerView2.getLayoutManager() == null) {
            return super.performAccessibilityAction(view, i10, bundle);
        }
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            if (bVar.performAccessibilityAction(view, i10, bundle)) {
                return true;
            }
        } else if (super.performAccessibilityAction(view, i10, bundle)) {
            return true;
        }
        f1 f1Var = recyclerView2.getLayoutManager().f5114b.f4837m;
        return false;
    }

    @Override // androidx.core.view.b
    public final void sendAccessibilityEvent(View view, int i10) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            bVar.sendAccessibilityEvent(view, i10);
        } else {
            super.sendAccessibilityEvent(view, i10);
        }
    }

    @Override // androidx.core.view.b
    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        androidx.core.view.b bVar = (androidx.core.view.b) this.f5014l.get(view);
        if (bVar != null) {
            bVar.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        } else {
            super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }
}
