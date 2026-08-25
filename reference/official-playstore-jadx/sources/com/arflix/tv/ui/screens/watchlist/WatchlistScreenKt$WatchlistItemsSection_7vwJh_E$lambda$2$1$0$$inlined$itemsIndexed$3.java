package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.MediaCardKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class WatchlistScreenKt$WatchlistItemsSection_7vwJh_E$lambda$2$1$0$$inlined$itemsIndexed$3 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ float $cardWidth$inlined;
    final /* synthetic */ int $focusedItemIndex$inlined;
    final /* synthetic */ boolean $isLandscape$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ Map $logoUrls$inlined;
    final /* synthetic */ r7.l $onItemClick$inlined;
    final /* synthetic */ r7.l $onItemFocused$inlined;
    final /* synthetic */ r7.l $onItemLongPress$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistItemsSection_7vwJh_E$lambda$2$1$0$$inlined$itemsIndexed$3(List list, float f10, boolean z, Map map, int i10, r7.l lVar, r7.l lVar2, r7.l lVar3) {
        super(4);
        this.$items = list;
        this.$cardWidth$inlined = f10;
        this.$isLandscape$inlined = z;
        this.$logoUrls$inlined = map;
        this.$focusedItemIndex$inlined = i10;
        this.$onItemFocused$inlined = lVar;
        this.$onItemClick$inlined = lVar2;
        this.$onItemLongPress$inlined = lVar3;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
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
        final MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(-1393704566);
        float f10 = this.$cardWidth$inlined;
        int i13 = i12;
        boolean z = this.$isLandscape$inlined;
        String str = (String) this.$logoUrls$inlined.get(WatchlistViewModelKt.watchlistLogoKey(mediaItem));
        int i14 = this.$focusedItemIndex$inlined;
        boolean z5 = i10 == i14 && i14 >= 0;
        boolean zChanged = composer.changed(this.$onItemFocused$inlined) | ((((i13 & 112) ^ 48) > 32 && composer.changed(i10)) || (i13 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onItemFocused$inlined;
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistItemsSection$3$2$1$3$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6468invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6468invoke() {
                    lVar.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChanged2 = composer.changed(this.$onItemClick$inlined) | composer.changed(mediaItem);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar2 = this.$onItemClick$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistItemsSection$3$2$1$3$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6469invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6469invoke() {
                    lVar2.invoke(mediaItem);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar2 = (r7.a) objRememberedValue2;
        boolean zChanged3 = composer.changed(this.$onItemLongPress$inlined) | composer.changed(mediaItem);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar3 = this.$onItemLongPress$inlined;
            objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistItemsSection$3$2$1$3$3$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6470invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6470invoke() {
                    lVar3.invoke(mediaItem);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        MediaCardKt.m6165MediaCardL7BDBA(mediaItem, f10, z, str, null, false, false, false, false, false, true, false, 2, 0, z5, 0.0f, false, aVar, aVar2, (r7.a) objRememberedValue3, null, composer, 0, 1573254, 0, 1092592);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
