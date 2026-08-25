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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snowboarding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snowboarding", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSnowboarding", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnowboardingKt {
    private static ImageVector _snowboarding;

    public static final ImageVector getSnowboarding(Icons.TwoTone twoTone) {
        ImageVector imageVector = _snowboarding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Snowboarding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 4.1f, 14.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.4f, 20.09f);
        pathBuilderA.curveToRelative(-0.23f, -0.05f, -0.46f, 0.02f, -0.64f, 0.17f);
        pathBuilderA.curveToRelative(-0.69f, 0.6f, -1.64f, 0.88f, -2.6f, 0.67f);
        pathBuilderA.lineTo(17.0f, 20.69f);
        pathBuilderA.lineToRelative(-1.0f, -6.19f);
        pathBuilderA.lineToRelative(-3.32f, -2.67f);
        pathBuilderA.lineToRelative(1.8f, -2.89f);
        pathBuilderA.curveTo(15.63f, 10.78f, 17.68f, 12.0f, 20.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-1.85f, 0.0f, -3.44f, -1.12f, -4.13f, -2.72f);
        pathBuilderA.lineToRelative(-0.52f, -1.21f);
        pathBuilderA.curveTo(15.16f, 5.64f, 14.61f, 5.0f, 13.7f, 5.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.lineTo(5.5f, 9.0f);
        pathBuilderA.lineToRelative(1.7f, 1.06f);
        pathBuilderA.lineTo(9.1f, 7.0f);
        pathBuilderA.horizontalLineToRelative(2.35f);
        pathBuilderA.lineToRelative(-2.51f, 3.99f);
        pathBuilderA.curveToRelative(-0.28f, 0.45f, -0.37f, 1.0f, -0.25f, 1.52f);
        pathBuilderA.lineTo(9.5f, 16.0f);
        pathBuilderA.lineTo(6.0f, 18.35f);
        pathBuilderA.lineToRelative(-0.47f, -0.1f);
        pathBuilderA.curveToRelative(-0.96f, -0.2f, -1.71f, -0.85f, -2.1f, -1.67f);
        pathBuilderA.curveToRelative(-0.1f, -0.21f, -0.28f, -0.37f, -0.51f, -0.42f);
        pathBuilderA.curveToRelative(-0.43f, -0.09f, -0.82f, 0.2f, -0.9f, 0.58f);
        pathBuilderA.curveTo(1.98f, 16.88f, 2.0f, 17.05f, 2.07f, 17.2f);
        pathBuilderA.curveToRelative(0.58f, 1.24f, 1.71f, 2.2f, 3.15f, 2.51f);
        pathBuilderA.lineToRelative(12.63f, 2.69f);
        pathBuilderA.curveToRelative(1.44f, 0.31f, 2.86f, -0.11f, 3.9f, -1.01f);
        pathBuilderA.curveToRelative(0.13f, -0.11f, 0.21f, -0.26f, 0.24f, -0.41f);
        pathBuilderA.curveTo(22.06f, 20.6f, 21.83f, 20.18f, 21.4f, 20.09f);
        a.z(pathBuilderA, 8.73f, 18.93f, 3.02f, -2.03f);
        pathBuilderA.lineToRelative(-0.44f, -3.32f);
        pathBuilderA.lineToRelative(2.84f, 2.02f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 0.75f, 4.64f, 8.73f, 18.93f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _snowboarding = imageVectorBuild;
        return imageVectorBuild;
    }
}
