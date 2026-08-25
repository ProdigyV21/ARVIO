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
import v.c;
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rocket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rocket", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRocket", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RocketKt {
    private static ImageVector _rocket;

    public static final ImageVector getRocket(Icons.TwoTone twoTone) {
        ImageVector imageVector = _rocket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Rocket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.98f, 18.25f);
        pathBuilderA.curveToRelative(-0.29f, -0.9f, -0.57f, -1.94f, -0.76f, -3.0f);
        pathBuilderA.lineTo(6.0f, 16.07f);
        a.n(pathBuilderA, 2.98f, 7.98f, 18.25f);
        pathBuilderA.moveTo(12.0f, 4.36f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, -3.0f, 2.02f, -3.0f, 8.64f);
        pathBuilderA.curveToRelative(0.0f, 2.25f, 1.0f, 5.0f, 1.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 1.0f, -2.75f, 1.0f, -5.0f);
        pathBuilderA.curveTo(15.0f, 6.38f, 12.0f, 4.36f, 12.0f, 4.36f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 13.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveTo(14.0f, 12.1f, 13.1f, 13.0f, 12.0f, 13.0f);
        d.k(pathBuilderA, 18.0f, 19.05f, -2.98f);
        pathBuilderA.lineToRelative(-1.22f, -0.81f);
        pathBuilderA.curveToRelative(-0.19f, 1.05f, -0.47f, 2.1f, -0.76f, 3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 18.0f, 19.05f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(14.0f, 11.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.reflectiveCurveTo(14.0f, 12.1f, 14.0f, 11.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(7.98f, 18.25f);
        pathBuilderA2.curveToRelative(-0.29f, -0.9f, -0.57f, -1.94f, -0.76f, -3.0f);
        pathBuilderA2.lineTo(6.0f, 16.07f);
        a.n(pathBuilderA2, 2.98f, 7.98f, 18.25f);
        pathBuilderA2.moveTo(12.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, 5.0f, 2.0f, 5.0f, 11.0f);
        pathBuilderA2.lineToRelative(2.11f, 1.41f);
        pathBuilderA2.curveToRelative(0.56f, 0.37f, 0.89f, 1.0f, 0.89f, 1.66f);
        pathBuilderA2.verticalLineTo(22.0f);
        pathBuilderA2.lineToRelative(-5.0f, -2.0f);
        pathBuilderA2.horizontalLineTo(9.0f);
        pathBuilderA2.lineToRelative(-5.0f, 2.0f);
        pathBuilderA2.verticalLineToRelative(-5.93f);
        pathBuilderA2.curveToRelative(0.0f, -0.67f, 0.33f, -1.29f, 0.89f, -1.66f);
        pathBuilderA2.lineTo(7.0f, 13.0f);
        pathBuilderA2.curveTo(7.0f, 4.0f, 12.0f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 4.36f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, -3.0f, 2.02f, -3.0f, 8.64f);
        pathBuilderA2.curveToRelative(0.0f, 2.25f, 1.0f, 5.0f, 1.0f, 5.0f);
        pathBuilderA2.horizontalLineToRelative(4.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, 1.0f, -2.75f, 1.0f, -5.0f);
        pathBuilderA2.curveTo(15.0f, 6.38f, 12.0f, 4.36f, 12.0f, 4.36f);
        d.k(pathBuilderA2, 18.0f, 19.05f, -2.98f);
        pathBuilderA2.lineToRelative(-1.22f, -0.81f);
        pathBuilderA2.curveToRelative(-0.19f, 1.05f, -0.47f, 2.1f, -0.76f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA2, 18.0f, 19.05f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rocket = imageVectorBuild;
        return imageVectorBuild;
    }
}
