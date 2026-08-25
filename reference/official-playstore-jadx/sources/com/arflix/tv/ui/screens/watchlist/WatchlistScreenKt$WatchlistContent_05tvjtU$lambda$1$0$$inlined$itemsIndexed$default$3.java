package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.util.AppLanguageKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class WatchlistScreenKt$WatchlistContent_05tvjtU$lambda$1$0$$inlined$itemsIndexed$default$3 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ float $cardWidth$inlined;
    final /* synthetic */ int $focusedItemIndex$inlined;
    final /* synthetic */ int $focusedSectionIndex$inlined;
    final /* synthetic */ boolean $isLandscape$inlined;
    final /* synthetic */ boolean $isMobile$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ Map $logoUrls$inlined;
    final /* synthetic */ r7.l $onItemClick$inlined;
    final /* synthetic */ r7.l $onItemFocused$inlined;
    final /* synthetic */ r7.l $onItemLongPress$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistContent_05tvjtU$lambda$1$0$$inlined$itemsIndexed$default$3(List list, Map map, float f10, boolean z, boolean z5, int i10, int i11, r7.l lVar, r7.l lVar2, r7.l lVar3) {
        super(4);
        this.$items = list;
        this.$logoUrls$inlined = map;
        this.$cardWidth$inlined = f10;
        this.$isLandscape$inlined = z;
        this.$isMobile$inlined = z5;
        this.$focusedSectionIndex$inlined = i10;
        this.$focusedItemIndex$inlined = i11;
        this.$onItemFocused$inlined = lVar;
        this.$onItemClick$inlined = lVar2;
        this.$onItemLongPress$inlined = lVar3;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        String str;
        if ((i11 & 14) == 0) {
            i12 = i11 | (composer.changed(lazyItemScope) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 112) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        if ((i12 & 731) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1091073711, i12, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
        }
        x xVar = (x) this.$items.get(i10);
        composer.startReplaceGroup(2104033126);
        String str2 = (String) xVar.f22608i;
        List list = (List) xVar.f22609l;
        if (p.a(str2, "movies")) {
            composer.startReplaceGroup(899158093);
            str = "Movies";
        } else {
            composer.startReplaceGroup(899158669);
            str = "Series";
        }
        String strTr = AppLanguageKt.tr(str, composer, 6);
        composer.endReplaceGroup();
        Map map = this.$logoUrls$inlined;
        float f10 = this.$cardWidth$inlined;
        boolean z = this.$isLandscape$inlined;
        int i13 = -1;
        boolean z5 = this.$isMobile$inlined;
        if (this.$focusedSectionIndex$inlined == i10) {
            i13 = this.$focusedItemIndex$inlined;
        }
        WatchlistScreenKt.m6459WatchlistItemsSection7vwJh_E(strTr, list, map, f10, z, z5, i13, this.$onItemFocused$inlined, this.$onItemClick$inlined, this.$onItemLongPress$inlined, composer, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
