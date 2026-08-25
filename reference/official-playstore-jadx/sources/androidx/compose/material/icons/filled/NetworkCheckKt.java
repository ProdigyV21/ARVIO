package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_networkCheck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NetworkCheck", "Landroidx/compose/material/icons/Icons$Filled;", "getNetworkCheck", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NetworkCheckKt {
    private static ImageVector _networkCheck;

    public static final ImageVector getNetworkCheck(Icons.Filled filled) {
        ImageVector imageVector = _networkCheck;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NetworkCheck", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.9f, 5.0f);
        pathBuilderA.curveToRelative(-0.17f, 0.0f, -0.32f, 0.09f, -0.41f, 0.23f);
        pathBuilderA.lineToRelative(-0.07f, 0.15f);
        pathBuilderA.lineToRelative(-5.18f, 11.65f);
        pathBuilderA.curveToRelative(-0.16f, 0.29f, -0.26f, 0.61f, -0.26f, 0.96f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, 0.9f, 2.01f, 2.01f, 2.01f);
        pathBuilderA.curveToRelative(0.96f, 0.0f, 1.77f, -0.68f, 1.96f, -1.59f);
        pathBuilderA.lineToRelative(0.01f, -0.03f);
        pathBuilderA.lineTo(16.4f, 5.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        a.z(pathBuilderA, 1.0f, 9.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(2.88f, -2.88f, 6.79f, -4.08f, 10.53f, -3.62f);
        pathBuilderA.lineToRelative(1.19f, -2.68f);
        pathBuilderA.curveTo(9.89f, 3.84f, 4.74f, 5.27f, 1.0f, 9.0f);
        a.z(pathBuilderA, 21.0f, 11.0f, 2.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.64f, -1.64f, -3.55f, -2.82f, -5.59f, -3.57f);
        pathBuilderA.lineToRelative(-0.53f, 2.82f);
        pathBuilderA.curveToRelative(1.5f, 0.62f, 2.9f, 1.53f, 4.12f, 2.75f);
        a.z(pathBuilderA, 17.0f, 15.0f, 2.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.8f, -0.8f, -1.7f, -1.42f, -2.66f, -1.89f);
        pathBuilderA.lineToRelative(-0.55f, 2.92f);
        pathBuilderA.curveToRelative(0.42f, 0.27f, 0.83f, 0.59f, 1.21f, 0.97f);
        a.z(pathBuilderA, 5.0f, 13.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(1.13f, -1.13f, 2.56f, -1.79f, 4.03f, -2.0f);
        pathBuilderA.lineToRelative(1.28f, -2.88f);
        pathBuilderA.curveToRelative(-2.63f, -0.08f, -5.3f, 0.87f, -7.31f, 2.88f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _networkCheck = imageVectorBuild;
        return imageVectorBuild;
    }
}
