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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_map", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Map", "Landroidx/compose/material/icons/Icons$Sharp;", "getMap", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MapKt {
    private static ImageVector _map;

    public static final ImageVector getMap(Icons.Sharp sharp) {
        ImageVector imageVector = _map;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Map", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 5.1f, 9.0f, 3.0f);
        pathBuilderJ.lineTo(3.0f, 5.02f);
        pathBuilderJ.verticalLineToRelative(16.2f);
        pathBuilderJ.lineToRelative(6.0f, -2.33f);
        pathBuilderJ.lineToRelative(6.0f, 2.1f);
        pathBuilderJ.lineToRelative(6.0f, -2.02f);
        b.y(pathBuilderJ, 21.0f, 2.77f, 15.0f, 5.1f);
        pathBuilderJ.moveTo(15.0f, 18.89f);
        pathBuilderJ.lineToRelative(-6.0f, -2.11f);
        pathBuilderJ.lineTo(9.0f, 5.11f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.j(pathBuilderJ, 6.0f, 2.11f, 11.67f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _map = imageVectorBuild;
        return imageVectorBuild;
    }
}
