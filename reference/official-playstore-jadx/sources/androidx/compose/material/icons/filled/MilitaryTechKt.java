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
import v.b;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_militaryTech", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MilitaryTech", "Landroidx/compose/material/icons/Icons$Filled;", "getMilitaryTech", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MilitaryTechKt {
    private static ImageVector _militaryTech;

    public static final ImageVector getMilitaryTech(Icons.Filled filled) {
        ImageVector imageVector = _militaryTech;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MilitaryTech", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.0f, 10.43f, 2.0f, 7.0f, 8.43f);
        pathBuilderJ.curveToRelative(0.0f, 0.35f, 0.18f, 0.68f, 0.49f, 0.86f);
        pathBuilderJ.lineToRelative(4.18f, 2.51f);
        pathBuilderJ.lineToRelative(-0.99f, 2.34f);
        pathBuilderJ.lineToRelative(-3.41f, 0.29f);
        pathBuilderJ.lineToRelative(2.59f, 2.24f);
        pathBuilderJ.lineTo(9.07f, 22.0f);
        pathBuilderJ.lineTo(12.0f, 20.23f);
        pathBuilderJ.lineTo(14.93f, 22.0f);
        pathBuilderJ.lineToRelative(-0.78f, -3.33f);
        pathBuilderJ.lineToRelative(2.59f, -2.24f);
        pathBuilderJ.lineToRelative(-3.41f, -0.29f);
        pathBuilderJ.lineToRelative(-0.99f, -2.34f);
        pathBuilderJ.lineToRelative(4.18f, -2.51f);
        pathBuilderJ.curveTo(16.82f, 11.11f, 17.0f, 10.79f, 17.0f, 10.43f);
        v.a.z(pathBuilderJ, 13.0f, 12.23f, -1.0f, 0.6f);
        b.o(pathBuilderJ, -1.0f, -0.6f, 3.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderJ, 12.23f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _militaryTech = imageVectorBuild;
        return imageVectorBuild;
    }
}
