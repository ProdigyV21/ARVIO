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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_exploreOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExploreOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getExploreOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExploreOffKt {
    private static ImageVector _exploreOff;

    public static final ImageVector getExploreOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _exploreOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ExploreOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 20.0f);
        pathBuilderA.curveToRelative(1.48f, 0.0f, 2.86f, -0.41f, 4.06f, -1.12f);
        pathBuilderA.lineToRelative(-3.98f, -3.98f);
        pathBuilderA.lineToRelative(-5.58f, 2.6f);
        pathBuilderA.lineToRelative(2.59f, -5.58f);
        pathBuilderA.lineToRelative(-3.97f, -3.98f);
        pathBuilderA.curveTo(4.41f, 9.14f, 4.0f, 10.52f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.48f, 0.0f, -2.86f, 0.41f, -4.06f, 1.12f);
        pathBuilderA.lineToRelative(3.98f, 3.98f);
        pathBuilderA.lineToRelative(5.58f, -2.6f);
        pathBuilderA.lineToRelative(-2.59f, 5.58f);
        pathBuilderA.lineToRelative(3.98f, 3.98f);
        pathBuilderA.curveToRelative(0.7f, -1.2f, 1.11f, -2.58f, 1.11f, -4.06f);
        pathBuilderA.curveToRelative(0.0f, -4.41f, -3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.5f, 6.5f, -5.58f, 2.59f);
        b.C(pathBuilderR, 2.99f, 2.99f, 2.1f, 4.93f);
        pathBuilderR.lineToRelative(1.56f, 1.56f);
        pathBuilderR.curveTo(2.61f, 8.07f, 2.0f, 9.96f, 2.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderR.curveToRelative(2.04f, 0.0f, 3.93f, -0.61f, 5.51f, -1.66f);
        pathBuilderR.lineToRelative(1.56f, 1.56f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 3.51f, 3.51f, 2.1f, 4.93f);
        pathBuilderR.moveTo(5.12f, 7.94f);
        pathBuilderR.lineToRelative(3.98f, 3.98f);
        pathBuilderR.lineToRelative(-2.6f, 5.58f);
        pathBuilderR.lineToRelative(5.58f, -2.59f);
        pathBuilderR.lineToRelative(3.98f, 3.98f);
        pathBuilderR.curveToRelative(-1.2f, 0.7f, -2.58f, 1.11f, -4.06f, 1.11f);
        pathBuilderR.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderR.curveToRelative(0.0f, -1.48f, 0.41f, -2.86f, 1.12f, -4.06f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 4.0f);
        pathBuilderR.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderR.curveToRelative(0.0f, 1.48f, -0.41f, 2.86f, -1.12f, 4.06f);
        pathBuilderR.lineToRelative(1.46f, 1.46f);
        pathBuilderR.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderR.curveToRelative(-2.04f, 0.0f, -3.93f, 0.61f, -5.51f, 1.66f);
        pathBuilderR.lineToRelative(1.46f, 1.46f);
        pathBuilderR.curveTo(9.14f, 4.41f, 10.52f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _exploreOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
