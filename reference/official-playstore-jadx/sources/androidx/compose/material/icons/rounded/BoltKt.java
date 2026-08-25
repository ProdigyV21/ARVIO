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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bolt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bolt", "Landroidx/compose/material/icons/Icons$Rounded;", "getBolt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BoltKt {
    private static ImageVector _bolt;

    public static final ImageVector getBolt(Icons.Rounded rounded) {
        ImageVector imageVector = _bolt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Bolt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.67f, 21.0f, 10.67f, 21.0f);
        pathBuilderJ.curveToRelative(-0.35f, 0.0f, -0.62f, -0.31f, -0.57f, -0.66f);
        pathBuilderJ.lineTo(11.0f, 14.0f);
        pathBuilderJ.horizontalLineTo(7.5f);
        pathBuilderJ.curveToRelative(-0.88f, 0.0f, -0.33f, -0.75f, -0.31f, -0.78f);
        pathBuilderJ.curveToRelative(1.26f, -2.23f, 3.15f, -5.53f, 5.65f, -9.93f);
        pathBuilderJ.curveToRelative(0.1f, -0.18f, 0.3f, -0.29f, 0.5f, -0.29f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.35f, 0.0f, 0.62f, 0.31f, 0.57f, 0.66f);
        pathBuilderJ.lineTo(13.01f, 10.0f);
        pathBuilderJ.horizontalLineToRelative(3.51f);
        pathBuilderJ.curveToRelative(0.4f, 0.0f, 0.62f, 0.19f, 0.4f, 0.66f);
        pathBuilderJ.curveToRelative(-3.29f, 5.74f, -5.2f, 9.09f, -5.75f, 10.05f);
        pathBuilderJ.curveTo(11.07f, 20.89f, 10.88f, 21.0f, 10.67f, 21.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bolt = imageVectorBuild;
        return imageVectorBuild;
    }
}
