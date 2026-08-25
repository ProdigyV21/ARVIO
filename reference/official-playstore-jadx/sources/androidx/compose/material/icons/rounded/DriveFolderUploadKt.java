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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_driveFolderUpload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DriveFolderUpload", "Landroidx/compose/material/icons/Icons$Rounded;", "getDriveFolderUpload", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DriveFolderUploadKt {
    private static ImageVector _driveFolderUpload;

    public static final ImageVector getDriveFolderUpload(Icons.Rounded rounded) {
        ImageVector imageVector = _driveFolderUpload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DriveFolderUpload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -1.41f, -1.41f);
        pathBuilderG.curveTo(10.21f, 4.21f, 9.7f, 4.0f, 9.17f, 4.0f);
        pathBuilderG.horizontalLineTo(4.0f);
        pathBuilderG.curveTo(2.9f, 4.0f, 2.01f, 4.9f, 2.01f, 6.0f);
        pathBuilderG.lineTo(2.0f, 18.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveTo(22.0f, 6.9f, 21.1f, 6.0f, 20.0f, 6.0f);
        d.k(pathBuilderG, 13.0f, 13.0f, 3.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderG.horizontalLineToRelative(0.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderG.verticalLineToRelative(-3.0f);
        pathBuilderG.horizontalLineTo(9.21f);
        pathBuilderG.curveToRelative(-0.45f, 0.0f, -0.67f, -0.54f, -0.35f, -0.85f);
        pathBuilderG.lineToRelative(2.8f, -2.79f);
        pathBuilderG.curveToRelative(0.2f, -0.2f, 0.51f, -0.19f, 0.71f, 0.0f);
        pathBuilderG.lineToRelative(2.79f, 2.79f);
        pathBuilderG.curveTo(15.46f, 12.46f, 15.24f, 13.0f, 14.8f, 13.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderG, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _driveFolderUpload = imageVectorBuild;
        return imageVectorBuild;
    }
}
