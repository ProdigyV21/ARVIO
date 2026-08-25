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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_watchOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WatchOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getWatchOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WatchOffKt {
    private static ImageVector _watchOff;

    public static final ImageVector getWatchOff(Icons.Rounded rounded) {
        ImageVector imageVector = _watchOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WatchOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 7.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.64f, -0.13f, 1.25f, -0.35f, 1.82f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.curveTo(18.69f, 14.33f, 19.0f, 13.2f, 19.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.22f, -1.03f, -4.19f, -2.64f, -5.47f);
        pathBuilderA.lineToRelative(-0.93f, -3.1f);
        pathBuilderA.curveTo(15.17f, 2.58f, 14.4f, 2.0f, 13.51f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-3.02f);
        pathBuilderA.curveTo(9.6f, 2.0f, 8.83f, 2.58f, 8.57f, 3.42f);
        pathBuilderA.lineTo(8.04f, 5.21f);
        pathBuilderA.lineToRelative(2.14f, 2.14f);
        pathBuilderA.curveTo(10.75f, 7.13f, 11.36f, 7.0f, 12.0f, 7.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.1f, 3.51f, 2.1f, 3.51f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(3.75f, 3.75f);
        pathBuilderJ.curveTo(5.31f, 9.67f, 5.0f, 10.8f, 5.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.22f, 1.03f, 4.19f, 2.64f, 5.47f);
        pathBuilderJ.lineToRelative(0.93f, 3.1f);
        pathBuilderJ.curveTo(8.83f, 21.42f, 9.6f, 22.0f, 10.49f, 22.0f);
        pathBuilderJ.horizontalLineToRelative(3.02f);
        pathBuilderJ.curveToRelative(0.88f, 0.0f, 1.66f, -0.58f, 1.92f, -1.43f);
        pathBuilderJ.lineToRelative(0.53f, -1.78f);
        pathBuilderJ.lineToRelative(3.11f, 3.11f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(3.51f, 3.51f);
        pathBuilderJ.curveTo(3.12f, 3.12f, 2.49f, 3.12f, 2.1f, 3.51f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 17.0f);
        pathBuilderJ.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.64f, 0.13f, -1.25f, 0.35f, -1.82f);
        pathBuilderJ.lineToRelative(6.47f, 6.47f);
        pathBuilderJ.curveTo(13.25f, 16.87f, 12.64f, 17.0f, 12.0f, 17.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _watchOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
