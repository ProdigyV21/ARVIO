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
import v.b;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hotelClass", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HotelClass", "Landroidx/compose/material/icons/Icons$Outlined;", "getHotelClass", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HotelClassKt {
    private static ImageVector _hotelClass;

    public static final ImageVector getHotelClass(Icons.Outlined outlined) {
        ImageVector imageVector = _hotelClass;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.HotelClass", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(11.0f, 8.89f, 11.94f, 12.0f, 2.82f);
        pathBuilderT.lineToRelative(-2.27f, 1.62f);
        pathBuilderT.lineToRelative(0.93f, 3.01f);
        pathBuilderT.lineTo(11.0f, 14.79f);
        pathBuilderT.lineToRelative(-2.42f, 1.84f);
        pathBuilderT.lineToRelative(0.93f, -3.01f);
        pathBuilderT.lineTo(7.24f, 12.0f);
        d.q(pathBuilderT, 2.82f, 11.0f, 8.89f);
        pathBuilderT.moveTo(8.58f, 10.0f);
        pathBuilderT.horizontalLineTo(1.0f);
        pathBuilderT.lineToRelative(6.17f, 4.41f);
        pathBuilderT.lineTo(4.83f, 22.0f);
        pathBuilderT.lineTo(11.0f, 17.31f);
        pathBuilderT.lineTo(17.18f, 22.0f);
        pathBuilderT.lineToRelative(-2.35f, -7.59f);
        pathBuilderT.lineTo(21.0f, 10.0f);
        pathBuilderT.horizontalLineToRelative(-7.58f);
        b.y(pathBuilderT, 11.0f, 2.0f, 8.58f, 10.0f);
        pathBuilderT.moveTo(21.36f, 22.0f);
        pathBuilderT.lineToRelative(-1.86f, -6.01f);
        pathBuilderT.lineTo(23.68f, 13.0f);
        pathBuilderT.horizontalLineToRelative(-3.44f);
        pathBuilderT.lineToRelative(-3.08f, 2.2f);
        d.C(pathBuilderT, 1.46f, 4.72f, 21.36f, 22.0f);
        pathBuilderT.moveTo(17.0f, 8.0f);
        pathBuilderT.lineToRelative(-1.82f, -6.0f);
        pathBuilderT.lineToRelative(-1.04f, 3.45f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderT, 14.91f, 8.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hotelClass = imageVectorBuild;
        return imageVectorBuild;
    }
}
