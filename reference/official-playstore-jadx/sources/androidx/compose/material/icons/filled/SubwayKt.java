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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_subway", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Subway", "Landroidx/compose/material/icons/Icons$Filled;", "getSubway", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SubwayKt {
    private static ImageVector _subway;

    public static final ImageVector getSubway(Icons.Filled filled) {
        ImageVector imageVector = _subway;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Subway", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(15.5f, 16.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = a.b(8.5f, 16.0f, -1.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB3 = c.b(7.01f, 9.0f, 10.0f, 5.0f, -10.0f);
        pathBuilderB3.moveTo(17.8f, 2.8f);
        pathBuilderB3.curveTo(16.0f, 2.09f, 13.86f, 2.0f, 12.0f, 2.0f);
        pathBuilderB3.curveToRelative(-1.86f, 0.0f, -4.0f, 0.09f, -5.8f, 0.8f);
        pathBuilderB3.curveTo(3.53f, 3.84f, 2.0f, 6.05f, 2.0f, 8.86f);
        pathBuilderB3.lineTo(2.0f, 22.0f);
        pathBuilderB3.horizontalLineToRelative(20.0f);
        pathBuilderB3.lineTo(22.0f, 8.86f);
        pathBuilderB3.curveToRelative(0.0f, -2.81f, -1.53f, -5.02f, -4.2f, -6.06f);
        pathBuilderB3.close();
        pathBuilderB3.moveTo(18.0f, 15.88f);
        pathBuilderB3.curveToRelative(0.0f, 1.45f, -1.18f, 2.62f, -2.63f, 2.62f);
        pathBuilderB3.lineToRelative(1.13f, 1.12f);
        pathBuilderB3.lineTo(16.5f, 20.0f);
        pathBuilderB3.lineTo(15.0f, 20.0f);
        pathBuilderB3.lineToRelative(-1.5f, -1.5f);
        pathBuilderB3.horizontalLineToRelative(-2.83f);
        pathBuilderB3.lineTo(9.17f, 20.0f);
        pathBuilderB3.lineTo(7.5f, 20.0f);
        pathBuilderB3.verticalLineToRelative(-0.38f);
        pathBuilderB3.lineToRelative(1.12f, -1.12f);
        pathBuilderB3.curveTo(7.18f, 18.5f, 6.0f, 17.32f, 6.0f, 15.88f);
        pathBuilderB3.lineTo(6.0f, 9.0f);
        pathBuilderB3.curveToRelative(0.0f, -2.63f, 3.0f, -3.0f, 6.0f, -3.0f);
        pathBuilderB3.curveToRelative(3.32f, 0.0f, 6.0f, 0.38f, 6.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderB3, 6.88f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _subway = imageVectorBuild;
        return imageVectorBuild;
    }
}
