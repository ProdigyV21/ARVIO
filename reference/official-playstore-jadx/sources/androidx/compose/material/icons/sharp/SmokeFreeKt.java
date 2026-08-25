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
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smokeFree", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmokeFree", "Landroidx/compose/material/icons/Icons$Sharp;", "getSmokeFree", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmokeFreeKt {
    private static ImageVector _smokeFree;

    public static final ImageVector getSmokeFree(Icons.Sharp sharp) {
        ImageVector imageVector = _smokeFree;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SmokeFree", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(20.5f, 13.0f, 22.0f, 13.0f, 3.0f);
        e.r(pathBuilderS, -1.5f, 14.5f, 8.65f, 1.53f);
        pathBuilderS.curveToRelative(1.05f, 0.0f, 1.97f, 0.74f, 1.97f, 2.05f);
        b.A(pathBuilderS, 18.0f, 12.0f, 1.5f, -1.64f);
        pathBuilderS.curveToRelative(0.0f, -1.81f, -1.6f, -3.16f, -3.47f, -3.16f);
        pathBuilderS.lineTo(14.5f, 7.2f);
        pathBuilderS.curveToRelative(-1.02f, 0.0f, -1.85f, -0.98f, -1.85f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(0.83f, -1.75f, 1.85f, -1.75f);
        pathBuilderS.verticalLineToRelative(-1.5f);
        pathBuilderS.curveToRelative(-1.85f, 0.0f, -3.35f, 1.5f, -3.35f, 3.35f);
        pathBuilderS.reflectiveCurveToRelative(1.5f, 3.35f, 3.35f, 3.35f);
        a.g(pathBuilderS, 17.0f, 13.0f, -2.34f);
        c.z(pathBuilderS, 17.0f, 15.34f, 18.85f, 4.73f);
        pathBuilderS.curveToRelative(0.62f, -0.61f, 1.0f, -1.45f, 1.0f, -2.38f);
        pathBuilderS.horizontalLineToRelative(-1.5f);
        pathBuilderS.curveToRelative(0.0f, 1.02f, -0.83f, 1.85f, -1.85f, 1.85f);
        pathBuilderS.verticalLineToRelative(1.5f);
        pathBuilderS.curveToRelative(2.24f, 0.0f, 4.0f, 1.83f, 4.0f, 4.07f);
        pathBuilderS.lineTo(20.5f, 12.0f);
        pathBuilderS.lineTo(22.0f, 12.0f);
        pathBuilderS.lineTo(22.0f, 9.76f);
        pathBuilderS.curveToRelative(0.0f, -2.22f, -1.28f, -4.14f, -3.15f, -5.03f);
        b.w(pathBuilderS, 18.0f, 13.0f, 1.5f, 3.0f);
        c.z(pathBuilderS, 18.0f, 16.0f, 3.41f, 4.59f);
        pathBuilderS.lineTo(2.0f, 6.0f);
        pathBuilderS.lineToRelative(7.0f, 7.0f);
        a.h(pathBuilderS, 2.0f, 13.0f, 3.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 7.0f, 7.0f, 1.41f, -1.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smokeFree = imageVectorBuild;
        return imageVectorBuild;
    }
}
