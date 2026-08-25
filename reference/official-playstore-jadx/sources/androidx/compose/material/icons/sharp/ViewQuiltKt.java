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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_viewQuilt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewQuilt", "Landroidx/compose/material/icons/Icons$Sharp;", "getViewQuilt$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getViewQuilt", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewQuiltKt {
    private static ImageVector _viewQuilt;

    public static final ImageVector getViewQuilt(Icons.Sharp sharp) {
        ImageVector imageVector = _viewQuilt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ViewQuilt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = f.b(21.0f, 5.0f, 6.5f, 9.33f, 5.0f);
        f.l(pathBuilderB, 21.0f, 14.67f, 19.0f, -6.5f);
        f.f(pathBuilderB, 9.33f, 19.0f, 14.67f);
        f.g(pathBuilderB, 15.67f, 12.5f, 19.0f, 21.0f);
        e.y(pathBuilderB, -6.5f, 15.67f, 8.33f, 19.0f);
        pathBuilderB.verticalLineTo(5.0f);
        pathBuilderB.horizontalLineTo(3.0f);
        pathBuilderB.verticalLineToRelative(14.0f);
        pathBuilderB.horizontalLineTo(8.33f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewQuilt = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getViewQuilt$annotations(Icons.Sharp sharp) {
    }
}
