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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directions", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Directions", "Landroidx/compose/material/icons/Icons$Outlined;", "getDirections", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsKt {
    private static ImageVector _directions;

    public static final ImageVector getDirections(Icons.Outlined outlined) {
        ImageVector imageVector = _directions;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Directions", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.43f, 10.59f, -9.01f, -9.01f);
        pathBuilderR.curveToRelative(-0.75f, -0.75f, -2.07f, -0.76f, -2.83f, 0.0f);
        pathBuilderR.lineToRelative(-9.0f, 9.0f);
        pathBuilderR.curveToRelative(-0.78f, 0.78f, -0.78f, 2.04f, 0.0f, 2.82f);
        pathBuilderR.lineToRelative(9.0f, 9.0f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 0.9f, 0.58f, 1.41f, 0.58f);
        pathBuilderR.curveToRelative(0.51f, 0.0f, 1.02f, -0.19f, 1.41f, -0.58f);
        pathBuilderR.lineToRelative(8.99f, -8.99f);
        pathBuilderR.curveToRelative(0.79f, -0.76f, 0.8f, -2.02f, 0.03f, -2.82f);
        a.z(pathBuilderR, 12.01f, 20.99f, -9.0f, -9.0f);
        pathBuilderR.lineToRelative(9.0f, -9.0f);
        b.D(pathBuilderR, 9.0f, 9.0f, -9.0f, 9.0f);
        c.m(pathBuilderR, 8.0f, 11.0f, 4.0f, 2.0f);
        e.l(pathBuilderR, -3.0f, 4.0f, 2.5f);
        pathBuilderR.lineToRelative(3.5f, -3.5f);
        pathBuilderR.lineTo(14.0f, 7.5f);
        pathBuilderR.lineTo(14.0f, 10.0f);
        pathBuilderR.lineTo(9.0f, 10.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directions = imageVectorBuild;
        return imageVectorBuild;
    }
}
