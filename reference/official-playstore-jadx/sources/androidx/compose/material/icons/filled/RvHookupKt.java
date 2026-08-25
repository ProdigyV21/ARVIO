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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rvHookup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RvHookup", "Landroidx/compose/material/icons/Icons$Filled;", "getRvHookup", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RvHookupKt {
    private static ImageVector _rvHookup;

    public static final ImageVector getRvHookup(Icons.Filled filled) {
        ImageVector imageVector = _rvHookup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RvHookup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(20.0f, 17.0f, -6.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.lineTo(7.0f, 9.0f);
        pathBuilderI.lineTo(7.0f, 7.0f);
        pathBuilderI.lineToRelative(-3.0f, 3.0f);
        b.u(pathBuilderI, 3.0f, 3.0f, -2.0f, 4.0f);
        d.r(pathBuilderI, 3.0f, 4.0f, 14.0f, 3.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderI.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        d.x(pathBuilderI, 8.0f, -2.0f, -2.0f);
        pathBuilderI.moveTo(11.0f, 20.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        b.w(pathBuilderI, 18.0f, 14.0f, -4.0f, -3.0f);
        b.B(pathBuilderI, 4.0f, 3.0f, 17.0f, 2.0f);
        d.r(pathBuilderI, 2.0f, 9.0f, 4.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(8.0f);
        pathBuilderI.verticalLineToRelative(2.0f);
        pathBuilderI.lineToRelative(3.0f, -3.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rvHookup = imageVectorBuild;
        return imageVectorBuild;
    }
}
