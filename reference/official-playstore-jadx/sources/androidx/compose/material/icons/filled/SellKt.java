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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sell", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sell", "Landroidx/compose/material/icons/Icons$Filled;", "getSell", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SellKt {
    private static ImageVector _sell;

    public static final ImageVector getSell(Icons.Filled filled) {
        ImageVector imageVector = _sell;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Sell", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.41f, 11.41f, -8.83f, -8.83f);
        pathBuilderR.curveTo(12.21f, 2.21f, 11.7f, 2.0f, 11.17f, 2.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderR.verticalLineToRelative(7.17f);
        pathBuilderR.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.59f, 1.41f);
        pathBuilderR.lineToRelative(8.83f, 8.83f);
        pathBuilderR.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderR.lineToRelative(7.17f, -7.17f);
        pathBuilderR.curveTo(22.2f, 13.46f, 22.2f, 12.2f, 21.41f, 11.41f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.5f, 8.0f);
        pathBuilderR.curveTo(5.67f, 8.0f, 5.0f, 7.33f, 5.0f, 6.5f);
        pathBuilderR.reflectiveCurveTo(5.67f, 5.0f, 6.5f, 5.0f);
        pathBuilderR.reflectiveCurveTo(8.0f, 5.67f, 8.0f, 6.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 7.33f, 8.0f, 6.5f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sell = imageVectorBuild;
        return imageVectorBuild;
    }
}
