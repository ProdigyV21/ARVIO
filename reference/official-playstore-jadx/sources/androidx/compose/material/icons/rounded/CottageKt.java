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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cottage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cottage", "Landroidx/compose/material/icons/Icons$Rounded;", "getCottage", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CottageKt {
    private static ImageVector _cottage;

    public static final ImageVector getCottage(Icons.Rounded rounded) {
        ImageVector imageVector = _cottage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Cottage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.39f, 12.19f);
        pathBuilderA.curveToRelative(0.34f, -0.44f, 0.25f, -1.07f, -0.19f, -1.4f);
        pathBuilderA.lineToRelative(-9.6f, -7.33f);
        pathBuilderA.curveToRelative(-0.36f, -0.27f, -0.86f, -0.27f, -1.21f, 0.0f);
        pathBuilderA.lineTo(6.0f, 7.58f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(4.0f, 6.45f, 4.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(2.11f);
        pathBuilderA.lineToRelative(-2.21f, 1.68f);
        pathBuilderA.curveToRelative(-0.44f, 0.33f, -0.52f, 0.96f, -0.19f, 1.4f);
        pathBuilderA.curveToRelative(0.34f, 0.44f, 0.96f, 0.52f, 1.4f, 0.19f);
        pathBuilderA.lineTo(4.0f, 11.62f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-8.38f);
        pathBuilderA.lineToRelative(0.99f, 0.76f);
        pathBuilderA.curveTo(21.43f, 12.72f, 22.06f, 12.63f, 22.39f, 12.19f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.27f, 5.0f);
        pathBuilderA.curveToRelative(-0.74f, 0.0f, -1.26f, -0.8f, -0.9f, -1.45f);
        pathBuilderA.curveTo(4.89f, 2.62f, 5.87f, 2.0f, 7.0f, 2.0f);
        pathBuilderA.curveToRelative(0.38f, 0.0f, 0.72f, -0.22f, 0.89f, -0.53f);
        pathBuilderA.curveTo(8.04f, 1.16f, 8.39f, 1.0f, 8.73f, 1.0f);
        pathBuilderA.curveToRelative(0.74f, 0.0f, 1.26f, 0.8f, 0.9f, 1.45f);
        pathBuilderA.curveTo(9.11f, 3.38f, 8.13f, 4.0f, 7.0f, 4.0f);
        pathBuilderA.curveTo(6.62f, 4.0f, 6.28f, 4.22f, 6.11f, 4.53f);
        pathBuilderA.curveTo(5.96f, 4.84f, 5.61f, 5.0f, 5.27f, 5.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cottage = imageVectorBuild;
        return imageVectorBuild;
    }
}
