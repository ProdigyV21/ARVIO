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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gppBad", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GppBad", "Landroidx/compose/material/icons/Icons$Rounded;", "getGppBad", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GppBadKt {
    private static ImageVector _gppBad;

    public static final ImageVector getGppBad(Icons.Rounded rounded) {
        ImageVector imageVector = _gppBad;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.GppBad", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.7f, 4.51f, -6.0f, -2.25f);
        pathBuilderR.curveToRelative(-0.45f, -0.17f, -0.95f, -0.17f, -1.4f, 0.0f);
        pathBuilderR.lineToRelative(-6.0f, 2.25f);
        pathBuilderR.curveTo(4.52f, 4.81f, 4.0f, 5.55f, 4.0f, 6.39f);
        pathBuilderR.verticalLineToRelative(4.7f);
        pathBuilderR.curveToRelative(0.0f, 4.94f, 3.27f, 9.57f, 7.71f, 10.83f);
        pathBuilderR.curveToRelative(0.19f, 0.05f, 0.39f, 0.05f, 0.57f, 0.0f);
        pathBuilderR.curveTo(16.73f, 20.66f, 20.0f, 16.03f, 20.0f, 11.09f);
        pathBuilderR.verticalLineToRelative(-4.7f);
        pathBuilderR.curveTo(20.0f, 5.55f, 19.48f, 4.81f, 18.7f, 4.51f);
        a.p(pathBuilderR, 14.8f, 14.79f, 14.8f, 14.79f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.01f);
        pathBuilderR.lineTo(12.0f, 13.42f);
        pathBuilderR.lineToRelative(-1.39f, 1.38f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineTo(10.59f, 12.0f);
        pathBuilderR.lineTo(9.2f, 10.61f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineTo(12.0f, 10.59f);
        pathBuilderR.lineToRelative(1.39f, -1.39f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineTo(13.42f, 12.0f);
        pathBuilderR.lineToRelative(1.38f, 1.38f);
        pathBuilderR.curveTo(15.19f, 13.77f, 15.19f, 14.4f, 14.8f, 14.79f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gppBad = imageVectorBuild;
        return imageVectorBuild;
    }
}
