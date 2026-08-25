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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tableRestaurant", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TableRestaurant", "Landroidx/compose/material/icons/Icons$Filled;", "getTableRestaurant", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TableRestaurantKt {
    private static ImageVector _tableRestaurant;

    public static final ImageVector getTableRestaurant(Icons.Filled filled) {
        ImageVector imageVector = _tableRestaurant;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.TableRestaurant", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.96f, 9.73f, -1.43f, -5.0f);
        pathBuilderR.curveTo(20.41f, 4.3f, 20.02f, 4.0f, 19.57f, 4.0f);
        pathBuilderR.horizontalLineTo(4.43f);
        pathBuilderR.curveTo(3.98f, 4.0f, 3.59f, 4.3f, 3.47f, 4.73f);
        pathBuilderR.lineToRelative(-1.43f, 5.0f);
        pathBuilderR.curveTo(1.86f, 10.36f, 2.34f, 11.0f, 3.0f, 11.0f);
        c.A(pathBuilderR, 2.2f, 4.0f, 20.0f, 2.0f);
        pathBuilderR.lineToRelative(0.67f, -5.0f);
        c.A(pathBuilderR, 10.67f, 18.0f, 20.0f, 2.0f);
        pathBuilderR.lineToRelative(-1.2f, -9.0f);
        pathBuilderR.horizontalLineTo(21.0f);
        pathBuilderR.curveTo(21.66f, 11.0f, 22.14f, 10.36f, 21.96f, 9.73f);
        a.z(pathBuilderR, 6.93f, 13.0f, 0.27f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderR, 9.6f, 0.27f, 2.0f, 6.93f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tableRestaurant = imageVectorBuild;
        return imageVectorBuild;
    }
}
