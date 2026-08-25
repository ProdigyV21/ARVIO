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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatFlatAngled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatFlatAngled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAirlineSeatFlatAngled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatFlatAngledKt {
    private static ImageVector _airlineSeatFlatAngled;

    public static final ImageVector getAirlineSeatFlatAngled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _airlineSeatFlatAngled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AirlineSeatFlatAngled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.0f, 16.64f, -4.0f, -1.45f);
        f.n(pathBuilderR, 10.0f, 17.0f, 4.0f);
        pathBuilderR.moveTo(6.0f, 8.5f);
        pathBuilderR.curveToRelative(0.15f, 0.0f, 0.3f, -0.03f, 0.44f, -0.1f);
        pathBuilderR.curveToRelative(0.49f, -0.24f, 0.7f, -0.84f, 0.46f, -1.34f);
        pathBuilderR.curveToRelative(-0.19f, -0.41f, -0.59f, -0.56f, -0.9f, -0.56f);
        pathBuilderR.curveToRelative(-0.15f, 0.0f, -0.3f, 0.03f, -0.44f, 0.1f);
        pathBuilderR.curveToRelative(-0.32f, 0.16f, -0.45f, 0.42f, -0.5f, 0.56f);
        pathBuilderR.curveToRelative(-0.05f, 0.15f, -0.12f, 0.44f, 0.04f, 0.77f);
        pathBuilderR.curveToRelative(0.2f, 0.42f, 0.59f, 0.57f, 0.9f, 0.57f);
        a.z(pathBuilderR, 19.16f, 11.02f, -6.69f, -2.41f);
        pathBuilderR.lineToRelative(-0.7f, 1.91f);
        pathBuilderR.lineToRelative(8.59f, 3.11f);
        pathBuilderR.lineToRelative(0.01f, -0.02f);
        pathBuilderR.curveToRelative(0.19f, -0.51f, 0.17f, -1.05f, -0.06f, -1.53f);
        pathBuilderR.curveToRelative(-0.23f, -0.5f, -0.63f, -0.87f, -1.15f, -1.06f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(1.5f, 12.14f, 8.0f, 14.48f);
        b.A(pathBuilderJ, 8.0f, 19.0f, 8.0f, -1.63f);
        pathBuilderJ.lineTo(20.52f, 19.0f);
        pathBuilderJ.lineToRelative(0.69f, -1.89f);
        b.D(pathBuilderJ, -19.02f, -6.86f, -0.69f, 1.89f);
        pathBuilderJ.moveTo(10.0f, 15.19f);
        pathBuilderJ.lineToRelative(4.0f, 1.44f);
        b.g(pathBuilderJ, 14.0f, 17.0f, -4.0f, -1.81f);
        pathBuilderJ.moveTo(19.84f, 9.14f);
        pathBuilderJ.lineToRelative(-8.56f, -3.09f);
        pathBuilderJ.lineToRelative(-2.08f, 5.66f);
        pathBuilderJ.lineToRelative(12.36f, 4.47f);
        pathBuilderJ.lineToRelative(0.69f, -1.89f);
        pathBuilderJ.curveToRelative(0.77f, -2.09f, -0.31f, -4.39f, -2.41f, -5.15f);
        a.z(pathBuilderJ, 20.37f, 13.6f, -0.01f, 0.02f);
        pathBuilderJ.lineToRelative(-8.59f, -3.11f);
        pathBuilderJ.lineToRelative(0.7f, -1.91f);
        pathBuilderJ.lineToRelative(6.69f, 2.41f);
        pathBuilderJ.curveToRelative(0.52f, 0.19f, 0.93f, 0.56f, 1.15f, 1.05f);
        pathBuilderJ.curveToRelative(0.23f, 0.49f, 0.25f, 1.04f, 0.06f, 1.54f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(6.0f, 10.5f);
        pathBuilderJ.curveToRelative(0.44f, 0.0f, 0.88f, -0.1f, 1.3f, -0.3f);
        pathBuilderJ.curveToRelative(1.49f, -0.72f, 2.12f, -2.51f, 1.41f, -4.0f);
        pathBuilderJ.curveTo(8.19f, 5.13f, 7.12f, 4.5f, 6.0f, 4.5f);
        pathBuilderJ.curveToRelative(-0.44f, 0.0f, -0.88f, 0.1f, -1.3f, 0.3f);
        pathBuilderJ.curveToRelative(-1.49f, 0.71f, -2.12f, 2.5f, -1.4f, 4.0f);
        pathBuilderJ.curveToRelative(0.51f, 1.07f, 1.58f, 1.7f, 2.7f, 1.7f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(5.06f, 7.16f);
        pathBuilderJ.curveToRelative(0.05f, -0.14f, 0.18f, -0.4f, 0.51f, -0.56f);
        pathBuilderJ.curveToRelative(0.14f, -0.06f, 0.28f, -0.1f, 0.43f, -0.1f);
        pathBuilderJ.curveToRelative(0.31f, 0.0f, 0.7f, 0.15f, 0.9f, 0.56f);
        pathBuilderJ.curveToRelative(0.24f, 0.5f, 0.02f, 1.1f, -0.47f, 1.34f);
        pathBuilderJ.curveToRelative(-0.14f, 0.06f, -0.28f, 0.1f, -0.43f, 0.1f);
        pathBuilderJ.curveToRelative(-0.3f, 0.0f, -0.7f, -0.15f, -0.89f, -0.56f);
        pathBuilderJ.curveToRelative(-0.17f, -0.34f, -0.1f, -0.63f, -0.05f, -0.78f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatFlatAngled = imageVectorBuild;
        return imageVectorBuild;
    }
}
