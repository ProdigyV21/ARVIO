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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_filterAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FilterAlt", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFilterAlt", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FilterAltKt {
    private static ImageVector _filterAlt;

    public static final ImageVector getFilterAlt(Icons.TwoTone twoTone) {
        ImageVector imageVector = _filterAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FilterAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(7.0f, 6.0f, 10.0f, -5.01f, 6.3f);
        c.z(pathBuilderG, 7.0f, 6.0f, 4.25f, 5.61f);
        pathBuilderG.curveTo(6.27f, 8.2f, 10.0f, 13.0f, 10.0f, 13.0f);
        pathBuilderG.verticalLineToRelative(6.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderG.horizontalLineToRelative(2.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderG.verticalLineToRelative(-6.0f);
        pathBuilderG.curveToRelative(0.0f, 0.0f, 3.72f, -4.8f, 5.74f, -7.39f);
        pathBuilderG.curveTo(20.25f, 4.95f, 19.78f, 4.0f, 18.95f, 4.0f);
        pathBuilderG.horizontalLineTo(5.04f);
        pathBuilderG.curveTo(4.21f, 4.0f, 3.74f, 4.95f, 4.25f, 5.61f);
        pathBuilderG.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(a.r(7.0f, 6.0f, 10.0f, 0.0f), -5.01f, 6.3f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 0.3f, null, 0.3f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _filterAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
