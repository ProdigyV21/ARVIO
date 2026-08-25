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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_beenhere", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Beenhere", "Landroidx/compose/material/icons/Icons$Rounded;", "getBeenhere", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BeenhereKt {
    private static ImageVector _beenhere;

    public static final ImageVector getBeenhere(Icons.Rounded rounded) {
        ImageVector imageVector = _beenhere;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Beenhere", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 1.0f, 5.0f, 1.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.lineTo(3.0f, 15.93f);
        pathBuilderJ.curveToRelative(0.0f, 0.69f, 0.35f, 1.3f, 0.88f, 1.66f);
        pathBuilderJ.lineToRelative(7.57f, 5.04f);
        pathBuilderJ.curveToRelative(0.34f, 0.22f, 0.77f, 0.22f, 1.11f, 0.0f);
        pathBuilderJ.lineToRelative(7.56f, -5.04f);
        pathBuilderJ.curveToRelative(0.53f, -0.36f, 0.88f, -0.97f, 0.88f, -1.66f);
        pathBuilderJ.lineTo(21.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.z(pathBuilderJ, 18.3f, 7.7f, -7.59f, 7.59f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(5.71f, 11.7f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.reflectiveCurveToRelative(1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineTo(10.0f, 13.17f);
        pathBuilderJ.lineToRelative(6.88f, -6.88f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, 0.4f, 1.02f, 0.01f, 1.41f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _beenhere = imageVectorBuild;
        return imageVectorBuild;
    }
}
