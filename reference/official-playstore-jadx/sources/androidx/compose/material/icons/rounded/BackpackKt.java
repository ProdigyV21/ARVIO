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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_backpack", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Backpack", "Landroidx/compose/material/icons/Icons$Rounded;", "getBackpack", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackpackKt {
    private static ImageVector _backpack;

    public static final ImageVector getBackpack(Icons.Rounded rounded) {
        ImageVector imageVector = _backpack;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Backpack", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(20.0f, 8.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderI.horizontalLineTo(6.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderI.verticalLineTo(8.0f);
        pathBuilderI.curveToRelative(0.0f, -1.86f, 1.28f, -3.41f, 3.0f, -3.86f);
        pathBuilderI.verticalLineTo(3.5f);
        pathBuilderI.curveTo(7.0f, 2.67f, 7.67f, 2.0f, 8.5f, 2.0f);
        pathBuilderI.horizontalLineToRelative(0.0f);
        pathBuilderI.curveTo(9.33f, 2.0f, 10.0f, 2.67f, 10.0f, 3.5f);
        c.i(pathBuilderI, 4.0f, 4.0f, 3.5f);
        pathBuilderI.curveTo(14.0f, 2.67f, 14.67f, 2.0f, 15.5f, 2.0f);
        pathBuilderI.horizontalLineToRelative(0.0f);
        pathBuilderI.curveTo(16.33f, 2.0f, 17.0f, 2.67f, 17.0f, 3.5f);
        pathBuilderI.verticalLineToRelative(0.64f);
        pathBuilderI.curveTo(18.72f, 4.59f, 20.0f, 6.14f, 20.0f, 8.0f);
        a.p(pathBuilderI, 6.0f, 13.0f, 6.0f, 13.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(9.0f);
        pathBuilderI.verticalLineToRelative(1.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.horizontalLineToRelative(0.0f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.verticalLineToRelative(-2.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.horizontalLineTo(7.0f);
        pathBuilderI.curveTo(6.45f, 12.0f, 6.0f, 12.45f, 6.0f, 13.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _backpack = imageVectorBuild;
        return imageVectorBuild;
    }
}
