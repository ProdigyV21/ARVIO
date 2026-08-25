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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_workspacePremium", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WorkspacePremium", "Landroidx/compose/material/icons/Icons$Rounded;", "getWorkspacePremium", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WorkspacePremiumKt {
    private static ImageVector _workspacePremium;

    public static final ImageVector getWorkspacePremium(Icons.Rounded rounded) {
        ImageVector imageVector = _workspacePremium;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WorkspacePremium", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.92f, 12.75f, 12.0f, 11.93f);
        pathBuilderJ.lineToRelative(1.07f, 0.81f);
        pathBuilderJ.curveToRelative(0.39f, 0.29f, 0.92f, -0.08f, 0.78f, -0.55f);
        pathBuilderJ.lineToRelative(-0.42f, -1.36f);
        pathBuilderJ.lineToRelative(1.2f, -0.95f);
        pathBuilderJ.curveTo(15.0f, 9.6f, 14.79f, 9.0f, 14.31f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(-1.4f);
        pathBuilderJ.lineToRelative(-0.43f, -1.34f);
        pathBuilderJ.curveToRelative(-0.15f, -0.46f, -0.8f, -0.46f, -0.95f, 0.0f);
        pathBuilderJ.lineTo(11.09f, 9.0f);
        pathBuilderJ.horizontalLineTo(9.68f);
        pathBuilderJ.curveTo(9.21f, 9.0f, 9.0f, 9.6f, 9.37f, 9.89f);
        pathBuilderJ.lineToRelative(1.19f, 0.95f);
        pathBuilderJ.lineToRelative(-0.42f, 1.36f);
        pathBuilderJ.curveTo(10.0f, 12.67f, 10.53f, 13.04f, 10.92f, 12.75f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(6.0f, 21.61f);
        pathBuilderJ.curveToRelative(0.0f, 0.68f, 0.67f, 1.16f, 1.32f, 0.95f);
        pathBuilderJ.lineTo(12.0f, 21.0f);
        pathBuilderJ.lineToRelative(4.68f, 1.56f);
        pathBuilderJ.curveTo(17.33f, 22.78f, 18.0f, 22.3f, 18.0f, 21.61f);
        pathBuilderJ.verticalLineToRelative(-6.33f);
        pathBuilderJ.curveToRelative(1.24f, -1.41f, 2.0f, -3.25f, 2.0f, -5.28f);
        pathBuilderJ.curveToRelative(0.0f, -4.42f, -3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilderJ.reflectiveCurveToRelative(-8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.03f, 0.76f, 3.87f, 2.0f, 5.28f);
        f.r(pathBuilderJ, 21.61f, 12.0f, 4.0f);
        pathBuilderJ.curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderJ.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderJ, 8.69f, 4.0f, 12.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _workspacePremium = imageVectorBuild;
        return imageVectorBuild;
    }
}
