package androidx.compose.material.icons.sharp;

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_person3", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Person3", "Landroidx/compose/material/icons/Icons$Sharp;", "getPerson3", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Person3Kt {
    private static ImageVector _person3;

    public static final ImageVector getPerson3(Icons.Sharp sharp) {
        ImageVector imageVector = _person3;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Person3", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.39f, 14.56f);
        pathBuilderA.curveTo(16.71f, 13.7f, 14.53f, 13.0f, 12.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA.curveTo(4.61f, 15.07f, 4.0f, 16.1f, 4.0f, 17.22f);
        a.j(pathBuilderA, 20.0f, 16.0f, -2.78f);
        pathBuilderA.curveTo(20.0f, 16.1f, 19.39f, 15.07f, 18.39f, 14.56f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(10.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.17f, 0.0f, 3.83f, 0.0f, 4.0f, 0.0f);
        pathBuilderA2.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.73f, -0.27f, -1.4f, -0.71f, -1.92f);
        pathBuilderA2.curveTo(16.42f, 6.75f, 16.5f, 6.38f, 16.5f, 6.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.25f, -0.77f, -2.32f, -1.86f, -2.77f);
        pathBuilderA2.curveTo(14.0f, 2.48f, 13.06f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.0f, 0.48f, -2.64f, 1.23f);
        pathBuilderA2.curveTo(8.27f, 3.68f, 7.5f, 4.75f, 7.5f, 6.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.38f, 0.08f, 0.75f, 0.21f, 1.08f);
        pathBuilderA2.curveTo(7.27f, 7.6f, 7.0f, 8.27f, 7.0f, 9.0f);
        pathBuilderA2.curveTo(7.0f, 10.66f, 8.34f, 12.0f, 10.0f, 12.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _person3 = imageVectorBuild;
        return imageVectorBuild;
    }
}
