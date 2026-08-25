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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nestCamWiredStand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NestCamWiredStand", "Landroidx/compose/material/icons/Icons$Outlined;", "getNestCamWiredStand", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NestCamWiredStandKt {
    private static ImageVector _nestCamWiredStand;

    public static final ImageVector getNestCamWiredStand(Icons.Outlined outlined) {
        ImageVector imageVector = _nestCamWiredStand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NestCamWiredStand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 1.0f);
        pathBuilderA.curveToRelative(-0.15f, 0.0f, 0.11f, -0.02f, -4.28f, 0.42f);
        pathBuilderA.curveTo(8.47f, 1.75f, 6.0f, 4.48f, 6.0f, 7.75f);
        pathBuilderA.reflectiveCurveToRelative(2.47f, 6.0f, 5.72f, 6.33f);
        pathBuilderA.lineToRelative(1.9f, 0.19f);
        pathBuilderA.lineToRelative(-0.56f, 0.85f);
        pathBuilderA.curveTo(12.71f, 15.04f, 12.36f, 15.0f, 12.0f, 15.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.67f, -0.83f, -3.15f, -2.09f, -4.06f);
        pathBuilderA.lineToRelative(0.97f, -1.45f);
        pathBuilderA.curveToRelative(0.04f, 0.0f, 0.09f, 0.01f, 0.13f, 0.01f);
        pathBuilderA.curveToRelative(1.09f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.curveTo(18.0f, 1.89f, 17.09f, 1.0f, 16.0f, 1.0f);
        c.C(pathBuilderA, 15.0f, 21.0f, 9.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -1.65f, 1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        f.r(pathBuilderA, 21.0f, 8.0f, 7.75f);
        pathBuilderA.curveToRelative(0.0f, -2.25f, 1.69f, -4.11f, 3.92f, -4.34f);
        pathBuilderA.lineTo(16.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.lineToRelative(0.03f, 9.5f);
        pathBuilderA.lineToRelative(-4.11f, -0.42f);
        pathBuilderA.curveTo(9.69f, 11.86f, 8.0f, 10.0f, 8.0f, 7.75f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nestCamWiredStand = imageVectorBuild;
        return imageVectorBuild;
    }
}
