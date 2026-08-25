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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_queuePlayNext", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QueuePlayNext", "Landroidx/compose/material/icons/Icons$Sharp;", "getQueuePlayNext", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QueuePlayNextKt {
    private static ImageVector _queuePlayNext;

    public static final ImageVector getQueuePlayNext(Icons.Sharp sharp) {
        ImageVector imageVector = _queuePlayNext;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.QueuePlayNext", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(23.0f, 3.0f, 1.0f, 3.0f, 16.0f);
        a.o(pathBuilderS, 7.0f, 2.0f, 8.0f, -2.0f);
        c.q(pathBuilderS, 2.0f, -2.0f, 3.0f, 17.0f);
        b.A(pathBuilderS, 3.0f, 5.0f, 18.0f, 8.0f);
        d.q(pathBuilderS, 2.0f, 23.0f, 3.0f);
        pathBuilderS.moveTo(13.0f, 10.0f);
        b.A(pathBuilderS, 13.0f, 7.0f, -2.0f, 3.0f);
        a.h(pathBuilderS, 8.0f, 10.0f, 2.0f, 3.0f);
        b.z(pathBuilderS, 3.0f, 2.0f, -3.0f, 3.0f);
        e.D(pathBuilderS, -2.0f, -3.0f, 24.0f, 18.0f);
        pathBuilderS.lineToRelative(-4.5f, 4.5f);
        pathBuilderS.lineTo(18.0f, 21.0f);
        pathBuilderS.lineToRelative(3.0f, -3.0f);
        pathBuilderS.lineToRelative(-3.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderS, 1.5f, -1.5f, 24.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _queuePlayNext = imageVectorBuild;
        return imageVectorBuild;
    }
}
