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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_roofing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Roofing", "Landroidx/compose/material/icons/Icons$Rounded;", "getRoofing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoofingKt {
    private static ImageVector _roofing;

    public static final ImageVector getRoofing(Icons.Rounded rounded) {
        ImageVector imageVector = _roofing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Roofing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(13.0f, 18.0f, -2.0f, -2.0f, 2.0f);
        w.a.k(pathBuilderK, 18.0f, 9.0f, 15.0f, 4.0f);
        pathBuilderK.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderK.horizontalLineToRelative(4.0f);
        pathBuilderK.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderK.verticalLineToRelative(-4.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderK.horizontalLineToRelative(-4.0f);
        pathBuilderK.curveTo(9.45f, 14.0f, 9.0f, 14.45f, 9.0f, 15.0f);
        a.p(pathBuilderK, 19.0f, 9.3f, 19.0f, 9.3f);
        pathBuilderK.verticalLineTo(5.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderK.horizontalLineToRelative(-1.0f);
        pathBuilderK.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderK.verticalLineToRelative(1.6f);
        pathBuilderK.verticalLineToRelative(0.0f);
        pathBuilderK.lineToRelative(-3.33f, -3.0f);
        pathBuilderK.curveToRelative(-0.38f, -0.34f, -0.96f, -0.34f, -1.34f, 0.0f);
        pathBuilderK.lineToRelative(-8.36f, 7.53f);
        pathBuilderK.curveTo(2.63f, 11.43f, 2.84f, 12.0f, 3.3f, 12.0f);
        pathBuilderK.horizontalLineToRelative(1.31f);
        pathBuilderK.curveToRelative(0.25f, 0.0f, 0.49f, -0.09f, 0.67f, -0.26f);
        pathBuilderK.lineTo(12.0f, 5.69f);
        pathBuilderK.lineToRelative(6.71f, 6.05f);
        pathBuilderK.curveTo(18.9f, 11.91f, 19.14f, 12.0f, 19.38f, 12.0f);
        pathBuilderK.horizontalLineToRelative(1.31f);
        pathBuilderK.curveToRelative(0.46f, 0.0f, 0.68f, -0.57f, 0.33f, -0.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderK, 19.0f, 9.3f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _roofing = imageVectorBuild;
        return imageVectorBuild;
    }
}
