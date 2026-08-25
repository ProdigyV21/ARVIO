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
import v.a;
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smartButton", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmartButton", "Landroidx/compose/material/icons/Icons$Outlined;", "getSmartButton", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmartButtonKt {
    private static ImageVector _smartButton;

    public static final ImageVector getSmartButton(Icons.Outlined outlined) {
        ImageVector imageVector = _smartButton;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SmartButton", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        a.z(pathBuilderI, 14.5f, 19.0f, 1.09f, -2.41f);
        pathBuilderI.lineTo(18.0f, 15.5f);
        pathBuilderI.lineToRelative(-2.41f, -1.09f);
        pathBuilderI.lineTo(14.5f, 12.0f);
        pathBuilderI.lineToRelative(-1.09f, 2.41f);
        pathBuilderI.lineTo(11.0f, 15.5f);
        d.C(pathBuilderI, 2.41f, 1.09f, 14.5f, 19.0f);
        pathBuilderI.moveTo(17.0f, 14.0f);
        pathBuilderI.lineToRelative(0.62f, -1.38f);
        pathBuilderI.lineTo(19.0f, 12.0f);
        pathBuilderI.lineToRelative(-1.38f, -0.62f);
        pathBuilderI.lineTo(17.0f, 10.0f);
        pathBuilderI.lineToRelative(-0.62f, 1.38f);
        pathBuilderI.lineTo(15.0f, 12.0f);
        d.C(pathBuilderI, 1.38f, 0.62f, 17.0f, 14.0f);
        pathBuilderI.moveTo(14.5f, 19.0f);
        pathBuilderI.lineToRelative(1.09f, -2.41f);
        pathBuilderI.lineTo(18.0f, 15.5f);
        pathBuilderI.lineToRelative(-2.41f, -1.09f);
        pathBuilderI.lineTo(14.5f, 12.0f);
        pathBuilderI.lineToRelative(-1.09f, 2.41f);
        pathBuilderI.lineTo(11.0f, 15.5f);
        d.C(pathBuilderI, 2.41f, 1.09f, 14.5f, 19.0f);
        pathBuilderI.moveTo(17.0f, 14.0f);
        pathBuilderI.lineToRelative(0.62f, -1.38f);
        pathBuilderI.lineTo(19.0f, 12.0f);
        pathBuilderI.lineToRelative(-1.38f, -0.62f);
        pathBuilderI.lineTo(17.0f, 10.0f);
        pathBuilderI.lineToRelative(-0.62f, 1.38f);
        pathBuilderI.lineTo(15.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderI, 1.38f, 0.62f, 17.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smartButton = imageVectorBuild;
        return imageVectorBuild;
    }
}
