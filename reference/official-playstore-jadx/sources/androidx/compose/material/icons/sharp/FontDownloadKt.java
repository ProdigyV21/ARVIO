package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownload", "Landroidx/compose/material/icons/Icons$Sharp;", "getFontDownload", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadKt {
    private static ImageVector _fontDownload;

    public static final ImageVector getFontDownload(Icons.Sharp sharp) {
        ImageVector imageVector = _fontDownload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.FontDownload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(9.93f, 13.5f, 4.14f, 12.0f, 7.98f);
        c.z(pathBuilderI, 9.93f, 13.5f, 22.0f, 2.0f);
        a.h(pathBuilderI, 2.0f, 2.0f, 20.0f, 20.0f);
        c.z(pathBuilderI, 22.0f, 2.0f, 15.95f, 18.5f);
        pathBuilderI.lineToRelative(-1.14f, -3.0f);
        pathBuilderI.lineTo(9.17f, 15.5f);
        pathBuilderI.lineToRelative(-1.12f, 3.0f);
        pathBuilderI.lineTo(5.96f, 18.5f);
        pathBuilderI.lineToRelative(5.11f, -13.0f);
        pathBuilderI.horizontalLineToRelative(1.86f);
        pathBuilderI.lineToRelative(5.11f, 13.0f);
        pathBuilderI.horizontalLineToRelative(-2.09f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fontDownload = imageVectorBuild;
        return imageVectorBuild;
    }
}
