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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sms", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sms", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSms", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmsKt {
    private static ImageVector _sms;

    public static final ImageVector getSms(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sms;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Sms", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(4.0f, 17.17f, 5.17f, 16.0f);
        pathBuilderJ.lineTo(20.0f, 16.0f);
        pathBuilderJ.lineTo(20.0f, 4.0f);
        d.f(pathBuilderJ, 4.0f, 4.0f, 13.17f);
        a.C(pathBuilderJ, 15.0f, 9.0f, 2.0f, 2.0f);
        d.q(pathBuilderJ, -2.0f, 15.0f, 9.0f);
        a.C(pathBuilderJ, 11.0f, 9.0f, 2.0f, 2.0f);
        d.q(pathBuilderJ, -2.0f, 11.0f, 9.0f);
        a.C(pathBuilderJ, 7.0f, 9.0f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 7.0f, 11.0f, 7.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(20.0f, 2.0f, 4.0f, 2.0f);
        pathBuilderJ2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        f.q(pathBuilderJ2, 18.0f, 4.0f, -4.0f, 14.0f);
        pathBuilderJ2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ2.lineTo(22.0f, 4.0f);
        pathBuilderJ2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ2, 20.0f, 16.0f, 5.17f, 16.0f);
        pathBuilderJ2.lineTo(4.0f, 17.17f);
        b.g(pathBuilderJ2, 4.0f, 4.0f, 16.0f, 12.0f);
        a.C(pathBuilderJ2, 7.0f, 9.0f, 2.0f, 2.0f);
        c.z(pathBuilderJ2, 7.0f, 11.0f, 15.0f, 9.0f);
        d.x(pathBuilderJ2, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderJ2, 11.0f, 9.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ2, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sms = imageVectorBuild;
        return imageVectorBuild;
    }
}
