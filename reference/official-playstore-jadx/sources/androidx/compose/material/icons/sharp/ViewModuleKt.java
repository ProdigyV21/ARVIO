package androidx.compose.material.icons.sharp;

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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewModule", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewModule", "Landroidx/compose/material/icons/Icons$Sharp;", "getViewModule", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewModuleKt {
    private static ImageVector _viewModule;

    public static final ImageVector getViewModule(Icons.Sharp sharp) {
        ImageVector imageVector = _viewModule;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ViewModule", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = f.b(14.67f, 5.0f, 6.5f, 9.33f, 5.0f);
        a.s(pathBuilderB, 14.67f, 15.67f, 11.5f, 21.0f);
        f.k(pathBuilderB, 5.0f, -5.33f, 11.5f);
        c.u(pathBuilderB, 14.67f, 19.0f, -6.5f, 9.33f);
        c.n(pathBuilderB, 19.0f, 14.67f, 15.67f, 12.5f);
        pathBuilderB.verticalLineTo(19.0f);
        pathBuilderB.horizontalLineTo(21.0f);
        pathBuilderB.verticalLineToRelative(-6.5f);
        pathBuilderB.horizontalLineTo(15.67f);
        pathBuilderB.close();
        c.B(pathBuilderB, 8.33f, 12.5f, 3.0f, 19.0f);
        c.D(pathBuilderB, 5.33f, 12.5f, 8.33f, 11.5f);
        pathBuilderB.verticalLineTo(5.0f);
        pathBuilderB.horizontalLineTo(3.0f);
        pathBuilderB.verticalLineToRelative(6.5f);
        pathBuilderB.horizontalLineTo(8.33f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewModule = imageVectorBuild;
        return imageVectorBuild;
    }
}
