package androidx.compose.material.icons.twotone;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editCalendar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditCalendar", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEditCalendar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditCalendarKt {
    private static ImageVector _editCalendar;

    public static final ImageVector getEditCalendar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _editCalendar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EditCalendar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        PathBuilder pathBuilderK = a.k(5.0f, 10.0f, 14.0f, 2.0f, 2.0f);
        pathBuilderK.verticalLineTo(6.0f);
        pathBuilderK.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        w.a.C(pathBuilderK, -1.0f, 2.0f, -2.0f, 2.0f);
        w.a.m(pathBuilderK, 8.0f, 2.0f, 6.0f, 2.0f);
        pathBuilderK.horizontalLineTo(5.0f);
        pathBuilderK.curveTo(3.89f, 4.0f, 3.01f, 4.9f, 3.01f, 6.0f);
        pathBuilderK.lineTo(3.0f, 20.0f);
        pathBuilderK.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        d.z(pathBuilderK, 7.0f, -2.0f, 5.0f, 10.0f);
        a.C(pathBuilderK, 5.0f, 6.0f, 14.0f, 2.0f);
        c.l(pathBuilderK, 5.0f, 6.0f, 22.84f, 16.28f);
        pathBuilderK.lineToRelative(-0.71f, 0.71f);
        pathBuilderK.lineToRelative(-2.12f, -2.12f);
        pathBuilderK.lineToRelative(0.71f, -0.71f);
        pathBuilderK.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderK.lineToRelative(0.71f, 0.71f);
        pathBuilderK.curveTo(23.23f, 15.26f, 23.23f, 15.89f, 22.84f, 16.28f);
        a.z(pathBuilderK, 19.3f, 15.58f, 2.12f, 2.12f);
        e.h(pathBuilderK, -5.3f, 5.3f, 14.0f, -2.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderK, 19.3f, 15.58f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editCalendar = imageVectorBuild;
        return imageVectorBuild;
    }
}
