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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_exploreOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExploreOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getExploreOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExploreOffKt {
    private static ImageVector _exploreOff;

    public static final ImageVector getExploreOff(Icons.Sharp sharp) {
        ImageVector imageVector = _exploreOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ExploreOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.0f, 6.0f, -2.91f, 6.26f);
        pathBuilderR.lineToRelative(5.25f, 5.25f);
        pathBuilderR.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderR.curveToRelative(-2.04f, 0.0f, -3.93f, 0.61f, -5.51f, 1.66f);
        d.C(pathBuilderR, 5.25f, 5.25f, 18.0f, 6.0f);
        pathBuilderR.moveTo(2.1f, 4.93f);
        pathBuilderR.lineToRelative(1.56f, 1.56f);
        pathBuilderR.curveTo(2.61f, 8.07f, 2.0f, 9.96f, 2.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderR.curveToRelative(2.04f, 0.0f, 3.93f, -0.61f, 5.51f, -1.66f);
        pathBuilderR.lineToRelative(1.56f, 1.56f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderR, 3.51f, 3.51f, 2.1f, 4.93f);
        pathBuilderR.moveTo(8.91f, 11.74f);
        pathBuilderR.lineToRelative(3.35f, 3.35f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderR, 6.0f, 18.0f, 2.91f, -6.26f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _exploreOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
