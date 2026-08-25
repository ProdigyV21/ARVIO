package androidx.compose.material.icons.sharp;

import a0.b;
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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_reportOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ReportOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getReportOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReportOffKt {
    private static ImageVector _reportOff;

    public static final ImageVector getReportOff(Icons.Sharp sharp) {
        ImageVector imageVector = _reportOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ReportOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(11.0f, 7.0f, 2.0f, 2.33f);
        pathBuilderA.lineToRelative(7.2f, 7.2f);
        pathBuilderA.lineToRelative(0.8f, -0.8f);
        pathBuilderA.lineTo(21.0f, 8.27f);
        pathBuilderA.lineTo(15.73f, 3.0f);
        pathBuilderA.lineTo(8.27f, 3.0f);
        d.C(pathBuilderA, -0.8f, 0.8f, 11.0f, 7.33f);
        pathBuilderA.moveTo(2.41f, 1.58f);
        pathBuilderA.lineTo(1.0f, 2.99f);
        pathBuilderA.lineToRelative(3.64f, 3.64f);
        pathBuilderA.lineTo(3.0f, 8.27f);
        b.k(pathBuilderA, 7.46f, 8.27f, 21.0f, 7.46f);
        pathBuilderA.lineToRelative(1.64f, -1.64f);
        pathBuilderA.lineTo(21.01f, 23.0f);
        d.C(pathBuilderA, 1.41f, -1.41f, 2.41f, 1.58f);
        pathBuilderA.moveTo(11.0f, 12.99f);
        pathBuilderA.lineToRelative(0.01f, 0.01f);
        d.f(pathBuilderA, 11.0f, 13.0f, -0.01f);
        pathBuilderA.moveTo(12.0f, 17.3f);
        pathBuilderA.curveToRelative(-0.72f, 0.0f, -1.3f, -0.58f, -1.3f, -1.3f);
        pathBuilderA.curveToRelative(0.0f, -0.72f, 0.58f, -1.3f, 1.3f, -1.3f);
        pathBuilderA.reflectiveCurveToRelative(1.3f, 0.58f, 1.3f, 1.3f);
        pathBuilderA.curveToRelative(0.0f, 0.72f, -0.58f, 1.3f, -1.3f, 1.3f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _reportOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
