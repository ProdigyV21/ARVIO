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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_functions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Functions", "Landroidx/compose/material/icons/Icons$Rounded;", "getFunctions", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FunctionsKt {
    private static ImageVector _functions;

    public static final ImageVector getFunctions(Icons.Rounded rounded) {
        ImageVector imageVector = _functions;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Functions", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(16.5f, 4.0f, 7.56f);
        pathBuilderQ.curveTo(6.7f, 4.0f, 6.0f, 4.7f, 6.0f, 5.56f);
        pathBuilderQ.curveToRelative(0.0f, 0.28f, 0.12f, 0.55f, 0.32f, 0.74f);
        pathBuilderQ.lineTo(12.5f, 12.0f);
        pathBuilderQ.lineToRelative(-6.18f, 5.7f);
        pathBuilderQ.curveToRelative(-0.2f, 0.19f, -0.32f, 0.46f, -0.32f, 0.74f);
        pathBuilderQ.curveTo(6.0f, 19.3f, 6.7f, 20.0f, 7.56f, 20.0f);
        pathBuilderQ.horizontalLineToRelative(8.94f);
        pathBuilderQ.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderQ.reflectiveCurveToRelative(-0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderQ.horizontalLineTo(11.0f);
        pathBuilderQ.lineToRelative(3.59f, -3.59f);
        pathBuilderQ.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderQ.lineTo(11.0f, 7.0f);
        pathBuilderQ.horizontalLineToRelative(5.5f);
        pathBuilderQ.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderQ, 17.33f, 4.0f, 16.5f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _functions = imageVectorBuild;
        return imageVectorBuild;
    }
}
