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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fiberPin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FiberPin", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFiberPin", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FiberPinKt {
    private static ImageVector _fiberPin;

    public static final ImageVector getFiberPin(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fiberPin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FiberPin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(5.0f, 14.62f, 1.31f, -1.75f, 1.75f);
        pathBuilderK.curveToRelative(0.74f, 0.0f, 1.31f, -0.57f, 1.31f, -1.31f);
        pathBuilderK.verticalLineToRelative(-0.88f);
        pathBuilderK.curveToRelative(0.0f, -0.74f, -0.57f, -1.31f, -1.31f, -1.31f);
        d.f(pathBuilderK, 5.0f, 9.37f, 5.25f);
        a.C(pathBuilderK, 6.31f, 10.69f, 1.75f, 0.88f);
        d.f(pathBuilderK, 6.31f, 11.57f, -0.88f);
        a.C(pathBuilderK, 11.34f, 9.38f, 1.31f, 5.25f);
        e.r(pathBuilderK, -1.31f, 14.62f, 14.62f, 1.1f);
        pathBuilderK.verticalLineToRelative(-3.06f);
        pathBuilderK.lineToRelative(2.23f, 3.06f);
        pathBuilderK.lineTo(19.0f, 14.62f);
        b.A(pathBuilderK, 19.0f, 9.38f, -1.09f, 3.06f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.g(pathBuilderK, -2.19f, -3.06f, -1.1f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.c(4.0f, 6.0f, 16.0f, 12.0f, 4.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 4.0f, 4.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.11f, 0.0f, -1.99f, 0.89f, -1.99f, 2.0f);
        pathBuilderJ.lineTo(2.0f, 18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 20.0f, 18.0f, 4.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderJ, 4.0f, 6.0f, 16.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fiberPin = imageVectorBuild;
        return imageVectorBuild;
    }
}
