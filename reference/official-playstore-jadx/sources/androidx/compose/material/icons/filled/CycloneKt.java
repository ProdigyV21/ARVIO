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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cyclone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cyclone", "Landroidx/compose/material/icons/Icons$Filled;", "getCyclone", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CycloneKt {
    private static ImageVector _cyclone;

    public static final ImageVector getCyclone(Icons.Filled filled) {
        ImageVector imageVector = _cyclone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Cyclone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 8.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveTo(16.0f, 9.79f, 14.21f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 14.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveTo(14.0f, 13.1f, 13.1f, 14.0f, 12.0f, 14.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = e.a(22.0f, 7.47f, 5.35f);
        pathBuilderA2.curveTo(20.05f, 4.77f, 16.56f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.curveTo(9.85f, 4.0f, 7.89f, 4.86f, 6.46f, 6.24f);
        pathBuilderA2.curveTo(6.59f, 5.39f, 6.86f, 3.84f, 7.47f, 2.0f);
        pathBuilderA2.horizontalLineTo(5.35f);
        pathBuilderA2.curveTo(4.77f, 3.95f, 4.0f, 7.44f, 4.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.15f, 0.86f, 4.11f, 2.24f, 5.54f);
        pathBuilderA2.curveToRelative(-0.85f, -0.14f, -2.4f, -0.4f, -4.24f, -1.01f);
        pathBuilderA2.verticalLineToRelative(2.12f);
        pathBuilderA2.curveTo(3.95f, 19.23f, 7.44f, 20.0f, 12.0f, 20.0f);
        pathBuilderA2.curveToRelative(2.15f, 0.0f, 4.11f, -0.86f, 5.54f, -2.24f);
        pathBuilderA2.curveToRelative(-0.14f, 0.85f, -0.4f, 2.4f, -1.01f, 4.24f);
        pathBuilderA2.horizontalLineToRelative(2.12f);
        pathBuilderA2.curveTo(19.23f, 20.05f, 20.0f, 16.56f, 20.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.15f, -0.86f, -4.11f, -2.24f, -5.54f);
        pathBuilderA2.curveTo(18.61f, 6.59f, 20.16f, 6.86f, 22.0f, 7.47f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 18.0f);
        pathBuilderA2.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA2.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 15.31f, 18.0f, 12.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cyclone = imageVectorBuild;
        return imageVectorBuild;
    }
}
