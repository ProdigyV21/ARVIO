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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_rtt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rtt", "Landroidx/compose/material/icons/Icons$Filled;", "getRtt$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getRtt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RttKt {
    private static ImageVector _rtt;

    public static final ImageVector getRtt(Icons.Filled filled) {
        ImageVector imageVector = _rtt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Rtt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(9.03f, 3.0f, -1.11f, 7.07f, 2.62f);
        pathBuilderC.lineToRelative(0.7f, -4.5f);
        pathBuilderC.horizontalLineToRelative(2.58f);
        pathBuilderC.lineTo(11.8f, 18.43f);
        pathBuilderC.lineTo(9.47f, 18.43f);
        pathBuilderC.lineTo(9.06f, 21.0f);
        e.C(pathBuilderC, 7.27f, 0.4f, -2.57f, -2.35f);
        pathBuilderC.lineToRelative(2.0f, -12.86f);
        e.C(pathBuilderC, 2.58f, -0.71f, 4.5f, 2.65f);
        b.y(pathBuilderC, 22.0f, 3.0f, 9.03f, 3.0f);
        pathBuilderC.moveTo(8.0f, 5.0f);
        pathBuilderC.lineTo(4.0f, 5.0f);
        pathBuilderC.lineToRelative(-0.31f, 2.0f);
        d.q(pathBuilderC, 4.0f, 8.0f, 5.0f);
        pathBuilderC.moveTo(7.39f, 9.0f);
        e.C(pathBuilderC, -4.0f, -0.31f, 2.0f, 4.0f);
        b.C(pathBuilderC, 0.31f, -2.0f, 8.31f, 17.0f);
        c.A(pathBuilderC, -6.0f, 2.0f, 19.0f, 6.0f);
        b.C(pathBuilderC, 0.31f, -2.0f, 8.93f, 13.0f);
        e.C(pathBuilderC, -6.0f, -0.31f, 2.0f, 6.01f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderC, 0.3f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rtt = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getRtt$annotations(Icons.Filled filled) {
    }
}
