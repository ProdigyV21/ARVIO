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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flare", "Landroidx/compose/material/icons/Icons$Outlined;", "getFlare", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlareKt {
    private static ImageVector _flare;

    public static final ImageVector getFlare(Icons.Outlined outlined) {
        ImageVector imageVector = _flare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Flare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(7.0f, 11.0f, 1.0f, 11.0f, 2.0f);
        b.B(pathBuilderS, 6.0f, -2.0f, 9.17f, 7.76f);
        pathBuilderS.lineTo(7.05f, 5.64f);
        pathBuilderS.lineTo(5.64f, 7.05f);
        b.D(pathBuilderS, 2.12f, 2.12f, 1.41f, -1.41f);
        a.C(pathBuilderS, 13.0f, 1.0f, -2.0f, 6.0f);
        d.q(pathBuilderS, 2.0f, 13.0f, 1.0f);
        pathBuilderS.moveTo(18.36f, 7.05f);
        pathBuilderS.lineToRelative(-1.41f, -1.41f);
        pathBuilderS.lineToRelative(-2.12f, 2.12f);
        b.D(pathBuilderS, 1.41f, 1.41f, 2.12f, -2.12f);
        c.m(pathBuilderS, 17.0f, 11.0f, 2.0f, 6.0f);
        e.D(pathBuilderS, -2.0f, -6.0f, 12.0f, 9.0f);
        pathBuilderS.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        a.z(pathBuilderS, 14.83f, 16.24f, 2.12f, 2.12f);
        pathBuilderS.lineToRelative(1.41f, -1.41f);
        b.D(pathBuilderS, -2.12f, -2.12f, -1.41f, 1.41f);
        pathBuilderS.moveTo(5.64f, 16.95f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        pathBuilderS.lineToRelative(2.12f, -2.12f);
        b.D(pathBuilderS, -1.41f, -1.41f, -2.12f, 2.12f);
        a.C(pathBuilderS, 11.0f, 23.0f, 2.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderS, -2.0f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flare = imageVectorBuild;
        return imageVectorBuild;
    }
}
