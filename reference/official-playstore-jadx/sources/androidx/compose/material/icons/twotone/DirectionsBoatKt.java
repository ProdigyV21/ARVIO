package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBoat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBoat", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDirectionsBoat", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBoatKt {
    private static ImageVector _directionsBoat;

    public static final ImageVector getDirectionsBoat(Icons.TwoTone twoTone) {
        ImageVector imageVector = _directionsBoat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DirectionsBoat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.49f, 15.68f, 8.0f, 13.96f);
        pathBuilderJ.lineToRelative(1.51f, 1.72f);
        pathBuilderJ.curveToRelative(0.34f, 0.4f, 1.28f, 1.32f, 2.49f, 1.32f);
        pathBuilderJ.curveToRelative(1.21f, 0.0f, 2.15f, -0.92f, 2.49f, -1.32f);
        pathBuilderJ.lineTo(16.0f, 13.96f);
        pathBuilderJ.lineToRelative(1.51f, 1.72f);
        pathBuilderJ.curveToRelative(0.2f, 0.23f, 0.6f, 0.64f, 1.14f, 0.94f);
        pathBuilderJ.lineToRelative(1.12f, -3.97f);
        pathBuilderJ.lineToRelative(-2.39f, -0.78f);
        pathBuilderJ.lineTo(12.0f, 10.11f);
        pathBuilderJ.lineToRelative(-5.38f, 1.77f);
        pathBuilderJ.lineToRelative(-2.4f, 0.79f);
        pathBuilderJ.lineToRelative(1.13f, 3.96f);
        pathBuilderJ.curveToRelative(0.55f, -0.31f, 0.94f, -0.72f, 1.14f, -0.95f);
        b.w(pathBuilderJ, 11.0f, 3.0f, 2.0f, 1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(3.95f, 19.0f, 4.0f, 19.0f);
        pathBuilderJ2.curveToRelative(1.6f, 0.0f, 3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderJ2.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderJ2.reflectiveCurveToRelative(3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderJ2.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderJ2.horizontalLineToRelative(0.05f);
        pathBuilderJ2.lineToRelative(1.89f, -6.68f);
        pathBuilderJ2.curveToRelative(0.08f, -0.26f, 0.06f, -0.54f, -0.06f, -0.78f);
        pathBuilderJ2.reflectiveCurveToRelative(-0.34f, -0.42f, -0.6f, -0.5f);
        pathBuilderJ2.lineTo(20.0f, 10.62f);
        pathBuilderJ2.lineTo(20.0f, 6.0f);
        pathBuilderJ2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ2.horizontalLineToRelative(-3.0f);
        pathBuilderJ2.lineTo(15.0f, 1.0f);
        pathBuilderJ2.lineTo(9.0f, 1.0f);
        pathBuilderJ2.verticalLineToRelative(3.0f);
        pathBuilderJ2.lineTo(6.0f, 4.0f);
        pathBuilderJ2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ2.verticalLineToRelative(4.62f);
        pathBuilderJ2.lineToRelative(-1.29f, 0.42f);
        pathBuilderJ2.curveToRelative(-0.26f, 0.08f, -0.48f, 0.26f, -0.6f, 0.5f);
        pathBuilderJ2.reflectiveCurveToRelative(-0.15f, 0.52f, -0.06f, 0.78f);
        c.z(pathBuilderJ2, 3.95f, 19.0f, 11.0f, 3.0f);
        e.g(pathBuilderJ2, 2.0f, 1.0f, -2.0f);
        c.z(pathBuilderJ2, 11.0f, 3.0f, 6.0f, 6.0f);
        c.q(pathBuilderJ2, 12.0f, 3.97f, 12.0f, 8.0f);
        b.y(pathBuilderJ2, 6.0f, 9.97f, 6.0f, 6.0f);
        pathBuilderJ2.moveTo(6.62f, 11.87f);
        pathBuilderJ2.lineTo(12.0f, 10.11f);
        pathBuilderJ2.lineToRelative(5.38f, 1.77f);
        pathBuilderJ2.lineToRelative(2.39f, 0.78f);
        pathBuilderJ2.lineToRelative(-1.12f, 3.97f);
        pathBuilderJ2.curveToRelative(-0.54f, -0.3f, -0.94f, -0.71f, -1.14f, -0.94f);
        pathBuilderJ2.lineTo(16.0f, 13.96f);
        pathBuilderJ2.lineToRelative(-1.51f, 1.72f);
        pathBuilderJ2.curveToRelative(-0.34f, 0.4f, -1.28f, 1.32f, -2.49f, 1.32f);
        pathBuilderJ2.curveToRelative(-1.21f, 0.0f, -2.15f, -0.92f, -2.49f, -1.32f);
        pathBuilderJ2.lineTo(8.0f, 13.96f);
        pathBuilderJ2.lineToRelative(-1.51f, 1.72f);
        pathBuilderJ2.curveToRelative(-0.2f, 0.23f, -0.6f, 0.63f, -1.14f, 0.93f);
        b.D(pathBuilderJ2, -1.13f, -3.96f, 2.4f, -0.78f);
        pathBuilderJ2.moveTo(8.0f, 22.01f);
        pathBuilderJ2.curveToRelative(1.26f, 0.64f, 2.63f, 0.97f, 4.0f, 0.97f);
        pathBuilderJ2.reflectiveCurveToRelative(2.74f, -0.32f, 4.0f, -0.97f);
        pathBuilderJ2.curveToRelative(1.26f, 0.65f, 2.62f, 0.99f, 4.0f, 0.99f);
        e.g(pathBuilderJ2, 2.0f, -2.0f, -2.0f);
        pathBuilderJ2.curveToRelative(-1.39f, 0.0f, -2.78f, -0.47f, -4.0f, -1.32f);
        pathBuilderJ2.curveToRelative(-1.22f, 0.85f, -2.61f, 1.28f, -4.0f, 1.28f);
        pathBuilderJ2.reflectiveCurveToRelative(-2.78f, -0.43f, -4.0f, -1.28f);
        pathBuilderJ2.curveTo(6.78f, 20.53f, 5.39f, 21.0f, 4.0f, 21.0f);
        a.h(pathBuilderJ2, 2.0f, 21.0f, 2.0f, 2.0f);
        pathBuilderJ2.curveToRelative(1.38f, 0.0f, 2.74f, -0.35f, 4.0f, -0.99f);
        pathBuilderJ2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBoat = imageVectorBuild;
        return imageVectorBuild;
    }
}
