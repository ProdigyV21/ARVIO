package androidx.compose.material.icons.filled;

import a0.b;
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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_riceBowl", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RiceBowl", "Landroidx/compose/material/icons/Icons$Filled;", "getRiceBowl", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RiceBowlKt {
    private static ImageVector _riceBowl;

    public static final ImageVector getRiceBowl(Icons.Filled filled) {
        ImageVector imageVector = _riceBowl;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RiceBowl", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 12.0f, 22.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderJ.reflectiveCurveTo(2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.69f, 2.47f, 6.86f, 6.0f, 8.25f);
        w.a.j(pathBuilderJ, 22.0f, 8.0f, -1.75f);
        pathBuilderJ.curveTo(19.53f, 18.86f, 22.0f, 15.69f, 22.0f, 12.0f);
        a.u(pathBuilderJ, 20.0f, 12.0f, -4.0f, 5.08f);
        pathBuilderJ.curveTo(18.39f, 6.47f, 20.0f, 9.05f, 20.0f, 12.0f);
        b.t(pathBuilderJ, 14.0f, 4.26f, 12.0f, -4.0f);
        pathBuilderJ.verticalLineTo(4.26f);
        pathBuilderJ.curveTo(10.64f, 4.1f, 11.31f, 4.0f, 12.0f, 4.0f);
        pathBuilderJ.reflectiveCurveTo(13.36f, 4.1f, 14.0f, 4.26f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(4.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.95f, 1.61f, -5.53f, 4.0f, -6.92f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderJ, 12.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _riceBowl = imageVectorBuild;
        return imageVectorBuild;
    }
}
