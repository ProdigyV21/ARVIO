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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_euro", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Euro", "Landroidx/compose/material/icons/Icons$Rounded;", "getEuro", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EuroKt {
    private static ImageVector _euro;

    public static final ImageVector getEuro(Icons.Rounded rounded) {
        ImageVector imageVector = _euro;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Euro", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 18.5f);
        pathBuilderA.curveToRelative(-2.51f, 0.0f, -4.68f, -1.42f, -5.76f, -3.5f);
        pathBuilderA.horizontalLineToRelative(5.14f);
        pathBuilderA.curveToRelative(0.38f, 0.0f, 0.73f, -0.21f, 0.89f, -0.55f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.33f, -0.66f, -0.15f, -1.45f, -0.89f, -1.45f);
        pathBuilderA.horizontalLineToRelative(-5.8f);
        pathBuilderA.curveToRelative(-0.05f, -0.33f, -0.08f, -0.66f, -0.08f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.03f, -0.67f, 0.08f, -1.0f);
        pathBuilderA.horizontalLineToRelative(5.8f);
        pathBuilderA.curveToRelative(0.38f, 0.0f, 0.73f, -0.21f, 0.89f, -0.55f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(15.61f, 9.78f, 15.13f, 9.0f, 14.38f, 9.0f);
        pathBuilderA.horizontalLineTo(9.24f);
        pathBuilderA.curveTo(10.32f, 6.92f, 12.5f, 5.5f, 15.0f, 5.5f);
        pathBuilderA.curveToRelative(1.25f, 0.0f, 2.42f, 0.36f, 3.42f, 0.97f);
        pathBuilderA.curveToRelative(0.5f, 0.31f, 1.15f, 0.26f, 1.57f, -0.16f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.58f, -0.58f, 0.45f, -1.53f, -0.25f, -1.96f);
        pathBuilderA.curveTo(18.36f, 3.5f, 16.73f, 3.0f, 15.0f, 3.0f);
        pathBuilderA.curveToRelative(-3.92f, 0.0f, -7.24f, 2.51f, -8.48f, 6.0f);
        pathBuilderA.horizontalLineToRelative(-2.9f);
        pathBuilderA.curveTo(3.24f, 9.0f, 2.89f, 9.21f, 2.72f, 9.55f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(2.39f, 10.22f, 2.87f, 11.0f, 3.62f, 11.0f);
        pathBuilderA.horizontalLineToRelative(2.44f);
        pathBuilderA.curveTo(6.02f, 11.33f, 6.0f, 11.66f, 6.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.02f, 0.67f, 0.06f, 1.0f);
        pathBuilderA.horizontalLineTo(3.62f);
        pathBuilderA.curveToRelative(-0.38f, 0.0f, -0.73f, 0.21f, -0.89f, 0.55f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(2.39f, 14.22f, 2.87f, 15.0f, 3.62f, 15.0f);
        pathBuilderA.horizontalLineToRelative(2.9f);
        pathBuilderA.curveToRelative(1.24f, 3.49f, 4.56f, 6.0f, 8.48f, 6.0f);
        pathBuilderA.curveToRelative(1.74f, 0.0f, 3.36f, -0.49f, 4.74f, -1.35f);
        pathBuilderA.curveToRelative(0.69f, -0.43f, 0.82f, -1.39f, 0.24f, -1.97f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.42f, -0.42f, -1.07f, -0.47f, -1.57f, -0.15f);
        pathBuilderA.curveTo(17.42f, 18.15f, 16.26f, 18.5f, 15.0f, 18.5f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _euro = imageVectorBuild;
        return imageVectorBuild;
    }
}
