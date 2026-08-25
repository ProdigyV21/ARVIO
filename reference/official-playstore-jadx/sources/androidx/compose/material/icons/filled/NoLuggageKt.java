package androidx.compose.material.icons.filled;

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
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noLuggage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoLuggage", "Landroidx/compose/material/icons/Icons$Filled;", "getNoLuggage", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoLuggageKt {
    private static ImageVector _noLuggage;

    public static final ImageVector getNoLuggage(Icons.Filled filled) {
        ImageVector imageVector = _noLuggage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoLuggage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(12.75f, 9.0f, 0.92f, 1.75f, 1.75f);
        a0.a.s(pathBuilderG, 9.0f, 16.0f, 4.17f);
        pathBuilderG.lineToRelative(3.0f, 3.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.horizontalLineToRelative(-2.0f);
        pathBuilderG.verticalLineTo(3.0f);
        pathBuilderG.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderG.horizontalLineToRelative(-4.0f);
        pathBuilderG.curveTo(9.45f, 2.0f, 9.0f, 2.45f, 9.0f, 3.0f);
        a0.a.o(pathBuilderG, 3.0f, 8.83f, 3.0f, 3.0f);
        a.v(pathBuilderG, 12.75f, 10.5f, 3.5f, 3.0f);
        f.k(pathBuilderG, 6.0f, -3.0f, 3.5f);
        pathBuilderG.moveTo(21.19f, 21.19f);
        pathBuilderG.lineTo(2.81f, 2.81f);
        pathBuilderG.lineTo(1.39f, 4.22f);
        pathBuilderG.lineToRelative(3.63f, 3.63f);
        pathBuilderG.curveTo(5.02f, 7.9f, 5.0f, 7.95f, 5.0f, 8.0f);
        pathBuilderG.verticalLineToRelative(11.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderG.horizontalLineToRelative(6.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderG.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderG.curveToRelative(0.34f, 0.0f, 0.65f, -0.09f, 0.93f, -0.24f);
        d.C(pathBuilderG, 1.85f, 1.85f, 21.19f, 21.19f);
        pathBuilderG.moveTo(8.0f, 18.0f);
        a0.a.l(pathBuilderG, -7.17f, 1.5f, 1.5f, 18.0f);
        a.v(pathBuilderG, 8.0f, 12.75f, 18.0f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.h(pathBuilderG, -3.92f, 1.5f, 1.5f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noLuggage = imageVectorBuild;
        return imageVectorBuild;
    }
}
