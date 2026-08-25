package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_borderInner", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BorderInner", "Landroidx/compose/material/icons/Icons$Rounded;", "getBorderInner", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BorderInnerKt {
    private static ImageVector _borderInner;

    public static final ImageVector getBorderInner(Icons.Rounded rounded) {
        ImageVector imageVector = _borderInner;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BorderInner", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(3.0f, 21.0f, 2.0f, -2.0f);
        d.f(pathBuilderA, 3.0f, 19.0f, 2.0f);
        a.C(pathBuilderA, 7.0f, 21.0f, 2.0f, -2.0f);
        d.f(pathBuilderA, 7.0f, 19.0f, 2.0f);
        pathBuilderA.moveTo(5.0f, 7.0f);
        a.h(pathBuilderA, 3.0f, 7.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 5.0f, 7.0f, 3.0f, 17.0f);
        c.q(pathBuilderA, 2.0f, -2.0f, 3.0f, 15.0f);
        b.f(pathBuilderA, 2.0f, 9.0f, 3.0f);
        a.h(pathBuilderA, 7.0f, 3.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 9.0f, 3.0f, 5.0f, 3.0f);
        a.h(pathBuilderA, 3.0f, 3.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 5.0f, 3.0f, 17.0f, 3.0f);
        e.g(pathBuilderA, -2.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 17.0f, 3.0f, 19.0f, 9.0f);
        c.A(pathBuilderA, 2.0f, 21.0f, 7.0f, -2.0f);
        e.q(pathBuilderA, 2.0f, 19.0f, 3.0f, 2.0f);
        c.j(pathBuilderA, 2.0f, 21.0f, 3.0f, -2.0f);
        a.C(pathBuilderA, 15.0f, 21.0f, 2.0f, -2.0f);
        b.B(pathBuilderA, -2.0f, 2.0f, 12.0f, 3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.lineTo(4.0f, 11.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-7.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-7.0f);
        pathBuilderA.lineTo(13.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        b.w(pathBuilderA, 19.0f, 21.0f, 2.0f, -2.0f);
        b.B(pathBuilderA, -2.0f, 2.0f, 19.0f, 17.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderA, 2.0f, -2.0f, -2.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _borderInner = imageVectorBuild;
        return imageVectorBuild;
    }
}
