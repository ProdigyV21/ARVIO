package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_title", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Title", "Landroidx/compose/material/icons/Icons$Filled;", "getTitle", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TitleKt {
    private static ImageVector _title;

    public static final ImageVector getTitle(Icons.Filled filled) {
        ImageVector imageVector = _title;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(new ImageVector.Builder("Filled.Title", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null), a.d(y.a.u(5.0f, 4.0f, 3.0f, 5.5f, 12.0f), 3.0f, 7.0f, 19.0f, 4.0f), VectorKt.getDefaultFillType(), "", new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, StrokeCap.INSTANCE.m3803getButtKaPHkGw(), StrokeJoin.INSTANCE.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _title = imageVectorBuild;
        return imageVectorBuild;
    }
}
