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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_biotech", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Biotech", "Landroidx/compose/material/icons/Icons$Filled;", "getBiotech", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BiotechKt {
    private static ImageVector _biotech;

    public static final ImageVector getBiotech(Icons.Filled filled) {
        ImageVector imageVector = _biotech;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Biotech", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.0f, 19.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        e.g(pathBuilderA, -4.0f, -2.0f, 3.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-8.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.09f, 0.59f, -2.04f, 1.46f, -2.56f);
        pathBuilderA.curveTo(8.17f, 9.03f, 8.0f, 8.54f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.21f, 0.04f, -0.42f, 0.09f, -0.62f);
        pathBuilderA.curveTo(6.28f, 8.13f, 5.0f, 9.92f, 5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderA, 2.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(10.56f, 5.51f);
        pathBuilderA2.curveTo(11.91f, 5.54f, 13.0f, 6.64f, 13.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.75f, -0.33f, 1.41f, -0.85f, 1.87f);
        pathBuilderA2.lineToRelative(0.59f, 1.62f);
        pathBuilderA2.lineToRelative(0.94f, -0.34f);
        pathBuilderA2.lineToRelative(0.34f, 0.94f);
        pathBuilderA2.lineToRelative(1.88f, -0.68f);
        pathBuilderA2.lineToRelative(-0.34f, -0.94f);
        pathBuilderA2.lineToRelative(0.94f, -0.34f);
        pathBuilderA2.lineTo(13.76f, 2.6f);
        pathBuilderA2.lineToRelative(-0.94f, 0.34f);
        pathBuilderA2.lineTo(12.48f, 2.0f);
        pathBuilderA2.lineTo(10.6f, 2.68f);
        pathBuilderA2.lineToRelative(0.34f, 0.94f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderA2, 10.0f, 3.97f, 10.56f, 5.51f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = v.a.b(10.5f, 8.0f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _biotech = imageVectorBuild;
        return imageVectorBuild;
    }
}
