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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phonelinkOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhonelinkOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getPhonelinkOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhonelinkOffKt {
    private static ImageVector _phonelinkOff;

    public static final ImageVector getPhonelinkOff(Icons.Sharp sharp) {
        ImageVector imageVector = _phonelinkOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PhonelinkOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.56f, 4.0f, -2.5f, -2.49f);
        c.z(pathBuilderR, 4.56f, 4.0f, 24.0f, 8.0f);
        f.y(pathBuilderR, -8.0f, 4.61f, 2.0f, 2.0f);
        b.A(pathBuilderR, 18.0f, 10.0f, 4.0f, 7.0f);
        pathBuilderR.horizontalLineToRelative(-1.61f);
        d.C(pathBuilderR, 3.0f, 3.0f, 24.0f, 20.0f);
        pathBuilderR.moveTo(22.0f, 6.0f);
        pathBuilderR.lineTo(22.0f, 4.0f);
        d.l(pathBuilderR, 7.39f, 4.0f, 2.0f, 2.0f);
        pathBuilderR.moveTo(2.06f, 1.51f);
        pathBuilderR.lineTo(0.65f, 2.92f);
        pathBuilderR.lineTo(2.0f, 4.27f);
        pathBuilderR.lineTo(2.0f, 17.0f);
        a.h(pathBuilderR, 0.0f, 17.0f, 3.0f, 17.73f);
        pathBuilderR.lineToRelative(2.35f, 2.35f);
        d.C(pathBuilderR, 1.41f, -1.41f, 2.06f, 1.51f);
        pathBuilderR.moveTo(4.0f, 17.0f);
        pathBuilderR.lineTo(4.0f, 6.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 14.73f, 17.0f, 4.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phonelinkOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
