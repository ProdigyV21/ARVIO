package androidx.compose.material.icons.filled;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fiberNew", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FiberNew", "Landroidx/compose/material/icons/Icons$Filled;", "getFiberNew", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FiberNewKt {
    private static ImageVector _fiberNew;

    public static final ImageVector getFiberNew(Icons.Filled filled) {
        ImageVector imageVector = _fiberNew;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FiberNew", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.89f, 4.0f, 2.01f, 4.89f, 2.01f, 6.0f);
        pathBuilderQ.lineTo(2.0f, 18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.89f, 21.11f, 4.0f, 20.0f, 4.0f);
        w.a.r(pathBuilderQ, 8.5f, 15.0f, 7.3f);
        b.m(pathBuilderQ, -2.55f, -3.5f, 15.0f, 3.5f);
        b.l(pathBuilderQ, 9.0f, 1.25f, 2.5f, 3.5f);
        a.v(pathBuilderQ, 9.0f, 8.5f, 15.0f);
        a.A(pathBuilderQ, 13.5f, 10.26f, 11.0f, 1.12f);
        a0.a.y(pathBuilderQ, 2.5f, 1.26f, 11.0f, 1.11f);
        a0.a.C(pathBuilderQ, 2.5f, 15.0f, -4.0f, 9.0f);
        c.D(pathBuilderQ, 4.0f, 10.26f, 20.5f, 14.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-4.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        v.b.r(pathBuilderQ, 9.0f, 1.25f, 4.51f, 1.13f);
        v.b.r(pathBuilderQ, 9.99f, 1.25f, 3.51f, 1.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.m(pathBuilderQ, 9.0f, 1.25f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fiberNew = imageVectorBuild;
        return imageVectorBuild;
    }
}
