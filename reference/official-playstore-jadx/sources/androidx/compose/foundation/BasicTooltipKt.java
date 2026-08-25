package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a+\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"BasicTooltipState", "Landroidx/compose/foundation/BasicTooltipState;", "initialIsVisible", "", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "rememberBasicTooltipState", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BasicTooltipState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
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
        composer.startReplaceableGroup(1123859613);
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
            ComposerKt.traceEventStart(1123859613, i10, -1, "androidx.compose.foundation.rememberBasicTooltipState (BasicTooltip.kt:82)");
        }
        Boolean boolValueOf = Boolean.valueOf(z5);
        composer.startReplaceableGroup(511388516);
        boolean zChanged = composer.changed(boolValueOf) | composer.changed(mutatorMutex);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new BasicTooltipStateImpl(z, z5, mutatorMutex);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        BasicTooltipStateImpl basicTooltipStateImpl = (BasicTooltipStateImpl) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return basicTooltipStateImpl;
    }
}
