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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_exploreOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExploreOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getExploreOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExploreOffKt {
    private static ImageVector _exploreOff;

    public static final ImageVector getExploreOff(Icons.Outlined outlined) {
        ImageVector imageVector = _exploreOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ExploreOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.48f, -0.41f, 2.86f, -1.12f, 4.06f);
        pathBuilderA.lineToRelative(1.46f, 1.46f);
        pathBuilderA.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.curveToRelative(-2.04f, 0.0f, -3.93f, 0.61f, -5.51f, 1.66f);
        pathBuilderA.lineToRelative(1.46f, 1.46f);
        pathBuilderA.curveTo(9.14f, 4.41f, 10.52f, 4.0f, 12.0f, 4.0f);
        a.p(pathBuilderA, 14.91f, 12.08f, 17.5f, 6.5f);
        b.D(pathBuilderA, -5.58f, 2.59f, 2.99f, 2.99f);
        pathBuilderA.moveTo(2.1f, 4.93f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.curveTo(2.61f, 8.07f, 2.0f, 9.96f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.curveToRelative(2.04f, 0.0f, 3.93f, -0.61f, 5.51f, -1.66f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderA, 3.51f, 3.51f, 2.1f, 4.93f);
        pathBuilderA.moveTo(5.12f, 7.94f);
        pathBuilderA.lineToRelative(3.98f, 3.98f);
        pathBuilderA.lineToRelative(-2.6f, 5.58f);
        pathBuilderA.lineToRelative(5.58f, -2.59f);
        pathBuilderA.lineToRelative(3.98f, 3.98f);
        pathBuilderA.curveToRelative(-1.2f, 0.7f, -2.58f, 1.11f, -4.06f, 1.11f);
        pathBuilderA.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.48f, 0.41f, -2.86f, 1.12f, -4.06f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _exploreOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
