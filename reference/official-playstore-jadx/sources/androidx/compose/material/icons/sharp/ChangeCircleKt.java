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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_changeCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ChangeCircle", "Landroidx/compose/material/icons/Icons$Sharp;", "getChangeCircle", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChangeCircleKt {
    private static ImageVector _changeCircle;

    public static final ImageVector getChangeCircle(Icons.Sharp sharp) {
        ImageVector imageVector = _changeCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ChangeCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        d.k(pathBuilderA, 12.06f, 19.0f, -2.01f);
        pathBuilderA.curveToRelative(-0.02f, 0.0f, -0.04f, 0.0f, -0.06f, 0.0f);
        pathBuilderA.curveToRelative(-1.28f, 0.0f, -2.56f, -0.49f, -3.54f, -1.46f);
        pathBuilderA.curveToRelative(-1.71f, -1.71f, -1.92f, -4.35f, -0.64f, -6.29f);
        pathBuilderA.lineToRelative(1.1f, 1.1f);
        pathBuilderA.curveToRelative(-0.71f, 1.33f, -0.53f, 3.01f, 0.59f, 4.13f);
        pathBuilderA.curveToRelative(0.7f, 0.7f, 1.62f, 1.03f, 2.54f, 1.01f);
        pathBuilderA.verticalLineToRelative(-2.14f);
        d.C(pathBuilderA, 2.83f, 2.83f, 12.06f, 19.0f);
        pathBuilderA.moveTo(16.17f, 14.76f);
        pathBuilderA.lineToRelative(-1.1f, -1.1f);
        pathBuilderA.curveToRelative(0.71f, -1.33f, 0.53f, -3.01f, -0.59f, -4.13f);
        pathBuilderA.curveTo(13.79f, 8.84f, 12.9f, 8.5f, 12.0f, 8.5f);
        pathBuilderA.curveToRelative(-0.02f, 0.0f, -0.04f, 0.0f, -0.06f, 0.0f);
        pathBuilderA.verticalLineToRelative(2.15f);
        pathBuilderA.lineTo(9.11f, 7.83f);
        pathBuilderA.lineTo(11.94f, 5.0f);
        pathBuilderA.verticalLineToRelative(2.02f);
        pathBuilderA.curveToRelative(1.3f, -0.02f, 2.61f, 0.45f, 3.6f, 1.45f);
        pathBuilderA.curveTo(17.24f, 10.17f, 17.45f, 12.82f, 16.17f, 14.76f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _changeCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
