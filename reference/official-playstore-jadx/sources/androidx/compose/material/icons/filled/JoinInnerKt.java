package androidx.compose.material.icons.filled;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_joinInner", "Landroidx/compose/ui/graphics/vector/ImageVector;", "JoinInner", "Landroidx/compose/material/icons/Icons$Filled;", "getJoinInner", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JoinInnerKt {
    private static ImageVector _joinInner;

    public static final ImageVector getJoinInner(Icons.Filled filled) {
        ImageVector imageVector = _joinInner;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.JoinInner", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 12.0f);
        pathBuilderA.arcToRelative(3.0f, 5.74f, 0.0f, true, false, 6.0f, 0.0f);
        pathBuilderA.arcToRelative(3.0f, 5.74f, 0.0f, true, false, -6.0f, 0.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(9.04f, 16.87f);
        pathBuilderA2.curveTo(8.71f, 16.95f, 8.36f, 17.0f, 8.0f, 17.0f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA2.curveToRelative(0.36f, 0.0f, 0.71f, 0.05f, 1.04f, 0.13f);
        pathBuilderA2.curveToRelative(0.39f, -0.56f, 0.88f, -1.12f, 1.49f, -1.63f);
        pathBuilderA2.curveTo(9.75f, 5.19f, 8.9f, 5.0f, 8.0f, 5.0f);
        pathBuilderA2.curveToRelative(-3.86f, 0.0f, -7.0f, 3.14f, -7.0f, 7.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.14f, 7.0f, 7.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.9f, 0.0f, 1.75f, -0.19f, 2.53f, -0.5f);
        pathBuilderA2.curveTo(9.92f, 17.99f, 9.43f, 17.43f, 9.04f, 16.87f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(16.0f, 5.0f);
        pathBuilderA3.curveToRelative(-0.9f, 0.0f, -1.75f, 0.19f, -2.53f, 0.5f);
        pathBuilderA3.curveToRelative(0.61f, 0.51f, 1.1f, 1.07f, 1.49f, 1.63f);
        pathBuilderA3.curveTo(15.29f, 7.05f, 15.64f, 7.0f, 16.0f, 7.0f);
        pathBuilderA3.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA3.reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderA3.curveToRelative(-0.36f, 0.0f, -0.71f, -0.05f, -1.04f, -0.13f);
        pathBuilderA3.curveToRelative(-0.39f, 0.56f, -0.88f, 1.12f, -1.49f, 1.63f);
        pathBuilderA3.curveTo(14.25f, 18.81f, 15.1f, 19.0f, 16.0f, 19.0f);
        pathBuilderA3.curveToRelative(3.86f, 0.0f, 7.0f, -3.14f, 7.0f, -7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA3, 19.86f, 5.0f, 16.0f, 5.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _joinInner = imageVectorBuild;
        return imageVectorBuild;
    }
}
