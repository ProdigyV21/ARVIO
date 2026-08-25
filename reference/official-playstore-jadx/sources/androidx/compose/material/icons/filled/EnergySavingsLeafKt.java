package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_energySavingsLeaf", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EnergySavingsLeaf", "Landroidx/compose/material/icons/Icons$Filled;", "getEnergySavingsLeaf", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnergySavingsLeafKt {
    private static ImageVector _energySavingsLeaf;

    public static final ImageVector getEnergySavingsLeaf(Icons.Filled filled) {
        ImageVector imageVector = _energySavingsLeaf;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.EnergySavingsLeaf", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 3.0f);
        pathBuilderA.curveToRelative(-4.8f, 0.0f, -9.0f, 3.86f, -9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 2.12f, 0.74f, 4.07f, 1.97f, 5.61f);
        pathBuilderA.lineTo(3.0f, 19.59f);
        pathBuilderA.lineTo(4.41f, 21.0f);
        pathBuilderA.lineToRelative(1.97f, -1.97f);
        pathBuilderA.curveTo(7.93f, 20.26f, 9.88f, 21.0f, 12.0f, 21.0f);
        pathBuilderA.curveToRelative(2.3f, 0.0f, 4.61f, -0.88f, 6.36f, -2.64f);
        pathBuilderA.curveTo(20.12f, 16.61f, 21.0f, 14.3f, 21.0f, 12.0f);
        d.C(pathBuilderA, 0.0f, -9.0f, 12.0f, 3.0f);
        pathBuilderA.moveTo(15.83f, 12.26f);
        pathBuilderA.lineToRelative(-5.16f, 4.63f);
        pathBuilderA.curveToRelative(-0.16f, 0.15f, -0.41f, 0.14f, -0.56f, -0.01f);
        pathBuilderA.curveToRelative(-0.14f, -0.14f, -0.16f, -0.36f, -0.04f, -0.52f);
        pathBuilderA.lineToRelative(2.44f, -3.33f);
        pathBuilderA.lineToRelative(-4.05f, -0.4f);
        pathBuilderA.curveToRelative(-0.44f, -0.04f, -0.63f, -0.59f, -0.3f, -0.89f);
        pathBuilderA.lineToRelative(5.16f, -4.63f);
        pathBuilderA.curveToRelative(0.16f, -0.15f, 0.41f, -0.14f, 0.56f, 0.01f);
        pathBuilderA.curveToRelative(0.14f, 0.14f, 0.16f, 0.36f, 0.04f, 0.52f);
        pathBuilderA.lineToRelative(-2.44f, 3.33f);
        pathBuilderA.lineToRelative(4.05f, 0.4f);
        pathBuilderA.curveTo(15.98f, 11.41f, 16.16f, 11.96f, 15.83f, 12.26f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _energySavingsLeaf = imageVectorBuild;
        return imageVectorBuild;
    }
}
