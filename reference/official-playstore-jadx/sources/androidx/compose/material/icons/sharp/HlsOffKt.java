package androidx.compose.material.icons.sharp;

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
import v.a;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hlsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HlsOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getHlsOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HlsOffKt {
    private static ImageVector _hlsOff;

    public static final ImageVector getHlsOff(Icons.Sharp sharp) {
        ImageVector imageVector = _hlsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HlsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(17.83f, 15.0f, 2.67f, -3.5f, 17.0f);
        a.y(pathBuilderH, -1.0f, 2.0f, 11.0f, 1.5f);
        e.x(pathBuilderH, 9.0f, -5.0f, 3.5f, 19.0f);
        a.y(pathBuilderH, 1.0f, -2.0f, 13.0f, -1.17f);
        c.z(pathBuilderH, 17.83f, 15.0f, 8.0f, 10.83f);
        c.o(pathBuilderH, 15.0f, 6.5f, -2.5f, -2.0f);
        b.p(pathBuilderH, 15.0f, 3.0f, 9.0f, 1.5f);
        f.t(pathBuilderH, 2.0f, 2.0f, 9.33f);
        pathBuilderH.lineTo(1.39f, 4.22f);
        pathBuilderH.lineToRelative(1.41f, -1.41f);
        pathBuilderH.lineToRelative(18.38f, 18.38f);
        pathBuilderH.lineToRelative(-1.41f, 1.41f);
        v.b.m(pathBuilderH, 12.17f, 15.0f, 10.0f, -2.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderH, 8.0f, 10.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hlsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
