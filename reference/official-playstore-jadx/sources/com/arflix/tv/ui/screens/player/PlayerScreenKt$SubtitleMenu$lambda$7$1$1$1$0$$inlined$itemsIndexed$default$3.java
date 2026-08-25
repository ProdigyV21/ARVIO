package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u000b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "androidx/compose/foundation/lazy/LazyDslKt$itemsIndexed$4", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class PlayerScreenKt$SubtitleMenu$lambda$7$1$1$1$0$$inlined$itemsIndexed$default$3 extends kotlin.jvm.internal.r implements r7.r<LazyItemScope, Integer, Composer, Integer, x6.t0> {
    final /* synthetic */ int $focusedIndex$inlined;
    final /* synthetic */ List $items;
    final /* synthetic */ r7.l $onSelectAudio$inlined;
    final /* synthetic */ int $selectedAudioIndex$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$SubtitleMenu$lambda$7$1$1$1$0$$inlined$itemsIndexed$default$3(List list, int i10, int i11, r7.l lVar) {
        super(4);
        this.$items = list;
        this.$selectedAudioIndex$inlined = i10;
        this.$focusedIndex$inlined = i11;
        this.$onSelectAudio$inlined = lVar;
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
        composer.startReplaceGroup(-862504142);
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
        String str = strU0.length() != 0 ? strU0 : null;
        boolean z = i10 == this.$selectedAudioIndex$inlined;
        boolean z5 = this.$focusedIndex$inlined == i10;
        boolean zChanged = composer.changed(this.$onSelectAudio$inlined) | composer.changed(audioTrackInfo);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final r7.l lVar = this.$onSelectAudio$inlined;
            objRememberedValue = new r7.a<x6.t0>() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$SubtitleMenu$8$2$2$2$1$2$2$1
                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m6271invoke();
                    return x6.t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m6271invoke() {
                    lVar.invoke(audioTrackInfo);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        PlayerScreenKt.TrackMenuItem(fullLanguageName, str, z, z5, (r7.a) objRememberedValue, null, composer, 0, 32);
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
