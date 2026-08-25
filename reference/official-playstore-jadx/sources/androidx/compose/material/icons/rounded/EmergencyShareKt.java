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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergencyShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmergencyShare", "Landroidx/compose/material/icons/Icons$Rounded;", "getEmergencyShare", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmergencyShareKt {
    private static ImageVector _emergencyShare;

    public static final ImageVector getEmergencyShare(Icons.Rounded rounded) {
        ImageVector imageVector = _emergencyShare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.EmergencyShare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 9.0f);
        pathBuilderA.curveToRelative(-3.15f, 0.0f, -6.0f, 2.41f, -6.0f, 6.15f);
        pathBuilderA.curveToRelative(0.0f, 2.35f, 1.78f, 5.11f, 5.34f, 8.27f);
        pathBuilderA.curveToRelative(0.37f, 0.33f, 0.95f, 0.33f, 1.33f, 0.0f);
        pathBuilderA.curveTo(16.22f, 20.25f, 18.0f, 17.5f, 18.0f, 15.15f);
        pathBuilderA.curveTo(18.0f, 11.41f, 15.15f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 16.5f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(12.83f, 16.5f, 12.0f, 16.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.18f, 6.82f);
        pathBuilderA.curveToRelative(-0.35f, 0.35f, -0.89f, 0.38f, -1.3f, 0.09f);
        pathBuilderA.curveTo(14.07f, 6.34f, 13.07f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.reflectiveCurveTo(9.93f, 6.34f, 9.12f, 6.91f);
        pathBuilderA.curveToRelative(-0.41f, 0.28f, -0.95f, 0.26f, -1.3f, -0.09f);
        pathBuilderA.curveToRelative(-0.43f, -0.43f, -0.39f, -1.15f, 0.09f, -1.5f);
        pathBuilderA.curveTo(9.06f, 4.49f, 10.48f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(2.94f, 0.49f, 4.09f, 1.32f);
        pathBuilderA.curveTo(16.58f, 5.67f, 16.61f, 6.39f, 16.18f, 6.82f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.97f, 3.97f);
        pathBuilderA.curveTo(4.55f, 3.54f, 4.59f, 2.85f, 5.05f, 2.47f);
        pathBuilderA.curveTo(6.95f, 0.93f, 9.37f, 0.0f, 12.01f, 0.0f);
        pathBuilderA.curveToRelative(2.64f, 0.0f, 5.06f, 0.93f, 6.95f, 2.48f);
        pathBuilderA.curveToRelative(0.46f, 0.38f, 0.5f, 1.07f, 0.08f, 1.49f);
        pathBuilderA.curveToRelative(-0.36f, 0.36f, -0.93f, 0.39f, -1.32f, 0.07f);
        pathBuilderA.curveTo(16.16f, 2.77f, 14.17f, 2.0f, 12.01f, 2.0f);
        pathBuilderA.curveTo(9.83f, 2.0f, 7.84f, 2.77f, 6.29f, 4.04f);
        pathBuilderA.curveTo(5.9f, 4.36f, 5.33f, 4.32f, 4.97f, 3.97f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emergencyShare = imageVectorBuild;
        return imageVectorBuild;
    }
}
