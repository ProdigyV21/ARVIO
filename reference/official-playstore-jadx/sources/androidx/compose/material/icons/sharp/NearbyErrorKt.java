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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearbyError", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearbyError", "Landroidx/compose/material/icons/Icons$Sharp;", "getNearbyError", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearbyErrorKt {
    private static ImageVector _nearbyError;

    public static final ImageVector getNearbyError(Icons.Sharp sharp) {
        ImageVector imageVector = _nearbyError;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NearbyError", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.0f, 7.58f, 16.42f, 12.0f);
        pathBuilderJ.lineTo(12.0f, 16.42f);
        b.y(pathBuilderJ, 7.58f, 12.0f, 12.0f, 7.58f);
        pathBuilderJ.moveTo(12.0f, 19.2f);
        pathBuilderJ.lineTo(4.8f, 12.0f);
        pathBuilderJ.lineTo(12.0f, 4.8f);
        pathBuilderJ.lineToRelative(6.0f, 6.0f);
        pathBuilderJ.verticalLineTo(7.17f);
        pathBuilderJ.lineToRelative(-5.99f, -5.99f);
        pathBuilderJ.lineTo(1.18f, 12.01f);
        pathBuilderJ.lineToRelative(10.83f, 10.83f);
        pathBuilderJ.lineTo(18.0f, 16.83f);
        c.x(pathBuilderJ, 13.2f, 12.0f, 19.2f);
        a.C(pathBuilderJ, 20.0f, 20.0f, 2.0f, 2.0f);
        c.D(pathBuilderJ, -2.0f, 20.0f, 22.0f, 10.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.verticalLineTo(10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearbyError = imageVectorBuild;
        return imageVectorBuild;
    }
}
