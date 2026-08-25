package androidx.compose.material.icons.sharp;

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
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pedalBike", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PedalBike", "Landroidx/compose/material/icons/Icons$Sharp;", "getPedalBike", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PedalBikeKt {
    private static ImageVector _pedalBike;

    public static final ImageVector getPedalBike(Icons.Sharp sharp) {
        ImageVector imageVector = _pedalBike;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PedalBike", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(18.18f, 10.0f, 16.0f, 4.0f, -4.0f);
        d.D(pathBuilderT, 2.0f, 2.6f, 1.46f, 4.0f);
        a0.a.n(pathBuilderT, -4.81f, -0.36f, -1.0f, 12.0f);
        c.o(pathBuilderT, 7.0f, 7.0f, 2.0f, 1.75f);
        pathBuilderT.lineToRelative(1.82f, 5.0f);
        pathBuilderT.horizontalLineTo(9.9f);
        pathBuilderT.curveToRelative(-0.44f, -2.23f, -2.31f, -3.88f, -4.65f, -3.99f);
        pathBuilderT.curveTo(2.45f, 9.87f, 0.0f, 12.2f, 0.0f, 15.0f);
        pathBuilderT.curveToRelative(0.0f, 2.8f, 2.2f, 5.0f, 5.0f, 5.0f);
        pathBuilderT.curveToRelative(2.46f, 0.0f, 4.45f, -1.69f, 4.9f, -4.0f);
        pathBuilderT.horizontalLineToRelative(4.2f);
        pathBuilderT.curveToRelative(0.44f, 2.23f, 2.31f, 3.88f, 4.65f, 3.99f);
        pathBuilderT.curveToRelative(2.8f, 0.13f, 5.25f, -2.19f, 5.25f, -5.0f);
        pathBuilderT.curveToRelative(0.0f, -2.8f, -2.2f, -5.0f, -5.0f, -5.0f);
        v.a.x(pathBuilderT, 18.18f, 7.82f, 16.0f);
        pathBuilderT.curveToRelative(-0.4f, 1.17f, -1.49f, 2.0f, -2.82f, 2.0f);
        pathBuilderT.curveToRelative(-1.68f, 0.0f, -3.0f, -1.32f, -3.0f, -3.0f);
        pathBuilderT.reflectiveCurveToRelative(1.32f, -3.0f, 3.0f, -3.0f);
        pathBuilderT.curveToRelative(1.33f, 0.0f, 2.42f, 0.83f, 2.82f, 2.0f);
        b.e(pathBuilderT, 5.0f, 2.0f, 7.82f);
        pathBuilderT.moveTo(14.1f, 14.0f);
        a0.a.n(pathBuilderT, -1.4f, -0.73f, -2.0f, 15.0f);
        pathBuilderT.curveTo(14.56f, 12.58f, 14.24f, 13.25f, 14.1f, 14.0f);
        pathBuilderT.close();
        pathBuilderT.moveTo(19.0f, 18.0f);
        pathBuilderT.curveToRelative(-1.68f, 0.0f, -3.0f, -1.32f, -3.0f, -3.0f);
        pathBuilderT.curveToRelative(0.0f, -0.93f, 0.41f, -1.73f, 1.05f, -2.28f);
        pathBuilderT.lineToRelative(0.96f, 2.64f);
        pathBuilderT.lineToRelative(1.88f, -0.68f);
        pathBuilderT.lineToRelative(-0.97f, -2.67f);
        pathBuilderT.curveToRelative(0.03f, 0.0f, 0.06f, -0.01f, 0.09f, -0.01f);
        pathBuilderT.curveToRelative(1.68f, 0.0f, 3.0f, 1.32f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderT, 20.68f, 18.0f, 19.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pedalBike = imageVectorBuild;
        return imageVectorBuild;
    }
}
