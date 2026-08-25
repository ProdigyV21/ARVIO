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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sortByAlpha", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SortByAlpha", "Landroidx/compose/material/icons/Icons$Rounded;", "getSortByAlpha", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SortByAlphaKt {
    private static ImageVector _sortByAlpha;

    public static final ImageVector getSortByAlpha(Icons.Rounded rounded) {
        ImageVector imageVector = _sortByAlpha;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SortByAlpha", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.93f, 2.65f);
        pathBuilderA.curveToRelative(-0.2f, -0.2f, -0.51f, -0.2f, -0.71f, 0.0f);
        pathBuilderA.lineToRelative(-2.01f, 2.01f);
        c.v(pathBuilderA, 4.72f, -2.0f, -2.01f);
        pathBuilderA.moveTo(12.23f, 21.35f);
        pathBuilderA.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderA.lineToRelative(1.98f, -1.98f);
        c.v(pathBuilderA, -4.66f, 1.97f, 1.98f);
        pathBuilderA.moveTo(10.98f, 17.73f);
        pathBuilderA.curveToRelative(0.6f, 0.0f, 1.01f, -0.6f, 0.79f, -1.16f);
        pathBuilderA.lineTo(8.04f, 7.03f);
        pathBuilderA.curveToRelative(-0.18f, -0.46f, -0.63f, -0.76f, -1.12f, -0.76f);
        pathBuilderA.curveToRelative(-0.49f, 0.0f, -0.94f, 0.3f, -1.12f, 0.76f);
        pathBuilderA.lineToRelative(-3.74f, 9.53f);
        pathBuilderA.curveToRelative(-0.22f, 0.56f, 0.19f, 1.16f, 0.79f, 1.16f);
        pathBuilderA.curveToRelative(0.35f, 0.0f, 0.67f, -0.22f, 0.8f, -0.55f);
        pathBuilderA.lineToRelative(0.71f, -1.9f);
        pathBuilderA.horizontalLineToRelative(5.11f);
        pathBuilderA.lineToRelative(0.71f, 1.9f);
        pathBuilderA.curveToRelative(0.13f, 0.34f, 0.45f, 0.56f, 0.8f, 0.56f);
        a.z(pathBuilderA, 4.97f, 13.64f, 1.94f, -5.18f);
        d.C(pathBuilderA, 1.94f, 5.18f, 4.97f, 13.64f);
        pathBuilderA.moveTo(21.05f, 16.14f);
        pathBuilderA.horizontalLineToRelative(-5.33f);
        pathBuilderA.lineToRelative(5.72f, -8.29f);
        pathBuilderA.curveToRelative(0.46f, -0.66f, -0.02f, -1.57f, -0.82f, -1.57f);
        pathBuilderA.horizontalLineToRelative(-6.48f);
        pathBuilderA.curveToRelative(-0.44f, 0.0f, -0.79f, 0.36f, -0.79f, 0.8f);
        pathBuilderA.verticalLineToRelative(0.01f);
        pathBuilderA.curveToRelative(0.0f, 0.44f, 0.36f, 0.8f, 0.79f, 0.8f);
        pathBuilderA.horizontalLineToRelative(5.09f);
        pathBuilderA.lineToRelative(-5.73f, 8.28f);
        pathBuilderA.curveToRelative(-0.46f, 0.66f, 0.02f, 1.57f, 0.82f, 1.57f);
        pathBuilderA.horizontalLineToRelative(6.72f);
        pathBuilderA.curveToRelative(0.44f, 0.0f, 0.79f, -0.36f, 0.79f, -0.79f);
        pathBuilderA.curveToRelative(0.02f, -0.45f, -0.34f, -0.81f, -0.78f, -0.81f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sortByAlpha = imageVectorBuild;
        return imageVectorBuild;
    }
}
