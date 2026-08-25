package androidx.compose.material.icons.automirrored.sharp;

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
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rtt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rtt", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getRtt", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RttKt {
    private static ImageVector _rtt;

    public static final ImageVector getRtt(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _rtt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.Rtt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(9.03f, 3.0f, -1.11f, 7.07f, 2.62f);
        pathBuilderC.lineToRelative(0.7f, -4.5f);
        a.f(pathBuilderC, 2.58f, 11.8f, 18.43f, 9.47f);
        pathBuilderC.lineTo(9.06f, 21.0f);
        e.C(pathBuilderC, 7.27f, 0.4f, -2.57f, -2.35f);
        pathBuilderC.lineToRelative(2.0f, -12.86f);
        e.C(pathBuilderC, 2.58f, -0.71f, 4.5f, 2.65f);
        a.e(pathBuilderC, 22.0f, 3.0f, 9.03f);
        pathBuilderC.moveTo(8.0f, 5.0f);
        pathBuilderC.horizontalLineTo(4.0f);
        pathBuilderC.lineTo(3.69f, 7.0f);
        pathBuilderC.horizontalLineToRelative(4.0f);
        c.z(pathBuilderC, 8.0f, 5.0f, 7.39f, 9.0f);
        e.C(pathBuilderC, -4.0f, -0.31f, 2.0f, 4.0f);
        c.z(pathBuilderC, 7.39f, 9.0f, 8.31f, 17.0f);
        c.A(pathBuilderC, -6.0f, 2.0f, 19.0f, 6.0f);
        c.z(pathBuilderC, 8.31f, 17.0f, 8.93f, 13.0f);
        e.C(pathBuilderC, -6.0f, -0.31f, 2.0f, 6.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderC, 8.93f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rtt = imageVectorBuild;
        return imageVectorBuild;
    }
}
