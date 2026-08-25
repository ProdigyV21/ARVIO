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
import v.a;
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbAuto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWbAuto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbAutoKt {
    private static ImageVector _wbAuto;

    public static final ImageVector getWbAuto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wbAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WbAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.curveToRelative(2.35f, 0.0f, 4.38f, -1.36f, 5.36f, -3.32f);
        pathBuilderA.lineToRelative(0.01f, -0.01f);
        pathBuilderA.curveToRelative(0.4f, -0.81f, 0.63f, -1.71f, 0.63f, -2.67f);
        pathBuilderA.curveToRelative(0.0f, -3.31f, -2.69f, -6.0f, -6.0f, -6.0f);
        a.z(pathBuilderA, 10.3f, 16.0f, -0.7f, -2.0f);
        pathBuilderA.lineTo(6.4f, 14.0f);
        pathBuilderA.lineToRelative(-0.7f, 2.0f);
        pathBuilderA.lineTo(3.8f, 16.0f);
        pathBuilderA.lineTo(7.0f, 7.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.lineToRelative(3.2f, 9.0f);
        pathBuilderA.horizontalLineToRelative(-1.9f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.85f, 12.65f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderA, 2.3f, 8.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(7.0f, 7.0f, -3.2f, 9.0f, 1.9f);
        pathBuilderC.lineToRelative(0.7f, -2.0f);
        e.C(pathBuilderC, 3.2f, 0.7f, 2.0f, 1.9f);
        b.y(pathBuilderC, 9.0f, 7.0f, 7.0f, 7.0f);
        pathBuilderC.moveTo(6.85f, 12.65f);
        pathBuilderC.lineTo(8.0f, 9.0f);
        y.a.k(pathBuilderC, 1.15f, 3.65f, -2.3f);
        pathBuilderC.moveTo(20.8f, 13.29f);
        pathBuilderC.lineTo(19.3f, 7.0f);
        pathBuilderC.horizontalLineToRelative(-1.6f);
        pathBuilderC.lineToRelative(-1.49f, 6.29f);
        pathBuilderC.lineTo(15.0f, 7.0f);
        pathBuilderC.horizontalLineToRelative(-0.76f);
        pathBuilderC.lineToRelative(-0.01f, 0.01f);
        pathBuilderC.curveTo(12.76f, 5.18f, 10.53f, 4.0f, 8.0f, 4.0f);
        pathBuilderC.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderC.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderC.curveToRelative(2.96f, 0.0f, 5.55f, -1.61f, 6.93f, -4.0f);
        pathBuilderC.curveToRelative(0.03f, -0.06f, 0.05f, -0.12f, 0.08f, -0.18f);
        pathBuilderC.curveToRelative(0.05f, -0.08f, 0.09f, -0.17f, 0.14f, -0.25f);
        pathBuilderC.lineToRelative(0.1f, 0.43f);
        pathBuilderC.lineTo(17.0f, 16.0f);
        pathBuilderC.lineToRelative(1.5f, -6.1f);
        pathBuilderC.lineTo(20.0f, 16.0f);
        pathBuilderC.horizontalLineToRelative(1.75f);
        pathBuilderC.lineToRelative(2.05f, -9.0f);
        d.l(pathBuilderC, 22.0f, 7.0f, -1.2f, 6.29f);
        pathBuilderC.moveTo(13.37f, 14.67f);
        pathBuilderC.curveTo(12.38f, 16.64f, 10.35f, 18.0f, 8.0f, 18.0f);
        pathBuilderC.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderC.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderC.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderC.curveToRelative(0.0f, 0.96f, -0.23f, 1.86f, -0.63f, 2.67f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}
