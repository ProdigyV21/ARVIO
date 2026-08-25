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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_voiceOverOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VoiceOverOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getVoiceOverOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VoiceOverOffKt {
    private static ImageVector _voiceOverOff;

    public static final ImageVector getVoiceOverOff(Icons.Rounded rounded) {
        ImageVector imageVector = _voiceOverOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.VoiceOverOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.72f, 6.41f);
        pathBuilderA.curveToRelative(-0.35f, 0.35f, -0.44f, 0.88f, -0.25f, 1.35f);
        pathBuilderA.curveToRelative(0.3f, 0.75f, 0.32f, 1.58f, 0.05f, 2.34f);
        pathBuilderA.curveToRelative(-0.16f, 0.46f, -0.06f, 0.98f, 0.29f, 1.32f);
        pathBuilderA.curveToRelative(0.6f, 0.6f, 1.66f, 0.47f, 2.02f, -0.31f);
        pathBuilderA.curveToRelative(0.64f, -1.39f, 0.6f, -2.99f, -0.12f, -4.41f);
        pathBuilderA.curveToRelative(-0.4f, -0.75f, -1.41f, -0.88f, -1.99f, -0.29f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.18f, 2.89f);
        pathBuilderA.curveToRelative(-0.4f, 0.4f, -0.46f, 1.02f, -0.13f, 1.48f);
        pathBuilderA.curveToRelative(1.93f, 2.68f, 1.95f, 6.25f, 0.09f, 9.07f);
        pathBuilderA.curveToRelative(-0.31f, 0.46f, -0.23f, 1.08f, 0.16f, 1.47f);
        pathBuilderA.curveToRelative(0.51f, 0.51f, 1.38f, 0.46f, 1.81f, -0.13f);
        pathBuilderA.curveToRelative(2.57f, -3.51f, 2.52f, -8.2f, -0.17f, -11.77f);
        pathBuilderA.curveToRelative(-0.43f, -0.56f, -1.26f, -0.62f, -1.76f, -0.12f);
        a.z(pathBuilderA, 9.43f, 5.04f, 3.53f, 3.53f);
        pathBuilderA.curveToRelative(-0.2f, -1.86f, -1.67f, -3.33f, -3.53f, -3.53f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.71f, 3.56f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(1.91f, 1.91f);
        pathBuilderA.curveToRelative(-0.56f, 0.89f, -0.79f, 2.01f, -0.47f, 3.2f);
        pathBuilderA.curveToRelative(0.36f, 1.33f, 1.44f, 2.4f, 2.77f, 2.77f);
        pathBuilderA.curveToRelative(1.19f, 0.33f, 2.31f, 0.09f, 3.2f, -0.47f);
        pathBuilderA.lineToRelative(4.4f, 4.4f);
        pathBuilderA.curveTo(13.74f, 15.6f, 10.78f, 15.0f, 9.0f, 15.0f);
        pathBuilderA.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, -0.11f, -0.7f, -0.29f, -1.02f);
        pathBuilderA.lineToRelative(2.31f, 2.31f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(5.12f, 3.56f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _voiceOverOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
