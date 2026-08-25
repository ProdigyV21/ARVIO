package androidx.compose.material.icons.sharp;

import a0.a;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bedroomBaby", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BedroomBaby", "Landroidx/compose/material/icons/Icons$Sharp;", "getBedroomBaby", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BedroomBabyKt {
    private static ImageVector _bedroomBaby;

    public static final ImageVector getBedroomBaby(Icons.Sharp sharp) {
        ImageVector imageVector = _bedroomBaby;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.BedroomBaby", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(22.0f, 2.0f, 2.0f, 20.0f, 20.0f);
        f.r(pathBuilderH, 2.0f, 12.0f, 18.0f);
        pathBuilderH.curveToRelative(-2.64f, 0.0f, -5.13f, -1.03f, -7.0f, -2.9f);
        pathBuilderH.lineToRelative(1.06f, -1.06f);
        pathBuilderH.curveToRelative(0.34f, 0.34f, 0.71f, 0.65f, 1.1f, 0.92f);
        pathBuilderH.lineTo(8.0f, 13.5f);
        pathBuilderH.verticalLineTo(9.51f);
        pathBuilderH.lineTo(6.45f, 10.5f);
        pathBuilderH.lineToRelative(-0.95f, -1.0f);
        pathBuilderH.lineTo(7.0f, 7.76f);
        pathBuilderH.lineTo(6.0f, 7.0f);
        a.n(pathBuilderH, 3.65f, 1.73f, 3.0f, 17.0f);
        e.l(pathBuilderH, 1.0f, -1.0f, 2.5f);
        pathBuilderH.lineToRelative(0.84f, 1.46f);
        pathBuilderH.curveToRelative(0.39f, -0.28f, 0.76f, -0.58f, 1.1f, -0.92f);
        pathBuilderH.lineTo(19.0f, 15.1f);
        pathBuilderH.curveTo(17.13f, 16.97f, 14.64f, 18.0f, 12.0f, 18.0f);
        pathBuilderH.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderH.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.69f, 14.24f);
        pathBuilderA.curveToRelative(-1.74f, 0.65f, -3.66f, 0.65f, -5.4f, 0.0f);
        pathBuilderA.lineToRelative(-0.81f, 1.41f);
        pathBuilderA.lineToRelative(-0.03f, 0.06f);
        pathBuilderA.curveToRelative(1.1f, 0.52f, 2.28f, 0.79f, 3.53f, 0.79f);
        pathBuilderA.reflectiveCurveToRelative(2.45f, -0.28f, 3.55f, -0.79f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, -0.03f, -0.06f, 14.69f, 14.24f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bedroomBaby = imageVectorBuild;
        return imageVectorBuild;
    }
}
