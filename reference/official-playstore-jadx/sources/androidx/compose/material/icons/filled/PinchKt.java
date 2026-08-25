package androidx.compose.material.icons.filled;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pinch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pinch", "Landroidx/compose/material/icons/Icons$Filled;", "getPinch", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinchKt {
    private static ImageVector _pinch;

    public static final ImageVector getPinch(Icons.Filled filled) {
        ImageVector imageVector = _pinch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Pinch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(6.0f, 2.5f, 1.0f, 5.0f, 5.0f);
        pathBuilderA.horizontalLineTo(9.5f);
        pathBuilderA.verticalLineTo(3.56f);
        pathBuilderA.lineTo(3.56f, 9.5f);
        a.x(pathBuilderA, 6.0f, 11.0f, 1.0f, 6.0f);
        c.q(pathBuilderA, 1.5f, 2.44f, 8.44f, 2.5f);
        v.a.x(pathBuilderA, 6.0f, 22.98f, 16.82f);
        pathBuilderA.lineToRelative(-0.63f, 4.46f);
        pathBuilderA.curveTo(22.21f, 22.27f, 21.36f, 23.0f, 20.37f, 23.0f);
        pathBuilderA.horizontalLineToRelative(-6.16f);
        pathBuilderA.curveToRelative(-0.53f, 0.0f, -1.29f, -0.21f, -1.66f, -0.59f);
        pathBuilderA.lineTo(8.0f, 17.62f);
        pathBuilderA.lineToRelative(0.83f, -0.84f);
        pathBuilderA.curveToRelative(0.24f, -0.24f, 0.58f, -0.35f, 0.92f, -0.28f);
        pathBuilderA.lineTo(13.0f, 17.24f);
        pathBuilderA.verticalLineTo(6.5f);
        pathBuilderA.curveTo(13.0f, 5.67f, 13.67f, 5.0f, 14.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(16.0f, 5.67f, 16.0f, 6.5f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.horizontalLineToRelative(0.91f);
        pathBuilderA.curveToRelative(0.31f, 0.0f, 0.62f, 0.07f, 0.89f, 0.21f);
        pathBuilderA.lineToRelative(4.09f, 2.04f);
        pathBuilderA.curveTo(22.66f, 15.14f, 23.1f, 15.97f, 22.98f, 16.82f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pinch = imageVectorBuild;
        return imageVectorBuild;
    }
}
