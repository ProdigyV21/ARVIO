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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_widgets", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Widgets", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWidgets", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WidgetsKt {
    private static ImageVector _widgets;

    public static final ImageVector getWidgets(Icons.TwoTone twoTone) {
        ImageVector imageVector = _widgets;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Widgets", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(5.0f, 5.0f, 4.0f, 4.0f);
        c.z(pathBuilderA, 5.0f, 9.0f, 15.0f, 15.0f);
        d.x(pathBuilderA, 4.0f, 4.0f, -4.0f);
        a.C(pathBuilderA, 5.0f, 15.0f, 4.0f, 4.0f);
        c.z(pathBuilderA, 5.0f, 19.0f, 16.66f, 4.52f);
        pathBuilderA.lineToRelative(-2.83f, 2.82f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 2.83f, 2.83f, 2.83f, -2.83f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.66f, 1.69f, 11.0f, 7.34f);
        pathBuilderJ.lineTo(16.66f, 13.0f);
        b.D(pathBuilderJ, 5.66f, -5.66f, -5.66f, -5.65f);
        pathBuilderJ.moveTo(13.83f, 7.34f);
        pathBuilderJ.lineToRelative(2.83f, -2.83f);
        pathBuilderJ.lineToRelative(2.83f, 2.83f);
        b.D(pathBuilderJ, -2.83f, 2.83f, -2.83f, -2.83f);
        c.m(pathBuilderJ, 3.0f, 3.0f, 8.0f, 8.0f);
        b.y(pathBuilderJ, 11.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.moveTo(9.0f, 9.0f);
        pathBuilderJ.lineTo(5.0f, 9.0f);
        b.g(pathBuilderJ, 5.0f, 5.0f, 4.0f, 4.0f);
        a.C(pathBuilderJ, 3.0f, 21.0f, 8.0f, -8.0f);
        d.f(pathBuilderJ, 3.0f, 13.0f, 8.0f);
        a.C(pathBuilderJ, 5.0f, 15.0f, 4.0f, 4.0f);
        d.f(pathBuilderJ, 5.0f, 19.0f, -4.0f);
        c.m(pathBuilderJ, 13.0f, 13.0f, 8.0f, 8.0f);
        e.D(pathBuilderJ, -8.0f, -8.0f, 19.0f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderJ, -4.0f, -4.0f, 4.0f, 4.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _widgets = imageVectorBuild;
        return imageVectorBuild;
    }
}
