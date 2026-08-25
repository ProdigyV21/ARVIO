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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_psychology", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Psychology", "Landroidx/compose/material/icons/Icons$Rounded;", "getPsychology", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PsychologyKt {
    private static ImageVector _psychology;

    public static final ImageVector getPsychology(Icons.Rounded rounded) {
        ImageVector imageVector = _psychology;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Psychology", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.0f, 8.57f);
        pathBuilderA.curveToRelative(-0.79f, 0.0f, -1.43f, 0.64f, -1.43f, 1.43f);
        pathBuilderA.reflectiveCurveToRelative(0.64f, 1.43f, 1.43f, 1.43f);
        pathBuilderA.reflectiveCurveToRelative(1.43f, -0.64f, 1.43f, -1.43f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.79f, 8.57f, 13.0f, 8.57f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(13.21f, 3.0f);
        pathBuilderA2.curveToRelative(-3.84f, -0.11f, -7.0f, 2.87f, -7.19f, 6.64f);
        pathBuilderA2.lineTo(4.1f, 12.2f);
        pathBuilderA2.curveTo(3.85f, 12.53f, 4.09f, 13.0f, 4.5f, 13.0f);
        pathBuilderA2.horizontalLineTo(6.0f);
        pathBuilderA2.verticalLineToRelative(3.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(1.0f);
        pathBuilderA2.verticalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA2.horizontalLineToRelative(5.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.verticalLineToRelative(-3.68f);
        pathBuilderA2.curveToRelative(2.44f, -1.16f, 4.1f, -3.68f, 4.0f, -6.58f);
        pathBuilderA2.curveTo(19.86f, 6.12f, 16.82f, 3.11f, 13.21f, 3.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(16.0f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.13f, -0.01f, 0.26f, -0.02f, 0.39f);
        pathBuilderA2.lineToRelative(0.83f, 0.66f);
        pathBuilderA2.curveToRelative(0.08f, 0.06f, 0.1f, 0.16f, 0.05f, 0.25f);
        pathBuilderA2.lineToRelative(-0.8f, 1.39f);
        pathBuilderA2.curveToRelative(-0.05f, 0.09f, -0.16f, 0.12f, -0.24f, 0.09f);
        pathBuilderA2.lineToRelative(-0.99f, -0.4f);
        pathBuilderA2.curveToRelative(-0.21f, 0.16f, -0.43f, 0.29f, -0.67f, 0.39f);
        pathBuilderA2.lineTo(14.0f, 13.83f);
        pathBuilderA2.curveToRelative(-0.01f, 0.1f, -0.1f, 0.17f, -0.2f, 0.17f);
        pathBuilderA2.horizontalLineToRelative(-1.6f);
        pathBuilderA2.curveToRelative(-0.1f, 0.0f, -0.18f, -0.07f, -0.2f, -0.17f);
        pathBuilderA2.lineToRelative(-0.15f, -1.06f);
        pathBuilderA2.curveToRelative(-0.25f, -0.1f, -0.47f, -0.23f, -0.68f, -0.39f);
        pathBuilderA2.lineToRelative(-0.99f, 0.4f);
        pathBuilderA2.curveToRelative(-0.09f, 0.03f, -0.2f, 0.0f, -0.25f, -0.09f);
        pathBuilderA2.lineToRelative(-0.8f, -1.39f);
        pathBuilderA2.curveToRelative(-0.05f, -0.08f, -0.03f, -0.19f, 0.05f, -0.25f);
        pathBuilderA2.lineToRelative(0.84f, -0.66f);
        pathBuilderA2.curveTo(10.01f, 10.26f, 10.0f, 10.13f, 10.0f, 10.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.13f, 0.02f, -0.27f, 0.04f, -0.39f);
        pathBuilderA2.lineTo(9.19f, 8.95f);
        pathBuilderA2.curveToRelative(-0.08f, -0.06f, -0.1f, -0.16f, -0.05f, -0.26f);
        pathBuilderA2.lineToRelative(0.8f, -1.38f);
        pathBuilderA2.curveToRelative(0.05f, -0.09f, 0.15f, -0.12f, 0.24f, -0.09f);
        pathBuilderA2.lineToRelative(1.0f, 0.4f);
        pathBuilderA2.curveToRelative(0.2f, -0.15f, 0.43f, -0.29f, 0.67f, -0.39f);
        pathBuilderA2.lineToRelative(0.15f, -1.06f);
        pathBuilderA2.curveTo(12.02f, 6.07f, 12.1f, 6.0f, 12.2f, 6.0f);
        pathBuilderA2.horizontalLineToRelative(1.6f);
        pathBuilderA2.curveToRelative(0.1f, 0.0f, 0.18f, 0.07f, 0.2f, 0.17f);
        pathBuilderA2.lineToRelative(0.15f, 1.06f);
        pathBuilderA2.curveToRelative(0.24f, 0.1f, 0.46f, 0.23f, 0.67f, 0.39f);
        pathBuilderA2.lineToRelative(1.0f, -0.4f);
        pathBuilderA2.curveToRelative(0.09f, -0.03f, 0.2f, 0.0f, 0.24f, 0.09f);
        pathBuilderA2.lineToRelative(0.8f, 1.38f);
        pathBuilderA2.curveToRelative(0.05f, 0.09f, 0.03f, 0.2f, -0.05f, 0.26f);
        pathBuilderA2.lineToRelative(-0.85f, 0.66f);
        pathBuilderA2.curveTo(15.99f, 9.73f, 16.0f, 9.86f, 16.0f, 10.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _psychology = imageVectorBuild;
        return imageVectorBuild;
    }
}
