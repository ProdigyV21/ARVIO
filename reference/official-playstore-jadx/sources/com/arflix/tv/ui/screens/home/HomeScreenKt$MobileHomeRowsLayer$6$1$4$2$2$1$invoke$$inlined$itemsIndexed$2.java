package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "T", "index", "", "invoke", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$3"}, k = 3, mv = {2, 3, 0}, xi = 48)
public final class HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$invoke$$inlined$itemsIndexed$2 extends kotlin.jvm.internal.r implements r7.l<Integer, Object> {
    final /* synthetic */ List $items;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$MobileHomeRowsLayer$6$1$4$2$2$1$invoke$$inlined$itemsIndexed$2(List list) {
        super(1);
        this.$items = list;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Object invoke(int i10) {
        MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        return mediaItem.isPlaceholder() ? "placeholder_card" : androidx.compose.material3.d.m(mediaItem.getMediaType().name(), "_mobile_card");
    }
}
