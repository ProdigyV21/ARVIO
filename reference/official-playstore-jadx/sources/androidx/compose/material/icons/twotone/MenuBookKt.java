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
import w.a;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_menuBook", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MenuBook", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMenuBook$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getMenuBook", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MenuBookKt {
    private static ImageVector _menuBook;

    public static final ImageVector getMenuBook(Icons.TwoTone twoTone) {
        ImageVector imageVector = _menuBook;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MenuBook", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 5.0f);
        pathBuilderA.curveToRelative(-1.11f, -0.35f, -2.33f, -0.5f, -3.5f, -0.5f);
        pathBuilderA.curveToRelative(-1.95f, 0.0f, -4.05f, 0.4f, -5.5f, 1.5f);
        pathBuilderA.curveToRelative(-1.45f, -1.1f, -3.55f, -1.5f, -5.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(2.45f, 4.9f, 1.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(14.65f);
        pathBuilderA.curveToRelative(0.0f, 0.25f, 0.25f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.curveToRelative(0.1f, 0.0f, 0.15f, -0.05f, 0.25f, -0.05f);
        pathBuilderA.curveTo(3.1f, 20.45f, 5.05f, 20.0f, 6.5f, 20.0f);
        pathBuilderA.curveToRelative(1.95f, 0.0f, 4.05f, 0.4f, 5.5f, 1.5f);
        pathBuilderA.curveToRelative(1.35f, -0.85f, 3.8f, -1.5f, 5.5f, -1.5f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.35f, 0.3f, 4.75f, 1.05f);
        pathBuilderA.curveToRelative(0.1f, 0.05f, 0.15f, 0.05f, 0.25f, 0.05f);
        pathBuilderA.curveToRelative(0.25f, 0.0f, 0.5f, -0.25f, 0.5f, -0.5f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveTo(22.4f, 5.55f, 21.75f, 5.25f, 21.0f, 5.0f);
        a.u(pathBuilderA, 3.0f, 18.5f, 7.0f);
        pathBuilderA.curveToRelative(1.1f, -0.35f, 2.3f, -0.5f, 3.5f, -0.5f);
        pathBuilderA.curveToRelative(1.34f, 0.0f, 3.13f, 0.41f, 4.5f, 0.99f);
        pathBuilderA.verticalLineToRelative(11.5f);
        pathBuilderA.curveTo(9.63f, 18.41f, 7.84f, 18.0f, 6.5f, 18.0f);
        pathBuilderA.curveTo(5.3f, 18.0f, 4.1f, 18.15f, 3.0f, 18.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.0f, 18.5f);
        pathBuilderA.curveToRelative(-1.1f, -0.35f, -2.3f, -0.5f, -3.5f, -0.5f);
        pathBuilderA.curveToRelative(-1.34f, 0.0f, -3.13f, 0.41f, -4.5f, 0.99f);
        pathBuilderA.verticalLineTo(7.49f);
        pathBuilderA.curveToRelative(1.37f, -0.59f, 3.16f, -0.99f, 4.5f, -0.99f);
        pathBuilderA.curveToRelative(1.2f, 0.0f, 2.4f, 0.15f, 3.5f, 0.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 18.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(11.0f, 7.49f);
        pathBuilderA2.curveTo(9.63f, 6.91f, 7.84f, 6.5f, 6.5f, 6.5f);
        pathBuilderA2.curveTo(5.3f, 6.5f, 4.1f, 6.65f, 3.0f, 7.0f);
        pathBuilderA2.verticalLineToRelative(11.5f);
        pathBuilderA2.curveTo(4.1f, 18.15f, 5.3f, 18.0f, 6.5f, 18.0f);
        pathBuilderA2.curveToRelative(1.34f, 0.0f, 3.13f, 0.41f, 4.5f, 0.99f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA2, 7.49f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(17.5f, 10.5f);
        pathBuilderA3.curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f);
        pathBuilderA3.verticalLineTo(9.24f);
        pathBuilderA3.curveTo(19.21f, 9.09f, 18.36f, 9.0f, 17.5f, 9.0f);
        pathBuilderA3.curveToRelative(-1.28f, 0.0f, -2.46f, 0.16f, -3.5f, 0.47f);
        pathBuilderA3.verticalLineToRelative(1.57f);
        pathBuilderA3.curveTo(14.99f, 10.69f, 16.18f, 10.5f, 17.5f, 10.5f);
        pathBuilderA3.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA4 = c.a(17.5f, 13.16f);
        pathBuilderA4.curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f);
        pathBuilderA4.verticalLineTo(11.9f);
        pathBuilderA4.curveToRelative(-0.79f, -0.15f, -1.64f, -0.24f, -2.5f, -0.24f);
        pathBuilderA4.curveToRelative(-1.28f, 0.0f, -2.46f, 0.16f, -3.5f, 0.47f);
        pathBuilderA4.verticalLineToRelative(1.57f);
        pathBuilderA4.curveTo(14.99f, 13.36f, 16.18f, 13.16f, 17.5f, 13.16f);
        pathBuilderA4.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA5 = c.a(17.5f, 15.83f);
        pathBuilderA5.curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f);
        pathBuilderA5.verticalLineToRelative(-1.52f);
        pathBuilderA5.curveToRelative(-0.79f, -0.15f, -1.64f, -0.24f, -2.5f, -0.24f);
        pathBuilderA5.curveToRelative(-1.28f, 0.0f, -2.46f, 0.16f, -3.5f, 0.47f);
        pathBuilderA5.verticalLineToRelative(1.57f);
        pathBuilderA5.curveTo(14.99f, 16.02f, 16.18f, 15.83f, 17.5f, 15.83f);
        pathBuilderA5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _menuBook = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getMenuBook$annotations(Icons.TwoTone twoTone) {
    }
}
