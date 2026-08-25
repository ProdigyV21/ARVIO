package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J:\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"Landroidx/tv/material3/RadioButtonDefaults;", "", "Landroidx/tv/material3/RadioButtonColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/RadioButtonColors;", "Landroidx/compose/ui/graphics/Color;", "selectedColor", "unselectedColor", "disabledSelectedColor", "disabledUnselectedColor", "colors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/RadioButtonColors;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RadioButtonDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final RadioButtonDefaults f5705a = new RadioButtonDefaults();

    public static RadioButtonColors a(h1 h1Var) {
        RadioButtonColors radioButtonColors = h1Var.F;
        if (radioButtonColors != null) {
            return radioButtonColors;
        }
        long jA = ColorSchemeKt.a(h1Var, j2.g.f19112b);
        long jA2 = ColorSchemeKt.a(h1Var, j2.g.f19113c);
        j2.d dVar = j2.d.f19080m;
        RadioButtonColors radioButtonColors2 = new RadioButtonColors(jA, jA2, Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.a(h1Var, dVar), 0.38f, 0.0f, 0.0f, 0.0f, 14, null));
        h1Var.F = radioButtonColors2;
        return radioButtonColors2;
    }

    public final RadioButtonColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(-255975974);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-255975974, i10, -1, "androidx.tv.material3.RadioButtonDefaults.colors (RadioButton.kt:124)");
        }
        RadioButtonColors radioButtonColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return radioButtonColorsA;
    }

    /* JADX INFO: renamed from: colors-ro_MJ88, reason: not valid java name */
    public final RadioButtonColors m6003colorsro_MJ88(long j10, long j11, long j12, long j13, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(186377550);
        long jM3508getUnspecified0d7_KjU = (i11 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i11 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i11 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i11 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(186377550, i10, -1, "androidx.tv.material3.RadioButtonDefaults.colors (RadioButton.kt:144)");
        }
        RadioButtonColors radioButtonColorsA = a(MaterialTheme.f5670a.getColorScheme(composer, 6));
        Color.Companion companion = Color.INSTANCE;
        if (jM3508getUnspecified0d7_KjU == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU = radioButtonColorsA.f5701a;
        }
        long j14 = jM3508getUnspecified0d7_KjU;
        if (jM3508getUnspecified0d7_KjU2 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU2 = radioButtonColorsA.f5702b;
        }
        long j15 = jM3508getUnspecified0d7_KjU2;
        if (jM3508getUnspecified0d7_KjU3 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU3 = radioButtonColorsA.f5703c;
        }
        long j16 = jM3508getUnspecified0d7_KjU3;
        if (jM3508getUnspecified0d7_KjU4 == companion.m3508getUnspecified0d7_KjU()) {
            jM3508getUnspecified0d7_KjU4 = radioButtonColorsA.f5704d;
        }
        RadioButtonColors radioButtonColors = new RadioButtonColors(j14, j15, j16, jM3508getUnspecified0d7_KjU4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return radioButtonColors;
    }
}
