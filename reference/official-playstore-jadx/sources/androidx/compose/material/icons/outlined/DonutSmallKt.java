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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutSmall", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutSmall", "Landroidx/compose/material/icons/Icons$Outlined;", "getDonutSmall", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutSmallKt {
    private static ImageVector _donutSmall;

    public static final ImageVector getDonutSmall(Icons.Outlined outlined) {
        ImageVector imageVector = _donutSmall;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DonutSmall", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(14.82f, 11.0f, 7.13f);
        pathBuilderA.curveToRelative(-0.47f, -4.72f, -4.23f, -8.48f, -8.95f, -8.95f);
        pathBuilderA.verticalLineToRelative(7.13f);
        pathBuilderA.curveToRelative(0.85f, 0.31f, 1.51f, 0.97f, 1.82f, 1.82f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 4.58f);
        pathBuilderA.curveTo(17.0f, 5.4f, 18.6f, 7.0f, 19.42f, 9.0f);
        pathBuilderA.horizontalLineToRelative(-3.43f);
        pathBuilderA.curveToRelative(-0.28f, -0.37f, -0.62f, -0.71f, -0.99f, -0.99f);
        c.z(pathBuilderA, 15.0f, 4.58f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.19f, 3.95f, 9.45f, 9.0f, 9.95f);
        pathBuilderA.verticalLineToRelative(-7.13f);
        pathBuilderA.curveTo(9.84f, 14.4f, 9.0f, 13.3f, 9.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.3f, 0.84f, -2.4f, 2.0f, -2.82f);
        pathBuilderA.lineTo(11.0f, 2.05f);
        pathBuilderA.curveToRelative(-5.05f, 0.5f, -9.0f, 4.76f, -9.0f, 9.95f);
        d.k(pathBuilderA, 9.0f, 4.58f, 3.44f);
        pathBuilderA.curveToRelative(-1.23f, 0.92f, -2.0f, 2.39f, -2.0f, 3.98f);
        pathBuilderA.curveToRelative(0.0f, 1.59f, 0.77f, 3.06f, 2.0f, 3.99f);
        pathBuilderA.verticalLineToRelative(3.44f);
        pathBuilderA.curveTo(6.04f, 18.24f, 4.0f, 15.35f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -3.35f, 2.04f, -6.24f, 5.0f, -7.42f);
        d.k(pathBuilderA, 13.0f, 14.82f, 7.13f);
        pathBuilderA.curveToRelative(4.72f, -0.47f, 8.48f, -4.23f, 8.95f, -8.95f);
        pathBuilderA.horizontalLineToRelative(-7.13f);
        pathBuilderA.curveToRelative(-0.31f, 0.85f, -0.97f, 1.51f, -1.82f, 1.82f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 15.99f);
        pathBuilderA.curveToRelative(0.37f, -0.28f, 0.71f, -0.61f, 0.99f, -0.99f);
        pathBuilderA.horizontalLineToRelative(3.43f);
        pathBuilderA.curveTo(18.6f, 17.0f, 17.0f, 18.6f, 15.0f, 19.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderA, -3.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _donutSmall = imageVectorBuild;
        return imageVectorBuild;
    }
}
