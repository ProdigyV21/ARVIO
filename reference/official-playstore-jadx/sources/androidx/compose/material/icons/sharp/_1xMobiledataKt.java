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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__1xMobiledata", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_1xMobiledata", "Landroidx/compose/material/icons/Icons$Sharp;", "get_1xMobiledata", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _1xMobiledataKt {
    private static ImageVector __1xMobiledata;

    public static final ImageVector get_1xMobiledata(Icons.Sharp sharp) {
        ImageVector imageVector = __1xMobiledata;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp._1xMobiledata", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(4.0f, 7.0f, 4.0f, 10.0f, 6.0f);
        a.v(pathBuilderH, 9.0f, 4.0f, 7.0f);
        pathBuilderH.moveTo(15.83f, 11.72f);
        pathBuilderH.lineTo(18.66f, 7.0f);
        pathBuilderH.horizontalLineToRelative(-2.33f);
        pathBuilderH.lineToRelative(-1.66f, 2.77f);
        pathBuilderH.lineTo(13.0f, 7.0f);
        pathBuilderH.horizontalLineToRelative(-2.33f);
        pathBuilderH.lineToRelative(2.83f, 4.72f);
        pathBuilderH.lineTo(10.33f, 17.0f);
        pathBuilderH.horizontalLineToRelative(2.33f);
        pathBuilderH.lineToRelative(2.0f, -3.34f);
        pathBuilderH.lineToRelative(2.0f, 3.34f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderH, 19.0f, 15.83f, 11.72f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __1xMobiledata = imageVectorBuild;
        return imageVectorBuild;
    }
}
