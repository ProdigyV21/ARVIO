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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_castConnected", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CastConnected", "Landroidx/compose/material/icons/Icons$Rounded;", "getCastConnected", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CastConnectedKt {
    private static ImageVector _castConnected;

    public static final ImageVector getCastConnected(Icons.Rounded rounded) {
        ImageVector imageVector = _castConnected;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CastConnected", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 16.0f, 19.0f, 8.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(6.0f, 7.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.63f);
        pathBuilderJ.curveToRelative(3.96f, 1.28f, 7.09f, 4.41f, 8.37f, 8.37f);
        pathBuilderJ.lineTo(18.0f, 17.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        a.p(pathBuilderJ, 21.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.lineTo(3.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(-5.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(6.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(23.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        c.y(pathBuilderJ, 1.0f, 18.0f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.62f, -0.19f, -1.2f, -0.51f, -1.68f);
        pathBuilderJ.curveTo(2.95f, 18.52f, 2.04f, 18.0f, 1.0f, 18.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(2.14f, 14.09f);
        pathBuilderJ.curveToRelative(-0.6f, -0.1f, -1.14f, 0.39f, -1.14f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.49f, 0.36f, 0.9f, 0.85f, 0.98f);
        pathBuilderJ.curveToRelative(2.08f, 0.36f, 3.72f, 2.0f, 4.08f, 4.08f);
        pathBuilderJ.curveToRelative(0.08f, 0.49f, 0.49f, 0.85f, 0.98f, 0.85f);
        pathBuilderJ.curveToRelative(0.61f, 0.0f, 1.09f, -0.54f, 1.0f, -1.14f);
        pathBuilderJ.curveToRelative(-0.48f, -2.96f, -2.82f, -5.29f, -5.77f, -5.77f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(2.1f, 10.05f);
        pathBuilderJ.curveToRelative(-0.59f, -0.05f, -1.1f, 0.41f, -1.1f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.51f, 0.38f, 0.94f, 0.88f, 0.99f);
        pathBuilderJ.curveToRelative(4.27f, 0.41f, 7.67f, 3.81f, 8.08f, 8.08f);
        pathBuilderJ.curveToRelative(0.05f, 0.5f, 0.48f, 0.87f, 0.99f, 0.87f);
        pathBuilderJ.curveToRelative(0.6f, 0.0f, 1.06f, -0.52f, 1.0f, -1.11f);
        pathBuilderJ.curveToRelative(-0.53f, -5.19f, -4.66f, -9.31f, -9.85f, -9.83f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _castConnected = imageVectorBuild;
        return imageVectorBuild;
    }
}
