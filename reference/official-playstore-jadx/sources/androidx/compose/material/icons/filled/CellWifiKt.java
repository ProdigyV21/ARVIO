package androidx.compose.material.icons.filled;

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cellWifi", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CellWifi", "Landroidx/compose/material/icons/Icons$Filled;", "getCellWifi", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CellWifiKt {
    private static ImageVector _cellWifi;

    public static final ImageVector getCellWifi(Icons.Filled filled) {
        ImageVector imageVector = _cellWifi;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.CellWifi", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(18.0f, 9.98f, 6.0f, 22.0f, 12.0f);
        b.z(pathBuilderT, 4.0f, 5.97f, 18.0f, 9.98f);
        v.a.C(pathBuilderT, 20.0f, 20.0f, -2.0f, -7.22f);
        c.e(pathBuilderT, 2.0f, -2.0f, 20.0f);
        pathBuilderT.moveTo(5.22f, 7.22f);
        pathBuilderT.lineTo(3.93f, 5.93f);
        pathBuilderT.curveToRelative(3.9f, -3.91f, 10.24f, -3.91f, 14.15f, 0.0f);
        pathBuilderT.lineToRelative(-1.29f, 1.29f);
        pathBuilderT.curveTo(13.6f, 4.03f, 8.41f, 4.03f, 5.22f, 7.22f);
        v.a.p(pathBuilderT, 12.93f, 11.07f, 11.0f, 13.0f);
        pathBuilderT.lineToRelative(-1.93f, -1.93f);
        pathBuilderT.curveTo(10.14f, 10.01f, 11.86f, 10.01f, 12.93f, 11.07f);
        pathBuilderT.close();
        pathBuilderT.moveTo(14.22f, 9.79f);
        pathBuilderT.curveToRelative(-1.78f, -1.77f, -4.66f, -1.77f, -6.43f, 0.0f);
        pathBuilderT.lineTo(6.5f, 8.5f);
        pathBuilderT.curveToRelative(2.48f, -2.48f, 6.52f, -2.48f, 9.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderT, 14.22f, 9.79f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cellWifi = imageVectorBuild;
        return imageVectorBuild;
    }
}
