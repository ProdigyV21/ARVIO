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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_checkroom", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Checkroom", "Landroidx/compose/material/icons/Icons$Filled;", "getCheckroom", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CheckroomKt {
    private static ImageVector _checkroom;

    public static final ImageVector getCheckroom(Icons.Filled filled) {
        ImageVector imageVector = _checkroom;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Checkroom", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(21.6f, 18.2f, 13.0f, 11.75f, -0.91f);
        pathBuilderS.curveToRelative(1.65f, -0.49f, 2.8f, -2.17f, 2.43f, -4.05f);
        pathBuilderS.curveToRelative(-0.26f, -1.31f, -1.3f, -2.4f, -2.61f, -2.7f);
        pathBuilderS.curveTo(10.54f, 3.57f, 8.5f, 5.3f, 8.5f, 7.5f);
        pathBuilderS.horizontalLineToRelative(2.0f);
        pathBuilderS.curveTo(10.5f, 6.67f, 11.17f, 6.0f, 12.0f, 6.0f);
        pathBuilderS.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderS.curveToRelative(0.0f, 0.84f, -0.69f, 1.52f, -1.53f, 1.5f);
        pathBuilderS.curveTo(11.43f, 8.99f, 11.0f, 9.45f, 11.0f, 9.99f);
        pathBuilderS.verticalLineToRelative(1.76f);
        pathBuilderS.lineTo(2.4f, 18.2f);
        pathBuilderS.curveTo(1.63f, 18.78f, 2.04f, 20.0f, 3.0f, 20.0f);
        pathBuilderS.horizontalLineToRelative(9.0f);
        pathBuilderS.horizontalLineToRelative(9.0f);
        pathBuilderS.curveTo(21.96f, 20.0f, 22.37f, 18.78f, 21.6f, 18.2f);
        a.z(pathBuilderS, 6.0f, 18.0f, 6.0f, -4.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderS, 6.0f, 4.5f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _checkroom = imageVectorBuild;
        return imageVectorBuild;
    }
}
