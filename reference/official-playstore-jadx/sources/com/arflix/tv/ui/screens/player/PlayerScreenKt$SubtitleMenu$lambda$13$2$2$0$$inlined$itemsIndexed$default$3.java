package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class PlayerScreenKt$SubtitleMenu$lambda$13$2$2$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ List $audioTracks$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelectAudio$inlined;
    final /* synthetic */ int $selectedAudioIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$SubtitleMenu$lambda$13$2$2$0$$inlined$itemsIndexed$default$3(List list, int i10, r7.l lVar, List list2) {
        super(4);
        this.$items = list;
        this.$selectedAudioIndex$inlined = i10;
        this.$onSelectAudio$inlined = lVar;
        this.$audioTracks$inlined = list2;
    }

    @Override // r7.r
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
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
        final AudioTrackInfo audioTrackInfo = (AudioTrackInfo) this.$items.get(i10);
        composer.startReplaceGroup(65409941);
        String fullLanguageName = PlayerScreenKt.getFullLanguageName(audioTrackInfo.getLanguage());
        String label = audioTrackInfo.getLabel();
        if (label != null) {
            if (kotlin.text.o.h0(label)) {
                label = null;
            }
            if (label != null) {
                fullLanguageName = label;
            }
        }
        String strDetectAudioCodecLabel = PlayerScreenKt.detectAudioCodecLabel(audioTrackInfo.getCodec(), fullLanguageName);
        int channelCount = audioTrackInfo.getChannelCount();
        String strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{strDetectAudioCodecLabel, channelCount != 1 ? channelCount != 2 ? channelCount != 6 ? channelCount != 8 ? audioTrackInfo.getChannelCount() > 0 ? a2.i(audioTrackInfo.getChannelCount(), "ch") : null : "7.1" : "5.1" : "Stereo" : "Mono"}), " • ", null, null, null, 62);
        if (strU0.length() == 0) {
            strU0 = null;
        }
        boolean z = i10 == this.$selectedAudioIndex$inlined;
        boolean zChanged = composer.changed(this.$onSelectAudio$inlined) | composer.changed(audioTrackInfo);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelectAudio$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$SubtitleMenu$11$3$3$1$4$1$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6269invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6269invoke() {
                    lVar.invoke(audioTrackInfo);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        PlayerScreenKt.MobileTrackItem(fullLanguageName, strU0, z, (r7.a) objRememberedValue, composer, 0);
        if (i10 < t7.a.w(this.$audioTracks$inlined)) {
            composer.startReplaceGroup(66627527);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(67062860);
            composer.endReplaceGroup();
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
