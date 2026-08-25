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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsGymnastics", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsGymnastics", "Landroidx/compose/material/icons/Icons$Rounded;", "getSportsGymnastics", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsGymnasticsKt {
    private static ImageVector _sportsGymnastics;

    public static final ImageVector getSportsGymnastics(Icons.Rounded rounded) {
        ImageVector imageVector = _sportsGymnastics;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SportsGymnastics", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(7.1f, 8.0f, 6.0f, 8.0f);
        pathBuilderA.reflectiveCurveTo(4.0f, 7.1f, 4.0f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 22.0f);
        pathBuilderA.curveToRelative(-0.56f, 0.0f, -1.02f, -0.44f, -1.05f, -1.0f);
        pathBuilderA.lineToRelative(-0.45f, -9.0f);
        pathBuilderA.lineTo(8.0f, 11.0f);
        pathBuilderA.horizontalLineTo(2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.lineToRelative(6.26f, -4.47f);
        pathBuilderA.curveToRelative(0.42f, -0.3f, 1.0f, -0.23f, 1.34f, 0.16f);
        pathBuilderA.curveToRelative(0.38f, 0.45f, 0.3f, 1.12f, -0.18f, 1.46f);
        pathBuilderA.lineTo(11.14f, 8.5f);
        pathBuilderA.horizontalLineTo(14.0f);
        pathBuilderA.lineToRelative(7.09f, -4.09f);
        pathBuilderA.curveToRelative(0.41f, -0.24f, 0.93f, -0.15f, 1.24f, 0.21f);
        pathBuilderA.curveToRelative(0.36f, 0.43f, 0.3f, 1.07f, -0.14f, 1.41f);
        pathBuilderA.lineTo(14.5f, 12.0f);
        pathBuilderA.lineToRelative(-0.45f, 9.0f);
        pathBuilderA.curveTo(14.02f, 21.56f, 13.56f, 22.0f, 13.0f, 22.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsGymnastics = imageVectorBuild;
        return imageVectorBuild;
    }
}
