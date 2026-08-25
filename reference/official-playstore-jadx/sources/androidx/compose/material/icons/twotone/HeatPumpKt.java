package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.c;
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heatPump", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeatPump", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHeatPump", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeatPumpKt {
    private static ImageVector _heatPump;

    public static final ImageVector getHeatPump(Icons.TwoTone twoTone) {
        ImageVector imageVector = _heatPump;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HeatPump", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(5.0f, 19.0f, 14.0f, 5.0f, 5.0f);
        f.r(pathBuilderS, 19.0f, 12.0f, 6.0f);
        pathBuilderS.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderS.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderS, 8.69f, 6.0f, 12.0f, 6.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        f.o(pathBuilderQ, 19.0f, 19.0f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 14.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 18.0f);
        pathBuilderA.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveTo(8.69f, 18.0f, 12.0f, 18.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.25f, 15.92f);
        pathBuilderA.curveToRelative(-0.55f, -0.1f, -1.05f, -0.32f, -1.5f, -0.62f);
        c.e(pathBuilderA, 1.5f, -1.5f, 15.92f);
        pathBuilderA.moveTo(12.75f, 15.92f);
        pathBuilderA.verticalLineToRelative(-2.11f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.curveTo(13.8f, 15.61f, 13.3f, 15.82f, 12.75f, 15.92f);
        v.a.z(pathBuilderA, 15.31f, 14.25f, -1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(2.11f);
        pathBuilderA.curveTo(15.82f, 13.3f, 15.61f, 13.8f, 15.31f, 14.25f);
        v.a.g(pathBuilderA, 15.92f, 11.25f, -2.11f);
        pathBuilderA.lineToRelative(1.5f, -1.5f);
        pathBuilderA.curveTo(15.61f, 10.2f, 15.82f, 10.7f, 15.92f, 11.25f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.75f, 8.08f);
        pathBuilderA.curveToRelative(0.55f, 0.1f, 1.05f, 0.32f, 1.5f, 0.62f);
        c.e(pathBuilderA, -1.5f, 1.5f, 8.08f);
        pathBuilderA.moveTo(12.0f, 11.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveTo(11.0f, 11.45f, 11.45f, 11.0f, 12.0f, 11.0f);
        d.k(pathBuilderA, 11.25f, 8.08f, 2.11f);
        pathBuilderA.lineToRelative(-1.5f, -1.5f);
        pathBuilderA.curveTo(10.2f, 8.39f, 10.7f, 8.18f, 11.25f, 8.08f);
        v.a.z(pathBuilderA, 8.69f, 9.75f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineTo(8.08f);
        pathBuilderA.curveTo(8.18f, 10.7f, 8.39f, 10.2f, 8.69f, 9.75f);
        v.a.z(pathBuilderA, 10.19f, 12.75f, -1.5f, 1.5f);
        pathBuilderA.curveToRelative(-0.3f, -0.44f, -0.51f, -0.95f, -0.62f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderA, 10.19f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heatPump = imageVectorBuild;
        return imageVectorBuild;
    }
}
