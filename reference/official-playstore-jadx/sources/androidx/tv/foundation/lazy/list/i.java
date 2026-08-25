package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.layout.Remeasurement;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m0 f5396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5397b;

    public i(m0 m0Var, int i10) {
        this.f5396a = m0Var;
        this.f5397b = i10;
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getFirstPlacedIndex() {
        return Math.max(0, this.f5396a.a() - this.f5397b);
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final boolean getHasVisibleItems() {
        return !this.f5396a.c().getVisibleItemsInfo().isEmpty();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getItemCount() {
        return this.f5396a.c().getTotalItemsCount();
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final int getLastPlacedIndex() {
        return Math.min(getItemCount() - 1, ((b0) kotlin.collections.x.w0(this.f5396a.c().getVisibleItemsInfo())).getIndex() + this.f5397b);
    }

    @Override // androidx.tv.foundation.lazy.list.h
    public final void remeasure() {
        Remeasurement remeasurement = this.f5396a.f5439n;
        if (remeasurement != null) {
            remeasurement.forceRemeasure();
        }
    }
}
