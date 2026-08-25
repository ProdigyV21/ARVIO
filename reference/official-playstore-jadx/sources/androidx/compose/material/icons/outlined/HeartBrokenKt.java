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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heartBroken", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeartBroken", "Landroidx/compose/material/icons/Icons$Outlined;", "getHeartBroken", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeartBrokenKt {
    private static ImageVector _heartBroken;

    public static final ImageVector getHeartBroken(Icons.Outlined outlined) {
        ImageVector imageVector = _heartBroken;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.HeartBroken", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 3.0f);
        pathBuilderA.curveToRelative(-0.96f, 0.0f, -1.9f, 0.25f, -2.73f, 0.69f);
        pathBuilderA.lineTo(12.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.lineToRelative(-3.0f, 10.0f);
        pathBuilderA.lineToRelative(1.0f, -9.0f);
        pathBuilderA.horizontalLineToRelative(-3.0f);
        pathBuilderA.lineToRelative(1.54f, -5.39f);
        pathBuilderA.curveTo(10.47f, 3.61f, 9.01f, 3.0f, 7.5f, 3.0f);
        pathBuilderA.curveTo(4.42f, 3.0f, 2.0f, 5.42f, 2.0f, 8.5f);
        pathBuilderA.curveToRelative(0.0f, 4.13f, 4.16f, 7.18f, 10.0f, 12.5f);
        pathBuilderA.curveToRelative(5.47f, -4.94f, 10.0f, -8.26f, 10.0f, -12.5f);
        pathBuilderA.curveTo(22.0f, 5.42f, 19.58f, 3.0f, 16.5f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.24f, 16.73f);
        pathBuilderA.curveTo(6.45f, 13.34f, 4.0f, 11.0f, 4.0f, 8.5f);
        pathBuilderA.curveTo(4.0f, 6.54f, 5.54f, 5.0f, 7.5f, 5.0f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.19f, 0.15f, 1.73f, 0.42f);
        pathBuilderA.lineTo(7.35f, 12.0f);
        d.q(pathBuilderA, 3.42f, 10.24f, 16.73f);
        pathBuilderA.moveTo(15.13f, 15.53f);
        pathBuilderA.lineTo(17.69f, 7.0f);
        pathBuilderA.horizontalLineToRelative(-2.91f);
        pathBuilderA.lineToRelative(0.61f, -1.82f);
        pathBuilderA.curveTo(15.75f, 5.06f, 16.13f, 5.0f, 16.5f, 5.0f);
        pathBuilderA.curveTo(18.46f, 5.0f, 20.0f, 6.54f, 20.0f, 8.5f);
        pathBuilderA.curveTo(20.0f, 10.71f, 17.98f, 12.93f, 15.13f, 15.53f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heartBroken = imageVectorBuild;
        return imageVectorBuild;
    }
}
