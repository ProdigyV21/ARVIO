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
import v.b;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_screenshotMonitor", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScreenshotMonitor", "Landroidx/compose/material/icons/Icons$Rounded;", "getScreenshotMonitor", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScreenshotMonitorKt {
    private static ImageVector _screenshotMonitor;

    public static final ImageVector getScreenshotMonitor(Icons.Rounded rounded) {
        ImageVector imageVector = _screenshotMonitor;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ScreenshotMonitor", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 3.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 3.0f, 2.0f, 3.9f, 2.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(4.0f);
        pathBuilderQ.verticalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(6.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-1.0f);
        pathBuilderQ.horizontalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(22.0f, 3.89f, 21.1f, 3.0f, 20.0f, 3.0f);
        f.o(pathBuilderQ, 20.0f, 17.0f, 4.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, 16.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(6.5f, 7.5f, 1.75f);
        pathBuilderA.curveTo(8.66f, 7.5f, 9.0f, 7.16f, 9.0f, 6.75f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(9.0f, 6.34f, 8.66f, 6.0f, 8.25f, 6.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(5.45f, 6.0f, 5.0f, 6.45f, 5.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(2.25f);
        pathBuilderA.curveTo(5.0f, 9.66f, 5.34f, 10.0f, 5.75f, 10.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(6.16f, 10.0f, 6.5f, 9.66f, 6.5f, 9.25f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 7.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.25f, 12.0f, 18.25f, 12.0f);
        pathBuilderJ.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderJ.verticalLineToRelative(1.75f);
        pathBuilderJ.horizontalLineToRelative(-1.75f);
        pathBuilderJ.curveToRelative(-0.41f, 0.0f, -0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderJ.horizontalLineTo(18.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-2.25f);
        pathBuilderJ.curveTo(19.0f, 12.34f, 18.66f, 12.0f, 18.25f, 12.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _screenshotMonitor = imageVectorBuild;
        return imageVectorBuild;
    }
}
