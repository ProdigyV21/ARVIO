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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_upcoming", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Upcoming", "Landroidx/compose/material/icons/Icons$TwoTone;", "getUpcoming", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UpcomingKt {
    private static ImageVector _upcoming;

    public static final ImageVector getUpcoming(Icons.TwoTone twoTone) {
        ImageVector imageVector = _upcoming;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Upcoming", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 17.0f);
        pathBuilderA.curveToRelative(-2.04f, 0.0f, -3.81f, -1.24f, -4.58f, -3.0f);
        b.q(pathBuilderA, 4.0f, 5.0f, 16.0f, -5.0f);
        pathBuilderA.horizontalLineToRelative(-3.42f);
        pathBuilderA.curveTo(15.81f, 15.76f, 14.04f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.16f, 7.26f, -1.41f, -1.41f);
        pathBuilderR.lineTo(16.19f, 9.4f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.curveTo(17.6f, 10.81f, 21.05f, 7.29f, 21.16f, 7.26f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(11.0f, 3.0f, 2.0f, 5.0f, -2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(20.0f, 12.0f, -5.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA2.horizontalLineTo(4.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA2.verticalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(16.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.verticalLineToRelative(-5.0f);
        pathBuilderA2.curveTo(22.0f, 12.9f, 21.1f, 12.0f, 20.0f, 12.0f);
        c.C(pathBuilderA2, 20.0f, 19.0f, 4.0f, -5.0f);
        pathBuilderA2.horizontalLineToRelative(3.42f);
        pathBuilderA2.curveToRelative(0.77f, 1.76f, 2.54f, 3.0f, 4.58f, 3.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.81f, -1.24f, 4.58f, -3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderA2, 20.0f, 19.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.4f, 10.81f, 7.81f, 9.4f);
        pathBuilderJ.lineTo(4.26f, 5.84f);
        pathBuilderJ.lineTo(2.84f, 7.26f);
        pathBuilderJ.curveTo(2.95f, 7.29f, 6.4f, 10.81f, 6.4f, 10.81f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _upcoming = imageVectorBuild;
        return imageVectorBuild;
    }
}
