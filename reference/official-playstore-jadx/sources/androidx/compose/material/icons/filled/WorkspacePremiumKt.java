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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_workspacePremium", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WorkspacePremium", "Landroidx/compose/material/icons/Icons$Filled;", "getWorkspacePremium", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WorkspacePremiumKt {
    private static ImageVector _workspacePremium;

    public static final ImageVector getWorkspacePremium(Icons.Filled filled) {
        ImageVector imageVector = _workspacePremium;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.WorkspacePremium", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
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
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderJ, 8.69f, 4.0f, 12.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _workspacePremium = imageVectorBuild;
        return imageVectorBuild;
    }
}
