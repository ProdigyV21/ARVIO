package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airplanemodeInactive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirplanemodeInactive", "Landroidx/compose/material/icons/Icons$Outlined;", "getAirplanemodeInactive", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirplanemodeInactiveKt {
    private static ImageVector _airplanemodeInactive;

    public static final ImageVector getAirplanemodeInactive(Icons.Outlined outlined) {
        ImageVector imageVector = _airplanemodeInactive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AirplanemodeInactive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(10.5f, 7.67f, 3.5f);
        pathBuilderA.curveTo(10.5f, 2.67f, 11.17f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        a.k(pathBuilderA, 9.0f, 8.5f, 5.0f, 2.0f);
        d.C(pathBuilderA, -4.49f, -1.32f, 10.5f, 7.67f);
        pathBuilderA.moveTo(19.78f, 22.61f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineTo(13.5f, 13.5f);
        pathBuilderA.lineTo(9.56f, 9.56f);
        pathBuilderA.lineTo(2.81f, 2.81f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(6.38f, 6.38f);
        pathBuilderA.lineTo(2.0f, 14.0f);
        a.l(pathBuilderA, 2.0f, 8.5f, -2.5f, 19.0f);
        pathBuilderA.lineTo(8.0f, 20.5f);
        pathBuilderA.lineTo(8.0f, 22.0f);
        pathBuilderA.lineToRelative(4.0f, -1.0f);
        pathBuilderA.lineToRelative(4.0f, 1.0f);
        pathBuilderA.lineToRelative(0.0f, -1.5f);
        pathBuilderA.lineTo(13.5f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderA, -2.67f, 19.78f, 22.61f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airplanemodeInactive = imageVectorBuild;
        return imageVectorBuild;
    }
}
