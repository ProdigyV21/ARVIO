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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_starRate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StarRate", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStarRate", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarRateKt {
    private static ImageVector _starRate;

    public static final ImageVector getStarRate(Icons.TwoTone twoTone) {
        ImageVector imageVector = _starRate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.StarRate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.94f, 12.0f, -0.94f, -3.11f);
        pathBuilderR.lineToRelative(-0.94f, 3.11f);
        pathBuilderR.lineToRelative(-2.82f, 0.0f);
        pathBuilderR.lineToRelative(2.27f, 1.62f);
        pathBuilderR.lineToRelative(-0.93f, 3.01f);
        pathBuilderR.lineToRelative(2.42f, -1.84f);
        pathBuilderR.lineToRelative(2.42f, 1.84f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -0.93f, -3.01f, 2.27f, -1.62f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(22.0f, 10.0f, -7.58f, 12.0f, 2.0f);
        pathBuilderI.lineToRelative(-2.42f, 8.0f);
        pathBuilderI.horizontalLineTo(2.0f);
        pathBuilderI.lineToRelative(6.17f, 4.41f);
        pathBuilderI.lineTo(5.83f, 22.0f);
        pathBuilderI.lineTo(12.0f, 17.31f);
        pathBuilderI.lineTo(18.17f, 22.0f);
        d.C(pathBuilderI, -2.35f, -7.59f, 22.0f, 10.0f);
        pathBuilderI.moveTo(14.42f, 16.63f);
        pathBuilderI.lineTo(12.0f, 14.79f);
        pathBuilderI.lineToRelative(-2.42f, 1.84f);
        pathBuilderI.lineToRelative(0.93f, -3.01f);
        pathBuilderI.lineTo(8.24f, 12.0f);
        pathBuilderI.horizontalLineToRelative(2.82f);
        pathBuilderI.lineTo(12.0f, 8.89f);
        pathBuilderI.lineTo(12.94f, 12.0f);
        pathBuilderI.horizontalLineToRelative(2.82f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderI, -2.27f, 1.62f, 14.42f, 16.63f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _starRate = imageVectorBuild;
        return imageVectorBuild;
    }
}
