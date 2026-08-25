package androidx.compose.material.icons.filled;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_strikethroughS", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StrikethroughS", "Landroidx/compose/material/icons/Icons$Filled;", "getStrikethroughS", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrikethroughSKt {
    private static ImageVector _strikethroughS;

    public static final ImageVector getStrikethroughS(Icons.Filled filled) {
        ImageVector imageVector = _strikethroughS;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.StrikethroughS", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.85f, 7.08f);
        pathBuilderA.curveTo(6.85f, 4.37f, 9.45f, 3.0f, 12.24f, 3.0f);
        pathBuilderA.curveToRelative(1.64f, 0.0f, 3.0f, 0.49f, 3.9f, 1.28f);
        pathBuilderA.curveToRelative(0.77f, 0.65f, 1.46f, 1.73f, 1.46f, 3.24f);
        pathBuilderA.horizontalLineToRelative(-3.01f);
        pathBuilderA.curveToRelative(0.0f, -0.31f, -0.05f, -0.59f, -0.15f, -0.85f);
        pathBuilderA.curveToRelative(-0.29f, -0.86f, -1.2f, -1.28f, -2.25f, -1.28f);
        pathBuilderA.curveToRelative(-1.86f, 0.0f, -2.34f, 1.02f, -2.34f, 1.7f);
        pathBuilderA.curveToRelative(0.0f, 0.48f, 0.25f, 0.88f, 0.74f, 1.21f);
        pathBuilderA.curveTo(10.97f, 8.55f, 11.36f, 8.78f, 12.0f, 9.0f);
        pathBuilderA.horizontalLineTo(7.39f);
        pathBuilderA.curveTo(7.18f, 8.66f, 6.85f, 8.11f, 6.85f, 7.08f);
        a.z(pathBuilderA, 21.0f, 12.0f, -2.0f, 3.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.horizontalLineToRelative(9.62f);
        pathBuilderA.curveToRelative(1.15f, 0.45f, 1.96f, 0.75f, 1.96f, 1.97f);
        pathBuilderA.curveToRelative(0.0f, 1.0f, -0.81f, 1.67f, -2.28f, 1.67f);
        pathBuilderA.curveToRelative(-1.54f, 0.0f, -2.93f, -0.54f, -2.93f, -2.51f);
        pathBuilderA.horizontalLineTo(6.4f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.08f, 1.13f, 0.24f, 1.58f);
        pathBuilderA.curveToRelative(0.81f, 2.29f, 3.29f, 3.3f, 5.67f, 3.3f);
        pathBuilderA.curveToRelative(2.27f, 0.0f, 5.3f, -0.89f, 5.3f, -4.05f);
        pathBuilderA.curveToRelative(0.0f, -0.3f, -0.01f, -1.16f, -0.48f, -1.94f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.m(pathBuilderA, 21.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _strikethroughS = imageVectorBuild;
        return imageVectorBuild;
    }
}
