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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flipCameraIos", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlipCameraIos", "Landroidx/compose/material/icons/Icons$Outlined;", "getFlipCameraIos", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlipCameraIosKt {
    private static ImageVector _flipCameraIos;

    public static final ImageVector getFlipCameraIos(Icons.Outlined outlined) {
        ImageVector imageVector = _flipCameraIos;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FlipCameraIos", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(20.0f, 5.0f, -3.17f, 15.0f, 3.0f);
        b.q(pathBuilderI, 9.0f, 7.17f, 5.0f, 4.0f);
        pathBuilderI.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.verticalLineTo(7.0f);
        pathBuilderI.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        f.o(pathBuilderI, 20.0f, 19.0f, 4.0f, 7.0f);
        pathBuilderI.horizontalLineToRelative(3.17f);
        pathBuilderI.horizontalLineToRelative(0.88f);
        pathBuilderI.lineToRelative(0.59f, -0.65f);
        pathBuilderI.lineTo(9.88f, 5.0f);
        pathBuilderI.horizontalLineToRelative(4.24f);
        pathBuilderI.lineToRelative(1.24f, 1.35f);
        pathBuilderI.lineTo(15.95f, 7.0f);
        pathBuilderI.horizontalLineToRelative(0.88f);
        pathBuilderI.horizontalLineTo(20.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderI, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 17.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.lineToRelative(-2.5f, -2.5f);
        pathBuilderA.lineTo(5.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(0.86f, 0.0f, 1.65f, -0.24f, 2.36f, -0.62f);
        pathBuilderA.lineToRelative(-0.74f, -0.74f);
        pathBuilderA.curveTo(13.13f, 16.87f, 12.58f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 8.0f);
        pathBuilderA2.curveToRelative(-0.86f, 0.0f, -1.65f, 0.24f, -2.36f, 0.62f);
        pathBuilderA2.lineToRelative(0.74f, 0.73f);
        pathBuilderA2.curveTo(10.87f, 9.13f, 11.42f, 9.0f, 12.0f, 9.0f);
        pathBuilderA2.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA2.horizontalLineToRelative(-2.0f);
        pathBuilderA2.lineToRelative(2.5f, 2.5f);
        pathBuilderA2.lineTo(19.0f, 13.0f);
        pathBuilderA2.horizontalLineToRelative(-2.0f);
        pathBuilderA2.curveTo(17.0f, 10.24f, 14.76f, 8.0f, 12.0f, 8.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flipCameraIos = imageVectorBuild;
        return imageVectorBuild;
    }
}
