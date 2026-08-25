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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_electricBolt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ElectricBolt", "Landroidx/compose/material/icons/Icons$Rounded;", "getElectricBolt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ElectricBoltKt {
    private static ImageVector _electricBolt;

    public static final ImageVector getElectricBolt(Icons.Rounded rounded) {
        ImageVector imageVector = _electricBolt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ElectricBolt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.69f, 2.21f, 4.33f, 11.49f);
        pathBuilderJ.curveToRelative(-0.64f, 0.58f, -0.28f, 1.65f, 0.58f, 1.73f);
        pathBuilderJ.lineTo(13.0f, 14.0f);
        pathBuilderJ.lineToRelative(-4.85f, 6.76f);
        pathBuilderJ.curveToRelative(-0.22f, 0.31f, -0.19f, 0.74f, 0.08f, 1.01f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.3f, 0.3f, 0.77f, 0.31f, 1.08f, 0.02f);
        pathBuilderJ.lineToRelative(10.36f, -9.28f);
        pathBuilderJ.curveToRelative(0.64f, -0.58f, 0.28f, -1.65f, -0.58f, -1.73f);
        pathBuilderJ.lineTo(11.0f, 10.0f);
        pathBuilderJ.lineToRelative(4.85f, -6.76f);
        pathBuilderJ.curveToRelative(0.22f, -0.31f, 0.19f, -0.74f, -0.08f, -1.01f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveTo(15.47f, 1.93f, 15.0f, 1.92f, 14.69f, 2.21f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _electricBolt = imageVectorBuild;
        return imageVectorBuild;
    }
}
