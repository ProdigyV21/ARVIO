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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__10mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_10mp", "Landroidx/compose/material/icons/Icons$Rounded;", "get_10mp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _10mpKt {
    private static ImageVector __10mp;

    public static final ImageVector get_10mp(Icons.Rounded rounded) {
        ImageVector imageVector = __10mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded._10mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(13.5f, 7.0f, 15.0f, 3.0f, -1.5f);
        a.B(pathBuilderH, 7.0f, 19.0f, 3.0f, 5.0f);
        pathBuilderH.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderH.verticalLineToRelative(14.0f);
        pathBuilderH.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderH.horizontalLineToRelative(14.0f);
        pathBuilderH.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderH.verticalLineTo(5.0f);
        pathBuilderH.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        a.p(pathBuilderH, 11.75f, 18.5f, 11.75f, 18.5f);
        pathBuilderH.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        w.a.j(pathBuilderH, 14.0f, -1.0f, 2.25f);
        pathBuilderH.curveTo(10.0f, 16.66f, 9.66f, 17.0f, 9.25f, 17.0f);
        pathBuilderH.horizontalLineToRelative(0.0f);
        pathBuilderH.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        w.a.j(pathBuilderH, 14.0f, -1.0f, 3.75f);
        pathBuilderH.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderH.horizontalLineToRelative(0.0f);
        pathBuilderH.curveTo(6.34f, 18.5f, 6.0f, 18.16f, 6.0f, 17.75f);
        pathBuilderH.verticalLineTo(13.5f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.horizontalLineToRelative(4.5f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderH.verticalLineToRelative(4.25f);
        pathBuilderH.curveTo(12.5f, 18.16f, 12.16f, 18.5f, 11.75f, 18.5f);
        a.p(pathBuilderH, 14.25f, 18.5f, 14.25f, 18.5f);
        pathBuilderH.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderH.verticalLineTo(13.5f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.horizontalLineTo(17.0f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderH.verticalLineTo(16.0f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderH.horizontalLineToRelative(-2.0f);
        pathBuilderH.verticalLineToRelative(0.75f);
        pathBuilderH.curveTo(15.0f, 18.16f, 14.66f, 18.5f, 14.25f, 18.5f);
        d.k(pathBuilderH, 10.0f, 6.5f, 4.25f);
        pathBuilderH.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderH.horizontalLineToRelative(0.0f);
        pathBuilderH.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderH.verticalLineTo(7.0f);
        pathBuilderH.horizontalLineTo(7.75f);
        pathBuilderH.curveTo(7.34f, 7.0f, 7.0f, 6.66f, 7.0f, 6.25f);
        pathBuilderH.verticalLineToRelative(0.0f);
        pathBuilderH.curveTo(7.0f, 5.84f, 7.34f, 5.5f, 7.75f, 5.5f);
        pathBuilderH.horizontalLineTo(9.0f);
        pathBuilderH.curveTo(9.55f, 5.5f, 10.0f, 5.95f, 10.0f, 6.5f);
        pathBuilderH.close();
        pathBuilderH.moveTo(16.5f, 10.5f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderH.horizontalLineTo(13.0f);
        pathBuilderH.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderH.verticalLineToRelative(-4.0f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.horizontalLineToRelative(2.5f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        a.D(pathBuilderH, 10.5f, 15.0f, 14.0f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.t(pathBuilderH, 1.5f, 15.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __10mp = imageVectorBuild;
        return imageVectorBuild;
    }
}
