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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bungalow", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bungalow", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBungalow", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BungalowKt {
    private static ImageVector _bungalow;

    public static final ImageVector getBungalow(Icons.TwoTone twoTone) {
        ImageVector imageVector = _bungalow;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Bungalow", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(12.0f, 6.78f, -3.0f, 4.8f, 19.0f);
        v.a.o(pathBuilderA, 2.0f, -3.0f, 2.0f, 3.0f);
        d.m(pathBuilderA, 2.0f, -7.42f, 12.0f, 6.78f);
        v.a.C(pathBuilderA, 13.0f, 14.0f, -2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 2.0f, 14.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = v.a.k(13.0f, 14.0f, -2.0f, -2.0f, 2.0f);
        f.r(pathBuilderK, 14.0f, 18.1f, 16.56f);
        c.f(pathBuilderK, 17.0f, 14.79f, 21.0f, 7.0f);
        pathBuilderK.verticalLineToRelative(-6.2f);
        pathBuilderK.lineToRelative(-1.1f, 1.76f);
        pathBuilderK.lineTo(4.2f, 15.5f);
        pathBuilderK.lineTo(12.0f, 3.0f);
        d.C(pathBuilderK, 7.8f, 12.5f, 18.1f, 16.56f);
        pathBuilderK.moveTo(15.0f, 11.59f);
        pathBuilderK.lineToRelative(-3.0f, -4.8f);
        b.o(pathBuilderK, -3.0f, 4.8f, 19.0f, 2.0f);
        b.z(pathBuilderK, -3.0f, 2.0f, 3.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderK, 11.59f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bungalow = imageVectorBuild;
        return imageVectorBuild;
    }
}
