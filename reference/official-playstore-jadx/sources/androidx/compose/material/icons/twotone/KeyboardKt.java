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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_keyboard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Keyboard", "Landroidx/compose/material/icons/Icons$TwoTone;", "getKeyboard", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyboardKt {
    private static ImageVector _keyboard;

    public static final ImageVector getKeyboard(Icons.TwoTone twoTone) {
        ImageVector imageVector = _keyboard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Keyboard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(4.0f, 17.0f, 16.0f, 20.0f, 7.0f);
        d.f(pathBuilderI, 4.0f, 7.0f, 10.0f);
        a.C(pathBuilderI, 17.0f, 8.0f, 2.0f, 2.0f);
        d.q(pathBuilderI, -2.0f, 17.0f, 8.0f);
        a.C(pathBuilderI, 17.0f, 11.0f, 2.0f, 2.0f);
        b.B(pathBuilderI, -2.0f, -2.0f, 14.0f, 8.0f);
        e.g(pathBuilderI, 2.0f, 2.0f, -2.0f);
        c.z(pathBuilderI, 14.0f, 8.0f, 14.0f, 11.0f);
        y.a.r(pathBuilderI, 2.0f, 2.0f, -2.0f, -2.0f);
        a.C(pathBuilderI, 11.0f, 8.0f, 2.0f, 2.0f);
        d.q(pathBuilderI, -2.0f, 11.0f, 8.0f);
        a.C(pathBuilderI, 11.0f, 11.0f, 2.0f, 2.0f);
        b.B(pathBuilderI, -2.0f, -2.0f, 8.0f, 8.0f);
        c.q(pathBuilderI, 2.0f, 2.0f, 8.0f, 10.0f);
        c.z(pathBuilderI, 8.0f, 8.0f, 8.0f, 11.0f);
        c.q(pathBuilderI, 2.0f, 2.0f, 8.0f, 13.0f);
        c.r(pathBuilderI, -2.0f, 8.0f, 14.0f, 8.0f);
        d.g(pathBuilderI, 2.0f, 8.0f, 16.0f, -2.0f);
        a.C(pathBuilderI, 5.0f, 8.0f, 2.0f, 2.0f);
        b.y(pathBuilderI, 5.0f, 10.0f, 5.0f, 8.0f);
        a.C(pathBuilderI, 5.0f, 11.0f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderI, 5.0f, 13.0f, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 5.0f, 4.0f, 5.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.lineTo(2.0f, 17.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 20.0f, 17.0f, 4.0f, 17.0f);
        b.g(pathBuilderJ, 4.0f, 7.0f, 16.0f, 10.0f);
        a.C(pathBuilderJ, 11.0f, 8.0f, 2.0f, 2.0f);
        e.r(pathBuilderJ, -2.0f, 11.0f, 11.0f, 2.0f);
        e.D(pathBuilderJ, 2.0f, -2.0f, 8.0f, 8.0f);
        d.m(pathBuilderJ, 2.0f, 2.0f, 8.0f, 10.0f);
        a.C(pathBuilderJ, 8.0f, 11.0f, 2.0f, 2.0f);
        c.z(pathBuilderJ, 8.0f, 13.0f, 5.0f, 11.0f);
        d.m(pathBuilderJ, 2.0f, 2.0f, 5.0f, 13.0f);
        a.C(pathBuilderJ, 5.0f, 8.0f, 2.0f, 2.0f);
        c.z(pathBuilderJ, 5.0f, 10.0f, 8.0f, 14.0f);
        d.m(pathBuilderJ, 8.0f, 2.0f, 8.0f, 16.0f);
        a.C(pathBuilderJ, 14.0f, 11.0f, 2.0f, 2.0f);
        e.r(pathBuilderJ, -2.0f, 14.0f, 8.0f, 2.0f);
        e.D(pathBuilderJ, 2.0f, -2.0f, 17.0f, 11.0f);
        d.x(pathBuilderJ, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderJ, 17.0f, 8.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _keyboard = imageVectorBuild;
        return imageVectorBuild;
    }
}
