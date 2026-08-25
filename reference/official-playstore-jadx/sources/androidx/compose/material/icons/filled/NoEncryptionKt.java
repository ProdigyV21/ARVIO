package androidx.compose.material.icons.filled;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noEncryption", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoEncryption", "Landroidx/compose/material/icons/Icons$Filled;", "getNoEncryption", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoEncryptionKt {
    private static ImageVector _noEncryption;

    public static final ImageVector getNoEncryption(Icons.Filled filled) {
        ImageVector imageVector = _noEncryption;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoEncryption", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.0f, 21.78f, 4.22f, 5.0f);
        pathBuilderJ.lineTo(3.0f, 6.22f);
        pathBuilderJ.lineToRelative(2.04f, 2.04f);
        pathBuilderJ.curveTo(4.42f, 8.6f, 4.0f, 9.25f, 4.0f, 10.0f);
        pathBuilderJ.verticalLineToRelative(10.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.23f, 0.0f, 0.45f, -0.05f, 0.66f, -0.12f);
        b.y(pathBuilderJ, 19.78f, 23.0f, 21.0f, 21.78f);
        pathBuilderJ.moveTo(8.9f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.71f, 1.39f, -3.1f, 3.1f, -3.1f);
        pathBuilderJ.reflectiveCurveToRelative(3.1f, 1.39f, 3.1f, 3.1f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.horizontalLineTo(9.66f);
        pathBuilderJ.lineTo(20.0f, 18.34f);
        pathBuilderJ.verticalLineTo(10.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(-1.0f);
        pathBuilderJ.verticalLineTo(6.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderJ.curveToRelative(-2.56f, 0.0f, -4.64f, 1.93f, -4.94f, 4.4f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderJ, 8.9f, 7.24f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noEncryption = imageVectorBuild;
        return imageVectorBuild;
    }
}
