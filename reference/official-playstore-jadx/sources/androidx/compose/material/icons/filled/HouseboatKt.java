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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_houseboat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Houseboat", "Landroidx/compose/material/icons/Icons$Filled;", "getHouseboat", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HouseboatKt {
    private static ImageVector _houseboat;

    public static final ImageVector getHouseboat(Icons.Filled filled) {
        ImageVector imageVector = _houseboat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Houseboat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 16.0f);
        pathBuilderA.curveToRelative(-1.95f, 0.0f, -2.1f, 1.0f, -3.34f, 1.0f);
        pathBuilderA.curveToRelative(-1.19f, 0.0f, -1.42f, -1.0f, -3.33f, -1.0f);
        pathBuilderA.curveTo(3.38f, 16.0f, 3.24f, 17.0f, 2.0f, 17.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(1.9f, 0.0f, 2.17f, -1.0f, 3.35f, -1.0f);
        pathBuilderA.curveToRelative(1.19f, 0.0f, 1.42f, 1.0f, 3.33f, 1.0f);
        pathBuilderA.curveToRelative(1.95f, 0.0f, 2.08f, -1.0f, 3.32f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.37f, 1.0f, 3.32f, 1.0f);
        pathBuilderA.curveToRelative(1.91f, 0.0f, 2.14f, -1.0f, 3.33f, -1.0f);
        pathBuilderA.curveToRelative(1.18f, 0.0f, 1.45f, 1.0f, 3.35f, 1.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-1.24f, 0.0f, -1.38f, -1.0f, -3.33f, -1.0f);
        pathBuilderA.curveToRelative(-1.91f, 0.0f, -2.14f, 1.0f, -3.33f, 1.0f);
        pathBuilderA.curveTo(14.1f, 17.0f, 13.95f, 16.0f, 12.0f, 16.0f);
        a.z(pathBuilderA, 20.34f, 11.34f, -1.37f, 1.37f);
        pathBuilderA.curveTo(18.78f, 12.89f, 18.52f, 13.0f, 18.26f, 13.0f);
        pathBuilderA.horizontalLineTo(17.0f);
        pathBuilderA.verticalLineTo(9.65f);
        pathBuilderA.lineToRelative(1.32f, 0.97f);
        pathBuilderA.lineTo(19.5f, 9.0f);
        pathBuilderA.lineTo(12.0f, 3.5f);
        pathBuilderA.lineTo(4.5f, 9.0f);
        pathBuilderA.lineToRelative(1.18f, 1.61f);
        c.f(pathBuilderA, 7.0f, 9.65f, 13.0f, 5.74f);
        pathBuilderA.curveToRelative(-0.27f, 0.0f, -0.52f, -0.11f, -0.71f, -0.29f);
        pathBuilderA.lineToRelative(-1.37f, -1.37f);
        pathBuilderA.lineToRelative(-1.41f, 1.41f);
        pathBuilderA.lineToRelative(1.37f, 1.37f);
        pathBuilderA.curveTo(4.18f, 14.68f, 4.95f, 15.0f, 5.74f, 15.0f);
        pathBuilderA.horizontalLineToRelative(12.51f);
        pathBuilderA.curveToRelative(0.8f, 0.0f, 1.56f, -0.32f, 2.12f, -0.88f);
        d.C(pathBuilderA, 1.37f, -1.37f, 20.34f, 11.34f);
        a.C(pathBuilderA, 13.0f, 13.0f, -2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 2.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _houseboat = imageVectorBuild;
        return imageVectorBuild;
    }
}
