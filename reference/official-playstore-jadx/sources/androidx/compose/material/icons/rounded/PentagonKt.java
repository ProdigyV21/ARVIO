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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pentagon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pentagon", "Landroidx/compose/material/icons/Icons$Rounded;", "getPentagon", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PentagonKt {
    private static ImageVector _pentagon;

    public static final ImageVector getPentagon(Icons.Rounded rounded) {
        ImageVector imageVector = _pentagon;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Pentagon", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(2.47f, 10.42f, 3.07f, 9.22f);
        pathBuilderR.curveTo(5.82f, 20.45f, 6.58f, 21.0f, 7.44f, 21.0f);
        pathBuilderR.horizontalLineToRelative(9.12f);
        pathBuilderR.curveToRelative(0.86f, 0.0f, 1.63f, -0.55f, 1.9f, -1.37f);
        pathBuilderR.lineToRelative(3.07f, -9.22f);
        pathBuilderR.curveToRelative(0.28f, -0.84f, -0.03f, -1.76f, -0.75f, -2.27f);
        pathBuilderR.lineTo(13.15f, 2.8f);
        pathBuilderR.curveToRelative(-0.69f, -0.48f, -1.61f, -0.48f, -2.29f, 0.0f);
        pathBuilderR.lineTo(3.22f, 8.14f);
        pathBuilderR.curveTo(2.5f, 8.65f, 2.19f, 9.58f, 2.47f, 10.42f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pentagon = imageVectorBuild;
        return imageVectorBuild;
    }
}
