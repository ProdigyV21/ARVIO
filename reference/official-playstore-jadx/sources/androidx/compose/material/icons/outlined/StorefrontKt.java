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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_storefront", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Storefront", "Landroidx/compose/material/icons/Icons$Outlined;", "getStorefront", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StorefrontKt {
    private static ImageVector _storefront;

    public static final ImageVector getStorefront(Icons.Outlined outlined) {
        ImageVector imageVector = _storefront;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Storefront", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.9f, 8.89f, -1.05f, -4.37f);
        pathBuilderR.curveToRelative(-0.22f, -0.9f, -1.0f, -1.52f, -1.91f, -1.52f);
        pathBuilderR.horizontalLineTo(5.05f);
        pathBuilderR.curveTo(4.15f, 3.0f, 3.36f, 3.63f, 3.15f, 4.52f);
        pathBuilderR.lineTo(2.1f, 8.89f);
        pathBuilderR.curveToRelative(-0.24f, 1.02f, -0.02f, 2.06f, 0.62f, 2.88f);
        pathBuilderR.curveTo(2.8f, 11.88f, 2.91f, 11.96f, 3.0f, 12.06f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-6.94f);
        pathBuilderR.curveToRelative(0.09f, -0.09f, 0.2f, -0.18f, 0.28f, -0.28f);
        pathBuilderR.curveTo(21.92f, 10.96f, 22.15f, 9.91f, 21.9f, 8.89f);
        a.z(pathBuilderR, 18.91f, 4.99f, 1.05f, 4.37f);
        pathBuilderR.curveToRelative(0.1f, 0.42f, 0.01f, 0.84f, -0.25f, 1.17f);
        pathBuilderR.curveTo(19.57f, 10.71f, 19.27f, 11.0f, 18.77f, 11.0f);
        pathBuilderR.curveToRelative(-0.61f, 0.0f, -1.14f, -0.49f, -1.21f, -1.14f);
        b.y(pathBuilderR, 16.98f, 5.0f, 18.91f, 4.99f);
        pathBuilderR.moveTo(13.0f, 5.0f);
        pathBuilderR.horizontalLineToRelative(1.96f);
        pathBuilderR.lineToRelative(0.54f, 4.52f);
        pathBuilderR.curveToRelative(0.05f, 0.39f, -0.07f, 0.78f, -0.33f, 1.07f);
        pathBuilderR.curveTo(14.95f, 10.85f, 14.63f, 11.0f, 14.22f, 11.0f);
        pathBuilderR.curveTo(13.55f, 11.0f, 13.0f, 10.41f, 13.0f, 9.69f);
        f.r(pathBuilderR, 5.0f, 8.49f, 9.52f);
        b.m(pathBuilderR, 9.04f, 5.0f, 11.0f, 4.69f);
        pathBuilderR.curveTo(11.0f, 10.41f, 10.45f, 11.0f, 9.71f, 11.0f);
        pathBuilderR.curveToRelative(-0.34f, 0.0f, -0.65f, -0.15f, -0.89f, -0.41f);
        pathBuilderR.curveTo(8.57f, 10.3f, 8.45f, 9.91f, 8.49f, 9.52f);
        a.p(pathBuilderR, 4.04f, 9.36f, 5.05f, 5.0f);
        pathBuilderR.horizontalLineToRelative(1.97f);
        pathBuilderR.lineTo(6.44f, 9.86f);
        pathBuilderR.curveTo(6.36f, 10.51f, 5.84f, 11.0f, 5.23f, 11.0f);
        pathBuilderR.curveToRelative(-0.49f, 0.0f, -0.8f, -0.29f, -0.93f, -0.47f);
        pathBuilderR.curveTo(4.03f, 10.21f, 3.94f, 9.78f, 4.04f, 9.36f);
        d.k(pathBuilderR, 5.0f, 19.0f, -6.03f);
        pathBuilderR.curveTo(5.08f, 12.98f, 5.15f, 13.0f, 5.23f, 13.0f);
        pathBuilderR.curveToRelative(0.87f, 0.0f, 1.66f, -0.36f, 2.24f, -0.95f);
        pathBuilderR.curveToRelative(0.6f, 0.6f, 1.4f, 0.95f, 2.31f, 0.95f);
        pathBuilderR.curveToRelative(0.87f, 0.0f, 1.65f, -0.36f, 2.23f, -0.93f);
        pathBuilderR.curveToRelative(0.59f, 0.57f, 1.39f, 0.93f, 2.29f, 0.93f);
        pathBuilderR.curveToRelative(0.84f, 0.0f, 1.64f, -0.35f, 2.24f, -0.95f);
        pathBuilderR.curveToRelative(0.58f, 0.59f, 1.37f, 0.95f, 2.24f, 0.95f);
        pathBuilderR.curveToRelative(0.08f, 0.0f, 0.15f, -0.02f, 0.23f, -0.03f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderR, 19.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _storefront = imageVectorBuild;
        return imageVectorBuild;
    }
}
