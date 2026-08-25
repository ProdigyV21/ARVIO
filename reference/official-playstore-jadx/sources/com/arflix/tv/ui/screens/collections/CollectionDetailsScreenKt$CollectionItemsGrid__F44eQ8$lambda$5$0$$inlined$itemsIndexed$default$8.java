package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.tv.foundation.lazy.grid.q0;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.components.MediaCardKt;
import java.util.List;
import java.util.Map;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/tv/foundation/lazy/grid/q0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/grid/q0;ILandroidx/compose/runtime/Composer;I)V", "androidx/tv/foundation/lazy/grid/LazyGridDslKt$itemsIndexed$5", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$8 extends kotlin.jvm.internal.r implements r7.r<q0, Integer, Composer, Integer, t0> {
    final /* synthetic */ Map $cardLogoUrls$inlined;
    final /* synthetic */ float $cardWidth$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ List $items$inlined;
    final /* synthetic */ r7.a $onClearPendingFocus$inlined;
    final /* synthetic */ r7.l $onItemClick$inlined;
    final /* synthetic */ r7.p $onItemFocused$inlined;
    final /* synthetic */ r7.a $onNearEnd$inlined;
    final /* synthetic */ int $pendingFocusIndex$inlined;
    final /* synthetic */ boolean $usePosterCards$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionItemsGrid__F44eQ8$lambda$5$0$$inlined$itemsIndexed$default$8(List list, Map map, int i10, r7.a aVar, float f10, boolean z, r7.p pVar, List list2, r7.a aVar2, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$cardLogoUrls$inlined = map;
        this.$pendingFocusIndex$inlined = i10;
        this.$onClearPendingFocus$inlined = aVar;
        this.$cardWidth$inlined = f10;
        this.$usePosterCards$inlined = z;
        this.$onItemFocused$inlined = pVar;
        this.$items$inlined = list2;
        this.$onNearEnd$inlined = aVar2;
        this.$onItemClick$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((q0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return t0.f22605a;
    }

    public final void invoke(q0 q0Var, final int i10, Composer composer, int i11) {
        int i12;
        int i13;
        FocusRequester focusRequester;
        final MediaItem mediaItem;
        if ((i11 & 6) == 0) {
            i12 = i11 | (composer.changed(q0Var) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composer.changed(i10) ? 32 : 16;
        }
        int i14 = i12;
        if ((i14 & 147) == 146 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-619965551, i14, -1, "androidx.tv.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:559)");
        }
        MediaItem mediaItem2 = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(1289387330);
        String str = (String) this.$cardLogoUrls$inlined.get(mediaItem2.getMediaType() + "_" + mediaItem2.getId());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = androidx.compose.foundation.c.h(composer);
        }
        FocusRequester focusRequester2 = (FocusRequester) objRememberedValue;
        Integer numValueOf = Integer.valueOf(this.$pendingFocusIndex$inlined);
        int i15 = (i14 & 112) ^ 48;
        boolean zChanged = composer.changed(this.$pendingFocusIndex$inlined) | ((i15 > 32 && composer.changed(i10)) || (i14 & 48) == 32) | composer.changed(this.$onClearPendingFocus$inlined);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            i13 = 0;
            Object collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1 = new CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1(this.$pendingFocusIndex$inlined, i10, this.$onClearPendingFocus$inlined, focusRequester2, null);
            composer.updateRememberedValue(collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1);
            objRememberedValue2 = collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1;
        } else {
            i13 = 0;
        }
        EffectsKt.LaunchedEffect(numValueOf, (r7.p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, i13);
        float f10 = this.$cardWidth$inlined;
        boolean z = this.$usePosterCards$inlined;
        boolean z5 = !z;
        int i16 = z ? 2 : 1;
        int i17 = (composer.changed(this.$onItemFocused$inlined) ? 1 : 0) | (composer.changed(mediaItem2) ? 1 : 0) | (((i15 <= 32 || !composer.changed(i10)) && (i14 & 48) != 32) ? i13 : 1) | (composer.changed(this.$items$inlined) ? 1 : 0) | (composer.changed(this.$onNearEnd$inlined) ? 1 : 0);
        Object objRememberedValue3 = composer.rememberedValue();
        if (i17 != 0 || objRememberedValue3 == companion.getEmpty()) {
            final r7.p pVar = this.$onItemFocused$inlined;
            final List list = this.$items$inlined;
            final r7.a aVar = this.$onNearEnd$inlined;
            focusRequester = focusRequester2;
            mediaItem = mediaItem2;
            Object obj = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6198invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6198invoke() {
                    pVar.invoke(mediaItem, Integer.valueOf(i10));
                    if (list.size() <= 10 || i10 < list.size() - 2) {
                        return;
                    }
                    aVar.invoke();
                }
            };
            composer.updateRememberedValue(obj);
            objRememberedValue3 = obj;
        } else {
            focusRequester = focusRequester2;
            mediaItem = mediaItem2;
        }
        r7.a aVar2 = (r7.a) objRememberedValue3;
        boolean zChanged2 = composer.changed(this.$onItemClick$inlined) | composer.changed(mediaItem);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
            final r7.l lVar = this.$onItemClick$inlined;
            objRememberedValue4 = new r7.a<t0>() { // from class: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$3$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6199invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6199invoke() {
                    lVar.invoke(mediaItem);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        MediaCardKt.m6165MediaCardL7BDBA(mediaItem, f10, z5, str, null, false, false, false, false, false, true, false, i16, 0, false, 0.0f, false, aVar2, (r7.a) objRememberedValue4, null, FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester), composer, 0, 6, 0, 650224);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
