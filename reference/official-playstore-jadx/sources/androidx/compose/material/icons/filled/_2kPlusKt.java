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
import v.a;
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__2kPlus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_2kPlus", "Landroidx/compose/material/icons/Icons$Filled;", "get_2kPlus", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _2kPlusKt {
    private static ImageVector __2kPlus;

    public static final ImageVector get_2kPlus(Icons.Filled filled) {
        ImageVector imageVector = __2kPlus;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled._2kPlus", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 3.0f, 5.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(21.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.5f, 11.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        e.g(pathBuilderJ, -2.0f, 1.0f, 3.0f);
        pathBuilderJ.lineTo(9.5f, 15.0f);
        pathBuilderJ.lineTo(5.0f, 15.0f);
        pathBuilderJ.verticalLineToRelative(-2.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        c.q(pathBuilderJ, 2.0f, -1.0f, 5.0f, 10.5f);
        pathBuilderJ.lineTo(5.0f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(3.5f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderJ, 1.5f, 14.25f, 15.0f);
        pathBuilderJ.lineToRelative(-1.75f, -2.25f);
        pathBuilderJ.lineTo(12.5f, 15.0f);
        pathBuilderJ.lineTo(11.0f, 15.0f);
        b.A(pathBuilderJ, 11.0f, 9.0f, 1.5f, 2.25f);
        pathBuilderJ.lineTo(14.25f, 9.0f);
        pathBuilderJ.lineTo(16.0f, 9.0f);
        pathBuilderJ.lineToRelative(-2.25f, 3.0f);
        f.n(pathBuilderJ, 16.0f, 15.0f, -1.75f);
        pathBuilderJ.moveTo(20.0f, 12.5f);
        c.A(pathBuilderJ, -1.5f, 18.5f, 14.0f, -1.0f);
        d.r(pathBuilderJ, -1.5f, 16.0f, 12.5f, -1.0f);
        c.A(pathBuilderJ, 1.5f, 17.5f, 10.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(1.5f);
        pathBuilderJ.lineTo(20.0f, 11.5f);
        pathBuilderJ.verticalLineToRelative(1.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __2kPlus = imageVectorBuild;
        return imageVectorBuild;
    }
}
