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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mobileOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MobileOff", "Landroidx/compose/material/icons/Icons$Outlined;", "getMobileOff", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MobileOffKt {
    private static ImageVector _mobileOff;

    public static final ImageVector getMobileOff(Icons.Outlined outlined) {
        ImageVector imageVector = _mobileOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MobileOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(17.0f, 5.0f, 8.61f, 2.0f, 2.0f);
        pathBuilderG.verticalLineTo(3.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.horizontalLineTo(7.0f);
        pathBuilderG.curveToRelative(-0.71f, 0.0f, -1.33f, 0.37f, -1.68f, 0.93f);
        a.e(pathBuilderG, 8.39f, 5.0f, 17.0f);
        pathBuilderG.moveTo(1.49f, 3.76f);
        pathBuilderG.lineTo(5.0f, 7.27f);
        pathBuilderG.verticalLineTo(21.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(10.0f);
        pathBuilderG.curveToRelative(1.02f, 0.0f, 1.85f, -0.77f, 1.98f, -1.75f);
        pathBuilderG.lineToRelative(1.72f, 1.72f);
        pathBuilderG.lineToRelative(1.41f, -1.41f);
        b.y(pathBuilderG, 2.9f, 2.35f, 1.49f, 3.76f);
        pathBuilderG.moveTo(7.0f, 9.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.e(pathBuilderG, 16.73f, 19.0f, 7.0f, 9.27f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mobileOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
