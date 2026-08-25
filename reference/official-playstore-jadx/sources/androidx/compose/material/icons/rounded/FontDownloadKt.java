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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownload", "Landroidx/compose/material/icons/Icons$Rounded;", "getFontDownload", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadKt {
    private static ImageVector _fontDownload;

    public static final ImageVector getFontDownload(Icons.Rounded rounded) {
        ImageVector imageVector = _fontDownload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FontDownload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(9.93f, 13.5f, 4.14f, 12.0f, 7.98f);
        c.z(pathBuilderI, 9.93f, 13.5f, 20.0f, 2.0f);
        pathBuilderI.lineTo(4.0f, 2.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(22.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.z(pathBuilderI, 15.71f, 17.88f, -0.9f, -2.38f);
        pathBuilderI.lineTo(9.17f, 15.5f);
        pathBuilderI.lineToRelative(-0.89f, 2.37f);
        pathBuilderI.curveToRelative(-0.14f, 0.38f, -0.5f, 0.63f, -0.91f, 0.63f);
        pathBuilderI.curveToRelative(-0.68f, 0.0f, -1.15f, -0.69f, -0.9f, -1.32f);
        pathBuilderI.lineToRelative(4.25f, -10.81f);
        pathBuilderI.curveToRelative(0.22f, -0.53f, 0.72f, -0.87f, 1.28f, -0.87f);
        pathBuilderI.reflectiveCurveToRelative(1.06f, 0.34f, 1.27f, 0.87f);
        pathBuilderI.lineToRelative(4.25f, 10.81f);
        pathBuilderI.curveToRelative(0.25f, 0.63f, -0.22f, 1.32f, -0.9f, 1.32f);
        pathBuilderI.curveToRelative(-0.4f, 0.0f, -0.76f, -0.25f, -0.91f, -0.62f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fontDownload = imageVectorBuild;
        return imageVectorBuild;
    }
}
