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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_permCameraMic", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PermCameraMic", "Landroidx/compose/material/icons/Icons$Rounded;", "getPermCameraMic", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PermCameraMicKt {
    private static ImageVector _permCameraMic;

    public static final ImageVector getPermCameraMic(Icons.Rounded rounded) {
        ImageVector imageVector = _permCameraMic;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PermCameraMic", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 5.0f, -3.17f, -1.24f, -1.35f);
        pathBuilderG.curveToRelative(-0.37f, -0.41f, -0.91f, -0.65f, -1.47f, -0.65f);
        pathBuilderG.lineTo(9.88f, 3.0f);
        pathBuilderG.curveToRelative(-0.56f, 0.0f, -1.1f, 0.24f, -1.48f, 0.65f);
        pathBuilderG.lineTo(7.17f, 5.0f);
        pathBuilderG.lineTo(4.0f, 5.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(7.0f);
        pathBuilderG.verticalLineToRelative(-2.09f);
        pathBuilderG.curveToRelative(-2.45f, -0.42f, -4.41f, -2.32f, -4.89f, -4.75f);
        pathBuilderG.curveToRelative(-0.12f, -0.61f, 0.38f, -1.16f, 0.99f, -1.16f);
        pathBuilderG.curveToRelative(0.49f, 0.0f, 0.88f, 0.35f, 0.98f, 0.83f);
        pathBuilderG.curveTo(8.47f, 15.64f, 10.07f, 17.0f, 12.0f, 17.0f);
        pathBuilderG.reflectiveCurveToRelative(3.53f, -1.36f, 3.91f, -3.17f);
        pathBuilderG.curveToRelative(0.1f, -0.48f, 0.5f, -0.83f, 0.98f, -0.83f);
        pathBuilderG.curveToRelative(0.61f, 0.0f, 1.11f, 0.55f, 0.99f, 1.16f);
        pathBuilderG.curveToRelative(-0.48f, 2.43f, -2.44f, 4.34f, -4.89f, 4.75f);
        pathBuilderG.lineTo(12.99f, 21.0f);
        pathBuilderG.horizontalLineToRelative(7.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(21.99f, 7.0f);
        pathBuilderG.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(14.0f, 13.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderG.lineTo(10.0f, 9.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderG.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderG, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _permCameraMic = imageVectorBuild;
        return imageVectorBuild;
    }
}
