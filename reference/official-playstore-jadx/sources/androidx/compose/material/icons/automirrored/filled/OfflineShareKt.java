package androidx.compose.material.icons.automirrored.filled;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_offlineShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "OfflineShare", "Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;", "getOfflineShare", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OfflineShareKt {
    private static ImageVector _offlineShare;

    public static final ImageVector getOfflineShare(Icons.AutoMirrored.Filled filled) {
        ImageVector imageVector = _offlineShare;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.OfflineShare", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(14.6f, 10.26f, 1.31f, 17.0f, 9.33f);
        pathBuilderS.lineTo(14.6f, 7.1f);
        pathBuilderS.verticalLineToRelative(1.28f);
        pathBuilderS.curveToRelative(-2.33f, 0.32f, -3.26f, 1.92f, -3.6f, 3.52f);
        pathBuilderS.curveToRelative(0.83f, -1.13f, 1.93f, -1.64f, 3.6f, -1.64f);
        a.p(pathBuilderS, 16.0f, 23.0f, 6.0f, 23.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        b.A(pathBuilderS, 4.0f, 5.0f, 2.0f, 16.0f);
        b.B(pathBuilderS, 10.0f, 2.0f, 18.0f, 1.0f);
        pathBuilderS.horizontalLineToRelative(-8.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.horizontalLineToRelative(8.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderS.lineTo(20.0f, 3.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.g(pathBuilderS, 18.0f, 16.0f, -8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderS, 10.0f, 4.0f, 8.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _offlineShare = imageVectorBuild;
        return imageVectorBuild;
    }
}
