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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsBar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsBar", "Landroidx/compose/material/icons/Icons$Filled;", "getSportsBar", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsBarKt {
    private static ImageVector _sportsBar;

    public static final ImageVector getSportsBar(Icons.Filled filled) {
        ImageVector imageVector = _sportsBar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SportsBar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(19.0f, 9.0f, -1.56f);
        pathBuilderA.curveTo(17.79f, 8.41f, 18.0f, 7.73f, 18.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(-0.34f, 0.0f, -0.66f, 0.05f, -0.98f, 0.13f);
        pathBuilderA.curveTo(12.2f, 2.45f, 11.16f, 2.02f, 10.0f, 2.02f);
        pathBuilderA.curveToRelative(-1.89f, 0.0f, -3.51f, 1.11f, -4.27f, 2.71f);
        pathBuilderA.curveTo(4.15f, 5.26f, 3.0f, 6.74f, 3.0f, 8.5f);
        pathBuilderA.curveToRelative(0.0f, 1.86f, 1.28f, 3.41f, 3.0f, 3.86f);
        b.A(pathBuilderA, 6.0f, 21.0f, 11.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveTo(21.0f, 9.9f, 20.1f, 9.0f, 19.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.0f, 10.5f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.85f, 0.55f, -1.6f, 1.37f, -1.88f);
        pathBuilderA.lineToRelative(0.8f, -0.27f);
        pathBuilderA.lineToRelative(0.36f, -0.76f);
        pathBuilderA.curveTo(8.0f, 4.62f, 8.94f, 4.02f, 10.0f, 4.02f);
        pathBuilderA.curveToRelative(0.79f, 0.0f, 1.39f, 0.35f, 1.74f, 0.65f);
        pathBuilderA.lineToRelative(0.78f, 0.65f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.64f, -0.32f, 1.47f, -0.32f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, -3.0f, 0.0f, -3.0f, 0.0f);
        pathBuilderA.curveTo(9.67f, 7.0f, 9.15f, 10.5f, 7.0f, 10.5f);
        b.w(pathBuilderA, 19.0f, 17.0f, -2.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 2.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsBar = imageVectorBuild;
        return imageVectorBuild;
    }
}
