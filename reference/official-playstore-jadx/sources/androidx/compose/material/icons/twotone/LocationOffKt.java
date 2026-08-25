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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_locationOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocationOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocationOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocationOffKt {
    private static ImageVector _locationOff;

    public static final ImageVector getLocationOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _locationOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocationOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 1.06f, -0.39f, 2.32f, -1.0f, 3.62f);
        pathBuilderA.lineToRelative(1.49f, 1.49f);
        pathBuilderA.curveTo(18.37f, 12.36f, 19.0f, 10.57f, 19.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(-1.84f, 0.0f, -3.5f, 0.71f, -4.75f, 1.86f);
        pathBuilderA.lineToRelative(1.43f, 1.43f);
        pathBuilderA.curveTo(9.56f, 4.5f, 10.72f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 6.5f);
        pathBuilderA.curveToRelative(-0.59f, 0.0f, -1.13f, 0.21f, -1.56f, 0.56f);
        pathBuilderA.lineToRelative(3.5f, 3.5f);
        pathBuilderA.curveToRelative(0.35f, -0.43f, 0.56f, -0.97f, 0.56f, -1.56f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        a.p(pathBuilderA, 3.41f, 2.86f, 2.0f, 4.27f);
        pathBuilderA.lineToRelative(3.18f, 3.18f);
        pathBuilderA.curveTo(5.07f, 7.95f, 5.0f, 8.47f, 5.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 5.25f, 7.0f, 13.0f, 7.0f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(1.67f, -1.85f, 3.38f, -4.35f);
        pathBuilderA.lineTo(18.73f, 21.0f);
        d.C(pathBuilderA, 1.41f, -1.41f, 3.41f, 2.86f);
        pathBuilderA.moveTo(12.0f, 18.88f);
        pathBuilderA.curveToRelative(-2.01f, -2.58f, -4.8f, -6.74f, -4.98f, -9.59f);
        pathBuilderA.lineToRelative(6.92f, 6.92f);
        pathBuilderA.curveToRelative(-0.65f, 0.98f, -1.33f, 1.89f, -1.94f, 2.67f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _locationOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
