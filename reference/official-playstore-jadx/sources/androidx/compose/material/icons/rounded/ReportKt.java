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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_report", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Report", "Landroidx/compose/material/icons/Icons$Rounded;", "getReport", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReportKt {
    private static ImageVector _report;

    public static final ImageVector getReport(Icons.Rounded rounded) {
        ImageVector imageVector = _report;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Report", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.32f, 3.0f, 8.68f, 3.0f);
        pathBuilderJ.curveToRelative(-0.26f, 0.0f, -0.52f, 0.11f, -0.7f, 0.29f);
        pathBuilderJ.lineTo(3.29f, 7.98f);
        pathBuilderJ.curveToRelative(-0.18f, 0.18f, -0.29f, 0.44f, -0.29f, 0.7f);
        pathBuilderJ.verticalLineToRelative(6.63f);
        pathBuilderJ.curveToRelative(0.0f, 0.27f, 0.11f, 0.52f, 0.29f, 0.71f);
        pathBuilderJ.lineToRelative(4.68f, 4.68f);
        pathBuilderJ.curveToRelative(0.19f, 0.19f, 0.45f, 0.3f, 0.71f, 0.3f);
        pathBuilderJ.horizontalLineToRelative(6.63f);
        pathBuilderJ.curveToRelative(0.27f, 0.0f, 0.52f, -0.11f, 0.71f, -0.29f);
        pathBuilderJ.lineToRelative(4.68f, -4.68f);
        pathBuilderJ.curveToRelative(0.19f, -0.19f, 0.29f, -0.44f, 0.29f, -0.71f);
        pathBuilderJ.lineTo(20.99f, 8.68f);
        pathBuilderJ.curveToRelative(0.0f, -0.27f, -0.11f, -0.52f, -0.29f, -0.71f);
        pathBuilderJ.lineToRelative(-4.68f, -4.68f);
        pathBuilderJ.curveToRelative(-0.18f, -0.18f, -0.44f, -0.29f, -0.7f, -0.29f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 17.3f);
        pathBuilderJ.curveToRelative(-0.72f, 0.0f, -1.3f, -0.58f, -1.3f, -1.3f);
        pathBuilderJ.reflectiveCurveToRelative(0.58f, -1.3f, 1.3f, -1.3f);
        pathBuilderJ.reflectiveCurveToRelative(1.3f, 0.58f, 1.3f, 1.3f);
        pathBuilderJ.reflectiveCurveToRelative(-0.58f, 1.3f, -1.3f, 1.3f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 13.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(11.0f, 8.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _report = imageVectorBuild;
        return imageVectorBuild;
    }
}
