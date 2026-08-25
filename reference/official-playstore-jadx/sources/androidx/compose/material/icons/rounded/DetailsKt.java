package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_details", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Details", "Landroidx/compose/material/icons/Icons$Rounded;", "getDetails", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DetailsKt {
    private static ImageVector _details;

    public static final ImageVector getDetails(Icons.Rounded rounded) {
        ImageVector imageVector = _details;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Details", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.13f, 4.57f, -8.3f, 14.94f);
        pathBuilderR.curveTo(2.46f, 20.18f, 2.94f, 21.0f, 3.7f, 21.0f);
        pathBuilderR.horizontalLineToRelative(16.6f);
        pathBuilderR.curveToRelative(0.76f, 0.0f, 1.24f, -0.82f, 0.87f, -1.49f);
        pathBuilderR.lineToRelative(-8.3f, -14.94f);
        pathBuilderR.curveTo(12.49f, 3.89f, 11.51f, 3.89f, 11.13f, 4.57f);
        a.p(pathBuilderR, 13.0f, 8.92f, 18.6f, 19.0f);
        c.l(pathBuilderR, 13.0f, 8.92f, 11.0f, 8.92f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.horizontalLineTo(5.4f);
        pathBuilderR.lineTo(11.0f, 8.92f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _details = imageVectorBuild;
        return imageVectorBuild;
    }
}
