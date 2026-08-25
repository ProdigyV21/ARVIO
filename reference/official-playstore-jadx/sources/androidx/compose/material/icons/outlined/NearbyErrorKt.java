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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearbyError", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearbyError", "Landroidx/compose/material/icons/Icons$Outlined;", "getNearbyError", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearbyErrorKt {
    private static ImageVector _nearbyError;

    public static final ImageVector getNearbyError(Icons.Outlined outlined) {
        ImageVector imageVector = _nearbyError;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NearbyError", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 7.57f, 4.42f, 4.42f);
        pathBuilderR.lineTo(12.0f, 16.41f);
        d.C(pathBuilderR, -4.42f, -4.42f, 12.0f, 7.57f);
        pathBuilderR.moveTo(12.0f, 19.19f);
        pathBuilderR.lineToRelative(-7.2f, -7.2f);
        pathBuilderR.lineToRelative(7.2f, -7.2f);
        pathBuilderR.lineToRelative(6.0f, 6.0f);
        pathBuilderR.verticalLineTo(7.16f);
        pathBuilderR.lineToRelative(-4.58f, -4.58f);
        pathBuilderR.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderR.lineToRelative(-8.01f, 8.0f);
        pathBuilderR.curveToRelative(-0.78f, 0.78f, -0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderR.lineToRelative(8.01f, 8.0f);
        pathBuilderR.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderR.lineTo(18.0f, 16.82f);
        y.a.n(pathBuilderR, -3.63f, 12.0f, 19.19f);
        a.C(pathBuilderR, 20.0f, 20.0f, 2.0f, 2.0f);
        c.D(pathBuilderR, -2.0f, 20.0f, 22.0f, 10.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.verticalLineToRelative(8.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.verticalLineTo(10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearbyError = imageVectorBuild;
        return imageVectorBuild;
    }
}
