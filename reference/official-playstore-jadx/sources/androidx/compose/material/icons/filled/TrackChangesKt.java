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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_trackChanges", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TrackChanges", "Landroidx/compose/material/icons/Icons$Filled;", "getTrackChanges", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TrackChangesKt {
    private static ImageVector _trackChanges;

    public static final ImageVector getTrackChanges(Icons.Filled filled) {
        ImageVector imageVector = _trackChanges;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TrackChanges", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.07f, 4.93f, -1.41f, 1.41f);
        pathBuilderR.curveTo(19.1f, 7.79f, 20.0f, 9.79f, 20.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f);
        pathBuilderR.reflectiveCurveToRelative(-8.0f, -3.58f, -8.0f, -8.0f);
        pathBuilderR.curveToRelative(0.0f, -4.08f, 3.05f, -7.44f, 7.0f, -7.93f);
        pathBuilderR.verticalLineToRelative(2.02f);
        pathBuilderR.curveTo(8.16f, 6.57f, 6.0f, 9.03f, 6.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderR.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderR.curveToRelative(0.0f, -1.66f, -0.67f, -3.16f, -1.76f, -4.24f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        pathBuilderR.curveTo(15.55f, 9.9f, 16.0f, 10.9f, 16.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.86f, 1.28f, -3.41f, 3.0f, -3.86f);
        pathBuilderR.verticalLineToRelative(2.14f);
        pathBuilderR.curveToRelative(-0.6f, 0.35f, -1.0f, 0.98f, -1.0f, 1.72f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.curveToRelative(0.0f, -0.74f, -0.4f, -1.38f, -1.0f, -1.72f);
        pathBuilderR.verticalLineTo(2.0f);
        pathBuilderR.horizontalLineToRelative(-1.0f);
        pathBuilderR.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderR.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderR.curveToRelative(0.0f, -2.76f, -1.12f, -5.26f, -2.93f, -7.07f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _trackChanges = imageVectorBuild;
        return imageVectorBuild;
    }
}
