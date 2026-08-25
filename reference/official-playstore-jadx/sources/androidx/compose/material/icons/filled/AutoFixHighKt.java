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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoFixHigh", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoFixHigh", "Landroidx/compose/material/icons/Icons$Filled;", "getAutoFixHigh", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoFixHighKt {
    private static ImageVector _autoFixHigh;

    public static final ImageVector getAutoFixHigh(Icons.Filled filled) {
        ImageVector imageVector = _autoFixHigh;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AutoFixHigh", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.5f, 5.6f, 10.0f, 7.0f);
        pathBuilderJ.lineTo(8.6f, 4.5f);
        pathBuilderJ.lineTo(10.0f, 2.0f);
        pathBuilderJ.lineTo(7.5f, 3.4f);
        pathBuilderJ.lineTo(5.0f, 2.0f);
        d.C(pathBuilderJ, 1.4f, 2.5f, 5.0f, 7.0f);
        pathBuilderJ.moveTo(19.5f, 15.4f);
        pathBuilderJ.lineTo(17.0f, 14.0f);
        pathBuilderJ.lineToRelative(1.4f, 2.5f);
        pathBuilderJ.lineTo(17.0f, 19.0f);
        pathBuilderJ.lineToRelative(2.5f, -1.4f);
        pathBuilderJ.lineTo(22.0f, 19.0f);
        d.C(pathBuilderJ, -1.4f, -2.5f, 22.0f, 14.0f);
        pathBuilderJ.moveTo(22.0f, 2.0f);
        pathBuilderJ.lineToRelative(-2.5f, 1.4f);
        pathBuilderJ.lineTo(17.0f, 2.0f);
        pathBuilderJ.lineToRelative(1.4f, 2.5f);
        pathBuilderJ.lineTo(17.0f, 7.0f);
        pathBuilderJ.lineToRelative(2.5f, -1.4f);
        d.l(pathBuilderJ, 22.0f, 7.0f, -1.4f, -2.5f);
        pathBuilderJ.moveTo(14.37f, 7.29f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(1.29f, 18.96f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(2.34f, 2.34f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(16.7f, 11.05f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        b.C(pathBuilderJ, -2.33f, -2.35f, 13.34f, 12.78f);
        pathBuilderJ.lineToRelative(-2.12f, -2.12f);
        pathBuilderJ.lineToRelative(2.44f, -2.44f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 2.12f, 2.12f, -2.44f, 2.44f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _autoFixHigh = imageVectorBuild;
        return imageVectorBuild;
    }
}
