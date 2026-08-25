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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_business", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Business", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBusiness", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BusinessKt {
    private static ImageVector _business;

    public static final ImageVector getBusiness(Icons.TwoTone twoTone) {
        ImageVector imageVector = _business;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Business", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(12.0f, 11.0f, 2.0f, 2.0f, -2.0f);
        b.z(pathBuilderK, 2.0f, 2.0f, 2.0f, -2.0f);
        c.w(pathBuilderK, 2.0f, 8.0f, 20.0f, 9.0f);
        b.B(pathBuilderK, -8.0f, 2.0f, 16.0f, 11.0f);
        y.a.r(pathBuilderK, 2.0f, 2.0f, -2.0f, -2.0f);
        a.C(pathBuilderK, 16.0f, 15.0f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderK, -2.0f, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(16.0f, 15.0f, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderB, 16.0f, 11.0f, 2.0f, 2.0f);
        f.x(pathBuilderB, -2.0f, 22.0f, 7.0f);
        pathBuilderB.lineTo(12.0f, 7.0f);
        pathBuilderB.lineTo(12.0f, 3.0f);
        a.h(pathBuilderB, 2.0f, 3.0f, 18.0f, 20.0f);
        c.z(pathBuilderB, 22.0f, 7.0f, 6.0f, 19.0f);
        a.h(pathBuilderB, 4.0f, 19.0f, -2.0f, 2.0f);
        b.f(pathBuilderB, 2.0f, 6.0f, 15.0f);
        a.h(pathBuilderB, 4.0f, 15.0f, -2.0f, 2.0f);
        b.f(pathBuilderB, 2.0f, 6.0f, 11.0f);
        pathBuilderB.lineTo(4.0f, 11.0f);
        b.g(pathBuilderB, 4.0f, 9.0f, 2.0f, 2.0f);
        pathBuilderB.moveTo(6.0f, 7.0f);
        pathBuilderB.lineTo(4.0f, 7.0f);
        b.g(pathBuilderB, 4.0f, 5.0f, 2.0f, 2.0f);
        pathBuilderB.moveTo(10.0f, 19.0f);
        a.h(pathBuilderB, 8.0f, 19.0f, -2.0f, 2.0f);
        b.f(pathBuilderB, 2.0f, 10.0f, 15.0f);
        a.h(pathBuilderB, 8.0f, 15.0f, -2.0f, 2.0f);
        b.f(pathBuilderB, 2.0f, 10.0f, 11.0f);
        pathBuilderB.lineTo(8.0f, 11.0f);
        b.g(pathBuilderB, 8.0f, 9.0f, 2.0f, 2.0f);
        pathBuilderB.moveTo(10.0f, 7.0f);
        pathBuilderB.lineTo(8.0f, 7.0f);
        b.g(pathBuilderB, 8.0f, 5.0f, 2.0f, 2.0f);
        a.C(pathBuilderB, 20.0f, 19.0f, -8.0f, -2.0f);
        a.o(pathBuilderB, 2.0f, -2.0f, -2.0f, -2.0f);
        e.g(pathBuilderB, 2.0f, -2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderB, 12.0f, 9.0f, 8.0f, 10.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _business = imageVectorBuild;
        return imageVectorBuild;
    }
}
