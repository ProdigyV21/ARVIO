package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class WideButtonKt$WideButton$2 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f5783i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f5784l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WideButtonKt$WideButton$2(boolean z, MutableInteractionSource mutableInteractionSource) {
        super(2);
        this.f5783i = z;
        this.f5784l = mutableInteractionSource;
    }

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1323022728, i10, -1, "androidx.tv.material3.WideButton.<anonymous> (WideButton.kt:82)");
        }
        WideButtonDefaults.f5780a.Background(this.f5783i, this.f5784l, composer, RendererCapabilities.DECODER_SUPPORT_MASK);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
