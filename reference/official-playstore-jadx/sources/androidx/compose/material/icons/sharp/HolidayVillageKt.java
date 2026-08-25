package androidx.compose.material.icons.sharp;

import a0.a;
import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_holidayVillage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HolidayVillage", "Landroidx/compose/material/icons/Icons$Sharp;", "getHolidayVillage", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HolidayVillageKt {
    private static ImageVector _holidayVillage;

    public static final ImageVector getHolidayVillage(Icons.Sharp sharp) {
        ImageVector imageVector = _holidayVillage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HolidayVillage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a.f(18.0f, 20.0f, 8.35f, 13.65f, 4.0f);
        b.x(pathBuilderF, -2.83f, 16.0f, 9.18f, 20.0f);
        a.t(pathBuilderF, 18.0f, 22.0f, 20.0f, 6.69f);
        pathBuilderF.lineTo(19.31f, 4.0f);
        b.x(pathBuilderF, -2.83f, 20.0f, 7.52f, 20.0f);
        v.a.x(pathBuilderF, 22.0f, 8.0f, 4.0f);
        v.b.u(pathBuilderF, -6.0f, 6.0f, 10.0f, 5.0f);
        v.b.z(pathBuilderF, -5.0f, 2.0f, 5.0f, 5.0f);
        c.x(pathBuilderF, 10.0f, 8.0f, 4.0f);
        v.a.A(pathBuilderF, 9.0f, 13.0f, 7.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderF, 2.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _holidayVillage = imageVectorBuild;
        return imageVectorBuild;
    }
}
