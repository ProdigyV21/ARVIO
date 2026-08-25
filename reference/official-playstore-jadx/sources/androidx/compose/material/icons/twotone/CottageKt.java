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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cottage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cottage", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCottage", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CottageKt {
    private static ImageVector _cottage;

    public static final ImageVector getCottage(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cottage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Cottage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.0f, 19.0f, -5.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -4.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 4.0f);
        pathBuilderR.lineToRelative(-5.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -8.9f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 6.0f, -4.58f, 6.0f, 4.58f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = w.a.n(12.0f, 3.0f, 6.0f, 7.58f, 6.0f);
        pathBuilderN.horizontalLineTo(4.0f);
        pathBuilderN.verticalLineToRelative(3.11f);
        pathBuilderN.lineTo(1.0f, 11.4f);
        pathBuilderN.lineToRelative(1.21f, 1.59f);
        b.n(pathBuilderN, 4.0f, 11.62f, 21.0f, 16.0f);
        pathBuilderN.verticalLineToRelative(-9.38f);
        pathBuilderN.lineToRelative(1.79f, 1.36f);
        b.y(pathBuilderN, 23.0f, 11.4f, 12.0f, 3.0f);
        a.C(pathBuilderN, 18.0f, 19.0f, -5.0f, -4.0f);
        a0.a.y(pathBuilderN, -2.0f, 4.0f, 6.0f, -8.9f);
        pathBuilderN.lineToRelative(6.0f, -4.58f);
        c.e(pathBuilderN, 6.0f, 4.58f, 19.0f);
        pathBuilderN.moveTo(10.0f, 1.0f);
        pathBuilderN.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderN.curveTo(6.45f, 4.0f, 6.0f, 4.45f, 6.0f, 5.0f);
        pathBuilderN.horizontalLineTo(4.0f);
        pathBuilderN.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderN.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderN, 10.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cottage = imageVectorBuild;
        return imageVectorBuild;
    }
}
