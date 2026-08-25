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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_egg", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Egg", "Landroidx/compose/material/icons/Icons$Sharp;", "getEgg", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EggKt {
    private static ImageVector _egg;

    public static final ImageVector getEgg(Icons.Sharp sharp) {
        ImageVector imageVector = _egg;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Egg", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 3.0f);
        pathBuilderA.curveTo(8.5f, 3.0f, 5.0f, 9.33f, 5.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 3.87f, 3.13f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -3.13f, 7.0f, -7.0f);
        pathBuilderA.curveTo(19.0f, 9.33f, 15.5f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 18.0f);
        pathBuilderA.curveToRelative(-3.0f, 0.0f, -5.0f, -1.99f, -5.0f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.0f, -1.0f, 0.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 2.92f, 2.42f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 2.0f);
        pathBuilderA.curveTo(14.0f, 18.0f, 13.55f, 18.0f, 13.0f, 18.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _egg = imageVectorBuild;
        return imageVectorBuild;
    }
}
