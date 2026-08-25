package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flaky", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flaky", "Landroidx/compose/material/icons/Icons$Rounded;", "getFlaky", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlakyKt {
    private static ImageVector _flaky;

    public static final ImageVector getFlaky(Icons.Rounded rounded) {
        ImageVector imageVector = _flaky;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Flaky", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.16f, 15.72f);
        pathBuilderA.curveToRelative(-0.29f, -0.29f, -0.29f, -0.77f, 0.0f, -1.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.29f, -0.29f, 0.77f, -0.29f, 1.06f, 0.0f);
        pathBuilderA.lineToRelative(0.82f, 0.82f);
        pathBuilderA.lineToRelative(1.96f, -1.96f);
        pathBuilderA.curveToRelative(0.29f, -0.29f, 0.77f, -0.29f, 1.06f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.29f, 0.29f, 0.29f, 0.77f, 0.0f, 1.06f);
        pathBuilderA.lineToRelative(-2.65f, 2.65f);
        pathBuilderA.curveToRelative(-0.19f, 0.19f, -0.51f, 0.2f, -0.7f, 0.0f);
        c.z(pathBuilderA, 12.16f, 15.72f, 12.0f, 2.0f);
        pathBuilderA.curveTo(6.5f, 2.0f, 2.0f, 6.5f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.5f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.5f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.5f, 2.0f, 12.0f, 2.0f);
        a.z(pathBuilderA, 7.87f, 6.81f, 0.88f, 0.88f);
        pathBuilderA.lineToRelative(0.88f, -0.88f);
        pathBuilderA.curveToRelative(0.29f, -0.29f, 0.77f, -0.29f, 1.06f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.29f, 0.29f, 0.29f, 0.77f, 0.0f, 1.06f);
        pathBuilderA.lineTo(9.81f, 8.75f);
        pathBuilderA.lineToRelative(0.88f, 0.88f);
        pathBuilderA.curveToRelative(0.29f, 0.29f, 0.29f, 0.77f, 0.0f, 1.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, 0.29f, -0.77f, 0.29f, -1.06f, 0.0f);
        pathBuilderA.lineTo(8.75f, 9.81f);
        pathBuilderA.lineToRelative(-0.88f, 0.88f);
        pathBuilderA.curveToRelative(-0.29f, 0.29f, -0.77f, 0.29f, -1.06f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.29f, -0.29f, -0.29f, -0.77f, 0.0f, -1.06f);
        pathBuilderA.lineToRelative(0.88f, -0.88f);
        pathBuilderA.lineTo(6.81f, 7.87f);
        pathBuilderA.curveToRelative(-0.29f, -0.29f, -0.29f, -0.77f, 0.0f, -1.06f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(7.1f, 6.51f, 7.57f, 6.51f, 7.87f, 6.81f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-2.2f, 0.0f, -4.2f, -0.9f, -5.7f, -2.3f);
        pathBuilderA.lineTo(17.7f, 6.3f);
        pathBuilderA.curveTo(19.1f, 7.8f, 20.0f, 9.8f, 20.0f, 12.0f);
        pathBuilderA.curveTo(20.0f, 16.4f, 16.4f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flaky = imageVectorBuild;
        return imageVectorBuild;
    }
}
