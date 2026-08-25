package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_systemUpdateAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SystemUpdateAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getSystemUpdateAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SystemUpdateAltKt {
    private static ImageVector _systemUpdateAlt;

    public static final ImageVector getSystemUpdateAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _systemUpdateAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SystemUpdateAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(12.0f, 16.0f, 4.0f, -4.0f, -3.0f);
        b.A(pathBuilderC, 13.0f, 3.0f, -2.0f, 9.0f);
        d.l(pathBuilderC, 8.0f, 12.0f, 4.0f, 4.0f);
        a.C(pathBuilderC, 21.0f, 3.0f, -6.0f, 1.99f);
        c.q(pathBuilderC, 6.0f, 14.03f, 3.0f, 19.02f);
        pathBuilderC.lineTo(3.0f, 4.99f);
        pathBuilderC.horizontalLineToRelative(6.0f);
        pathBuilderC.lineTo(9.0f, 3.0f);
        pathBuilderC.lineTo(3.0f, 3.0f);
        pathBuilderC.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderC.verticalLineToRelative(14.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(18.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderC.lineTo(23.0f, 5.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.z(pathBuilderC, 12.0f, 16.0f, 4.0f, -4.0f);
        c.A(pathBuilderC, -3.0f, 13.0f, 3.0f, -2.0f);
        pathBuilderC.verticalLineToRelative(9.0f);
        d.l(pathBuilderC, 8.0f, 12.0f, 4.0f, 4.0f);
        a.C(pathBuilderC, 21.0f, 3.0f, -6.0f, 1.99f);
        c.q(pathBuilderC, 6.0f, 14.03f, 3.0f, 19.02f);
        pathBuilderC.lineTo(3.0f, 4.99f);
        pathBuilderC.horizontalLineToRelative(6.0f);
        pathBuilderC.lineTo(9.0f, 3.0f);
        pathBuilderC.lineTo(3.0f, 3.0f);
        pathBuilderC.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderC.verticalLineToRelative(14.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(18.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderC.lineTo(23.0f, 5.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _systemUpdateAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
