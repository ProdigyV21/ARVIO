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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_batchPrediction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BatchPrediction", "Landroidx/compose/material/icons/Icons$Filled;", "getBatchPrediction", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BatchPredictionKt {
    private static ImageVector _batchPrediction;

    public static final ImageVector getBatchPrediction(Icons.Filled filled) {
        ImageVector imageVector = _batchPrediction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BatchPrediction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.0f, 8.0f, 7.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(10.0f);
        pathBuilderQ.curveTo(19.0f, 8.9f, 18.1f, 8.0f, 17.0f, 8.0f);
        a.u(pathBuilderQ, 13.0f, 20.5f, -2.0f, 19.0f);
        c.D(pathBuilderQ, 2.0f, 20.5f, 13.0f, 18.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.5f, -2.5f, -3.0f, -2.5f, -5.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderQ.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderQ.curveTo(15.5f, 15.0f, 13.0f, 16.5f, 13.0f, 18.0f);
        c.C(pathBuilderQ, 18.0f, 6.5f, 6.0f, 0.0f);
        pathBuilderQ.curveTo(6.0f, 5.67f, 6.67f, 5.0f, 7.5f, 5.0f);
        pathBuilderQ.horizontalLineToRelative(9.0f);
        pathBuilderQ.curveTo(17.33f, 5.0f, 18.0f, 5.67f, 18.0f, 6.5f);
        c.z(pathBuilderQ, 18.0f, 6.5f, 17.0f, 3.5f);
        pathBuilderQ.horizontalLineTo(7.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveTo(7.0f, 2.67f, 7.67f, 2.0f, 8.5f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(7.0f);
        pathBuilderQ.curveTo(16.33f, 2.0f, 17.0f, 2.67f, 17.0f, 3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderQ, 17.0f, 3.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _batchPrediction = imageVectorBuild;
        return imageVectorBuild;
    }
}
