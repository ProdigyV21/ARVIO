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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_micOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MicOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getMicOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MicOffKt {
    private static ImageVector _micOff;

    public static final ImageVector getMicOff(Icons.Sharp sharp) {
        ImageVector imageVector = _micOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MicOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 10.6f, 15.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderJ.curveToRelative(-1.54f, 0.0f, -2.79f, 1.16f, -2.96f, 2.65f);
        c.z(pathBuilderJ, 15.0f, 10.6f, 19.0f, 11.0f);
        pathBuilderJ.horizontalLineToRelative(-1.7f);
        pathBuilderJ.curveToRelative(0.0f, 0.58f, -0.1f, 1.13f, -0.27f, 1.64f);
        pathBuilderJ.lineToRelative(1.27f, 1.27f);
        pathBuilderJ.curveToRelative(0.44f, -0.88f, 0.7f, -1.87f, 0.7f, -2.91f);
        a.p(pathBuilderJ, 4.41f, 2.86f, 3.0f, 4.27f);
        pathBuilderJ.lineToRelative(6.0f, 6.0f);
        pathBuilderJ.lineTo(9.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.23f, 0.0f, 0.44f, -0.03f, 0.65f, -0.08f);
        pathBuilderJ.lineToRelative(1.66f, 1.66f);
        pathBuilderJ.curveToRelative(-0.71f, 0.33f, -1.5f, 0.52f, -2.31f, 0.52f);
        pathBuilderJ.curveToRelative(-2.76f, 0.0f, -5.3f, -2.1f, -5.3f, -5.1f);
        pathBuilderJ.lineTo(5.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.41f, 2.72f, 6.23f, 6.0f, 6.72f);
        b.A(pathBuilderJ, 11.0f, 21.0f, 2.0f, -3.28f);
        pathBuilderJ.curveToRelative(0.91f, -0.13f, 1.77f, -0.45f, 2.55f, -0.9f);
        pathBuilderJ.lineToRelative(4.2f, 4.2f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 1.41f, -1.41f, 4.41f, 2.86f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _micOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
