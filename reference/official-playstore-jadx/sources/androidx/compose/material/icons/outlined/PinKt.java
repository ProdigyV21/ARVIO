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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pin", "Landroidx/compose/material/icons/Icons$Outlined;", "getPin", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinKt {
    private static ImageVector _pin;

    public static final ImageVector getPin(Icons.Outlined outlined) {
        ImageVector imageVector = _pin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Pin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderQ, 20.0f, 18.0f, 4.0f, 6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, 16.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(6.49f, 10.5f, 0.0f, 4.5f);
        pathBuilderR.lineToRelative(1.15f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -6.0f);
        pathBuilderR.lineToRelative(-0.87f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -1.76f, 1.27f, 0.58f, 0.89f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.47f, 10.05f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.81f, 0.32f, 0.81f, 0.72f);
        pathBuilderA.curveToRelative(0.0f, 0.37f, -0.14f, 0.64f, -0.54f, 1.06f);
        pathBuilderA.curveToRelative(-0.36f, 0.38f, -1.06f, 1.08f, -2.13f, 2.15f);
        b.r(pathBuilderA, 15.0f, 3.89f, -0.99f, -2.37f);
        pathBuilderA.lineToRelative(-0.03f, -0.05f);
        pathBuilderA.curveToRelative(0.68f, -0.68f, 1.15f, -1.14f, 1.4f, -1.39f);
        pathBuilderA.curveToRelative(0.61f, -0.6f, 0.92f, -1.22f, 0.92f, -1.86f);
        pathBuilderA.curveToRelative(0.0f, -0.24f, -0.05f, -1.04f, -0.91f, -1.48f);
        pathBuilderA.curveTo(12.04f, 9.0f, 11.25f, 8.87f, 10.56f, 9.2f);
        pathBuilderA.curveToRelative(-0.82f, 0.39f, -0.99f, 1.13f, -1.0f, 1.15f);
        pathBuilderA.lineToRelative(1.01f, 0.42f);
        pathBuilderA.curveTo(10.67f, 10.44f, 10.95f, 10.05f, 11.47f, 10.05f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(16.99f, 13.94f);
        pathBuilderA2.curveToRelative(-0.83f, 0.0f, -0.99f, -0.76f, -1.02f, -0.86f);
        pathBuilderA2.lineToRelative(-1.03f, 0.41f);
        pathBuilderA2.curveToRelative(0.45f, 1.59f, 2.01f, 1.51f, 2.05f, 1.51f);
        pathBuilderA2.curveToRelative(1.2f, 0.0f, 1.68f, -0.72f, 1.76f, -0.85f);
        pathBuilderA2.curveToRelative(0.32f, -0.49f, 0.36f, -1.24f, -0.01f, -1.76f);
        pathBuilderA2.curveToRelative(-0.17f, -0.24f, -0.4f, -0.41f, -0.68f, -0.52f);
        pathBuilderA2.verticalLineTo(11.8f);
        pathBuilderA2.curveToRelative(0.2f, -0.1f, 0.37f, -0.26f, 0.52f, -0.48f);
        pathBuilderA2.curveToRelative(0.26f, -0.41f, 0.31f, -1.07f, -0.02f, -1.57f);
        pathBuilderA2.curveTo(18.48f, 9.64f, 18.03f, 9.0f, 16.94f, 9.0f);
        pathBuilderA2.curveToRelative(-1.26f, 0.0f, -1.74f, 0.9f, -1.85f, 1.24f);
        pathBuilderA2.lineToRelative(0.99f, 0.41f);
        pathBuilderA2.curveToRelative(0.11f, -0.32f, 0.35f, -0.64f, 0.85f, -0.64f);
        pathBuilderA2.curveToRelative(0.44f, 0.0f, 0.75f, 0.26f, 0.75f, 0.65f);
        pathBuilderA2.curveToRelative(0.0f, 0.58f, -0.55f, 0.72f, -0.88f, 0.72f);
        e.g(pathBuilderA2, -0.46f, 1.0f, 0.5f);
        pathBuilderA2.curveToRelative(0.56f, 0.0f, 1.04f, 0.24f, 1.04f, 0.79f);
        pathBuilderA2.curveTo(17.88f, 13.66f, 17.4f, 13.94f, 16.99f, 13.94f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pin = imageVectorBuild;
        return imageVectorBuild;
    }
}
