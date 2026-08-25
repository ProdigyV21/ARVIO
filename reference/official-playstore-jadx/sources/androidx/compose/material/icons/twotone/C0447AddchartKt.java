package androidx.compose.material.icons.twotone;

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

/* JADX INFO: renamed from: androidx.compose.material.icons.twotone.AddchartKt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addchart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Addchart", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAddchart", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class C0447AddchartKt {
    private static ImageVector _addchart;

    public static final ImageVector getAddchart(Icons.TwoTone twoTone) {
        ImageVector imageVector = _addchart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Addchart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(22.0f, 5.0f, 2.0f, -3.0f, 3.0f);
        a0.a.C(pathBuilderU, -2.0f, 7.0f, -3.0f, 5.0f);
        w.a.C(pathBuilderU, 3.0f, 2.0f, 2.0f, 3.0f);
        w.a.s(pathBuilderU, 22.0f, 19.0f, 19.0f, 5.0f);
        a.l(pathBuilderU, 5.0f, 6.0f, 3.0f, 5.0f);
        pathBuilderU.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderU.verticalLineToRelative(14.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(14.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        v.a.t(pathBuilderU, -6.0f, -2.0f, 19.0f);
        c.m(pathBuilderU, 15.0f, 13.0f, 4.0f, 2.0f);
        e.y(pathBuilderU, -4.0f, 15.0f, 11.0f, 17.0f);
        f.A(pathBuilderU, 2.0f, 9.0f, -2.0f, 17.0f);
        c.u(pathBuilderU, 9.0f, 17.0f, -6.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderU, 6.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addchart = imageVectorBuild;
        return imageVectorBuild;
    }
}
