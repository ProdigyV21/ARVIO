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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emojiFoodBeverage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmojiFoodBeverage", "Landroidx/compose/material/icons/Icons$Filled;", "getEmojiFoodBeverage", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmojiFoodBeverageKt {
    private static ImageVector _emojiFoodBeverage;

    public static final ImageVector getEmojiFoodBeverage(Icons.Filled filled) {
        ImageVector imageVector = _emojiFoodBeverage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.EmojiFoodBeverage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(20.0f, 3.0f, 9.0f, 2.4f);
        pathBuilderA.lineToRelative(1.81f, 1.45f);
        pathBuilderA.curveTo(10.93f, 6.94f, 11.0f, 7.09f, 11.0f, 7.24f);
        pathBuilderA.verticalLineToRelative(4.26f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveTo(6.22f, 12.0f, 6.0f, 11.78f, 6.0f, 11.5f);
        pathBuilderA.verticalLineTo(7.24f);
        pathBuilderA.curveToRelative(0.0f, -0.15f, 0.07f, -0.3f, 0.19f, -0.39f);
        c.f(pathBuilderA, 8.0f, 5.4f, 3.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.curveTo(22.0f, 3.89f, 21.11f, 3.0f, 20.0f, 3.0f);
        a.u(pathBuilderA, 20.0f, 8.0f, -2.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA, 2.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(4.0f, 19.0f, 16.0f, 2.0f, -16.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emojiFoodBeverage = imageVectorBuild;
        return imageVectorBuild;
    }
}
