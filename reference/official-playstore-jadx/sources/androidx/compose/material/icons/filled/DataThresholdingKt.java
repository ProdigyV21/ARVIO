package androidx.compose.material.icons.filled;

import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dataThresholding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DataThresholding", "Landroidx/compose/material/icons/Icons$Filled;", "getDataThresholding", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DataThresholdingKt {
    private static ImageVector _dataThresholding;

    public static final ImageVector getDataThresholding(Icons.Filled filled) {
        ImageVector imageVector = _dataThresholding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DataThresholding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        a.z(pathBuilderQ, 10.67f, 8.17f, 2.0f, 2.0f);
        pathBuilderQ.lineToRelative(3.67f, -3.67f);
        pathBuilderQ.lineToRelative(1.41f, 1.41f);
        pathBuilderQ.lineTo(12.67f, 13.0f);
        pathBuilderQ.lineToRelative(-2.0f, -2.0f);
        pathBuilderQ.lineToRelative(-3.0f, 3.0f);
        d.C(pathBuilderQ, -1.41f, -1.41f, 10.67f, 8.17f);
        pathBuilderQ.moveTo(5.0f, 16.0f);
        pathBuilderQ.horizontalLineToRelative(1.72f);
        pathBuilderQ.lineTo(5.0f, 17.72f);
        pathBuilderQ.verticalLineTo(16.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(5.84f, 19.0f);
        pathBuilderQ.lineToRelative(3.0f, -3.0f);
        b.B(pathBuilderQ, 1.83f, -3.0f, 3.0f, 5.84f);
        pathBuilderQ.moveTo(9.8f, 19.0f);
        pathBuilderQ.lineToRelative(3.0f, -3.0f);
        b.B(pathBuilderQ, 1.62f, -3.0f, 3.0f, 9.8f);
        pathBuilderQ.moveTo(13.53f, 19.0f);
        pathBuilderQ.lineToRelative(3.0f, -3.0f);
        b.B(pathBuilderQ, 1.62f, -3.0f, 3.0f, 13.53f);
        pathBuilderQ.moveTo(19.0f, 19.0f);
        pathBuilderQ.horizontalLineToRelative(-1.73f);
        pathBuilderQ.lineTo(19.0f, 17.27f);
        pathBuilderQ.verticalLineTo(19.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dataThresholding = imageVectorBuild;
        return imageVectorBuild;
    }
}
