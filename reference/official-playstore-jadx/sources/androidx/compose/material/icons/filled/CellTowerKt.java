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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cellTower", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CellTower", "Landroidx/compose/material/icons/Icons$Filled;", "getCellTower", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CellTowerKt {
    private static ImageVector _cellTower;

    public static final ImageVector getCellTower(Icons.Filled filled) {
        ImageVector imageVector = _cellTower;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.CellTower", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(7.3f, 14.7f, 1.2f, -1.2f);
        pathBuilderR.curveToRelative(-1.0f, -1.0f, -1.5f, -2.3f, -1.5f, -3.5f);
        pathBuilderR.curveToRelative(0.0f, -1.3f, 0.5f, -2.6f, 1.5f, -3.5f);
        pathBuilderR.lineTo(7.3f, 5.3f);
        pathBuilderR.curveToRelative(-1.3f, 1.3f, -2.0f, 3.0f, -2.0f, 4.7f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 6.0f, 13.4f, 7.3f, 14.7f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(19.1f, 2.9f, -1.2f, 1.2f);
        pathBuilderR2.curveToRelative(1.6f, 1.6f, 2.4f, 3.8f, 2.4f, 5.9f);
        pathBuilderR2.curveToRelative(0.0f, 2.1f, -0.8f, 4.3f, -2.4f, 5.9f);
        pathBuilderR2.lineToRelative(1.2f, 1.2f);
        pathBuilderR2.curveToRelative(2.0f, -2.0f, 2.9f, -4.5f, 2.9f, -7.1f);
        pathBuilderR2.curveTo(22.0f, 7.4f, 21.0f, 4.9f, 19.1f, 2.9f);
        pathBuilderR2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.1f, 4.1f, 4.9f, 2.9f);
        pathBuilderJ.curveTo(3.0f, 4.9f, 2.0f, 7.4f, 2.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.6f, 1.0f, 5.1f, 2.9f, 7.1f);
        pathBuilderJ.lineToRelative(1.2f, -1.2f);
        pathBuilderJ.curveToRelative(-1.6f, -1.6f, -2.4f, -3.8f, -2.4f, -5.9f);
        pathBuilderJ.curveTo(3.7f, 7.9f, 4.5f, 5.7f, 6.1f, 4.1f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.7f, 14.7f);
        pathBuilderA.curveToRelative(1.3f, -1.3f, 2.0f, -3.0f, 2.0f, -4.7f);
        pathBuilderA.curveToRelative(-0.1f, -1.7f, -0.7f, -3.4f, -2.0f, -4.7f);
        pathBuilderA.lineToRelative(-1.2f, 1.2f);
        pathBuilderA.curveToRelative(1.0f, 1.0f, 1.5f, 2.3f, 1.5f, 3.5f);
        pathBuilderA.curveToRelative(0.0f, 1.3f, -0.5f, 2.6f, -1.5f, 3.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 16.7f, 14.7f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(14.5f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderA2.reflectiveCurveTo(9.5f, 8.62f, 9.5f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.76f, 0.34f, 1.42f, 0.87f, 1.88f);
        pathBuilderA2.lineTo(7.0f, 22.0f);
        e.C(pathBuilderA2, 2.0f, 0.67f, -2.0f, 4.67f);
        pathBuilderA2.lineTo(15.0f, 22.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.lineToRelative(-3.37f, -10.12f);
        pathBuilderA2.curveTo(14.16f, 11.42f, 14.5f, 10.76f, 14.5f, 10.0f);
        a.p(pathBuilderA2, 10.33f, 18.0f, 12.0f, 13.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderA2, 1.67f, 5.0f, 10.33f), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cellTower = imageVectorBuild;
        return imageVectorBuild;
    }
}
