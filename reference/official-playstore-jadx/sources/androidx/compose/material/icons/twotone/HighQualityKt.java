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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_highQuality", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HighQuality", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHighQuality", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HighQualityKt {
    private static ImageVector _highQuality;

    public static final ImageVector getHighQuality(Icons.TwoTone twoTone) {
        ImageVector imageVector = _highQuality;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HighQuality", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(19.0f, 6.0f, 5.0f, 6.0f, 12.0f);
        d.q(pathBuilderS, 14.0f, 19.0f, 6.0f);
        pathBuilderS.moveTo(11.0f, 15.0f);
        a.h(pathBuilderS, 9.5f, 15.0f, -2.0f, -2.0f);
        pathBuilderS.verticalLineToRelative(2.0f);
        pathBuilderS.lineTo(6.0f, 15.0f);
        b.A(pathBuilderS, 6.0f, 9.0f, 1.5f, 2.5f);
        pathBuilderS.horizontalLineToRelative(2.0f);
        pathBuilderS.lineTo(9.5f, 9.0f);
        d.f(pathBuilderS, 11.0f, 9.0f, 6.0f);
        pathBuilderS.moveTo(18.0f, 14.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        e.g(pathBuilderS, -0.75f, 1.5f, -1.5f);
        pathBuilderS.lineTo(14.75f, 15.0f);
        pathBuilderS.lineTo(14.0f, 15.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.verticalLineToRelative(-4.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderS.horizontalLineToRelative(3.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        c.r(pathBuilderS, 4.0f, 14.5f, 10.5f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderS, 3.0f, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(3.0f, 6.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(14.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(21.0f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.lineTo(5.0f, 4.0f);
        pathBuilderI.curveToRelative(-1.11f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        b.w(pathBuilderI, 5.0f, 6.0f, 14.0f, 12.0f);
        b.y(pathBuilderI, 5.0f, 18.0f, 5.0f, 6.0f);
        pathBuilderI.moveTo(9.5f, 11.5f);
        pathBuilderI.horizontalLineToRelative(-2.0f);
        pathBuilderI.lineTo(7.5f, 9.0f);
        a.h(pathBuilderI, 6.0f, 9.0f, 6.0f, 1.5f);
        e.l(pathBuilderI, -2.0f, 2.0f, 2.0f);
        pathBuilderI.lineTo(11.0f, 15.0f);
        b.y(pathBuilderI, 11.0f, 9.0f, 9.5f, 9.0f);
        pathBuilderI.moveTo(17.0f, 9.0f);
        pathBuilderI.horizontalLineToRelative(-3.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(4.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        e.g(pathBuilderI, 0.75f, 1.5f, 1.5f);
        pathBuilderI.lineTo(16.25f, 15.0f);
        pathBuilderI.lineTo(17.0f, 15.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-4.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        b.w(pathBuilderI, 16.5f, 13.5f, -2.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderI, 2.0f, 3.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _highQuality = imageVectorBuild;
        return imageVectorBuild;
    }
}
