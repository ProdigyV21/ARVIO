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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_anchor", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Anchor", "Landroidx/compose/material/icons/Icons$Rounded;", "getAnchor", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnchorKt {
    private static ImageVector _anchor;

    public static final ImageVector getAnchor(Icons.Rounded rounded) {
        ImageVector imageVector = _anchor;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Anchor", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(13.0f, 9.0f, 7.82f);
        pathBuilderA.curveTo(14.16f, 7.4f, 15.0f, 6.3f, 15.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.65f, -1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 3.35f, 9.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 1.3f, 0.84f, 2.4f, 2.0f, 2.82f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(8.92f);
        pathBuilderA.curveToRelative(-2.22f, -0.33f, -4.59f, -1.68f, -5.55f, -3.37f);
        pathBuilderA.lineToRelative(1.14f, -1.14f);
        pathBuilderA.curveToRelative(0.22f, -0.22f, 0.19f, -0.57f, -0.05f, -0.75f);
        pathBuilderA.lineTo(3.8f, 12.6f);
        pathBuilderA.curveTo(3.47f, 12.35f, 3.0f, 12.59f, 3.0f, 13.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 3.88f, 4.92f, 7.0f, 9.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(9.0f, -3.12f, 9.0f, -7.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, -0.47f, -0.65f, -0.8f, -0.4f);
        pathBuilderA.lineToRelative(-2.74f, 2.05f);
        pathBuilderA.curveToRelative(-0.24f, 0.18f, -0.27f, 0.54f, -0.05f, 0.75f);
        pathBuilderA.lineToRelative(1.14f, 1.14f);
        pathBuilderA.curveToRelative(-0.96f, 1.69f, -3.33f, 3.04f, -5.55f, 3.37f);
        pathBuilderA.verticalLineTo(11.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.x(pathBuilderA, 13.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 11.45f, 4.0f, 12.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _anchor = imageVectorBuild;
        return imageVectorBuild;
    }
}
