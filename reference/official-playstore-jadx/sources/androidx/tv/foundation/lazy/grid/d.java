package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.layout.Remeasurement;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements androidx.tv.foundation.lazy.list.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b1 f5192a;

    public d(b1 b1Var) {
        this.f5192a = b1Var;
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getFirstPlacedIndex() {
        return ((MutableIntState) this.f5192a.f5153a.f5232b).getIntValue();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final boolean getHasVisibleItems() {
        return !((t0) this.f5192a.f5154b.getValue()).getVisibleItemsInfo().isEmpty();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getItemCount() {
        return ((t0) this.f5192a.f5154b.getValue()).getTotalItemsCount();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getLastPlacedIndex() {
        return ((p0) kotlin.collections.x.w0(((t0) this.f5192a.f5154b.getValue()).getVisibleItemsInfo())).getIndex();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final void remeasure() {
        Remeasurement remeasurement = this.f5192a.f5165n;
        if (remeasurement != null) {
            remeasurement.forceRemeasure();
        }
    }
}
