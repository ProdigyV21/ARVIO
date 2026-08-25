package androidx.compose.material.icons.outlined;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_colorize", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Colorize", "Landroidx/compose/material/icons/Icons$Outlined;", "getColorize", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorizeKt {
    private static ImageVector _colorize;

    public static final ImageVector getColorize(Icons.Outlined outlined) {
        ImageVector imageVector = _colorize;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Colorize", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.66f, 5.41f, 0.92f, 0.92f);
        pathBuilderR.lineToRelative(-2.69f, 2.69f);
        pathBuilderR.lineToRelative(-0.92f, -0.92f);
        pathBuilderR.lineToRelative(2.69f, -2.69f);
        pathBuilderR.moveTo(17.67f, 3.0f);
        pathBuilderR.curveToRelative(-0.26f, 0.0f, -0.51f, 0.1f, -0.71f, 0.29f);
        pathBuilderR.lineToRelative(-3.12f, 3.12f);
        pathBuilderR.lineToRelative(-1.93f, -1.91f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        b.n(pathBuilderR, 3.0f, 16.25f, 21.0f, 4.75f);
        pathBuilderR.lineToRelative(8.92f, -8.92f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(-1.92f, -1.92f);
        pathBuilderR.lineToRelative(3.12f, -3.12f);
        pathBuilderR.curveToRelative(0.4f, -0.4f, 0.4f, -1.03f, 0.01f, -1.42f);
        pathBuilderR.lineToRelative(-2.34f, -2.34f);
        pathBuilderR.curveToRelative(-0.2f, -0.19f, -0.45f, -0.29f, -0.7f, -0.29f);
        a.p(pathBuilderR, 6.92f, 19.0f, 5.0f, 17.08f);
        pathBuilderR.lineToRelative(8.06f, -8.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.92f, 1.92f, 6.92f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _colorize = imageVectorBuild;
        return imageVectorBuild;
    }
}
