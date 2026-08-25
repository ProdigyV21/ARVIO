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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_runCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RunCircle", "Landroidx/compose/material/icons/Icons$Filled;", "getRunCircle", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RunCircleKt {
    private static ImageVector _runCircle;

    public static final ImageVector getRunCircle(Icons.Filled filled) {
        ImageVector imageVector = _runCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RunCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.5f, 6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveTo(12.5f, 6.45f, 12.95f, 6.0f, 13.5f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 12.0f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -2.01f, -0.54f, -2.91f, -1.76f);
        pathBuilderA.lineToRelative(-0.41f, 2.35f);
        b.n(pathBuilderA, 14.0f, 14.03f, 18.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.58f);
        pathBuilderA.lineToRelative(-1.11f, -1.21f);
        pathBuilderA.lineToRelative(-0.52f, 2.64f);
        pathBuilderA.lineTo(7.6f, 15.08f);
        pathBuilderA.lineToRelative(0.2f, -0.98f);
        pathBuilderA.lineToRelative(2.78f, 0.57f);
        pathBuilderA.lineToRelative(0.96f, -4.89f);
        c.f(pathBuilderA, 10.0f, 10.35f, 12.0f, 9.0f);
        pathBuilderA.verticalLineTo(9.65f);
        pathBuilderA.lineToRelative(3.28f, -1.21f);
        pathBuilderA.curveToRelative(0.49f, -0.18f, 1.03f, 0.06f, 1.26f, 0.53f);
        pathBuilderA.curveTo(14.37f, 10.67f, 15.59f, 11.0f, 16.0f, 11.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _runCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
