package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insights", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Insights", "Landroidx/compose/material/icons/Icons$Sharp;", "getInsights", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InsightsKt {
    private static ImageVector _insights;

    public static final ImageVector getInsights(Icons.Sharp sharp) {
        ImageVector imageVector = _insights;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Insights", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 8.0f);
        pathBuilderA.curveToRelative(-1.45f, 0.0f, -2.26f, 1.44f, -1.93f, 2.51f);
        pathBuilderA.lineToRelative(-3.55f, 3.56f);
        pathBuilderA.curveToRelative(-0.3f, -0.09f, -0.74f, -0.09f, -1.04f, 0.0f);
        pathBuilderA.lineToRelative(-2.55f, -2.55f);
        pathBuilderA.curveTo(12.27f, 10.45f, 11.46f, 9.0f, 10.0f, 9.0f);
        pathBuilderA.curveToRelative(-1.45f, 0.0f, -2.27f, 1.44f, -1.93f, 2.52f);
        pathBuilderA.lineToRelative(-4.56f, 4.55f);
        pathBuilderA.curveTo(2.44f, 15.74f, 1.0f, 16.55f, 1.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(1.45f, 0.0f, 2.26f, -1.44f, 1.93f, -2.51f);
        pathBuilderA.lineToRelative(4.55f, -4.56f);
        pathBuilderA.curveToRelative(0.3f, 0.09f, 0.74f, 0.09f, 1.04f, 0.0f);
        pathBuilderA.lineToRelative(2.55f, 2.55f);
        pathBuilderA.curveTo(12.73f, 16.55f, 13.54f, 18.0f, 15.0f, 18.0f);
        pathBuilderA.curveToRelative(1.45f, 0.0f, 2.27f, -1.44f, 1.93f, -2.52f);
        pathBuilderA.lineToRelative(3.56f, -3.55f);
        pathBuilderA.curveTo(21.56f, 12.26f, 23.0f, 11.45f, 23.0f, 10.0f);
        pathBuilderA.curveTo(23.0f, 8.9f, 22.1f, 8.0f, 21.0f, 8.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.0f, 9.0f, 0.94f, -2.07f);
        pathBuilderR.lineToRelative(2.06f, -0.93f);
        pathBuilderR.lineToRelative(-2.06f, -0.93f);
        pathBuilderR.lineToRelative(-0.94f, -2.07f);
        pathBuilderR.lineToRelative(-0.92f, 2.07f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -2.08f, 0.93f, 2.08f, 0.93f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(3.5f, 11.0f, 0.5f, -2.0f);
        pathBuilderR2.lineToRelative(2.0f, -0.5f);
        pathBuilderR2.lineToRelative(-2.0f, -0.5f);
        pathBuilderR2.lineToRelative(-0.5f, -2.0f);
        pathBuilderR2.lineToRelative(-0.5f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, -2.0f, 0.5f, 2.0f, 0.5f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _insights = imageVectorBuild;
        return imageVectorBuild;
    }
}
