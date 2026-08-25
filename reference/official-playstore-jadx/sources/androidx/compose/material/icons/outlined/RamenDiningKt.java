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
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ramenDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RamenDining", "Landroidx/compose/material/icons/Icons$Outlined;", "getRamenDining", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RamenDiningKt {
    private static ImageVector _ramenDining;

    public static final ImageVector getRamenDining(Icons.Outlined outlined) {
        ImageVector imageVector = _ramenDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RamenDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.66f, 14.0f);
        pathBuilderA.curveToRelative(-0.66f, 1.92f, -2.24f, 3.54f, -4.4f, 4.39f);
        b.n(pathBuilderA, 14.0f, 18.89f, 20.0f, -4.0f);
        pathBuilderA.verticalLineToRelative(-1.11f);
        pathBuilderA.lineToRelative(-1.27f, -0.5f);
        pathBuilderA.curveToRelative(-2.16f, -0.85f, -3.74f, -2.47f, -4.4f, -4.39f);
        pathBuilderA.horizontalLineTo(19.66f);
        pathBuilderA.moveTo(22.0f, 2.0f);
        c.f(pathBuilderA, 4.0f, 3.99f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 3.69f, 2.47f, 6.86f, 6.0f, 8.25f);
        a.j(pathBuilderA, 22.0f, 8.0f, -1.75f);
        pathBuilderA.curveToRelative(3.53f, -1.39f, 6.0f, -4.56f, 6.0f, -8.25f);
        a0.a.x(pathBuilderA, 10.5f, 8.0f, 22.0f, 6.5f);
        pathBuilderA.horizontalLineTo(10.5f);
        pathBuilderA.verticalLineTo(4.78f);
        pathBuilderA.lineTo(22.0f, 3.51f);
        c.x(pathBuilderA, 2.0f, 22.0f, 2.0f);
        pathBuilderA.moveTo(8.0f, 6.5f);
        pathBuilderA.verticalLineTo(5.06f);
        pathBuilderA.lineToRelative(1.0f, -0.11f);
        pathBuilderA.verticalLineTo(6.5f);
        a.w(pathBuilderA, 8.0f, 8.0f, 6.5f);
        pathBuilderA.moveTo(5.5f, 6.5f);
        pathBuilderA.verticalLineTo(5.34f);
        pathBuilderA.lineToRelative(1.0f, -0.11f);
        pathBuilderA.verticalLineTo(6.5f);
        a.w(pathBuilderA, 5.5f, 5.5f, 6.5f);
        e.o(pathBuilderA, 8.0f, 12.0f, 8.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.lineTo(8.0f, 12.0f);
        pathBuilderA.close();
        e.o(pathBuilderA, 5.5f, 12.0f, 8.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.horizontalLineTo(5.5f);
        pathBuilderA.lineTo(5.5f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ramenDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
