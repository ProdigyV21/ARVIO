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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dangerous", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dangerous", "Landroidx/compose/material/icons/Icons$Rounded;", "getDangerous", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DangerousKt {
    private static ImageVector _dangerous;

    public static final ImageVector getDangerous(Icons.Rounded rounded) {
        ImageVector imageVector = _dangerous;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Dangerous", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(14.9f, 3.0f, 9.1f);
        pathBuilderQ.curveTo(8.57f, 3.0f, 8.06f, 3.21f, 7.68f, 3.59f);
        pathBuilderQ.lineToRelative(-4.1f, 4.1f);
        pathBuilderQ.curveTo(3.21f, 8.06f, 3.0f, 8.57f, 3.0f, 9.1f);
        pathBuilderQ.verticalLineToRelative(5.8f);
        pathBuilderQ.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.59f, 1.41f);
        pathBuilderQ.lineToRelative(4.1f, 4.1f);
        pathBuilderQ.curveTo(8.06f, 20.79f, 8.57f, 21.0f, 9.1f, 21.0f);
        pathBuilderQ.horizontalLineToRelative(5.8f);
        pathBuilderQ.curveToRelative(0.53f, 0.0f, 1.04f, -0.21f, 1.41f, -0.59f);
        pathBuilderQ.lineToRelative(4.1f, -4.1f);
        pathBuilderQ.curveTo(20.79f, 15.94f, 21.0f, 15.43f, 21.0f, 14.9f);
        pathBuilderQ.verticalLineTo(9.1f);
        pathBuilderQ.curveToRelative(0.0f, -0.53f, -0.21f, -1.04f, -0.59f, -1.41f);
        pathBuilderQ.lineToRelative(-4.1f, -4.1f);
        pathBuilderQ.curveTo(15.94f, 3.21f, 15.43f, 3.0f, 14.9f, 3.0f);
        a.p(pathBuilderQ, 15.54f, 15.54f, 15.54f, 15.54f);
        pathBuilderQ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderQ.lineTo(12.0f, 13.41f);
        pathBuilderQ.lineToRelative(-2.12f, 2.12f);
        pathBuilderQ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderQ.lineTo(10.59f, 12.0f);
        pathBuilderQ.lineTo(8.46f, 9.88f);
        pathBuilderQ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderQ.lineTo(12.0f, 10.59f);
        pathBuilderQ.lineToRelative(2.12f, -2.12f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderQ.lineTo(13.41f, 12.0f);
        pathBuilderQ.lineToRelative(2.12f, 2.12f);
        pathBuilderQ.curveTo(15.93f, 14.51f, 15.93f, 15.15f, 15.54f, 15.54f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dangerous = imageVectorBuild;
        return imageVectorBuild;
    }
}
