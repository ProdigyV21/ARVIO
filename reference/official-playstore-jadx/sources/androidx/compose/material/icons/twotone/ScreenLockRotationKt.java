package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenLockRotation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenLockRotation", "Landroidx/compose/material/icons/Icons$TwoTone;", "getScreenLockRotation", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenLockRotationKt {
    private static ImageVector _screenLockRotation;

    public static final ImageVector getScreenLockRotation(Icons.TwoTone twoTone) {
        ImageVector imageVector = _screenLockRotation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ScreenLockRotation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.3f, 13.77f, -2.57f, -2.57f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.lineToRelative(2.22f, 2.22f);
        pathBuilderR.lineToRelative(-5.66f, 5.66f);
        pathBuilderR.lineTo(3.56f, 9.17f);
        pathBuilderR.lineToRelative(5.66f, -5.66f);
        pathBuilderR.lineToRelative(2.1f, 2.1f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(-2.45f, -2.45f);
        pathBuilderR.curveToRelative(-0.59f, -0.59f, -1.54f, -0.59f, -2.12f, 0.0f);
        pathBuilderR.lineTo(1.8f, 8.11f);
        pathBuilderR.curveToRelative(-0.59f, 0.59f, -0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderR.lineToRelative(12.02f, 12.02f);
        pathBuilderR.curveToRelative(0.59f, 0.59f, 1.54f, 0.59f, 2.12f, 0.0f);
        pathBuilderR.lineToRelative(6.36f, -6.36f);
        pathBuilderR.curveToRelative(0.59f, -0.59f, 0.59f, -1.54f, 0.0f, -2.12f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.52f, 21.48f);
        pathBuilderR.curveTo(4.25f, 19.94f, 1.91f, 16.76f, 1.55f, 13.0f);
        pathBuilderR.lineTo(0.05f, 13.0f);
        pathBuilderR.curveTo(0.56f, 19.16f, 5.71f, 24.0f, 12.0f, 24.0f);
        pathBuilderR.lineToRelative(0.66f, -0.03f);
        b.D(pathBuilderR, -3.81f, -3.82f, -1.33f, 1.33f);
        pathBuilderR.moveTo(15.05f, 10.0f);
        pathBuilderR.horizontalLineToRelative(5.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.lineTo(21.05f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-0.5f);
        pathBuilderR.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderR.reflectiveCurveToRelative(-2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilderR.lineTo(15.05f, 4.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.85f, 3.5f);
        pathBuilderR.curveToRelative(0.0f, -0.94f, 0.76f, -1.7f, 1.7f, -1.7f);
        pathBuilderR.reflectiveCurveToRelative(1.7f, 0.76f, 1.7f, 1.7f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderR, 19.25f, 4.0f, -3.4f, -0.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenLockRotation = imageVectorBuild;
        return imageVectorBuild;
    }
}
