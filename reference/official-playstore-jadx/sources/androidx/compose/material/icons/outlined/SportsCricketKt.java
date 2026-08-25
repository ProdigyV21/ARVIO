package androidx.compose.material.icons.outlined;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsCricket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsCricket", "Landroidx/compose/material/icons/Icons$Outlined;", "getSportsCricket", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsCricketKt {
    private static ImageVector _sportsCricket;

    public static final ImageVector getSportsCricket(Icons.Outlined outlined) {
        ImageVector imageVector = _sportsCricket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SportsCricket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.04f, 12.79f, -8.5f, -8.5f);
        pathBuilderR.curveTo(6.35f, 4.1f, 6.09f, 4.0f, 5.83f, 4.0f);
        pathBuilderR.reflectiveCurveTo(5.32f, 4.1f, 5.13f, 4.29f);
        pathBuilderR.lineTo(2.29f, 7.13f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.42f);
        pathBuilderR.lineToRelative(8.5f, 8.5f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.45f, 0.29f, 0.71f, 0.29f);
        pathBuilderR.curveToRelative(0.26f, 0.0f, 0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilderR.lineToRelative(2.83f, -2.83f);
        pathBuilderR.curveTo(15.43f, 13.82f, 15.43f, 13.18f, 15.04f, 12.79f);
        a.p(pathBuilderR, 11.5f, 14.92f, 4.41f, 7.83f);
        pathBuilderR.lineToRelative(1.42f, -1.42f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 7.09f, 7.09f, 11.5f, 14.92f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(14.341f, 17.756f, 1.414f, -1.414f), 4.243f, 4.243f, -1.414f, 1.414f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.5f, 2.0f);
        pathBuilderA.curveTo(16.57f, 2.0f, 15.0f, 3.57f, 15.0f, 5.5f);
        pathBuilderA.curveTo(15.0f, 7.43f, 16.57f, 9.0f, 18.5f, 9.0f);
        pathBuilderA.reflectiveCurveTo(22.0f, 7.43f, 22.0f, 5.5f);
        pathBuilderA.curveTo(22.0f, 3.57f, 20.43f, 2.0f, 18.5f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.5f, 7.0f);
        pathBuilderA.curveTo(17.67f, 7.0f, 17.0f, 6.33f, 17.0f, 5.5f);
        pathBuilderA.reflectiveCurveTo(17.67f, 4.0f, 18.5f, 4.0f);
        pathBuilderA.reflectiveCurveTo(20.0f, 4.67f, 20.0f, 5.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 19.33f, 7.0f, 18.5f, 7.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsCricket = imageVectorBuild;
        return imageVectorBuild;
    }
}
