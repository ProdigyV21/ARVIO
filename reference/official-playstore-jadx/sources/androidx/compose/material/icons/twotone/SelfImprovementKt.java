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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_selfImprovement", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SelfImprovement", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSelfImprovement", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelfImprovementKt {
    private static ImageVector _selfImprovement;

    public static final ImageVector getSelfImprovement(Icons.TwoTone twoTone) {
        ImageVector imageVector = _selfImprovement;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SelfImprovement", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 6.0f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(21.0f, 16.0f, -2.0f);
        pathBuilderI.curveToRelative(-2.24f, 0.0f, -4.16f, -0.96f, -5.6f, -2.68f);
        pathBuilderI.lineToRelative(-1.34f, -1.6f);
        pathBuilderI.curveTo(13.68f, 9.26f, 13.12f, 9.0f, 12.53f, 9.0f);
        pathBuilderI.horizontalLineToRelative(-1.05f);
        pathBuilderI.curveToRelative(-0.59f, 0.0f, -1.15f, 0.26f, -1.53f, 0.72f);
        pathBuilderI.lineToRelative(-1.34f, 1.6f);
        pathBuilderI.curveTo(7.16f, 13.04f, 5.24f, 14.0f, 3.0f, 14.0f);
        pathBuilderI.verticalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(2.77f, 0.0f, 5.19f, -1.17f, 7.0f, -3.25f);
        pathBuilderI.verticalLineTo(15.0f);
        pathBuilderI.lineToRelative(-3.88f, 1.55f);
        pathBuilderI.curveTo(5.45f, 16.82f, 5.0f, 17.48f, 5.0f, 18.21f);
        pathBuilderI.curveTo(5.0f, 19.2f, 5.8f, 20.0f, 6.79f, 20.0f);
        pathBuilderI.horizontalLineTo(9.0f);
        pathBuilderI.verticalLineToRelative(-0.5f);
        pathBuilderI.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderI.horizontalLineToRelative(3.0f);
        pathBuilderI.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderI.reflectiveCurveTo(14.78f, 18.0f, 14.5f, 18.0f);
        pathBuilderI.horizontalLineToRelative(-3.0f);
        pathBuilderI.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderI.verticalLineTo(20.0f);
        pathBuilderI.horizontalLineToRelative(7.21f);
        pathBuilderI.curveTo(18.2f, 20.0f, 19.0f, 19.2f, 19.0f, 18.21f);
        pathBuilderI.curveToRelative(0.0f, -0.73f, -0.45f, -1.39f, -1.12f, -1.66f);
        pathBuilderI.lineTo(14.0f, 15.0f);
        pathBuilderI.verticalLineToRelative(-2.25f);
        pathBuilderI.curveTo(15.81f, 14.83f, 18.23f, 16.0f, 21.0f, 16.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _selfImprovement = imageVectorBuild;
        return imageVectorBuild;
    }
}
