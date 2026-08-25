package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$PrimaryScrollableTabRow$1 extends r implements q<List<? extends TabPosition>, Composer, Integer, t0> {
    final /* synthetic */ int $selectedTabIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$PrimaryScrollableTabRow$1(int i10) {
        super(3);
        this.$selectedTabIndex = i10;
    }

    private static final float invoke$lambda$0(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((List<TabPosition>) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(List<TabPosition> list, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(438091970, i10, -1, "androidx.compose.material3.PrimaryScrollableTabRow.<anonymous> (TabRow.kt:682)");
        }
        if (this.$selectedTabIndex < list.size()) {
            State<Dp> stateM81animateDpAsStateAjpBEmI = AnimateAsStateKt.m81animateDpAsStateAjpBEmI(list.get(this.$selectedTabIndex).getContentWidth(), null, null, null, composer, 0, 14);
            TabRowDefaults tabRowDefaults = TabRowDefaults.INSTANCE;
            tabRowDefaults.m2051PrimaryIndicator10LGxhE(tabRowDefaults.tabIndicatorOffset(Modifier.INSTANCE, list.get(this.$selectedTabIndex)), invoke$lambda$0(stateM81animateDpAsStateAjpBEmI), 0.0f, 0L, null, composer, 196608, 28);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
