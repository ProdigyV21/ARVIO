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
import v.b;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_editCalendar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EditCalendar", "Landroidx/compose/material/icons/Icons$Filled;", "getEditCalendar", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditCalendarKt {
    private static ImageVector _editCalendar;

    public static final ImageVector getEditCalendar(Icons.Filled filled) {
        ImageVector imageVector = _editCalendar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.EditCalendar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(12.0f, 22.0f, 5.0f);
        pathBuilderQ.curveToRelative(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.lineTo(3.01f, 6.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.1f, 0.88f, -2.0f, 1.99f, -2.0f);
        w.a.C(pathBuilderQ, 1.0f, 2.0f, 2.0f, 2.0f);
        w.a.C(pathBuilderQ, 8.0f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(1.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        f.u(pathBuilderQ, 6.0f, -2.0f, -2.0f, 5.0f);
        a.t(pathBuilderQ, 10.0f, 7.0f, 22.0f);
        pathBuilderQ.moveTo(22.13f, 16.99f);
        pathBuilderQ.lineToRelative(0.71f, -0.71f);
        pathBuilderQ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderQ.lineToRelative(-0.71f, -0.71f);
        pathBuilderQ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        d.C(pathBuilderQ, -0.71f, 0.71f, 22.13f, 16.99f);
        pathBuilderQ.moveTo(21.42f, 17.7f);
        e.h(pathBuilderQ, -5.3f, 5.3f, 14.0f, -2.12f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ, 5.3f, -5.3f, 21.42f, 17.7f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _editCalendar = imageVectorBuild;
        return imageVectorBuild;
    }
}
