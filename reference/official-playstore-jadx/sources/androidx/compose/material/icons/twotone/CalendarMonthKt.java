package androidx.compose.material.icons.twotone;

import a0.a;
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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_calendarMonth", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CalendarMonth", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCalendarMonth", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CalendarMonthKt {
    private static ImageVector _calendarMonth;

    public static final ImageVector getCalendarMonth(Icons.TwoTone twoTone) {
        ImageVector imageVector = _calendarMonth;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CalendarMonth", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(5.0f, 6.0f, 14.0f, 2.0f, -14.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(19.0f, 4.0f, -1.0f, 2.0f, -2.0f);
        d.B(pathBuilderU, 2.0f, 8.0f, 2.0f, 6.0f);
        pathBuilderU.verticalLineToRelative(2.0f);
        pathBuilderU.horizontalLineTo(5.0f);
        pathBuilderU.curveTo(3.89f, 4.0f, 3.01f, 4.9f, 3.01f, 6.0f);
        pathBuilderU.lineTo(3.0f, 20.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(14.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.verticalLineTo(6.0f);
        pathBuilderU.curveTo(21.0f, 4.9f, 20.1f, 4.0f, 19.0f, 4.0f);
        f.o(pathBuilderU, 19.0f, 20.0f, 5.0f, 10.0f);
        c.D(pathBuilderU, 14.0f, 20.0f, 19.0f, 8.0f);
        w.a.l(pathBuilderU, 5.0f, 6.0f, 14.0f, 8.0f);
        v.a.A(pathBuilderU, 9.0f, 14.0f, 7.0f, -2.0f);
        c.D(pathBuilderU, 2.0f, 14.0f, 13.0f, 14.0f);
        f.w(pathBuilderU, -2.0f, -2.0f, 2.0f, 14.0f);
        v.a.C(pathBuilderU, 17.0f, 14.0f, -2.0f, -2.0f);
        c.D(pathBuilderU, 2.0f, 14.0f, 9.0f, 18.0f);
        c.k(pathBuilderU, 7.0f, -2.0f, 2.0f, 18.0f);
        v.a.C(pathBuilderU, 13.0f, 18.0f, -2.0f, -2.0f);
        c.D(pathBuilderU, 2.0f, 18.0f, 17.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderU, -2.0f, -2.0f, 2.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _calendarMonth = imageVectorBuild;
        return imageVectorBuild;
    }
}
