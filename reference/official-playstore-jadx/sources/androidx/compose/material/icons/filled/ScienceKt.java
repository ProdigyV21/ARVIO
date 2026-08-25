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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_science", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Science", "Landroidx/compose/material/icons/Icons$Filled;", "getScience", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScienceKt {
    private static ImageVector _science;

    public static final ImageVector getScience(Icons.Filled filled) {
        ImageVector imageVector = _science;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Science", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(19.8f, 18.4f, 14.0f, 10.67f, 6.5f);
        pathBuilderN.lineToRelative(1.35f, -1.69f);
        pathBuilderN.curveTo(15.61f, 4.48f, 15.38f, 4.0f, 14.96f, 4.0f);
        pathBuilderN.horizontalLineTo(9.04f);
        pathBuilderN.curveTo(8.62f, 4.0f, 8.39f, 4.48f, 8.65f, 4.81f);
        pathBuilderN.lineTo(10.0f, 6.5f);
        pathBuilderN.verticalLineToRelative(4.17f);
        pathBuilderN.lineTo(4.2f, 18.4f);
        pathBuilderN.curveTo(3.71f, 19.06f, 4.18f, 20.0f, 5.0f, 20.0f);
        pathBuilderN.horizontalLineToRelative(14.0f);
        pathBuilderN.curveTo(19.82f, 20.0f, 20.29f, 19.06f, 19.8f, 18.4f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _science = imageVectorBuild;
        return imageVectorBuild;
    }
}
