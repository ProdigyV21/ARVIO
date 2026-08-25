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
import v.b;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dataThresholding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DataThresholding", "Landroidx/compose/material/icons/Icons$Sharp;", "getDataThresholding", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DataThresholdingKt {
    private static ImageVector _dataThresholding;

    public static final ImageVector getDataThresholding(Icons.Sharp sharp) {
        ImageVector imageVector = _dataThresholding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DataThresholding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(21.0f, 3.0f, 3.0f, 18.0f, 18.0f);
        f.r(pathBuilderH, 3.0f, 10.67f, 8.17f);
        pathBuilderH.lineToRelative(2.0f, 2.0f);
        pathBuilderH.lineToRelative(3.67f, -3.67f);
        pathBuilderH.lineToRelative(1.41f, 1.41f);
        pathBuilderH.lineTo(12.67f, 13.0f);
        pathBuilderH.lineToRelative(-2.0f, -2.0f);
        pathBuilderH.lineToRelative(-3.0f, 3.0f);
        d.C(pathBuilderH, -1.41f, -1.41f, 10.67f, 8.17f);
        pathBuilderH.moveTo(5.0f, 16.0f);
        pathBuilderH.horizontalLineToRelative(1.72f);
        pathBuilderH.lineTo(5.0f, 17.72f);
        pathBuilderH.verticalLineTo(16.0f);
        pathBuilderH.close();
        pathBuilderH.moveTo(5.84f, 19.0f);
        pathBuilderH.lineToRelative(3.0f, -3.0f);
        a0.b.B(pathBuilderH, 1.83f, -3.0f, 3.0f, 5.84f);
        pathBuilderH.moveTo(9.8f, 19.0f);
        pathBuilderH.lineToRelative(3.0f, -3.0f);
        a0.b.B(pathBuilderH, 1.62f, -3.0f, 3.0f, 9.8f);
        pathBuilderH.moveTo(13.53f, 19.0f);
        pathBuilderH.lineToRelative(3.0f, -3.0f);
        a0.b.B(pathBuilderH, 1.62f, -3.0f, 3.0f, 13.53f);
        pathBuilderH.moveTo(19.0f, 19.0f);
        pathBuilderH.horizontalLineToRelative(-1.73f);
        pathBuilderH.lineTo(19.0f, 17.27f);
        pathBuilderH.verticalLineTo(19.0f);
        pathBuilderH.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderH.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dataThresholding = imageVectorBuild;
        return imageVectorBuild;
    }
}
