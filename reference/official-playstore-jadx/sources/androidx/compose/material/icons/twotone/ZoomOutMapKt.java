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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_zoomOutMap", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ZoomOutMap", "Landroidx/compose/material/icons/Icons$TwoTone;", "getZoomOutMap", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ZoomOutMapKt {
    private static ImageVector _zoomOutMap;

    public static final ImageVector getZoomOutMap(Icons.TwoTone twoTone) {
        ImageVector imageVector = _zoomOutMap;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ZoomOutMap", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.3f, 5.3f, -2.89f, 2.87f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        pathBuilderR.lineTo(18.7f, 6.7f);
        pathBuilderR.lineTo(21.0f, 9.0f);
        f.n(pathBuilderR, 21.0f, 3.0f, -6.0f);
        pathBuilderR.moveTo(9.0f, 3.0f);
        pathBuilderR.lineTo(3.0f, 3.0f);
        pathBuilderR.verticalLineToRelative(6.0f);
        pathBuilderR.lineToRelative(2.3f, -2.3f);
        pathBuilderR.lineToRelative(2.87f, 2.89f);
        d.C(pathBuilderR, 1.42f, -1.42f, 6.7f, 5.3f);
        pathBuilderR.moveTo(8.17f, 14.41f);
        pathBuilderR.lineTo(5.3f, 17.3f);
        a.h(pathBuilderR, 3.0f, 15.0f, 6.0f, 6.0f);
        b.D(pathBuilderR, -2.3f, -2.3f, 2.89f, -2.87f);
        pathBuilderR.moveTo(15.83f, 14.41f);
        pathBuilderR.lineToRelative(-1.42f, 1.42f);
        pathBuilderR.lineToRelative(2.89f, 2.87f);
        b.A(pathBuilderR, 15.0f, 21.0f, 6.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderR, -2.3f, 2.3f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _zoomOutMap = imageVectorBuild;
        return imageVectorBuild;
    }
}
