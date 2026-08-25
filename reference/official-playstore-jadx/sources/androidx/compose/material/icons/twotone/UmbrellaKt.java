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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_umbrella", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Umbrella", "Landroidx/compose/material/icons/Icons$TwoTone;", "getUmbrella", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UmbrellaKt {
    private static ImageVector _umbrella;

    public static final ImageVector getUmbrella(Icons.TwoTone twoTone) {
        ImageVector imageVector = _umbrella;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Umbrella", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.28f, 8.5f, 0.76f, 0.58f);
        pathBuilderR.lineToRelative(0.92f, -0.23f);
        pathBuilderR.lineTo(13.0f, 14.8f);
        c.x(pathBuilderR, 8.29f, 13.28f, 8.5f);
        pathBuilderR.moveTo(9.03f, 8.86f);
        pathBuilderR.lineTo(11.0f, 14.8f);
        pathBuilderR.verticalLineTo(8.29f);
        pathBuilderR.lineTo(10.72f, 8.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 9.96f, 9.09f, 9.03f, 8.86f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = w.a.n(14.5f, 6.92f, 13.0f, 5.77f, 3.88f);
        pathBuilderN.verticalLineTo(3.4f);
        pathBuilderN.curveToRelative(0.0f, -0.26f, 0.22f, -0.48f, 0.5f, -0.48f);
        pathBuilderN.curveToRelative(0.28f, 0.0f, 0.5f, 0.21f, 0.5f, 0.48f);
        c.i(pathBuilderN, 4.0f, 2.0f, 3.4f);
        pathBuilderN.curveTo(16.0f, 2.07f, 14.88f, 1.0f, 13.5f, 1.0f);
        pathBuilderN.curveTo(12.12f, 1.0f, 11.0f, 2.07f, 11.0f, 3.4f);
        pathBuilderN.verticalLineToRelative(0.48f);
        pathBuilderN.verticalLineToRelative(1.89f);
        pathBuilderN.lineTo(9.5f, 6.92f);
        pathBuilderN.lineTo(6.0f, 6.07f);
        pathBuilderN.lineToRelative(5.05f, 15.25f);
        pathBuilderN.curveTo(11.2f, 21.77f, 11.6f, 22.0f, 12.0f, 22.0f);
        pathBuilderN.reflectiveCurveToRelative(0.8f, -0.23f, 0.95f, -0.69f);
        b.y(pathBuilderN, 18.0f, 6.07f, 14.5f, 6.92f);
        pathBuilderN.moveTo(13.28f, 8.5f);
        pathBuilderN.lineToRelative(0.76f, 0.58f);
        pathBuilderN.lineToRelative(0.92f, -0.23f);
        pathBuilderN.lineTo(13.0f, 14.8f);
        c.x(pathBuilderN, 8.29f, 13.28f, 8.5f);
        pathBuilderN.moveTo(9.96f, 9.09f);
        pathBuilderN.lineToRelative(0.76f, -0.58f);
        pathBuilderN.lineTo(11.0f, 8.29f);
        pathBuilderN.verticalLineToRelative(6.51f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderN, 9.03f, 8.86f, 9.96f, 9.09f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _umbrella = imageVectorBuild;
        return imageVectorBuild;
    }
}
