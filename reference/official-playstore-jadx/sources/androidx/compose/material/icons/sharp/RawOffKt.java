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
import v.b;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rawOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RawOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getRawOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RawOffKt {
    private static ImageVector _rawOff;

    public static final ImageVector getRawOff(Icons.Sharp sharp) {
        ImageVector imageVector = _rawOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.RawOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.15f, 14.32f, 0.59f, -2.36f);
        pathBuilderR.lineToRelative(0.76f, 3.04f);
        pathBuilderR.lineToRelative(1.48f, 0.0f);
        pathBuilderR.lineToRelative(1.5f, -6.0f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(-0.74f, 3.0f);
        pathBuilderR.lineToRelative(-0.74f, -3.0f);
        pathBuilderR.lineToRelative(-1.52f, 0.0f);
        pathBuilderR.lineToRelative(-0.74f, 3.0f);
        pathBuilderR.lineToRelative(-0.74f, -3.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -1.5f, 0.0f, 0.72f, 2.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = d.n(1.39f, 4.22f, 6.17f, 9.0f, 3.0f);
        b.z(pathBuilderN, 6.0f, 1.5f, -2.0f, 1.1f);
        pathBuilderN.lineToRelative(0.9f, 2.0f);
        pathBuilderN.horizontalLineTo(8.0f);
        pathBuilderN.lineToRelative(-0.9f, -2.1f);
        pathBuilderN.curveTo(7.6f, 12.6f, 8.0f, 12.1f, 8.0f, 11.5f);
        pathBuilderN.verticalLineToRelative(-0.67f);
        pathBuilderN.lineToRelative(1.43f, 1.43f);
        pathBuilderN.lineTo(8.75f, 15.0f);
        e.C(pathBuilderN, 1.5f, 0.38f, -1.5f, 0.04f);
        pathBuilderN.lineToRelative(9.11f, 9.11f);
        pathBuilderN.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderN, 2.81f, 2.81f, 1.39f, 4.22f);
        a.C(pathBuilderN, 6.5f, 11.5f, -2.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderN, 2.0f, 11.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rawOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
