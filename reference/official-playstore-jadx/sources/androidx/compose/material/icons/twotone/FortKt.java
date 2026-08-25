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
import v.b;
import v.c;
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fort", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fort", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFort", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FortKt {
    private static ImageVector _fort;

    public static final ImageVector getFort(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fort;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Fort", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(19.0f, 8.17f, 20.17f, 7.0f, -4.34f);
        c.f(pathBuilderT, 17.0f, 8.17f, 12.0f, 7.0f);
        pathBuilderT.verticalLineTo(8.17f);
        pathBuilderT.lineTo(8.17f, 7.0f);
        pathBuilderT.horizontalLineTo(3.83f);
        pathBuilderT.lineTo(5.0f, 8.17f);
        a0.a.l(pathBuilderT, 7.66f, -2.0f, 2.0f, 19.0f);
        pathBuilderT.horizontalLineToRelative(5.0f);
        pathBuilderT.verticalLineToRelative(-1.0f);
        pathBuilderT.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderT.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        e.l(pathBuilderT, 1.0f, 5.0f, -1.17f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderT, -2.0f, -2.0f, 8.17f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a0.a.a(23.0f, 7.0f, 3.0f, -2.0f, 2.0f);
        a.C(pathBuilderA, -2.0f, 3.0f, -2.0f, 2.0f);
        a.C(pathBuilderA, -2.0f, 3.0f, -2.0f, 4.0f);
        d.v(pathBuilderA, 2.0f, 2.0f, 1.0f, 9.0f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.lineToRelative(2.0f, -2.0f);
        pathBuilderA.verticalLineTo(3.0f);
        a0.b.C(pathBuilderA, 9.0f, 2.0f, 7.0f, 3.0f);
        a0.b.C(pathBuilderA, 5.0f, 2.0f, 3.0f, 3.0f);
        a0.a.D(pathBuilderA, 1.0f, 4.0f, 2.0f, 2.0f);
        e.v(pathBuilderA, 6.0f, -2.0f, 2.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(9.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        e.l(pathBuilderA, 3.0f, 9.0f, -4.0f);
        pathBuilderA.lineToRelative(-2.0f, -2.0f);
        c.x(pathBuilderA, 9.0f, 23.0f, 7.0f);
        v.a.C(pathBuilderA, 21.0f, 19.0f, -5.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.0f, 1.79f, -4.0f, 4.0f);
        y.a.g(pathBuilderA, 1.0f, 3.0f, -1.17f);
        pathBuilderA.lineToRelative(2.0f, -2.0f);
        pathBuilderA.verticalLineTo(8.17f);
        pathBuilderA.lineTo(3.83f, 7.0f);
        pathBuilderA.horizontalLineToRelative(4.34f);
        b.n(pathBuilderA, 7.0f, 8.17f, 12.0f, 10.0f);
        pathBuilderA.verticalLineTo(8.17f);
        pathBuilderA.lineTo(15.83f, 7.0f);
        pathBuilderA.horizontalLineToRelative(4.34f);
        pathBuilderA.lineTo(19.0f, 8.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.b.h(pathBuilderA, 7.66f, 2.0f, 2.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fort = imageVectorBuild;
        return imageVectorBuild;
    }
}
