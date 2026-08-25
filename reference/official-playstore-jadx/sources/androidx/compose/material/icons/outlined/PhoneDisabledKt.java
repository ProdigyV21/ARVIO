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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneDisabled", "Landroidx/compose/material/icons/Icons$Outlined;", "getPhoneDisabled", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneDisabledKt {
    private static ImageVector _phoneDisabled;

    public static final ImageVector getPhoneDisabled(Icons.Outlined outlined) {
        ImageVector imageVector = _phoneDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PhoneDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.34f, 14.54f, -1.43f, -1.43f);
        pathBuilderR.curveToRelative(0.56f, -0.73f, 1.05f, -1.5f, 1.47f, -2.32f);
        pathBuilderR.lineToRelative(-2.2f, -2.2f);
        pathBuilderR.curveToRelative(-0.28f, -0.28f, -0.36f, -0.67f, -0.25f, -1.02f);
        pathBuilderR.curveTo(15.3f, 6.45f, 15.5f, 5.25f, 15.5f, 4.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.horizontalLineTo(20.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.curveTo(21.0f, 7.98f, 19.63f, 11.64f, 17.34f, 14.54f);
        pathBuilderR.close();
        pathBuilderR.moveTo(14.52f, 17.35f);
        pathBuilderR.curveTo(11.63f, 19.64f, 7.97f, 21.0f, 4.0f, 21.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-3.49f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.curveToRelative(1.24f, 0.0f, 2.45f, -0.2f, 3.57f, -0.57f);
        pathBuilderR.curveToRelative(0.1f, -0.04f, 0.21f, -0.05f, 0.31f, -0.05f);
        pathBuilderR.curveToRelative(0.26f, 0.0f, 0.51f, 0.1f, 0.71f, 0.29f);
        pathBuilderR.lineToRelative(2.2f, 2.2f);
        pathBuilderR.curveToRelative(0.81f, -0.42f, 1.58f, -0.9f, 2.3f, -1.46f);
        pathBuilderR.lineTo(1.39f, 4.22f);
        pathBuilderR.lineToRelative(1.42f, -1.41f);
        pathBuilderR.lineTo(21.19f, 21.2f);
        d.C(pathBuilderR, -1.41f, 1.41f, 14.52f, 17.35f);
        pathBuilderR.moveTo(7.6f, 17.02f);
        pathBuilderR.curveToRelative(-0.85f, 0.24f, -1.72f, 0.39f, -2.6f, 0.45f);
        pathBuilderR.verticalLineToRelative(1.49f);
        pathBuilderR.curveToRelative(1.32f, -0.09f, 2.59f, -0.35f, 3.8f, -0.75f);
        c.z(pathBuilderR, 7.6f, 17.02f, 17.46f, 5.0f);
        pathBuilderR.curveToRelative(-0.06f, 0.89f, -0.21f, 1.76f, -0.45f, 2.59f);
        pathBuilderR.lineToRelative(1.2f, 1.2f);
        pathBuilderR.curveToRelative(0.41f, -1.2f, 0.67f, -2.47f, 0.76f, -3.79f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderR, 17.46f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
