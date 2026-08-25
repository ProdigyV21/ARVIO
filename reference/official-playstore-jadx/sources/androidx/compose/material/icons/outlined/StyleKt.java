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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_style", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Style", "Landroidx/compose/material/icons/Icons$Outlined;", "getStyle", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StyleKt {
    private static ImageVector _style;

    public static final ImageVector getStyle(Icons.Outlined outlined) {
        ImageVector imageVector = _style;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Style", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(2.53f, 19.65f, 1.34f, 0.56f, -9.03f);
        pathBuilderS.lineToRelative(-2.43f, 5.86f);
        pathBuilderS.curveToRelative(-0.41f, 1.02f, 0.08f, 2.19f, 1.09f, 2.61f);
        a.p(pathBuilderS, 22.03f, 15.95f, 17.07f, 3.98f);
        pathBuilderS.curveToRelative(-0.31f, -0.75f, -1.04f, -1.21f, -1.81f, -1.23f);
        pathBuilderS.curveToRelative(-0.26f, 0.0f, -0.53f, 0.04f, -0.79f, 0.15f);
        pathBuilderS.lineTo(7.1f, 5.95f);
        pathBuilderS.curveToRelative(-0.75f, 0.31f, -1.21f, 1.03f, -1.23f, 1.8f);
        pathBuilderS.curveToRelative(-0.01f, 0.27f, 0.04f, 0.54f, 0.15f, 0.8f);
        pathBuilderS.lineToRelative(4.96f, 11.97f);
        pathBuilderS.curveToRelative(0.31f, 0.76f, 1.05f, 1.22f, 1.83f, 1.23f);
        pathBuilderS.curveToRelative(0.26f, 0.0f, 0.52f, -0.05f, 0.77f, -0.15f);
        pathBuilderS.lineToRelative(7.36f, -3.05f);
        pathBuilderS.curveToRelative(1.02f, -0.42f, 1.51f, -1.59f, 1.09f, -2.6f);
        a.p(pathBuilderS, 12.83f, 19.75f, 7.87f, 7.79f);
        pathBuilderS.lineToRelative(7.35f, -3.04f);
        pathBuilderS.horizontalLineToRelative(0.01f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 4.95f, 11.95f, -7.35f, 3.05f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(11.0f, 9.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.88f, 19.75f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(1.45f);
        pathBuilderA.lineToRelative(-3.45f, -8.34f);
        pathBuilderA.verticalLineToRelative(6.34f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _style = imageVectorBuild;
        return imageVectorBuild;
    }
}
