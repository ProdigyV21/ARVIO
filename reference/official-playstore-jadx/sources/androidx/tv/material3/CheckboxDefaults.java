package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004JN\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Landroidx/tv/material3/CheckboxDefaults;", "", "Landroidx/tv/material3/CheckboxColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/CheckboxColors;", "Landroidx/compose/ui/graphics/Color;", "checkedColor", "uncheckedColor", "checkmarkColor", "disabledCheckedColor", "disabledUncheckedColor", "disabledIndeterminateColor", "colors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/CheckboxColors;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CheckboxDefaults f5585a = new CheckboxDefaults();

    public static CheckboxColors a(h1 h1Var) {
        CheckboxColors checkboxColors = h1Var.D;
        if (checkboxColors != null) {
            return checkboxColors;
        }
        long jA = ColorSchemeKt.a(h1Var, j2.a.f19045c);
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        j2.d dVar = j2.a.f19043a;
        long jA2 = ColorSchemeKt.a(h1Var, dVar);
        long jM3507getTransparent0d7_KjU2 = companion.m3507getTransparent0d7_KjU();
        j2.d dVar2 = j2.a.f19044b;
        CheckboxColors checkboxColors2 = new CheckboxColors(jA, jM3507getTransparent0d7_KjU, jA2, jM3507getTransparent0d7_KjU2, Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), companion.m3507getTransparent0d7_KjU(), Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.a(h1Var, dVar), ColorSchemeKt.a(h1Var, j2.a.f19047e), Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, j2.a.f19046d), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar2), 0.38f, 0.0f, 0.0f, 0.0f, 14, null));
        h1Var.D = checkboxColors2;
        return checkboxColors2;
    }

    public final CheckboxColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(813415510);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(813415510, i10, -1, "androidx.tv.material3.CheckboxDefaults.colors (Checkbox.kt:171)");
        }
        CheckboxColors checkboxColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return checkboxColorsA;
    }

    /* JADX INFO: renamed from: colors-5tl4gsc, reason: not valid java name */
    public final CheckboxColors m5982colors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        long j16;
        composer.startReplaceableGroup(-336928460);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i11 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i11 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        if (ComposerKt.isTraceInProgress()) {
            j16 = jM3508getUnspecified0d7_KjU;
            ComposerKt.traceEventStart(-336928460, i10, -1, "androidx.tv.material3.CheckboxDefaults.colors (Checkbox.kt:196)");
        } else {
            j16 = jM3508getUnspecified0d7_KjU;
        }
        CheckboxColors checkboxColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        long jM3507getTransparent0d7_KjU2 = companion.m3507getTransparent0d7_KjU();
        long jM3507getTransparent0d7_KjU3 = companion.m3507getTransparent0d7_KjU();
        if (jM3508getUnspecified0d7_KjU3 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU3 = checkboxColorsA.f5574a;
        }
        long j17 = jM3508getUnspecified0d7_KjU3;
        if (jM3507getTransparent0d7_KjU == companion.m3508getUnspecified0d7_KjU()) {
            jM3507getTransparent0d7_KjU = checkboxColorsA.f5575b;
        }
        long j18 = jM3507getTransparent0d7_KjU;
        long j19 = j16 != companion.m3508getUnspecified0d7_KjU() ? j16 : checkboxColorsA.f5576c;
        long j20 = jM3507getTransparent0d7_KjU2 != companion.m3508getUnspecified0d7_KjU() ? jM3507getTransparent0d7_KjU2 : checkboxColorsA.f5577d;
        long j21 = jM3508getUnspecified0d7_KjU4 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU4 : checkboxColorsA.f5578e;
        long j22 = jM3507getTransparent0d7_KjU3 != companion.m3508getUnspecified0d7_KjU() ? jM3507getTransparent0d7_KjU3 : checkboxColorsA.f5579f;
        long j23 = jM3508getUnspecified0d7_KjU6 != companion.m3508getUnspecified0d7_KjU() ? jM3508getUnspecified0d7_KjU6 : checkboxColorsA.f5580g;
        long j24 = j16 != companion.m3508getUnspecified0d7_KjU() ? j16 : checkboxColorsA.f5581h;
        if (jM3508getUnspecified0d7_KjU2 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU2 = checkboxColorsA.f5582i;
        }
        long j25 = jM3508getUnspecified0d7_KjU2;
        if (jM3508getUnspecified0d7_KjU4 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU4 = checkboxColorsA.f5583j;
        }
        long j26 = jM3508getUnspecified0d7_KjU4;
        if (jM3508getUnspecified0d7_KjU5 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU5 = checkboxColorsA.k;
        }
        long j27 = jM3508getUnspecified0d7_KjU5;
        if (jM3508getUnspecified0d7_KjU6 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU6 = checkboxColorsA.f5584l;
        }
        CheckboxColors checkboxColors = new CheckboxColors(j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, jM3508getUnspecified0d7_KjU6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return checkboxColors;
    }
}
