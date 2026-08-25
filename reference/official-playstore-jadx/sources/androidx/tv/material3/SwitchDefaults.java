package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J²\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/tv/material3/SwitchDefaults;", "", "Landroidx/tv/material3/SwitchColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/SwitchColors;", "Landroidx/compose/ui/graphics/Color;", "checkedThumbColor", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "colors-V1nXRL4", "(JJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/tv/material3/SwitchColors;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SwitchDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SwitchDefaults f5756a = new SwitchDefaults();

    static {
        Dp.m5678constructorimpl(12);
    }

    public static SwitchColors a(h1 h1Var) {
        SwitchColors switchColors = h1Var.E;
        if (switchColors != null) {
            return switchColors;
        }
        long jA = ColorSchemeKt.a(h1Var, j2.l.f19125a);
        long jA2 = ColorSchemeKt.a(h1Var, j2.l.f19128d);
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        long jA3 = ColorSchemeKt.a(h1Var, j2.l.f19127c);
        long jA4 = ColorSchemeKt.a(h1Var, j2.l.f19134j);
        long jA5 = ColorSchemeKt.a(h1Var, j2.l.f19136m);
        long jA6 = ColorSchemeKt.a(h1Var, j2.l.f19133i);
        long jA7 = ColorSchemeKt.a(h1Var, j2.l.f19135l);
        long jM3517compositeOverOWjLjI = ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, j2.d.f19084q), 1.0f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w());
        j2.d dVar = j2.d.f19080m;
        long jM3517compositeOverOWjLjI2 = ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w());
        long jM3507getTransparent0d7_KjU2 = companion.m3507getTransparent0d7_KjU();
        long jM3517compositeOverOWjLjI3 = ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w());
        long jM3517compositeOverOWjLjI4 = ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w());
        j2.d dVar2 = j2.d.f19085r;
        SwitchColors switchColors2 = new SwitchColors(jA, jA2, jM3507getTransparent0d7_KjU, jA3, jA4, jA5, jA6, jA7, jM3517compositeOverOWjLjI, jM3517compositeOverOWjLjI2, jM3507getTransparent0d7_KjU2, jM3517compositeOverOWjLjI3, jM3517compositeOverOWjLjI4, ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w()), ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), h1Var.w()));
        h1Var.E = switchColors2;
        return switchColors2;
    }

    public final SwitchColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(-2006111579);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2006111579, i10, -1, "androidx.tv.material3.SwitchDefaults.colors (Switch.kt:242)");
        }
        SwitchColors switchColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return switchColorsA;
    }

    /* JADX INFO: renamed from: colors-V1nXRL4, reason: not valid java name */
    public final SwitchColors m6012colorsV1nXRL4(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, Composer composer, int i10, int i11, int i12) {
        long j26;
        composer.startReplaceableGroup(-1594982755);
        long jM3508getUnspecified0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i12 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i12 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i12 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i12 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i12 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i12 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i12 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i12 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i12 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        long jM3508getUnspecified0d7_KjU11 = (i12 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i12 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        long jM3508getUnspecified0d7_KjU13 = (i12 & 4096) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j22;
        long jM3508getUnspecified0d7_KjU14 = (i12 & 8192) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j23;
        long jM3508getUnspecified0d7_KjU15 = (i12 & 16384) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j24;
        long jM3508getUnspecified0d7_KjU16 = (i12 & 32768) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j25;
        if (ComposerKt.isTraceInProgress()) {
            j26 = jM3508getUnspecified0d7_KjU;
            ComposerKt.traceEventStart(-1594982755, i10, i11, "androidx.tv.material3.SwitchDefaults.colors (Switch.kt:284)");
        } else {
            j26 = jM3508getUnspecified0d7_KjU;
        }
        SwitchColors switchColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        Color.Companion companion = Color.INSTANCE;
        long j27 = j26 != companion.m3508getUnspecified0d7_KjU() ? j26 : switchColorsA.f5741a;
        if (jM3508getUnspecified0d7_KjU2 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU2 = switchColorsA.f5742b;
        }
        long j28 = jM3508getUnspecified0d7_KjU2;
        if (jM3508getUnspecified0d7_KjU3 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU3 = switchColorsA.f5743c;
        }
        long j29 = jM3508getUnspecified0d7_KjU3;
        if (jM3508getUnspecified0d7_KjU4 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU4 = switchColorsA.f5744d;
        }
        long j30 = jM3508getUnspecified0d7_KjU4;
        if (jM3508getUnspecified0d7_KjU5 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU5 = switchColorsA.f5745e;
        }
        long j31 = jM3508getUnspecified0d7_KjU5;
        if (jM3508getUnspecified0d7_KjU6 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU6 = switchColorsA.f5746f;
        }
        SwitchColors switchColors = new SwitchColors(j27, j28, j29, j30, j31, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU7 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU7 : switchColorsA.f5747g, jM3508getUnspecified0d7_KjU8 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU8 : switchColorsA.f5748h, jM3508getUnspecified0d7_KjU9 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU9 : switchColorsA.f5749i, jM3508getUnspecified0d7_KjU10 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU10 : switchColorsA.f5750j, jM3508getUnspecified0d7_KjU11 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU11 : switchColorsA.k, jM3508getUnspecified0d7_KjU12 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU12 : switchColorsA.f5751l, jM3508getUnspecified0d7_KjU13 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU13 : switchColorsA.f5752m, jM3508getUnspecified0d7_KjU14 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU14 : switchColorsA.f5753n, jM3508getUnspecified0d7_KjU15 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU15 : switchColorsA.f5754o, jM3508getUnspecified0d7_KjU16 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU16 : switchColorsA.f5755p);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return switchColors;
    }
}
