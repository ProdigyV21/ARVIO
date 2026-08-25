package androidx.compose.material.icons.sharp;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenLockLandscape", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenLockLandscape", "Landroidx/compose/material/icons/Icons$Sharp;", "getScreenLockLandscape", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenLockLandscapeKt {
    private static ImageVector _screenLockLandscape;

    public static final ImageVector getScreenLockLandscape(Icons.Sharp sharp) {
        ImageVector imageVector = _screenLockLandscape;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ScreenLockLandscape", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(23.0f, 5.0f, 1.0f, 5.0f, 14.0f);
        d.q(pathBuilderS, 22.0f, 23.0f, 5.0f);
        pathBuilderS.moveTo(19.0f, 17.0f);
        pathBuilderS.lineTo(5.0f, 17.0f);
        b.g(pathBuilderS, 5.0f, 7.0f, 14.0f, 10.0f);
        a.C(pathBuilderS, 9.0f, 16.0f, 6.0f, -5.0f);
        pathBuilderS.horizontalLineToRelative(-1.0f);
        pathBuilderS.verticalLineToRelative(-0.9f);
        pathBuilderS.curveToRelative(0.0f, -1.0f, -0.69f, -1.92f, -1.68f, -2.08f);
        pathBuilderS.curveTo(11.07f, 7.83f, 10.0f, 8.79f, 10.0f, 10.0f);
        d.g(pathBuilderS, 1.0f, 9.0f, 11.0f, 5.0f);
        pathBuilderS.moveTo(10.8f, 10.0f);
        pathBuilderS.curveToRelative(0.0f, -0.66f, 0.54f, -1.2f, 1.2f, -1.2f);
        pathBuilderS.reflectiveCurveToRelative(1.2f, 0.54f, 1.2f, 1.2f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderS, 1.0f, -2.4f, -1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenLockLandscape = imageVectorBuild;
        return imageVectorBuild;
    }
}
