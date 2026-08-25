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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbsUpDown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbsUpDown", "Landroidx/compose/material/icons/Icons$Outlined;", "getThumbsUpDown", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbsUpDownKt {
    private static ImageVector _thumbsUpDown;

    public static final ImageVector getThumbsUpDown(Icons.Outlined outlined) {
        ImageVector imageVector = _thumbsUpDown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ThumbsUpDown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.lineTo(5.82f, 5.0f);
        pathBuilderA.lineToRelative(0.66f, -3.18f);
        pathBuilderA.lineToRelative(0.02f, -0.23f);
        pathBuilderA.curveToRelative(0.0f, -0.31f, -0.13f, -0.59f, -0.33f, -0.8f);
        pathBuilderA.lineTo(5.38f, 0.0f);
        pathBuilderA.lineTo(0.44f, 4.94f);
        pathBuilderA.curveTo(0.17f, 5.21f, 0.0f, 5.59f, 0.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(6.5f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(6.75f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.15f, -0.38f, 1.38f, -0.91f);
        pathBuilderA.lineToRelative(2.26f, -5.29f);
        pathBuilderA.curveToRelative(0.07f, -0.17f, 0.11f, -0.36f, 0.11f, -0.55f);
        c.z(pathBuilderA, 12.0f, 6.0f, 10.0f, 7.13f);
        pathBuilderA.lineTo(7.92f, 12.0f);
        pathBuilderA.lineTo(2.0f, 12.0f);
        pathBuilderA.lineTo(2.0f, 6.21f);
        pathBuilderA.lineToRelative(1.93f, -1.93f);
        pathBuilderA.lineTo(3.36f, 7.0f);
        d.f(pathBuilderA, 10.0f, 7.0f, 0.13f);
        pathBuilderA.moveTo(22.5f, 10.0f);
        pathBuilderA.horizontalLineToRelative(-6.75f);
        pathBuilderA.curveToRelative(-0.62f, 0.0f, -1.15f, 0.38f, -1.38f, 0.91f);
        pathBuilderA.lineToRelative(-2.26f, 5.29f);
        pathBuilderA.curveToRelative(-0.07f, 0.17f, -0.11f, 0.36f, -0.11f, 0.55f);
        pathBuilderA.lineTo(12.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(5.18f);
        pathBuilderA.lineToRelative(-0.66f, 3.18f);
        pathBuilderA.lineToRelative(-0.02f, 0.24f);
        pathBuilderA.curveToRelative(0.0f, 0.31f, 0.13f, 0.59f, 0.33f, 0.8f);
        pathBuilderA.lineToRelative(0.79f, 0.78f);
        pathBuilderA.lineToRelative(4.94f, -4.94f);
        pathBuilderA.curveToRelative(0.27f, -0.27f, 0.44f, -0.65f, 0.44f, -1.06f);
        pathBuilderA.verticalLineToRelative(-6.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        a.z(pathBuilderA, 22.0f, 17.79f, -1.93f, 1.93f);
        pathBuilderA.lineToRelative(0.57f, -2.72f);
        pathBuilderA.lineTo(14.0f, 17.0f);
        pathBuilderA.verticalLineToRelative(-0.13f);
        pathBuilderA.lineTo(16.08f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderA, 22.0f, 12.0f, 5.79f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbsUpDown = imageVectorBuild;
        return imageVectorBuild;
    }
}
