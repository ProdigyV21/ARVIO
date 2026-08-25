package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.Subtitle;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$0$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ String $aiTargetLanguageName$inlined;
    final /* synthetic */ boolean $isAiTranslating$inlined;
    final /* synthetic */ boolean $isFindingBestMatch$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ Subtitle $selectedSubtitle$inlined;
    final /* synthetic */ int $subtitleLangIndex$inlined;
    final /* synthetic */ int $subtitlePanelFocus$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$0$0$$inlined$itemsIndexed$default$3(List list, int i10, int i11, boolean z, boolean z5, String str, Subtitle subtitle) {
        super(4);
        this.$items = list;
        this.$subtitlePanelFocus$inlined = i10;
        this.$subtitleLangIndex$inlined = i11;
        this.$isAiTranslating$inlined = z;
        this.$isFindingBestMatch$inlined = z5;
        this.$aiTargetLanguageName$inlined = str;
        this.$selectedSubtitle$inlined = subtitle;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        boolean z;
        boolean z5;
        boolean z10;
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
        x6.x xVar = (x6.x) this.$items.get(i10);
        composer.startReplaceGroup(1441728361);
        String str = (String) xVar.f22608i;
        List list = (List) xVar.f22609l;
        int size = list.size();
        boolean z11 = false;
        boolean z12 = true;
        if (this.$subtitlePanelFocus$inlined == 0 && this.$subtitleLangIndex$inlined == i10 + 1) {
            z = false;
            z11 = true;
        } else {
            z = false;
        }
        if (this.$subtitleLangIndex$inlined == i10 + 1) {
            z5 = true;
        } else {
            z5 = true;
            z12 = z;
        }
        if ((this.$isAiTranslating$inlined || this.$isFindingBestMatch$inlined) && !kotlin.text.o.h0(this.$aiTargetLanguageName$inlined) && kotlin.text.u.L(str, this.$aiTargetLanguageName$inlined, z5)) {
            z10 = z5;
            break;
        }
        if (this.$selectedSubtitle$inlined != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (PlayerScreenKt.isSameSubtitleTrack(this.$selectedSubtitle$inlined, ((Subtitle) ((x6.x) it.next()).f22609l).getId())) {
                    z10 = z5;
                    break;
                }
            }
        }
        z10 = z;
        PlayerScreenKt.LangPanelItem(str, size, z11, z12, z10, composer, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
