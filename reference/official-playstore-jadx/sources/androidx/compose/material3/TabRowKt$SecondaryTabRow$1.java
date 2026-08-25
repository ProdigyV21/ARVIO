package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$SecondaryTabRow$1 extends r implements q<TabIndicatorScope, Composer, Integer, t0> {
    final /* synthetic */ int $selectedTabIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$SecondaryTabRow$1(int i10) {
        super(3);
        this.$selectedTabIndex = i10;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((TabIndicatorScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(TabIndicatorScope tabIndicatorScope, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= (i10 & 8) == 0 ? composer.changed(tabIndicatorScope) : composer.changedInstance(tabIndicatorScope) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(286693261, i10, -1, "androidx.compose.material3.SecondaryTabRow.<anonymous> (TabRow.kt:210)");
        }
        TabRowDefaults.INSTANCE.m2052SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, this.$selectedTabIndex, false), 0.0f, 0L, composer, 3072, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
