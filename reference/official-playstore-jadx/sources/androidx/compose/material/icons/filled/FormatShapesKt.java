package androidx.compose.material.icons.filled;

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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatShapes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatShapes", "Landroidx/compose/material/icons/Icons$Filled;", "getFormatShapes", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatShapesKt {
    private static ImageVector _formatShapes;

    public static final ImageVector getFormatShapes(Icons.Filled filled) {
        ImageVector imageVector = _formatShapes;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FormatShapes", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(23.0f, 7.0f, 23.0f, 1.0f, -6.0f);
        pathBuilderT.verticalLineToRelative(2.0f);
        pathBuilderT.lineTo(7.0f, 3.0f);
        pathBuilderT.lineTo(7.0f, 1.0f);
        v.a.h(pathBuilderT, 1.0f, 1.0f, 6.0f, 2.0f);
        d.r(pathBuilderT, 10.0f, 1.0f, 17.0f, 6.0f);
        v.a.o(pathBuilderT, 6.0f, -2.0f, 10.0f, 2.0f);
        e.g(pathBuilderT, 6.0f, -6.0f, -2.0f);
        f.n(pathBuilderT, 21.0f, 7.0f, 2.0f);
        v.a.C(pathBuilderT, 3.0f, 3.0f, 2.0f, 2.0f);
        b.y(pathBuilderT, 3.0f, 5.0f, 3.0f, 3.0f);
        pathBuilderT.moveTo(5.0f, 21.0f);
        v.a.h(pathBuilderT, 3.0f, 21.0f, -2.0f, 2.0f);
        b.f(pathBuilderT, 2.0f, 17.0f, 19.0f);
        pathBuilderT.lineTo(7.0f, 19.0f);
        pathBuilderT.verticalLineToRelative(-2.0f);
        pathBuilderT.lineTo(5.0f, 17.0f);
        pathBuilderT.lineTo(5.0f, 7.0f);
        c.A(pathBuilderT, 2.0f, 7.0f, 5.0f, 10.0f);
        b.z(pathBuilderT, 2.0f, 2.0f, 10.0f, -2.0f);
        c.r(pathBuilderT, 2.0f, 21.0f, 21.0f, -2.0f);
        b.l(pathBuilderT, -2.0f, 2.0f, 2.0f);
        pathBuilderT.moveTo(19.0f, 5.0f);
        b.A(pathBuilderT, 19.0f, 3.0f, 2.0f, 2.0f);
        e.r(pathBuilderT, -2.0f, 13.73f, 14.0f, -3.49f);
        pathBuilderT.lineToRelative(-0.73f, 2.0f);
        pathBuilderT.lineTo(7.89f, 16.0f);
        pathBuilderT.lineToRelative(3.4f, -9.0f);
        e.C(pathBuilderT, 1.4f, 3.41f, 9.0f, -1.63f);
        b.C(pathBuilderT, -0.74f, -2.0f, 10.69f, 12.74f);
        pathBuilderT.horizontalLineToRelative(2.61f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderT, 12.0f, 8.91f, -1.31f, 3.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatShapes = imageVectorBuild;
        return imageVectorBuild;
    }
}
