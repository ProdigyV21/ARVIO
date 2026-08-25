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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenSearchDesktop", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenSearchDesktop", "Landroidx/compose/material/icons/Icons$Filled;", "getScreenSearchDesktop", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenSearchDesktopKt {
    private static ImageVector _screenSearchDesktop;

    public static final ImageVector getScreenSearchDesktop(Icons.Filled filled) {
        ImageVector imageVector = _screenSearchDesktop;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ScreenSearchDesktop", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 18.0f);
        pathBuilderA.curveTo(21.1f, 18.0f, 21.99f, 17.1f, 21.99f, 16.0f);
        pathBuilderA.lineTo(22.0f, 6.0f);
        pathBuilderA.curveTo(22.0f, 4.89f, 21.1f, 4.0f, 20.0f, 4.0f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        pathBuilderA.curveTo(2.89f, 4.0f, 2.0f, 4.89f, 2.0f, 6.0f);
        pathBuilderA.lineTo(2.0f, 16.0f);
        pathBuilderA.curveTo(2.0f, 17.1f, 2.89f, 18.0f, 4.0f, 18.0f);
        pathBuilderA.lineTo(0.0f, 18.0f);
        pathBuilderA.lineTo(0.0f, 20.0f);
        pathBuilderA.lineTo(24.0f, 20.0f);
        b.y(pathBuilderA, 24.0f, 18.0f, 20.0f, 18.0f);
        pathBuilderA.moveTo(4.0f, 16.0f);
        pathBuilderA.lineTo(4.0f, 6.0f);
        pathBuilderA.lineTo(20.0f, 6.0f);
        pathBuilderA.lineTo(20.0f, 16.0f);
        b.y(pathBuilderA, 20.0f, 16.01f, 4.0f, 16.0f);
        pathBuilderA.moveTo(9.097f, 9.953f);
        pathBuilderA.curveTo(9.097f, 8.926f, 9.933f, 8.089f, 10.961f, 8.089f);
        pathBuilderA.curveTo(11.988f, 8.089f, 12.825f, 8.926f, 12.825f, 9.953f);
        pathBuilderA.curveTo(12.825f, 10.98f, 11.988f, 11.817f, 10.961f, 11.817f);
        pathBuilderA.curveTo(9.933f, 11.817f, 9.097f, 10.98f, 9.097f, 9.953f);
        a.p(pathBuilderA, 16.129f, 14.189f, 13.647f, 11.707f);
        pathBuilderA.curveTo(13.978f, 11.202f, 14.174f, 10.6f, 14.174f, 9.953f);
        pathBuilderA.curveTo(14.174f, 8.181f, 12.733f, 6.74f, 10.961f, 6.74f);
        pathBuilderA.curveTo(9.189f, 6.74f, 7.747f, 8.181f, 7.747f, 9.953f);
        pathBuilderA.curveTo(7.747f, 11.725f, 9.189f, 13.167f, 10.961f, 13.167f);
        pathBuilderA.curveTo(11.597f, 13.167f, 12.186f, 12.975f, 12.685f, 12.656f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 15.174f, 15.144f, 16.129f, 14.189f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenSearchDesktop = imageVectorBuild;
        return imageVectorBuild;
    }
}
