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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noAdultContent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoAdultContent", "Landroidx/compose/material/icons/Icons$Rounded;", "getNoAdultContent", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoAdultContentKt {
    private static ImageVector _noAdultContent;

    public static final ImageVector getNoAdultContent(Icons.Rounded rounded) {
        ImageVector imageVector = _noAdultContent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NoAdultContent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.85f, 0.63f, -3.54f, 1.69f, -4.9f);
        pathBuilderA.lineTo(7.59f, 9.0f);
        pathBuilderA.horizontalLineToRelative(2.83f);
        pathBuilderA.lineTo(7.1f, 5.69f);
        pathBuilderA.curveTo(8.46f, 4.63f, 10.15f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.85f, -0.63f, 3.54f, -1.69f, 4.9f);
        pathBuilderA.lineToRelative(-1.9f, -1.9f);
        pathBuilderA.horizontalLineToRelative(-2.83f);
        pathBuilderA.lineToRelative(3.31f, 3.31f);
        pathBuilderA.curveTo(15.54f, 19.37f, 13.85f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.curveTo(7.59f, 20.0f, 4.0f, 16.41f, 4.0f, 12.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.25f, 14.0f, -1.5f, -2.0f);
        pathBuilderR.lineToRelative(1.5f, -2.0f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(-0.75f, 1.0f);
        pathBuilderR.lineToRelative(-0.75f, -1.0f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(1.5f, 2.0f);
        pathBuilderR.lineToRelative(-1.5f, 2.0f);
        pathBuilderR.lineToRelative(1.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.75f, -1.0f, 0.75f, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(8.0f, 10.0f, -0.75f, 1.0f);
        pathBuilderR2.lineToRelative(-0.75f, -1.0f);
        pathBuilderR2.lineToRelative(-1.5f, 0.0f);
        pathBuilderR2.lineToRelative(1.5f, 2.0f);
        pathBuilderR2.lineToRelative(-1.5f, 2.0f);
        pathBuilderR2.lineToRelative(1.5f, 0.0f);
        pathBuilderR2.lineToRelative(0.75f, -1.0f);
        pathBuilderR2.lineToRelative(0.75f, 1.0f);
        pathBuilderR2.lineToRelative(1.5f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, -1.5f, -2.0f, 1.5f, -2.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR3 = a.r(16.0f, 14.0f, 0.75f, -1.0f);
        pathBuilderR3.lineToRelative(0.75f, 1.0f);
        pathBuilderR3.lineToRelative(1.5f, 0.0f);
        pathBuilderR3.lineToRelative(-1.5f, -2.0f);
        pathBuilderR3.lineToRelative(1.5f, -2.0f);
        pathBuilderR3.lineToRelative(-1.5f, 0.0f);
        pathBuilderR3.lineToRelative(-0.75f, 1.0f);
        pathBuilderR3.lineToRelative(-0.75f, -1.0f);
        pathBuilderR3.lineToRelative(-1.5f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR3, 1.5f, 2.0f, -1.5f, 2.0f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noAdultContent = imageVectorBuild;
        return imageVectorBuild;
    }
}
