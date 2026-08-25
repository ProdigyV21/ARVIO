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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_videoFile", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VideoFile", "Landroidx/compose/material/icons/Icons$Rounded;", "getVideoFile", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VideoFileKt {
    private static ImageVector _videoFile;

    public static final ImageVector getVideoFile(Icons.Rounded rounded) {
        ImageVector imageVector = _videoFile;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.VideoFile", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(13.17f, 2.0f, 6.01f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderQ.lineTo(4.0f, 20.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(8.83f);
        pathBuilderQ.curveToRelative(0.0f, -0.53f, -0.21f, -1.04f, -0.59f, -1.41f);
        pathBuilderQ.lineToRelative(-4.83f, -4.83f);
        pathBuilderQ.curveTo(14.21f, 2.21f, 13.7f, 2.0f, 13.17f, 2.0f);
        w.a.u(pathBuilderQ, 13.0f, 8.0f, 3.5f);
        pathBuilderQ.lineTo(18.5f, 9.0f);
        pathBuilderQ.horizontalLineTo(14.0f);
        pathBuilderQ.curveTo(13.45f, 9.0f, 13.0f, 8.55f, 13.0f, 8.0f);
        a.z(pathBuilderQ, 14.0f, 14.0f, 1.27f, -0.67f);
        pathBuilderQ.curveTo(15.6f, 13.15f, 16.0f, 13.39f, 16.0f, 13.77f);
        pathBuilderQ.verticalLineToRelative(2.46f);
        pathBuilderQ.curveToRelative(0.0f, 0.38f, -0.4f, 0.62f, -0.73f, 0.44f);
        pathBuilderQ.lineTo(14.0f, 16.0f);
        pathBuilderQ.verticalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineTo(9.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(-4.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _videoFile = imageVectorBuild;
        return imageVectorBuild;
    }
}
