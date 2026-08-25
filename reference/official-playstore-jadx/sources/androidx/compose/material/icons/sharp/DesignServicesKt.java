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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_designServices", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DesignServices", "Landroidx/compose/material/icons/Icons$Sharp;", "getDesignServices", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DesignServicesKt {
    private static ImageVector _designServices;

    public static final ImageVector getDesignServices(Icons.Sharp sharp) {
        ImageVector imageVector = _designServices;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DesignServices", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.79f, 17.06f, -5.55f, -5.55f);
        pathBuilderR.lineToRelative(1.57f, -1.57f);
        pathBuilderR.lineToRelative(-3.75f, -3.75f);
        pathBuilderR.lineToRelative(-1.57f, 1.57f);
        pathBuilderR.lineTo(6.94f, 2.21f);
        pathBuilderR.lineTo(2.21f, 6.94f);
        pathBuilderR.lineToRelative(5.55f, 5.55f);
        b.n(pathBuilderR, 3.0f, 17.25f, 21.0f, 3.75f);
        pathBuilderR.lineToRelative(4.76f, -4.76f);
        pathBuilderR.lineToRelative(5.55f, 5.55f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        y.a.n(pathBuilderR, 0.0f, 21.79f, 17.06f);
        pathBuilderR.moveTo(9.18f, 11.07f);
        pathBuilderR.lineTo(5.04f, 6.94f);
        pathBuilderR.lineToRelative(1.9f, -1.9f);
        pathBuilderR.lineToRelative(1.27f, 1.27f);
        pathBuilderR.lineTo(7.02f, 7.5f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(1.19f, -1.19f);
        d.C(pathBuilderR, 1.45f, 1.45f, 9.18f, 11.07f);
        pathBuilderR.moveTo(12.93f, 14.82f);
        pathBuilderR.lineToRelative(1.9f, -1.9f);
        pathBuilderR.lineToRelative(1.45f, 1.45f);
        pathBuilderR.lineToRelative(-1.19f, 1.19f);
        pathBuilderR.lineToRelative(1.41f, 1.41f);
        pathBuilderR.lineToRelative(1.19f, -1.19f);
        pathBuilderR.lineToRelative(1.27f, 1.27f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, -1.9f, 1.9f, 12.93f, 14.82f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(15.133f, 5.123f, 2.538f, -2.538f), 3.748f, 3.748f, -2.538f, 2.538f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _designServices = imageVectorBuild;
        return imageVectorBuild;
    }
}
