package androidx.compose.material.icons.outlined;

import a0.b;
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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__30fps", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_30fps", "Landroidx/compose/material/icons/Icons$Outlined;", "get_30fps", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _30fpsKt {
    private static ImageVector __30fps;

    public static final ImageVector get_30fps(Icons.Outlined outlined) {
        ImageVector imageVector = __30fps;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined._30fps", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(2.0f, 5.0f, 3.0f, 6.0f, 2.5f);
        a0.a.e(pathBuilderU, 3.0f, 3.0f, 5.0f, 16.0f);
        v.a.n(pathBuilderU, 2.0f, 3.0f, 6.0f);
        pathBuilderU.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderU.verticalLineToRelative(-1.9f);
        pathBuilderU.curveToRelative(0.0f, -1.16f, -0.94f, -2.1f, -2.1f, -2.1f);
        pathBuilderU.curveToRelative(1.16f, 0.0f, 2.1f, -0.94f, 2.1f, -2.1f);
        pathBuilderU.verticalLineTo(8.0f);
        pathBuilderU.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        f.l(pathBuilderU, 2.0f, 19.0f, 8.0f, 8.0f);
        b.w(pathBuilderU, -4.0f, 8.0f, 19.0f);
        pathBuilderU.moveTo(19.0f, 5.0f);
        pathBuilderU.horizontalLineToRelative(-4.0f);
        pathBuilderU.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderU.verticalLineToRelative(8.0f);
        pathBuilderU.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderU.horizontalLineToRelative(4.0f);
        pathBuilderU.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderU.verticalLineTo(8.0f);
        pathBuilderU.curveTo(22.0f, 6.34f, 20.66f, 5.0f, 19.0f, 5.0f);
        pathBuilderU.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __30fps = imageVectorBuild;
        return imageVectorBuild;
    }
}
