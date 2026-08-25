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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tram", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tram", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTram", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TramKt {
    private static ImageVector _tram;

    public static final ImageVector getTram(Icons.TwoTone twoTone) {
        ImageVector imageVector = _tram;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Tram", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(12.97f, 7.0f, -1.94f);
        pathBuilderA.curveToRelative(-2.75f, 0.08f, -3.62f, 0.58f, -3.9f, 1.0f);
        pathBuilderA.horizontalLineToRelative(9.74f);
        pathBuilderA.curveToRelative(-0.28f, -0.42f, -1.15f, -0.92f, -3.9f, -1.0f);
        d.k(pathBuilderA, 7.0f, 16.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.45f, 0.3f, 0.84f, 0.74f, 0.95f);
        pathBuilderA.horizontalLineToRelative(3.11f);
        pathBuilderA.curveToRelative(-0.22f, -0.26f, -0.35f, -0.59f, -0.35f, -0.95f);
        pathBuilderA.curveToRelative(0.0f, -0.39f, 0.15f, -0.73f, 0.39f, -1.0f);
        c.z(pathBuilderA, 7.0f, 16.0f, 13.5f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 0.36f, -0.13f, 0.69f, -0.35f, 0.95f);
        pathBuilderA.horizontalLineToRelative(3.11f);
        pathBuilderA.curveToRelative(0.44f, -0.11f, 0.74f, -0.5f, 0.74f, -0.95f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.horizontalLineToRelative(-3.89f);
        pathBuilderA.curveToRelative(0.24f, 0.27f, 0.39f, 0.61f, 0.39f, 1.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(13.0f, 5.0f, 0.75f, -1.5f);
        pathBuilderR.lineTo(17.0f, 3.5f);
        pathBuilderR.lineTo(17.0f, 2.0f);
        a.h(pathBuilderR, 7.0f, 2.0f, 1.5f, 4.75f);
        pathBuilderR.lineTo(11.0f, 5.0f);
        pathBuilderR.curveToRelative(-3.13f, 0.09f, -6.0f, 0.73f, -6.0f, 3.5f);
        pathBuilderR.lineTo(5.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, 1.5f, 1.11f, 2.73f, 2.55f, 2.95f);
        a.h(pathBuilderR, 6.0f, 21.5f, 0.5f, 2.0f);
        pathBuilderR.lineToRelative(2.0f, -2.0f);
        e.C(pathBuilderR, 4.0f, 2.0f, 2.0f, 2.0f);
        f.q(pathBuilderR, -0.5f, -1.55f, -1.55f, -0.01f);
        pathBuilderR.horizontalLineToRelative(0.01f);
        pathBuilderR.curveTo(17.89f, 19.73f, 19.0f, 18.5f, 19.0f, 17.0f);
        pathBuilderR.lineTo(19.0f, 8.5f);
        pathBuilderR.curveToRelative(0.0f, -2.77f, -2.87f, -3.41f, -6.0f, -3.5f);
        a.g(pathBuilderR, 11.03f, 7.0f, 1.94f);
        pathBuilderR.curveToRelative(2.75f, 0.08f, 3.62f, 0.58f, 3.9f, 1.0f);
        pathBuilderR.lineTo(7.13f, 8.0f);
        pathBuilderR.curveToRelative(0.28f, -0.42f, 1.15f, -0.92f, 3.9f, -1.0f);
        a.p(pathBuilderR, 10.85f, 17.95f, 7.74f, 17.95f);
        pathBuilderR.curveTo(7.3f, 17.84f, 7.0f, 17.45f, 7.0f, 17.0f);
        pathBuilderR.verticalLineToRelative(-1.0f);
        pathBuilderR.horizontalLineToRelative(3.89f);
        pathBuilderR.curveToRelative(-0.24f, 0.27f, -0.39f, 0.61f, -0.39f, 1.0f);
        pathBuilderR.curveToRelative(0.0f, 0.36f, 0.13f, 0.69f, 0.35f, 0.95f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 17.0f);
        pathBuilderR.curveToRelative(0.0f, 0.45f, -0.3f, 0.84f, -0.74f, 0.95f);
        pathBuilderR.horizontalLineToRelative(-3.11f);
        pathBuilderR.curveToRelative(0.22f, -0.26f, 0.35f, -0.59f, 0.35f, -0.95f);
        pathBuilderR.curveToRelative(0.0f, -0.39f, -0.15f, -0.73f, -0.39f, -1.0f);
        d.f(pathBuilderR, 17.0f, 16.0f, 1.0f);
        pathBuilderR.moveTo(17.0f, 14.0f);
        a.h(pathBuilderR, 7.0f, 14.0f, -4.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderR, 4.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tram = imageVectorBuild;
        return imageVectorBuild;
    }
}
