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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenRotation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenRotation", "Landroidx/compose/material/icons/Icons$Rounded;", "getScreenRotation", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenRotationKt {
    private static ImageVector _screenRotation;

    public static final ImageVector getScreenRotation(Icons.Rounded rounded) {
        ImageVector imageVector = _screenRotation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ScreenRotation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.23f, 1.75f);
        pathBuilderA.curveToRelative(-0.59f, -0.59f, -1.54f, -0.59f, -2.12f, 0.0f);
        pathBuilderA.lineTo(1.75f, 8.11f);
        pathBuilderA.curveToRelative(-0.59f, 0.59f, -0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderA.lineToRelative(12.02f, 12.02f);
        pathBuilderA.curveToRelative(0.59f, 0.59f, 1.54f, 0.59f, 2.12f, 0.0f);
        pathBuilderA.lineToRelative(6.36f, -6.36f);
        pathBuilderA.curveToRelative(0.59f, -0.59f, 0.59f, -1.54f, 0.0f, -2.12f);
        c.z(pathBuilderA, 10.23f, 1.75f, 14.12f, 20.48f);
        pathBuilderA.lineTo(3.52f, 9.88f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(4.95f, -4.95f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(10.61f, 10.61f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(-4.95f, 4.95f);
        pathBuilderA.curveToRelative(-0.39f, 0.38f, -1.03f, 0.38f, -1.42f, -0.01f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.61f, 1.4f);
        pathBuilderA.curveTo(16.04f, 0.57f, 14.06f, -0.03f, 11.81f, 0.02f);
        pathBuilderA.curveToRelative(-0.18f, 0.0f, -0.26f, 0.22f, -0.14f, 0.35f);
        pathBuilderA.lineToRelative(3.48f, 3.48f);
        pathBuilderA.lineToRelative(1.33f, -1.33f);
        pathBuilderA.curveToRelative(3.09f, 1.46f, 5.34f, 4.37f, 5.89f, 7.86f);
        pathBuilderA.curveToRelative(0.06f, 0.41f, 0.44f, 0.69f, 0.86f, 0.62f);
        pathBuilderA.curveToRelative(0.41f, -0.06f, 0.69f, -0.45f, 0.62f, -0.86f);
        pathBuilderA.curveToRelative(-0.6f, -3.8f, -2.96f, -7.0f, -6.24f, -8.74f);
        a.z(pathBuilderA, 8.85f, 20.16f, -1.33f, 1.33f);
        pathBuilderA.curveToRelative(-3.09f, -1.46f, -5.34f, -4.37f, -5.89f, -7.86f);
        pathBuilderA.curveToRelative(-0.06f, -0.41f, -0.44f, -0.69f, -0.86f, -0.62f);
        pathBuilderA.curveToRelative(-0.41f, 0.06f, -0.69f, 0.45f, -0.62f, 0.86f);
        pathBuilderA.curveToRelative(0.6f, 3.81f, 2.96f, 7.01f, 6.24f, 8.75f);
        pathBuilderA.curveToRelative(1.57f, 0.83f, 3.55f, 1.43f, 5.8f, 1.38f);
        pathBuilderA.curveToRelative(0.18f, 0.0f, 0.26f, -0.22f, 0.14f, -0.35f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -3.48f, -3.49f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenRotation = imageVectorBuild;
        return imageVectorBuild;
    }
}
