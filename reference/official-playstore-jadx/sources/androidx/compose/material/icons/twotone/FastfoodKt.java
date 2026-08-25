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
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fastfood", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fastfood", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFastfood", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FastfoodKt {
    private static ImageVector _fastfood;

    public static final ImageVector getFastfood(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fastfood;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Fastfood", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(1.0f, 21.98f);
        pathBuilderA.curveToRelative(0.0f, 0.56f, 0.45f, 1.01f, 1.01f, 1.01f);
        pathBuilderA.horizontalLineTo(15.0f);
        pathBuilderA.curveToRelative(0.56f, 0.0f, 1.01f, -0.45f, 1.01f, -1.01f);
        pathBuilderA.verticalLineTo(21.0f);
        pathBuilderA.horizontalLineTo(1.0f);
        pathBuilderA.verticalLineToRelative(0.98f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(8.5f, 10.99f);
        pathBuilderA2.curveToRelative(-1.42f, 0.0f, -3.77f, 0.46f, -4.88f, 2.01f);
        pathBuilderA2.horizontalLineToRelative(9.77f);
        pathBuilderA2.curveToRelative(-1.12f, -1.55f, -3.47f, -2.01f, -4.89f, -2.01f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(8.5f, 8.99f);
        pathBuilderA3.curveTo(4.75f, 8.99f, 1.0f, 11.0f, 1.0f, 15.0f);
        pathBuilderA3.horizontalLineToRelative(15.0f);
        pathBuilderA3.curveToRelative(0.0f, -4.0f, -3.75f, -6.01f, -7.5f, -6.01f);
        pathBuilderA3.close();
        pathBuilderA3.moveTo(3.62f, 13.0f);
        pathBuilderA3.curveToRelative(1.11f, -1.55f, 3.47f, -2.01f, 4.88f, -2.01f);
        pathBuilderA3.reflectiveCurveToRelative(3.77f, 0.46f, 4.88f, 2.01f);
        a.v(pathBuilderA3, 3.62f, 1.0f, 17.0f, 15.0f);
        e.y(pathBuilderA3, 2.0f, 1.0f, 18.0f, 5.0f);
        b.r(pathBuilderA3, 1.0f, -2.0f, 4.0f, -5.0f);
        pathBuilderA3.lineToRelative(0.23f, 2.0f);
        a0.a.n(pathBuilderA3, 9.56f, -1.4f, 14.0f, 18.0f);
        pathBuilderA3.verticalLineToRelative(2.0f);
        pathBuilderA3.horizontalLineToRelative(1.72f);
        pathBuilderA3.curveToRelative(0.84f, 0.0f, 1.53f, -0.65f, 1.63f, -1.47f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderA3, 23.0f, 5.0f, -5.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fastfood = imageVectorBuild;
        return imageVectorBuild;
    }
}
