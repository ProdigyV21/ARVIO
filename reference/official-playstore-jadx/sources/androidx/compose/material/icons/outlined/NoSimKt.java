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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noSim", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoSim", "Landroidx/compose/material/icons/Icons$Outlined;", "getNoSim", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoSimKt {
    private static ImageVector _noSim;

    public static final ImageVector getNoSim(Icons.Outlined outlined) {
        ImageVector imageVector = _noSim;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NoSim", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.26f, 21.21f, 3.79f, 3.74f);
        pathBuilderJ.lineTo(2.38f, 5.15f);
        pathBuilderJ.lineToRelative(2.74f, 2.74f);
        pathBuilderJ.lineToRelative(-0.12f, 0.12f);
        pathBuilderJ.lineTo(5.0f, 19.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(10.0f);
        pathBuilderJ.curveToRelative(0.35f, 0.0f, 0.68f, -0.1f, 0.97f, -0.26f);
        b.D(pathBuilderJ, 1.88f, 1.88f, 1.41f, -1.41f);
        pathBuilderJ.moveTo(7.0f, 19.0f);
        pathBuilderJ.lineTo(7.0f, 9.77f);
        b.y(pathBuilderJ, 16.23f, 19.0f, 7.0f, 19.0f);
        pathBuilderJ.moveTo(10.84f, 5.0f);
        pathBuilderJ.lineTo(17.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(9.11f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        pathBuilderJ.lineTo(19.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(-6.99f);
        pathBuilderJ.lineTo(7.95f, 5.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 1.41f, 1.41f, 10.84f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noSim = imageVectorBuild;
        return imageVectorBuild;
    }
}
