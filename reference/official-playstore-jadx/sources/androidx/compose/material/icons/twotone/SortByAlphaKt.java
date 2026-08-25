package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sortByAlpha", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SortByAlpha", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSortByAlpha", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SortByAlphaKt {
    private static ImageVector _sortByAlpha;

    public static final ImageVector getSortByAlpha(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sortByAlpha;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SortByAlpha", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.94f, 4.66f, 12.58f, 2.3f);
        b.C(pathBuilderJ, -2.36f, 2.36f, 10.39f, 17.73f);
        pathBuilderJ.horizontalLineToRelative(1.84f);
        pathBuilderJ.lineTo(7.74f, 6.27f);
        pathBuilderJ.lineTo(6.1f, 6.27f);
        pathBuilderJ.lineTo(1.6f, 17.73f);
        e.C(pathBuilderJ, 1.84f, 0.92f, -2.45f, 5.11f);
        b.C(pathBuilderJ, 0.92f, 2.45f, 4.97f, 13.64f);
        pathBuilderJ.lineToRelative(1.94f, -5.18f);
        d.C(pathBuilderJ, 1.94f, 5.18f, 4.97f, 13.64f);
        pathBuilderJ.moveTo(12.58f, 21.7f);
        y.a.k(pathBuilderJ, 2.33f, -2.33f, -4.66f);
        pathBuilderJ.moveTo(21.66f, 7.54f);
        b.A(pathBuilderJ, 21.66f, 6.28f, -8.3f, 1.6f);
        a0.b.v(pathBuilderJ, 5.88f, -5.92f, 8.56f, 1.29f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.j(pathBuilderJ, 8.53f, -1.59f, -6.12f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sortByAlpha = imageVectorBuild;
        return imageVectorBuild;
    }
}
