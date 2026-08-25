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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_filePresent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FilePresent", "Landroidx/compose/material/icons/Icons$Filled;", "getFilePresent", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FilePresentKt {
    private static ImageVector _filePresent;

    public static final ImageVector getFilePresent(Icons.Filled filled) {
        ImageVector imageVector = _filePresent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FilePresent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 2.0f, 6.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        d.l(pathBuilderJ, 20.0f, 7.0f, -5.0f, -5.0f);
        pathBuilderJ.moveTo(6.0f, 20.0f);
        b.A(pathBuilderJ, 6.0f, 4.0f, 8.0f, 4.0f);
        d.m(pathBuilderJ, 4.0f, 12.0f, 6.0f, 20.0f);
        pathBuilderJ.moveTo(16.0f, 10.0f);
        pathBuilderJ.verticalLineToRelative(5.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderJ.reflectiveCurveToRelative(-4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderJ.lineTo(8.0f, 8.5f);
        pathBuilderJ.curveToRelative(0.0f, -1.47f, 1.26f, -2.64f, 2.76f, -2.49f);
        pathBuilderJ.curveToRelative(1.3f, 0.13f, 2.24f, 1.32f, 2.24f, 2.63f);
        pathBuilderJ.lineTo(13.0f, 15.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.lineTo(11.0f, 8.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderJ.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderJ.lineTo(10.0f, 15.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderJ, -5.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _filePresent = imageVectorBuild;
        return imageVectorBuild;
    }
}
