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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hiking", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hiking", "Landroidx/compose/material/icons/Icons$Rounded;", "getHiking", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HikingKt {
    private static ImageVector _hiking;

    public static final ImageVector getHiking(Icons.Rounded rounded) {
        ImageVector imageVector = _hiking;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Hiking", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(12.4f, 5.5f, 13.5f, 5.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.25f, 9.0f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderA.lineToRelative(0.0f, 1.03f);
        pathBuilderA.curveToRelative(-1.23f, -0.37f, -2.22f, -1.17f, -2.8f, -2.18f);
        pathBuilderA.lineToRelative(-1.0f, -1.6f);
        pathBuilderA.curveToRelative(-0.41f, -0.65f, -1.11f, -1.0f, -1.84f, -1.0f);
        pathBuilderA.curveToRelative(-0.78f, 0.0f, -1.59f, 0.5f, -1.78f, 1.44f);
        pathBuilderA.lineTo(7.25f, 21.76f);
        pathBuilderA.curveTo(7.12f, 22.4f, 7.61f, 23.0f, 8.27f, 23.0f);
        pathBuilderA.curveToRelative(0.49f, 0.0f, 0.91f, -0.34f, 1.02f, -0.81f);
        pathBuilderA.lineTo(10.9f, 15.0f);
        pathBuilderA.lineToRelative(2.1f, 2.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-5.64f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.22f, -1.07f, -0.62f, -1.45f);
        pathBuilderA.lineTo(12.9f, 13.5f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.0f, 1.15f, 2.41f, 2.01f, 4.0f, 2.34f);
        pathBuilderA.verticalLineToRelative(9.41f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderA.reflectiveCurveTo(19.0f, 22.66f, 19.0f, 22.25f);
        pathBuilderA.verticalLineTo(9.75f);
        pathBuilderA.curveTo(19.0f, 9.34f, 18.66f, 9.0f, 18.25f, 9.0f);
        a.z(pathBuilderA, 7.43f, 13.13f, -2.12f, -0.41f);
        pathBuilderA.curveToRelative(-0.54f, -0.11f, -0.9f, -0.63f, -0.79f, -1.17f);
        pathBuilderA.lineToRelative(0.76f, -3.93f);
        pathBuilderA.curveToRelative(0.21f, -1.08f, 1.26f, -1.79f, 2.34f, -1.58f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.16f, 0.23f, 7.43f, 13.13f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hiking = imageVectorBuild;
        return imageVectorBuild;
    }
}
