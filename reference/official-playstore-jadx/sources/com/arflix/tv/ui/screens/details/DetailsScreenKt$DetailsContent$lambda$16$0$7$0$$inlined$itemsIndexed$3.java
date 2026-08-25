package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class DetailsScreenKt$DetailsContent$lambda$16$0$7$0$$inlined$itemsIndexed$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSimilarClick$inlined;
    final /* synthetic */ Map $similarLogoUrls$inlined;
    final /* synthetic */ boolean $usePosterCards$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsContent$lambda$16$0$7$0$$inlined$itemsIndexed$3(List list, Map map, boolean z, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$similarLogoUrls$inlined = map;
        this.$usePosterCards$inlined = z;
        this.$onSimilarClick$inlined = lVar;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, final int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 14) == 0) {
            i12 = (composer.changed(lazyItemScope) ? 4 : 2) | i11;
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
        MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(213510576);
        String str = (String) this.$similarLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
        boolean z = this.$usePosterCards$inlined;
        boolean zChanged = ((((i12 & 112) ^ 48) > 32 && composer.changed(i10)) || (i12 & 48) == 32) | composer.changed(this.$onSimilarClick$inlined);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSimilarClick$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsContent$8$1$7$1$3$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6230invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6230invoke() {
                    lVar.invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        DetailsScreenKt.SimilarMediaCard(mediaItem, str, z, false, (r7.a) objRememberedValue, composer, 3072, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
