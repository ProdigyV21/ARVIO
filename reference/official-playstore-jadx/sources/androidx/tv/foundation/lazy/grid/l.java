package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.internal.ComposableLambdaKt;

/* JADX INFO: loaded from: classes3.dex */
public final class l extends LazyLayoutIntervalContent implements v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k0 f5267a = new k0(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MutableIntervalList f5268b = new MutableIntervalList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5269c;

    public l(r7.l lVar) {
        lVar.invoke(this);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public final IntervalList getIntervals() {
        return this.f5268b;
    }

    @Override // androidx.tv.foundation.lazy.grid.v0
    public final void item(Object obj, r7.l lVar, Object obj2, r7.q qVar) {
        this.f5268b.addInterval(1, new i(null, new k(lVar), new androidx.navigation.r((String) obj2, 1), ComposableLambdaKt.composableLambdaInstance(659494635, true, new LazyGridIntervalContent$item$4(qVar))));
        this.f5269c = true;
    }

    @Override // androidx.tv.foundation.lazy.grid.v0
    public final void items(int i10, r7.l lVar, r7.p pVar, r7.l lVar2, r7.r rVar) {
        this.f5268b.addInterval(i10, new i(lVar, j.f5253l, lVar2, rVar));
    }
}
