package androidx.compose.material.icons.rounded;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoDelete", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoDelete", "Landroidx/compose/material/icons/Icons$Rounded;", "getAutoDelete", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoDeleteKt {
    private static ImageVector _autoDelete;

    public static final ImageVector getAutoDelete(Icons.Rounded rounded) {
        ImageVector imageVector = _autoDelete;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AutoDelete", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 9.0f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.37f, 0.1f, -2.0f, 0.29f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(5.68f);
        pathBuilderA.curveToRelative(1.12f, 2.36f, 3.53f, 4.0f, 6.32f, 4.0f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 7.0f, -3.13f, 7.0f, -7.0f);
        pathBuilderA.curveTo(23.0f, 12.13f, 19.87f, 9.0f, 16.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 21.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 18.76f, 21.0f, 16.0f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(14.0f, 4.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA2.horizontalLineToRelative(-2.5f);
        pathBuilderA2.lineToRelative(-0.71f, -0.71f);
        pathBuilderA2.curveTo(10.61f, 1.11f, 10.35f, 1.0f, 10.09f, 1.0f);
        pathBuilderA2.horizontalLineTo(5.91f);
        pathBuilderA2.curveTo(5.65f, 1.0f, 5.39f, 1.11f, 5.21f, 1.29f);
        pathBuilderA2.lineTo(4.5f, 2.0f);
        pathBuilderA2.horizontalLineTo(2.0f);
        pathBuilderA2.curveTo(1.45f, 2.0f, 1.0f, 2.45f, 1.0f, 3.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA2, 14.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.75f, 12.0f, 15.75f, 12.0f);
        pathBuilderJ.curveTo(15.34f, 12.0f, 15.0f, 12.34f, 15.0f, 12.75f);
        pathBuilderJ.verticalLineToRelative(3.68f);
        pathBuilderJ.curveToRelative(0.0f, 0.36f, 0.19f, 0.68f, 0.5f, 0.86f);
        pathBuilderJ.lineToRelative(2.52f, 1.47f);
        pathBuilderJ.curveToRelative(0.33f, 0.19f, 0.75f, 0.09f, 0.96f, -0.22f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.23f, -0.34f, 0.12f, -0.81f, -0.24f, -1.02f);
        pathBuilderJ.lineTo(16.5f, 16.2f);
        pathBuilderJ.verticalLineToRelative(-3.45f);
        pathBuilderJ.curveTo(16.5f, 12.34f, 16.16f, 12.0f, 15.75f, 12.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoDelete = imageVectorBuild;
        return imageVectorBuild;
    }
}
