package androidx.compose.material.icons.rounded;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cable", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cable", "Landroidx/compose/material/icons/Icons$Rounded;", "getCable", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CableKt {
    private static ImageVector _cable;

    public static final ImageVector getCable(Icons.Rounded rounded) {
        ImageVector imageVector = _cable;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Cable", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(20.0f, 5.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.lineToRelative(0.0f, 6.91f);
        pathBuilderA.curveToRelative(0.0f, 1.04f, -0.76f, 1.98f, -1.79f, 2.08f);
        pathBuilderA.curveTo(14.01f, 19.11f, 13.0f, 18.17f, 13.0f, 17.0f);
        pathBuilderA.lineToRelative(0.0f, -9.86f);
        pathBuilderA.curveToRelative(0.0f, -2.13f, -1.61f, -3.99f, -3.74f, -4.13f);
        pathBuilderA.curveTo(6.93f, 2.86f, 5.0f, 4.7f, 5.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.lineToRelative(0.0f, -6.91f);
        pathBuilderA.curveToRelative(0.0f, -1.04f, 0.76f, -1.98f, 1.79f, -2.08f);
        pathBuilderA.curveTo(9.99f, 4.89f, 11.0f, 5.83f, 11.0f, 7.0f);
        pathBuilderA.lineToRelative(0.0f, 9.86f);
        pathBuilderA.curveToRelative(0.0f, 2.13f, 1.61f, 3.99f, 3.74f, 4.13f);
        pathBuilderA.curveTo(17.07f, 21.14f, 19.0f, 19.3f, 19.0f, 17.0f);
        pathBuilderA.verticalLineToRelative(-7.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveTo(21.0f, 5.45f, 20.55f, 5.0f, 20.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cable = imageVectorBuild;
        return imageVectorBuild;
    }
}
