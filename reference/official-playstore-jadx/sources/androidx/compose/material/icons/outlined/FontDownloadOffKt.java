package androidx.compose.material.icons.outlined;

import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fontDownloadOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FontDownloadOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getFontDownloadOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontDownloadOffKt {
    private static ImageVector _fontDownloadOff;

    public static final ImageVector getFontDownloadOff(Icons.Outlined outlined) {
        ImageVector imageVector = _fontDownloadOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FontDownloadOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(4.83f, 2.0f, 20.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        a0.a.l(pathBuilderQ, 15.17f, -2.0f, -2.0f, 4.0f);
        w.a.w(pathBuilderQ, 6.83f, 4.83f, 2.0f);
        pathBuilderQ.moveTo(10.92f, 6.0f);
        pathBuilderQ.lineToRelative(-0.57f, 1.52f);
        pathBuilderQ.lineToRelative(1.36f, 1.36f);
        pathBuilderQ.lineToRelative(0.23f, -0.66f);
        pathBuilderQ.horizontalLineToRelative(0.1f);
        pathBuilderQ.lineToRelative(0.54f, 1.52f);
        pathBuilderQ.lineToRelative(3.04f, 3.04f);
        w.a.e(pathBuilderQ, 13.07f, 6.0f, 10.92f);
        pathBuilderQ.moveTo(20.49f, 23.31f);
        pathBuilderQ.lineTo(19.17f, 22.0f);
        pathBuilderQ.horizontalLineTo(4.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.83f);
        pathBuilderQ.lineTo(0.69f, 3.51f);
        pathBuilderQ.lineTo(2.1f, 2.1f);
        d.C(pathBuilderQ, 19.8f, 19.8f, 20.49f, 23.31f);
        pathBuilderQ.moveTo(17.17f, 20.0f);
        pathBuilderQ.lineToRelative(-5.07f, -5.07f);
        b.q(pathBuilderQ, 9.58f, 8.49f, 18.0f, 6.41f);
        pathBuilderQ.lineToRelative(2.39f, -6.37f);
        pathBuilderQ.lineTo(4.0f, 6.83f);
        pathBuilderQ.verticalLineTo(20.0f);
        pathBuilderQ.horizontalLineTo(17.17f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fontDownloadOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
