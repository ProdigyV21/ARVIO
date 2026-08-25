package androidx.compose.material.icons.filled;

import a0.a;
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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brunchDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BrunchDining", "Landroidx/compose/material/icons/Icons$Filled;", "getBrunchDining", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrunchDiningKt {
    private static ImageVector _brunchDining;

    public static final ImageVector getBrunchDining(Icons.Filled filled) {
        ImageVector imageVector = _brunchDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BrunchDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(18.0f, 8.0f, 2.0f, 4.0f, -2.0f);
        v.a.B(pathBuilderU, 8.0f, 15.51f, 22.0f, 2.49f);
        pathBuilderU.curveTo(2.22f, 22.0f, 2.0f, 21.78f, 2.0f, 21.5f);
        w.a.j(pathBuilderU, 20.0f, 14.0f, 1.5f);
        pathBuilderU.curveTo(16.0f, 21.78f, 15.78f, 22.0f, 15.51f, 22.0f);
        v.a.z(pathBuilderU, 18.0f, 15.89f, -0.4f, -0.42f);
        pathBuilderU.curveToRelative(-1.02f, -1.08f, -1.6f, -2.52f, -1.6f, -4.0f);
        w.a.j(pathBuilderU, 2.0f, 6.0f, 9.51f);
        pathBuilderU.curveToRelative(0.0f, 1.46f, -0.54f, 2.87f, -1.53f, 3.94f);
        b.n(pathBuilderU, 20.0f, 15.97f, 20.0f, 2.0f);
        v.a.t(pathBuilderU, 2.0f, -4.0f, 15.89f);
        c.m(pathBuilderU, 7.0f, 16.0f, -2.0f, 4.0f);
        pathBuilderU.verticalLineToRelative(2.0f);
        pathBuilderU.horizontalLineToRelative(4.5f);
        pathBuilderU.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderU.verticalLineToRelative(1.0f);
        pathBuilderU.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderU.horizontalLineToRelative(-13.0f);
        pathBuilderU.curveTo(2.22f, 18.0f, 2.0f, 17.78f, 2.0f, 17.5f);
        pathBuilderU.verticalLineToRelative(-1.0f);
        pathBuilderU.curveTo(2.0f, 16.22f, 2.22f, 16.0f, 2.5f, 16.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderU, 7.0f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brunchDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
