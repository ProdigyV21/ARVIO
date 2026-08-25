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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fastRewind", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FastRewind", "Landroidx/compose/material/icons/Icons$Rounded;", "getFastRewind", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FastRewindKt {
    private static ImageVector _fastRewind;

    public static final ImageVector getFastRewind(Icons.Rounded rounded) {
        ImageVector imageVector = _fastRewind;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FastRewind", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(11.0f, 16.07f, 11.0f, 7.93f);
        pathBuilderJ.curveToRelative(0.0f, -0.81f, -0.91f, -1.28f, -1.58f, -0.82f);
        pathBuilderJ.lineToRelative(-5.77f, 4.07f);
        pathBuilderJ.curveToRelative(-0.56f, 0.4f, -0.56f, 1.24f, 0.0f, 1.63f);
        pathBuilderJ.lineToRelative(5.77f, 4.07f);
        pathBuilderJ.curveToRelative(0.67f, 0.47f, 1.58f, 0.0f, 1.58f, -0.81f);
        a.z(pathBuilderJ, 12.66f, 12.82f, 5.77f, 4.07f);
        pathBuilderJ.curveToRelative(0.66f, 0.47f, 1.58f, -0.01f, 1.58f, -0.82f);
        pathBuilderJ.lineTo(20.01f, 7.93f);
        pathBuilderJ.curveToRelative(0.0f, -0.81f, -0.91f, -1.28f, -1.58f, -0.82f);
        pathBuilderJ.lineToRelative(-5.77f, 4.07f);
        pathBuilderJ.curveToRelative(-0.57f, 0.4f, -0.57f, 1.24f, 0.0f, 1.64f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fastRewind = imageVectorBuild;
        return imageVectorBuild;
    }
}
