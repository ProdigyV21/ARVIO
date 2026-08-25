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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dynamicForm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DynamicForm", "Landroidx/compose/material/icons/Icons$Rounded;", "getDynamicForm", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DynamicFormKt {
    private static ImageVector _dynamicForm;

    public static final ImageVector getDynamicForm(Icons.Rounded rounded) {
        ImageVector imageVector = _dynamicForm;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DynamicForm", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.68f, 9.71f, -3.72f, 8.19f);
        pathBuilderR.curveTo(17.73f, 18.39f, 17.0f, 18.23f, 17.0f, 17.69f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.horizontalLineToRelative(-1.5f);
        pathBuilderR.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderR.verticalLineToRelative(-6.0f);
        pathBuilderR.curveTo(15.0f, 4.22f, 15.22f, 4.0f, 15.5f, 4.0f);
        pathBuilderR.horizontalLineToRelative(5.76f);
        pathBuilderR.curveToRelative(0.35f, 0.0f, 0.6f, 0.36f, 0.46f, 0.69f);
        pathBuilderR.lineTo(20.0f, 9.0f);
        pathBuilderR.horizontalLineToRelative(1.22f);
        pathBuilderR.curveTo(21.59f, 9.0f, 21.83f, 9.38f, 21.68f, 9.71f);
        a0.a.z(pathBuilderR, 15.0f, 13.0f, 7.0f, 4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-3.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        a.x(pathBuilderR, 15.0f, 6.25f, 16.5f);
        pathBuilderR.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderR.reflectiveCurveToRelative(-0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderR.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderR.reflectiveCurveTo(6.25f, 16.91f, 6.25f, 16.5f);
        a0.a.z(pathBuilderR, 13.0f, 4.0f, 7.0f, 4.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineTo(6.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        a.x(pathBuilderR, 13.0f, 6.25f, 7.5f);
        pathBuilderR.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderR.reflectiveCurveTo(4.75f, 7.09f, 4.75f, 7.5f);
        pathBuilderR.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 6.25f, 7.91f, 6.25f, 7.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dynamicForm = imageVectorBuild;
        return imageVectorBuild;
    }
}
