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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doNotDisturbOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoNotDisturbOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getDoNotDisturbOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoNotDisturbOffKt {
    private static ImageVector _doNotDisturbOff;

    public static final ImageVector getDoNotDisturbOff(Icons.Outlined outlined) {
        ImageVector imageVector = _doNotDisturbOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DoNotDisturbOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.94f, 5.12f);
        pathBuilderA.curveTo(9.14f, 4.41f, 10.52f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.48f, -0.41f, 2.86f, -1.12f, 4.06f);
        pathBuilderA.lineToRelative(1.46f, 1.46f);
        pathBuilderA.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.curveTo(9.96f, 2.0f, 8.07f, 2.61f, 6.49f, 3.66f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 7.94f, 5.12f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderJ.lineToRelative(2.27f, 2.27f);
        pathBuilderJ.curveTo(2.61f, 8.07f, 2.0f, 9.96f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderJ.curveToRelative(2.04f, 0.0f, 3.93f, -0.61f, 5.51f, -1.66f);
        pathBuilderJ.lineToRelative(2.27f, 2.27f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 2.81f, 2.81f);
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.48f, 0.41f, -2.86f, 1.12f, -4.06f);
        b.m(pathBuilderJ, 8.17f, 11.0f, 7.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(3.17f);
        pathBuilderJ.lineToRelative(5.88f, 5.88f);
        pathBuilderJ.curveTo(14.86f, 19.59f, 13.48f, 20.0f, 12.0f, 20.0f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(13.83f, 11.0f, 2.0f, 2.0f), 1.17f, 0.0f, 0.0f, -2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doNotDisturbOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
