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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_whatshot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Whatshot", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWhatshot", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WhatshotKt {
    private static ImageVector _whatshot;

    public static final ImageVector getWhatshot(Icons.TwoTone twoTone) {
        ImageVector imageVector = _whatshot;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Whatshot", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.11f, 6.77f);
        pathBuilderA.curveToRelative(-0.53f, 2.6f, -2.62f, 4.43f, -5.28f, 4.43f);
        pathBuilderA.curveToRelative(-1.56f, 0.0f, -2.96f, -0.62f, -3.97f, -1.63f);
        pathBuilderA.curveTo(6.3f, 10.96f, 6.0f, 12.47f, 6.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderA.curveToRelative(0.0f, -2.56f, -0.66f, -5.03f, -1.89f, -7.23f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.89f, 17.99f);
        pathBuilderA.curveToRelative(-1.37f, 0.0f, -2.49f, -1.08f, -2.49f, -2.42f);
        pathBuilderA.curveToRelative(0.0f, -1.25f, 0.81f, -2.13f, 2.17f, -2.41f);
        pathBuilderA.curveToRelative(1.37f, -0.28f, 2.78f, -0.93f, 3.57f, -1.99f);
        pathBuilderA.curveToRelative(0.3f, 1.0f, 0.46f, 2.05f, 0.46f, 3.12f);
        pathBuilderA.curveToRelative(0.0f, 2.04f, -1.66f, 3.7f, -3.71f, 3.7f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(11.57f, 13.16f);
        pathBuilderA2.curveToRelative(-1.36f, 0.28f, -2.17f, 1.16f, -2.17f, 2.41f);
        pathBuilderA2.curveToRelative(0.0f, 1.34f, 1.11f, 2.42f, 2.49f, 2.42f);
        pathBuilderA2.curveToRelative(2.05f, 0.0f, 3.71f, -1.66f, 3.71f, -3.71f);
        pathBuilderA2.curveToRelative(0.0f, -1.07f, -0.15f, -2.12f, -0.46f, -3.12f);
        pathBuilderA2.curveToRelative(-0.79f, 1.07f, -2.2f, 1.72f, -3.57f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(13.5f, 0.67f);
        pathBuilderA2.reflectiveCurveToRelative(0.74f, 2.65f, 0.74f, 4.8f);
        pathBuilderA2.curveToRelative(0.0f, 2.06f, -1.35f, 3.73f, -3.41f, 3.73f);
        pathBuilderA2.curveToRelative(-2.07f, 0.0f, -3.63f, -1.67f, -3.63f, -3.73f);
        pathBuilderA2.lineToRelative(0.03f, -0.36f);
        pathBuilderA2.curveTo(5.21f, 7.51f, 4.0f, 10.62f, 4.0f, 14.0f);
        pathBuilderA2.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA2.curveTo(20.0f, 8.61f, 17.41f, 3.8f, 13.5f, 0.67f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.53f, 0.3f, -3.04f, 0.86f, -4.43f);
        pathBuilderA2.curveToRelative(1.01f, 1.01f, 2.41f, 1.63f, 3.97f, 1.63f);
        pathBuilderA2.curveToRelative(2.66f, 0.0f, 4.75f, -1.83f, 5.28f, -4.43f);
        pathBuilderA2.curveTo(17.34f, 8.97f, 18.0f, 11.44f, 18.0f, 14.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _whatshot = imageVectorBuild;
        return imageVectorBuild;
    }
}
