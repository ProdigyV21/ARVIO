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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownloadOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownloadOff", "Landroidx/compose/material/icons/Icons$Filled;", "getFontDownloadOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadOffKt {
    private static ImageVector _fontDownloadOff;

    public static final ImageVector getFontDownloadOff(Icons.Filled filled) {
        ImageVector imageVector = _fontDownloadOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FontDownloadOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.58f, 9.75f, -0.87f, -0.87f);
        pathBuilderR.lineToRelative(0.23f, -0.66f);
        d.q(pathBuilderR, 0.1f, 12.58f, 9.75f);
        pathBuilderR.moveTo(10.35f, 7.52f);
        pathBuilderR.lineTo(10.92f, 6.0f);
        pathBuilderR.horizontalLineToRelative(2.14f);
        pathBuilderR.lineToRelative(2.55f, 6.79f);
        pathBuilderR.lineTo(22.0f, 19.17f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        w.a.w(pathBuilderR, 4.83f, 10.35f, 7.52f);
        pathBuilderR.moveTo(20.49f, 23.31f);
        pathBuilderR.lineTo(19.17f, 22.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineTo(4.83f);
        pathBuilderR.lineTo(0.69f, 3.51f);
        pathBuilderR.lineTo(2.1f, 2.1f);
        d.C(pathBuilderR, 19.8f, 19.8f, 20.49f, 23.31f);
        pathBuilderR.moveTo(12.1f, 14.93f);
        pathBuilderR.lineToRelative(-3.3f, -3.3f);
        pathBuilderR.lineTo(6.41f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderR, 2.08f, 1.09f, -3.07f, 12.1f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fontDownloadOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
