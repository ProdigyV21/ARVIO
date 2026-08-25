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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_permMedia", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PermMedia", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPermMedia", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PermMediaKt {
    private static ImageVector _permMedia;

    public static final ImageVector getPermMedia(Icons.TwoTone twoTone) {
        ImageVector imageVector = _permMedia;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PermMedia", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.17f, 6.0f, -0.59f, -0.59f);
        pathBuilderR.lineTo(11.17f, 4.0f);
        a.h(pathBuilderR, 6.0f, 4.0f, 12.0f, 16.0f);
        f.n(pathBuilderR, 22.0f, 6.0f, -8.83f);
        pathBuilderR.moveTo(17.5f, 10.5f);
        pathBuilderR.lineTo(21.0f, 15.0f);
        pathBuilderR.lineTo(7.0f, 15.0f);
        pathBuilderR.lineToRelative(4.5f, -6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 3.5f, 4.51f, 2.5f, -3.01f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(2.0f, 6.0f, 0.0f, 6.0f, 5.0f);
        pathBuilderS.horizontalLineToRelative(0.01f);
        pathBuilderS.lineTo(0.0f, 20.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        c.q(pathBuilderS, 18.0f, -2.0f, 2.0f, 20.0f);
        c.z(pathBuilderS, 2.0f, 6.0f, 7.0f, 15.0f);
        pathBuilderS.horizontalLineToRelative(14.0f);
        pathBuilderS.lineToRelative(-3.5f, -4.5f);
        d.C(pathBuilderS, -2.5f, 3.01f, 11.5f, 9.0f);
        pathBuilderS.moveTo(22.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(-8.0f);
        pathBuilderS.lineToRelative(-2.0f, -2.0f);
        pathBuilderS.lineTo(6.0f, 2.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderS.lineTo(4.0f, 16.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.horizontalLineToRelative(16.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderS.lineTo(24.0f, 6.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderS, 22.0f, 16.0f, 6.0f, 16.0f);
        pathBuilderS.lineTo(6.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(5.17f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        pathBuilderS.lineToRelative(0.59f, 0.59f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderS, 22.0f, 6.0f, 10.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _permMedia = imageVectorBuild;
        return imageVectorBuild;
    }
}
