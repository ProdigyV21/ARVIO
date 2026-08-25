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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hideImage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HideImage", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHideImage", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HideImageKt {
    private static ImageVector _hideImage;

    public static final ImageVector getHideImage(Icons.TwoTone twoTone) {
        ImageVector imageVector = _hideImage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HideImage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.17f, 19.0f, -2.0f, -2.0f);
        pathBuilderR.lineToRelative(-8.17f, 0.0f);
        pathBuilderR.lineToRelative(3.0f, -4.0f);
        pathBuilderR.lineToRelative(2.25f, 3.0f);
        pathBuilderR.lineToRelative(0.82f, -1.1f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -7.07f, -7.07f, 0.0f, 11.17f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(7.83f, 5.0f, 11.17f, 11.17f), 0.0f, -11.17f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = w.a.g(19.0f, 5.0f, 11.17f, 2.0f, 2.0f);
        pathBuilderG.verticalLineTo(5.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.horizontalLineTo(5.83f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        pathBuilderG.horizontalLineTo(19.0f);
        pathBuilderG.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderJ.lineTo(3.0f, 5.83f);
        pathBuilderJ.verticalLineTo(19.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(13.17f);
        pathBuilderJ.lineToRelative(1.61f, 1.61f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 2.81f, 2.81f);
        pathBuilderJ.moveTo(5.0f, 19.0f);
        pathBuilderJ.verticalLineTo(7.83f);
        pathBuilderJ.lineToRelative(7.07f, 7.07f);
        pathBuilderJ.lineTo(11.25f, 16.0f);
        pathBuilderJ.lineTo(9.0f, 13.0f);
        pathBuilderJ.lineToRelative(-3.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderJ, 8.17f, 2.0f, 2.0f, 5.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hideImage = imageVectorBuild;
        return imageVectorBuild;
    }
}
