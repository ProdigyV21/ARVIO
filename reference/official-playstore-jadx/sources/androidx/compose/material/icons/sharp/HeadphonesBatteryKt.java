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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_headphonesBattery", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeadphonesBattery", "Landroidx/compose/material/icons/Icons$Sharp;", "getHeadphonesBattery", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeadphonesBatteryKt {
    private static ImageVector _headphonesBattery;

    public static final ImageVector getHeadphonesBattery(Icons.Sharp sharp) {
        ImageVector imageVector = _headphonesBattery;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HeadphonesBattery", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.0f, 7.0f, 0.0f, -1.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 1.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 11.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 6.0f, 0.0f, 0.0f, -11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        f.u(pathBuilderA, 6.0f, 4.0f, -5.0f, 3.5f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(0.0f, -2.48f, 2.02f, -4.5f, 4.5f, -4.5f);
        pathBuilderA.reflectiveCurveToRelative(4.5f, 2.02f, 4.5f, 4.5f);
        a0.a.A(pathBuilderA, 1.0f, 10.0f, 5.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveTo(14.0f, 8.69f, 11.31f, 6.0f, 8.0f, 6.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _headphonesBattery = imageVectorBuild;
        return imageVectorBuild;
    }
}
