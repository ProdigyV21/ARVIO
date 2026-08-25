package androidx.compose.material3;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0001\u001a+\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"BasicTooltipState", "Landroidx/compose/material3/BasicTooltipState;", "initialIsVisible", "", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "rememberBasicTooltipState", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BasicTooltipState;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTooltipKt {
    public static final BasicTooltipState BasicTooltipState(boolean z, boolean z5, MutatorMutex mutatorMutex) {
        return new BasicTooltipStateImpl(z, z5, mutatorMutex);
    }

    public static /* synthetic */ BasicTooltipState BasicTooltipState$default(boolean z, boolean z5, MutatorMutex mutatorMutex, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        if ((i10 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return BasicTooltipState(z, z5, mutatorMutex);
    }

    public static final BasicTooltipState rememberBasicTooltipState(boolean z, boolean z5, MutatorMutex mutatorMutex, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1386430812);
        if ((i11 & 1) != 0) {
            z = false;
        }
        if ((i11 & 2) != 0) {
            z5 = true;
        }
        if ((i11 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1386430812, i10, -1, "androidx.compose.material3.rememberBasicTooltipState (BasicTooltip.kt:87)");
        }
        composer.startReplaceableGroup(-459690368);
        boolean z10 = ((((i10 & 112) ^ 48) > 32 && composer.changed(z5)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(mutatorMutex)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new BasicTooltipStateImpl(z, z5, mutatorMutex);
            composer.updateRememberedValue(objRememberedValue);
        }
        BasicTooltipStateImpl basicTooltipStateImpl = (BasicTooltipStateImpl) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return basicTooltipStateImpl;
    }
}
