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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cleanHands", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CleanHands", "Landroidx/compose/material/icons/Icons$Rounded;", "getCleanHands", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CleanHandsKt {
    private static ImageVector _cleanHands;

    public static final ImageVector getCleanHands(Icons.Rounded rounded) {
        ImageVector imageVector = _cleanHands;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CleanHands", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.99f, 7.0f, 1.37f, -0.63f);
        pathBuilderR.lineTo(16.99f, 5.0f);
        pathBuilderR.lineToRelative(0.63f, 1.37f);
        pathBuilderR.lineTo(18.99f, 7.0f);
        pathBuilderR.lineToRelative(-1.37f, 0.63f);
        pathBuilderR.lineTo(16.99f, 9.0f);
        d.C(pathBuilderR, -0.63f, -1.37f, 14.99f, 7.0f);
        pathBuilderR.moveTo(20.0f, 14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.curveToRelative(0.0f, -0.78f, -0.99f, -2.44f, -1.58f, -3.36f);
        pathBuilderR.curveToRelative(-0.2f, -0.31f, -0.64f, -0.31f, -0.84f, 0.0f);
        pathBuilderR.curveTo(18.99f, 9.56f, 18.0f, 11.22f, 18.0f, 12.0f);
        pathBuilderR.curveTo(18.0f, 13.1f, 18.9f, 14.0f, 20.0f, 14.0f);
        a.p(pathBuilderR, 9.24f, 9.5f, 15.0f, 11.65f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.curveToRelative(0.0f, -2.42f, -1.72f, -4.44f, -4.0f, -4.9f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.35f, 0.0f, 0.68f, 0.06f, 1.0f, 0.18f);
        pathBuilderR.curveToRelative(0.37f, 0.13f, 0.78f, 0.05f, 1.05f, -0.22f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.51f, -0.51f, 0.34f, -1.39f, -0.33f, -1.64f);
        pathBuilderR.curveTo(14.19f, 2.11f, 13.61f, 2.0f, 13.0f, 2.0f);
        pathBuilderR.horizontalLineTo(8.5f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineTo(9.0f);
        pathBuilderR.verticalLineToRelative(2.11f);
        pathBuilderR.curveTo(7.22f, 6.48f, 5.8f, 7.79f, 5.25f, 9.5f);
        a.x(pathBuilderR, 9.24f, 3.0f, 11.0f);
        pathBuilderR.lineTo(3.0f, 11.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-7.0f);
        pathBuilderR.curveTo(5.0f, 11.9f, 4.1f, 11.0f, 3.0f, 11.0f);
        a.g(pathBuilderR, 19.99f, 17.0f, -6.83f);
        pathBuilderR.curveToRelative(-0.11f, 0.0f, -0.22f, -0.02f, -0.33f, -0.06f);
        pathBuilderR.lineToRelative(-1.47f, -0.51f);
        pathBuilderR.curveToRelative(-0.26f, -0.09f, -0.39f, -0.37f, -0.3f, -0.63f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.09f, -0.26f, 0.38f, -0.4f, 0.64f, -0.3f);
        pathBuilderR.lineToRelative(1.12f, 0.43f);
        pathBuilderR.curveToRelative(0.11f, 0.04f, 0.24f, 0.07f, 0.36f, 0.07f);
        pathBuilderR.horizontalLineToRelative(2.63f);
        pathBuilderR.curveToRelative(0.65f, 0.0f, 1.18f, -0.53f, 1.18f, -1.18f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.49f, -0.31f, -0.93f, -0.77f, -1.11f);
        pathBuilderR.lineTo(9.3f, 11.13f);
        pathBuilderR.curveTo(9.08f, 11.04f, 8.84f, 11.0f, 8.6f, 11.0f);
        a0.a.D(pathBuilderR, 7.0f, 9.02f, 6.37f, 1.81f);
        pathBuilderR.curveToRelative(0.41f, 0.12f, 0.85f, 0.1f, 1.25f, -0.05f);
        pathBuilderR.lineTo(22.0f, 19.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveTo(22.0f, 17.89f, 21.1f, 17.0f, 19.99f, 17.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cleanHands = imageVectorBuild;
        return imageVectorBuild;
    }
}
