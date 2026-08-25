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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_electricMeter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ElectricMeter", "Landroidx/compose/material/icons/Icons$Rounded;", "getElectricMeter", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ElectricMeterKt {
    private static ImageVector _electricMeter;

    public static final ImageVector getElectricMeter(Icons.Rounded rounded) {
        ImageVector imageVector = _electricMeter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ElectricMeter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.73f, 2.0f);
        pathBuilderA.curveTo(7.05f, 2.14f, 3.15f, 6.03f, 3.0f, 10.71f);
        pathBuilderA.curveToRelative(-0.13f, 4.04f, 2.42f, 7.5f, 6.0f, 8.77f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.06f);
        pathBuilderA.curveToRelative(0.33f, 0.04f, 0.66f, 0.06f, 1.0f, 0.06f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -0.02f, 1.0f, -0.06f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.53f);
        pathBuilderA.curveToRelative(3.49f, -1.24f, 6.0f, -4.57f, 6.0f, -8.47f);
        pathBuilderA.curveTo(21.0f, 5.95f, 16.82f, 1.85f, 11.73f, 2.0f);
        a.p(pathBuilderA, 13.54f, 14.71f, 12.0f, 16.25f);
        pathBuilderA.curveToRelative(-0.41f, 0.41f, -1.09f, 0.41f, -1.5f, 0.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.41f, -0.41f, -0.41f, -1.09f, 0.0f, -1.5f);
        pathBuilderA.lineToRelative(0.5f, -0.5f);
        pathBuilderA.lineToRelative(-0.54f, -0.54f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(12.0f, 10.75f);
        pathBuilderA.curveToRelative(0.41f, -0.41f, 1.09f, -0.41f, 1.5f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.41f, 0.41f, 0.41f, 1.09f, 0.0f, 1.5f);
        pathBuilderA.lineToRelative(-0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.54f, 0.54f);
        pathBuilderA.curveTo(13.93f, 13.68f, 13.93f, 14.32f, 13.54f, 14.71f);
        w.a.r(pathBuilderA, 15.0f, 9.0f, 9.0f);
        pathBuilderA.curveTo(8.45f, 9.0f, 8.0f, 8.55f, 8.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(16.0f, 8.55f, 15.55f, 9.0f, 15.0f, 9.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _electricMeter = imageVectorBuild;
        return imageVectorBuild;
    }
}
