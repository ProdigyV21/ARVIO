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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rateReview", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RateReview", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRateReview", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RateReviewKt {
    private static ImageVector _rateReview;

    public static final ImageVector getRateReview(Icons.TwoTone twoTone) {
        ImageVector imageVector = _rateReview;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RateReview", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.0f, 17.17f, 0.59f, -0.59f);
        w.a.p(pathBuilderR, 0.58f, -0.58f, 20.0f, 4.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.verticalLineToRelative(13.17f);
        pathBuilderR.close();
        pathBuilderR.moveTo(18.0f, 14.0f);
        a0.a.n(pathBuilderR, -7.5f, 2.0f, -2.0f, 18.0f);
        b.f(pathBuilderR, 2.0f, 6.0f, 11.53f);
        pathBuilderR.lineToRelative(5.88f, -5.88f);
        pathBuilderR.curveToRelative(0.2f, -0.2f, 0.51f, -0.2f, 0.71f, 0.0f);
        pathBuilderR.lineToRelative(1.77f, 1.77f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.2f, 0.51f, 0.0f, 0.71f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.k(pathBuilderR, 8.47f, 14.0f, 6.0f, -2.47f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 2.0f, 4.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.lineTo(2.0f, 22.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.horizontalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 20.0f, 16.0f, 5.17f, 16.0f);
        pathBuilderJ.lineToRelative(-0.59f, 0.59f);
        pathBuilderJ.lineToRelative(-0.58f, 0.58f);
        b.g(pathBuilderJ, 4.0f, 4.0f, 16.0f, 12.0f);
        pathBuilderJ.moveTo(10.5f, 14.0f);
        e.p(pathBuilderJ, 18.0f, 14.0f, -2.0f, -5.5f);
        pathBuilderJ.moveTo(14.36f, 8.13f);
        pathBuilderJ.curveToRelative(0.2f, -0.2f, 0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderJ.lineToRelative(-1.77f, -1.77f);
        pathBuilderJ.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderJ.lineTo(6.0f, 11.53f);
        pathBuilderJ.lineTo(6.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.e(pathBuilderJ, 2.47f, 5.89f, -5.87f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rateReview = imageVectorBuild;
        return imageVectorBuild;
    }
}
