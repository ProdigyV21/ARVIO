package androidx.tv.material3;

import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001J_\u0010\u0003\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\fJ\u009e\u0001\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ_\u0010!\u001a\u00020)2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 2\b\b\u0002\u0010&\u001a\u00020 2\b\b\u0002\u0010'\u001a\u00020 2\b\b\u0002\u0010(\u001a\u00020 H\u0007¢\u0006\u0004\b!\u0010*R\u0014\u0010-\u001a\u00020 8CX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006."}, d2 = {"Landroidx/tv/material3/ListItemDefaults;", "", "Landroidx/compose/ui/graphics/Shape;", "shape", "focusedShape", "pressedShape", "selectedShape", "disabledShape", "focusedSelectedShape", "focusedDisabledShape", "pressedSelectedShape", "Landroidx/tv/material3/d2;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/d2;", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "selectedContainerColor", "selectedContentColor", "disabledContainerColor", "disabledContentColor", "focusedSelectedContainerColor", "focusedSelectedContentColor", "pressedSelectedContainerColor", "pressedSelectedContentColor", "Landroidx/tv/material3/x1;", "colors-u3YEpmA", "(JJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/tv/material3/x1;", "colors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "selectedBorder", "disabledBorder", "focusedSelectedBorder", "focusedDisabledBorder", "pressedSelectedBorder", "Landroidx/tv/material3/w1;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/w1;", "getFocusedDisabledBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/d;", "FocusedDisabledBorder", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ListItemDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ListItemDefaults f5640a = new ListItemDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5641b = Dp.m5678constructorimpl(32);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f5642c = Dp.m5678constructorimpl(20);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f5643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final RoundedCornerShape f5644e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final PaddingValues f5645f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final PaddingValues f5646g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final float f5647h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final float f5648i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final float f5649j;
    public static final float k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final float f5650l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final float f5651m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final float f5652n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final float f5653o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final float f5654p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final float f5655q;

    static {
        float f10 = j2.e.f19087a;
        f5643d = j2.e.f19087a;
        float f11 = 8;
        f5644e = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11));
        float f12 = 12;
        f5645f = PaddingKt.m523PaddingValuesYgX7TsA(Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(f12));
        f5646g = PaddingKt.m523PaddingValuesYgX7TsA(Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(10));
        f5647h = Dp.m5678constructorimpl(f11);
        f5648i = Dp.m5678constructorimpl(f11);
        f5649j = Dp.m5678constructorimpl(48);
        float f13 = 56;
        k = Dp.m5678constructorimpl(f13);
        f5650l = Dp.m5678constructorimpl(64);
        f5651m = Dp.m5678constructorimpl(80);
        float f14 = 40;
        f5652n = Dp.m5678constructorimpl(f14);
        f5653o = Dp.m5678constructorimpl(f14);
        f5654p = Dp.m5678constructorimpl(f13);
        f5655q = Dp.m5678constructorimpl(72);
    }

    public static y1 a() {
        s1 s1Var = s1.f6376c;
        return new y1(s1Var, s1Var, s1Var, s1Var, s1Var, s1Var);
    }

    public static c2 b() {
        return new c2(1.05f, 1.05f);
    }

    private final d getFocusedDisabledBorder(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1417828768, i10, -1, "androidx.tv.material3.ListItemDefaults.<get-FocusedDisabledBorder> (ListItemDefaults.kt:49)");
        }
        d dVar = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(2), MaterialTheme.f5670a.getColorScheme(composer, 6).b()), (Shape) null, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return dVar;
    }

    public final w1 border(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8, Composer composer, int i10, int i11) {
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
            dVar7 = getFocusedDisabledBorder(composer, (i10 >> 24) & 14);
        }
        d dVar9 = dVar7;
        if ((i11 & 128) != 0) {
            dVar8 = dVar;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(598737150, i10, -1, "androidx.tv.material3.ListItemDefaults.border (ListItemDefaults.kt:241)");
        }
        d dVar10 = dVar8;
        d dVar11 = dVar6;
        d dVar12 = dVar5;
        d dVar13 = dVar3;
        d dVar14 = dVar;
        w1 w1Var = new w1(dVar14, dVar2, dVar13, dVar4, dVar12, dVar11, dVar9, dVar10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return w1Var;
    }

    /* JADX INFO: renamed from: colors-u3YEpmA, reason: not valid java name */
    public final x1 m5994colorsu3YEpmA(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, Composer composer, int i10, int i11, int i12) {
        long jM3507getTransparent0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j10;
        int i13 = i12 & 2;
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        long jN = i13 != 0 ? materialTheme.getColorScheme(composer, 6).n() : j11;
        long jF = (i12 & 4) != 0 ? materialTheme.getColorScheme(composer, 6).f() : j12;
        long jM5987contentColorForek8zF_U = (i12 & 8) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 6) & 14) : j13;
        long j24 = (i12 & 16) != 0 ? jF : j14;
        long jM5987contentColorForek8zF_U2 = (i12 & 32) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jF, composer, (i10 >> 6) & 14) : j15;
        long jM3471copywmQWz5c$default = (i12 & 64) != 0 ? Color.m3471copywmQWz5c$default(materialTheme.getColorScheme(composer, 6).v(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : j16;
        long jM = (i12 & 128) != 0 ? materialTheme.getColorScheme(composer, 6).m() : j17;
        long jM3507getTransparent0d7_KjU2 = (i12 & 256) != 0 ? Color.INSTANCE.m3507getTransparent0d7_KjU() : j18;
        long jN2 = (i12 & 512) != 0 ? materialTheme.getColorScheme(composer, 6).n() : j19;
        long j25 = (i12 & 1024) != 0 ? jF : j20;
        long j26 = (i12 & 2048) != 0 ? jM5987contentColorForek8zF_U : j21;
        long j27 = (i12 & 4096) != 0 ? j24 : j22;
        long j28 = (i12 & 8192) != 0 ? jM5987contentColorForek8zF_U2 : j23;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1416941372, i10, i11, "androidx.tv.material3.ListItemDefaults.colors (ListItemDefaults.kt:159)");
        }
        x1 x1Var = new x1(jM3507getTransparent0d7_KjU, jN, jF, jM5987contentColorForek8zF_U, j24, jM5987contentColorForek8zF_U2, jM3471copywmQWz5c$default, jM, jM3507getTransparent0d7_KjU2, jN2, j25, j26, j27, j28);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x1Var;
    }

    public final d2 shape(Shape shape, Shape shape2, Shape shape3, Shape shape4, Shape shape5, Shape shape6, Shape shape7, Shape shape8, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            shape = f5644e;
        }
        if ((i11 & 2) != 0) {
            shape2 = shape;
        }
        if ((i11 & 4) != 0) {
            shape3 = shape;
        }
        if ((i11 & 8) != 0) {
            shape4 = shape;
        }
        if ((i11 & 16) != 0) {
            shape5 = shape;
        }
        if ((i11 & 32) != 0) {
            shape6 = shape;
        }
        Shape shape9 = (i11 & 64) != 0 ? shape5 : shape7;
        if ((i11 & 128) != 0) {
            shape8 = shape;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1855125628, i10, -1, "androidx.tv.material3.ListItemDefaults.shape (ListItemDefaults.kt:101)");
        }
        Shape shape10 = shape8;
        Shape shape11 = shape5;
        Shape shape12 = shape3;
        Shape shape13 = shape;
        d2 d2Var = new d2(shape13, shape2, shape12, shape4, shape11, shape6, shape9, shape10);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return d2Var;
    }
}
