package androidx.compose.material.icons.automirrored.twotone;

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
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewSidebar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewSidebar", "Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;", "getViewSidebar", "(Landroidx/compose/material/icons/Icons$AutoMirrored$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewSidebarKt {
    private static ImageVector _viewSidebar;

    public static final ImageVector getViewSidebar(Icons.AutoMirrored.TwoTone twoTone) {
        ImageVector imageVector = _viewSidebar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.TwoTone.ViewSidebar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(20.0f, 8.67f, -2.5f, 6.0f, 20.0f);
        v.a.B(pathBuilderS, 8.67f, 17.5f, 10.67f, 20.0f);
        v.a.t(pathBuilderS, 2.67f, -2.5f, 10.67f);
        v.a.C(pathBuilderS, 4.0f, 6.0f, 11.5f, 12.0f);
        c.l(pathBuilderS, 4.0f, 6.0f, 17.5f, 18.0f);
        pathBuilderS.verticalLineToRelative(-2.67f);
        pathBuilderS.horizontalLineTo(20.0f);
        pathBuilderS.verticalLineTo(18.0f);
        pathBuilderS.horizontalLineTo(17.5f);
        pathBuilderS.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = w.a.q(2.0f, 4.0f, 16.0f, 20.0f, 4.0f);
        w.a.v(pathBuilderQ, 2.0f, 20.0f, 8.67f, -2.5f);
        v.a.v(pathBuilderQ, 6.0f, 20.0f, 8.67f);
        v.a.A(pathBuilderQ, 17.5f, 10.67f, 20.0f, 2.67f);
        c.D(pathBuilderQ, -2.5f, 10.67f, 4.0f, 6.0f);
        d.z(pathBuilderQ, 11.5f, 12.0f, 4.0f, 6.0f);
        c.u(pathBuilderQ, 17.5f, 18.0f, -2.67f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderQ, 18.0f, 17.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewSidebar = imageVectorBuild;
        return imageVectorBuild;
    }
}
