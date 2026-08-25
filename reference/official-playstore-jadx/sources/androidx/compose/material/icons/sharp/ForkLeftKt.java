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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forkLeft", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ForkLeft", "Landroidx/compose/material/icons/Icons$Sharp;", "getForkLeft", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForkLeftKt {
    private static ImageVector _forkLeft;

    public static final ImageVector getForkLeft(Icons.Sharp sharp) {
        ImageVector imageVector = _forkLeft;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ForkLeft", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(9.41f, 15.59f, 8.0f, 17.0f);
        pathBuilderJ.lineToRelative(-4.0f, -4.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.lineToRelative(1.41f, 1.41f);
        pathBuilderJ.lineTo(7.83f, 12.0f);
        pathBuilderJ.curveToRelative(1.51f, -0.33f, 3.73f, 0.08f, 5.17f, 1.36f);
        pathBuilderJ.lineToRelative(0.0f, -6.53f);
        pathBuilderJ.lineToRelative(-1.59f, 1.59f);
        pathBuilderJ.lineTo(10.0f, 7.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.lineToRelative(4.0f, 4.0f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineTo(15.0f, 6.83f);
        a0.a.k(pathBuilderJ, 21.0f, -2.0f, 0.0f, -4.0f);
        pathBuilderJ.curveToRelative(-0.73f, -2.58f, -3.07f, -3.47f, -5.17f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 9.41f, 15.59f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _forkLeft = imageVectorBuild;
        return imageVectorBuild;
    }
}
