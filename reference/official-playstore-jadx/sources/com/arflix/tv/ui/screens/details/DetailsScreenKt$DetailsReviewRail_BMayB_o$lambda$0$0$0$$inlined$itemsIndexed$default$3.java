package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.Review;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/tv/foundation/lazy/list/c0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/list/c0;ILandroidx/compose/runtime/Composer;I)V", "androidx/tv/foundation/lazy/list/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class DetailsScreenKt$DetailsReviewRail_BMayB_o$lambda$0$0$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<androidx.tv.foundation.lazy.list.c0, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ FocusSection $focusSectionForUi$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ int $reviewIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsReviewRail_BMayB_o$lambda$0$0$0$$inlined$itemsIndexed$default$3(List list, FocusSection focusSection, int i10) {
        super(4);
        this.$items = list;
        this.$focusSectionForUi$inlined = focusSection;
        this.$reviewIndex$inlined = i10;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((androidx.tv.foundation.lazy.list.c0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(androidx.tv.foundation.lazy.list.c0 c0Var, int i10, Composer composer, int i11) {
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
        Review review = (Review) this.$items.get(i10);
        composer.startReplaceGroup(87729159);
        DetailsScreenKt.ReviewCard(review, this.$focusSectionForUi$inlined == FocusSection.REVIEWS && i10 == this.$reviewIndex$inlined, composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
