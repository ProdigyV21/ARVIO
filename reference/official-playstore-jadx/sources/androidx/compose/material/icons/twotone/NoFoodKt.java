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
import v.b;
import v.c;
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noFood", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoFood", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNoFood", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoFoodKt {
    private static ImageVector _noFood;

    public static final ImageVector getNoFood(Icons.TwoTone twoTone) {
        ImageVector imageVector = _noFood;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NoFood", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.16f, 11.0f);
        pathBuilderA.curveToRelative(-1.43f, 0.07f, -3.52f, 0.57f, -4.54f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.h(pathBuilderA, 6.55f, 8.16f, 11.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = d.a(1.0f, 21.0f, 15.01f, 0.98f);
        pathBuilderA2.curveToRelative(0.0f, 0.56f, -0.45f, 1.01f, -1.01f, 1.01f);
        pathBuilderA2.horizontalLineTo(2.01f);
        pathBuilderA2.curveTo(1.45f, 22.99f, 1.0f, 22.54f, 1.0f, 21.98f);
        f.r(pathBuilderA2, 21.0f, 20.49f, 23.31f);
        c.f(pathBuilderA2, 16.0f, 18.83f, 19.0f, 1.0f);
        d.D(pathBuilderA2, -2.0f, 13.17f, -2.0f, -2.0f);
        pathBuilderA2.horizontalLineTo(1.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.24f, 2.46f, -5.17f, 5.38f, -5.79f);
        pathBuilderA2.lineToRelative(-5.7f, -5.7f);
        pathBuilderA2.lineTo(2.1f, 2.1f);
        pathBuilderA2.lineTo(13.0f, 13.0f);
        pathBuilderA2.lineToRelative(2.0f, 2.0f);
        d.C(pathBuilderA2, 6.9f, 6.9f, 20.49f, 23.31f);
        pathBuilderA2.moveTo(10.17f, 13.0f);
        pathBuilderA2.lineToRelative(-2.0f, -2.0f);
        pathBuilderA2.curveToRelative(-1.42f, 0.06f, -3.52f, 0.56f, -4.55f, 2.0f);
        a.v(pathBuilderA2, 10.17f, 23.0f, 5.0f, -5.0f);
        b.r(pathBuilderA2, 1.0f, -2.0f, 4.0f, -5.0f);
        pathBuilderA2.lineToRelative(0.23f, 2.0f);
        pathBuilderA2.horizontalLineToRelative(9.56f);
        pathBuilderA2.lineToRelative(-1.0f, 9.97f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA2, 1.83f, 1.83f, 23.0f, 5.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noFood = imageVectorBuild;
        return imageVectorBuild;
    }
}
