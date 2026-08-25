package androidx.compose.material.icons.outlined;

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_update", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Update", "Landroidx/compose/material/icons/Icons$Outlined;", "getUpdate", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UpdateKt {
    private static ImageVector _update;

    public static final ImageVector getUpdate(Icons.Outlined outlined) {
        ImageVector imageVector = _update;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Update", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(11.0f, 8.0f, 5.0f, 4.25f, 2.52f);
        pathBuilderG.lineToRelative(0.77f, -1.28f);
        y.a.h(pathBuilderG, -3.52f, -2.09f, 8.0f, 11.0f);
        pathBuilderG.moveTo(21.0f, 10.0f);
        pathBuilderG.verticalLineTo(3.0f);
        pathBuilderG.lineToRelative(-2.64f, 2.64f);
        pathBuilderG.curveTo(16.74f, 4.01f, 14.49f, 3.0f, 12.0f, 3.0f);
        pathBuilderG.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderG.reflectiveCurveToRelative(4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderG.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderG.horizontalLineToRelative(-2.0f);
        pathBuilderG.curveToRelative(0.0f, 3.86f, -3.14f, 7.0f, -7.0f, 7.0f);
        pathBuilderG.reflectiveCurveToRelative(-7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderG.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderG.curveToRelative(1.93f, 0.0f, 3.68f, 0.79f, 4.95f, 2.05f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderG, 14.0f, 10.0f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _update = imageVectorBuild;
        return imageVectorBuild;
    }
}
