package androidx.compose.material.icons.twotone;

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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_report", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Report", "Landroidx/compose/material/icons/Icons$TwoTone;", "getReport", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReportKt {
    private static ImageVector _report;

    public static final ImageVector getReport(Icons.TwoTone twoTone) {
        ImageVector imageVector = _report;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Report", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(9.1f, 5.0f, 5.0f, 9.1f, 5.8f);
        pathBuilderS.lineTo(9.1f, 19.0f);
        pathBuilderS.horizontalLineToRelative(5.8f);
        pathBuilderS.lineToRelative(4.1f, -4.1f);
        pathBuilderS.lineTo(19.0f, 9.1f);
        b.y(pathBuilderS, 14.9f, 5.0f, 9.1f, 5.0f);
        pathBuilderS.moveTo(12.0f, 17.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderS.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        a.g(pathBuilderS, 13.0f, 14.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderS, 11.0f, 7.0f, 2.0f, 7.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderP = y.a.p(15.73f, 3.0f, 8.27f, 3.0f, 8.27f);
        a0.b.k(pathBuilderP, 7.46f, 8.27f, 21.0f, 7.46f);
        pathBuilderP.lineTo(21.0f, 15.73f);
        c.x(pathBuilderP, 8.27f, 15.73f, 3.0f);
        pathBuilderP.moveTo(19.0f, 14.9f);
        pathBuilderP.lineTo(14.9f, 19.0f);
        pathBuilderP.horizontalLineTo(9.1f);
        pathBuilderP.lineTo(5.0f, 14.9f);
        pathBuilderP.verticalLineTo(9.1f);
        pathBuilderP.lineTo(9.1f, 5.0f);
        pathBuilderP.horizontalLineToRelative(5.8f);
        pathBuilderP.lineTo(19.0f, 9.1f);
        pathBuilderP.verticalLineToRelative(5.8f);
        pathBuilderP.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderP.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 16.0f, -1.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilderB.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(11.0f, 7.0f, 2.0f, 7.0f, -2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _report = imageVectorBuild;
        return imageVectorBuild;
    }
}
