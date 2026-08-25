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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_modeFanOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ModeFanOff", "Landroidx/compose/material/icons/Icons$Filled;", "getModeFanOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModeFanOffKt {
    private static ImageVector _modeFanOff;

    public static final ImageVector getModeFanOff(Icons.Filled filled) {
        ImageVector imageVector = _modeFanOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ModeFanOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.34f, 8.36f, -2.29f, 0.82f);
        pathBuilderR.curveToRelative(-0.18f, -0.13f, -0.38f, -0.25f, -0.58f, -0.34f);
        pathBuilderR.curveToRelative(0.17f, -0.83f, 0.63f, -1.58f, 1.36f, -2.06f);
        pathBuilderR.curveTo(16.85f, 5.44f, 16.18f, 2.0f, 13.39f, 2.0f);
        pathBuilderR.curveToRelative(-3.08f, 0.0f, -4.9f, 1.47f, -5.3f, 3.26f);
        pathBuilderR.lineTo(18.73f, 15.9f);
        pathBuilderR.curveToRelative(1.5f, 0.39f, 3.27f, -0.51f, 3.27f, -2.51f);
        pathBuilderR.curveTo(22.0f, 9.0f, 18.99f, 7.16f, 16.34f, 8.36f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderJ.lineTo(5.27f, 8.1f);
        pathBuilderJ.curveTo(3.77f, 7.7f, 2.0f, 8.61f, 2.0f, 10.61f);
        pathBuilderJ.curveToRelative(0.0f, 4.4f, 3.01f, 6.24f, 5.66f, 5.03f);
        pathBuilderJ.lineToRelative(2.29f, -0.82f);
        pathBuilderJ.curveToRelative(0.18f, 0.13f, 0.38f, 0.25f, 0.58f, 0.34f);
        pathBuilderJ.curveToRelative(-0.17f, 0.83f, -0.63f, 1.58f, -1.36f, 2.06f);
        pathBuilderJ.curveTo(7.15f, 18.56f, 7.82f, 22.0f, 10.61f, 22.0f);
        pathBuilderJ.curveToRelative(3.08f, 0.0f, 4.9f, -1.47f, 5.3f, -3.26f);
        pathBuilderJ.lineToRelative(3.87f, 3.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 1.41f, -1.41f, 2.81f, 2.81f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _modeFanOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
