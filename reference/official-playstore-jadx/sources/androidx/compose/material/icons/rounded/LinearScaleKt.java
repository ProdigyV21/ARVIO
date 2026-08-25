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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_linearScale", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LinearScale", "Landroidx/compose/material/icons/Icons$Rounded;", "getLinearScale", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LinearScaleKt {
    private static ImageVector _linearScale;

    public static final ImageVector getLinearScale(Icons.Rounded rounded) {
        ImageVector imageVector = _linearScale;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LinearScale", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 7.0f);
        pathBuilderA.curveToRelative(-2.41f, 0.0f, -4.43f, 1.72f, -4.9f, 4.0f);
        pathBuilderA.horizontalLineTo(6.79f);
        pathBuilderA.curveTo(6.4f, 10.12f, 5.52f, 9.5f, 4.5f, 9.5f);
        pathBuilderA.curveTo(3.12f, 9.5f, 2.0f, 10.62f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderA.curveToRelative(1.02f, 0.0f, 1.9f, -0.62f, 2.29f, -1.5f);
        pathBuilderA.horizontalLineToRelative(5.31f);
        pathBuilderA.curveToRelative(0.46f, 2.28f, 2.48f, 4.0f, 4.9f, 4.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(19.76f, 7.0f, 17.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 15.0f);
        pathBuilderA.curveToRelative(-1.65f, 0.0f, -3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 18.65f, 15.0f, 17.0f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _linearScale = imageVectorBuild;
        return imageVectorBuild;
    }
}
