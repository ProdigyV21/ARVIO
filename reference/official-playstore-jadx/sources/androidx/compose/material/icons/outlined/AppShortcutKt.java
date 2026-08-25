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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_appShortcut", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AppShortcut", "Landroidx/compose/material/icons/Icons$Outlined;", "getAppShortcut", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppShortcutKt {
    private static ImageVector _appShortcut;

    public static final ImageVector getAppShortcut(Icons.Outlined outlined) {
        ImageVector imageVector = _appShortcut;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AppShortcut", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(17.0f, 18.0f, 7.0f, 6.0f, 10.0f);
        f.t(pathBuilderC, 1.0f, 2.0f, 3.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderC.horizontalLineTo(7.0f);
        pathBuilderC.curveTo(5.9f, 1.0f, 5.0f, 1.9f, 5.0f, 3.0f);
        pathBuilderC.verticalLineToRelative(18.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(10.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.t(pathBuilderC, -4.0f, -2.0f, 18.0f);
        a.C(pathBuilderC, 7.0f, 3.0f, 10.0f, 1.0f);
        c.l(pathBuilderC, 7.0f, 3.0f, 17.0f, 21.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.f(pathBuilderC, 7.0f, -1.0f, 10.0f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.38f, 9.62f, 0.62f, 1.38f);
        pathBuilderR.lineToRelative(0.62f, -1.38f);
        pathBuilderR.lineToRelative(1.38f, -0.62f);
        pathBuilderR.lineToRelative(-1.38f, -0.62f);
        pathBuilderR.lineToRelative(-0.62f, -1.38f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -0.62f, 1.38f, -1.38f, 0.62f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(16.0f, 8.0f, -1.25f, 2.75f);
        pathBuilderR2.lineToRelative(-2.75f, 1.25f);
        pathBuilderR2.lineToRelative(2.75f, 1.25f);
        pathBuilderR2.lineToRelative(1.25f, 2.75f);
        pathBuilderR2.lineToRelative(1.25f, -2.75f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, 2.75f, -1.25f, -2.75f, -1.25f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR3 = a.r(21.0f, 13.0f, -0.62f, 1.38f);
        pathBuilderR3.lineToRelative(-1.38f, 0.62f);
        pathBuilderR3.lineToRelative(1.38f, 0.62f);
        pathBuilderR3.lineToRelative(0.62f, 1.38f);
        pathBuilderR3.lineToRelative(0.62f, -1.38f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR3, 1.38f, -0.62f, -1.38f, -0.62f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _appShortcut = imageVectorBuild;
        return imageVectorBuild;
    }
}
