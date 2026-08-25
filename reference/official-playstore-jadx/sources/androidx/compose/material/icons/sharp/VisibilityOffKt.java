package androidx.compose.material.icons.sharp;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_visibilityOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VisibilityOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getVisibilityOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VisibilityOffKt {
    private static ImageVector _visibilityOff;

    public static final ImageVector getVisibilityOff(Icons.Sharp sharp) {
        ImageVector imageVector = _visibilityOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VisibilityOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.5f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 0.51f, -0.1f, 1.0f, -0.24f, 1.46f);
        pathBuilderA.lineToRelative(3.06f, 3.06f);
        pathBuilderA.curveToRelative(1.39f, -1.23f, 2.49f, -2.77f, 3.18f, -4.53f);
        pathBuilderA.curveTo(21.27f, 7.11f, 17.0f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.27f, 0.0f, -2.49f, 0.2f, -3.64f, 0.57f);
        pathBuilderA.lineToRelative(2.17f, 2.17f);
        pathBuilderA.curveToRelative(0.47f, -0.14f, 0.96f, -0.24f, 1.47f, -0.24f);
        a.p(pathBuilderA, 3.42f, 2.45f, 2.01f, 3.87f);
        pathBuilderA.lineToRelative(2.68f, 2.68f);
        pathBuilderA.curveTo(3.06f, 7.83f, 1.77f, 9.53f, 1.0f, 11.5f);
        pathBuilderA.curveTo(2.73f, 15.89f, 7.0f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.curveToRelative(1.52f, 0.0f, 2.97f, -0.3f, 4.31f, -0.82f);
        pathBuilderA.lineToRelative(3.43f, 3.43f);
        d.C(pathBuilderA, 1.41f, -1.41f, 3.42f, 2.45f);
        pathBuilderA.moveTo(12.0f, 16.5f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.77f, 0.18f, -1.5f, 0.49f, -2.14f);
        pathBuilderA.lineToRelative(1.57f, 1.57f);
        pathBuilderA.curveToRelative(-0.03f, 0.18f, -0.06f, 0.37f, -0.06f, 0.57f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.2f, 0.0f, 0.38f, -0.03f, 0.57f, -0.07f);
        pathBuilderA.lineTo(14.14f, 16.0f);
        pathBuilderA.curveToRelative(-0.65f, 0.32f, -1.37f, 0.5f, -2.14f, 0.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.97f, 11.17f);
        pathBuilderA.curveToRelative(-0.15f, -1.4f, -1.25f, -2.49f, -2.64f, -2.64f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, 2.64f, 2.64f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _visibilityOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
