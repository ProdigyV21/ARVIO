package com.arflix.tv.ui.screens.collections;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "", "index", "", "invoke", "(I)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$5 extends kotlin.jvm.internal.r implements r7.l<Integer, Object> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.p $key;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$5(r7.p pVar, List list) {
        super(1);
        this.$key = pVar;
        this.$items = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Object invoke(int i10) {
        return this.$key.invoke(Integer.valueOf(i10), this.$items.get(i10));
    }
}
