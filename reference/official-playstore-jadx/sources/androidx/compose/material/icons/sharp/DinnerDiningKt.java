package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dinnerDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DinnerDining", "Landroidx/compose/material/icons/Icons$Sharp;", "getDinnerDining", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DinnerDiningKt {
    private static ImageVector _dinnerDining;

    public static final ImageVector getDinnerDining(Icons.Sharp sharp) {
        ImageVector imageVector = _dinnerDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DinnerDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(2.0f, 19.0f, 2.0f, 2.0f), 16.0f, 0.0f, 2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(3.0f, 18.0f, 16.97f, 0.0f);
        pathBuilderR.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderR.curveToRelative(0.29f, -3.26f, -2.28f, -6.0f, -5.48f, -6.0f);
        pathBuilderR.curveToRelative(-2.35f, 0.0f, -4.35f, 1.48f, -5.14f, 3.55f);
        pathBuilderR.curveTo(8.94f, 15.32f, 8.48f, 15.17f, 8.0f, 15.08f);
        d.A(pathBuilderR, 9.0f, 4.0f, 6.75f, 9.0f);
        c.p(pathBuilderR, -1.5f, -9.0f, 3.0f, 3.0f);
        f.u(pathBuilderR, 1.5f, 1.0f, 0.75f, 3.0f);
        c.p(pathBuilderR, 1.5f, 1.0f, 7.5f, 3.0f);
        w.a.j(pathBuilderR, 9.0f, 1.0f, 7.39f);
        pathBuilderR.curveTo(3.56f, 16.85f, 3.22f, 17.39f, 3.0f, 18.0f);
        a0.b.t(pathBuilderR, 8.0f, 7.5f, 6.75f, 2.0f);
        c.n(pathBuilderR, 7.5f, 8.0f, 10.0f, 5.25f);
        w.a.l(pathBuilderR, 8.0f, 4.5f, 2.0f, 5.25f);
        a.C(pathBuilderR, 5.5f, 4.5f, 1.0f, 0.75f);
        c.D(pathBuilderR, -1.0f, 4.5f, 5.5f, 6.75f);
        f.A(pathBuilderR, 1.0f, 7.5f, -1.0f, 6.75f);
        pathBuilderR.moveTo(6.5f, 9.0f);
        pathBuilderR.verticalLineToRelative(6.06f);
        pathBuilderR.curveToRelative(-0.35f, 0.06f, -0.68f, 0.17f, -1.0f, 0.3f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderR, 9.0f, 6.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dinnerDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
