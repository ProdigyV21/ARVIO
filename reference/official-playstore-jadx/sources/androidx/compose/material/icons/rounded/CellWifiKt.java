package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cellWifi", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CellWifi", "Landroidx/compose/material/icons/Icons$Rounded;", "getCellWifi", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CellWifiKt {
    private static ImageVector _cellWifi;

    public static final ImageVector getCellWifi(Icons.Rounded rounded) {
        ImageVector imageVector = _cellWifi;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CellWifi", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.29f, 7.68f, 7.7f, 20.29f);
        pathBuilderJ.curveTo(7.07f, 20.92f, 7.52f, 22.0f, 8.41f, 22.0f);
        pathBuilderJ.horizontalLineTo(21.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineTo(8.39f);
        pathBuilderJ.curveTo(22.0f, 7.5f, 20.92f, 7.05f, 20.29f, 7.68f);
        b.w(pathBuilderJ, 20.0f, 20.0f, -2.0f, -7.22f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderJ, 2.0f, -2.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.61f, 10.68f);
        pathBuilderA.curveToRelative(-0.28f, 0.17f, -0.32f, 0.56f, -0.09f, 0.79f);
        pathBuilderA.lineToRelative(0.82f, 0.82f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.82f, -0.82f);
        pathBuilderA.curveToRelative(0.23f, -0.23f, 0.18f, -0.62f, -0.09f, -0.79f);
        pathBuilderA.curveTo(11.61f, 10.14f, 10.49f, 10.14f, 9.61f, 10.68f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(8.42f, 9.3f);
        pathBuilderA2.curveToRelative(1.57f, -1.12f, 3.7f, -1.12f, 5.27f, 0.0f);
        pathBuilderA2.curveToRelative(0.36f, 0.26f, 0.85f, 0.22f, 1.16f, -0.1f);
        pathBuilderA2.curveToRelative(0.39f, -0.39f, 0.35f, -1.06f, -0.1f, -1.38f);
        pathBuilderA2.curveToRelative(-2.2f, -1.57f, -5.19f, -1.57f, -7.4f, 0.0f);
        pathBuilderA2.curveTo(6.9f, 8.14f, 6.85f, 8.81f, 7.25f, 9.2f);
        pathBuilderA2.curveTo(7.57f, 9.52f, 8.06f, 9.56f, 8.42f, 9.3f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(16.26f, 6.69f);
        pathBuilderA3.curveToRelative(0.34f, 0.28f, 0.83f, 0.28f, 1.14f, -0.03f);
        pathBuilderA3.lineToRelative(0.12f, -0.12f);
        pathBuilderA3.curveToRelative(0.35f, -0.35f, 0.31f, -0.92f, -0.08f, -1.24f);
        pathBuilderA3.curveToRelative(-3.67f, -3.05f, -9.02f, -3.07f, -12.7f, -0.06f);
        pathBuilderA3.curveTo(4.31f, 5.59f, 4.27f, 6.23f, 4.66f, 6.61f);
        pathBuilderA3.curveTo(4.98f, 6.94f, 5.5f, 6.98f, 5.85f, 6.69f);
        pathBuilderA3.curveTo(8.86f, 4.21f, 13.25f, 4.21f, 16.26f, 6.69f);
        pathBuilderA3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cellWifi = imageVectorBuild;
        return imageVectorBuild;
    }
}
