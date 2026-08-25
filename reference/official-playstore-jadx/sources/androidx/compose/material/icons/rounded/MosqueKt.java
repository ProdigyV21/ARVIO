package androidx.compose.material.icons.rounded;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mosque", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Mosque", "Landroidx/compose/material/icons/Icons$Rounded;", "getMosque", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MosqueKt {
    private static ImageVector _mosque;

    public static final ImageVector getMosque(Icons.Rounded rounded) {
        ImageVector imageVector = _mosque;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Mosque", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(7.0f, 8.0f, 10.0f);
        pathBuilderA.curveToRelative(0.29f, 0.0f, 0.57f, 0.06f, 0.84f, 0.13f);
        pathBuilderA.curveTo(17.93f, 7.8f, 18.0f, 7.46f, 18.0f, 7.09f);
        pathBuilderA.curveToRelative(0.0f, -1.31f, -0.65f, -2.53f, -1.74f, -3.25f);
        pathBuilderA.lineTo(12.0f, 1.0f);
        pathBuilderA.lineTo(7.74f, 3.84f);
        pathBuilderA.curveTo(6.65f, 4.56f, 6.0f, 5.78f, 6.0f, 7.09f);
        pathBuilderA.curveTo(6.0f, 7.46f, 6.07f, 7.8f, 6.16f, 8.13f);
        pathBuilderA.curveTo(6.43f, 8.06f, 6.71f, 8.0f, 7.0f, 8.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(24.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -2.0f, -3.0f, -2.0f, -3.0f);
        pathBuilderA2.reflectiveCurveToRelative(-2.0f, 1.9f, -2.0f, 3.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.74f, 0.4f, 1.38f, 1.0f, 1.72f);
        w.a.j(pathBuilderA2, 13.0f, -2.0f, -2.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA2.horizontalLineTo(7.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        w.a.y(pathBuilderA2, 2.0f, 3.0f, 8.72f);
        pathBuilderA2.curveTo(3.6f, 8.38f, 4.0f, 7.74f, 4.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -2.0f, -3.0f, -2.0f, -3.0f);
        pathBuilderA2.reflectiveCurveTo(0.0f, 5.9f, 0.0f, 7.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.74f, 0.4f, 1.38f, 1.0f, 1.72f);
        w.a.j(pathBuilderA2, 21.0f, 9.0f, -4.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        f.t(pathBuilderA2, 4.0f, 9.0f, 8.72f);
        pathBuilderA2.curveTo(23.6f, 8.38f, 24.0f, 7.74f, 24.0f, 7.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mosque = imageVectorBuild;
        return imageVectorBuild;
    }
}
