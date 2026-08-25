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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cached", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Cached", "Landroidx/compose/material/icons/Icons$Rounded;", "getCached", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CachedKt {
    private static ImageVector _cached;

    public static final ImageVector getCached(Icons.Rounded rounded) {
        ImageVector imageVector = _cached;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Cached", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.65f, 8.35f, -2.79f, 2.79f);
        pathBuilderR.curveToRelative(-0.32f, 0.32f, -0.1f, 0.86f, 0.35f, 0.86f);
        pathBuilderR.horizontalLineTo(18.0f);
        pathBuilderR.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderR.curveToRelative(-0.79f, 0.0f, -1.56f, -0.15f, -2.25f, -0.44f);
        pathBuilderR.curveToRelative(-0.36f, -0.15f, -0.77f, -0.04f, -1.04f, 0.23f);
        pathBuilderR.curveToRelative(-0.51f, 0.51f, -0.33f, 1.37f, 0.34f, 1.64f);
        pathBuilderR.curveToRelative(0.91f, 0.37f, 1.91f, 0.57f, 2.95f, 0.57f);
        pathBuilderR.curveToRelative(4.42f, 0.0f, 8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderR.horizontalLineToRelative(1.79f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderR.lineToRelative(-2.79f, -2.79f);
        pathBuilderR.curveToRelative(-0.19f, -0.2f, -0.51f, -0.2f, -0.7f, -0.01f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -3.31f, 2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderR.curveToRelative(0.79f, 0.0f, 1.56f, 0.15f, 2.25f, 0.44f);
        pathBuilderR.curveToRelative(0.36f, 0.15f, 0.77f, 0.04f, 1.04f, -0.23f);
        pathBuilderR.curveToRelative(0.51f, -0.51f, 0.33f, -1.37f, -0.34f, -1.64f);
        pathBuilderR.curveTo(14.04f, 4.2f, 13.04f, 4.0f, 12.0f, 4.0f);
        pathBuilderR.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderR.horizontalLineTo(2.21f);
        pathBuilderR.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderR.lineToRelative(2.79f, 2.79f);
        pathBuilderR.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderR.lineToRelative(2.79f, -2.79f);
        pathBuilderR.curveToRelative(0.31f, -0.31f, 0.09f, -0.85f, -0.36f, -0.85f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderR, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cached = imageVectorBuild;
        return imageVectorBuild;
    }
}
