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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneDisabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhoneDisabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneDisabledKt {
    private static ImageVector _phoneDisabled;

    public static final ImageVector getPhoneDisabled(Icons.Rounded rounded) {
        ImageVector imageVector = _phoneDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhoneDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.54f, 17.37f);
        pathBuilderA.curveToRelative(-2.63f, 2.08f, -5.89f, 3.39f, -9.45f, 3.61f);
        pathBuilderA.curveToRelative(-1.13f, 0.07f, -2.07f, -0.87f, -2.07f, -2.0f);
        pathBuilderA.verticalLineToRelative(-1.73f);
        pathBuilderA.curveToRelative(-0.01f, -1.01f, 0.75f, -1.86f, 1.76f, -1.98f);
        pathBuilderA.lineToRelative(2.54f, -0.29f);
        pathBuilderA.curveToRelative(0.61f, -0.07f, 1.21f, 0.14f, 1.64f, 0.57f);
        pathBuilderA.lineToRelative(1.84f, 1.84f);
        pathBuilderA.curveToRelative(0.81f, -0.41f, 1.59f, -0.9f, 2.31f, -1.45f);
        pathBuilderA.lineTo(2.1f, 4.93f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.03f, -0.39f, 1.42f, 0.0f);
        pathBuilderA.lineTo(20.49f, 20.5f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(-1.02f, 0.39f, -1.41f, 0.0f);
        c.z(pathBuilderA, 14.54f, 17.37f, 17.39f, 10.8f);
        pathBuilderA.lineToRelative(-1.85f, -1.85f);
        pathBuilderA.curveToRelative(-0.43f, -0.43f, -0.64f, -1.03f, -0.57f, -1.64f);
        pathBuilderA.lineToRelative(0.29f, -2.52f);
        pathBuilderA.curveToRelative(0.12f, -1.01f, 0.97f, -1.77f, 1.99f, -1.77f);
        pathBuilderA.horizontalLineToRelative(1.73f);
        pathBuilderA.curveToRelative(1.13f, 0.0f, 2.07f, 0.94f, 2.0f, 2.07f);
        pathBuilderA.curveToRelative(-0.22f, 3.57f, -1.54f, 6.83f, -3.62f, 9.47f);
        pathBuilderA.lineToRelative(-1.43f, -1.43f);
        pathBuilderA.curveTo(16.48f, 12.4f, 16.97f, 11.62f, 17.39f, 10.8f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
