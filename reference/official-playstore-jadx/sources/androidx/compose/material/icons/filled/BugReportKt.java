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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bugReport", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BugReport", "Landroidx/compose/material/icons/Icons$Filled;", "getBugReport", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BugReportKt {
    private static ImageVector _bugReport;

    public static final ImageVector getBugReport(Icons.Filled filled) {
        ImageVector imageVector = _bugReport;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BugReport", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(20.0f, 8.0f, -2.81f);
        pathBuilderA.curveToRelative(-0.45f, -0.78f, -1.07f, -1.45f, -1.82f, -1.96f);
        pathBuilderA.lineTo(17.0f, 4.41f);
        pathBuilderA.lineTo(15.59f, 3.0f);
        pathBuilderA.lineToRelative(-2.17f, 2.17f);
        pathBuilderA.curveTo(12.96f, 5.06f, 12.49f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.49f, 0.0f, -0.96f, 0.06f, -1.41f, 0.17f);
        pathBuilderA.lineTo(8.41f, 3.0f);
        pathBuilderA.lineTo(7.0f, 4.41f);
        pathBuilderA.lineToRelative(1.62f, 1.63f);
        pathBuilderA.curveTo(7.88f, 6.55f, 7.26f, 7.22f, 6.81f, 8.0f);
        a.h(pathBuilderA, 4.0f, 8.0f, 2.0f, 2.09f);
        pathBuilderA.curveToRelative(-0.05f, 0.33f, -0.09f, 0.66f, -0.09f, 1.0f);
        d.r(pathBuilderA, 1.0f, 4.0f, 12.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.34f, 0.04f, 0.67f, 0.09f, 1.0f);
        a.h(pathBuilderA, 4.0f, 16.0f, 2.0f, 2.81f);
        pathBuilderA.curveToRelative(1.04f, 1.79f, 2.97f, 3.0f, 5.19f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(4.15f, -1.21f, 5.19f, -3.0f);
        a.h(pathBuilderA, 20.0f, 18.0f, -2.0f, -2.09f);
        pathBuilderA.curveToRelative(0.05f, -0.33f, 0.09f, -0.66f, 0.09f, -1.0f);
        b.z(pathBuilderA, -1.0f, 2.0f, -2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.34f, -0.04f, -0.67f, -0.09f, -1.0f);
        b.y(pathBuilderA, 20.0f, 10.0f, 20.0f, 8.0f);
        a.C(pathBuilderA, 14.0f, 16.0f, -4.0f, -2.0f);
        b.B(pathBuilderA, 4.0f, 2.0f, 14.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderA, -4.0f, -2.0f, 4.0f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bugReport = imageVectorBuild;
        return imageVectorBuild;
    }
}
