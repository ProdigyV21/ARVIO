package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/tv/foundation/lazy/list/c0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/list/c0;ILandroidx/compose/runtime/Composer;I)V", "androidx/tv/foundation/lazy/list/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class DetailsScreenKt$DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<androidx.tv.foundation.lazy.list.c0, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ State $currentOnSimilarClick$inlined;
    final /* synthetic */ FocusSection $focusSectionForUi$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ boolean $similarFixedFocus$inlined;
    final /* synthetic */ int $similarIndex$inlined;
    final /* synthetic */ Map $similarLogoUrls$inlined;
    final /* synthetic */ boolean $usePosterCards$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$3(List list, Map map, boolean z, FocusSection focusSection, int i10, boolean z5, State state) {
        super(4);
        this.$items = list;
        this.$similarLogoUrls$inlined = map;
        this.$usePosterCards$inlined = z;
        this.$focusSectionForUi$inlined = focusSection;
        this.$similarIndex$inlined = i10;
        this.$similarFixedFocus$inlined = z5;
        this.$currentOnSimilarClick$inlined = state;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((androidx.tv.foundation.lazy.list.c0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(androidx.tv.foundation.lazy.list.c0 c0Var, final int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 6) == 0) {
            i12 = (composer.changed(c0Var) ? 4 : 2) | i11;
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
            ComposerKt.traceEventStart(-906771355, i12, -1, "androidx.tv.foundation.lazy.list.itemsIndexed.<anonymous> (LazyDsl.kt:159)");
        }
        MediaItem mediaItem = (MediaItem) this.$items.get(i10);
        composer.startReplaceGroup(78308221);
        boolean z = (((i12 & 112) ^ 48) > 32 && composer.changed(i10)) || (i12 & 48) == 32;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final State state = this.$currentOnSimilarClick$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsSimilarRail$1$1$1$1$2$onClickForSimilar$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6235invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6235invoke() {
                    state.getValue().invoke(Integer.valueOf(i10));
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        DetailsScreenKt.SimilarMediaCard(mediaItem, (String) this.$similarLogoUrls$inlined.get(mediaItem.getMediaType() + "_" + mediaItem.getId()), this.$usePosterCards$inlined, this.$focusSectionForUi$inlined == FocusSection.SIMILAR && i10 == this.$similarIndex$inlined && !this.$similarFixedFocus$inlined, (r7.a) objRememberedValue, composer, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
