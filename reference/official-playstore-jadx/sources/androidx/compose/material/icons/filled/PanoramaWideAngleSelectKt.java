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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaWideAngleSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaWideAngleSelect", "Landroidx/compose/material/icons/Icons$Filled;", "getPanoramaWideAngleSelect", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaWideAngleSelectKt {
    private static ImageVector _panoramaWideAngleSelect;

    public static final ImageVector getPanoramaWideAngleSelect(Icons.Filled filled) {
        ImageVector imageVector = _panoramaWideAngleSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PanoramaWideAngleSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-2.73f, 0.0f, -5.22f, 0.24f, -7.95f, 0.72f);
        pathBuilderA.lineToRelative(-0.93f, 0.16f);
        pathBuilderA.lineToRelative(-0.25f, 0.9f);
        pathBuilderA.curveTo(2.29f, 7.85f, 2.0f, 9.93f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.29f, 4.15f, 0.87f, 6.22f);
        pathBuilderA.lineToRelative(0.25f, 0.89f);
        pathBuilderA.lineToRelative(0.93f, 0.16f);
        pathBuilderA.curveToRelative(2.73f, 0.49f, 5.22f, 0.73f, 7.95f, 0.73f);
        pathBuilderA.reflectiveCurveToRelative(5.22f, -0.24f, 7.95f, -0.72f);
        pathBuilderA.lineToRelative(0.93f, -0.16f);
        pathBuilderA.lineToRelative(0.25f, -0.89f);
        pathBuilderA.curveToRelative(0.58f, -2.08f, 0.87f, -4.16f, 0.87f, -6.23f);
        pathBuilderA.reflectiveCurveToRelative(-0.29f, -4.15f, -0.87f, -6.22f);
        pathBuilderA.lineToRelative(-0.25f, -0.89f);
        pathBuilderA.lineToRelative(-0.93f, -0.16f);
        pathBuilderA.curveTo(17.22f, 4.24f, 14.73f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaWideAngleSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}
