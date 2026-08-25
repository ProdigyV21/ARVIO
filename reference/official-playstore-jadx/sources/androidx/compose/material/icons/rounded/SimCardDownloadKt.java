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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_simCardDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SimCardDownload", "Landroidx/compose/material/icons/Icons$Rounded;", "getSimCardDownload", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SimCardDownloadKt {
    private static ImageVector _simCardDownload;

    public static final ImageVector getSimCardDownload(Icons.Rounded rounded) {
        ImageVector imageVector = _simCardDownload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SimCardDownload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(18.0f, 2.0f, -7.17f);
        pathBuilderA.curveTo(10.3f, 2.0f, 9.79f, 2.21f, 9.41f, 2.59f);
        pathBuilderA.lineTo(4.59f, 7.41f);
        pathBuilderA.curveTo(4.21f, 7.79f, 4.0f, 8.3f, 4.0f, 8.83f);
        pathBuilderA.verticalLineTo(20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        a.z(pathBuilderA, 11.65f, 16.65f, -2.79f, -2.79f);
        pathBuilderA.curveTo(8.54f, 13.54f, 8.76f, 13.0f, 9.21f, 13.0f);
        pathBuilderA.horizontalLineTo(11.0f);
        pathBuilderA.verticalLineToRelative(-2.99f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.44f, -0.99f, 0.99f, -1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveTo(12.55f, 9.0f, 13.0f, 9.45f, 13.0f, 10.01f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.horizontalLineToRelative(1.79f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.67f, 0.54f, 0.35f, 0.85f);
        pathBuilderA.lineToRelative(-2.79f, 2.79f);
        pathBuilderA.curveTo(12.16f, 16.84f, 11.84f, 16.84f, 11.65f, 16.65f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _simCardDownload = imageVectorBuild;
        return imageVectorBuild;
    }
}
