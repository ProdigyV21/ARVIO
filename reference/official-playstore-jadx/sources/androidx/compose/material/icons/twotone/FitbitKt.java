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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fitbit", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fitbit", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFitbit", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FitbitKt {
    private static ImageVector _fitbit;

    public static final ImageVector getFitbit(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fitbit;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Fitbit", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.89f, 13.89f);
        pathBuilderA.curveToRelative(1.04f, 0.0f, 1.89f, -0.85f, 1.89f, -1.89f);
        pathBuilderA.reflectiveCurveToRelative(-0.85f, -1.89f, -1.89f, -1.89f);
        pathBuilderA.curveTo(18.85f, 10.11f, 18.0f, 10.96f, 18.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(18.85f, 13.89f, 19.89f, 13.89f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.65f, 13.68f);
        pathBuilderA.curveToRelative(0.93f, 0.0f, 1.68f, -0.75f, 1.68f, -1.68f);
        pathBuilderA.reflectiveCurveToRelative(-0.75f, -1.68f, -1.68f, -1.68f);
        pathBuilderA.curveToRelative(-0.93f, 0.0f, -1.68f, 0.75f, -1.68f, 1.68f);
        pathBuilderA.reflectiveCurveTo(14.72f, 13.68f, 15.65f, 13.68f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.65f, 9.42f);
        pathBuilderA.curveToRelative(0.93f, 0.0f, 1.68f, -0.75f, 1.68f, -1.68f);
        pathBuilderA.curveToRelative(0.0f, -0.93f, -0.75f, -1.68f, -1.68f, -1.68f);
        pathBuilderA.curveToRelative(-0.93f, 0.0f, -1.68f, 0.75f, -1.68f, 1.68f);
        pathBuilderA.curveTo(13.97f, 8.67f, 14.72f, 9.42f, 15.65f, 9.42f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.65f, 17.93f);
        pathBuilderA.curveToRelative(0.93f, 0.0f, 1.68f, -0.75f, 1.68f, -1.68f);
        pathBuilderA.curveToRelative(0.0f, -0.93f, -0.75f, -1.68f, -1.68f, -1.68f);
        pathBuilderA.curveToRelative(-0.93f, 0.0f, -1.68f, 0.75f, -1.68f, 1.68f);
        pathBuilderA.curveTo(13.97f, 17.17f, 14.72f, 17.93f, 15.65f, 17.93f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.41f, 13.47f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.reflectiveCurveToRelative(-0.66f, -1.47f, -1.47f, -1.47f);
        pathBuilderA.curveToRelative(-0.81f, 0.0f, -1.47f, 0.66f, -1.47f, 1.47f);
        pathBuilderA.reflectiveCurveTo(10.59f, 13.47f, 11.41f, 13.47f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.41f, 9.21f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.reflectiveCurveToRelative(-0.66f, -1.47f, -1.47f, -1.47f);
        pathBuilderA.curveToRelative(-0.81f, 0.0f, -1.47f, 0.66f, -1.47f, 1.47f);
        pathBuilderA.reflectiveCurveTo(10.59f, 9.21f, 11.41f, 9.21f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.41f, 17.73f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.curveToRelative(0.0f, -0.81f, -0.66f, -1.47f, -1.47f, -1.47f);
        pathBuilderA.curveToRelative(-0.81f, 0.0f, -1.47f, 0.66f, -1.47f, 1.47f);
        pathBuilderA.curveTo(9.93f, 17.07f, 10.59f, 17.73f, 11.41f, 17.73f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.41f, 22.0f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.curveToRelative(0.0f, -0.81f, -0.66f, -1.47f, -1.47f, -1.47f);
        pathBuilderA.curveToRelative(-0.81f, 0.0f, -1.47f, 0.66f, -1.47f, 1.47f);
        pathBuilderA.curveTo(9.93f, 21.34f, 10.59f, 22.0f, 11.41f, 22.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.41f, 4.94f);
        pathBuilderA.curveToRelative(0.81f, 0.0f, 1.47f, -0.66f, 1.47f, -1.47f);
        pathBuilderA.reflectiveCurveTo(12.22f, 2.0f, 11.41f, 2.0f);
        pathBuilderA.curveToRelative(-0.81f, 0.0f, -1.47f, 0.66f, -1.47f, 1.47f);
        pathBuilderA.reflectiveCurveTo(10.59f, 4.94f, 11.41f, 4.94f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.16f, 13.26f);
        pathBuilderA.curveToRelative(0.7f, 0.0f, 1.26f, -0.57f, 1.26f, -1.26f);
        pathBuilderA.reflectiveCurveToRelative(-0.57f, -1.26f, -1.26f, -1.26f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.26f, 0.57f, -1.26f, 1.26f);
        pathBuilderA.reflectiveCurveTo(6.46f, 13.26f, 7.16f, 13.26f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.16f, 17.51f);
        pathBuilderA.curveToRelative(0.7f, 0.0f, 1.26f, -0.57f, 1.26f, -1.26f);
        pathBuilderA.curveToRelative(0.0f, -0.7f, -0.57f, -1.26f, -1.26f, -1.26f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.26f, 0.57f, -1.26f, 1.26f);
        pathBuilderA.curveTo(5.9f, 16.94f, 6.46f, 17.51f, 7.16f, 17.51f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.16f, 9.02f);
        pathBuilderA.curveToRelative(0.7f, 0.0f, 1.26f, -0.57f, 1.26f, -1.26f);
        pathBuilderA.curveToRelative(0.0f, -0.7f, -0.57f, -1.26f, -1.26f, -1.26f);
        pathBuilderA.curveToRelative(-0.7f, 0.0f, -1.26f, 0.57f, -1.26f, 1.26f);
        pathBuilderA.curveTo(5.9f, 8.45f, 6.46f, 9.02f, 7.16f, 9.02f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.29f, 13.05f);
        pathBuilderA.curveToRelative(0.58f, 0.0f, 1.05f, -0.47f, 1.05f, -1.05f);
        pathBuilderA.reflectiveCurveToRelative(-0.47f, -1.05f, -1.05f, -1.05f);
        pathBuilderA.curveToRelative(-0.58f, 0.0f, -1.05f, 0.47f, -1.05f, 1.05f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 2.71f, 13.05f, 3.29f, 13.05f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fitbit = imageVectorBuild;
        return imageVectorBuild;
    }
}
