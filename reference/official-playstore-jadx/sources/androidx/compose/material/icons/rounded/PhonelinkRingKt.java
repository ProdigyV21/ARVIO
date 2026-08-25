package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phonelinkRing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhonelinkRing", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhonelinkRing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhonelinkRingKt {
    private static ImageVector _phonelinkRing;

    public static final ImageVector getPhonelinkRing(Icons.Rounded rounded) {
        ImageVector imageVector = _phonelinkRing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhonelinkRing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.0f, 1.0f, 4.0f, 1.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(10.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(16.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 14.0f, 20.0f, 4.0f, 20.0f);
        b.g(pathBuilderJ, 4.0f, 4.0f, 10.0f, 16.0f);
        pathBuilderJ.moveTo(20.63f, 8.26f);
        pathBuilderJ.curveToRelative(-0.26f, -0.32f, -0.74f, -0.36f, -1.04f, -0.06f);
        pathBuilderJ.lineToRelative(-0.03f, 0.03f);
        pathBuilderJ.curveToRelative(-0.25f, 0.25f, -0.26f, 0.65f, -0.05f, 0.93f);
        pathBuilderJ.curveToRelative(1.26f, 1.64f, 1.25f, 3.87f, -0.02f, 5.57f);
        pathBuilderJ.curveToRelative(-0.21f, 0.28f, -0.19f, 0.67f, 0.05f, 0.92f);
        pathBuilderJ.lineToRelative(0.05f, 0.05f);
        pathBuilderJ.curveToRelative(0.29f, 0.29f, 0.76f, 0.26f, 1.03f, -0.05f);
        pathBuilderJ.curveToRelative(1.8f, -2.13f, 1.8f, -5.19f, 0.01f, -7.39f);
        a.z(pathBuilderJ, 17.42f, 10.37f, -0.06f, 0.06f);
        pathBuilderJ.curveToRelative(-0.2f, 0.2f, -0.26f, 0.5f, -0.15f, 0.76f);
        pathBuilderJ.curveToRelative(0.21f, 0.49f, 0.21f, 1.03f, 0.0f, 1.52f);
        pathBuilderJ.curveToRelative(-0.11f, 0.26f, -0.05f, 0.56f, 0.15f, 0.76f);
        pathBuilderJ.lineToRelative(0.08f, 0.08f);
        pathBuilderJ.curveToRelative(0.32f, 0.32f, 0.87f, 0.25f, 1.09f, -0.15f);
        pathBuilderJ.curveToRelative(0.49f, -0.89f, 0.49f, -1.94f, -0.01f, -2.86f);
        pathBuilderJ.curveToRelative(-0.22f, -0.42f, -0.77f, -0.5f, -1.1f, -0.17f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phonelinkRing = imageVectorBuild;
        return imageVectorBuild;
    }
}
