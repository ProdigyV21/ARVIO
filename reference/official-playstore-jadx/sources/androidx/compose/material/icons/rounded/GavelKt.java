package androidx.compose.material.icons.rounded;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gavel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Gavel", "Landroidx/compose/material/icons/Icons$Rounded;", "getGavel", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GavelKt {
    private static ImageVector _gavel;

    public static final ImageVector getGavel(Icons.Rounded rounded) {
        ImageVector imageVector = _gavel;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Gavel", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(2.0f, 21.0f, 10.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.lineTo(2.0f, 23.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        a.z(pathBuilderA, 5.24f, 8.07f, 2.83f, -2.83f);
        pathBuilderA.lineTo(20.8f, 17.97f);
        pathBuilderA.curveToRelative(0.78f, 0.78f, 0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderA.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        c.z(pathBuilderA, 5.24f, 8.07f, 13.73f, 2.41f);
        pathBuilderA.lineToRelative(2.83f, 2.83f);
        pathBuilderA.curveToRelative(0.78f, 0.78f, 0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.lineToRelative(-5.65f, -5.66f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.curveToRelative(0.78f, -0.79f, 2.05f, -0.79f, 2.83f, -0.01f);
        a.z(pathBuilderA, 3.83f, 9.48f, 5.66f, 5.66f);
        pathBuilderA.lineToRelative(-1.41f, 1.41f);
        pathBuilderA.curveToRelative(-0.78f, 0.78f, -2.05f, 0.78f, -2.83f, 0.0f);
        pathBuilderA.lineToRelative(-2.83f, -2.83f);
        pathBuilderA.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, 1.41f, -1.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gavel = imageVectorBuild;
        return imageVectorBuild;
    }
}
