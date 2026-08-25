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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lightMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LightMode", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLightMode", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LightModeKt {
    private static ImageVector _lightMode;

    public static final ImageVector getLightMode(Icons.TwoTone twoTone) {
        ImageVector imageVector = _lightMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LightMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 12.0f, -3.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, 6.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 9.0f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(10.35f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.moveTo(12.0f, 7.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(14.76f, 7.0f, 12.0f, 7.0f);
        c.z(pathBuilderA, 12.0f, 7.0f, 2.0f, 13.0f);
        pathBuilderA.lineToRelative(2.0f, 0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.lineToRelative(-2.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(1.45f, 13.0f, 2.0f, 13.0f);
        a.z(pathBuilderA, 20.0f, 13.0f, 2.0f, 0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.lineToRelative(-2.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(19.45f, 13.0f, 20.0f, 13.0f);
        d.k(pathBuilderA, 11.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(11.0f, 1.45f, 11.0f, 2.0f);
        d.k(pathBuilderA, 11.0f, 20.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.curveTo(11.45f, 19.0f, 11.0f, 19.45f, 11.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.99f, 4.58f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(1.06f, 1.06f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(0.39f, -1.03f, 0.0f, -1.41f);
        c.z(pathBuilderA, 5.99f, 4.58f, 18.36f, 16.95f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(1.06f, 1.06f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.41f);
        c.z(pathBuilderA, 18.36f, 16.95f, 19.42f, 5.99f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-1.06f, 1.06f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(1.03f, 0.39f, 1.41f, 0.0f);
        c.z(pathBuilderA, 19.42f, 5.99f, 7.05f, 18.36f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.03f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-1.06f, 1.06f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(1.03f, 0.39f, 1.41f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 7.05f, 18.36f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _lightMode = imageVectorBuild;
        return imageVectorBuild;
    }
}
