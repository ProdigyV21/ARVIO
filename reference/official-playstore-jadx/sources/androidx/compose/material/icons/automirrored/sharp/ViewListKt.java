package androidx.compose.material.icons.automirrored.sharp;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewList", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewList", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getViewList", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewListKt {
    private static ImageVector _viewList;

    public static final ImageVector getViewList(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _viewList;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.ViewList", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(3.0f, 14.0f, 4.0f, -4.0f, 3.0f);
        a.D(pathBuilderH, 14.0f, 3.0f, 19.0f, 4.0f);
        d.u(pathBuilderH, -4.0f, 3.0f, 19.0f);
        e.m(pathBuilderH, 3.0f, 9.0f, 4.0f, 5.0f);
        c.l(pathBuilderH, 3.0f, 9.0f, 8.0f, 14.0f);
        d.z(pathBuilderH, 13.0f, -4.0f, 8.0f, 14.0f);
        a.C(pathBuilderH, 8.0f, 19.0f, 13.0f, -4.0f);
        c.l(pathBuilderH, 8.0f, 19.0f, 8.0f, 5.0f);
        pathBuilderH.verticalLineToRelative(4.0f);
        pathBuilderH.horizontalLineToRelative(13.0f);
        pathBuilderH.verticalLineTo(5.0f);
        pathBuilderH.horizontalLineTo(8.0f);
        pathBuilderH.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderH.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewList = imageVectorBuild;
        return imageVectorBuild;
    }
}
