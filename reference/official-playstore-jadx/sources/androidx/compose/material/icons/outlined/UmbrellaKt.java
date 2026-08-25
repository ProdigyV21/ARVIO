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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_umbrella", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Umbrella", "Landroidx/compose/material/icons/Icons$Outlined;", "getUmbrella", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UmbrellaKt {
    private static ImageVector _umbrella;

    public static final ImageVector getUmbrella(Icons.Outlined outlined) {
        ImageVector imageVector = _umbrella;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Umbrella", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(14.5f, 6.92f, 13.0f, 5.77f, 3.88f);
        pathBuilderN.verticalLineTo(3.4f);
        pathBuilderN.curveToRelative(0.0f, -0.26f, 0.22f, -0.48f, 0.5f, -0.48f);
        pathBuilderN.curveToRelative(0.28f, 0.0f, 0.5f, 0.21f, 0.5f, 0.48f);
        c.i(pathBuilderN, 4.0f, 2.0f, 3.4f);
        pathBuilderN.curveTo(16.0f, 2.07f, 14.88f, 1.0f, 13.5f, 1.0f);
        pathBuilderN.curveTo(12.12f, 1.0f, 11.0f, 2.07f, 11.0f, 3.4f);
        pathBuilderN.verticalLineToRelative(0.48f);
        pathBuilderN.verticalLineToRelative(1.89f);
        pathBuilderN.lineTo(9.5f, 6.92f);
        pathBuilderN.lineTo(6.0f, 6.07f);
        pathBuilderN.lineToRelative(5.05f, 15.25f);
        pathBuilderN.curveTo(11.2f, 21.77f, 11.6f, 22.0f, 12.0f, 22.0f);
        pathBuilderN.reflectiveCurveToRelative(0.8f, -0.23f, 0.95f, -0.69f);
        b.y(pathBuilderN, 18.0f, 6.07f, 14.5f, 6.92f);
        pathBuilderN.moveTo(13.28f, 8.5f);
        pathBuilderN.lineToRelative(0.76f, 0.58f);
        pathBuilderN.lineToRelative(0.92f, -0.23f);
        pathBuilderN.lineTo(13.0f, 14.8f);
        c.x(pathBuilderN, 8.29f, 13.28f, 8.5f);
        pathBuilderN.moveTo(9.96f, 9.09f);
        pathBuilderN.lineToRelative(0.76f, -0.58f);
        pathBuilderN.lineTo(11.0f, 8.29f);
        pathBuilderN.verticalLineToRelative(6.51f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderN, 9.03f, 8.86f, 9.96f, 9.09f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _umbrella = imageVectorBuild;
        return imageVectorBuild;
    }
}
