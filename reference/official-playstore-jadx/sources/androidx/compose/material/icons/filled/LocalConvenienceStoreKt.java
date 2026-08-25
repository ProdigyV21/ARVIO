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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localConvenienceStore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalConvenienceStore", "Landroidx/compose/material/icons/Icons$Filled;", "getLocalConvenienceStore", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalConvenienceStoreKt {
    private static ImageVector _localConvenienceStore;

    public static final ImageVector getLocalConvenienceStore(Icons.Filled filled) {
        ImageVector imageVector = _localConvenienceStore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LocalConvenienceStore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.0f, 7.0f, 19.0f, 4.0f);
        pathBuilderJ.lineTo(5.0f, 4.0f);
        d.r(pathBuilderJ, 3.0f, 2.0f, 7.0f, 13.0f);
        a.o(pathBuilderJ, 8.0f, -4.0f, 4.0f, 4.0f);
        c.j(pathBuilderJ, 8.0f, 22.0f, 7.0f, -3.0f);
        pathBuilderJ.moveTo(11.0f, 10.0f);
        a.h(pathBuilderJ, 9.0f, 10.0f, 1.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(1.0f);
        pathBuilderJ.lineTo(8.0f, 12.0f);
        pathBuilderJ.lineTo(8.0f, 9.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.lineTo(10.0f, 8.0f);
        pathBuilderJ.lineTo(8.0f, 8.0f);
        b.g(pathBuilderJ, 8.0f, 7.0f, 3.0f, 3.0f);
        a.C(pathBuilderJ, 16.0f, 12.0f, -1.0f, -2.0f);
        c.A(pathBuilderJ, -2.0f, 13.0f, 7.0f, 1.0f);
        c.w(pathBuilderJ, 2.0f, 1.0f, 15.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderJ, 1.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localConvenienceStore = imageVectorBuild;
        return imageVectorBuild;
    }
}
