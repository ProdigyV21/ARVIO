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
import v.a;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_storefront", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Storefront", "Landroidx/compose/material/icons/Icons$Rounded;", "getStorefront", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StorefrontKt {
    private static ImageVector _storefront;

    public static final ImageVector getStorefront(Icons.Rounded rounded) {
        ImageVector imageVector = _storefront;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Storefront", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.9f, 7.89f, -1.05f, -3.37f);
        pathBuilderR.curveToRelative(-0.22f, -0.9f, -1.0f, -1.52f, -1.91f, -1.52f);
        pathBuilderR.horizontalLineTo(5.05f);
        pathBuilderR.curveToRelative(-0.9f, 0.0f, -1.69f, 0.63f, -1.9f, 1.52f);
        pathBuilderR.lineTo(2.1f, 7.89f);
        pathBuilderR.curveTo(1.64f, 9.86f, 2.95f, 11.0f, 3.0f, 11.06f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-7.94f);
        pathBuilderR.curveTo(22.12f, 9.94f, 22.09f, 8.65f, 21.9f, 7.89f);
        a.g(pathBuilderR, 13.0f, 5.0f, 1.96f);
        pathBuilderR.lineToRelative(0.54f, 3.52f);
        pathBuilderR.curveTo(15.59f, 9.23f, 15.11f, 10.0f, 14.22f, 10.0f);
        pathBuilderR.curveTo(13.55f, 10.0f, 13.0f, 9.41f, 13.0f, 8.69f);
        f.r(pathBuilderR, 5.0f, 6.44f, 8.86f);
        pathBuilderR.curveTo(6.36f, 9.51f, 5.84f, 10.0f, 5.23f, 10.0f);
        pathBuilderR.curveTo(4.3f, 10.0f, 3.88f, 9.03f, 4.04f, 8.36f);
        pathBuilderR.lineTo(5.05f, 5.0f);
        d.q(pathBuilderR, 1.97f, 6.44f, 8.86f);
        pathBuilderR.moveTo(11.0f, 8.69f);
        pathBuilderR.curveTo(11.0f, 9.41f, 10.45f, 10.0f, 9.71f, 10.0f);
        pathBuilderR.curveToRelative(-0.75f, 0.0f, -1.3f, -0.7f, -1.22f, -1.48f);
        pathBuilderR.lineTo(9.04f, 5.0f);
        pathBuilderR.horizontalLineTo(11.0f);
        pathBuilderR.verticalLineTo(8.69f);
        pathBuilderR.close();
        pathBuilderR.moveTo(18.77f, 10.0f);
        pathBuilderR.curveToRelative(-0.61f, 0.0f, -1.14f, -0.49f, -1.21f, -1.14f);
        pathBuilderR.lineTo(16.98f, 5.0f);
        pathBuilderR.lineToRelative(1.93f, -0.01f);
        pathBuilderR.lineToRelative(1.05f, 3.37f);
        pathBuilderR.curveTo(20.12f, 9.03f, 19.71f, 10.0f, 18.77f, 10.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _storefront = imageVectorBuild;
        return imageVectorBuild;
    }
}
