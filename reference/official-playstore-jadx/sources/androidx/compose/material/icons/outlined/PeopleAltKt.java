package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_peopleAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PeopleAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getPeopleAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PeopleAltKt {
    private static ImageVector _peopleAlt;

    public static final ImageVector getPeopleAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _peopleAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PeopleAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.67f, 13.13f);
        pathBuilderA.curveTo(18.04f, 14.06f, 19.0f, 15.32f, 19.0f, 17.0f);
        e.l(pathBuilderA, 3.0f, 4.0f, -3.0f);
        pathBuilderA.curveTo(23.0f, 14.82f, 19.43f, 13.53f, 16.67f, 13.13f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(15.0f, 12.0f);
        pathBuilderA2.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA2.curveToRelative(-0.47f, 0.0f, -0.91f, 0.1f, -1.33f, 0.24f);
        pathBuilderA2.curveTo(14.5f, 5.27f, 15.0f, 6.58f, 15.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(-0.5f, 2.73f, -1.33f, 3.76f);
        pathBuilderA2.curveTo(14.09f, 11.9f, 14.53f, 12.0f, 15.0f, 12.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(9.0f, 12.0f);
        pathBuilderA3.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA3.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA3.reflectiveCurveTo(5.0f, 5.79f, 5.0f, 8.0f);
        pathBuilderA3.curveTo(5.0f, 10.21f, 6.79f, 12.0f, 9.0f, 12.0f);
        pathBuilderA3.close();
        pathBuilderA3.moveTo(9.0f, 6.0f);
        pathBuilderA3.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA3.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA3.reflectiveCurveTo(7.0f, 9.1f, 7.0f, 8.0f);
        pathBuilderA3.curveTo(7.0f, 6.9f, 7.9f, 6.0f, 9.0f, 6.0f);
        pathBuilderA3.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA4 = c.a(9.0f, 13.0f);
        pathBuilderA4.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        e.l(pathBuilderA4, 3.0f, 16.0f, -3.0f);
        pathBuilderA4.curveTo(17.0f, 14.34f, 11.67f, 13.0f, 9.0f, 13.0f);
        a.r(pathBuilderA4, 15.0f, 18.0f, 3.0f);
        pathBuilderA4.lineToRelative(0.0f, -0.99f);
        pathBuilderA4.curveTo(3.2f, 16.29f, 6.3f, 15.0f, 9.0f, 15.0f);
        pathBuilderA4.reflectiveCurveToRelative(5.8f, 1.29f, 6.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA4, 18.0f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _peopleAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
