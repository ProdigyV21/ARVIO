package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class SearchOverlayKt$SearchOverlay$lambda$16$1$1$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ FocusRequester $firstResultFocus$inlined;
    final /* synthetic */ FocusRequester $focusRequester$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onPick$inlined;
    final /* synthetic */ MutableState $results$delegate$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchOverlayKt$SearchOverlay$lambda$16$1$1$0$$inlined$itemsIndexed$default$3(List list, FocusRequester focusRequester, r7.l lVar, MutableState mutableState, FocusRequester focusRequester2) {
        super(4);
        this.$items = list;
        this.$firstResultFocus$inlined = focusRequester;
        this.$onPick$inlined = lVar;
        this.$results$delegate$inlined = mutableState;
        this.$focusRequester$inlined = focusRequester2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(final LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        r7.a aVar;
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
        SearchResult searchResult = (SearchResult) this.$items.get(i10);
        composer.startReplaceGroup(177046451);
        EnrichedChannel channel = searchResult.getChannel();
        Modifier modifierFocusRequester = (SearchOverlayKt.SearchOverlay$lambda$7(this.$results$delegate$inlined).isEmpty() || !kotlin.jvm.internal.p.a(channel.getId(), ((SearchResult) kotlin.collections.x.m0(SearchOverlayKt.SearchOverlay$lambda$7(this.$results$delegate$inlined))).getChannel().getId())) ? Modifier.INSTANCE : FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, this.$firstResultFocus$inlined);
        String matchText = searchResult.getMatchText();
        r7.l lVar = this.$onPick$inlined;
        if (SearchOverlayKt.SearchOverlay$lambda$7(this.$results$delegate$inlined).isEmpty() || !kotlin.jvm.internal.p.a(channel.getId(), ((SearchResult) kotlin.collections.x.m0(SearchOverlayKt.SearchOverlay$lambda$7(this.$results$delegate$inlined))).getChannel().getId())) {
            composer.startReplaceGroup(177629591);
            composer.endReplaceGroup();
            aVar = null;
        } else {
            composer.startReplaceGroup(177520874);
            boolean z = (((i12 & 14) ^ 6) > 4 && composer.changed(lazyItemScope)) || (i12 & 6) == 4;
            Object objRememberedValue = composer.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final FocusRequester focusRequester = this.$focusRequester$inlined;
                objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$6$2$2$1$2$1$1
                    @Override // r7.a
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m6452invoke();
                        return x6.t0.f22605a;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m6452invoke() {
                        try {
                            focusRequester.requestFocus();
                        } catch (Throwable unused) {
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            aVar = (r7.a) objRememberedValue;
            composer.endReplaceGroup();
        }
        SearchOverlayKt.SearchResultRow(channel, matchText, lVar, aVar, modifierFocusRequester, composer, 0, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
