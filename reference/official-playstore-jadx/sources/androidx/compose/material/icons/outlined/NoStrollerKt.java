package androidx.compose.material.icons.outlined;

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noStroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoStroller", "Landroidx/compose/material/icons/Icons$Outlined;", "getNoStroller", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoStrollerKt {
    private static ImageVector _noStroller;

    public static final ImageVector getNoStroller(Icons.Outlined outlined) {
        ImageVector imageVector = _noStroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NoStroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 18.9f, 8.0f, 20.0f);
        d.k(pathBuilderA, 15.0f, 8.66f, 3.51f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-7.9f);
        pathBuilderA.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilderA.curveTo(19.42f, 5.0f, 20.0f, 5.66f, 20.0f, 6.48f);
        c.i(pathBuilderA, 7.0f, 2.0f, 6.48f);
        pathBuilderA.curveTo(22.0f, 4.56f, 20.52f, 3.0f, 18.65f, 3.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilderA.lineToRelative(-3.5f, 4.11f);
        d.C(pathBuilderA, 1.42f, 1.42f, 15.0f, 8.66f);
        pathBuilderA.moveTo(19.78f, 22.61f);
        pathBuilderA.lineToRelative(-1.91f, -1.91f);
        pathBuilderA.curveTo(17.58f, 21.46f, 16.86f, 22.0f, 16.0f, 22.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.86f, 0.54f, -1.58f, 1.3f, -1.87f);
        pathBuilderA.lineTo(14.17f, 17.0f);
        pathBuilderA.horizontalLineTo(7.43f);
        pathBuilderA.curveToRelative(-0.85f, 0.0f, -1.31f, -1.0f, -0.76f, -1.65f);
        pathBuilderA.lineToRelative(2.69f, -3.16f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineToRelative(7.86f, 7.86f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        d.C(pathBuilderA, 9.11f, 9.11f, 19.78f, 22.61f);
        pathBuilderA.moveTo(12.17f, 15.0f);
        pathBuilderA.lineToRelative(-1.39f, -1.39f);
        a.e(pathBuilderA, 9.6f, 15.0f, 12.17f);
        pathBuilderA.moveTo(10.0f, 5.0f);
        pathBuilderA.curveToRelative(0.29f, 0.0f, 0.58f, 0.02f, 0.86f, 0.05f);
        pathBuilderA.lineTo(9.49f, 6.67f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.lineTo(14.3f, 4.1f);
        pathBuilderA.curveTo(13.03f, 3.4f, 11.56f, 3.0f, 10.0f, 3.0f);
        pathBuilderA.curveTo(8.77f, 3.0f, 7.6f, 3.25f, 6.53f, 3.7f);
        pathBuilderA.lineTo(8.1f, 5.27f);
        pathBuilderA.curveTo(8.71f, 5.1f, 9.35f, 5.0f, 10.0f, 5.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noStroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
