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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_directionsRun", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsRun", "Landroidx/compose/material/icons/Icons$Sharp;", "getDirectionsRun$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getDirectionsRun", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsRunKt {
    private static ImageVector _directionsRun;

    public static final ImageVector getDirectionsRun(Icons.Sharp sharp) {
        ImageVector imageVector = _directionsRun;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DirectionsRun", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.49f, 5.48f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        a.z(pathBuilderA, 9.89f, 19.38f, 1.0f, -4.4f);
        b.u(pathBuilderA, 2.1f, 2.0f, 6.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-7.5f);
        pathBuilderA.lineToRelative(-2.1f, -2.0f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.3f, 1.5f, 3.3f, 2.5f, 5.5f, 2.5f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-1.9f, 0.0f, -3.5f, -1.0f, -4.3f, -2.4f);
        pathBuilderA.lineToRelative(-1.0f, -1.6f);
        pathBuilderA.curveToRelative(-0.4f, -0.6f, -1.0f, -1.0f, -1.7f, -1.0f);
        pathBuilderA.curveToRelative(-0.3f, 0.0f, -0.5f, 0.1f, -0.8f, 0.1f);
        b.u(pathBuilderA, -5.2f, 2.2f, 4.7f, 2.0f);
        pathBuilderA.verticalLineToRelative(-3.4f);
        pathBuilderA.lineToRelative(1.8f, -0.7f);
        pathBuilderA.lineToRelative(-1.6f, 8.1f);
        pathBuilderA.lineToRelative(-4.9f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -0.4f, 2.0f, 7.0f, 1.4f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsRun = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getDirectionsRun$annotations(Icons.Sharp sharp) {
    }
}
