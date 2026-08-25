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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenRotation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenRotation", "Landroidx/compose/material/icons/Icons$Filled;", "getScreenRotation", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenRotationKt {
    private static ImageVector _screenRotation;

    public static final ImageVector getScreenRotation(Icons.Filled filled) {
        ImageVector imageVector = _screenRotation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ScreenRotation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.48f, 2.52f);
        pathBuilderA.curveToRelative(3.27f, 1.55f, 5.61f, 4.72f, 5.97f, 8.48f);
        pathBuilderA.horizontalLineToRelative(1.5f);
        pathBuilderA.curveTo(23.44f, 4.84f, 18.29f, 0.0f, 12.0f, 0.0f);
        pathBuilderA.lineToRelative(-0.66f, 0.03f);
        b.D(pathBuilderA, 3.81f, 3.81f, 1.33f, -1.32f);
        pathBuilderA.moveTo(10.23f, 1.75f);
        pathBuilderA.curveToRelative(-0.59f, -0.59f, -1.54f, -0.59f, -2.12f, 0.0f);
        pathBuilderA.lineTo(1.75f, 8.11f);
        pathBuilderA.curveToRelative(-0.59f, 0.59f, -0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderA.lineToRelative(12.02f, 12.02f);
        pathBuilderA.curveToRelative(0.59f, 0.59f, 1.54f, 0.59f, 2.12f, 0.0f);
        pathBuilderA.lineToRelative(6.36f, -6.36f);
        pathBuilderA.curveToRelative(0.59f, -0.59f, 0.59f, -1.54f, 0.0f, -2.12f);
        c.z(pathBuilderA, 10.23f, 1.75f, 14.83f, 21.19f);
        pathBuilderA.lineTo(2.81f, 9.17f);
        pathBuilderA.lineToRelative(6.36f, -6.36f);
        b.D(pathBuilderA, 12.02f, 12.02f, -6.36f, 6.36f);
        pathBuilderA.moveTo(7.52f, 21.48f);
        pathBuilderA.curveTo(4.25f, 19.94f, 1.91f, 16.76f, 1.55f, 13.0f);
        pathBuilderA.lineTo(0.05f, 13.0f);
        pathBuilderA.curveTo(0.56f, 19.16f, 5.71f, 24.0f, 12.0f, 24.0f);
        pathBuilderA.lineToRelative(0.66f, -0.03f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -3.81f, -3.81f, -1.33f, 1.32f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenRotation = imageVectorBuild;
        return imageVectorBuild;
    }
}
