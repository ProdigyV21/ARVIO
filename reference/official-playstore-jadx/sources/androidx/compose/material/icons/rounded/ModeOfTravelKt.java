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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_modeOfTravel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ModeOfTravel", "Landroidx/compose/material/icons/Icons$Rounded;", "getModeOfTravel", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModeOfTravelKt {
    private static ImageVector _modeOfTravel;

    public static final ImageVector getModeOfTravel(Icons.Rounded rounded) {
        ImageVector imageVector = _modeOfTravel;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ModeOfTravel", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.0f, 10.2f);
        pathBuilderA.curveTo(4.0f, 5.22f, 7.8f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(4.0f, 0.0f, 7.64f, 2.92f, 7.97f, 7.5f);
        pathBuilderA.lineToRelative(2.32f, 0.0f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.67f, 0.54f, 0.35f, 0.85f);
        pathBuilderA.lineToRelative(-3.29f, 3.29f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.51f, 0.2f, -0.71f, 0.0f);
        pathBuilderA.lineToRelative(-3.29f, -3.29f);
        pathBuilderA.curveToRelative(-0.31f, -0.31f, -0.09f, -0.85f, 0.35f, -0.85f);
        pathBuilderA.lineToRelative(2.26f, 0.0f);
        pathBuilderA.curveTo(17.65f, 6.24f, 15.13f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(-3.35f, 0.0f, -6.0f, 2.57f, -6.0f, 6.2f);
        pathBuilderA.curveToRelative(0.0f, 2.34f, 1.95f, 5.44f, 6.0f, 9.14f);
        pathBuilderA.curveToRelative(0.64f, -0.59f, 1.23f, -1.16f, 1.77f, -1.71f);
        pathBuilderA.curveToRelative(-0.17f, -0.34f, -0.27f, -0.72f, -0.27f, -1.12f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.reflectiveCurveTo(17.38f, 19.0f, 16.0f, 19.0f);
        pathBuilderA.curveToRelative(-0.24f, 0.0f, -0.47f, -0.03f, -0.69f, -0.1f);
        pathBuilderA.curveToRelative(-0.78f, 0.82f, -1.67f, 1.66f, -2.65f, 2.52f);
        pathBuilderA.curveToRelative(-0.38f, 0.33f, -0.95f, 0.33f, -1.33f, 0.0f);
        pathBuilderA.curveTo(6.45f, 17.12f, 4.0f, 13.38f, 4.0f, 10.2f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _modeOfTravel = imageVectorBuild;
        return imageVectorBuild;
    }
}
