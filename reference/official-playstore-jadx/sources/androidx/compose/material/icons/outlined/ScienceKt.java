package androidx.compose.material.icons.outlined;

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
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_science", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Science", "Landroidx/compose/material/icons/Icons$Outlined;", "getScience", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScienceKt {
    private static ImageVector _science;

    public static final ImageVector getScience(Icons.Outlined outlined) {
        ImageVector imageVector = _science;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Science", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = d.n(13.0f, 11.33f, 18.0f, 18.0f, 6.0f);
        b.o(pathBuilderN, 5.0f, -6.67f, 6.0f, 2.0f);
        pathBuilderN.moveTo(15.96f, 4.0f);
        pathBuilderN.horizontalLineTo(8.04f);
        pathBuilderN.curveTo(7.62f, 4.0f, 7.39f, 4.48f, 7.65f, 4.81f);
        pathBuilderN.lineTo(9.0f, 6.5f);
        pathBuilderN.verticalLineToRelative(4.17f);
        pathBuilderN.lineTo(3.2f, 18.4f);
        pathBuilderN.curveTo(2.71f, 19.06f, 3.18f, 20.0f, 4.0f, 20.0f);
        pathBuilderN.horizontalLineToRelative(16.0f);
        pathBuilderN.curveToRelative(0.82f, 0.0f, 1.29f, -0.94f, 0.8f, -1.6f);
        pathBuilderN.lineTo(15.0f, 10.67f);
        pathBuilderN.verticalLineTo(6.5f);
        pathBuilderN.lineToRelative(1.35f, -1.69f);
        pathBuilderN.curveTo(16.61f, 4.48f, 16.38f, 4.0f, 15.96f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderN, 15.96f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _science = imageVectorBuild;
        return imageVectorBuild;
    }
}
