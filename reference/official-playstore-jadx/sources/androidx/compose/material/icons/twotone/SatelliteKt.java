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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_satellite", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Satellite", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSatellite", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SatelliteKt {
    private static ImageVector _satellite;

    public static final ImageVector getSatellite(Icons.TwoTone twoTone) {
        ImageVector imageVector = _satellite;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Satellite", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(5.0f, 19.0f, 14.0f, 19.0f, 5.0f);
        d.f(pathBuilderI, 5.0f, 5.0f, 14.0f);
        pathBuilderI.moveTo(6.0f, 6.0f);
        pathBuilderI.horizontalLineToRelative(2.57f);
        pathBuilderI.curveToRelative(0.0f, 1.42f, -1.15f, 2.58f, -2.57f, 2.58f);
        c.z(pathBuilderI, 6.0f, 6.0f, 6.0f, 10.29f);
        pathBuilderI.curveToRelative(2.37f, 0.0f, 4.28f, -1.93f, 4.28f, -4.29f);
        pathBuilderI.lineTo(12.0f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, 3.31f, -2.68f, 6.0f, -6.0f, 6.0f);
        b.f(pathBuilderI, -1.71f, 9.0f, 13.15f);
        pathBuilderI.lineToRelative(2.14f, 2.58f);
        pathBuilderI.lineToRelative(3.0f, -3.86f);
        pathBuilderI.lineTo(18.0f, 17.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderI, 6.0f, 17.0f, 3.0f, -3.85f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 3.0f, 5.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(21.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 19.0f, 19.0f, 5.0f, 19.0f);
        b.g(pathBuilderJ, 5.0f, 5.0f, 14.0f, 14.0f);
        pathBuilderJ.moveTo(8.57f, 6.0f);
        pathBuilderJ.lineTo(6.0f, 6.0f);
        pathBuilderJ.verticalLineToRelative(2.58f);
        pathBuilderJ.curveToRelative(1.42f, 0.0f, 2.57f, -1.16f, 2.57f, -2.58f);
        a.g(pathBuilderJ, 12.0f, 6.0f, -1.72f);
        pathBuilderJ.curveToRelative(0.0f, 2.36f, -1.91f, 4.29f, -4.28f, 4.29f);
        pathBuilderJ.lineTo(6.0f, 12.0f);
        pathBuilderJ.curveToRelative(3.32f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        a.z(pathBuilderJ, 14.14f, 11.86f, -3.0f, 3.87f);
        pathBuilderJ.lineTo(9.0f, 13.15f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderJ, 6.0f, 17.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _satellite = imageVectorBuild;
        return imageVectorBuild;
    }
}
