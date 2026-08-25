package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signalCellular4Bar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SignalCellular4Bar", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSignalCellular4Bar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignalCellular4BarKt {
    private static ImageVector _signalCellular4Bar;

    public static final ImageVector getSignalCellular4Bar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _signalCellular4Bar;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(new ImageVector.Builder("TwoTone.SignalCellular4Bar", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null), d.o(e.b(2.0f, 22.0f, 20.0f, 2.0f), 2.0f, 22.0f), VectorKt.getDefaultFillType(), "", new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, StrokeCap.INSTANCE.m3803getButtKaPHkGw(), StrokeJoin.INSTANCE.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _signalCellular4Bar = imageVectorBuild;
        return imageVectorBuild;
    }
}
