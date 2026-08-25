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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nordicWalking", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NordicWalking", "Landroidx/compose/material/icons/Icons$Rounded;", "getNordicWalking", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NordicWalkingKt {
    private static ImageVector _nordicWalking;

    public static final ImageVector getNordicWalking(Icons.Rounded rounded) {
        ImageVector imageVector = _nordicWalking;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NordicWalking", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.25f, 23.0f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        a.s(pathBuilderA, 14.0f, 19.0f, 8.25f);
        pathBuilderA.curveTo(19.0f, 22.66f, 18.66f, 23.0f, 18.25f, 23.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.93f, 23.0f);
        pathBuilderA.curveToRelative(0.35f, 0.0f, 0.66f, -0.24f, 0.73f, -0.59f);
        pathBuilderA.lineTo(7.53f, 14.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.lineToRelative(-1.8f, 8.09f);
        pathBuilderA.curveTo(4.1f, 22.56f, 4.45f, 23.0f, 4.93f, 23.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(12.4f, 5.5f, 13.5f, 5.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 23.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-5.64f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.22f, -1.07f, -0.62f, -1.45f);
        pathBuilderA.lineTo(12.9f, 13.5f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.07f, 1.24f, 2.62f, 2.13f, 4.36f, 2.41f);
        pathBuilderA.curveToRelative(0.6f, 0.1f, 1.14f, -0.38f, 1.14f, -0.99f);
        pathBuilderA.curveToRelative(0.0f, -0.49f, -0.35f, -0.91f, -0.83f, -0.98f);
        pathBuilderA.curveToRelative(-1.53f, -0.24f, -2.79f, -1.14f, -3.47f, -2.33f);
        pathBuilderA.lineToRelative(-1.0f, -1.6f);
        pathBuilderA.curveToRelative(-0.56f, -0.89f, -1.68f, -1.25f, -2.66f, -0.84f);
        pathBuilderA.lineTo(7.22f, 7.78f);
        pathBuilderA.curveTo(6.48f, 8.1f, 6.0f, 8.82f, 6.0f, 9.62f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(9.6f);
        pathBuilderA.lineToRelative(1.8f, -0.7f);
        pathBuilderA.lineTo(7.25f, 21.76f);
        pathBuilderA.curveTo(7.12f, 22.4f, 7.61f, 23.0f, 8.27f, 23.0f);
        pathBuilderA.curveToRelative(0.49f, 0.0f, 0.91f, -0.34f, 1.02f, -0.81f);
        pathBuilderA.lineTo(10.9f, 15.0f);
        pathBuilderA.lineToRelative(2.1f, 2.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.curveTo(13.0f, 22.55f, 13.45f, 23.0f, 14.0f, 23.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nordicWalking = imageVectorBuild;
        return imageVectorBuild;
    }
}
