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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swipeLeft", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwipeLeft", "Landroidx/compose/material/icons/Icons$Sharp;", "getSwipeLeft", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwipeLeftKt {
    private static ImageVector _swipeLeft;

    public static final ImageVector getSwipeLeft(Icons.Sharp sharp) {
        ImageVector imageVector = _swipeLeft;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SwipeLeft", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(20.18f, 15.4f, 19.1f, 23.0f, -9.0f);
        pathBuilderT.lineTo(5.0f, 17.62f);
        pathBuilderT.lineToRelative(1.22f, -1.23f);
        pathBuilderT.lineTo(10.0f, 17.24f);
        pathBuilderT.verticalLineTo(6.5f);
        pathBuilderT.curveTo(10.0f, 5.67f, 10.67f, 5.0f, 11.5f, 5.0f);
        pathBuilderT.reflectiveCurveTo(13.0f, 5.67f, 13.0f, 6.5f);
        a.z(pathBuilderT, 6.0f, 1.38f, 20.18f, 15.4f);
        pathBuilderT.moveTo(12.0f, 2.5f);
        pathBuilderT.curveToRelative(4.74f, 0.0f, 7.67f, 2.52f, 8.43f, 4.5f);
        pathBuilderT.horizontalLineTo(22.0f);
        pathBuilderT.curveToRelative(-0.73f, -2.88f, -4.51f, -6.0f, -10.0f, -6.0f);
        pathBuilderT.curveTo(8.78f, 1.0f, 5.82f, 2.13f, 3.5f, 4.02f);
        c.o(pathBuilderT, 2.0f, 2.0f, 5.0f, 5.0f);
        pathBuilderT.verticalLineTo(5.5f);
        pathBuilderT.horizontalLineTo(4.09f);
        pathBuilderT.curveTo(6.21f, 3.64f, 8.97f, 2.5f, 12.0f, 2.5f);
        pathBuilderT.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderT.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swipeLeft = imageVectorBuild;
        return imageVectorBuild;
    }
}
