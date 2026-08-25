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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightlight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Nightlight", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNightlight", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightlightKt {
    private static ImageVector _nightlight;

    public static final ImageVector getNightlight(Icons.TwoTone twoTone) {
        ImageVector imageVector = _nightlight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Nightlight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -4.41f, 3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.68f, 0.02f, 1.01f, 0.07f);
        pathBuilderA.curveTo(13.1f, 6.23f, 12.0f, 9.05f, 12.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.1f, 5.77f, 3.01f, 7.93f);
        pathBuilderA.curveTo(14.68f, 19.98f, 14.34f, 20.0f, 14.0f, 20.0f);
        pathBuilderA.curveTo(9.59f, 20.0f, 6.0f, 16.41f, 6.0f, 12.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(14.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.7f, 2.01f, -6.92f, 5.0f, -8.65f);
        pathBuilderA2.curveTo(17.53f, 2.5f, 15.82f, 2.0f, 14.0f, 2.0f);
        pathBuilderA2.curveTo(8.48f, 2.0f, 4.0f, 6.48f, 4.0f, 12.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.curveToRelative(1.82f, 0.0f, 3.53f, -0.5f, 5.0f, -1.35f);
        pathBuilderA2.curveTo(16.01f, 18.92f, 14.0f, 15.7f, 14.0f, 12.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(15.01f, 19.93f);
        pathBuilderA2.curveTo(14.68f, 19.98f, 14.34f, 20.0f, 14.0f, 20.0f);
        pathBuilderA2.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA2.curveToRelative(0.34f, 0.0f, 0.68f, 0.02f, 1.01f, 0.07f);
        pathBuilderA2.curveTo(13.1f, 6.23f, 12.0f, 9.05f, 12.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 13.1f, 17.77f, 15.01f, 19.93f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightlight = imageVectorBuild;
        return imageVectorBuild;
    }
}
