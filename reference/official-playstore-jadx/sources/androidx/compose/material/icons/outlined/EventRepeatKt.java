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
import v.c;
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_eventRepeat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EventRepeat", "Landroidx/compose/material/icons/Icons$Outlined;", "getEventRepeat", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EventRepeatKt {
    private static ImageVector _eventRepeat;

    public static final ImageVector getEventRepeat(Icons.Outlined outlined) {
        ImageVector imageVector = _eventRepeat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.EventRepeat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(21.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.C(pathBuilderA, -1.0f, 2.0f, -2.0f, 2.0f);
        a.m(pathBuilderA, 8.0f, 2.0f, 6.0f, 2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 4.0f, 3.0f, 4.9f, 3.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        d.y(pathBuilderA, 7.0f, -2.0f, 5.0f, 10.0f);
        y.a.q(pathBuilderA, 14.0f, 2.0f, 21.0f);
        c.B(pathBuilderA, 19.0f, 8.0f, 5.0f, 6.0f);
        c.D(pathBuilderA, 14.0f, 8.0f, 15.64f, 20.0f);
        pathBuilderA.curveToRelative(0.43f, 1.45f, 1.77f, 2.5f, 3.36f, 2.5f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.57f, -3.5f, -3.5f, -3.5f);
        pathBuilderA.curveToRelative(-0.95f, 0.0f, -1.82f, 0.38f, -2.45f, 1.0f);
        b.o(pathBuilderA, 1.45f, 0.0f, 18.0f, -4.0f);
        d.D(pathBuilderA, -4.0f, 1.5f, 0.0f, 1.43f);
        pathBuilderA.curveTo(16.4f, 14.55f, 17.64f, 14.0f, 19.0f, 14.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderA.curveToRelative(-2.42f, 0.0f, -4.44f, -1.72f, -4.9f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 15.64f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _eventRepeat = imageVectorBuild;
        return imageVectorBuild;
    }
}
