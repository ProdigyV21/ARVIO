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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_eggAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EggAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getEggAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EggAltKt {
    private static ImageVector _eggAlt;

    public static final ImageVector getEggAlt(Icons.Filled filled) {
        ImageVector imageVector = _eggAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.EggAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.0f, 9.0f);
        pathBuilderA.curveTo(17.0f, 7.0f, 15.99f, 2.0f, 9.97f, 2.0f);
        pathBuilderA.curveTo(4.95f, 2.0f, 1.94f, 6.0f, 2.0f, 11.52f);
        pathBuilderA.curveTo(2.06f, 17.04f, 6.96f, 19.0f, 9.97f, 19.0f);
        pathBuilderA.curveToRelative(2.01f, 0.0f, 2.01f, 3.0f, 6.02f, 3.0f);
        pathBuilderA.curveTo(19.0f, 22.0f, 22.0f, 19.0f, 22.0f, 15.02f);
        pathBuilderA.curveTo(22.0f, 12.0f, 21.01f, 11.0f, 19.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 15.5f);
        pathBuilderA.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.93f, 15.5f, 12.0f, 15.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _eggAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
