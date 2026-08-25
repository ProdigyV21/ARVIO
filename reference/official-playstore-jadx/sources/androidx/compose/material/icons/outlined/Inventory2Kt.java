package androidx.compose.material.icons.outlined;

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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_inventory2", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Inventory2", "Landroidx/compose/material/icons/Icons$Outlined;", "getInventory2", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Inventory2Kt {
    private static ImageVector _inventory2;

    public static final ImageVector getInventory2(Icons.Outlined outlined) {
        ImageVector imageVector = _inventory2;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Inventory2", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(3.0f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(3.01f);
        pathBuilderQ.curveTo(2.0f, 7.73f, 2.43f, 8.35f, 3.0f, 8.7f);
        pathBuilderQ.verticalLineTo(20.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 1.1f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.9f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(8.7f);
        pathBuilderQ.curveToRelative(0.57f, -0.35f, 1.0f, -0.97f, 1.0f, -1.69f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.0f, 2.0f, 20.0f, 2.0f);
        f.o(pathBuilderQ, 19.0f, 20.0f, 5.0f, 9.0f);
        c.D(pathBuilderQ, 14.0f, 20.0f, 20.0f, 7.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.i(pathBuilderQ, 4.0f, 4.0f, 16.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(9.0f, 12.0f, 6.0f, 2.0f, -6.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _inventory2 = imageVectorBuild;
        return imageVectorBuild;
    }
}
