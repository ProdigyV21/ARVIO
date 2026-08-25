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
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_templeBuddhist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TempleBuddhist", "Landroidx/compose/material/icons/Icons$Sharp;", "getTempleBuddhist", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TempleBuddhistKt {
    private static ImageVector _templeBuddhist;

    public static final ImageVector getTempleBuddhist(Icons.Sharp sharp) {
        ImageVector imageVector = _templeBuddhist;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.TempleBuddhist", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 9.02f);
        pathBuilderA.curveToRelative(0.0f, 1.09f, -0.89f, 1.98f, -1.98f, 1.98f);
        pathBuilderA.horizontalLineTo(4.98f);
        pathBuilderA.curveTo(3.89f, 11.0f, 3.0f, 10.11f, 3.0f, 9.02f);
        pathBuilderA.horizontalLineTo(1.0f);
        pathBuilderA.curveToRelative(0.0f, 1.86f, 1.28f, 3.4f, 3.0f, 3.84f);
        b.r(pathBuilderA, 22.0f, 6.0f, -5.0f, 4.0f);
        e.l(pathBuilderA, 5.0f, 6.0f, -9.14f);
        pathBuilderA.curveToRelative(0.55f, -0.14f, 3.0f, -1.04f, 3.0f, -3.86f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 21.0f, 9.02f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(6.0f, 8.86f, 10.0f, 12.0f, 8.86f);
        pathBuilderM.curveToRelative(0.55f, -0.14f, 3.0f, -1.04f, 3.0f, -3.86f);
        pathBuilderM.lineToRelative(-2.0f, 0.02f);
        pathBuilderM.curveTo(19.0f, 6.11f, 18.11f, 7.0f, 17.02f, 7.0f);
        pathBuilderM.horizontalLineTo(6.98f);
        pathBuilderM.curveTo(5.89f, 7.0f, 5.0f, 6.11f, 5.0f, 5.02f);
        pathBuilderM.horizontalLineTo(3.0f);
        pathBuilderM.curveTo(3.0f, 6.87f, 4.28f, 8.42f, 6.0f, 8.86f);
        pathBuilderM.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderM.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(v.a.r(12.0f, 1.0f, -3.75f, 5.0f), 7.5f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _templeBuddhist = imageVectorBuild;
        return imageVectorBuild;
    }
}
