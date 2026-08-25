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
import v.b;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_chalet", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Chalet", "Landroidx/compose/material/icons/Icons$Filled;", "getChalet", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChaletKt {
    private static ImageVector _chalet;

    public static final ImageVector getChalet(Icons.Filled filled) {
        ImageVector imageVector = _chalet;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Chalet", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.0f, 7.5f, 7.5f, 7.5f);
        pathBuilderR.lineToRelative(-1.41f, 1.41f);
        b.n(pathBuilderR, 15.0f, 15.33f, 20.0f, -4.0f);
        a.w(pathBuilderR, -5.0f, 9.0f, 5.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(-4.67f);
        pathBuilderR.lineToRelative(-1.09f, 1.09f);
        b.y(pathBuilderR, 2.5f, 15.0f, 10.0f, 7.5f);
        pathBuilderR.moveTo(22.0f, 6.5f);
        pathBuilderR.horizontalLineToRelative(-1.19f);
        pathBuilderR.lineToRelative(0.75f, -0.75f);
        pathBuilderR.lineToRelative(-0.71f, -0.71f);
        b.p(pathBuilderR, 19.39f, 6.5f, 18.5f, 5.61f);
        pathBuilderR.lineToRelative(1.45f, -1.45f);
        pathBuilderR.lineToRelative(-0.71f, -0.71f);
        b.n(pathBuilderR, 18.5f, 4.19f, 3.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(1.19f);
        pathBuilderR.lineToRelative(-0.75f, -0.75f);
        pathBuilderR.lineToRelative(-0.71f, 0.71f);
        b.o(pathBuilderR, 1.45f, 1.45f, 6.5f, -0.89f);
        pathBuilderR.lineToRelative(-1.45f, -1.45f);
        pathBuilderR.lineToRelative(-0.71f, 0.71f);
        e.h(pathBuilderR, 0.75f, 0.75f, 14.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(1.19f);
        pathBuilderR.lineToRelative(-0.75f, 0.75f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        a0.a.m(pathBuilderR, 1.45f, -1.45f, 0.89f, 0.89f);
        pathBuilderR.lineToRelative(-1.45f, 1.45f);
        pathBuilderR.lineToRelative(0.71f, 0.71f);
        b.o(pathBuilderR, 0.75f, -0.75f, 11.0f, 1.0f);
        pathBuilderR.verticalLineTo(9.81f);
        pathBuilderR.lineToRelative(0.75f, 0.75f);
        pathBuilderR.lineToRelative(0.71f, -0.71f);
        b.n(pathBuilderR, 18.5f, 8.39f, 7.5f, 0.89f);
        pathBuilderR.lineToRelative(1.45f, 1.45f);
        pathBuilderR.lineToRelative(0.71f, -0.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.e(pathBuilderR, 20.81f, 7.5f, 22.0f, 6.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _chalet = imageVectorBuild;
        return imageVectorBuild;
    }
}
