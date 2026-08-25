package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/tv/foundation/lazy/list/m0;", "state", "", "isVertical", "Li2/m;", "rememberLazyListSemanticState", "(Landroidx/tv/foundation/lazy/list/m0;ZLandroidx/compose/runtime/Composer;I)Li2/m;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazySemanticsKt {
    public static final i2.m rememberLazyListSemanticState(m0 m0Var, boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1654148481);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1654148481, i10, -1, "androidx.tv.foundation.lazy.list.rememberLazyListSemanticState (LazySemantics.kt:31)");
        }
        composer.startReplaceableGroup(402721805);
        boolean z5 = ((((i10 & 14) ^ 6) > 4 && composer.changed(m0Var)) || (i10 & 6) == 4) | ((((i10 & 112) ^ 48) > 32 && composer.changed(z)) || (i10 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new i2.n(m0Var, z);
            composer.updateRememberedValue(objRememberedValue);
        }
        i2.m mVar = (i2.m) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return mVar;
    }
}
