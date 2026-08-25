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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smartButton", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmartButton", "Landroidx/compose/material/icons/Icons$Rounded;", "getSmartButton", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmartButtonKt {
    private static ImageVector _smartButton;

    public static final ImageVector getSmartButton(Icons.Rounded rounded) {
        ImageVector imageVector = _smartButton;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SmartButton", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(22.0f, 9.0f, 6.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        e.C(pathBuilderI, -1.0f, 0.0f, -2.0f, 1.0f);
        c.o(pathBuilderI, 9.0f, 4.0f, 6.0f, 6.0f);
        pathBuilderI.verticalLineToRelative(2.0f);
        pathBuilderI.horizontalLineTo(4.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderI.verticalLineTo(9.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveTo(21.1f, 7.0f, 22.0f, 7.9f, 22.0f, 9.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(14.04f, 17.99f);
        pathBuilderI.curveToRelative(0.18f, 0.39f, 0.73f, 0.39f, 0.91f, 0.0f);
        pathBuilderI.lineToRelative(0.63f, -1.4f);
        pathBuilderI.lineToRelative(1.4f, -0.63f);
        pathBuilderI.curveToRelative(0.39f, -0.18f, 0.39f, -0.73f, 0.0f, -0.91f);
        pathBuilderI.lineToRelative(-1.4f, -0.63f);
        pathBuilderI.lineToRelative(-0.63f, -1.4f);
        pathBuilderI.curveToRelative(-0.18f, -0.39f, -0.73f, -0.39f, -0.91f, 0.0f);
        pathBuilderI.lineToRelative(-0.63f, 1.4f);
        pathBuilderI.lineToRelative(-1.4f, 0.63f);
        pathBuilderI.curveToRelative(-0.39f, 0.18f, -0.39f, 0.73f, 0.0f, 0.91f);
        d.C(pathBuilderI, 1.4f, 0.63f, 14.04f, 17.99f);
        pathBuilderI.moveTo(16.74f, 13.43f);
        pathBuilderI.curveToRelative(0.1f, 0.22f, 0.42f, 0.22f, 0.52f, 0.0f);
        pathBuilderI.lineToRelative(0.36f, -0.8f);
        pathBuilderI.lineToRelative(0.8f, -0.36f);
        pathBuilderI.curveToRelative(0.22f, -0.1f, 0.22f, -0.42f, 0.0f, -0.52f);
        pathBuilderI.lineToRelative(-0.8f, -0.36f);
        pathBuilderI.lineToRelative(-0.36f, -0.8f);
        pathBuilderI.curveToRelative(-0.1f, -0.22f, -0.42f, -0.22f, -0.52f, 0.0f);
        pathBuilderI.lineToRelative(-0.36f, 0.8f);
        pathBuilderI.lineToRelative(-0.8f, 0.36f);
        pathBuilderI.curveToRelative(-0.22f, 0.1f, -0.22f, 0.42f, 0.0f, 0.52f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderI, 0.8f, 0.36f, 16.74f, 13.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smartButton = imageVectorBuild;
        return imageVectorBuild;
    }
}
