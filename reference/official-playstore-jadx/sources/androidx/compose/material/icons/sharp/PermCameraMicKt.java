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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_permCameraMic", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PermCameraMic", "Landroidx/compose/material/icons/Icons$Sharp;", "getPermCameraMic", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PermCameraMicKt {
    private static ImageVector _permCameraMic;

    public static final ImageVector getPermCameraMic(Icons.Sharp sharp) {
        ImageVector imageVector = _permCameraMic;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PermCameraMic", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(22.0f, 5.0f, -5.17f, 15.0f, 3.0f);
        pathBuilderI.lineTo(9.0f, 3.0f);
        pathBuilderI.lineTo(7.17f, 5.0f);
        a.h(pathBuilderI, 2.0f, 5.0f, 16.0f, 9.0f);
        pathBuilderI.verticalLineToRelative(-2.09f);
        pathBuilderI.curveToRelative(-2.83f, -0.48f, -5.0f, -2.94f, -5.0f, -5.91f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderI.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, 2.97f, -2.17f, 5.43f, -5.0f, 5.91f);
        pathBuilderI.lineTo(13.0f, 21.0f);
        d.q(pathBuilderI, 9.0f, 22.0f, 5.0f);
        pathBuilderI.moveTo(14.0f, 13.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderI.lineTo(10.0f, 9.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderI, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _permCameraMic = imageVectorBuild;
        return imageVectorBuild;
    }
}
