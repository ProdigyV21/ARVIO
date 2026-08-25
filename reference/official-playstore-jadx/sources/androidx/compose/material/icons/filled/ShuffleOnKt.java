package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.b;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shuffleOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShuffleOn", "Landroidx/compose/material/icons/Icons$Filled;", "getShuffleOn", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShuffleOnKt {
    private static ImageVector _shuffleOn;

    public static final ImageVector getShuffleOn(Icons.Filled filled) {
        ImageVector imageVector = _shuffleOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ShuffleOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.0f, 1.0f, 3.0f, 1.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(23.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 10.59f, 9.17f, 5.41f, 4.0f);
        pathBuilderJ.lineTo(4.0f, 5.41f);
        b.D(pathBuilderJ, 5.17f, 5.17f, 1.42f, -1.41f);
        pathBuilderJ.moveTo(14.5f, 4.0f);
        pathBuilderJ.lineToRelative(2.04f, 2.04f);
        pathBuilderJ.lineTo(4.0f, 18.59f);
        pathBuilderJ.lineTo(5.41f, 20.0f);
        pathBuilderJ.lineTo(17.96f, 7.46f);
        pathBuilderJ.lineTo(20.0f, 9.5f);
        f.n(pathBuilderJ, 20.0f, 4.0f, -5.5f);
        pathBuilderJ.moveTo(14.83f, 13.41f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineToRelative(3.13f, 3.13f);
        pathBuilderJ.lineTo(14.5f, 20.0f);
        pathBuilderJ.lineTo(20.0f, 20.0f);
        pathBuilderJ.verticalLineToRelative(-5.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, -2.04f, 2.04f, -3.13f, -3.13f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shuffleOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
