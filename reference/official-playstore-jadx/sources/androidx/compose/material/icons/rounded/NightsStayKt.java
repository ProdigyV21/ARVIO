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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightsStay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightsStay", "Landroidx/compose/material/icons/Icons$Rounded;", "getNightsStay", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightsStayKt {
    private static ImageVector _nightsStay;

    public static final ImageVector getNightsStay(Icons.Rounded rounded) {
        ImageVector imageVector = _nightsStay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NightsStay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.1f, 12.08f);
        pathBuilderA.curveToRelative(-2.0f, -3.88f, -0.92f, -7.36f, 0.07f, -9.27f);
        pathBuilderA.curveToRelative(0.19f, -0.36f, -0.12f, -0.77f, -0.53f, -0.72f);
        pathBuilderA.curveTo(5.62f, 2.77f, 1.78f, 7.16f, 1.99f, 12.41f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.01f, 0.0f, 0.01f, 0.01f);
        pathBuilderA.curveTo(2.62f, 12.15f, 3.29f, 12.0f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.18f, 0.83f, 4.1f, 2.15f);
        pathBuilderA.curveTo(9.77f, 14.63f, 11.0f, 16.17f, 11.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.52f, -0.87f, 2.83f, -2.12f, 3.51f);
        pathBuilderA.curveToRelative(0.98f, 0.32f, 2.03f, 0.5f, 3.11f, 0.5f);
        pathBuilderA.curveToRelative(3.13f, 0.0f, 5.92f, -1.44f, 7.76f, -3.69f);
        pathBuilderA.curveToRelative(0.26f, -0.32f, 0.04f, -0.79f, -0.37f, -0.82f);
        pathBuilderA.curveTo(16.89f, 17.37f, 13.1f, 15.97f, 11.1f, 12.08f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(7.0f, 16.0f, -0.18f, 0.0f);
        pathBuilderR.curveTo(6.4f, 14.84f, 5.3f, 14.0f, 4.0f, 14.0f);
        pathBuilderR.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.curveToRelative(0.62f, 0.0f, 2.49f, 0.0f, 3.0f, 0.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.curveTo(9.0f, 16.9f, 8.1f, 16.0f, 7.0f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightsStay = imageVectorBuild;
        return imageVectorBuild;
    }
}
