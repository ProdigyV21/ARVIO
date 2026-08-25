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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cleanHands", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CleanHands", "Landroidx/compose/material/icons/Icons$Sharp;", "getCleanHands", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CleanHandsKt {
    private static ImageVector _cleanHands;

    public static final ImageVector getCleanHands(Icons.Sharp sharp) {
        ImageVector imageVector = _cleanHands;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CleanHands", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.99f, 7.0f, 1.37f, -0.63f);
        pathBuilderR.lineTo(16.99f, 5.0f);
        pathBuilderR.lineToRelative(0.63f, 1.37f);
        pathBuilderR.lineTo(18.99f, 7.0f);
        pathBuilderR.lineToRelative(-1.37f, 0.63f);
        pathBuilderR.lineTo(16.99f, 9.0f);
        d.C(pathBuilderR, -0.63f, -1.37f, 14.99f, 7.0f);
        pathBuilderR.moveTo(20.0f, 14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -2.0f, -4.0f, -2.0f, -4.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.0f, 2.9f, -2.0f, 4.0f);
        pathBuilderR.curveTo(18.0f, 13.1f, 18.9f, 14.0f, 20.0f, 14.0f);
        a.u(pathBuilderR, 1.0f, 22.0f, 4.0f, 11.0f);
        c.l(pathBuilderR, 1.0f, 22.0f, 9.24f, 9.5f);
        pathBuilderR.lineTo(15.0f, 11.65f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.curveToRelative(0.0f, -2.42f, -1.72f, -4.44f, -4.0f, -4.9f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.57f, 0.0f, 1.1f, 0.17f, 1.55f, 0.45f);
        pathBuilderR.lineToRelative(1.43f, -1.43f);
        pathBuilderR.curveTo(15.15f, 2.39f, 14.13f, 2.0f, 13.0f, 2.0f);
        pathBuilderR.curveToRelative(-1.48f, 0.0f, -5.5f, 0.0f, -5.5f, 0.0f);
        y.a.g(pathBuilderR, 2.0f, 9.0f, 2.11f);
        pathBuilderR.curveTo(7.22f, 6.48f, 5.8f, 7.79f, 5.25f, 9.5f);
        w.a.v(pathBuilderR, 9.24f, 22.0f, 17.0f, -9.0f);
        pathBuilderR.lineToRelative(-2.09f, -0.73f);
        pathBuilderR.lineToRelative(0.33f, -0.94f);
        pathBuilderR.lineTo(13.0f, 16.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.lineToRelative(0.0f, -2.0f);
        e.h(pathBuilderR, -8.03f, -3.0f, 7.0f, 9.02f);
        pathBuilderR.lineTo(14.0f, 22.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderR, 8.0f, -3.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cleanHands = imageVectorBuild;
        return imageVectorBuild;
    }
}
