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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hearing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hearing", "Landroidx/compose/material/icons/Icons$Sharp;", "getHearing", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HearingKt {
    private static ImageVector _hearing;

    public static final ImageVector getHearing(Icons.Sharp sharp) {
        ImageVector imageVector = _hearing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Hearing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 20.0f);
        pathBuilderA.curveToRelative(-0.29f, 0.0f, -0.56f, -0.06f, -0.76f, -0.15f);
        pathBuilderA.curveToRelative(-0.71f, -0.37f, -1.21f, -0.88f, -1.71f, -2.38f);
        pathBuilderA.curveToRelative(-0.51f, -1.56f, -1.47f, -2.29f, -2.39f, -3.0f);
        pathBuilderA.curveToRelative(-0.79f, -0.61f, -1.61f, -1.24f, -2.32f, -2.53f);
        pathBuilderA.curveTo(9.29f, 10.98f, 9.0f, 9.93f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -2.8f, 2.2f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.2f, 5.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -3.93f, -3.07f, -7.0f, -7.0f, -7.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 5.07f, 7.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 1.26f, 0.38f, 2.65f, 1.07f, 3.9f);
        pathBuilderA.curveToRelative(0.91f, 1.65f, 1.98f, 2.48f, 2.85f, 3.15f);
        pathBuilderA.curveToRelative(0.81f, 0.62f, 1.39f, 1.07f, 1.71f, 2.05f);
        pathBuilderA.curveToRelative(0.6f, 1.82f, 1.37f, 2.84f, 2.73f, 3.55f);
        pathBuilderA.curveToRelative(0.51f, 0.23f, 1.07f, 0.35f, 1.64f, 0.35f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        a.p(pathBuilderA, 7.64f, 2.64f, 6.22f, 1.22f);
        pathBuilderA.curveTo(4.23f, 3.21f, 3.0f, 5.96f, 3.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(1.23f, 5.79f, 3.22f, 7.78f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.curveTo(6.01f, 13.74f, 5.0f, 11.49f, 5.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(1.01f, -4.74f, 2.64f, -6.36f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.5f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.12f, -2.5f, -2.5f, -2.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.5f, 1.12f, -2.5f, 2.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hearing = imageVectorBuild;
        return imageVectorBuild;
    }
}
