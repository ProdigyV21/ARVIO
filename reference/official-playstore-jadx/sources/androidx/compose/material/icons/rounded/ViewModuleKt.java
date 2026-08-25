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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewModule", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewModule", "Landroidx/compose/material/icons/Icons$Rounded;", "getViewModule", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewModuleKt {
    private static ImageVector _viewModule;

    public static final ImageVector getViewModule(Icons.Rounded rounded) {
        ImageVector imageVector = _viewModule;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ViewModule", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(14.67f, 6.0f, 4.5f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(-3.33f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderI.verticalLineTo(6.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(3.33f);
        pathBuilderI.curveTo(14.22f, 5.0f, 14.67f, 5.45f, 14.67f, 6.0f);
        w.a.r(pathBuilderI, 16.67f, 11.5f, 20.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineTo(6.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(-3.33f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(4.5f);
        pathBuilderI.curveTo(15.67f, 11.05f, 16.11f, 11.5f, 16.67f, 11.5f);
        d.k(pathBuilderI, 14.67f, 18.0f, -4.5f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(-3.33f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineTo(18.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(3.33f);
        pathBuilderI.curveTo(14.22f, 19.0f, 14.67f, 18.55f, 14.67f, 18.0f);
        w.a.u(pathBuilderI, 15.67f, 13.5f, 18.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineTo(20.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-4.5f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(-3.33f);
        pathBuilderI.curveTo(16.11f, 12.5f, 15.67f, 12.95f, 15.67f, 13.5f);
        w.a.r(pathBuilderI, 7.33f, 12.5f, 4.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineTo(18.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(3.33f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-4.5f);
        pathBuilderI.curveTo(8.33f, 12.95f, 7.89f, 12.5f, 7.33f, 12.5f);
        w.a.u(pathBuilderI, 8.33f, 10.5f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineTo(4.0f);
        pathBuilderI.curveTo(3.45f, 5.0f, 3.0f, 5.45f, 3.0f, 6.0f);
        pathBuilderI.verticalLineToRelative(4.5f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(3.33f);
        pathBuilderI.curveTo(7.89f, 11.5f, 8.33f, 11.05f, 8.33f, 10.5f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewModule = imageVectorBuild;
        return imageVectorBuild;
    }
}
