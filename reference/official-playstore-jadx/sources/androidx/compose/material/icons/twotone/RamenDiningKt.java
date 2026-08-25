package androidx.compose.material.icons.twotone;

import a0.b;
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
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ramenDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RamenDining", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRamenDining", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RamenDiningKt {
    private static ImageVector _ramenDining;

    public static final ImageVector getRamenDining(Icons.TwoTone twoTone) {
        ImageVector imageVector = _ramenDining;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.RamenDining", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(8.73f, 18.39f, 1.27f, 0.5f, 20.0f);
        f.y(pathBuilderA, 4.0f, -1.11f, 1.27f, -0.5f);
        pathBuilderA.curveToRelative(2.16f, -0.85f, 3.74f, -2.47f, 4.4f, -4.39f);
        pathBuilderA.horizontalLineTo(4.34f);
        pathBuilderA.curveTo(4.99f, 15.92f, 6.58f, 17.54f, 8.73f, 18.39f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a0.a.f(22.0f, 3.51f, 2.0f, 4.0f, 3.99f);
        pathBuilderF.verticalLineTo(12.0f);
        pathBuilderF.horizontalLineTo(2.0f);
        pathBuilderF.curveToRelative(0.0f, 3.69f, 2.47f, 6.86f, 6.0f, 8.25f);
        a.j(pathBuilderF, 22.0f, 8.0f, -1.75f);
        pathBuilderF.curveToRelative(3.53f, -1.39f, 6.0f, -4.56f, 6.0f, -8.25f);
        a0.a.x(pathBuilderF, 10.5f, 8.0f, 22.0f, 6.5f);
        pathBuilderF.horizontalLineTo(10.5f);
        pathBuilderF.verticalLineTo(4.78f);
        pathBuilderF.lineTo(22.0f, 3.51f);
        pathBuilderF.close();
        pathBuilderF.moveTo(8.0f, 5.06f);
        b.m(pathBuilderF, 1.0f, -0.11f, 6.5f, 8.0f);
        v.a.D(pathBuilderF, 5.06f, 8.0f, 8.0f, 1.0f);
        d.u(pathBuilderF, 4.0f, 8.0f, 8.0f);
        pathBuilderF.moveTo(5.5f, 5.34f);
        v.b.o(pathBuilderF, 1.0f, -0.11f, 6.5f, -1.0f);
        v.a.D(pathBuilderF, 5.34f, 5.5f, 8.0f, 1.0f);
        v.a.t(pathBuilderF, 4.0f, -1.0f, 8.0f);
        pathBuilderF.moveTo(19.66f, 14.0f);
        pathBuilderF.curveToRelative(-0.66f, 1.92f, -2.24f, 3.54f, -4.4f, 4.39f);
        v.b.n(pathBuilderF, 14.0f, 18.89f, 20.0f, -4.0f);
        pathBuilderF.verticalLineToRelative(-1.11f);
        pathBuilderF.lineToRelative(-1.27f, -0.5f);
        pathBuilderF.curveToRelative(-2.16f, -0.85f, -3.74f, -2.47f, -4.4f, -4.39f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderF, 19.66f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ramenDining = imageVectorBuild;
        return imageVectorBuild;
    }
}
