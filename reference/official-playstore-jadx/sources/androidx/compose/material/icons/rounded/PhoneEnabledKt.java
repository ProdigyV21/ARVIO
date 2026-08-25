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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneEnabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneEnabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhoneEnabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneEnabledKt {
    private static ImageVector _phoneEnabled;

    public static final ImageVector getPhoneEnabled(Icons.Rounded rounded) {
        ImageVector imageVector = _phoneEnabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhoneEnabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.78f, 15.27f, 2.54f, -0.29f);
        pathBuilderR.curveToRelative(0.61f, -0.07f, 1.21f, 0.14f, 1.64f, 0.57f);
        pathBuilderR.lineToRelative(1.84f, 1.84f);
        pathBuilderR.curveToRelative(2.83f, -1.44f, 5.15f, -3.75f, 6.59f, -6.59f);
        pathBuilderR.lineToRelative(-1.85f, -1.85f);
        pathBuilderR.curveToRelative(-0.43f, -0.43f, -0.64f, -1.03f, -0.57f, -1.64f);
        pathBuilderR.lineToRelative(0.29f, -2.52f);
        pathBuilderR.curveToRelative(0.12f, -1.01f, 0.97f, -1.77f, 1.99f, -1.77f);
        pathBuilderR.horizontalLineToRelative(1.73f);
        pathBuilderR.curveToRelative(1.13f, 0.0f, 2.07f, 0.94f, 2.0f, 2.07f);
        pathBuilderR.curveToRelative(-0.53f, 8.54f, -7.36f, 15.36f, -15.89f, 15.89f);
        pathBuilderR.curveToRelative(-1.13f, 0.07f, -2.07f, -0.87f, -2.07f, -2.0f);
        pathBuilderR.verticalLineToRelative(-1.73f);
        pathBuilderR.curveTo(3.01f, 16.24f, 3.77f, 15.39f, 4.78f, 15.27f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneEnabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
