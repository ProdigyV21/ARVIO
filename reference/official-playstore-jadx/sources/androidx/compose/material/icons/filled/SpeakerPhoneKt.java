package androidx.compose.material.icons.filled;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speakerPhone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SpeakerPhone", "Landroidx/compose/material/icons/Icons$Filled;", "getSpeakerPhone", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeakerPhoneKt {
    private static ImageVector _speakerPhone;

    public static final ImageVector getSpeakerPhone(Icons.Filled filled) {
        ImageVector imageVector = _speakerPhone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SpeakerPhone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.0f, 7.07f, 8.43f, 8.5f);
        pathBuilderJ.curveToRelative(0.91f, -0.91f, 2.18f, -1.48f, 3.57f, -1.48f);
        pathBuilderJ.reflectiveCurveToRelative(2.66f, 0.57f, 3.57f, 1.48f);
        pathBuilderJ.lineTo(17.0f, 7.07f);
        pathBuilderJ.curveTo(15.72f, 5.79f, 13.95f, 5.0f, 12.0f, 5.0f);
        pathBuilderJ.reflectiveCurveToRelative(-3.72f, 0.79f, -5.0f, 2.07f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 1.0f);
        pathBuilderJ.curveTo(8.98f, 1.0f, 6.24f, 2.23f, 4.25f, 4.21f);
        pathBuilderJ.lineToRelative(1.41f, 1.41f);
        pathBuilderJ.curveTo(7.28f, 4.0f, 9.53f, 3.0f, 12.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.72f, 1.0f, 6.34f, 2.62f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        pathBuilderJ.curveTo(17.76f, 2.23f, 15.02f, 1.0f, 12.0f, 1.0f);
        a.p(pathBuilderJ, 14.86f, 10.01f, 9.14f, 10.0f);
        pathBuilderJ.curveTo(8.51f, 10.0f, 8.0f, 10.51f, 8.0f, 11.14f);
        pathBuilderJ.verticalLineToRelative(9.71f);
        pathBuilderJ.curveToRelative(0.0f, 0.63f, 0.51f, 1.14f, 1.14f, 1.14f);
        pathBuilderJ.horizontalLineToRelative(5.71f);
        pathBuilderJ.curveToRelative(0.63f, 0.0f, 1.14f, -0.51f, 1.14f, -1.14f);
        pathBuilderJ.verticalLineToRelative(-9.71f);
        pathBuilderJ.curveToRelative(0.01f, -0.63f, -0.5f, -1.13f, -1.13f, -1.13f);
        a.p(pathBuilderJ, 15.0f, 20.0f, 9.0f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderJ, -8.0f, 6.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speakerPhone = imageVectorBuild;
        return imageVectorBuild;
    }
}
