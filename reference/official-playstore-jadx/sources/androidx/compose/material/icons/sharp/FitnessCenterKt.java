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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fitnessCenter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FitnessCenter", "Landroidx/compose/material/icons/Icons$Sharp;", "getFitnessCenter", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FitnessCenterKt {
    private static ImageVector _fitnessCenter;

    public static final ImageVector getFitnessCenter(Icons.Sharp sharp) {
        ImageVector imageVector = _fitnessCenter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.FitnessCenter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.57f, 14.86f, 22.0f, 13.43f);
        pathBuilderJ.lineTo(20.57f, 12.0f);
        pathBuilderJ.lineTo(17.0f, 15.57f);
        pathBuilderJ.lineTo(8.43f, 7.0f);
        pathBuilderJ.lineTo(12.0f, 3.43f);
        pathBuilderJ.lineTo(10.57f, 2.0f);
        pathBuilderJ.lineTo(9.14f, 3.43f);
        pathBuilderJ.lineTo(7.71f, 2.0f);
        pathBuilderJ.lineTo(5.57f, 4.14f);
        pathBuilderJ.lineTo(4.14f, 2.71f);
        pathBuilderJ.lineTo(2.71f, 4.14f);
        pathBuilderJ.lineToRelative(1.43f, 1.43f);
        pathBuilderJ.lineTo(2.0f, 7.71f);
        pathBuilderJ.lineToRelative(1.43f, 1.43f);
        pathBuilderJ.lineTo(2.0f, 10.57f);
        pathBuilderJ.lineTo(3.43f, 12.0f);
        pathBuilderJ.lineTo(7.0f, 8.43f);
        pathBuilderJ.lineTo(15.57f, 17.0f);
        pathBuilderJ.lineTo(12.0f, 20.57f);
        pathBuilderJ.lineTo(13.43f, 22.0f);
        pathBuilderJ.lineToRelative(1.43f, -1.43f);
        pathBuilderJ.lineTo(16.29f, 22.0f);
        pathBuilderJ.lineToRelative(2.14f, -2.14f);
        pathBuilderJ.lineToRelative(1.43f, 1.43f);
        pathBuilderJ.lineToRelative(1.43f, -1.43f);
        pathBuilderJ.lineToRelative(-1.43f, -1.43f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 22.0f, 16.29f, -1.43f, -1.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fitnessCenter = imageVectorBuild;
        return imageVectorBuild;
    }
}
