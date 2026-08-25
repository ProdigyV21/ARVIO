package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$SecondaryScrollableTabRow$1 extends r implements q<List<? extends TabPosition>, Composer, Integer, t0> {
    final /* synthetic */ int $selectedTabIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$SecondaryScrollableTabRow$1(int i10) {
        super(3);
        this.$selectedTabIndex = i10;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((List<TabPosition>) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(List<TabPosition> list, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-115843248, i10, -1, "androidx.compose.material3.SecondaryScrollableTabRow.<anonymous> (TabRow.kt:754)");
        }
        TabRowDefaults tabRowDefaults = TabRowDefaults.INSTANCE;
        tabRowDefaults.m2052SecondaryIndicator9IZ8Weo(tabRowDefaults.tabIndicatorOffset(Modifier.INSTANCE, list.get(this.$selectedTabIndex)), 0.0f, 0L, composer, 3072, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
