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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPlay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPlay", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalPlay", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPlayKt {
    private static ImageVector _localPlay;

    public static final ImageVector getLocalPlay(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localPlay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalPlay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.01f, 8.54f);
        pathBuilderA.curveTo(5.2f, 9.23f, 6.0f, 10.52f, 6.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.81f, 2.77f, -2.0f, 3.46f);
        b.A(pathBuilderA, 4.0f, 18.0f, 16.0f, -2.54f);
        pathBuilderA.curveToRelative(-1.19f, -0.69f, -2.0f, -1.99f, -2.0f, -3.46f);
        pathBuilderA.reflectiveCurveToRelative(0.81f, -2.77f, 2.0f, -3.46f);
        pathBuilderA.lineTo(20.0f, 6.0f);
        d.l(pathBuilderA, 4.0f, 6.0f, 0.01f, 2.54f);
        pathBuilderA.moveTo(10.73f, 10.22f);
        pathBuilderA.lineTo(12.0f, 7.0f);
        pathBuilderA.lineToRelative(1.26f, 3.23f);
        pathBuilderA.lineToRelative(3.47f, 0.2f);
        pathBuilderA.lineToRelative(-2.69f, 2.2f);
        pathBuilderA.lineToRelative(0.89f, 3.37f);
        pathBuilderA.lineTo(12.0f, 14.12f);
        pathBuilderA.lineTo(9.07f, 16.0f);
        pathBuilderA.lineToRelative(0.88f, -3.37f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -2.69f, -2.2f, 3.47f, -0.21f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 4.0f, 4.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 1.99f, 0.9f, 1.99f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.verticalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.0f, 8.54f);
        pathBuilderJ.curveToRelative(-1.19f, 0.69f, -2.0f, 1.99f, -2.0f, 3.46f);
        pathBuilderJ.reflectiveCurveToRelative(0.81f, 2.77f, 2.0f, 3.46f);
        pathBuilderJ.lineTo(20.0f, 18.0f);
        pathBuilderJ.lineTo(4.0f, 18.0f);
        pathBuilderJ.verticalLineToRelative(-2.54f);
        pathBuilderJ.curveToRelative(1.19f, -0.69f, 2.0f, -1.99f, 2.0f, -3.46f);
        pathBuilderJ.curveToRelative(0.0f, -1.48f, -0.8f, -2.77f, -1.99f, -3.46f);
        b.g(pathBuilderJ, 4.0f, 6.0f, 16.0f, 2.54f);
        pathBuilderJ.moveTo(9.07f, 16.0f);
        pathBuilderJ.lineTo(12.0f, 14.12f);
        pathBuilderJ.lineTo(14.93f, 16.0f);
        pathBuilderJ.lineToRelative(-0.89f, -3.36f);
        pathBuilderJ.lineToRelative(2.69f, -2.2f);
        pathBuilderJ.lineToRelative(-3.47f, -0.21f);
        pathBuilderJ.lineTo(12.0f, 7.0f);
        pathBuilderJ.lineToRelative(-1.27f, 3.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, -3.47f, 0.21f, 2.69f, 2.2f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localPlay = imageVectorBuild;
        return imageVectorBuild;
    }
}
