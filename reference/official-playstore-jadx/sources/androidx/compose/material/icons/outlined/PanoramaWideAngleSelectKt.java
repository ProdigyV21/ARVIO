package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_panoramaWideAngleSelect", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PanoramaWideAngleSelect", "Landroidx/compose/material/icons/Icons$Outlined;", "getPanoramaWideAngleSelect", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PanoramaWideAngleSelectKt {
    private static ImageVector _panoramaWideAngleSelect;

    public static final ImageVector getPanoramaWideAngleSelect(Icons.Outlined outlined) {
        ImageVector imageVector = _panoramaWideAngleSelect;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PanoramaWideAngleSelect", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveTo(8.03f, 4.0f, 5.15f, 4.63f, 3.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.55f, 1.97f, -1.0f, 3.92f, -1.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 3.03f, 0.45f, 5.05f, 1.0f, 7.0f);
        pathBuilderA.curveToRelative(2.15f, 0.37f, 4.98f, 1.0f, 9.0f, 1.0f);
        pathBuilderA.curveToRelative(3.97f, 0.0f, 6.85f, -0.63f, 9.0f, -1.0f);
        pathBuilderA.curveToRelative(0.57f, -2.02f, 1.0f, -3.99f, 1.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -3.03f, -0.45f, -5.05f, -1.0f, -7.0f);
        pathBuilderA.curveTo(18.85f, 4.63f, 16.02f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _panoramaWideAngleSelect = imageVectorBuild;
        return imageVectorBuild;
    }
}
