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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_powerOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PowerOff", "Landroidx/compose/material/icons/Icons$Filled;", "getPowerOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PowerOffKt {
    private static ImageVector _powerOff;

    public static final ImageVector getPowerOff(Icons.Filled filled) {
        ImageVector imageVector = _powerOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PowerOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.0f, 14.49f, 18.0f, 9.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.0f, -1.01f, -2.01f, -2.0f, -2.0f);
        b.A(pathBuilderJ, 16.0f, 3.0f, -2.0f, 4.0f);
        pathBuilderJ.horizontalLineToRelative(-4.0f);
        pathBuilderJ.lineTo(10.0f, 3.0f);
        pathBuilderJ.lineTo(8.0f, 3.0f);
        pathBuilderJ.verticalLineToRelative(2.48f);
        b.D(pathBuilderJ, 9.51f, 9.5f, 0.49f, -0.49f);
        pathBuilderJ.moveTo(16.24f, 16.26f);
        pathBuilderJ.lineTo(7.2f, 7.2f);
        pathBuilderJ.lineToRelative(-0.01f, 0.01f);
        pathBuilderJ.lineTo(3.98f, 4.0f);
        pathBuilderJ.lineTo(2.71f, 5.25f);
        pathBuilderJ.lineToRelative(3.36f, 3.36f);
        pathBuilderJ.curveTo(6.04f, 8.74f, 6.0f, 8.87f, 6.0f, 9.0f);
        d.r(pathBuilderJ, 5.48f, 9.5f, 18.0f, 3.0f);
        f.y(pathBuilderJ, 5.0f, -3.0f, 0.48f, -0.48f);
        pathBuilderJ.lineTo(19.45f, 22.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 1.26f, -1.28f, -4.47f, -4.46f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _powerOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
