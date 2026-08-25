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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_spa", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Spa", "Landroidx/compose/material/icons/Icons$Sharp;", "getSpa", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpaKt {
    private static ImageVector _spa;

    public static final ImageVector getSpa(Icons.Sharp sharp) {
        ImageVector imageVector = _spa;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Spa", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.55f, 12.0f);
        pathBuilderA.curveToRelative(-1.07f, -0.71f, -2.25f, -1.27f, -3.53f, -1.61f);
        pathBuilderA.curveToRelative(1.28f, 0.34f, 2.46f, 0.9f, 3.53f, 1.61f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.98f, 10.39f);
        pathBuilderA.curveToRelative(-1.29f, 0.34f, -2.49f, 0.91f, -3.57f, 1.64f);
        pathBuilderA.curveToRelative(1.08f, -0.73f, 2.28f, -1.3f, 3.57f, -1.64f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.49f, 9.63f);
        pathBuilderA.curveToRelative(-0.18f, -2.79f, -1.31f, -5.51f, -3.43f, -7.63f);
        pathBuilderA.curveToRelative(-2.14f, 2.14f, -3.32f, 4.86f, -3.55f, 7.63f);
        pathBuilderA.curveToRelative(1.28f, 0.68f, 2.46f, 1.56f, 3.49f, 2.63f);
        pathBuilderA.curveToRelative(1.03f, -1.06f, 2.21f, -1.94f, 3.49f, -2.63f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.99f, 12.28f);
        pathBuilderA.curveToRelative(-0.14f, -0.1f, -0.3f, -0.19f, -0.45f, -0.29f);
        pathBuilderA.curveToRelative(0.15f, 0.11f, 0.31f, 0.19f, 0.45f, 0.29f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.41f, 12.03f);
        pathBuilderA.curveToRelative(-0.13f, 0.09f, -0.27f, 0.16f, -0.4f, 0.26f);
        pathBuilderA.curveToRelative(0.13f, -0.1f, 0.27f, -0.17f, 0.4f, -0.26f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 15.45f);
        pathBuilderA.curveTo(9.85f, 12.17f, 6.18f, 10.0f, 2.0f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, 5.32f, 3.36f, 9.82f, 8.03f, 11.49f);
        pathBuilderA.curveToRelative(0.63f, 0.23f, 1.29f, 0.4f, 1.97f, 0.51f);
        pathBuilderA.curveToRelative(0.68f, -0.12f, 1.33f, -0.29f, 1.97f, -0.51f);
        pathBuilderA.curveTo(18.64f, 19.82f, 22.0f, 15.32f, 22.0f, 10.0f);
        pathBuilderA.curveToRelative(-4.18f, 0.0f, -7.85f, 2.17f, -10.0f, 5.45f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _spa = imageVectorBuild;
        return imageVectorBuild;
    }
}
