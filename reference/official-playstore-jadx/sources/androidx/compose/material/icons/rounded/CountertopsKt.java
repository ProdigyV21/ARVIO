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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_countertops", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Countertops", "Landroidx/compose/material/icons/Icons$Rounded;", "getCountertops", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CountertopsKt {
    private static ImageVector _countertops;

    public static final ImageVector getCountertops(Icons.Rounded rounded) {
        ImageVector imageVector = _countertops;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Countertops", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.0f, 10.0f, 0.0f, -2.83f);
        pathBuilderR.curveToRelative(0.0f, -1.62f, -1.22f, -3.08f, -2.84f, -3.17f);
        pathBuilderR.curveToRelative(-1.21f, -0.06f, -2.27f, 0.59f, -2.8f, 1.57f);
        pathBuilderR.curveTo(12.01f, 6.22f, 12.53f, 7.0f, 13.27f, 7.0f);
        pathBuilderR.horizontalLineToRelative(0.01f);
        pathBuilderR.curveToRelative(0.34f, 0.0f, 0.68f, -0.16f, 0.84f, -0.46f);
        pathBuilderR.curveTo(14.28f, 6.22f, 14.62f, 6.0f, 15.0f, 6.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(3.0f);
        pathBuilderR.horizontalLineTo(8.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineTo(5.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.curveTo(4.45f, 4.0f, 4.0f, 4.45f, 4.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(3.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineTo(3.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(1.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-7.0f);
        pathBuilderR.horizontalLineToRelative(1.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        w.a.v(pathBuilderR, 18.0f, 13.0f, 18.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderR, -6.0f, 2.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _countertops = imageVectorBuild;
        return imageVectorBuild;
    }
}
