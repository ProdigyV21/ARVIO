package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.DpRect;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$TabRow$1 extends kotlin.jvm.internal.r implements r7.r<List<? extends DpRect>, Boolean, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5767i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$TabRow$1(int i10) {
        super(4);
        this.f5767i = i10;
    }

    @Override // r7.r
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((List<DpRect>) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(List<DpRect> list, boolean z, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1042462891, i10, -1, "androidx.tv.material3.TabRow.<anonymous> (TabRow.kt:98)");
        }
        DpRect dpRect = (DpRect) kotlin.collections.x.p0(this.f5767i, list);
        if (dpRect != null) {
            TabRowDefaults.f5765a.m6016PillIndicatorjA1GFJw(dpRect, z, null, 0L, 0L, composer, (i10 & 112) | 196608, 28);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
