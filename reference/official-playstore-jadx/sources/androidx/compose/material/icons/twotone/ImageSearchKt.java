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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageSearch", "Landroidx/compose/material/icons/Icons$TwoTone;", "getImageSearch", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageSearchKt {
    private static ImageVector _imageSearch;

    public static final ImageVector getImageSearch(Icons.TwoTone twoTone) {
        ImageVector imageVector = _imageSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ImageSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.7f, 11.53f);
        pathBuilderA.curveToRelative(-0.7f, 0.31f, -1.45f, 0.47f, -2.21f, 0.47f);
        pathBuilderA.curveTo(12.46f, 12.0f, 10.0f, 9.53f, 10.0f, 6.5f);
        pathBuilderA.curveToRelative(0.0f, -0.17f, 0.01f, -0.34f, 0.03f, -0.5f);
        b.q(pathBuilderA, 4.0f, 14.0f, 14.0f, -8.17f);
        b.C(pathBuilderA, -0.3f, -0.3f, 5.5f, 18.0f);
        pathBuilderA.lineToRelative(2.75f, -3.53f);
        pathBuilderA.lineToRelative(1.96f, 2.36f);
        pathBuilderA.lineToRelative(2.75f, -3.54f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderA, 16.5f, 18.0f, -11.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.21f, 16.83f, -1.96f, -2.36f);
        pathBuilderR.lineTo(5.5f, 18.0f);
        c.v(pathBuilderR, 11.0f, -3.54f, -4.71f);
        pathBuilderR.moveTo(20.0f, 6.5f);
        pathBuilderR.curveTo(20.0f, 4.01f, 17.99f, 2.0f, 15.5f, 2.0f);
        pathBuilderR.reflectiveCurveTo(11.0f, 4.01f, 11.0f, 6.5f);
        pathBuilderR.reflectiveCurveToRelative(2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilderR.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderR.lineTo(21.0f, 13.42f);
        pathBuilderR.lineTo(22.42f, 12.0f);
        pathBuilderR.lineTo(19.3f, 8.89f);
        pathBuilderR.curveToRelative(0.44f, -0.7f, 0.7f, -1.51f, 0.7f, -2.39f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.5f, 9.0f);
        pathBuilderR.curveTo(14.12f, 9.0f, 13.0f, 7.88f, 13.0f, 6.5f);
        pathBuilderR.reflectiveCurveTo(14.12f, 4.0f, 15.5f, 4.0f);
        pathBuilderR.reflectiveCurveTo(18.0f, 5.12f, 18.0f, 6.5f);
        pathBuilderR.reflectiveCurveTo(16.88f, 9.0f, 15.5f, 9.0f);
        f.o(pathBuilderR, 18.0f, 20.0f, 4.0f, 6.0f);
        pathBuilderR.horizontalLineToRelative(6.03f);
        pathBuilderR.curveToRelative(0.06f, -0.72f, 0.27f, -1.39f, 0.58f, -2.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.b.h(pathBuilderR, -6.17f, -2.0f, -2.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _imageSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
