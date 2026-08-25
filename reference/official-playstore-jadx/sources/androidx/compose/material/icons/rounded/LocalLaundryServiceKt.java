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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localLaundryService", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalLaundryService", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalLaundryService", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalLaundryServiceKt {
    private static ImageVector _localLaundryService;

    public static final ImageVector getLocalLaundryService(Icons.Rounded rounded) {
        ImageVector imageVector = _localLaundryService;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalLaundryService", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.64f, 16.36f);
        pathBuilderA.curveToRelative(1.3f, 1.3f, 3.42f, 1.3f, 4.72f, 0.0f);
        pathBuilderA.curveToRelative(1.3f, -1.3f, 1.3f, -3.42f, 0.0f, -4.72f);
        c.z(pathBuilderA, 9.64f, 16.36f, 18.0f, 2.01f);
        pathBuilderA.lineTo(6.0f, 2.0f);
        pathBuilderA.curveTo(4.89f, 2.0f, 4.0f, 2.89f, 4.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(20.0f, 2.89f, 19.11f, 2.01f, 18.0f, 2.01f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(10.45f, 5.0f, 11.0f, 5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.0f, 5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(8.55f, 7.0f, 8.0f, 7.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 6.55f, 7.0f, 6.0f);
        pathBuilderA.reflectiveCurveTo(7.45f, 5.0f, 8.0f, 5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 19.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, 2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.curveTo(17.0f, 16.76f, 14.76f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localLaundryService = imageVectorBuild;
        return imageVectorBuild;
    }
}
