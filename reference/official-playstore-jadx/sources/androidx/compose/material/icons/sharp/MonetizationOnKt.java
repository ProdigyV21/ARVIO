package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_monetizationOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MonetizationOn", "Landroidx/compose/material/icons/Icons$Sharp;", "getMonetizationOn", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MonetizationOnKt {
    private static ImageVector _monetizationOn;

    public static final ImageVector getMonetizationOn(Icons.Sharp sharp) {
        ImageVector imageVector = _monetizationOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MonetizationOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        a.p(pathBuilderA, 13.41f, 18.09f, 13.41f, 20.0f);
        pathBuilderA.horizontalLineToRelative(-2.67f);
        pathBuilderA.verticalLineToRelative(-1.93f);
        pathBuilderA.curveToRelative(-1.71f, -0.36f, -3.16f, -1.46f, -3.27f, -3.4f);
        pathBuilderA.horizontalLineToRelative(1.96f);
        pathBuilderA.curveToRelative(0.1f, 1.05f, 0.82f, 1.87f, 2.65f, 1.87f);
        pathBuilderA.curveToRelative(1.96f, 0.0f, 2.4f, -0.98f, 2.4f, -1.59f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.44f, -1.61f, -2.67f, -2.14f);
        pathBuilderA.curveToRelative(-2.48f, -0.6f, -4.18f, -1.62f, -4.18f, -3.67f);
        pathBuilderA.curveToRelative(0.0f, -1.72f, 1.39f, -2.84f, 3.11f, -3.21f);
        b.A(pathBuilderA, 10.74f, 4.0f, 2.67f, 1.95f);
        pathBuilderA.curveToRelative(1.86f, 0.45f, 2.79f, 1.86f, 2.85f, 3.39f);
        pathBuilderA.lineTo(14.3f, 9.34f);
        pathBuilderA.curveToRelative(-0.05f, -1.11f, -0.64f, -1.87f, -2.22f, -1.87f);
        pathBuilderA.curveToRelative(-1.5f, 0.0f, -2.4f, 0.68f, -2.4f, 1.64f);
        pathBuilderA.curveToRelative(0.0f, 0.84f, 0.65f, 1.39f, 2.67f, 1.91f);
        pathBuilderA.reflectiveCurveToRelative(4.18f, 1.39f, 4.18f, 3.91f);
        pathBuilderA.curveToRelative(-0.01f, 1.83f, -1.38f, 2.83f, -3.12f, 3.16f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _monetizationOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
