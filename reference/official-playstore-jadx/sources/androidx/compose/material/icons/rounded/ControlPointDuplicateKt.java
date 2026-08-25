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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_controlPointDuplicate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ControlPointDuplicate", "Landroidx/compose/material/icons/Icons$Rounded;", "getControlPointDuplicate", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ControlPointDuplicateKt {
    private static ImageVector _controlPointDuplicate;

    public static final ImageVector getControlPointDuplicate(Icons.Rounded rounded) {
        ImageVector imageVector = _controlPointDuplicate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ControlPointDuplicate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 8.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.lineTo(16.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.58f, 1.4f, -4.83f, 3.48f, -6.04f);
        pathBuilderA.curveToRelative(0.32f, -0.19f, 0.53f, -0.51f, 0.53f, -0.88f);
        pathBuilderA.curveToRelative(0.0f, -0.77f, -0.84f, -1.25f, -1.51f, -0.86f);
        pathBuilderA.curveTo(1.82f, 5.78f, 0.0f, 8.68f, 0.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.82f, 6.22f, 4.5f, 7.78f);
        pathBuilderA.curveToRelative(0.67f, 0.39f, 1.51f, -0.09f, 1.51f, -0.86f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, -0.21f, -0.69f, -0.53f, -0.88f);
        pathBuilderA.curveTo(3.4f, 16.83f, 2.0f, 14.58f, 2.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 3.0f);
        pathBuilderA.curveToRelative(-4.96f, 0.0f, -9.0f, 4.04f, -9.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(4.04f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(9.0f, -4.04f, 9.0f, -9.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.04f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 19.0f);
        pathBuilderA.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, 3.14f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -3.14f, 7.0f, -7.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _controlPointDuplicate = imageVectorBuild;
        return imageVectorBuild;
    }
}
