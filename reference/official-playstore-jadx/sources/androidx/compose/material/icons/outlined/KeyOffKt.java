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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_keyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "KeyOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getKeyOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyOffKt {
    private static ImageVector _keyOff;

    public static final ImageVector getKeyOff(Icons.Outlined outlined) {
        ImageVector imageVector = _keyOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.KeyOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.7f, 13.53f, -1.71f, -1.71f);
        pathBuilderR.curveTo(9.0f, 11.88f, 9.0f, 11.94f, 9.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.curveToRelative(0.06f, 0.0f, 0.12f, 0.0f, 0.18f, 0.01f);
        pathBuilderR.lineTo(5.47f, 8.3f);
        pathBuilderR.curveTo(4.02f, 8.9f, 3.0f, 10.33f, 3.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderR.curveTo(8.67f, 16.0f, 10.1f, 14.98f, 10.7f, 13.53f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.19f, 15.02f);
        pathBuilderR.curveTo(11.15f, 16.8f, 9.21f, 18.0f, 7.0f, 18.0f);
        pathBuilderR.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderR.curveToRelative(0.0f, -2.21f, 1.2f, -4.15f, 2.98f, -5.19f);
        pathBuilderR.lineTo(1.39f, 4.22f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(18.38f, 18.38f);
        d.C(pathBuilderR, -1.41f, 1.41f, 12.19f, 15.02f);
        pathBuilderR.moveTo(16.26f, 13.43f);
        pathBuilderR.lineToRelative(1.24f, -0.93f);
        pathBuilderR.lineToRelative(1.81f, 1.36f);
        pathBuilderR.lineTo(21.17f, 12.0f);
        pathBuilderR.lineToRelative(-1.0f, -1.0f);
        pathBuilderR.lineToRelative(-6.34f, 0.0f);
        pathBuilderR.lineToRelative(-2.0f, -2.0f);
        pathBuilderR.lineTo(21.0f, 9.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.lineToRelative(3.0f, 3.0f);
        pathBuilderR.lineToRelative(-4.5f, 4.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, -0.69f, -0.51f, 16.26f, 13.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _keyOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
