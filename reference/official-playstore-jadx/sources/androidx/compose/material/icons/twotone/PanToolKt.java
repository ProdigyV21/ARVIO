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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panTool", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanTool", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPanTool", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanToolKt {
    private static ImageVector _panTool;

    public static final ImageVector getPanTool(Icons.TwoTone twoTone) {
        ImageVector imageVector = _panTool;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PanTool", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.5f, 5.65f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 3.42f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 2.51f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 4.79f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.23f, -0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(12.87f);
        pathBuilderA.lineToRelative(-5.35f, -2.83f);
        pathBuilderA.lineToRelative(-0.51f, 0.45f);
        pathBuilderA.lineToRelative(5.86f, 6.1f);
        pathBuilderA.curveToRelative(0.38f, 0.39f, 0.9f, 0.62f, 1.44f, 0.62f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(6.15f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.5f, 3.65f);
        pathBuilderA2.curveToRelative(-0.17f, 0.0f, -0.34f, 0.02f, -0.5f, 0.05f);
        pathBuilderA2.verticalLineToRelative(-0.28f);
        pathBuilderA2.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA2.curveToRelative(-0.33f, 0.0f, -0.65f, 0.06f, -0.94f, 0.18f);
        pathBuilderA2.curveTo(15.11f, 0.44f, 14.35f, 0.01f, 13.5f, 0.01f);
        pathBuilderA2.curveToRelative(-1.32f, 0.0f, -2.41f, 1.03f, -2.49f, 2.33f);
        pathBuilderA2.curveToRelative(-0.16f, -0.03f, -0.33f, -0.05f, -0.51f, -0.05f);
        pathBuilderA2.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilderA2.verticalLineToRelative(9.55f);
        pathBuilderA2.lineToRelative(-2.41f, -1.28f);
        pathBuilderA2.curveToRelative(-0.73f, -0.39f, -1.64f, -0.28f, -2.26f, 0.27f);
        pathBuilderA2.lineToRelative(-2.07f, 1.83f);
        pathBuilderA2.lineToRelative(7.3f, 7.61f);
        pathBuilderA2.curveToRelative(0.75f, 0.78f, 1.8f, 1.23f, 2.89f, 1.23f);
        pathBuilderA2.horizontalLineTo(18.0f);
        pathBuilderA2.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA2.verticalLineTo(6.15f);
        pathBuilderA2.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(20.0f, 20.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(-6.55f);
        pathBuilderA2.curveToRelative(-0.54f, 0.0f, -1.07f, -0.22f, -1.44f, -0.62f);
        pathBuilderA2.lineToRelative(-5.86f, -6.11f);
        pathBuilderA2.lineToRelative(0.51f, -0.45f);
        pathBuilderA2.lineTo(10.0f, 17.66f);
        pathBuilderA2.verticalLineTo(4.79f);
        pathBuilderA2.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA2.reflectiveCurveToRelative(0.5f, 0.23f, 0.5f, 0.5f);
        c.i(pathBuilderA2, 12.0f, 2.0f, 2.51f);
        pathBuilderA2.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA2.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        c.i(pathBuilderA2, 12.0f, 2.0f, 3.42f);
        pathBuilderA2.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA2.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        c.i(pathBuilderA2, 12.0f, 2.0f, 6.15f);
        pathBuilderA2.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA2.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA2, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panTool = imageVectorBuild;
        return imageVectorBuild;
    }
}
