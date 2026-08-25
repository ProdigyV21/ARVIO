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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_troubleshoot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Troubleshoot", "Landroidx/compose/material/icons/Icons$Rounded;", "getTroubleshoot", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TroubleshootKt {
    private static ImageVector _troubleshoot;

    public static final ImageVector getTroubleshoot(Icons.Rounded rounded) {
        ImageVector imageVector = _troubleshoot;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Troubleshoot", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.29f, 19.88f, -3.98f, -3.98f);
        pathBuilderR.curveToRelative(1.3f, -1.67f, 1.96f, -3.85f, 1.58f, -6.2f);
        pathBuilderR.curveToRelative(-0.54f, -3.41f, -3.33f, -6.14f, -6.75f, -6.62f);
        pathBuilderR.curveTo(7.57f, 2.44f, 3.61f, 5.69f, 3.07f, 10.0f);
        pathBuilderR.horizontalLineToRelative(2.02f);
        pathBuilderR.curveToRelative(0.53f, -3.13f, 3.48f, -5.44f, 6.85f, -4.93f);
        pathBuilderR.curveToRelative(2.61f, 0.4f, 4.7f, 2.57f, 5.02f, 5.2f);
        pathBuilderR.curveTo(17.39f, 13.9f, 14.55f, 17.0f, 11.0f, 17.0f);
        pathBuilderR.curveToRelative(-2.42f, 0.0f, -4.5f, -1.44f, -5.45f, -3.5f);
        pathBuilderR.horizontalLineTo(3.4f);
        pathBuilderR.curveTo(4.45f, 16.69f, 7.46f, 19.0f, 11.0f, 19.0f);
        pathBuilderR.curveToRelative(1.85f, 0.0f, 3.55f, -0.63f, 4.9f, -1.69f);
        pathBuilderR.lineToRelative(3.98f, 3.98f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveTo(21.68f, 20.9f, 21.68f, 20.27f, 21.29f, 19.88f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(8.43f, 9.69f, 1.03f, 4.47f);
        pathBuilderR2.curveTo(9.57f, 14.65f, 10.01f, 15.0f, 10.51f, 15.0f);
        pathBuilderR2.horizontalLineToRelative(0.0f);
        pathBuilderR2.curveToRelative(0.46f, 0.0f, 0.87f, -0.3f, 1.02f, -0.74f);
        pathBuilderR2.lineToRelative(1.01f, -3.04f);
        pathBuilderR2.lineToRelative(0.69f, 1.66f);
        pathBuilderR2.curveToRelative(0.16f, 0.37f, 0.52f, 0.62f, 0.92f, 0.62f);
        pathBuilderR2.horizontalLineToRelative(0.58f);
        pathBuilderR2.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderR2.verticalLineToRelative(0.0f);
        pathBuilderR2.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderR2.horizontalLineTo(14.5f);
        pathBuilderR2.lineToRelative(-0.97f, -2.34f);
        pathBuilderR2.curveTo(13.36f, 9.26f, 12.97f, 9.0f, 12.53f, 9.0f);
        pathBuilderR2.horizontalLineToRelative(-0.05f);
        pathBuilderR2.curveToRelative(-0.46f, 0.0f, -0.87f, 0.3f, -1.02f, 0.74f);
        pathBuilderR2.lineToRelative(-0.88f, 2.63f);
        pathBuilderR2.lineTo(9.54f, 7.83f);
        pathBuilderR2.curveTo(9.43f, 7.35f, 8.99f, 7.0f, 8.49f, 7.0f);
        pathBuilderR2.horizontalLineToRelative(0.0f);
        pathBuilderR2.curveTo(8.02f, 7.0f, 7.6f, 7.31f, 7.46f, 7.76f);
        pathBuilderR2.lineTo(6.45f, 11.0f);
        pathBuilderR2.horizontalLineToRelative(-4.7f);
        pathBuilderR2.curveTo(1.34f, 11.0f, 1.0f, 11.34f, 1.0f, 11.75f);
        pathBuilderR2.verticalLineToRelative(0.0f);
        pathBuilderR2.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderR2.horizontalLineToRelative(5.07f);
        pathBuilderR2.curveToRelative(0.44f, 0.0f, 0.82f, -0.28f, 0.95f, -0.7f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR2, 8.43f, 9.69f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _troubleshoot = imageVectorBuild;
        return imageVectorBuild;
    }
}
