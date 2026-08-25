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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_viewSidebar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewSidebar", "Landroidx/compose/material/icons/Icons$Rounded;", "getViewSidebar$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getViewSidebar", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewSidebarKt {
    private static ImageVector _viewSidebar;

    public static final ImageVector getViewSidebar(Icons.Rounded rounded) {
        ImageVector imageVector = _viewSidebar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ViewSidebar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(15.0f, 20.0f, 3.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveTo(16.0f, 19.55f, 15.55f, 20.0f, 15.0f, 20.0f);
        a.g(pathBuilderQ, 19.0f, 8.0f, 2.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveTo(18.0f, 7.55f, 18.45f, 8.0f, 19.0f, 8.0f);
        a.g(pathBuilderQ, 19.0f, 20.0f, 2.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveTo(18.0f, 19.55f, 18.45f, 20.0f, 19.0f, 20.0f);
        a.g(pathBuilderQ, 19.0f, 14.0f, 2.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(2.0f);
        pathBuilderQ.curveTo(18.0f, 13.55f, 18.45f, 14.0f, 19.0f, 14.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewSidebar = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getViewSidebar$annotations(Icons.Rounded rounded) {
    }
}
