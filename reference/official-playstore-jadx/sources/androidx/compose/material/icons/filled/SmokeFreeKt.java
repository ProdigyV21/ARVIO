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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smokeFree", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmokeFree", "Landroidx/compose/material/icons/Icons$Filled;", "getSmokeFree", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmokeFreeKt {
    private static ImageVector _smokeFree;

    public static final ImageVector getSmokeFree(Icons.Filled filled) {
        ImageVector imageVector = _smokeFree;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SmokeFree", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(2.0f, 6.0f, 6.99f, 7.0f);
        a.h(pathBuilderR, 2.0f, 13.0f, 3.0f, 9.99f);
        pathBuilderR.lineToRelative(7.0f, 7.0f);
        b.D(pathBuilderR, 1.26f, -1.25f, -17.0f, -17.0f);
        pathBuilderR.moveTo(20.5f, 13.0f);
        e.p(pathBuilderR, 22.0f, 13.0f, 3.0f, -1.5f);
        a.C(pathBuilderR, 18.0f, 13.0f, 1.5f, 3.0f);
        c.z(pathBuilderR, 18.0f, 16.0f, 18.85f, 4.88f);
        pathBuilderR.curveToRelative(0.62f, -0.61f, 1.0f, -1.45f, 1.0f, -2.38f);
        pathBuilderR.horizontalLineToRelative(-1.5f);
        pathBuilderR.curveToRelative(0.0f, 1.02f, -0.83f, 1.85f, -1.85f, 1.85f);
        pathBuilderR.verticalLineToRelative(1.5f);
        pathBuilderR.curveToRelative(2.24f, 0.0f, 4.0f, 1.83f, 4.0f, 4.07f);
        pathBuilderR.lineTo(20.5f, 12.0f);
        pathBuilderR.lineTo(22.0f, 12.0f);
        pathBuilderR.lineTo(22.0f, 9.92f);
        pathBuilderR.curveToRelative(0.0f, -2.23f, -1.28f, -4.15f, -3.15f, -5.04f);
        a.g(pathBuilderR, 14.5f, 8.7f, 1.53f);
        pathBuilderR.curveToRelative(1.05f, 0.0f, 1.97f, 0.74f, 1.97f, 2.05f);
        b.A(pathBuilderR, 18.0f, 12.0f, 1.5f, -1.59f);
        pathBuilderR.curveToRelative(0.0f, -1.8f, -1.6f, -3.16f, -3.47f, -3.16f);
        pathBuilderR.lineTo(14.5f, 7.25f);
        pathBuilderR.curveToRelative(-1.02f, 0.0f, -1.85f, -0.98f, -1.85f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.83f, -1.75f, 1.85f, -1.75f);
        pathBuilderR.lineTo(14.5f, 2.0f);
        pathBuilderR.curveToRelative(-1.85f, 0.0f, -3.35f, 1.5f, -3.35f, 3.35f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, 3.35f, 3.35f, 3.35f);
        a.p(pathBuilderR, 17.0f, 15.93f, 17.0f, 13.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderR, -2.93f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smokeFree = imageVectorBuild;
        return imageVectorBuild;
    }
}
