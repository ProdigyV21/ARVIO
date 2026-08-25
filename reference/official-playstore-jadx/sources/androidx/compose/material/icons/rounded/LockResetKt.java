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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lockReset", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LockReset", "Landroidx/compose/material/icons/Icons$Rounded;", "getLockReset", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LockResetKt {
    private static ImageVector _lockReset;

    public static final ImageVector getLockReset(Icons.Rounded rounded) {
        ImageVector imageVector = _lockReset;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LockReset", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.26f, 3.0f);
        pathBuilderA.curveTo(8.17f, 2.86f, 4.0f, 6.94f, 4.0f, 12.0f);
        pathBuilderA.horizontalLineTo(2.21f);
        pathBuilderA.curveToRelative(-0.45f, 0.0f, -0.67f, 0.54f, -0.35f, 0.85f);
        pathBuilderA.lineToRelative(2.79f, 2.79f);
        pathBuilderA.curveToRelative(0.2f, 0.2f, 0.51f, 0.2f, 0.71f, 0.0f);
        pathBuilderA.lineToRelative(2.8f, -2.79f);
        pathBuilderA.curveTo(8.46f, 12.54f, 8.24f, 12.0f, 7.79f, 12.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -3.89f, 3.2f, -7.06f, 7.1f, -7.0f);
        pathBuilderA.curveToRelative(3.71f, 0.05f, 6.84f, 3.18f, 6.9f, 6.9f);
        pathBuilderA.curveToRelative(0.06f, 3.91f, -3.1f, 7.1f, -7.0f, 7.1f);
        pathBuilderA.curveToRelative(-1.59f, 0.0f, -3.05f, -0.53f, -4.23f, -1.43f);
        pathBuilderA.curveToRelative(-0.4f, -0.3f, -0.96f, -0.27f, -1.31f, 0.09f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.43f, 0.43f, -0.39f, 1.14f, 0.09f, 1.5f);
        pathBuilderA.curveTo(9.06f, 20.31f, 10.95f, 21.0f, 13.0f, 21.0f);
        pathBuilderA.curveToRelative(5.06f, 0.0f, 9.14f, -4.17f, 9.0f, -9.25f);
        pathBuilderA.curveTo(21.87f, 7.05f, 17.95f, 3.13f, 13.26f, 3.0f);
        d.k(pathBuilderA, 15.0f, 11.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveTo(16.0f, 11.45f, 15.55f, 11.0f, 15.0f, 11.0f);
        b.w(pathBuilderA, 14.0f, 11.0f, -2.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _lockReset = imageVectorBuild;
        return imageVectorBuild;
    }
}
