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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownload", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFontDownload", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadKt {
    private static ImageVector _fontDownload;

    public static final ImageVector getFontDownload(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fontDownload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FontDownload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(4.0f, 20.0f, 16.0f, 20.0f, 4.0f);
        d.f(pathBuilderI, 4.0f, 4.0f, 16.0f);
        pathBuilderI.moveTo(11.07f, 5.5f);
        e.C(pathBuilderI, 1.86f, 5.11f, 13.0f, -2.09f);
        pathBuilderI.lineToRelative(-1.14f, -3.0f);
        pathBuilderI.lineTo(9.17f, 15.5f);
        pathBuilderI.lineToRelative(-1.12f, 3.0f);
        d.l(pathBuilderI, 5.96f, 18.5f, 5.11f, -13.0f);
        pathBuilderI.moveTo(12.0f, 7.98f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderI, 9.93f, 13.5f, 4.14f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(9.17f, 15.5f, 5.64f, 1.14f, 3.0f);
        e.C(pathBuilderG, 2.09f, -5.11f, -13.0f, -1.86f);
        pathBuilderG.lineToRelative(-5.11f, 13.0f);
        c.v(pathBuilderG, 2.09f, 1.12f, -3.0f);
        pathBuilderG.moveTo(12.0f, 7.98f);
        pathBuilderG.lineToRelative(2.07f, 5.52f);
        b.y(pathBuilderG, 9.93f, 13.5f, 12.0f, 7.98f);
        pathBuilderG.moveTo(20.0f, 2.0f);
        pathBuilderG.lineTo(4.0f, 2.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(22.0f, 4.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderG, 20.0f, 20.0f, 4.0f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderG, 4.0f, 4.0f, 16.0f, 16.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fontDownload = imageVectorBuild;
        return imageVectorBuild;
    }
}
