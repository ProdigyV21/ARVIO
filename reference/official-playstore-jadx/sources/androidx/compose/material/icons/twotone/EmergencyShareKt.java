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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergencyShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmergencyShare", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEmergencyShare", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmergencyShareKt {
    private static ImageVector _emergencyShare;

    public static final ImageVector getEmergencyShare(Icons.TwoTone twoTone) {
        ImageVector imageVector = _emergencyShare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EmergencyShare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.0f);
        pathBuilderA.curveToRelative(-1.94f, 0.0f, -4.0f, 1.45f, -4.0f, 4.15f);
        pathBuilderA.curveToRelative(0.0f, 0.94f, 0.55f, 2.93f, 4.0f, 6.17f);
        pathBuilderA.curveToRelative(3.45f, -3.24f, 4.0f, -5.23f, 4.0f, -6.17f);
        pathBuilderA.curveTo(16.0f, 12.45f, 13.94f, 11.0f, 12.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 16.5f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 12.83f, 16.5f, 12.0f, 16.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 4.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 3.68f, 0.78f, 4.95f, 2.05f);
        pathBuilderA2.lineToRelative(-1.41f, 1.41f);
        pathBuilderA2.curveTo(14.63f, 6.56f, 13.38f, 6.0f, 12.0f, 6.0f);
        pathBuilderA2.reflectiveCurveTo(9.37f, 6.56f, 8.46f, 7.46f);
        pathBuilderA2.lineTo(7.05f, 6.05f);
        pathBuilderA2.curveTo(8.32f, 4.78f, 10.07f, 4.0f, 12.0f, 4.0f);
        a.z(pathBuilderA2, 19.78f, 3.23f, -1.41f, 1.41f);
        pathBuilderA2.curveTo(16.74f, 3.01f, 14.49f, 2.0f, 12.01f, 2.0f);
        pathBuilderA2.reflectiveCurveTo(7.27f, 3.01f, 5.64f, 4.63f);
        pathBuilderA2.lineTo(4.22f, 3.22f);
        pathBuilderA2.curveTo(6.22f, 1.23f, 8.97f, 0.0f, 12.01f, 0.0f);
        pathBuilderA2.reflectiveCurveTo(17.79f, 1.23f, 19.78f, 3.23f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 11.0f);
        pathBuilderA2.curveToRelative(1.94f, 0.0f, 4.0f, 1.45f, 4.0f, 4.15f);
        pathBuilderA2.curveToRelative(0.0f, 0.94f, -0.55f, 2.93f, -4.0f, 6.17f);
        pathBuilderA2.curveToRelative(-3.45f, -3.24f, -4.0f, -5.23f, -4.0f, -6.17f);
        pathBuilderA2.curveTo(8.0f, 12.45f, 10.06f, 11.0f, 12.0f, 11.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 9.0f);
        pathBuilderA2.curveToRelative(-3.15f, 0.0f, -6.0f, 2.41f, -6.0f, 6.15f);
        pathBuilderA2.curveToRelative(0.0f, 2.49f, 2.0f, 5.44f, 6.0f, 8.85f);
        pathBuilderA2.curveToRelative(4.0f, -3.41f, 6.0f, -6.36f, 6.0f, -8.85f);
        pathBuilderA2.curveTo(18.0f, 11.41f, 15.15f, 9.0f, 12.0f, 9.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(13.5f, 15.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA2.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA2.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA2.curveTo(12.83f, 16.5f, 13.5f, 15.83f, 13.5f, 15.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emergencyShare = imageVectorBuild;
        return imageVectorBuild;
    }
}
