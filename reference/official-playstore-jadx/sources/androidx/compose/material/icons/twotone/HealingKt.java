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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_healing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Healing", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHealing", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HealingKt {
    private static ImageVector _healing;

    public static final ImageVector getHealing(Icons.TwoTone twoTone) {
        ImageVector imageVector = _healing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Healing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.03f, 16.72f, 3.63f, 3.62f);
        b.D(pathBuilderR, 3.62f, -3.63f, -3.62f, -3.62f);
        pathBuilderR.moveTo(7.29f, 3.71f);
        pathBuilderR.lineTo(3.66f, 7.34f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 3.63f, 3.62f, 3.62f, -3.63f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(17.73f, 12.02f, 3.98f, -3.98f);
        pathBuilderR2.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR2.lineToRelative(-4.34f, -4.34f);
        pathBuilderR2.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR2.lineToRelative(-3.98f, 3.98f);
        pathBuilderR2.lineTo(8.0f, 2.29f);
        pathBuilderR2.curveTo(7.8f, 2.1f, 7.55f, 2.0f, 7.29f, 2.0f);
        pathBuilderR2.curveToRelative(-0.25f, 0.0f, -0.51f, 0.1f, -0.7f, 0.29f);
        pathBuilderR2.lineTo(2.25f, 6.63f);
        pathBuilderR2.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR2.lineToRelative(3.98f, 3.98f);
        pathBuilderR2.lineTo(2.25f, 16.0f);
        pathBuilderR2.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR2.lineToRelative(4.34f, 4.34f);
        pathBuilderR2.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR2.lineToRelative(3.98f, -3.98f);
        pathBuilderR2.lineToRelative(3.98f, 3.98f);
        pathBuilderR2.curveToRelative(0.2f, 0.2f, 0.45f, 0.29f, 0.71f, 0.29f);
        pathBuilderR2.reflectiveCurveToRelative(0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilderR2.lineToRelative(4.34f, -4.34f);
        pathBuilderR2.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        b.C(pathBuilderR2, -3.99f, -3.98f, 12.0f, 9.0f);
        pathBuilderR2.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.p(pathBuilderR2, 7.29f, 10.96f, 3.66f, 7.34f);
        pathBuilderR2.lineToRelative(3.63f, -3.63f);
        b.D(pathBuilderR2, 3.62f, 3.62f, -3.62f, 3.63f);
        pathBuilderR2.moveTo(10.0f, 13.0f);
        pathBuilderR2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR2.close();
        pathBuilderR2.moveTo(12.0f, 15.0f);
        pathBuilderR2.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR2.close();
        pathBuilderR2.moveTo(14.0f, 11.0f);
        pathBuilderR2.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR2.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.z(pathBuilderR2, 16.66f, 20.34f, -3.63f, -3.62f);
        pathBuilderR2.lineToRelative(3.63f, -3.63f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, 3.62f, 3.62f, -3.62f, 3.63f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _healing = imageVectorBuild;
        return imageVectorBuild;
    }
}
