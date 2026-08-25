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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stars", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stars", "Landroidx/compose/material/icons/Icons$Rounded;", "getStars", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarsKt {
    private static ImageVector _stars;

    public static final ImageVector getStars(Icons.Rounded rounded) {
        ImageVector imageVector = _stars;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Stars", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.99f, 2.0f);
        pathBuilderA.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderA.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
        a.p(pathBuilderA, 15.22f, 17.39f, 12.0f, 15.45f);
        pathBuilderA.lineToRelative(-3.22f, 1.94f);
        pathBuilderA.curveToRelative(-0.38f, 0.23f, -0.85f, -0.11f, -0.75f, -0.54f);
        pathBuilderA.lineToRelative(0.85f, -3.66f);
        pathBuilderA.lineToRelative(-2.83f, -2.45f);
        pathBuilderA.curveToRelative(-0.33f, -0.29f, -0.15f, -0.84f, 0.29f, -0.88f);
        pathBuilderA.lineToRelative(3.74f, -0.32f);
        pathBuilderA.lineToRelative(1.46f, -3.45f);
        pathBuilderA.curveToRelative(0.17f, -0.41f, 0.75f, -0.41f, 0.92f, 0.0f);
        pathBuilderA.lineToRelative(1.46f, 3.44f);
        pathBuilderA.lineToRelative(3.74f, 0.32f);
        pathBuilderA.curveToRelative(0.44f, 0.04f, 0.62f, 0.59f, 0.28f, 0.88f);
        pathBuilderA.lineToRelative(-2.83f, 2.45f);
        pathBuilderA.lineToRelative(0.85f, 3.67f);
        pathBuilderA.curveToRelative(0.1f, 0.43f, -0.36f, 0.77f, -0.74f, 0.54f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stars = imageVectorBuild;
        return imageVectorBuild;
    }
}
