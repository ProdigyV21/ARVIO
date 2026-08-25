package com.arflix.tv.ui.screens.collections;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.tv.foundation.lazy.grid.q0;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/tv/foundation/lazy/grid/q0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/grid/q0;ILandroidx/compose/runtime/Composer;I)V", "androidx/tv/foundation/lazy/grid/LazyGridDslKt$itemsIndexed$5", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$4 extends kotlin.jvm.internal.r implements r7.r<q0, Integer, Composer, Integer, t0> {
    final /* synthetic */ float $cardHeight$inlined;
    final /* synthetic */ List $items;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$4(List list, float f10) {
        super(4);
        this.$items = list;
        this.$cardHeight$inlined = f10;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((q0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(q0 q0Var, int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 6) == 0) {
            i12 = (composer.changed(q0Var) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 147) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-619965551, i12, -1, "androidx.tv.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:559)");
        }
        ((Number) this.$items.get(i10)).intValue();
        composer.startReplaceGroup(-59860402);
        BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, this.$cardHeight$inlined), 12), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.05f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
