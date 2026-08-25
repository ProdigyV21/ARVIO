package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a {
    public static int a(Density density, long j10) {
        return t7.a.M(density.mo284toPxR2X_6o(j10));
    }

    public static int b(Density density, float f10) {
        float fMo285toPx0680j_4 = density.mo285toPx0680j_4(f10);
        if (Float.isInfinite(fMo285toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return t7.a.M(fMo285toPx0680j_4);
    }

    public static float c(Density density, float f10) {
        return Dp.m5678constructorimpl(f10 / density.getDensity());
    }

    public static float d(Density density, int i10) {
        return Dp.m5678constructorimpl(i10 / density.getDensity());
    }

    public static long e(Density density, long j10) {
        return j10 != Size.INSTANCE.m3313getUnspecifiedNHjbRc() ? DpKt.m5700DpSizeYgX7TsA(density.mo281toDpu2uoSUM(Size.m3305getWidthimpl(j10)), density.mo281toDpu2uoSUM(Size.m3302getHeightimpl(j10))) : DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
    }

    public static float f(Density density, long j10) {
        if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnitType.INSTANCE.m5898getSpUIouoOA())) {
            return density.mo285toPx0680j_4(density.mo280toDpGaN1DYA(j10));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    public static float g(Density density, float f10) {
        return density.getDensity() * f10;
    }

    public static Rect h(Density density, DpRect dpRect) {
        return new Rect(density.mo285toPx0680j_4(dpRect.m5761getLeftD9Ej5fM()), density.mo285toPx0680j_4(dpRect.m5763getTopD9Ej5fM()), density.mo285toPx0680j_4(dpRect.m5762getRightD9Ej5fM()), density.mo285toPx0680j_4(dpRect.m5760getBottomD9Ej5fM()));
    }

    public static long i(Density density, long j10) {
        return j10 != DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ() ? SizeKt.Size(density.mo285toPx0680j_4(DpSize.m5776getWidthD9Ej5fM(j10)), density.mo285toPx0680j_4(DpSize.m5774getHeightD9Ej5fM(j10))) : Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    public static long j(Density density, float f10) {
        return density.mo287toSp0xMU5do(density.mo281toDpu2uoSUM(f10));
    }

    public static long k(Density density, int i10) {
        return density.mo287toSp0xMU5do(density.mo282toDpu2uoSUM(i10));
    }
}
