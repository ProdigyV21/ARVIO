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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localDrink", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalDrink", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalDrink", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalDrinkKt {
    private static ImageVector _localDrink;

    public static final ImageVector getLocalDrink(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localDrink;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalDrink", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.0f, 20.01f, 17.0f, 20.0f);
        pathBuilderJ.lineToRelative(1.1f, -10.0f);
        b.y(pathBuilderJ, 5.89f, 10.0f, 7.0f, 20.01f);
        pathBuilderJ.moveTo(12.0f, 10.6f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, 3.4f, 3.0f, 5.4f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.0f, 3.0f, -5.4f, 3.0f, -5.4f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.01f, 20.23f);
        pathBuilderA.curveTo(5.13f, 21.23f, 5.97f, 22.0f, 7.0f, 22.0f);
        pathBuilderA.horizontalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(1.03f, 0.0f, 1.87f, -0.77f, 1.99f, -1.77f);
        pathBuilderA.lineTo(21.0f, 2.0f);
        d.l(pathBuilderA, 3.0f, 2.0f, 2.01f, 18.23f);
        pathBuilderA.moveTo(17.0f, 20.0f);
        pathBuilderA.lineToRelative(-10.0f, 0.01f);
        pathBuilderA.lineTo(5.89f, 10.0f);
        b.y(pathBuilderA, 18.1f, 10.0f, 17.0f, 20.0f);
        pathBuilderA.moveTo(18.76f, 4.0f);
        pathBuilderA.lineToRelative(-0.43f, 4.0f);
        pathBuilderA.lineTo(5.67f, 8.0f);
        y.a.k(pathBuilderA, -0.44f, -4.0f, 13.53f);
        pathBuilderA.moveTo(12.0f, 19.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -2.0f, -3.0f, -5.4f, -3.0f, -5.4f);
        pathBuilderA.reflectiveCurveTo(9.0f, 14.0f, 9.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 13.91f);
        pathBuilderA.curveToRelative(0.59f, 0.91f, 1.0f, 1.73f, 1.0f, 2.09f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, 0.41f, -1.19f, 1.0f, -2.09f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localDrink = imageVectorBuild;
        return imageVectorBuild;
    }
}
