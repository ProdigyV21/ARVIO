package androidx.compose.material.icons.twotone;

import a0.a;
import a0.b;
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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lan", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Lan", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLan", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LanKt {
    private static ImageVector _lan;

    public static final ImageVector getLan(Icons.TwoTone twoTone) {
        ImageVector imageVector = _lan;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Lan", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(10.0f, 7.0f, 4.0f, 4.0f, 3.0f);
        f.l(pathBuilderA, 10.0f, 9.0f, 17.0f, 3.0f);
        b.e(pathBuilderA, 5.0f, -3.0f, 9.0f);
        c.m(pathBuilderA, 19.0f, 17.0f, 3.0f, -4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.d(pathBuilderA, -3.0f, 19.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = v.a.k(13.0f, 22.0f, 8.0f, -7.0f, -3.0f);
        v.a.y(pathBuilderK, -4.0f, -5.0f, 9.0f, 3.0f);
        c.o(pathBuilderK, 2.0f, 8.0f, 7.0f, 3.0f);
        v.a.w(pathBuilderK, 2.0f, 6.0f, 4.0f, 3.0f);
        f.u(pathBuilderK, 7.0f, 8.0f, -7.0f, 8.0f);
        v.b.z(pathBuilderK, -2.0f, 8.0f, 2.0f, -3.0f);
        b.n(pathBuilderK, 22.0f, 10.0f, 7.0f, 4.0f);
        y.a.q(pathBuilderK, 4.0f, 3.0f, 10.0f);
        c.u(pathBuilderK, 9.0f, 17.0f, 3.0f, 5.0f);
        e.y(pathBuilderK, -3.0f, 9.0f, 19.0f, 17.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.d(pathBuilderK, 3.0f, -4.0f, -3.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _lan = imageVectorBuild;
        return imageVectorBuild;
    }
}
