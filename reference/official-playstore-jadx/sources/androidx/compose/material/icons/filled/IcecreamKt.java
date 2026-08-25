package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_icecream", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Icecream", "Landroidx/compose/material/icons/Icons$Filled;", "getIcecream", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IcecreamKt {
    private static ImageVector _icecream;

    public static final ImageVector getIcecream(Icons.Filled filled) {
        ImageVector imageVector = _icecream;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Icecream", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.79f, 12.4f, 3.26f, 6.22f);
        pathBuilderR.lineToRelative(3.17f, -6.21f);
        pathBuilderR.curveToRelative(-0.11f, -0.08f, -0.21f, -0.16f, -0.3f, -0.25f);
        pathBuilderR.curveTo(14.08f, 12.69f, 13.07f, 13.0f, 12.0f, 13.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.08f, -0.31f, -2.92f, -0.84f);
        pathBuilderR.curveTo(8.99f, 12.25f, 8.89f, 12.33f, 8.79f, 12.4f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.83f, 12.99f);
        pathBuilderR.curveTo(5.25f, 12.9f, 4.0f, 11.6f, 4.0f, 10.0f);
        pathBuilderR.curveToRelative(0.0f, -1.49f, 1.09f, -2.73f, 2.52f, -2.96f);
        pathBuilderR.curveTo(6.75f, 4.22f, 9.12f, 2.0f, 12.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(5.25f, 2.22f, 5.48f, 5.04f);
        pathBuilderR.curveTo(18.91f, 7.27f, 20.0f, 8.51f, 20.0f, 10.0f);
        pathBuilderR.curveToRelative(0.0f, 1.59f, -1.24f, 2.9f, -2.81f, 2.99f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 12.07f, 23.0f, 6.83f, 12.99f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _icecream = imageVectorBuild;
        return imageVectorBuild;
    }
}
