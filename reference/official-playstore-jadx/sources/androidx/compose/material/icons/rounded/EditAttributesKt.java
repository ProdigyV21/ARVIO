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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editAttributes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditAttributes", "Landroidx/compose/material/icons/Icons$Rounded;", "getEditAttributes", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditAttributesKt {
    private static ImageVector _editAttributes;

    public static final ImageVector getEditAttributes(Icons.Rounded rounded) {
        ImageVector imageVector = _editAttributes;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.EditAttributes", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.63f, 7.0f, 6.37f, 7.0f);
        pathBuilderJ.curveTo(3.96f, 7.0f, 2.0f, 9.24f, 2.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.96f, 5.0f, 4.37f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(11.26f);
        pathBuilderJ.curveToRelative(2.41f, 0.0f, 4.37f, -2.24f, 4.37f, -5.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.96f, -5.0f, -4.37f, -5.0f);
        a.p(pathBuilderJ, 11.11f, 10.6f, 7.6f, 14.11f);
        pathBuilderJ.curveToRelative(-0.1f, 0.1f, -0.23f, 0.15f, -0.35f, 0.15f);
        pathBuilderJ.reflectiveCurveToRelative(-0.26f, -0.05f, -0.35f, -0.15f);
        pathBuilderJ.lineToRelative(-1.86f, -1.86f);
        pathBuilderJ.curveToRelative(-0.2f, -0.2f, -0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderJ.reflectiveCurveToRelative(0.51f, -0.2f, 0.71f, 0.0f);
        pathBuilderJ.lineToRelative(1.51f, 1.51f);
        pathBuilderJ.lineToRelative(3.16f, -3.16f);
        pathBuilderJ.curveToRelative(0.2f, -0.2f, 0.51f, -0.2f, 0.71f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, 0.17f, 0.51f, -0.02f, 0.71f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editAttributes = imageVectorBuild;
        return imageVectorBuild;
    }
}
