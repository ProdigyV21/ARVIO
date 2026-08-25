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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_precisionManufacturing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PrecisionManufacturing", "Landroidx/compose/material/icons/Icons$Sharp;", "getPrecisionManufacturing", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrecisionManufacturingKt {
    private static ImageVector _precisionManufacturing;

    public static final ImageVector getPrecisionManufacturing(Icons.Sharp sharp) {
        ImageVector imageVector = _precisionManufacturing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PrecisionManufacturing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = d.b(14.0f, 10.0f, 9.82f, 2.01f, 2.01f);
        pathBuilderB.lineToRelative(5.23f, -2.44f);
        pathBuilderB.lineToRelative(-0.63f, -1.36f);
        pathBuilderB.lineToRelative(-4.28f, 2.0f);
        pathBuilderB.lineTo(14.0f, 7.7f);
        pathBuilderB.verticalLineTo(6.3f);
        pathBuilderB.lineToRelative(2.33f, -2.33f);
        pathBuilderB.lineToRelative(4.28f, 2.0f);
        pathBuilderB.lineToRelative(0.63f, -1.36f);
        pathBuilderB.lineToRelative(-5.23f, -2.44f);
        b.n(pathBuilderB, 14.0f, 4.18f, 4.0f, -2.0f);
        pathBuilderB.verticalLineToRelative(2.0f);
        pathBuilderB.horizontalLineTo(8.82f);
        pathBuilderB.curveTo(8.4f, 4.84f, 7.3f, 4.0f, 6.0f, 4.0f);
        pathBuilderB.curveTo(4.34f, 4.0f, 3.0f, 5.34f, 3.0f, 7.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.6f, 2.05f, 1.48f, 2.58f);
        b.m(pathBuilderB, 7.08f, 18.0f, 4.0f, 3.0f);
        e.g(pathBuilderB, 13.0f, -3.0f, -3.62f);
        pathBuilderB.lineTo(8.41f, 8.76f);
        pathBuilderB.curveTo(8.58f, 8.53f, 8.72f, 8.28f, 8.82f, 8.0f);
        a0.b.e(pathBuilderB, 12.0f, 2.0f, 14.0f);
        pathBuilderB.moveTo(6.0f, 8.0f);
        pathBuilderB.curveTo(5.45f, 8.0f, 5.0f, 7.55f, 5.0f, 7.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderB.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderB.curveTo(7.0f, 7.55f, 6.55f, 8.0f, 6.0f, 8.0f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _precisionManufacturing = imageVectorBuild;
        return imageVectorBuild;
    }
}
