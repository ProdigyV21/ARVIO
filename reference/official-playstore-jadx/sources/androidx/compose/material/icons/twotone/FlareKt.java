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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flare", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFlare", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlareKt {
    private static ImageVector _flare;

    public static final ImageVector getFlare(Icons.TwoTone twoTone) {
        ImageVector imageVector = _flare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Flare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(5.644f, 7.05f, 7.05f, 5.645f);
        b.D(pathBuilderJ, 2.123f, 2.122f, -1.408f, 1.407f);
        a.C(pathBuilderJ, 11.0f, 1.0f, 2.0f, 6.0f);
        f.x(pathBuilderJ, -2.0f, 16.242f, 14.834f);
        pathBuilderJ.lineToRelative(2.12f, 2.12f);
        b.D(pathBuilderJ, -1.406f, 1.408f, -2.12f, -2.12f);
        pathBuilderJ.moveTo(14.834f, 7.76f);
        pathBuilderJ.lineToRelative(2.12f, -2.123f);
        b.D(pathBuilderJ, 1.41f, 1.407f, -2.123f, 2.122f);
        pathBuilderJ.moveTo(9.166f, 16.242f);
        pathBuilderJ.lineToRelative(-2.122f, 2.12f);
        b.D(pathBuilderJ, -1.407f, -1.406f, 2.122f, -2.122f);
        pathBuilderJ.moveTo(12.0f, 9.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        b.w(pathBuilderJ, 11.0f, 17.0f, 2.0f, 6.0f);
        e.r(pathBuilderJ, -2.0f, 1.0f, 11.0f, 6.0f);
        y.a.n(pathBuilderJ, 2.0f, 1.0f, 13.0f);
        a.C(pathBuilderJ, 17.0f, 11.0f, 6.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ, -6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flare = imageVectorBuild;
        return imageVectorBuild;
    }
}
