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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_blurLinear", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BlurLinear", "Landroidx/compose/material/icons/Icons$Filled;", "getBlurLinear", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlurLinearKt {
    private static ImageVector _blurLinear;

    public static final ImageVector getBlurLinear(Icons.Filled filled) {
        ImageVector imageVector = _blurLinear;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BlurLinear", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.0f, 17.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 13.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        b.w(pathBuilderA, 3.0f, 21.0f, 18.0f, -2.0f);
        d.f(pathBuilderA, 3.0f, 19.0f, 2.0f);
        pathBuilderA.moveTo(5.0f, 9.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(5.83f, 6.5f, 5.0f, 6.5f);
        pathBuilderA.reflectiveCurveTo(3.5f, 7.17f, 3.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(4.17f, 9.5f, 5.0f, 9.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 13.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 16.5f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        c.y(pathBuilderA, 3.0f, 3.0f, 2.0f, 18.0f);
        b.y(pathBuilderA, 21.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.moveTo(17.0f, 8.5f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 12.5f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 9.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 13.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, 0.45f, 1.0f, 1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _blurLinear = imageVectorBuild;
        return imageVectorBuild;
    }
}
