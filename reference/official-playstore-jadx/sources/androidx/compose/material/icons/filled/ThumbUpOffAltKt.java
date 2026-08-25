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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbUpOffAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbUpOffAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getThumbUpOffAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbUpOffAltKt {
    private static ImageVector _thumbUpOffAlt;

    public static final ImageVector getThumbUpOffAlt(Icons.Filled filled) {
        ImageVector imageVector = _thumbUpOffAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ThumbUpOffAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.11f, 5.72f, -0.57f, 2.89f);
        pathBuilderR.curveToRelative(-0.12f, 0.59f, 0.04f, 1.2f, 0.42f, 1.66f);
        pathBuilderR.curveToRelative(0.38f, 0.46f, 0.94f, 0.73f, 1.54f, 0.73f);
        pathBuilderR.horizontalLineTo(20.0f);
        pathBuilderR.verticalLineToRelative(1.08f);
        pathBuilderR.lineTo(17.43f, 18.0f);
        pathBuilderR.horizontalLineTo(9.34f);
        pathBuilderR.curveToRelative(-0.18f, 0.0f, -0.34f, -0.16f, -0.34f, -0.34f);
        pathBuilderR.verticalLineTo(9.82f);
        pathBuilderR.lineToRelative(4.11f, -4.1f);
        pathBuilderR.moveTo(14.0f, 2.0f);
        pathBuilderR.lineTo(7.59f, 8.41f);
        pathBuilderR.curveTo(7.21f, 8.79f, 7.0f, 9.3f, 7.0f, 9.83f);
        pathBuilderR.verticalLineToRelative(7.83f);
        pathBuilderR.curveTo(7.0f, 18.95f, 8.05f, 20.0f, 9.34f, 20.0f);
        pathBuilderR.horizontalLineToRelative(8.1f);
        pathBuilderR.curveToRelative(0.71f, 0.0f, 1.36f, -0.37f, 1.72f, -0.97f);
        pathBuilderR.lineToRelative(2.67f, -6.15f);
        pathBuilderR.curveToRelative(0.11f, -0.25f, 0.17f, -0.52f, 0.17f, -0.8f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.horizontalLineToRelative(-5.5f);
        pathBuilderR.lineToRelative(0.92f, -4.65f);
        pathBuilderR.curveToRelative(0.05f, -0.22f, 0.02f, -0.46f, -0.08f, -0.66f);
        pathBuilderR.curveToRelative(-0.23f, -0.45f, -0.52f, -0.86f, -0.88f, -1.22f);
        c.z(pathBuilderR, 14.0f, 2.0f, 4.0f, 9.0f);
        a.n(pathBuilderR, 2.0f, 11.0f, 2.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-9.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbUpOffAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
