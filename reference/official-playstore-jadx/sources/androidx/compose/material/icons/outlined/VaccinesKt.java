package androidx.compose.material.icons.outlined;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_vaccines", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Vaccines", "Landroidx/compose/material/icons/Icons$Outlined;", "getVaccines", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VaccinesKt {
    private static ImageVector _vaccines;

    public static final ImageVector getVaccines(Icons.Outlined outlined) {
        ImageVector imageVector = _vaccines;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Vaccines", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(11.0f, 5.5f, 8.0f, 4.0f, 0.5f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderC.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderC.horizontalLineToRelative(-3.0f);
        pathBuilderC.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderC.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a0.a.w(pathBuilderC, 6.0f, 1.5f, 3.0f);
        pathBuilderC.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderC.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderC.verticalLineTo(15.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        f.y(pathBuilderC, 1.0f, 4.0f, 2.0f, 1.5f);
        pathBuilderC.verticalLineTo(17.0f);
        pathBuilderC.horizontalLineToRelative(1.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderC.verticalLineTo(7.5f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderC.curveTo(12.0f, 5.95f, 11.55f, 5.5f, 11.0f, 5.5f);
        w.a.r(pathBuilderC, 9.0f, 9.0f, 7.25f);
        pathBuilderC.curveTo(6.84f, 9.0f, 6.5f, 9.34f, 6.5f, 9.75f);
        pathBuilderC.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        b.i(pathBuilderC, 9.0f, 12.0f, 7.25f);
        pathBuilderC.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderC.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        b.q(pathBuilderC, 9.0f, 9.0f, 15.0f, 5.0f);
        f.k(pathBuilderC, 7.5f, 4.0f, 9.0f);
        pathBuilderC.moveTo(19.5f, 10.5f);
        pathBuilderC.verticalLineTo(10.0f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderC.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderC.horizontalLineToRelative(-5.0f);
        pathBuilderC.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderC.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderC.verticalLineToRelative(0.5f);
        pathBuilderC.curveToRelative(0.0f, 0.5f, -1.5f, 1.16f, -1.5f, 3.0f);
        pathBuilderC.verticalLineTo(20.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(4.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderC.verticalLineToRelative(-6.5f);
        pathBuilderC.curveTo(21.0f, 11.66f, 19.5f, 11.0f, 19.5f, 10.5f);
        b.t(pathBuilderC, 16.5f, 10.5f, 10.0f, 1.0f);
        pathBuilderC.verticalLineToRelative(0.5f);
        pathBuilderC.curveToRelative(0.0f, 1.6f, 1.5f, 2.0f, 1.5f, 3.0f);
        pathBuilderC.verticalLineTo(14.0f);
        pathBuilderC.horizontalLineToRelative(-4.0f);
        pathBuilderC.curveToRelative(0.0f, -0.21f, 0.0f, -0.39f, 0.0f, -0.5f);
        pathBuilderC.curveTo(15.0f, 12.5f, 16.5f, 12.1f, 16.5f, 10.5f);
        b.t(pathBuilderC, 19.0f, 15.5f, 17.0f, -4.0f);
        pathBuilderC.curveToRelative(0.0f, -0.51f, 0.0f, -1.02f, 0.0f, -1.5f);
        a.x(pathBuilderC, 19.0f, 15.0f, 20.0f);
        pathBuilderC.curveToRelative(0.0f, 0.0f, 0.0f, -0.63f, 0.0f, -1.5f);
        pathBuilderC.horizontalLineToRelative(4.0f);
        pathBuilderC.verticalLineTo(20.0f);
        pathBuilderC.horizontalLineTo(15.0f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _vaccines = imageVectorBuild;
        return imageVectorBuild;
    }
}
