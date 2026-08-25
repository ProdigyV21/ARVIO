package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import com.arflix.tv.data.model.Subtitle;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$2$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ int $headerCount$inlined;
    final /* synthetic */ boolean $isAiTranslating$inlined;
    final /* synthetic */ boolean $isLiveAudioTranslating$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ Subtitle $selectedSubtitle$inlined;
    final /* synthetic */ String $streamSource$inlined;
    final /* synthetic */ int $subtitlePanelFocus$inlined;
    final /* synthetic */ int $subtitleTrackIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$2$0$$inlined$itemsIndexed$default$3(List list, String str, Subtitle subtitle, int i10, boolean z, boolean z5, int i11, int i12) {
        super(4);
        this.$items = list;
        this.$streamSource$inlined = str;
        this.$selectedSubtitle$inlined = subtitle;
        this.$headerCount$inlined = i10;
        this.$isAiTranslating$inlined = z;
        this.$isLiveAudioTranslating$inlined = z5;
        this.$subtitlePanelFocus$inlined = i11;
        this.$subtitleTrackIndex$inlined = i12;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        int i12;
        String strC;
        String strU0;
        String str;
        String str2;
        boolean z;
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
        x6.x xVar = (x6.x) this.$items.get(i10);
        composer.startReplaceGroup(997957695);
        Subtitle subtitle = (Subtitle) xVar.f22609l;
        int iSubtitleMatchScore = PlayerScreenKt.subtitleMatchScore(this.$streamSource$inlined, subtitle);
        String fullLanguageName = PlayerScreenKt.getFullLanguageName(subtitle.getLang());
        Long lMatchedOffsetMsFor = PlayerScreenKt.matchedOffsetMsFor(this.$selectedSubtitle$inlined, subtitle.getId());
        if (lMatchedOffsetMsFor == null || (strC = androidx.compose.material3.d.C(" · ", PlayerScreenKt.formatMatchOffset(lMatchedOffsetMsFor.longValue()))) == null) {
            strC = "";
        }
        if (!subtitle.isEmbedded() && iSubtitleMatchScore > 0) {
            fullLanguageName = fullLanguageName + " (" + iSubtitleMatchScore + "%)";
        }
        String strM = androidx.compose.material3.d.m(fullLanguageName, strC);
        String str3 = null;
        if (subtitle.isEmbedded() && kotlin.text.o.h0(subtitle.getUrl())) {
            String fullLanguageName2 = PlayerScreenKt.getFullLanguageName(subtitle.getLang());
            String label = subtitle.getLabel();
            if (kotlin.text.o.h0(label) || label.equalsIgnoreCase(fullLanguageName2)) {
                label = null;
            }
            strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{"Built-in", label, subtitle.isForced() ? "Forced" : null}), " · ", null, null, null, 62);
        } else {
            String provider = subtitle.getProvider();
            if (kotlin.text.o.h0(provider)) {
                provider = null;
            }
            strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{provider, subtitle.isForced() ? "Forced" : null}), " · ", null, null, null, 62);
            if (kotlin.text.o.h0(strU0)) {
                strU0 = null;
            }
            String strO = com.arflix.tv.data.repository.g.o(PlayerScreenRegexes.INSTANCE.getBRACKET_REGEX(), subtitle.getId(), "");
            if (kotlin.text.o.h0(strO)) {
                strO = subtitle.getId();
            }
            if (!kotlin.text.o.h0(strO)) {
                str3 = strO;
            }
        }
        int i13 = i10 + this.$headerCount$inlined;
        if (this.$isAiTranslating$inlined || this.$isLiveAudioTranslating$inlined || !PlayerScreenKt.isSameSubtitleTrack(this.$selectedSubtitle$inlined, subtitle.getId())) {
            str = strM;
            str2 = strU0;
            z = false;
        } else {
            str = strM;
            str2 = strU0;
            z = true;
        }
        boolean z5 = this.$subtitlePanelFocus$inlined == 1 && this.$subtitleTrackIndex$inlined == i13;
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$SubtitleMenu$8$2$2$1$3$1$3$5$1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6270invoke() {
                }

                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6270invoke();
                    return x6.t0.f22605a;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        PlayerScreenKt.TrackMenuItem(str, str2, z, z5, (r7.a) objRememberedValue, str3, composer, 24576, 0);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
