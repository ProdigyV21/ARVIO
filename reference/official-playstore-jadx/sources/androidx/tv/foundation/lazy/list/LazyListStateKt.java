package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.unit.Dp;
import androidx.tv.foundation.lazy.grid.c1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "initialFirstVisibleItemIndex", "initialFirstVisibleItemScrollOffset", "Landroidx/tv/foundation/lazy/list/m0;", "rememberTvLazyListState", "(IILandroidx/compose/runtime/Composer;II)Landroidx/tv/foundation/lazy/list/m0;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListStateKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5355a = Dp.m5678constructorimpl(1);

    @x6.e
    public static final m0 rememberTvLazyListState(int i10, int i11, Composer composer, int i12, int i13) {
        composer.startReplaceableGroup(-296206322);
        if ((i13 & 1) != 0) {
            i10 = 0;
        }
        if ((i13 & 2) != 0) {
            i11 = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-296206322, i12, -1, "androidx.tv.foundation.lazy.list.rememberTvLazyListState (LazyListState.kt:83)");
        }
        Object[] objArr = new Object[0];
        Saver saver = m0.z;
        composer.startReplaceableGroup(1747888904);
        boolean z = ((((i12 & 14) ^ 6) > 4 && composer.changed(i10)) || (i12 & 6) == 4) | ((((i12 & 112) ^ 48) > 32 && composer.changed(i11)) || (i12 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new c1(i10, i11, 1);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        m0 m0Var = (m0) RememberSaveableKt.m3084rememberSaveable(objArr, saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return m0Var;
    }
}
