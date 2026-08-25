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
import v.b;
import v.c;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoCameraFront", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoCameraFront", "Landroidx/compose/material/icons/Icons$Sharp;", "getPhotoCameraFront", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoCameraFrontKt {
    private static ImageVector _photoCameraFront;

    public static final ImageVector getPhotoCameraFront(Icons.Sharp sharp) {
        ImageVector imageVector = _photoCameraFront;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PhotoCameraFront", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = d.n(16.83f, 5.0f, 15.0f, 3.0f, 9.0f);
        b.m(pathBuilderN, 7.17f, 5.0f, 2.0f, 16.0f);
        a.o(pathBuilderN, 20.0f, 5.0f, 16.83f);
        pathBuilderN.moveTo(12.0f, 9.0f);
        pathBuilderN.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderN.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderN.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderN.curveTo(10.0f, 9.9f, 10.9f, 9.0f, 12.0f, 9.0f);
        c.C(pathBuilderN, 16.0f, 17.0f, 8.0f, -0.57f);
        pathBuilderN.curveToRelative(0.0f, -0.81f, 0.48f, -1.53f, 1.22f, -1.85f);
        pathBuilderN.curveTo(10.07f, 14.21f, 11.01f, 14.0f, 12.0f, 14.0f);
        pathBuilderN.reflectiveCurveToRelative(1.93f, 0.21f, 2.78f, 0.58f);
        pathBuilderN.curveTo(15.52f, 14.9f, 16.0f, 15.62f, 16.0f, 16.43f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderN, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoCameraFront = imageVectorBuild;
        return imageVectorBuild;
    }
}
