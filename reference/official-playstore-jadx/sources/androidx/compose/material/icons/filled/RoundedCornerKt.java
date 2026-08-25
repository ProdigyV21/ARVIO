package androidx.compose.material.icons.filled;

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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_roundedCorner", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RoundedCorner", "Landroidx/compose/material/icons/Icons$Filled;", "getRoundedCorner", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoundedCornerKt {
    private static ImageVector _roundedCorner;

    public static final ImageVector getRoundedCorner(Icons.Filled filled) {
        ImageVector imageVector = _roundedCorner;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RoundedCorner", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(19.0f, 19.0f, 2.0f, 2.0f, -2.0f);
        a.D(pathBuilderK, 19.0f, 19.0f, 17.0f, 2.0f);
        a.t(pathBuilderK, -2.0f, -2.0f, 17.0f);
        a.C(pathBuilderK, 3.0f, 13.0f, 2.0f, -2.0f);
        c.l(pathBuilderK, 3.0f, 13.0f, 3.0f, 17.0f);
        d.z(pathBuilderK, 2.0f, -2.0f, 3.0f, 17.0f);
        e.m(pathBuilderK, 3.0f, 9.0f, 2.0f, 7.0f);
        c.l(pathBuilderK, 3.0f, 9.0f, 3.0f, 5.0f);
        a0.c.D(pathBuilderK, 2.0f, 3.0f, 3.0f, 5.0f);
        e.m(pathBuilderK, 7.0f, 5.0f, 2.0f, 3.0f);
        c.l(pathBuilderK, 7.0f, 5.0f, 15.0f, 21.0f);
        f.w(pathBuilderK, 2.0f, -2.0f, -2.0f, 21.0f);
        a.C(pathBuilderK, 11.0f, 21.0f, 2.0f, -2.0f);
        c.D(pathBuilderK, -2.0f, 21.0f, 15.0f, 21.0f);
        f.w(pathBuilderK, 2.0f, -2.0f, -2.0f, 21.0f);
        a.C(pathBuilderK, 7.0f, 21.0f, 2.0f, -2.0f);
        c.l(pathBuilderK, 7.0f, 21.0f, 3.0f, 21.0f);
        d.z(pathBuilderK, 2.0f, -2.0f, 3.0f, 21.0f);
        pathBuilderK.moveTo(21.0f, 8.0f);
        pathBuilderK.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        e.g(pathBuilderK, -5.0f, 2.0f, 5.0f);
        pathBuilderK.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderK, 5.0f, 2.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _roundedCorner = imageVectorBuild;
        return imageVectorBuild;
    }
}
