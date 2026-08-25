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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_chalet", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Chalet", "Landroidx/compose/material/icons/Icons$Rounded;", "getChalet", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChaletKt {
    private static ImageVector _chalet;

    public static final ImageVector getChalet(Icons.Rounded rounded) {
        ImageVector imageVector = _chalet;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Chalet", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.0f, 15.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.67f);
        pathBuilderA.lineToRelative(-0.38f, 0.38f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(6.09f, -6.09f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(6.09f, 6.09f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineTo(15.0f, 15.33f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-3.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveTo(11.0f, 15.45f, 10.55f, 15.0f, 10.0f, 15.0f);
        d.k(pathBuilderA, 17.5f, 7.5f, 0.89f);
        pathBuilderA.lineToRelative(-1.08f, 1.08f);
        pathBuilderA.curveToRelative(-0.18f, 0.18f, -0.21f, 0.48f, -0.05f, 0.69f);
        pathBuilderA.curveToRelative(0.19f, 0.23f, 0.53f, 0.24f, 0.74f, 0.04f);
        pathBuilderA.lineToRelative(0.39f, -0.39f);
        pathBuilderA.verticalLineToRelative(0.69f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineTo(9.81f);
        pathBuilderA.lineToRelative(0.39f, 0.39f);
        pathBuilderA.curveToRelative(0.21f, 0.21f, 0.55f, 0.19f, 0.74f, -0.04f);
        pathBuilderA.curveToRelative(0.17f, -0.2f, 0.14f, -0.5f, -0.05f, -0.69f);
        pathBuilderA.lineTo(18.5f, 8.39f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, -0.89f, 0.0f, -0.89f);
        pathBuilderA.horizontalLineToRelative(0.89f);
        pathBuilderA.lineToRelative(1.08f, 1.08f);
        pathBuilderA.curveToRelative(0.18f, 0.18f, 0.48f, 0.21f, 0.69f, 0.05f);
        pathBuilderA.curveToRelative(0.23f, -0.19f, 0.24f, -0.53f, 0.04f, -0.74f);
        pathBuilderA.lineTo(20.81f, 7.5f);
        pathBuilderA.horizontalLineToRelative(0.69f);
        pathBuilderA.curveTo(21.78f, 7.5f, 22.0f, 7.28f, 22.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.lineToRelative(-0.69f, 0.0f);
        pathBuilderA.lineToRelative(0.39f, -0.39f);
        pathBuilderA.curveToRelative(0.21f, -0.21f, 0.19f, -0.55f, -0.04f, -0.74f);
        pathBuilderA.curveToRelative(-0.2f, -0.17f, -0.5f, -0.14f, -0.69f, 0.05f);
        pathBuilderA.lineTo(19.39f, 6.5f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, -0.89f, 0.0f, -0.89f, 0.0f);
        pathBuilderA.verticalLineTo(5.61f);
        pathBuilderA.lineToRelative(1.08f, -1.08f);
        pathBuilderA.curveToRelative(0.18f, -0.18f, 0.21f, -0.48f, 0.05f, -0.69f);
        pathBuilderA.curveToRelative(-0.19f, -0.23f, -0.53f, -0.24f, -0.74f, -0.04f);
        pathBuilderA.lineTo(18.5f, 4.19f);
        pathBuilderA.verticalLineTo(3.5f);
        pathBuilderA.curveTo(18.5f, 3.22f, 18.28f, 3.0f, 18.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderA.verticalLineToRelative(0.69f);
        pathBuilderA.lineTo(17.11f, 3.8f);
        pathBuilderA.curveToRelative(-0.21f, -0.21f, -0.55f, -0.19f, -0.74f, 0.04f);
        pathBuilderA.curveToRelative(-0.17f, 0.2f, -0.14f, 0.5f, 0.05f, 0.69f);
        pathBuilderA.lineToRelative(1.08f, 1.08f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.89f, 0.0f, 0.89f);
        pathBuilderA.horizontalLineToRelative(-0.89f);
        pathBuilderA.lineToRelative(-1.08f, -1.08f);
        pathBuilderA.curveToRelative(-0.18f, -0.18f, -0.48f, -0.21f, -0.69f, -0.05f);
        pathBuilderA.curveToRelative(-0.23f, 0.19f, -0.24f, 0.53f, -0.04f, 0.74f);
        pathBuilderA.lineToRelative(0.39f, 0.39f);
        pathBuilderA.horizontalLineTo(14.5f);
        pathBuilderA.curveTo(14.22f, 6.5f, 14.0f, 6.72f, 14.0f, 7.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.69f, 0.0f);
        pathBuilderA.lineTo(14.8f, 7.89f);
        pathBuilderA.curveToRelative(-0.21f, 0.21f, -0.19f, 0.55f, 0.04f, 0.74f);
        pathBuilderA.curveToRelative(0.2f, 0.17f, 0.5f, 0.14f, 0.69f, -0.05f);
        pathBuilderA.lineToRelative(1.08f, -1.08f);
        pathBuilderA.curveTo(16.61f, 7.5f, 17.5f, 7.5f, 17.5f, 7.5f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _chalet = imageVectorBuild;
        return imageVectorBuild;
    }
}
