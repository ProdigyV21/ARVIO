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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_commute", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Commute", "Landroidx/compose/material/icons/Icons$Outlined;", "getCommute", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CommuteKt {
    private static ImageVector _commute;

    public static final ImageVector getCommute(Icons.Outlined outlined) {
        ImageVector imageVector = _commute;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Commute", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.0f, 4.0f, 5.0f, 4.0f);
        pathBuilderJ.curveTo(3.34f, 4.0f, 2.0f, 5.34f, 2.0f, 7.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        b.u(pathBuilderJ, -1.0f, 1.0f, 1.0f, 1.0f);
        a0.a.m(pathBuilderJ, 2.0f, -2.0f, 2.0f, -5.0f);
        pathBuilderJ.lineTo(4.0f, 13.0f);
        b.A(pathBuilderJ, 4.0f, 6.0f, 9.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.lineTo(15.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(5.0f, 14.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.57f, 9.66f);
        pathBuilderJ.curveToRelative(-0.14f, -0.4f, -0.52f, -0.66f, -0.97f, -0.66f);
        pathBuilderJ.horizontalLineToRelative(-7.19f);
        pathBuilderJ.curveToRelative(-0.46f, 0.0f, -0.83f, 0.26f, -0.98f, 0.66f);
        pathBuilderJ.lineToRelative(-1.42f, 4.11f);
        pathBuilderJ.verticalLineToRelative(5.51f);
        pathBuilderJ.curveToRelative(0.0f, 0.38f, 0.31f, 0.72f, 0.69f, 0.72f);
        pathBuilderJ.horizontalLineToRelative(0.62f);
        pathBuilderJ.curveToRelative(0.38f, 0.0f, 0.68f, -0.38f, 0.68f, -0.76f);
        b.A(pathBuilderJ, 12.0f, 18.0f, 8.0f, 1.24f);
        pathBuilderJ.curveToRelative(0.0f, 0.38f, 0.31f, 0.76f, 0.69f, 0.76f);
        pathBuilderJ.horizontalLineToRelative(0.61f);
        pathBuilderJ.curveToRelative(0.38f, 0.0f, 0.69f, -0.34f, 0.69f, -0.72f);
        pathBuilderJ.lineToRelative(0.01f, -1.37f);
        c.t(pathBuilderJ, -4.14f, -1.43f, -4.11f);
        pathBuilderJ.moveTo(12.41f, 10.0f);
        e.C(pathBuilderJ, 7.19f, 1.03f, 3.0f, -9.25f);
        b.C(pathBuilderJ, 1.03f, -3.0f, 12.0f, 16.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.0f, 16.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _commute = imageVectorBuild;
        return imageVectorBuild;
    }
}
