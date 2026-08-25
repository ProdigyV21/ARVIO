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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_workspacePremium", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WorkspacePremium", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWorkspacePremium", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WorkspacePremiumKt {
    private static ImageVector _workspacePremium;

    public static final ImageVector getWorkspacePremium(Icons.TwoTone twoTone) {
        ImageVector imageVector = _workspacePremium;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WorkspacePremium", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderA.reflectiveCurveTo(15.31f, 4.0f, 12.0f, 4.0f);
        a.p(pathBuilderA, 14.31f, 13.69f, 12.0f, 11.93f);
        pathBuilderA.lineToRelative(-2.32f, 1.76f);
        pathBuilderA.lineToRelative(0.88f, -2.85f);
        pathBuilderA.lineTo(8.25f, 9.0f);
        pathBuilderA.horizontalLineToRelative(2.84f);
        pathBuilderA.lineTo(12.0f, 6.19f);
        pathBuilderA.lineTo(12.91f, 9.0f);
        pathBuilderA.horizontalLineToRelative(2.84f);
        d.C(pathBuilderA, -2.32f, 1.84f, 14.31f, 13.69f);
        pathBuilderA.moveTo(12.0f, 19.0f);
        pathBuilderA.lineToRelative(-4.0f, 1.02f);
        pathBuilderA.verticalLineToRelative(-3.1f);
        pathBuilderA.curveTo(9.18f, 17.6f, 10.54f, 18.0f, 12.0f, 18.0f);
        pathBuilderA.reflectiveCurveToRelative(2.82f, -0.4f, 4.0f, -1.08f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, 3.1f, 12.0f, 19.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(9.68f, 13.69f, 12.0f, 11.93f);
        pathBuilderJ.lineToRelative(2.31f, 1.76f);
        pathBuilderJ.lineToRelative(-0.88f, -2.85f);
        pathBuilderJ.lineTo(15.75f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(-2.84f);
        pathBuilderJ.lineTo(12.0f, 6.19f);
        pathBuilderJ.lineTo(11.09f, 9.0f);
        pathBuilderJ.horizontalLineTo(8.25f);
        d.C(pathBuilderJ, 2.31f, 1.84f, 9.68f, 13.69f);
        pathBuilderJ.moveTo(20.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.reflectiveCurveToRelative(-8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.03f, 0.76f, 3.87f, 2.0f, 5.28f);
        pathBuilderJ.verticalLineTo(23.0f);
        pathBuilderJ.lineToRelative(6.0f, -2.0f);
        pathBuilderJ.lineToRelative(6.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(-7.72f);
        pathBuilderJ.curveTo(19.24f, 13.87f, 20.0f, 12.03f, 20.0f, 10.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 4.0f);
        pathBuilderJ.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderJ.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderJ.reflectiveCurveTo(8.69f, 4.0f, 12.0f, 4.0f);
        a.z(pathBuilderJ, 12.0f, 19.0f, -4.0f, 1.02f);
        pathBuilderJ.verticalLineToRelative(-3.1f);
        pathBuilderJ.curveTo(9.18f, 17.6f, 10.54f, 18.0f, 12.0f, 18.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.82f, -0.4f, 4.0f, -1.08f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderJ, 3.1f, 12.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _workspacePremium = imageVectorBuild;
        return imageVectorBuild;
    }
}
