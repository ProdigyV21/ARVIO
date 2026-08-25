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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tsunami", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tsunami", "Landroidx/compose/material/icons/Icons$Rounded;", "getTsunami", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TsunamiKt {
    private static ImageVector _tsunami;

    public static final ImageVector getTsunami(Icons.Rounded rounded) {
        ImageVector imageVector = _tsunami;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Tsunami", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.16f, 17.98f);
        pathBuilderA.curveToRelative(-2.76f, 1.76f, -4.67f, 0.77f, -5.61f, 0.08f);
        pathBuilderA.curveToRelative(-0.34f, -0.24f, -0.78f, -0.23f, -1.12f, 0.01f);
        pathBuilderA.curveToRelative(-0.97f, 0.7f, -2.83f, 1.65f, -5.55f, -0.06f);
        pathBuilderA.curveTo(5.55f, 17.8f, 5.13f, 17.78f, 4.81f, 18.0f);
        pathBuilderA.curveToRelative(-0.91f, 0.61f, -1.53f, 0.85f, -2.0f, 0.94f);
        pathBuilderA.curveTo(2.34f, 19.03f, 2.0f, 19.44f, 2.0f, 19.91f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.6f, 0.54f, 1.09f, 1.13f, 0.98f);
        pathBuilderA.curveToRelative(0.77f, -0.14f, 1.51f, -0.42f, 2.2f, -0.83f);
        pathBuilderA.curveToRelative(2.04f, 1.21f, 4.63f, 1.21f, 6.67f, 0.0f);
        pathBuilderA.curveToRelative(2.06f, 1.22f, 4.61f, 1.22f, 6.67f, 0.0f);
        pathBuilderA.curveToRelative(0.69f, 0.41f, 1.44f, 0.69f, 2.21f, 0.83f);
        pathBuilderA.curveToRelative(0.59f, 0.11f, 1.13f, -0.38f, 1.13f, -0.98f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.0f, -0.47f, -0.33f, -0.88f, -0.8f, -0.97f);
        pathBuilderA.curveToRelative(-0.49f, -0.1f, -1.11f, -0.34f, -2.02f, -0.94f);
        pathBuilderA.curveTo(18.88f, 17.79f, 18.47f, 17.78f, 18.16f, 17.98f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.33f, 12.0f, 21.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderQ.lineToRelative(-1.61f, 0.0f);
        pathBuilderQ.curveToRelative(-1.86f, 0.0f, -3.4f, -1.5f, -3.39f, -3.36f);
        pathBuilderQ.curveToRelative(0.0f, -0.37f, 0.06f, -0.7f, 0.16f, -1.05f);
        pathBuilderQ.curveTo(16.53f, 4.3f, 15.6f, 3.03f, 14.27f, 3.0f);
        pathBuilderQ.curveTo(14.18f, 3.0f, 14.09f, 3.0f, 14.0f, 3.0f);
        pathBuilderQ.curveTo(7.36f, 3.0f, 2.15f, 8.03f, 2.01f, 14.5f);
        pathBuilderQ.lineToRelative(0.0f, 0.03f);
        pathBuilderQ.curveToRelative(-0.04f, 1.13f, 1.07f, 1.98f, 2.14f, 1.6f);
        pathBuilderQ.curveToRelative(0.4f, -0.14f, 0.78f, -0.32f, 1.15f, -0.54f);
        pathBuilderQ.curveToRelative(2.08f, 1.2f, 4.64f, 1.22f, 6.7f, -0.02f);
        pathBuilderQ.curveToRelative(2.06f, 1.22f, 4.61f, 1.22f, 6.67f, 0.0f);
        pathBuilderQ.curveToRelative(0.68f, 0.41f, 1.42f, 0.68f, 2.18f, 0.82f);
        pathBuilderQ.curveToRelative(0.6f, 0.11f, 1.16f, -0.36f, 1.16f, -0.98f);
        pathBuilderQ.verticalLineToRelative(-0.01f);
        pathBuilderQ.curveToRelative(0.0f, -0.46f, -0.32f, -0.88f, -0.78f, -0.97f);
        pathBuilderQ.curveToRelative(-0.49f, -0.09f, -1.12f, -0.33f, -2.03f, -0.94f);
        pathBuilderQ.curveToRelative(-0.31f, -0.21f, -0.73f, -0.22f, -1.05f, -0.01f);
        pathBuilderQ.curveToRelative(-2.73f, 1.74f, -4.63f, 0.77f, -5.58f, 0.09f);
        pathBuilderQ.curveToRelative(-0.35f, -0.25f, -0.81f, -0.26f, -1.16f, -0.01f);
        pathBuilderQ.curveToRelative(-0.15f, 0.11f, -0.09f, 0.06f, -0.32f, 0.2f);
        pathBuilderQ.curveTo(10.39f, 12.82f, 10.0f, 11.7f, 10.0f, 10.5f);
        pathBuilderQ.curveToRelative(0.0f, -2.58f, 1.77f, -4.74f, 4.21f, -5.33f);
        pathBuilderQ.curveTo(14.08f, 5.68f, 14.0f, 6.19f, 14.0f, 6.67f);
        pathBuilderQ.curveTo(14.0f, 9.61f, 16.39f, 12.0f, 19.33f, 12.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tsunami = imageVectorBuild;
        return imageVectorBuild;
    }
}
