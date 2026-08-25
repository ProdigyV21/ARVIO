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
import v.d;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speakerPhone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SpeakerPhone", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSpeakerPhone", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeakerPhoneKt {
    private static ImageVector _speakerPhone;

    public static final ImageVector getSpeakerPhone(Icons.TwoTone twoTone) {
        ImageVector imageVector = _speakerPhone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SpeakerPhone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.c(9.0f, 12.0f, 6.0f, 8.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 1.0f);
        pathBuilderA.curveTo(8.98f, 1.0f, 6.24f, 2.23f, 4.25f, 4.21f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.curveTo(7.28f, 4.0f, 9.53f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(4.72f, 1.0f, 6.34f, 2.62f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.curveTo(17.76f, 2.23f, 15.02f, 1.0f, 12.0f, 1.0f);
        v.a.p(pathBuilderA, 7.0f, 7.07f, 8.43f, 8.5f);
        pathBuilderA.curveToRelative(0.91f, -0.91f, 2.18f, -1.48f, 3.57f, -1.48f);
        pathBuilderA.reflectiveCurveToRelative(2.66f, 0.57f, 3.57f, 1.48f);
        pathBuilderA.lineTo(17.0f, 7.07f);
        pathBuilderA.curveTo(15.72f, 5.79f, 13.95f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.72f, 0.79f, -5.0f, 2.07f);
        v.a.p(pathBuilderA, 14.86f, 10.01f, 9.14f, 10.0f);
        pathBuilderA.curveTo(8.51f, 10.0f, 8.0f, 10.51f, 8.0f, 11.14f);
        pathBuilderA.verticalLineToRelative(9.71f);
        pathBuilderA.curveToRelative(0.0f, 0.63f, 0.51f, 1.14f, 1.14f, 1.14f);
        pathBuilderA.horizontalLineToRelative(5.71f);
        pathBuilderA.curveToRelative(0.63f, 0.0f, 1.14f, -0.51f, 1.14f, -1.14f);
        pathBuilderA.verticalLineToRelative(-9.71f);
        pathBuilderA.curveToRelative(0.01f, -0.63f, -0.5f, -1.13f, -1.13f, -1.13f);
        v.a.p(pathBuilderA, 15.0f, 20.0f, 9.0f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.t(pathBuilderA, -8.0f, 6.0f, 8.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speakerPhone = imageVectorBuild;
        return imageVectorBuild;
    }
}
