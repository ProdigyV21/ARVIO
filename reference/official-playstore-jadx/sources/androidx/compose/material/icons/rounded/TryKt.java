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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_try", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Try", "Landroidx/compose/material/icons/Icons$Rounded;", "getTry", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TryKt {
    private static ImageVector _try;

    public static final ImageVector getTry(Icons.Rounded rounded) {
        ImageVector imageVector = _try;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Try", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(15.59f);
        pathBuilderQ.curveToRelative(0.0f, 0.89f, 1.08f, 1.34f, 1.71f, 0.71f);
        pathBuilderQ.lineTo(6.0f, 18.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        a.z(pathBuilderQ, 13.57f, 11.57f, -1.12f, 2.44f);
        pathBuilderQ.curveToRelative(-0.18f, 0.39f, -0.73f, 0.39f, -0.91f, 0.0f);
        pathBuilderQ.lineToRelative(-1.12f, -2.44f);
        pathBuilderQ.lineToRelative(-2.44f, -1.12f);
        pathBuilderQ.curveToRelative(-0.39f, -0.18f, -0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderQ.lineToRelative(2.44f, -1.12f);
        pathBuilderQ.lineToRelative(1.12f, -2.44f);
        pathBuilderQ.curveToRelative(0.18f, -0.39f, 0.73f, -0.39f, 0.91f, 0.0f);
        pathBuilderQ.lineToRelative(1.12f, 2.44f);
        pathBuilderQ.lineToRelative(2.44f, 1.12f);
        pathBuilderQ.curveToRelative(0.39f, 0.18f, 0.39f, 0.73f, 0.0f, 0.91f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderQ, 13.57f, 11.57f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _try = imageVectorBuild;
        return imageVectorBuild;
    }
}
