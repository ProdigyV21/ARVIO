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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_designServices", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DesignServices", "Landroidx/compose/material/icons/Icons$Outlined;", "getDesignServices", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DesignServicesKt {
    private static ImageVector _designServices;

    public static final ImageVector getDesignServices(Icons.Outlined outlined) {
        ImageVector imageVector = _designServices;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DesignServices", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.97f, 7.27f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(-2.83f, -2.83f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-4.49f, 4.49f);
        pathBuilderA.lineTo(8.35f, 3.63f);
        pathBuilderA.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderA.lineToRelative(-1.9f, 1.9f);
        pathBuilderA.curveToRelative(-0.78f, 0.78f, -0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderA.lineToRelative(3.89f, 3.89f);
        b.n(pathBuilderA, 3.0f, 16.76f, 21.0f, 4.24f);
        pathBuilderA.lineToRelative(4.52f, -4.52f);
        pathBuilderA.lineToRelative(3.89f, 3.89f);
        pathBuilderA.curveToRelative(0.95f, 0.95f, 2.23f, 0.6f, 2.83f, 0.0f);
        pathBuilderA.lineToRelative(1.9f, -1.9f);
        pathBuilderA.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        d.C(pathBuilderA, -3.89f, -3.89f, 20.97f, 7.27f);
        pathBuilderA.moveTo(5.04f, 6.94f);
        pathBuilderA.lineToRelative(1.89f, -1.9f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA.lineToRelative(1.27f, 1.27f);
        pathBuilderA.lineTo(7.02f, 7.5f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.lineToRelative(1.19f, -1.19f);
        pathBuilderA.lineToRelative(1.2f, 1.2f);
        d.C(pathBuilderA, -1.9f, 1.9f, 5.04f, 6.94f);
        pathBuilderA.moveTo(16.27f, 14.38f);
        pathBuilderA.lineToRelative(-1.19f, 1.19f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.lineToRelative(1.19f, -1.19f);
        pathBuilderA.lineToRelative(1.27f, 1.27f);
        pathBuilderA.lineToRelative(-1.9f, 1.9f);
        pathBuilderA.lineToRelative(-3.89f, -3.89f);
        d.C(pathBuilderA, 1.9f, -1.9f, 16.27f, 14.38f);
        a.A(pathBuilderA, 6.41f, 19.0f, 5.0f, -1.41f);
        pathBuilderA.lineToRelative(9.61f, -9.61f);
        pathBuilderA.lineToRelative(1.3f, 1.3f);
        d.C(pathBuilderA, 0.11f, 0.11f, 6.41f, 19.0f);
        pathBuilderA.moveTo(16.02f, 6.56f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, -1.41f, 1.41f, 16.02f, 6.56f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _designServices = imageVectorBuild;
        return imageVectorBuild;
    }
}
