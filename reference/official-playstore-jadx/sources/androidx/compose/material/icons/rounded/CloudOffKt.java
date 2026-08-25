package androidx.compose.material.icons.rounded;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cloudOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CloudOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getCloudOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloudOffKt {
    private static ImageVector _cloudOff;

    public static final ImageVector getCloudOff(Icons.Rounded rounded) {
        ImageVector imageVector = _cloudOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CloudOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(24.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, -2.64f, -2.05f, -4.78f, -4.65f, -4.96f);
        pathBuilderA.curveTo(18.67f, 6.59f, 15.64f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.33f, 0.0f, -2.57f, 0.36f, -3.65f, 0.97f);
        pathBuilderA.lineToRelative(1.49f, 1.49f);
        pathBuilderA.curveTo(10.51f, 6.17f, 11.23f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(3.04f, 0.0f, 5.5f, 2.46f, 5.5f, 5.5f);
        pathBuilderA.verticalLineToRelative(0.5f);
        pathBuilderA.horizontalLineTo(19.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.99f, -0.48f, 1.85f, -1.21f, 2.4f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.curveToRelative(1.09f, -0.92f, 1.8f, -2.27f, 1.8f, -3.81f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.71f, 4.56f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(2.06f, 2.06f);
        pathBuilderA.horizontalLineToRelative(-0.42f);
        pathBuilderA.curveToRelative(-3.28f, 0.35f, -5.76f, 3.34f, -5.29f, 6.79f);
        pathBuilderA.curveTo(0.46f, 17.84f, 3.19f, 20.0f, 6.22f, 20.0f);
        pathBuilderA.horizontalLineToRelative(11.51f);
        pathBuilderA.lineToRelative(1.29f, 1.29f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(5.12f, 4.56f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 18.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.r(pathBuilderA, 1.73f, 8.0f, 8.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cloudOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
