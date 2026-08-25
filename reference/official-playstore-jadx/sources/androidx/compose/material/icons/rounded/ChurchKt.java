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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_church", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Church", "Landroidx/compose/material/icons/Icons$Rounded;", "getChurch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChurchKt {
    private static ImageVector _church;

    public static final ImageVector getChurch(Icons.Rounded rounded) {
        ImageVector imageVector = _church;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Church", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(18.0f, 12.22f, -1.99f);
        pathBuilderI.curveToRelative(0.0f, -0.76f, -0.43f, -1.45f, -1.11f, -1.79f);
        b.n(pathBuilderI, 13.0f, 6.5f, 5.0f, 1.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(0.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(-1.0f);
        pathBuilderI.verticalLineTo(2.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(0.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(1.0f);
        pathBuilderI.horizontalLineToRelative(-1.0f);
        pathBuilderI.curveTo(9.45f, 3.0f, 9.0f, 3.45f, 9.0f, 4.0f);
        pathBuilderI.verticalLineToRelative(0.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.q(pathBuilderI, 1.0f, 1.5f, 7.11f, 8.45f);
        pathBuilderI.curveTo(6.43f, 8.79f, 6.0f, 9.48f, 6.0f, 10.24f);
        pathBuilderI.verticalLineToRelative(1.99f);
        pathBuilderI.lineToRelative(-2.81f, 1.25f);
        pathBuilderI.curveTo(2.47f, 13.79f, 2.0f, 14.51f, 2.0f, 15.3f);
        pathBuilderI.verticalLineTo(20.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(6.0f);
        pathBuilderI.lineToRelative(0.0f, -2.89f);
        pathBuilderI.curveToRelative(0.0f, -1.0f, 0.68f, -1.92f, 1.66f, -2.08f);
        pathBuilderI.curveTo(12.92f, 16.82f, 14.0f, 17.79f, 14.0f, 19.0f);
        pathBuilderI.verticalLineToRelative(3.0f);
        pathBuilderI.horizontalLineToRelative(6.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.verticalLineToRelative(-4.7f);
        pathBuilderI.curveToRelative(0.0f, -0.79f, -0.47f, -1.51f, -1.19f, -1.83f);
        c.z(pathBuilderI, 18.0f, 12.22f, 12.0f, 13.5f);
        pathBuilderI.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderI.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderI.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderI, 12.83f, 13.5f, 12.0f, 13.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _church = imageVectorBuild;
        return imageVectorBuild;
    }
}
