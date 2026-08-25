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
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rollerSkating", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RollerSkating", "Landroidx/compose/material/icons/Icons$Filled;", "getRollerSkating", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RollerSkatingKt {
    private static ImageVector _rollerSkating;

    public static final ImageVector getRollerSkating(Icons.Filled filled) {
        ImageVector imageVector = _rollerSkating;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RollerSkating", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 6.5f);
        pathBuilderA.curveTo(9.0f, 6.22f, 9.22f, 6.0f, 9.5f, 6.0f);
        a.f(pathBuilderA, 2.52f, 12.0f, 5.0f, 9.5f);
        pathBuilderA.curveTo(9.22f, 5.0f, 9.0f, 4.78f, 9.0f, 4.5f);
        pathBuilderA.curveTo(9.0f, 4.22f, 9.22f, 4.0f, 9.5f, 4.0f);
        a.m(pathBuilderA, 12.0f, 1.0f, 4.0f, 15.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.horizontalLineToRelative(13.0f);
        pathBuilderA.lineToRelative(0.0f, -2.88f);
        pathBuilderA.curveToRelative(0.0f, -2.1f, -1.55f, -3.53f, -3.03f, -3.88f);
        pathBuilderA.lineToRelative(-2.7f, -0.67f);
        pathBuilderA.curveTo(13.4f, 8.35f, 12.7f, 7.76f, 12.32f, 7.0f);
        pathBuilderA.horizontalLineTo(9.5f);
        pathBuilderA.curveTo(9.22f, 7.0f, 9.0f, 6.78f, 9.0f, 6.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.0f, 23.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveTo(6.66f, 23.0f, 5.0f, 23.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.0f, 23.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveTo(20.66f, 23.0f, 19.0f, 23.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 23.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.66f, 23.0f, 12.0f, 23.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rollerSkating = imageVectorBuild;
        return imageVectorBuild;
    }
}
