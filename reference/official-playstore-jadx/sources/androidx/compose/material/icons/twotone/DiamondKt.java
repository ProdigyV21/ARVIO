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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_diamond", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Diamond", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDiamond", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DiamondKt {
    private static ImageVector _diamond;

    public static final ImageVector getDiamond(Icons.TwoTone twoTone) {
        ImageVector imageVector = _diamond;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Diamond", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(8.88f, 5.0f, -2.64f, 0.0f), -1.5f, 3.0f, 2.64f, 0.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(19.26f, 8.0f, -1.5f, -3.0f), -2.64f, 0.0f, 1.5f, 3.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(11.0f, 16.68f, 0.0f, -6.68f), -5.56f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(13.0f, 16.68f, 5.56f, -6.68f), -5.56f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(12.88f, 5.0f, -1.76f, 0.0f), -1.5f, 3.0f, 4.76f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderP = y.a.p(19.0f, 3.0f, 5.0f, 2.0f, 9.0f);
        pathBuilderP.lineToRelative(10.0f, 12.0f);
        b.y(pathBuilderP, 22.0f, 9.0f, 19.0f, 3.0f);
        pathBuilderP.moveTo(17.76f, 5.0f);
        pathBuilderP.lineToRelative(1.5f, 3.0f);
        a0.b.B(pathBuilderP, -2.65f, -1.5f, -3.0f, 17.76f);
        pathBuilderP.moveTo(6.24f, 5.0f);
        a0.a.n(pathBuilderP, 2.65f, -1.5f, 3.0f, 4.74f);
        c.z(pathBuilderP, 6.24f, 5.0f, 11.0f, 16.68f);
        pathBuilderP.lineTo(5.44f, 10.0f);
        pathBuilderP.horizontalLineTo(11.0f);
        pathBuilderP.verticalLineTo(16.68f);
        pathBuilderP.close();
        pathBuilderP.moveTo(9.62f, 8.0f);
        pathBuilderP.lineToRelative(1.5f, -3.0f);
        a0.b.B(pathBuilderP, 1.76f, 1.5f, 3.0f, 9.62f);
        e.o(pathBuilderP, 13.0f, 16.68f, 10.0f, 5.56f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderP, 13.0f, 16.68f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _diamond = imageVectorBuild;
        return imageVectorBuild;
    }
}
