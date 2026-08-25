package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$PrimaryTabRow$1 extends r implements q<TabIndicatorScope, Composer, Integer, t0> {
    final /* synthetic */ int $selectedTabIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$PrimaryTabRow$1(int i10) {
        super(3);
        this.$selectedTabIndex = i10;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((TabIndicatorScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer, int i10) {
        int i11;
        if ((i10 & 6) == 0) {
            i11 = ((i10 & 8) == 0 ? composer.changed(tabIndicatorScope) : composer.changedInstance(tabIndicatorScope) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2021049253, i11, -1, "androidx.compose.material3.PrimaryTabRow.<anonymous> (TabRow.kt:153)");
        }
        TabRowDefaults.INSTANCE.m2051PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, this.$selectedTabIndex, true), Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, composer, 196656, 28);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
