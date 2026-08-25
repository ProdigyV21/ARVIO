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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hotTub", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HotTub", "Landroidx/compose/material/icons/Icons$Rounded;", "getHotTub", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HotTubKt {
    private static ImageVector _hotTub;

    public static final ImageVector getHotTub(Icons.Rounded rounded) {
        ImageVector imageVector = _hotTub;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HotTub", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(7.0f, 6.0f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(21.0f, 12.0f, -9.85f);
        pathBuilderA.curveToRelative(-0.31f, -0.22f, -0.59f, -0.46f, -0.82f, -0.72f);
        pathBuilderA.lineToRelative(-1.4f, -1.55f);
        pathBuilderA.curveToRelative(-0.19f, -0.21f, -0.43f, -0.38f, -0.69f, -0.5f);
        pathBuilderA.curveToRelative(-0.29f, -0.14f, -0.62f, -0.23f, -0.96f, -0.23f);
        pathBuilderA.horizontalLineToRelative(-0.03f);
        pathBuilderA.curveTo(6.01f, 9.0f, 5.0f, 10.01f, 5.0f, 11.25f);
        pathBuilderA.lineTo(5.0f, 12.0f);
        pathBuilderA.lineTo(3.0f, 12.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderA, 4.0f, 11.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderA, 4.0f, 15.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderA, 4.0f, 19.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderA, 4.0f, 15.06f, 10.0f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.93f, -0.39f, 0.94f, -0.89f);
        pathBuilderA.curveToRelative(0.04f, -1.4f, -0.58f, -2.48f, -1.35f, -3.25f);
        pathBuilderA.curveToRelative(-0.65f, -0.72f, -0.8f, -1.27f, -0.77f, -1.91f);
        pathBuilderA.curveToRelative(0.02f, -0.52f, -0.41f, -0.95f, -0.94f, -0.95f);
        pathBuilderA.curveToRelative(-0.5f, 0.0f, -0.93f, 0.4f, -0.94f, 0.9f);
        pathBuilderA.curveToRelative(-0.03f, 1.29f, 0.5f, 2.43f, 1.35f, 3.25f);
        pathBuilderA.curveToRelative(0.61f, 0.59f, 0.78f, 1.27f, 0.78f, 1.89f);
        pathBuilderA.curveToRelative(-0.01f, 0.52f, 0.4f, 0.96f, 0.93f, 0.96f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.06f, 10.0f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.93f, -0.39f, 0.94f, -0.89f);
        pathBuilderA.curveToRelative(0.04f, -1.4f, -0.58f, -2.48f, -1.35f, -3.25f);
        pathBuilderA.curveToRelative(-0.65f, -0.72f, -0.8f, -1.27f, -0.77f, -1.91f);
        pathBuilderA.curveToRelative(0.02f, -0.52f, -0.41f, -0.95f, -0.94f, -0.95f);
        pathBuilderA.curveToRelative(-0.5f, 0.0f, -0.93f, 0.4f, -0.94f, 0.9f);
        pathBuilderA.curveToRelative(-0.03f, 1.29f, 0.5f, 2.43f, 1.35f, 3.25f);
        pathBuilderA.curveToRelative(0.61f, 0.59f, 0.78f, 1.27f, 0.78f, 1.89f);
        pathBuilderA.curveToRelative(-0.01f, 0.52f, 0.4f, 0.96f, 0.93f, 0.96f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hotTub = imageVectorBuild;
        return imageVectorBuild;
    }
}
