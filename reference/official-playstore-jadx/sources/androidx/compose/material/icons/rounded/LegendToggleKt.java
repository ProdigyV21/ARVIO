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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_legendToggle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LegendToggle", "Landroidx/compose/material/icons/Icons$Rounded;", "getLegendToggle", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LegendToggleKt {
    private static ImageVector _legendToggle;

    public static final ImageVector getLegendToggle(Icons.Rounded rounded) {
        ImageVector imageVector = _legendToggle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LegendToggle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 15.0f, 5.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(20.0f, 14.55f, 19.55f, 15.0f, 19.0f, 15.0f);
        w.a.r(pathBuilderQ, 19.0f, 17.0f, 5.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(20.0f, 17.45f, 19.55f, 17.0f, 19.0f, 17.0f);
        a.z(pathBuilderQ, 15.0f, 11.0f, 4.58f, -3.25f);
        pathBuilderQ.curveTo(19.84f, 7.56f, 20.0f, 7.26f, 20.0f, 6.94f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.81f, -0.92f, -1.29f, -1.58f, -0.82f);
        pathBuilderQ.lineTo(15.0f, 8.55f);
        pathBuilderQ.lineTo(10.0f, 5.0f);
        pathBuilderQ.lineTo(4.48f, 8.36f);
        pathBuilderQ.curveTo(4.18f, 8.55f, 4.0f, 8.87f, 4.0f, 9.22f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.78f, 0.85f, 1.26f, 1.52f, 0.85f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ, 4.4f, -2.68f, 15.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _legendToggle = imageVectorBuild;
        return imageVectorBuild;
    }
}
