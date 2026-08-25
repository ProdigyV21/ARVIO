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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noCell", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoCell", "Landroidx/compose/material/icons/Icons$Filled;", "getNoCell", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoCellKt {
    private static ImageVector _noCell;

    public static final ImageVector getNoCell(Icons.Filled filled) {
        ImageVector imageVector = _noCell;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoCell", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.83f, 6.0f, -3.7f, -3.7f);
        pathBuilderR.curveTo(5.42f, 1.55f, 6.15f, 1.0f, 7.0f, 1.0f);
        pathBuilderR.lineToRelative(10.0f, 0.01f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.89f, 2.0f, 1.99f);
        a0.a.l(pathBuilderR, 13.17f, -2.0f, -2.0f, 6.0f);
        a.x(pathBuilderR, 8.83f, 19.78f, 22.61f);
        pathBuilderR.lineToRelative(-0.91f, -0.91f);
        pathBuilderR.curveTo(18.58f, 22.45f, 17.85f, 23.0f, 17.0f, 23.0f);
        pathBuilderR.horizontalLineTo(7.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineTo(7.83f);
        pathBuilderR.lineTo(1.39f, 4.22f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderR, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderR.moveTo(15.17f, 18.0f);
        pathBuilderR.lineTo(7.0f, 9.83f);
        pathBuilderR.verticalLineTo(18.0f);
        pathBuilderR.horizontalLineTo(15.17f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noCell = imageVectorBuild;
        return imageVectorBuild;
    }
}
