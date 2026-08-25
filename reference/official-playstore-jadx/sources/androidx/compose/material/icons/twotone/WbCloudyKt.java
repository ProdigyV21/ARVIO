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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbCloudy", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbCloudy", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWbCloudy", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbCloudyKt {
    private static ImageVector _wbCloudy;

    public static final ImageVector getWbCloudy(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wbCloudy;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WbCloudy", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.23f, 12.04f, -1.52f, -0.11f);
        pathBuilderR.lineToRelative(-0.3f, -1.5f);
        pathBuilderR.curveTo(16.89f, 7.86f, 14.62f, 6.0f, 12.01f, 6.0f);
        pathBuilderR.curveTo(9.95f, 6.0f, 8.08f, 7.14f, 7.13f, 8.96f);
        pathBuilderR.lineToRelative(-0.5f, 0.95f);
        pathBuilderR.lineToRelative(-1.07f, 0.11f);
        pathBuilderR.curveToRelative(-2.02f, 0.22f, -3.55f, 1.93f, -3.55f, 3.98f);
        pathBuilderR.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderR.horizontalLineToRelative(13.0f);
        pathBuilderR.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderR.curveToRelative(0.0f, -1.55f, -1.23f, -2.86f, -2.78f, -2.96f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.36f, 10.04f);
        pathBuilderA.curveTo(18.67f, 6.59f, 15.65f, 4.0f, 12.01f, 4.0f);
        pathBuilderA.curveTo(9.11f, 4.0f, 6.6f, 5.64f, 5.35f, 8.04f);
        pathBuilderA.curveTo(2.35f, 8.36f, 0.01f, 10.91f, 0.01f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.horizontalLineToRelative(13.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -2.64f, -2.05f, -4.78f, -4.65f, -4.96f);
        a.g(pathBuilderA, 19.01f, 18.0f, -13.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -2.05f, 1.53f, -3.76f, 3.56f, -3.97f);
        pathBuilderA.lineToRelative(1.07f, -0.11f);
        pathBuilderA.lineToRelative(0.5f, -0.95f);
        pathBuilderA.curveTo(8.08f, 7.14f, 9.95f, 6.0f, 12.01f, 6.0f);
        pathBuilderA.curveToRelative(2.61f, 0.0f, 4.89f, 1.86f, 5.4f, 4.43f);
        pathBuilderA.lineToRelative(0.3f, 1.5f);
        pathBuilderA.lineToRelative(1.52f, 0.11f);
        pathBuilderA.curveToRelative(1.56f, 0.11f, 2.78f, 1.41f, 2.78f, 2.96f);
        pathBuilderA.curveToRelative(0.0f, 1.65f, -1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbCloudy = imageVectorBuild;
        return imageVectorBuild;
    }
}
