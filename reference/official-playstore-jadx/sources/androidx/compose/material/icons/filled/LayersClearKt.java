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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_layersClear", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LayersClear", "Landroidx/compose/material/icons/Icons$Filled;", "getLayersClear", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayersClearKt {
    private static ImageVector _layersClear;

    public static final ImageVector getLayersClear(Icons.Filled filled) {
        ImageVector imageVector = _layersClear;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LayersClear", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.81f, 14.99f, 1.19f, -0.92f);
        pathBuilderR.lineToRelative(-1.43f, -1.43f);
        b.D(pathBuilderR, -1.19f, 0.92f, 1.43f, 1.43f);
        pathBuilderR.moveTo(19.36f, 10.27f);
        pathBuilderR.lineTo(21.0f, 9.0f);
        pathBuilderR.lineToRelative(-9.0f, -7.0f);
        pathBuilderR.lineToRelative(-2.91f, 2.27f);
        b.D(pathBuilderR, 7.87f, 7.88f, 2.4f, -1.88f);
        pathBuilderR.moveTo(3.27f, 1.0f);
        pathBuilderR.lineTo(2.0f, 2.27f);
        pathBuilderR.lineToRelative(4.22f, 4.22f);
        pathBuilderR.lineTo(3.0f, 9.0f);
        pathBuilderR.lineToRelative(1.63f, 1.27f);
        pathBuilderR.lineTo(12.0f, 16.0f);
        pathBuilderR.lineToRelative(2.1f, -1.63f);
        pathBuilderR.lineToRelative(1.43f, 1.43f);
        pathBuilderR.lineTo(12.0f, 18.54f);
        pathBuilderR.lineToRelative(-7.37f, -5.73f);
        pathBuilderR.lineTo(3.0f, 14.07f);
        pathBuilderR.lineToRelative(9.0f, 7.0f);
        pathBuilderR.lineToRelative(4.95f, -3.85f);
        pathBuilderR.lineTo(20.73f, 21.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderR, 22.0f, 19.73f, 3.27f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _layersClear = imageVectorBuild;
        return imageVectorBuild;
    }
}
