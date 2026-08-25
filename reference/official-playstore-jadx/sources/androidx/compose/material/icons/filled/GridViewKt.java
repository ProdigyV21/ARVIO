package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.b;
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gridView", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GridView", "Landroidx/compose/material/icons/Icons$Filled;", "getGridView", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GridViewKt {
    private static ImageVector _gridView;

    public static final ImageVector getGridView(Icons.Filled filled) {
        ImageVector imageVector = _gridView;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.GridView", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(3.0f, 3.0f, 8.0f, 8.0f);
        b.y(pathBuilderA, 11.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.moveTo(9.0f, 9.0f);
        pathBuilderA.lineTo(5.0f, 9.0f);
        b.g(pathBuilderA, 5.0f, 5.0f, 4.0f, 4.0f);
        c.m(pathBuilderA, 3.0f, 13.0f, 8.0f, 8.0f);
        a.n(pathBuilderA, -8.0f, 3.0f, 13.0f);
        pathBuilderA.moveTo(9.0f, 19.0f);
        v.a.h(pathBuilderA, 5.0f, 19.0f, -4.0f, 4.0f);
        e.q(pathBuilderA, 4.0f, 13.0f, 3.0f, 8.0f);
        c.j(pathBuilderA, 8.0f, 21.0f, 3.0f, -8.0f);
        pathBuilderA.moveTo(19.0f, 9.0f);
        c.A(pathBuilderA, -4.0f, 15.0f, 5.0f, 4.0f);
        e.q(pathBuilderA, 4.0f, 13.0f, 13.0f, 8.0f);
        d.x(pathBuilderA, 8.0f, -8.0f, -8.0f);
        v.a.C(pathBuilderA, 19.0f, 19.0f, -4.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.s(pathBuilderA, 4.0f, 4.0f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gridView = imageVectorBuild;
        return imageVectorBuild;
    }
}
