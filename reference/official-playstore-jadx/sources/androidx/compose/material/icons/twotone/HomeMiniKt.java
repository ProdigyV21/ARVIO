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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_homeMini", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HomeMini", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHomeMini", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HomeMiniKt {
    private static ImageVector _homeMini;

    public static final ImageVector getHomeMini(Icons.TwoTone twoTone) {
        ImageVector imageVector = _homeMini;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HomeMini", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 7.0f);
        pathBuilderA.curveToRelative(-7.91f, 0.0f, -8.0f, 4.8f, -8.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveTo(19.99f, 11.51f, 19.64f, 7.0f, 12.0f, 7.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(9.14f, 17.0f, 5.72f);
        pathBuilderA2.curveToRelative(2.1f, 0.0f, 3.92f, -1.24f, 4.71f, -3.0f);
        pathBuilderA2.horizontalLineTo(4.42f);
        pathBuilderA2.curveTo(5.22f, 15.76f, 7.04f, 17.0f, 9.14f, 17.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(12.0f, 5.0f);
        pathBuilderA3.curveTo(4.19f, 5.0f, 2.0f, 9.48f, 2.0f, 12.0f);
        pathBuilderA3.curveToRelative(0.0f, 3.86f, 3.13f, 7.0f, 6.99f, 7.0f);
        pathBuilderA3.horizontalLineToRelative(6.02f);
        pathBuilderA3.curveToRelative(2.69f, 0.0f, 6.99f, -2.08f, 6.99f, -7.0f);
        pathBuilderA3.curveTo(22.0f, 12.0f, 22.0f, 5.0f, 12.0f, 5.0f);
        w.a.r(pathBuilderA3, 14.86f, 17.0f, 9.14f);
        pathBuilderA3.curveToRelative(-2.1f, 0.0f, -3.92f, -1.24f, -4.71f, -3.0f);
        pathBuilderA3.horizontalLineToRelative(15.15f);
        pathBuilderA3.curveTo(18.78f, 15.76f, 16.96f, 17.0f, 14.86f, 17.0f);
        pathBuilderA3.close();
        pathBuilderA3.moveTo(4.0f, 12.0f);
        pathBuilderA3.curveToRelative(0.0f, -0.2f, 0.09f, -5.0f, 8.0f, -5.0f);
        pathBuilderA3.curveToRelative(7.64f, 0.0f, 7.99f, 4.51f, 8.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA3, 4.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _homeMini = imageVectorBuild;
        return imageVectorBuild;
    }
}
