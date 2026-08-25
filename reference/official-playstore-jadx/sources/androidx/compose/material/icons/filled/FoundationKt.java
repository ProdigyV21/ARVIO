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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_foundation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Foundation", "Landroidx/compose/material/icons/Icons$Filled;", "getFoundation", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FoundationKt {
    private static ImageVector _foundation;

    public static final ImageVector getFoundation(Icons.Filled filled) {
        ImageVector imageVector = _foundation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Foundation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(19.0f, 12.0f, 3.0f, 12.0f, 3.0f);
        b.A(pathBuilderI, 2.0f, 12.0f, 3.0f, 3.0f);
        b.q(pathBuilderI, 3.0f, 2.0f, 2.0f, 3.0f);
        a.o(pathBuilderI, 2.0f, -3.0f, 4.0f, 3.0f);
        a.o(pathBuilderI, 2.0f, -3.0f, 4.0f, 3.0f);
        a.o(pathBuilderI, 2.0f, -3.0f, 2.0f, -2.0f);
        c.D(pathBuilderI, -2.0f, 12.0f, 7.0f, 15.0f);
        a0.a.l(pathBuilderI, -4.81f, 4.0f, -3.6f, 15.0f);
        a0.a.t(pathBuilderI, 7.0f, 13.0f, 15.0f, 6.59f);
        pathBuilderI.lineToRelative(4.0f, 3.6f);
        pathBuilderI.verticalLineTo(15.0f);
        pathBuilderI.horizontalLineTo(13.0f);
        pathBuilderI.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _foundation = imageVectorBuild;
        return imageVectorBuild;
    }
}
