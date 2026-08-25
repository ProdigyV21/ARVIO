package androidx.compose.material.icons.sharp;

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
import v.a;
import v.c;
import v.e;
import v.f;

/* JADX INFO: renamed from: androidx.compose.material.icons.sharp.AddchartKt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addchart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Addchart", "Landroidx/compose/material/icons/Icons$Sharp;", "getAddchart", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class C0446AddchartKt {
    private static ImageVector _addchart;

    public static final ImageVector getAddchart(Icons.Sharp sharp) {
        ImageVector imageVector = _addchart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Addchart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(11.0f, 9.0f, 2.0f, 8.0f, -2.0f);
        w.a.k(pathBuilderK, 9.0f, 9.0f, 17.0f, -6.0f);
        b.e(pathBuilderK, 7.0f, 6.0f, 9.0f);
        c.B(pathBuilderK, 19.0f, 19.0f, 5.0f, 5.0f);
        w.a.B(pathBuilderK, 6.0f, 3.0f, 3.0f, 18.0f);
        f.w(pathBuilderK, 18.0f, -8.0f, -2.0f, 19.0f);
        c.m(pathBuilderK, 15.0f, 13.0f, 4.0f, 2.0f);
        e.y(pathBuilderK, -4.0f, 15.0f, 19.0f, 5.0f);
        v.b.r(pathBuilderK, 2.0f, -2.0f, 3.0f, -3.0f);
        v.b.z(pathBuilderK, 2.0f, 3.0f, 3.0f, 2.0f);
        pathBuilderK.verticalLineTo(7.0f);
        pathBuilderK.horizontalLineToRelative(3.0f);
        pathBuilderK.verticalLineTo(5.0f);
        pathBuilderK.horizontalLineTo(19.0f);
        pathBuilderK.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderK.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addchart = imageVectorBuild;
        return imageVectorBuild;
    }
}
