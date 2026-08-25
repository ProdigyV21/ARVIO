package androidx.compose.material.icons.sharp;

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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pinch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pinch", "Landroidx/compose/material/icons/Icons$Sharp;", "getPinch", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinchKt {
    private static ImageVector _pinch;

    public static final ImageVector getPinch(Icons.Sharp sharp) {
        ImageVector imageVector = _pinch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Pinch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(23.18f, 15.4f, 22.1f, 23.0f, -9.0f);
        pathBuilderT.lineTo(8.0f, 17.62f);
        pathBuilderT.lineToRelative(1.22f, -1.23f);
        pathBuilderT.lineTo(13.0f, 17.24f);
        pathBuilderT.verticalLineTo(6.5f);
        pathBuilderT.curveTo(13.0f, 5.67f, 13.67f, 5.0f, 14.5f, 5.0f);
        pathBuilderT.reflectiveCurveTo(16.0f, 5.67f, 16.0f, 6.5f);
        a.z(pathBuilderT, 6.0f, 1.38f, 23.18f, 15.4f);
        e.o(pathBuilderT, 6.0f, 2.5f, 1.0f, 5.0f);
        a.y(pathBuilderT, 5.0f, 9.5f, 3.56f);
        b.p(pathBuilderT, 3.56f, 9.5f, 6.0f, 11.0f);
        y.a.t(pathBuilderT, 1.0f, 6.0f, 1.5f, 2.44f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderT, 8.44f, 2.5f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pinch = imageVectorBuild;
        return imageVectorBuild;
    }
}
