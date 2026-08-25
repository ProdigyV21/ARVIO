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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_liveTv", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LiveTv", "Landroidx/compose/material/icons/Icons$Sharp;", "getLiveTv", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LiveTvKt {
    private static ImageVector _liveTv;

    public static final ImageVector getLiveTv(Icons.Sharp sharp) {
        ImageVector imageVector = _liveTv;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.LiveTv", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(23.0f, 6.0f, -9.59f, 3.29f, -3.29f);
        pathBuilderG.lineTo(16.0f, 2.0f);
        pathBuilderG.lineToRelative(-4.0f, 4.0f);
        pathBuilderG.lineToRelative(-4.0f, -4.0f);
        pathBuilderG.lineToRelative(-0.71f, 0.71f);
        pathBuilderG.lineTo(10.59f, 6.0f);
        a.h(pathBuilderG, 1.0f, 6.0f, 16.0f, 22.0f);
        c.z(pathBuilderG, 23.0f, 6.0f, 21.0f, 20.0f);
        pathBuilderG.lineTo(3.0f, 20.0f);
        b.g(pathBuilderG, 3.0f, 8.0f, 18.0f, 12.0f);
        pathBuilderG.moveTo(9.0f, 10.0f);
        pathBuilderG.verticalLineToRelative(8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderG, 7.0f, -4.0f, -7.0f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _liveTv = imageVectorBuild;
        return imageVectorBuild;
    }
}
