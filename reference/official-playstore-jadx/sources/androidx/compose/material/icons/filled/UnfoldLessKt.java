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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_unfoldLess", "Landroidx/compose/ui/graphics/vector/ImageVector;", "UnfoldLess", "Landroidx/compose/material/icons/Icons$Filled;", "getUnfoldLess", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnfoldLessKt {
    private static ImageVector _unfoldLess;

    public static final ImageVector getUnfoldLess(Icons.Filled filled) {
        ImageVector imageVector = _unfoldLess;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.UnfoldLess", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.41f, 18.59f, 8.83f, 20.0f);
        pathBuilderJ.lineTo(12.0f, 16.83f);
        pathBuilderJ.lineTo(15.17f, 20.0f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        d.l(pathBuilderJ, 12.0f, 14.0f, -4.59f, 4.59f);
        pathBuilderJ.moveTo(16.59f, 5.41f);
        pathBuilderJ.lineTo(15.17f, 4.0f);
        pathBuilderJ.lineTo(12.0f, 7.17f);
        pathBuilderJ.lineTo(8.83f, 4.0f);
        pathBuilderJ.lineTo(7.41f, 5.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 12.0f, 10.0f, 4.59f, -4.59f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _unfoldLess = imageVectorBuild;
        return imageVectorBuild;
    }
}
