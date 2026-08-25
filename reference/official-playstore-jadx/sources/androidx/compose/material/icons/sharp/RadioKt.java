package androidx.compose.material.icons.sharp;

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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_radio", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Radio", "Landroidx/compose/material/icons/Icons$Sharp;", "getRadio", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RadioKt {
    private static ImageVector _radio;

    public static final ImageVector getRadio(Icons.Sharp sharp) {
        ImageVector imageVector = _radio;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Radio", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(2.0f, 6.67f, 2.0f, 22.0f, 20.0f);
        pathBuilderT.lineTo(22.0f, 6.0f);
        pathBuilderT.lineTo(8.3f, 6.0f);
        pathBuilderT.lineToRelative(8.26f, -3.34f);
        b.y(pathBuilderT, 15.88f, 1.0f, 2.0f, 6.67f);
        pathBuilderT.moveTo(7.0f, 20.0f);
        pathBuilderT.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderT.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderT.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderT.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        b.w(pathBuilderT, 20.0f, 12.0f, -2.0f, -2.0f);
        c.q(pathBuilderT, -2.0f, 2.0f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderT, 4.0f, 8.0f, 16.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _radio = imageVectorBuild;
        return imageVectorBuild;
    }
}
