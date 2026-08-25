package androidx.compose.material.icons.rounded;

import a0.a;
import a0.b;
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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_filterListOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FilterListOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getFilterListOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FilterListOffKt {
    private static ImageVector _filterListOff;

    public static final ImageVector getFilterListOff(Icons.Rounded rounded) {
        ImageVector imageVector = _filterListOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FilterListOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        b.j(pathBuilderA, 8.83f, 2.0f, 2.0f, 20.0f);
        pathBuilderA.curveTo(20.55f, 8.0f, 21.0f, 7.55f, 21.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.n(pathBuilderA, -3.17f, 2.0f, 2.0f, 17.0f);
        pathBuilderA.curveTo(17.55f, 13.0f, 18.0f, 12.55f, 18.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.98f, 16.81f);
        pathBuilderA.curveTo(13.99f, 16.87f, 14.0f, 16.94f, 14.0f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.06f, 0.0f, 0.13f, 0.01f, 0.19f, 0.02f);
        pathBuilderA.lineTo(10.17f, 13.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.n(pathBuilderA, 1.17f, -3.0f, -3.0f, 4.0f);
        pathBuilderA.curveTo(3.45f, 8.0f, 3.0f, 7.55f, 3.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.32f, 0.15f, -0.6f, 0.38f, -0.79f);
        pathBuilderA.lineTo(2.1f, 4.93f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.reflectiveCurveToRelative(1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(16.97f, 16.97f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(-1.02f, 0.39f, -1.41f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 13.98f, 16.81f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _filterListOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
