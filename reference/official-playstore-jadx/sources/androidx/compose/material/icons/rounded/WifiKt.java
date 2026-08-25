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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wifi", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wifi", "Landroidx/compose/material/icons/Icons$Rounded;", "getWifi", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WifiKt {
    private static ImageVector _wifi;

    public static final ImageVector getWifi(Icons.Rounded rounded) {
        ImageVector imageVector = _wifi;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Wifi", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(2.06f, 10.06f);
        pathBuilderA.curveToRelative(0.51f, 0.51f, 1.32f, 0.56f, 1.87f, 0.1f);
        pathBuilderA.curveToRelative(4.67f, -3.84f, 11.45f, -3.84f, 16.13f, -0.01f);
        pathBuilderA.curveToRelative(0.56f, 0.46f, 1.38f, 0.42f, 1.89f, -0.09f);
        pathBuilderA.curveToRelative(0.59f, -0.59f, 0.55f, -1.57f, -0.1f, -2.1f);
        pathBuilderA.curveToRelative(-5.71f, -4.67f, -13.97f, -4.67f, -19.69f, 0.0f);
        pathBuilderA.curveToRelative(-0.65f, 0.52f, -0.7f, 1.5f, -0.1f, 2.1f);
        a.z(pathBuilderA, 9.82f, 17.82f, 1.47f, 1.47f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(1.47f, -1.47f);
        pathBuilderA.curveToRelative(0.47f, -0.47f, 0.37f, -1.28f, -0.23f, -1.59f);
        pathBuilderA.curveToRelative(-1.22f, -0.63f, -2.68f, -0.63f, -3.91f, 0.0f);
        pathBuilderA.curveToRelative(-0.57f, 0.31f, -0.68f, 1.12f, -0.21f, 1.59f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.09f, 14.09f);
        pathBuilderA.curveToRelative(0.49f, 0.49f, 1.26f, 0.54f, 1.83f, 0.13f);
        pathBuilderA.curveToRelative(2.44f, -1.73f, 5.72f, -1.73f, 8.16f, 0.0f);
        pathBuilderA.curveToRelative(0.57f, 0.4f, 1.34f, 0.36f, 1.83f, -0.13f);
        pathBuilderA.lineToRelative(0.01f, -0.01f);
        pathBuilderA.curveToRelative(0.6f, -0.6f, 0.56f, -1.62f, -0.13f, -2.11f);
        pathBuilderA.curveToRelative(-3.44f, -2.49f, -8.13f, -2.49f, -11.58f, 0.0f);
        pathBuilderA.curveToRelative(-0.69f, 0.5f, -0.73f, 1.51f, -0.12f, 2.12f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wifi = imageVectorBuild;
        return imageVectorBuild;
    }
}
