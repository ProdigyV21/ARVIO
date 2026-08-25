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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speakerGroup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SpeakerGroup", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSpeakerGroup", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeakerGroupKt {
    private static ImageVector _speakerGroup;

    public static final ImageVector getSpeakerGroup(Icons.TwoTone twoTone) {
        ImageVector imageVector = _speakerGroup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SpeakerGroup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.0f, 16.99f, 8.0f, 0.01f);
        b.g(pathBuilderR, 18.0f, 3.0f, -8.0f, 13.99f);
        pathBuilderR.moveTo(14.0f, 4.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(14.0f, 9.0f);
        pathBuilderR.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderR.reflectiveCurveTo(15.93f, 16.0f, 14.0f, 16.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.5f, -1.57f, -3.5f, -3.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 12.07f, 9.0f, 14.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.2f, 1.0f, 9.8f, 1.0f);
        pathBuilderJ.curveTo(8.81f, 1.0f, 8.0f, 1.81f, 8.0f, 2.8f);
        pathBuilderJ.verticalLineToRelative(14.4f);
        pathBuilderJ.curveToRelative(0.0f, 0.99f, 0.81f, 1.79f, 1.8f, 1.79f);
        pathBuilderJ.lineToRelative(8.4f, 0.01f);
        pathBuilderJ.curveToRelative(0.99f, 0.0f, 1.8f, -0.81f, 1.8f, -1.8f);
        pathBuilderJ.lineTo(20.0f, 2.8f);
        pathBuilderJ.curveToRelative(0.0f, -0.99f, -0.81f, -1.8f, -1.8f, -1.8f);
        a.z(pathBuilderJ, 18.0f, 17.0f, -8.0f, -0.01f);
        b.g(pathBuilderJ, 10.0f, 3.0f, 8.0f, 14.0f);
        pathBuilderJ.moveTo(14.0f, 8.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(14.0f, 16.0f);
        pathBuilderJ.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderJ.reflectiveCurveTo(15.93f, 9.0f, 14.0f, 9.0f);
        pathBuilderJ.reflectiveCurveToRelative(-3.5f, 1.57f, -3.5f, 3.5f);
        pathBuilderJ.reflectiveCurveTo(12.07f, 16.0f, 14.0f, 16.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(14.0f, 11.0f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderJ.reflectiveCurveTo(14.83f, 14.0f, 14.0f, 14.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderJ.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        a.p(pathBuilderJ, 6.0f, 5.0f, 4.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        c.q(pathBuilderJ, 10.0f, -2.0f, 6.0f, 21.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 6.0f, 5.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speakerGroup = imageVectorBuild;
        return imageVectorBuild;
    }
}
