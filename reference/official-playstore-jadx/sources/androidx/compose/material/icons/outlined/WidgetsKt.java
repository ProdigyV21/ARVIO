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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_widgets", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Widgets", "Landroidx/compose/material/icons/Icons$Outlined;", "getWidgets", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WidgetsKt {
    private static ImageVector _widgets;

    public static final ImageVector getWidgets(Icons.Outlined outlined) {
        ImageVector imageVector = _widgets;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Widgets", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.66f, 4.52f, 2.83f, 2.83f);
        pathBuilderR.lineToRelative(-2.83f, 2.83f);
        pathBuilderR.lineToRelative(-2.83f, -2.83f);
        pathBuilderR.lineToRelative(2.83f, -2.83f);
        pathBuilderR.moveTo(9.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        pathBuilderR.lineTo(5.0f, 9.0f);
        pathBuilderR.lineTo(5.0f, 5.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.moveToRelative(10.0f, 10.0f);
        pathBuilderR.verticalLineToRelative(4.0f);
        e.g(pathBuilderR, -4.0f, -4.0f, 4.0f);
        pathBuilderR.moveTo(9.0f, 15.0f);
        d.r(pathBuilderR, 4.0f, 5.0f, 19.0f, -4.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.moveToRelative(7.66f, -13.31f);
        pathBuilderR.lineTo(11.0f, 7.34f);
        pathBuilderR.lineTo(16.66f, 13.0f);
        b.D(pathBuilderR, 5.66f, -5.66f, -5.66f, -5.65f);
        pathBuilderR.moveTo(11.0f, 3.0f);
        a.h(pathBuilderR, 3.0f, 3.0f, 8.0f, 8.0f);
        c.z(pathBuilderR, 11.0f, 3.0f, 21.0f, 13.0f);
        y.a.r(pathBuilderR, -8.0f, 8.0f, 8.0f, -8.0f);
        pathBuilderR.moveTo(11.0f, 13.0f);
        a.h(pathBuilderR, 3.0f, 13.0f, 8.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderR, -8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _widgets = imageVectorBuild;
        return imageVectorBuild;
    }
}
