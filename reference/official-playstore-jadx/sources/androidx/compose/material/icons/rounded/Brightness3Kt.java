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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightness3", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Brightness3", "Landroidx/compose/material/icons/Icons$Rounded;", "getBrightness3", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Brightness3Kt {
    private static ImageVector _brightness3;

    public static final ImageVector getBrightness3(Icons.Rounded rounded) {
        ImageVector imageVector = _brightness3;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Brightness3", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.93f, 2.0f);
        pathBuilderA.curveTo(8.88f, 2.0f, 8.84f, 2.0f, 8.79f, 2.0f);
        pathBuilderA.curveTo(7.96f, 2.02f, 7.7f, 3.12f, 8.4f, 3.56f);
        pathBuilderA.curveToRelative(2.78f, 1.77f, 4.63f, 4.89f, 4.63f, 8.44f);
        pathBuilderA.curveToRelative(0.0f, 3.55f, -1.84f, 6.66f, -4.62f, 8.43f);
        pathBuilderA.curveTo(7.7f, 20.89f, 7.98f, 21.98f, 8.82f, 22.0f);
        pathBuilderA.curveToRelative(0.07f, 0.0f, 0.14f, 0.0f, 0.21f, 0.0f);
        pathBuilderA.curveToRelative(6.05f, 0.0f, 10.86f, -5.39f, 9.87f, -11.63f);
        pathBuilderA.curveTo(18.14f, 5.53f, 13.83f, 1.95f, 8.93f, 2.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightness3 = imageVectorBuild;
        return imageVectorBuild;
    }
}
