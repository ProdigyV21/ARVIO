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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doorbell", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Doorbell", "Landroidx/compose/material/icons/Icons$Rounded;", "getDoorbell", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoorbellKt {
    private static ImageVector _doorbell;

    public static final ImageVector getDoorbell(Icons.Rounded rounded) {
        ImageVector imageVector = _doorbell;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Doorbell", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.8f, 3.9f, -6.0f, 4.5f);
        pathBuilderR.curveTo(4.3f, 8.78f, 4.0f, 9.37f, 4.0f, 10.0f);
        pathBuilderR.verticalLineToRelative(9.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-9.0f);
        pathBuilderR.curveToRelative(0.0f, -0.63f, -0.3f, -1.22f, -0.8f, -1.6f);
        pathBuilderR.lineToRelative(-6.0f, -4.5f);
        pathBuilderR.curveTo(12.49f, 3.37f, 11.51f, 3.37f, 10.8f, 3.9f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 17.5f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveTo(13.0f, 17.05f, 12.55f, 17.5f, 12.0f, 17.5f);
        a.g(pathBuilderR, 15.5f, 16.0f, -7.0f);
        pathBuilderR.curveTo(8.22f, 16.0f, 8.0f, 15.78f, 8.0f, 15.5f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveTo(8.0f, 15.22f, 8.22f, 15.0f, 8.5f, 15.0f);
        pathBuilderR.horizontalLineTo(9.0f);
        pathBuilderR.verticalLineToRelative(-2.34f);
        pathBuilderR.curveToRelative(0.0f, -1.54f, 0.82f, -2.82f, 2.25f, -3.16f);
        pathBuilderR.verticalLineTo(9.25f);
        pathBuilderR.curveToRelative(0.0f, -0.41f, 0.34f, -0.75f, 0.75f, -0.75f);
        pathBuilderR.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderR.verticalLineTo(9.5f);
        pathBuilderR.curveTo(14.19f, 9.84f, 15.0f, 11.12f, 15.0f, 12.66f);
        pathBuilderR.verticalLineTo(15.0f);
        pathBuilderR.horizontalLineToRelative(0.5f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveTo(16.0f, 15.78f, 15.78f, 16.0f, 15.5f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doorbell = imageVectorBuild;
        return imageVectorBuild;
    }
}
