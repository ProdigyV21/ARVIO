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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wavingHand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WavingHand", "Landroidx/compose/material/icons/Icons$Sharp;", "getWavingHand", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WavingHandKt {
    private static ImageVector _wavingHand;

    public static final ImageVector getWavingHand(Icons.Sharp sharp) {
        ImageVector imageVector = _wavingHand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.WavingHand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.0f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(-1.5f);
        pathBuilderA.curveToRelative(2.48f, 0.0f, 4.5f, -2.02f, 4.5f, -4.5f);
        a.x(pathBuilderA, 23.0f, 1.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -3.31f, 2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA.verticalLineToRelative(1.5f);
        pathBuilderA.curveTo(4.52f, 2.5f, 2.5f, 4.52f, 2.5f, 7.0f);
        a.x(pathBuilderA, 1.0f, 8.9f, 3.43f);
        pathBuilderA.lineTo(3.42f, 8.91f);
        pathBuilderA.curveToRelative(-3.22f, 3.22f, -3.22f, 8.44f, 0.0f, 11.67f);
        pathBuilderA.reflectiveCurveToRelative(8.44f, 3.22f, 11.67f, 0.0f);
        pathBuilderA.lineToRelative(7.95f, -7.95f);
        pathBuilderA.lineToRelative(-1.77f, -1.77f);
        pathBuilderA.lineToRelative(-5.3f, 5.3f);
        pathBuilderA.lineToRelative(-0.71f, -0.71f);
        pathBuilderA.lineToRelative(7.42f, -7.42f);
        pathBuilderA.lineToRelative(-1.77f, -1.77f);
        pathBuilderA.lineToRelative(-6.72f, 6.72f);
        pathBuilderA.lineToRelative(-0.71f, -0.71f);
        pathBuilderA.lineToRelative(7.78f, -7.78f);
        pathBuilderA.lineTo(19.5f, 2.73f);
        pathBuilderA.lineToRelative(-7.78f, 7.78f);
        pathBuilderA.lineTo(11.02f, 9.8f);
        pathBuilderA.lineToRelative(6.36f, -6.36f);
        pathBuilderA.lineToRelative(-1.77f, -1.77f);
        pathBuilderA.lineToRelative(-8.51f, 8.51f);
        pathBuilderA.curveToRelative(1.22f, 1.57f, 1.11f, 3.84f, -0.33f, 5.28f);
        pathBuilderA.lineToRelative(-0.71f, -0.71f);
        pathBuilderA.curveToRelative(1.17f, -1.17f, 1.17f, -3.08f, 0.0f, -4.24f);
        pathBuilderA.lineToRelative(-0.35f, -0.35f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 4.95f, -4.95f, 8.9f, 3.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wavingHand = imageVectorBuild;
        return imageVectorBuild;
    }
}
