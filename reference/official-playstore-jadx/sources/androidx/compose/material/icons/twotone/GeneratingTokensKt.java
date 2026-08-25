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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_generatingTokens", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GeneratingTokens", "Landroidx/compose/material/icons/Icons$TwoTone;", "getGeneratingTokens", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GeneratingTokensKt {
    private static ImageVector _generatingTokens;

    public static final ImageVector getGeneratingTokens(Icons.TwoTone twoTone) {
        ImageVector imageVector = _generatingTokens;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.GeneratingTokens", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderA.reflectiveCurveTo(12.31f, 6.0f, 9.0f, 6.0f);
        b.w(pathBuilderA, 12.0f, 10.5f, -2.0f, 5.0f);
        a0.b.C(pathBuilderA, 8.0f, -5.0f, 6.0f, 9.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 6.0f, 10.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(9.0f, 4.0f);
        pathBuilderA2.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA2.curveTo(17.0f, 7.58f, 13.42f, 4.0f, 9.0f, 4.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(9.0f, 18.0f);
        pathBuilderA2.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA2.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderA2.reflectiveCurveTo(12.31f, 18.0f, 9.0f, 18.0f);
        b.w(pathBuilderA2, 12.0f, 10.5f, -2.0f, 5.0f);
        a0.b.C(pathBuilderA2, 8.0f, -5.0f, 6.0f, 9.0f);
        c.D(pathBuilderA2, 6.0f, 10.5f, 20.25f, 3.75f);
        pathBuilderA2.lineTo(23.0f, 5.0f);
        pathBuilderA2.lineToRelative(-2.75f, 1.25f);
        pathBuilderA2.lineTo(19.0f, 9.0f);
        pathBuilderA2.lineToRelative(-1.25f, -2.75f);
        pathBuilderA2.lineTo(15.0f, 5.0f);
        pathBuilderA2.lineToRelative(2.75f, -1.25f);
        b.y(pathBuilderA2, 19.0f, 1.0f, 20.25f, 3.75f);
        pathBuilderA2.moveTo(20.25f, 17.75f);
        pathBuilderA2.lineTo(23.0f, 19.0f);
        pathBuilderA2.lineToRelative(-2.75f, 1.25f);
        pathBuilderA2.lineTo(19.0f, 23.0f);
        pathBuilderA2.lineToRelative(-1.25f, -2.75f);
        pathBuilderA2.lineTo(15.0f, 19.0f);
        pathBuilderA2.lineToRelative(2.75f, -1.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA2, 19.0f, 15.0f, 20.25f, 17.75f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _generatingTokens = imageVectorBuild;
        return imageVectorBuild;
    }
}
