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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaHorizontalSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaHorizontalSelect", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPanoramaHorizontalSelect", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaHorizontalSelectKt {
    private static ImageVector _panoramaHorizontalSelect;

    public static final ImageVector getPanoramaHorizontalSelect(Icons.TwoTone twoTone) {
        ImageVector imageVector = _panoramaHorizontalSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PanoramaHorizontalSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(4.0f, 6.38f, 11.25f);
        pathBuilderI.curveToRelative(2.01f, -0.59f, 4.61f, -1.13f, 8.0f, -1.13f);
        pathBuilderI.curveToRelative(3.38f, 0.0f, 5.99f, 0.54f, 8.0f, 1.13f);
        pathBuilderI.verticalLineTo(6.37f);
        pathBuilderI.curveToRelative(-2.01f, 0.59f, -4.62f, 1.13f, -8.0f, 1.13f);
        pathBuilderI.curveTo(9.32f, 7.5f, 6.58f, 7.11f, 4.0f, 6.38f);
        pathBuilderI.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.01f, 4.0f);
        pathBuilderA.curveTo(20.45f, 4.0f, 17.4f, 5.5f, 12.0f, 5.5f);
        pathBuilderA.curveToRelative(-5.31f, 0.0f, -8.49f, -1.49f, -9.01f, -1.49f);
        pathBuilderA.curveTo(2.46f, 4.01f, 2.0f, 4.45f, 2.0f, 5.02f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.57f, 0.46f, 1.0f, 0.99f, 1.0f);
        pathBuilderA.curveToRelative(0.57f, 0.0f, 3.55f, -1.5f, 9.01f, -1.5f);
        pathBuilderA.curveToRelative(5.42f, 0.0f, 8.44f, 1.5f, 9.01f, 1.5f);
        pathBuilderA.curveToRelative(0.53f, 0.0f, 0.99f, -0.43f, 0.99f, -1.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(22.0f, 4.43f, 21.54f, 4.0f, 21.01f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.0f, 17.63f);
        pathBuilderA.curveToRelative(-2.01f, -0.59f, -4.62f, -1.13f, -8.0f, -1.13f);
        pathBuilderA.curveToRelative(-3.39f, 0.0f, -5.99f, 0.54f, -8.0f, 1.13f);
        pathBuilderA.verticalLineTo(6.38f);
        pathBuilderA.curveTo(6.58f, 7.11f, 9.32f, 7.5f, 12.0f, 7.5f);
        pathBuilderA.curveToRelative(3.38f, 0.0f, 5.99f, -0.54f, 8.0f, -1.13f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 17.63f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaHorizontalSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}
