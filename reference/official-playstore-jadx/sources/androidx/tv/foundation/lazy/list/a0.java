package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.internal.ComposableLambdaKt;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends LazyLayoutIntervalContent implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MutableIntervalList f5358a = new MutableIntervalList();

    public a0(r7.l lVar) {
        lVar.invoke(this);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public final IntervalList getIntervals() {
        return this.f5358a;
    }

    @Override // androidx.tv.foundation.lazy.list.g0
    public final void item(Object obj, Object obj2, r7.q qVar) {
        this.f5358a.addInterval(1, new z(obj != null ? new ab.h(obj, 5) : null, new f0(1, 2), ComposableLambdaKt.composableLambdaInstance(1749736512, true, new TvLazyListIntervalContent$item$3(qVar))));
    }

    @Override // androidx.tv.foundation.lazy.list.g0
    public final void items(int i10, r7.l lVar, r7.l lVar2, r7.r rVar) {
        this.f5358a.addInterval(i10, new z(lVar, lVar2, rVar));
    }
}
