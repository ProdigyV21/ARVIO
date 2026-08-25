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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearbyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearbyOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getNearbyOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearbyOffKt {
    private static ImageVector _nearbyOff;

    public static final ImageVector getNearbyOff(Icons.Sharp sharp) {
        ImageVector imageVector = _nearbyOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NearbyOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.82f, 12.01f, 18.83f, 16.0f);
        pathBuilderJ.lineToRelative(-1.81f, -1.81f);
        pathBuilderJ.lineTo(19.2f, 12.0f);
        pathBuilderJ.lineTo(12.0f, 4.8f);
        pathBuilderJ.lineTo(9.81f, 6.99f);
        pathBuilderJ.lineTo(8.0f, 5.17f);
        d.C(pathBuilderJ, 3.99f, -3.99f, 22.82f, 12.01f);
        pathBuilderJ.moveTo(21.19f, 21.19f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineTo(16.0f, 18.83f);
        pathBuilderJ.lineToRelative(-3.99f, 3.99f);
        pathBuilderJ.lineTo(1.18f, 11.99f);
        pathBuilderJ.lineTo(5.17f, 8.0f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        b.y(pathBuilderJ, 2.8f, 2.81f, 21.19f, 21.19f);
        pathBuilderJ.moveTo(14.19f, 17.02f);
        pathBuilderJ.lineToRelative(-1.39f, -1.39f);
        pathBuilderJ.lineToRelative(-0.8f, 0.8f);
        pathBuilderJ.lineTo(7.58f, 12.0f);
        pathBuilderJ.lineToRelative(0.8f, -0.8f);
        pathBuilderJ.lineToRelative(-1.4f, -1.39f);
        pathBuilderJ.lineTo(4.8f, 12.0f);
        d.C(pathBuilderJ, 7.2f, 7.2f, 14.19f, 17.02f);
        pathBuilderJ.moveTo(16.42f, 12.0f);
        pathBuilderJ.lineTo(12.0f, 7.58f);
        pathBuilderJ.lineToRelative(-0.8f, 0.8f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 4.42f, 4.42f, 16.42f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearbyOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
