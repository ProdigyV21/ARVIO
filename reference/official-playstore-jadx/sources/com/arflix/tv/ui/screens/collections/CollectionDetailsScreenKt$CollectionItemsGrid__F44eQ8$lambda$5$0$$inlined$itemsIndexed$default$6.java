package com.arflix.tv.ui.screens.collections;

import androidx.tv.foundation.lazy.grid.o0;
import androidx.tv.foundation.lazy.grid.s0;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/tv/foundation/lazy/grid/s0;", "", "it", "Landroidx/tv/foundation/lazy/grid/o0;", "invoke-x45P8T4", "(Landroidx/tv/foundation/lazy/grid/s0;I)J", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$6 extends kotlin.jvm.internal.r implements r7.p<s0, Integer, o0> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.q $span;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$6(r7.q qVar, List list) {
        super(2);
        this.$span = qVar;
        this.$items = list;
    }

    @Override // r7.p
    public /* synthetic */ Object invoke(Object obj, Object obj2) {
        return new o0(m6201invokex45P8T4((s0) obj, ((Number) obj2).intValue()));
    }

    /* JADX INFO: renamed from: invoke-x45P8T4, reason: not valid java name */
    public final long m6201invokex45P8T4(s0 s0Var, int i10) {
        return ((o0) this.$span.invoke(s0Var, Integer.valueOf(i10), this.$items.get(i10))).f5283a;
    }
}
