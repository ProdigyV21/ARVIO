package androidx.tv.material3;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001J.\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ²\u0001\u0010\u001c\u001a\u00020\u00192\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ_\u0010\u001e\u001a\u00020&2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020\u001d2\b\b\u0002\u0010\"\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020\u001d2\b\b\u0002\u0010$\u001a\u00020\u001d2\b\b\u0002\u0010%\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010'R\u0011\u0010*\u001a\u00020\u001d8G¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0017\u0010-\u001a\u00020\u00048Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u00101\u001a\u00020.8G¢\u0006\u0006\u001a\u0004\b/\u00100R\u0017\u00103\u001a\u00020\u00048Gø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b2\u0010,\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00064"}, d2 = {"Landroidx/tv/material3/NavigationDrawerItemDefaults;", "", "", "text", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Lx6/t0;", "TrailingBadge-RIQooxk", "(Ljava/lang/String;JJLandroidx/compose/runtime/Composer;II)V", "TrailingBadge", "inactiveContentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "selectedContainerColor", "selectedContentColor", "disabledContainerColor", "disabledContentColor", "disabledInactiveContentColor", "focusedSelectedContainerColor", "focusedSelectedContentColor", "pressedSelectedContainerColor", "pressedSelectedContentColor", "Landroidx/tv/material3/f2;", "colors-V1nXRL4", "(JJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/tv/material3/f2;", "colors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "selectedBorder", "disabledBorder", "focusedSelectedBorder", "focusedDisabledBorder", "pressedSelectedBorder", "Landroidx/tv/material3/e2;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/e2;", "getDefaultBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/d;", "DefaultBorder", "getTrailingBadgeContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "TrailingBadgeContainerColor", "Landroidx/compose/ui/text/TextStyle;", "getTrailingBadgeTextStyle", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/text/TextStyle;", "TrailingBadgeTextStyle", "getTrailingBadgeContentColor", "TrailingBadgeContentColor", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerItemDefaults {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f5675c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f5677e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float f5679g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final EnterTransition f5680h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ExitTransition f5681i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final NavigationDrawerItemDefaults f5673a = new NavigationDrawerItemDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5674b = Dp.m5678constructorimpl(24);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f5676d = Dp.m5678constructorimpl(256);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f5678f = Dp.m5678constructorimpl(64);

    static {
        float f10 = 56;
        f5675c = Dp.m5678constructorimpl(f10);
        f5677e = Dp.m5678constructorimpl(f10);
        float f11 = j2.e.f19087a;
        f5679g = j2.e.f19087a;
        f5680h = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.slideIn$default(null, s.f6363s, 1, null));
        f5681i = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.slideOut$default(null, s.f6364t, 1, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0176  */
    /* JADX INFO: renamed from: TrailingBadge-RIQooxk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m5998TrailingBadgeRIQooxk(java.lang.String r17, long r18, long r20, androidx.compose.runtime.Composer r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.NavigationDrawerItemDefaults.m5998TrailingBadgeRIQooxk(java.lang.String, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public final e2 border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            dVar = d.f5889d;
        }
        if ((i11 & 2) != 0) {
            dVar2 = dVar;
        }
        if ((i11 & 4) != 0) {
            dVar3 = dVar2;
        }
        if ((i11 & 8) != 0) {
            dVar4 = dVar;
        }
        if ((i11 & 16) != 0) {
            dVar5 = dVar;
        }
        if ((i11 & 32) != 0) {
            dVar6 = dVar2;
        }
        if ((i11 & 64) != 0) {
            dVar7 = getDefaultBorder(composer, (i10 >> 24) & 14);
        }
        d dVar9 = dVar7;
        if ((i11 & 128) != 0) {
            dVar8 = dVar;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-375276489, i10, -1, "androidx.tv.material3.NavigationDrawerItemDefaults.border (NavigationDrawerItemDefaults.kt:298)");
        }
        d dVar10 = dVar8;
        d dVar11 = dVar6;
        d dVar12 = dVar5;
        d dVar13 = dVar3;
        d dVar14 = dVar;
        e2 e2Var = new e2(dVar14, dVar2, dVar13, dVar4, dVar12, dVar11, dVar9, dVar10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return e2Var;
    }

    /* JADX INFO: renamed from: colors-V1nXRL4, reason: not valid java name */
    public final f2 m5999colorsV1nXRL4(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, Composer composer, int i10, int i11, int i12) {
        long j26;
        long jM3471copywmQWz5c$default;
        long jM3507getTransparent0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        int i13 = i12 & 2;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jN = i13 != 0 ? materialTheme.getColorScheme(composer, 6).n() : j11;
        long jM3471copywmQWz5c$default2 = (i12 & 4) != 0 ? Color.m3471copywmQWz5c$default(jN, 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j12;
        long jF = (i12 & 8) != 0 ? materialTheme.getColorScheme(composer, 6).f() : j13;
        long jM5987contentColorForek8zF_U = (i12 & 16) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 9) & 14) : j14;
        long j27 = (i12 & 32) != 0 ? jF : j15;
        long jM5987contentColorForek8zF_U2 = (i12 & 64) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 9) & 14) : j16;
        long jM3471copywmQWz5c$default3 = (i12 & 128) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).v(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j17;
        long jM = (i12 & 256) != 0 ? materialTheme.getColorScheme(composer, 6).m() : j18;
        long jM3507getTransparent0d7_KjU2 = (i12 & 512) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j19;
        long jN2 = (i12 & 1024) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j20;
        if ((i12 & 2048) != 0) {
            long j28 = jN2;
            jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(j28, 0.4f, 0.0f, 0.0f, 0.0f, 14, null);
            j26 = j28;
        } else {
            j26 = jN2;
            jM3471copywmQWz5c$default = j21;
        }
        long j29 = (i12 & 4096) != 0 ? jF : j22;
        long j30 = (i12 & 8192) != 0 ? jM5987contentColorForek8zF_U : j23;
        long j31 = (i12 & 16384) != 0 ? j27 : j24;
        long j32 = (32768 & i12) != 0 ? jM5987contentColorForek8zF_U2 : j25;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-4350393, i10, i11, "androidx.tv.material3.NavigationDrawerItemDefaults.colors (NavigationDrawerItemDefaults.kt:209)");
        }
        f2 f2Var = new f2(jM3507getTransparent0d7_KjU, jN, jM3471copywmQWz5c$default2, jF, jM5987contentColorForek8zF_U, j27, jM5987contentColorForek8zF_U2, jM3471copywmQWz5c$default3, jM, jM3507getTransparent0d7_KjU2, j26, jM3471copywmQWz5c$default, j29, j30, j31, j32);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return f2Var;
    }

    public final d getDefaultBorder(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-863436654, i10, -1, "androidx.tv.material3.NavigationDrawerItemDefaults.<get-DefaultBorder> (NavigationDrawerItemDefaults.kt:76)");
        }
        d dVar = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), MaterialTheme.f5670a.getColorScheme(composer, 6).b()), (Shape) null, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dVar;
    }

    public final long getTrailingBadgeContainerColor(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(945488599, i10, -1, "androidx.tv.material3.NavigationDrawerItemDefaults.<get-TrailingBadgeContainerColor> (NavigationDrawerItemDefaults.kt:82)");
        }
        long jZ = MaterialTheme.f5670a.getColorScheme(composer, 6).z();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jZ;
    }

    public final long getTrailingBadgeContentColor(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-323174233, i10, -1, "androidx.tv.material3.NavigationDrawerItemDefaults.<get-TrailingBadgeContentColor> (NavigationDrawerItemDefaults.kt:90)");
        }
        long jP = MaterialTheme.f5670a.getColorScheme(composer, 6).p();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jP;
    }

    public final TextStyle getTrailingBadgeTextStyle(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1003616144, i10, -1, "androidx.tv.material3.NavigationDrawerItemDefaults.<get-TrailingBadgeTextStyle> (NavigationDrawerItemDefaults.kt:86)");
        }
        TextStyle textStyle = MaterialTheme.f5670a.getTypography(composer, 6).f6312o;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return textStyle;
    }
}
