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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sanitizer", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sanitizer", "Landroidx/compose/material/icons/Icons$Rounded;", "getSanitizer", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SanitizerKt {
    private static ImageVector _sanitizer;

    public static final ImageVector getSanitizer(Icons.Rounded rounded) {
        ImageVector imageVector = _sanitizer;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Sanitizer", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.5f, 6.5f);
        pathBuilderA.curveToRelative(0.0f, -0.56f, 0.67f, -1.49f, 1.11f, -2.04f);
        pathBuilderA.curveToRelative(0.2f, -0.25f, 0.58f, -0.25f, 0.77f, 0.0f);
        pathBuilderA.curveToRelative(0.44f, 0.55f, 1.11f, 1.48f, 1.11f, 2.04f);
        pathBuilderA.curveTo(18.5f, 7.33f, 17.83f, 8.0f, 17.0f, 8.0f);
        pathBuilderA.reflectiveCurveTo(15.5f, 7.33f, 15.5f, 6.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.5f, 15.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.25f, -1.41f, -3.16f, -2.11f, -4.04f);
        pathBuilderA.curveToRelative(-0.2f, -0.25f, -0.57f, -0.25f, -0.77f, 0.0f);
        pathBuilderA.curveTo(18.41f, 9.34f, 17.0f, 11.25f, 17.0f, 12.5f);
        pathBuilderA.curveTo(17.0f, 13.88f, 18.12f, 15.0f, 19.5f, 15.0f);
        b.w(pathBuilderA, 12.0f, 14.0f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(13.0f, 14.45f, 12.55f, 14.0f, 12.0f, 14.0f);
        d.k(pathBuilderA, 16.0f, 12.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-8.0f);
        pathBuilderA.curveToRelative(0.0f, -2.97f, 2.16f, -5.43f, 5.0f, -5.91f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(7.45f, 4.0f, 7.0f, 3.55f, 7.0f, 3.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.61f, 0.0f, 1.19f, 0.11f, 1.72f, 0.31f);
        pathBuilderA.curveToRelative(0.67f, 0.25f, 0.83f, 1.13f, 0.33f, 1.64f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(14.77f, 4.23f, 14.36f, 4.31f, 14.0f, 4.18f);
        pathBuilderA.curveTo(13.68f, 4.06f, 13.35f, 4.0f, 13.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(2.09f);
        pathBuilderA.curveTo(13.84f, 6.57f, 16.0f, 9.03f, 16.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sanitizer = imageVectorBuild;
        return imageVectorBuild;
    }
}
