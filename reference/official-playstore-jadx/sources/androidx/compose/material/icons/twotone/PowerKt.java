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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_power", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Power", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPower", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PowerKt {
    private static ImageVector _power;

    public static final ImageVector getPower(Icons.TwoTone twoTone) {
        ImageVector imageVector = _power;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Power", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(8.0f, 13.65f, 3.5f, 3.52f, 19.0f);
        f.y(pathBuilderA, 1.0f, -1.83f, 3.5f, -3.51f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderA, 9.0f, 8.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(16.0f, 7.0f, 16.0f, 3.0f, -2.0f);
        c.w(pathBuilderT, 4.0f, -4.0f, 10.0f, 3.0f);
        v.a.h(pathBuilderT, 8.0f, 3.0f, 4.0f, -0.01f);
        pathBuilderT.curveTo(6.89f, 7.0f, 6.0f, 7.89f, 6.0f, 8.98f);
        d.r(pathBuilderT, 5.52f, 9.5f, 18.0f, 3.0f);
        f.y(pathBuilderT, 5.0f, -3.0f, 3.5f, -3.5f);
        pathBuilderT.lineTo(18.0f, 9.0f);
        pathBuilderT.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        v.a.z(pathBuilderT, 16.0f, 13.66f, -3.5f, 3.51f);
        b.A(pathBuilderT, 12.5f, 19.0f, -1.0f, -1.83f);
        pathBuilderT.lineTo(8.0f, 13.65f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderT, 8.0f, 9.0f, 8.0f, 4.66f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _power = imageVectorBuild;
        return imageVectorBuild;
    }
}
