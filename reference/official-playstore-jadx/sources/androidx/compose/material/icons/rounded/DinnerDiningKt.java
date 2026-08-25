package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dinnerDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DinnerDining", "Landroidx/compose/material/icons/Icons$Rounded;", "getDinnerDining", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DinnerDiningKt {
    private static ImageVector _dinnerDining;

    public static final ImageVector getDinnerDining(Icons.Rounded rounded) {
        ImageVector imageVector = _dinnerDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DinnerDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(2.85f, 19.85f, 1.0f, 1.0f);
        pathBuilderR.curveTo(3.95f, 20.95f, 4.07f, 21.0f, 4.21f, 21.0f);
        pathBuilderR.horizontalLineToRelative(15.59f);
        pathBuilderR.curveToRelative(0.13f, 0.0f, 0.26f, -0.05f, 0.35f, -0.15f);
        pathBuilderR.lineToRelative(1.0f, -1.0f);
        pathBuilderR.curveToRelative(0.31f, -0.31f, 0.09f, -0.85f, -0.35f, -0.85f);
        pathBuilderR.horizontalLineTo(3.21f);
        pathBuilderR.curveTo(2.76f, 19.0f, 2.54f, 19.54f, 2.85f, 19.85f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(3.0f, 18.0f, 16.97f, 0.0f);
        pathBuilderR2.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderR2.curveToRelative(0.29f, -3.26f, -2.28f, -6.0f, -5.48f, -6.0f);
        pathBuilderR2.curveToRelative(-2.35f, 0.0f, -4.35f, 1.48f, -5.14f, 3.55f);
        pathBuilderR2.curveTo(8.94f, 15.32f, 8.48f, 15.17f, 8.0f, 15.08f);
        pathBuilderR2.verticalLineTo(9.0f);
        pathBuilderR2.horizontalLineToRelative(1.75f);
        pathBuilderR2.curveTo(10.99f, 9.0f, 12.0f, 7.99f, 12.0f, 6.75f);
        pathBuilderR2.verticalLineToRelative(0.0f);
        pathBuilderR2.horizontalLineToRelative(8.25f);
        pathBuilderR2.curveTo(20.66f, 6.75f, 21.0f, 6.41f, 21.0f, 6.0f);
        pathBuilderR2.reflectiveCurveToRelative(-0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderR2.horizontalLineTo(12.0f);
        pathBuilderR2.verticalLineToRelative(0.0f);
        pathBuilderR2.curveTo(12.0f, 4.01f, 10.99f, 3.0f, 9.75f, 3.0f);
        pathBuilderR2.horizontalLineToRelative(-6.0f);
        pathBuilderR2.curveTo(3.34f, 3.0f, 3.0f, 3.34f, 3.0f, 3.75f);
        pathBuilderR2.reflectiveCurveTo(3.34f, 4.5f, 3.75f, 4.5f);
        a0.a.w(pathBuilderR2, 4.0f, 0.75f, 3.75f);
        pathBuilderR2.curveTo(3.34f, 5.25f, 3.0f, 5.59f, 3.0f, 6.0f);
        pathBuilderR2.reflectiveCurveToRelative(0.34f, 0.75f, 0.75f, 0.75f);
        b.i(pathBuilderR2, 4.0f, 7.5f, 3.75f);
        pathBuilderR2.curveTo(3.34f, 7.5f, 3.0f, 7.84f, 3.0f, 8.25f);
        pathBuilderR2.reflectiveCurveTo(3.34f, 9.0f, 3.75f, 9.0f);
        pathBuilderR2.horizontalLineTo(4.0f);
        pathBuilderR2.verticalLineToRelative(7.39f);
        pathBuilderR2.curveTo(3.56f, 16.85f, 3.22f, 17.39f, 3.0f, 18.0f);
        v.b.w(pathBuilderR2, 8.0f, 4.5f, 2.0f, 0.75f);
        c.l(pathBuilderR2, 8.0f, 4.5f, 8.0f, 6.75f);
        a0.c.D(pathBuilderR2, 2.0f, 7.5f, 8.0f, 6.75f);
        a.C(pathBuilderR2, 5.5f, 4.5f, 1.0f, 0.75f);
        c.D(pathBuilderR2, -1.0f, 4.5f, 5.5f, 6.75f);
        f.A(pathBuilderR2, 1.0f, 7.5f, -1.0f, 6.75f);
        a.C(pathBuilderR2, 5.5f, 9.0f, 1.0f, 6.06f);
        pathBuilderR2.curveToRelative(-0.35f, 0.06f, -0.68f, 0.17f, -1.0f, 0.3f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderR2, 9.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dinnerDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
