package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noStroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoStroller", "Landroidx/compose/material/icons/Icons$Filled;", "getNoStroller", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoStrollerKt {
    private static ImageVector _noStroller;

    public static final ImageVector getNoStroller(Icons.Filled filled) {
        ImageVector imageVector = _noStroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoStroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 18.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(4.9f, 18.0f, 6.0f, 18.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.65f, 3.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilderA.lineToRelative(-3.5f, 4.11f);
        pathBuilderA.lineTo(17.0f, 14.17f);
        pathBuilderA.verticalLineToRelative(-7.9f);
        pathBuilderA.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilderA.curveTo(19.42f, 5.0f, 20.0f, 5.66f, 20.0f, 6.48f);
        c.i(pathBuilderA, 7.0f, 2.0f, 6.48f);
        pathBuilderA.curveTo(22.0f, 4.56f, 20.52f, 3.0f, 18.65f, 3.0f);
        a.p(pathBuilderA, 10.67f, 10.67f, 2.81f, 2.81f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(7.97f, 7.97f);
        pathBuilderA.lineTo(6.7f, 15.31f);
        pathBuilderA.curveToRelative(-0.55f, 0.65f, -0.09f, 1.65f, 0.76f, 1.65f);
        pathBuilderA.horizontalLineToRelative(6.66f);
        pathBuilderA.lineToRelative(1.17f, 1.17f);
        pathBuilderA.curveTo(14.54f, 18.42f, 14.0f, 19.14f, 14.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.86f, 0.0f, 1.58f, -0.54f, 1.87f, -1.3f);
        pathBuilderA.lineToRelative(1.91f, 1.91f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderA, -4.8f, -4.8f, 10.67f, 10.67f);
        pathBuilderA.moveTo(13.47f, 5.03f);
        pathBuilderA.curveToRelative(0.27f, -0.32f, 0.58f, -0.72f, 0.98f, -1.09f);
        pathBuilderA.curveToRelative(-2.46f, -1.19f, -5.32f, -1.22f, -7.81f, -0.13f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 4.25f, 4.25f, 13.47f, 5.03f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noStroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
