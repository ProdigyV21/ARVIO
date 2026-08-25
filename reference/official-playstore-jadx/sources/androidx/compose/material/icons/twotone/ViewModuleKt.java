package androidx.compose.material.icons.twotone;

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
import v.c;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewModule", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewModule", "Landroidx/compose/material/icons/Icons$TwoTone;", "getViewModule", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewModuleKt {
    private static ImageVector _viewModule;

    public static final ImageVector getViewModule(Icons.TwoTone twoTone) {
        ImageVector imageVector = _viewModule;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ViewModule", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(19.0f, 11.0f, -3.33f, 7.0f, 19.0f);
        v.a.D(pathBuilderS, 11.0f, 13.67f, 11.0f, -3.33f);
        f.k(pathBuilderS, 7.0f, 3.33f, 11.0f);
        c.u(pathBuilderS, 8.33f, 7.0f, 4.0f, 5.0f);
        c.n(pathBuilderS, 7.0f, 8.33f, 5.0f, 17.0f);
        b.f(pathBuilderS, -4.0f, 3.33f, 4.0f, 5.0f);
        c.m(pathBuilderS, 10.33f, 17.0f, -4.0f, 3.33f);
        e.y(pathBuilderS, 4.0f, 10.33f, 15.67f, 17.0f);
        pathBuilderS.verticalLineToRelative(-4.0f);
        pathBuilderS.horizontalLineTo(19.0f);
        pathBuilderS.verticalLineToRelative(4.0f);
        pathBuilderS.horizontalLineTo(15.67f);
        pathBuilderS.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = w.a.q(3.0f, 5.0f, 14.0f, 18.0f, 5.0f);
        w.a.v(pathBuilderQ, 3.0f, 19.0f, 11.0f, -3.33f);
        v.a.v(pathBuilderQ, 7.0f, 19.0f, 11.0f);
        e.m(pathBuilderQ, 13.67f, 11.0f, -3.33f, 7.0f);
        c.D(pathBuilderQ, 3.33f, 11.0f, 8.33f, 7.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.horizontalLineTo(5.0f);
        pathBuilderQ.verticalLineTo(7.0f);
        pathBuilderQ.horizontalLineTo(8.33f);
        pathBuilderQ.close();
        c.m(pathBuilderQ, 5.0f, 17.0f, -4.0f, 3.33f);
        e.y(pathBuilderQ, 4.0f, 5.0f, 10.33f, 17.0f);
        b.f(pathBuilderQ, -4.0f, 3.33f, 4.0f, 10.33f);
        c.u(pathBuilderQ, 15.67f, 17.0f, -4.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderQ, 4.0f, 15.67f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewModule = imageVectorBuild;
        return imageVectorBuild;
    }
}
