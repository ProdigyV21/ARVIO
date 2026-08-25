package androidx.compose.material.icons.outlined;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pinch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pinch", "Landroidx/compose/material/icons/Icons$Outlined;", "getPinch", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinchKt {
    private static ImageVector _pinch;

    public static final ImageVector getPinch(Icons.Outlined outlined) {
        ImageVector imageVector = _pinch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Pinch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(6.0f, 2.5f, 1.0f, 5.0f, 5.0f);
        pathBuilderA.horizontalLineTo(9.5f);
        pathBuilderA.verticalLineTo(3.56f);
        pathBuilderA.lineTo(3.56f, 9.5f);
        a.x(pathBuilderA, 6.0f, 11.0f, 1.0f, 6.0f);
        c.q(pathBuilderA, 1.5f, 2.44f, 8.44f, 2.5f);
        v.a.x(pathBuilderA, 6.0f, 21.89f, 13.77f);
        pathBuilderA.lineToRelative(-3.8f, -1.67f);
        pathBuilderA.curveTo(17.96f, 12.04f, 17.81f, 12.0f, 17.65f, 12.0f);
        pathBuilderA.horizontalLineTo(17.0f);
        pathBuilderA.verticalLineTo(7.5f);
        pathBuilderA.curveTo(17.0f, 6.12f, 15.88f, 5.0f, 14.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(12.0f, 6.12f, 12.0f, 7.5f);
        pathBuilderA.verticalLineToRelative(8.15f);
        pathBuilderA.lineToRelative(-1.87f, -0.4f);
        pathBuilderA.curveToRelative(-0.19f, -0.03f, -1.02f, -0.15f, -1.73f, 0.56f);
        pathBuilderA.lineTo(7.0f, 17.22f);
        pathBuilderA.lineToRelative(5.12f, 5.19f);
        pathBuilderA.curveTo(12.49f, 22.79f, 13.0f, 23.0f, 13.53f, 23.0f);
        pathBuilderA.horizontalLineToRelative(6.55f);
        pathBuilderA.curveToRelative(0.98f, 0.0f, 1.81f, -0.7f, 1.97f, -1.67f);
        pathBuilderA.lineToRelative(0.92f, -5.44f);
        pathBuilderA.curveTo(23.12f, 15.03f, 22.68f, 14.17f, 21.89f, 13.77f);
        v.a.g(pathBuilderA, 20.08f, 21.0f, -6.55f);
        pathBuilderA.lineToRelative(-3.7f, -3.78f);
        pathBuilderA.lineTo(14.0f, 18.11f);
        pathBuilderA.verticalLineTo(7.5f);
        pathBuilderA.curveTo(14.0f, 7.22f, 14.22f, 7.0f, 14.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(15.0f, 7.22f, 15.0f, 7.5f);
        c.w(pathBuilderA, 6.18f, 1.76f, 21.0f, 15.56f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 20.08f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pinch = imageVectorBuild;
        return imageVectorBuild;
    }
}
