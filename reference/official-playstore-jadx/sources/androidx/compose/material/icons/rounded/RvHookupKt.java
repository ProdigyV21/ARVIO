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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rvHookup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RvHookup", "Landroidx/compose/material/icons/Icons$Rounded;", "getRvHookup", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RvHookupKt {
    private static ImageVector _rvHookup;

    public static final ImageVector getRvHookup(Icons.Rounded rounded) {
        ImageVector imageVector = _rvHookup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RvHookup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(21.0f, 17.0f, -1.0f, -6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.lineTo(7.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(-0.74f);
        pathBuilderA.curveToRelative(0.0f, -0.46f, -0.56f, -0.7f, -0.89f, -0.37f);
        pathBuilderA.lineTo(4.37f, 9.63f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.2f, 0.53f, 0.0f, 0.74f);
        pathBuilderA.lineToRelative(1.74f, 1.74f);
        pathBuilderA.curveToRelative(0.33f, 0.33f, 0.89f, 0.1f, 0.89f, -0.37f);
        b.A(pathBuilderA, 7.0f, 11.0f, 4.0f, 3.0f);
        pathBuilderA.lineTo(5.0f, 14.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 20.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        b.w(pathBuilderA, 18.0f, 14.0f, -4.0f, -3.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        c.r(pathBuilderA, 2.0f, 10.0f, 6.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(0.74f);
        pathBuilderA.curveToRelative(0.0f, 0.46f, 0.56f, 0.7f, 0.89f, 0.37f);
        pathBuilderA.lineToRelative(1.74f, -1.74f);
        pathBuilderA.curveToRelative(0.2f, -0.2f, 0.2f, -0.53f, 0.0f, -0.74f);
        pathBuilderA.lineToRelative(-1.74f, -1.74f);
        pathBuilderA.curveToRelative(-0.33f, -0.33f, -0.89f, -0.1f, -0.89f, 0.37f);
        pathBuilderA.lineTo(17.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, 0.45f, 1.0f, 1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rvHookup = imageVectorBuild;
        return imageVectorBuild;
    }
}
