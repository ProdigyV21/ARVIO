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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_micOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MicOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getMicOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicOffKt {
    private static ImageVector _micOff;

    public static final ImageVector getMicOff(Icons.Rounded rounded) {
        ImageVector imageVector = _micOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MicOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 10.6f, 15.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderJ.curveToRelative(-1.54f, 0.0f, -2.79f, 1.16f, -2.96f, 2.65f);
        c.z(pathBuilderJ, 15.0f, 10.6f, 18.08f, 11.0f);
        pathBuilderJ.curveToRelative(-0.41f, 0.0f, -0.77f, 0.3f, -0.83f, 0.71f);
        pathBuilderJ.curveToRelative(-0.05f, 0.32f, -0.12f, 0.64f, -0.22f, 0.93f);
        pathBuilderJ.lineToRelative(1.27f, 1.27f);
        pathBuilderJ.curveToRelative(0.3f, -0.6f, 0.52f, -1.25f, 0.63f, -1.94f);
        pathBuilderJ.curveToRelative(0.07f, -0.51f, -0.33f, -0.97f, -0.85f, -0.97f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(3.71f, 3.56f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineTo(9.0f, 10.27f);
        pathBuilderJ.verticalLineToRelative(0.43f);
        pathBuilderJ.curveToRelative(0.0f, 1.19f, 0.6f, 2.32f, 1.63f, 2.91f);
        pathBuilderJ.curveToRelative(0.75f, 0.43f, 1.41f, 0.44f, 2.02f, 0.31f);
        pathBuilderJ.lineToRelative(1.66f, 1.66f);
        pathBuilderJ.curveToRelative(-0.71f, 0.33f, -1.5f, 0.52f, -2.31f, 0.52f);
        pathBuilderJ.curveToRelative(-2.54f, 0.0f, -4.88f, -1.77f, -5.25f, -4.39f);
        pathBuilderJ.curveToRelative(-0.06f, -0.41f, -0.42f, -0.71f, -0.83f, -0.71f);
        pathBuilderJ.curveToRelative(-0.52f, 0.0f, -0.92f, 0.46f, -0.85f, 0.97f);
        pathBuilderJ.curveToRelative(0.46f, 2.96f, 2.96f, 5.3f, 5.93f, 5.75f);
        pathBuilderJ.lineTo(11.0f, 20.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-2.28f);
        pathBuilderJ.curveToRelative(0.91f, -0.13f, 1.77f, -0.45f, 2.55f, -0.9f);
        pathBuilderJ.lineToRelative(3.49f, 3.49f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(5.12f, 3.56f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _micOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
