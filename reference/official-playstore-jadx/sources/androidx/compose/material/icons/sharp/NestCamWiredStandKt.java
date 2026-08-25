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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nestCamWiredStand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NestCamWiredStand", "Landroidx/compose/material/icons/Icons$Sharp;", "getNestCamWiredStand", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NestCamWiredStandKt {
    private static ImageVector _nestCamWiredStand;

    public static final ImageVector getNestCamWiredStand(Icons.Sharp sharp) {
        ImageVector imageVector = _nestCamWiredStand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NestCamWiredStand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.0f, 0.85f, 11.98f, 1.4f);
        pathBuilderJ.curveTo(8.95f, 1.7f, 6.37f, 4.0f, 6.04f, 7.03f);
        pathBuilderJ.curveToRelative(-0.39f, 3.57f, 2.2f, 6.69f, 5.68f, 7.04f);
        pathBuilderJ.lineToRelative(1.9f, 0.19f);
        pathBuilderJ.lineToRelative(-0.56f, 0.85f);
        pathBuilderJ.curveToRelative(-0.88f, -0.19f, -1.83f, -0.18f, -2.85f, 0.25f);
        pathBuilderJ.curveTo(8.21f, 16.21f, 7.0f, 18.25f, 7.0f, 20.41f);
        b.A(pathBuilderJ, 7.0f, 23.0f, 10.0f, -3.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.67f, -0.83f, -3.15f, -2.09f, -4.06f);
        pathBuilderJ.lineToRelative(0.97f, -1.45f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderJ, 18.0f, 14.72f, 0.85f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nestCamWiredStand = imageVectorBuild;
        return imageVectorBuild;
    }
}
