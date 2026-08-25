package com.arflix.tv.ui.screens.search;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.MediaCardKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class SearchScreenKt$RowsLayer$lambda$4$0$0$1$3$0$8$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
    final /* synthetic */ Map $cardLogoUrls$inlined;
    final /* synthetic */ int $currentItemIndex$inlined;
    final /* synthetic */ boolean $isCurrentRow$inlined;
    final /* synthetic */ boolean $isPortrait$inlined;
    final /* synthetic */ boolean $isTouchDevice$inlined;
    final /* synthetic */ float $itemWidth$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onItemClick$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$RowsLayer$lambda$4$0$0$1$3$0$8$0$$inlined$itemsIndexed$default$3(List list, boolean z, int i10, float f10, boolean z5, Map map, r7.l lVar, boolean z10) {
        super(4);
        this.$items = list;
        this.$isCurrentRow$inlined = z;
        this.$currentItemIndex$inlined = i10;
        this.$itemWidth$inlined = f10;
        this.$isPortrait$inlined = z5;
        this.$cardLogoUrls$inlined = map;
        this.$onItemClick$inlined = lVar;
        this.$isTouchDevice$inlined = z10;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        Modifier modifierM214clickableXHw0xAI$default;
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
        composer.startReplaceGroup(2048798925);
        boolean z = this.$isCurrentRow$inlined && i10 == this.$currentItemIndex$inlined;
        MediaItem mediaItemCopy$default = MediaItem.copy$default(mediaItem, 0, SearchScreenKt.buildCardTitle(mediaItem), SearchScreenKt.buildCardSubtitle(mediaItem, composer, 0), null, "", null, null, null, null, null, null, null, null, 0, false, null, null, null, null, null, false, null, null, null, null, null, null, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -55, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
        float f10 = this.$itemWidth$inlined;
        boolean z5 = !this.$isPortrait$inlined;
        String str = (String) this.$cardLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$2$1$1$2$2$1$3$1$2$1$1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6287invoke() {
                }

                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6287invoke();
                    return t0.f22605a;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.a aVar = (r7.a) objRememberedValue;
        boolean zChanged = composer.changed(this.$onItemClick$inlined) | composer.changed(mediaItem);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            final r7.l lVar = this.$onItemClick$inlined;
            objRememberedValue2 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$2$1$1$2$2$1$3$1$2$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6288invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6288invoke() {
                    lVar.invoke(mediaItem);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar2 = (r7.a) objRememberedValue2;
        if (this.$isTouchDevice$inlined) {
            composer.startReplaceGroup(-1873536078);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            boolean zChanged2 = composer.changed(this.$onItemClick$inlined) | composer.changed(mediaItem);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
                final r7.l lVar2 = this.$onItemClick$inlined;
                objRememberedValue3 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.search.SearchScreenKt$RowsLayer$2$1$1$2$2$1$3$1$2$3$1
                    @Override // r7.a
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6289invoke();
                        return t0.f22605a;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6289invoke() {
                        lVar2.invoke(mediaItem);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(companion2, false, null, null, (r7.a) objRememberedValue3, 7, null);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1873534917);
            composer.endReplaceGroup();
            modifierM214clickableXHw0xAI$default = Modifier.INSTANCE;
        }
        MediaCardKt.m6165MediaCardL7BDBA(mediaItemCopy$default, f10, z5, str, null, false, false, false, false, false, false, false, 2, 1, z, 0.0f, false, aVar, aVar2, null, modifierM214clickableXHw0xAI$default, composer, 805306368, 14159232, 0, 560624);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
