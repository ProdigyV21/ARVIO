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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stroller", "Landroidx/compose/material/icons/Icons$Outlined;", "getStroller", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrollerKt {
    private static ImageVector _stroller;

    public static final ImageVector getStroller(Icons.Outlined outlined) {
        ImageVector imageVector = _stroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Stroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(18.0f, 18.9f, 18.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 18.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(7.1f, 18.0f, 6.0f, 18.0f);
        a.p(pathBuilderA, 15.0f, 8.66f, 9.6f, 15.0f);
        pathBuilderA.horizontalLineTo(15.0f);
        pathBuilderA.verticalLineTo(8.66f);
        pathBuilderA.moveTo(18.65f, 3.0f);
        pathBuilderA.curveTo(20.52f, 3.0f, 22.0f, 4.56f, 22.0f, 6.48f);
        c.i(pathBuilderA, 7.0f, -2.0f, 6.48f);
        pathBuilderA.curveTo(20.0f, 5.66f, 19.42f, 5.0f, 18.65f, 5.0f);
        pathBuilderA.curveTo(17.97f, 5.0f, 17.58f, 5.59f, 17.0f, 6.27f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(7.43f);
        pathBuilderA.curveToRelative(-0.85f, 0.0f, -1.31f, -1.0f, -0.76f, -1.65f);
        pathBuilderA.lineToRelative(8.8f, -10.32f);
        pathBuilderA.curveTo(16.11f, 4.27f, 16.99f, 3.0f, 18.65f, 3.0f);
        c.z(pathBuilderA, 18.65f, 3.0f, 10.0f, 5.0f);
        pathBuilderA.curveTo(9.35f, 5.0f, 8.71f, 5.09f, 8.09f, 5.27f);
        pathBuilderA.lineToRelative(1.4f, 1.4f);
        pathBuilderA.lineToRelative(1.37f, -1.61f);
        pathBuilderA.curveTo(10.58f, 5.02f, 10.29f, 5.0f, 10.0f, 5.0f);
        pathBuilderA.moveTo(10.0f, 3.0f);
        pathBuilderA.curveToRelative(1.56f, 0.0f, 3.03f, 0.4f, 4.3f, 1.1f);
        pathBuilderA.lineToRelative(-4.7f, 5.51f);
        pathBuilderA.lineTo(4.72f, 4.72f);
        pathBuilderA.curveTo(6.21f, 3.64f, 8.03f, 3.0f, 10.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 10.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
