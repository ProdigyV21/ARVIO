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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_map", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Map", "Landroidx/compose/material/icons/Icons$Filled;", "getMap", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MapKt {
    private static ImageVector _map;

    public static final ImageVector getMap(Icons.Filled filled) {
        ImageVector imageVector = _map;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Map", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.5f, 3.0f, -0.16f, 0.03f);
        pathBuilderR.lineTo(15.0f, 5.1f);
        pathBuilderR.lineTo(9.0f, 3.0f);
        pathBuilderR.lineTo(3.36f, 4.9f);
        pathBuilderR.curveToRelative(-0.21f, 0.07f, -0.36f, 0.25f, -0.36f, 0.48f);
        pathBuilderR.verticalLineTo(20.5f);
        pathBuilderR.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderR.lineToRelative(0.16f, -0.03f);
        pathBuilderR.lineTo(9.0f, 18.9f);
        pathBuilderR.lineToRelative(6.0f, 2.1f);
        pathBuilderR.lineToRelative(5.64f, -1.9f);
        pathBuilderR.curveToRelative(0.21f, -0.07f, 0.36f, -0.25f, 0.36f, -0.48f);
        pathBuilderR.verticalLineTo(3.5f);
        pathBuilderR.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        a.z(pathBuilderR, 15.0f, 19.0f, -6.0f, -2.11f);
        pathBuilderR.verticalLineTo(5.0f);
        pathBuilderR.lineToRelative(6.0f, 2.11f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _map = imageVectorBuild;
        return imageVectorBuild;
    }
}
