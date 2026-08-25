package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static float a(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        if (!TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnitType.INSTANCE.m5898getSpUIouoOA())) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        return Dp.m5678constructorimpl(lazyLayoutMeasureScope.getFontScale() * TextUnit.m5865getValueimpl(j10));
    }

    public static float b(LazyLayoutMeasureScope lazyLayoutMeasureScope, float f10) {
        return Dp.m5678constructorimpl(f10 / lazyLayoutMeasureScope.getDensity());
    }

    public static float c(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i10) {
        return Dp.m5678constructorimpl(i10 / lazyLayoutMeasureScope.getDensity());
    }

    public static long d(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        return j10 != Size.INSTANCE.m3313getUnspecifiedNHjbRc() ? DpKt.m5700DpSizeYgX7TsA(lazyLayoutMeasureScope.mo281toDpu2uoSUM(Size.m3305getWidthimpl(j10)), lazyLayoutMeasureScope.mo281toDpu2uoSUM(Size.m3302getHeightimpl(j10))) : DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
    }

    public static long e(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10) {
        return j10 != DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ() ? SizeKt.Size(lazyLayoutMeasureScope.mo285toPx0680j_4(DpSize.m5776getWidthD9Ej5fM(j10)), lazyLayoutMeasureScope.mo285toPx0680j_4(DpSize.m5774getHeightD9Ej5fM(j10))) : Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    public static long f(LazyLayoutMeasureScope lazyLayoutMeasureScope, float f10) {
        return TextUnitKt.getSp(f10 / lazyLayoutMeasureScope.getFontScale());
    }

    public static long g(LazyLayoutMeasureScope lazyLayoutMeasureScope, float f10) {
        return TextUnitKt.getSp(f10 / (lazyLayoutMeasureScope.getDensity() * lazyLayoutMeasureScope.getFontScale()));
    }

    public static long h(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i10) {
        return TextUnitKt.getSp(i10 / (lazyLayoutMeasureScope.getDensity() * lazyLayoutMeasureScope.getFontScale()));
    }
}
