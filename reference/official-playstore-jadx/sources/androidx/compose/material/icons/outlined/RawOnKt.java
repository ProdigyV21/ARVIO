package androidx.compose.material.icons.outlined;

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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rawOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RawOn", "Landroidx/compose/material/icons/Icons$Outlined;", "getRawOn", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RawOnKt {
    private static ImageVector _rawOn;

    public static final ImageVector getRawOn(Icons.Outlined outlined) {
        ImageVector imageVector = _rawOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RawOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(6.5f, 9.0f, 3.0f, 6.0f, 1.5f);
        d.D(pathBuilderH, -2.0f, 1.1f, 0.9f, 2.0f);
        pathBuilderH.horizontalLineTo(8.0f);
        pathBuilderH.lineToRelative(-0.9f, -2.1f);
        pathBuilderH.curveTo(7.6f, 12.6f, 8.0f, 12.1f, 8.0f, 11.5f);
        pathBuilderH.verticalLineToRelative(-1.0f);
        pathBuilderH.curveTo(8.0f, 9.7f, 7.3f, 9.0f, 6.5f, 9.0f);
        b.w(pathBuilderH, 6.5f, 11.5f, -2.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderH, 2.0f, 11.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(10.25f, 9.0f, -1.5f, 6.0f, 1.5f);
        pathBuilderC.lineToRelative(0.38f, -1.5f);
        e.C(pathBuilderC, 1.75f, 0.37f, 1.5f, 1.5f);
        f.v(pathBuilderC, -1.5f, -6.0f, 10.25f);
        pathBuilderC.moveTo(11.0f, 12.0f);
        pathBuilderC.lineToRelative(0.25f, -1.0f);
        pathBuilderC.horizontalLineToRelative(0.5f);
        pathBuilderC.lineTo(12.0f, 12.0f);
        pathBuilderC.horizontalLineTo(11.0f);
        pathBuilderC.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.98f, 9.0f, -0.74f, 3.0f);
        pathBuilderR.lineToRelative(-0.74f, -3.0f);
        pathBuilderR.lineToRelative(-1.52f, 0.0f);
        pathBuilderR.lineToRelative(-0.74f, 3.0f);
        pathBuilderR.lineToRelative(-0.74f, -3.0f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(1.5f, 6.0f);
        pathBuilderR.lineToRelative(1.48f, 0.0f);
        pathBuilderR.lineToRelative(0.76f, -3.04f);
        pathBuilderR.lineToRelative(0.76f, 3.04f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 1.48f, 0.0f, 1.5f, -6.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rawOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
