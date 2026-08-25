package androidx.tv.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathMeasure;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.util.MathHelpersKt;

/* JADX INFO: loaded from: classes3.dex */
public final class q0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ State f6323i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f6324l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f6325m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ State f6326n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ State f6327o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ m0 f6328p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(State state, State state2, State state3, State state4, State state5, m0 m0Var) {
        super(1);
        this.f6323i = state;
        this.f6324l = state2;
        this.f6325m = state3;
        this.f6326n = state4;
        this.f6327o = state5;
        this.f6328p = m0Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        float f10;
        float f11;
        DrawScope drawScope = (DrawScope) obj;
        float fFloor = (float) Math.floor(drawScope.mo285toPx0680j_4(CheckboxKt.f5588c));
        long jM3482unboximpl = ((Color) this.f6323i.getValue()).m3482unboximpl();
        long jM3482unboximpl2 = ((Color) this.f6324l.getValue()).m3482unboximpl();
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(CheckboxKt.f5589d);
        float f12 = fFloor / 2.0f;
        Stroke stroke = new Stroke(fFloor, 0.0f, 0, 0, null, 30, null);
        float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        if (Color.m3473equalsimpl0(jM3482unboximpl, jM3482unboximpl2)) {
            f10 = fFloor;
            androidx.compose.ui.graphics.drawscope.c.M(drawScope, jM3482unboximpl, 0L, SizeKt.Size(fM3305getWidthimpl, fM3305getWidthimpl), CornerRadiusKt.CornerRadius$default(fMo285toPx0680j_4, 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 226, null);
            f11 = 0.0f;
        } else {
            float f13 = fM3305getWidthimpl - (2 * fFloor);
            f10 = fFloor;
            androidx.compose.ui.graphics.drawscope.c.M(drawScope, jM3482unboximpl, OffsetKt.Offset(fFloor, fFloor), SizeKt.Size(f13, f13), CornerRadiusKt.CornerRadius$default(Math.max(0.0f, fMo285toPx0680j_4 - fFloor), 0.0f, 2, null), Fill.INSTANCE, 0.0f, null, 0, 224, null);
            float f14 = fM3305getWidthimpl - f10;
            f11 = 0.0f;
            androidx.compose.ui.graphics.drawscope.c.M(drawScope, jM3482unboximpl2, OffsetKt.Offset(f12, f12), SizeKt.Size(f14, f14), CornerRadiusKt.CornerRadius$default(fMo285toPx0680j_4 - f12, 0.0f, 2, null), stroke, 0.0f, null, 0, 224, null);
        }
        long jM3482unboximpl3 = ((Color) this.f6325m.getValue()).m3482unboximpl();
        float fFloatValue = ((Number) this.f6326n.getValue()).floatValue();
        float fFloatValue2 = ((Number) this.f6327o.getValue()).floatValue();
        Stroke stroke2 = new Stroke(f10, 0.0f, StrokeCap.INSTANCE.m3805getSquareKaPHkGw(), 0, null, 26, null);
        float fM3305getWidthimpl2 = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        float fLerp = MathHelpersKt.lerp(0.4f, 0.5f, fFloatValue2);
        float fLerp2 = MathHelpersKt.lerp(0.7f, 0.5f, fFloatValue2);
        float fLerp3 = MathHelpersKt.lerp(0.5f, 0.5f, fFloatValue2);
        float fLerp4 = MathHelpersKt.lerp(0.3f, 0.5f, fFloatValue2);
        m0 m0Var = this.f6328p;
        m0Var.f6205a.reset();
        Path path = m0Var.f6205a;
        path.moveTo(0.2f * fM3305getWidthimpl2, fLerp3 * fM3305getWidthimpl2);
        path.lineTo(fLerp * fM3305getWidthimpl2, fLerp2 * fM3305getWidthimpl2);
        path.lineTo(0.8f * fM3305getWidthimpl2, fM3305getWidthimpl2 * fLerp4);
        PathMeasure pathMeasure = m0Var.f6206b;
        pathMeasure.setPath(path, false);
        Path path2 = m0Var.f6207c;
        path2.reset();
        pathMeasure.getSegment(f11, pathMeasure.getLength() * fFloatValue, path2, true);
        androidx.compose.ui.graphics.drawscope.c.G(drawScope, m0Var.f6207c, jM3482unboximpl3, 0.0f, stroke2, null, 0, 52, null);
        return x6.t0.f22605a;
    }
}
