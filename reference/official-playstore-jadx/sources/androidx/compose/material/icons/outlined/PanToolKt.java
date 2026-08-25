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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panTool", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanTool", "Landroidx/compose/material/icons/Icons$Outlined;", "getPanTool", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanToolKt {
    private static ImageVector _panTool;

    public static final ImageVector getPanTool(Icons.Outlined outlined) {
        ImageVector imageVector = _panTool;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PanTool", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(18.0f, 24.0f, -6.55f);
        pathBuilderA.curveToRelative(-1.08f, 0.0f, -2.14f, -0.45f, -2.89f, -1.23f);
        pathBuilderA.lineToRelative(-7.3f, -7.61f);
        pathBuilderA.lineToRelative(2.07f, -1.83f);
        pathBuilderA.curveToRelative(0.62f, -0.55f, 1.53f, -0.66f, 2.26f, -0.27f);
        pathBuilderA.lineTo(8.0f, 14.34f);
        pathBuilderA.verticalLineTo(4.79f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.17f, 0.0f, 0.34f, 0.02f, 0.51f, 0.05f);
        pathBuilderA.curveToRelative(0.09f, -1.3f, 1.17f, -2.33f, 2.49f, -2.33f);
        pathBuilderA.curveToRelative(0.86f, 0.0f, 1.61f, 0.43f, 2.06f, 1.09f);
        pathBuilderA.curveToRelative(0.29f, -0.12f, 0.61f, -0.18f, 0.94f, -0.18f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.verticalLineToRelative(0.28f);
        pathBuilderA.curveToRelative(0.16f, -0.03f, 0.33f, -0.05f, 0.5f, -0.05f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        a.z(pathBuilderA, 4.14f, 15.28f, 5.86f, 6.1f);
        pathBuilderA.curveToRelative(0.38f, 0.39f, 0.9f, 0.62f, 1.44f, 0.62f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(6.15f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 3.42f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 2.51f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        c.i(pathBuilderA, 12.0f, -2.0f, 4.79f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, 0.23f, -0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(12.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -5.35f, -2.83f, -0.51f, 0.45f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panTool = imageVectorBuild;
        return imageVectorBuild;
    }
}
