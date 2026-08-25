package androidx.compose.material.icons.outlined;

import a0.a;
import a0.b;
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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_warehouse", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Warehouse", "Landroidx/compose/material/icons/Icons$Outlined;", "getWarehouse", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WarehouseKt {
    private static ImageVector _warehouse;

    public static final ImageVector getWarehouse(Icons.Outlined outlined) {
        ImageVector imageVector = _warehouse;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Warehouse", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(20.0f, 8.35f, 19.0f, -2.0f, -8.0f);
        b.C(pathBuilderA, 6.0f, 8.0f, 4.0f, 8.35f);
        d.C(pathBuilderA, 8.0f, -3.2f, 20.0f, 8.35f);
        pathBuilderA.moveTo(22.0f, 21.0f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.lineTo(12.0f, 3.0f);
        v.a.h(pathBuilderA, 2.0f, 7.0f, 14.0f, 6.0f);
        b.f(pathBuilderA, -8.0f, 8.0f, 8.0f, 22.0f);
        v.a.A(pathBuilderA, 11.0f, 19.0f, 9.0f, 2.0f);
        c.D(pathBuilderA, 2.0f, 19.0f, 13.0f, 16.0f);
        f.w(pathBuilderA, -2.0f, 2.0f, 2.0f, 16.0f);
        v.a.C(pathBuilderA, 15.0f, 19.0f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderA, 2.0f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _warehouse = imageVectorBuild;
        return imageVectorBuild;
    }
}
