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
import v.b;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_locationDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocationDisabled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocationDisabled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocationDisabledKt {
    private static ImageVector _locationDisabled;

    public static final ImageVector getLocationDisabled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _locationDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocationDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(23.0f, 13.0f, -2.0f, -2.06f);
        pathBuilderA.curveToRelative(-0.46f, -4.17f, -3.77f, -7.48f, -7.94f, -7.94f);
        a.j(pathBuilderA, 1.0f, -2.0f, 2.06f);
        pathBuilderA.curveToRelative(-0.98f, 0.11f, -1.91f, 0.38f, -2.77f, 0.78f);
        pathBuilderA.lineToRelative(1.53f, 1.53f);
        pathBuilderA.curveTo(10.46f, 5.13f, 11.22f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.79f, -0.13f, 1.54f, -0.37f, 2.24f);
        pathBuilderA.lineToRelative(1.53f, 1.53f);
        pathBuilderA.curveToRelative(0.4f, -0.86f, 0.67f, -1.79f, 0.78f, -2.77f);
        v.a.x(pathBuilderA, 23.0f, 4.41f, 2.86f);
        pathBuilderA.lineTo(3.0f, 4.27f);
        pathBuilderA.lineToRelative(2.04f, 2.04f);
        pathBuilderA.curveTo(3.97f, 7.62f, 3.26f, 9.23f, 3.06f, 11.0f);
        v.a.n(pathBuilderA, 1.0f, 2.0f, 2.06f);
        pathBuilderA.curveToRelative(0.46f, 4.17f, 3.77f, 7.48f, 7.94f, 7.94f);
        a.j(pathBuilderA, 23.0f, 2.0f, -2.06f);
        pathBuilderA.curveToRelative(1.77f, -0.2f, 3.38f, -0.91f, 4.69f, -1.98f);
        pathBuilderA.lineTo(19.73f, 21.0f);
        d.C(pathBuilderA, 1.41f, -1.41f, 4.41f, 2.86f);
        pathBuilderA.moveTo(12.0f, 19.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.61f, 0.55f, -3.09f, 1.46f, -4.27f);
        pathBuilderA.lineToRelative(9.81f, 9.81f);
        pathBuilderA.curveTo(15.09f, 18.45f, 13.61f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _locationDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
