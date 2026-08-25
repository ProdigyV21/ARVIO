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
import v.a;
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bedtimeOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BedtimeOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getBedtimeOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BedtimeOffKt {
    private static ImageVector _bedtimeOff;

    public static final ImageVector getBedtimeOff(Icons.Outlined outlined) {
        ImageVector imageVector = _bedtimeOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.BedtimeOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.27f, 4.49f);
        pathBuilderA.curveTo(9.14f, 5.08f, 9.07f, 5.64f, 9.03f, 6.2f);
        pathBuilderA.lineToRelative(2.05f, 2.05f);
        pathBuilderA.curveToRelative(-0.27f, -2.05f, 0.1f, -4.22f, 1.26f, -6.23f);
        pathBuilderA.curveToRelative(-0.12f, 0.0f, -0.23f, -0.01f, -0.35f, -0.01f);
        pathBuilderA.curveToRelative(-2.05f, 0.0f, -3.93f, 0.61f, -5.5f, 1.65f);
        pathBuilderA.lineToRelative(1.46f, 1.46f);
        pathBuilderA.curveTo(8.37f, 4.88f, 8.81f, 4.66f, 9.27f, 4.49f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(1.39f, 4.22f, 2.27f, 2.27f);
        pathBuilderR.curveTo(2.61f, 8.07f, 2.0f, 9.97f, 2.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderR.curveToRelative(2.04f, 0.0f, 3.92f, -0.63f, 5.5f, -1.67f);
        pathBuilderR.lineToRelative(2.28f, 2.28f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderR.moveTo(5.13f, 7.96f);
        pathBuilderR.lineToRelative(10.92f, 10.92f);
        pathBuilderR.curveTo(14.84f, 19.6f, 13.45f, 20.0f, 12.0f, 20.0f);
        pathBuilderR.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderR.curveTo(4.0f, 10.52f, 4.42f, 9.15f, 5.13f, 7.96f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bedtimeOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
