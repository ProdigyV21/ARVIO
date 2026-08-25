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
import v.a;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_altRoute", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AltRoute", "Landroidx/compose/material/icons/Icons$Filled;", "getAltRoute$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getAltRoute", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AltRouteKt {
    private static ImageVector _altRoute;

    public static final ImageVector getAltRoute(Icons.Filled filled) {
        ImageVector imageVector = _altRoute;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AltRoute", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.78f, 11.16f, -1.42f, 1.42f);
        pathBuilderR.curveToRelative(-0.68f, -0.69f, -1.34f, -1.58f, -1.79f, -2.94f);
        pathBuilderR.lineToRelative(1.94f, -0.49f);
        pathBuilderR.curveTo(8.83f, 10.04f, 9.28f, 10.65f, 9.78f, 11.16f);
        a.p(pathBuilderR, 11.0f, 6.0f, 7.0f, 2.0f);
        pathBuilderR.lineTo(3.0f, 6.0f);
        pathBuilderR.horizontalLineToRelative(3.02f);
        pathBuilderR.curveTo(6.04f, 6.81f, 6.1f, 7.54f, 6.21f, 8.17f);
        pathBuilderR.lineToRelative(1.94f, -0.49f);
        pathBuilderR.curveTo(8.08f, 7.2f, 8.03f, 6.63f, 8.02f, 6.0f);
        a.x(pathBuilderR, 11.0f, 21.0f, 6.0f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        pathBuilderR.lineToRelative(-4.0f, 4.0f);
        pathBuilderR.horizontalLineToRelative(2.99f);
        pathBuilderR.curveToRelative(-0.1f, 3.68f, -1.28f, 4.75f, -2.54f, 5.88f);
        pathBuilderR.curveToRelative(-0.5f, 0.44f, -1.01f, 0.92f, -1.45f, 1.55f);
        pathBuilderR.curveToRelative(-0.34f, -0.49f, -0.73f, -0.88f, -1.13f, -1.24f);
        pathBuilderR.lineTo(9.46f, 13.6f);
        pathBuilderR.curveTo(10.39f, 14.45f, 11.0f, 15.14f, 11.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        a.o(pathBuilderR, 0.0f, 5.0f, 2.0f, -5.0f);
        pathBuilderR.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.0f, -2.02f, 0.71f, -2.66f, 1.79f, -3.63f);
        pathBuilderR.curveToRelative(1.38f, -1.24f, 3.08f, -2.78f, 3.2f, -7.37f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderR, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _altRoute = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getAltRoute$annotations(Icons.Filled filled) {
    }
}
