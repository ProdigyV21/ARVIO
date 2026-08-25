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
import v.c;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrOn", "Landroidx/compose/material/icons/Icons$Sharp;", "getHdrOn", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrOnKt {
    private static ImageVector _hdrOn;

    public static final ImageVector getHdrOn(Icons.Sharp sharp) {
        ImageVector imageVector = _hdrOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HdrOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(21.0f, 12.9f, 21.0f, 9.0f, -5.0f);
        b.z(pathBuilderT, 6.0f, 1.5f, -2.0f, 1.1f);
        pathBuilderT.lineToRelative(0.9f, 2.0f);
        pathBuilderT.lineTo(21.0f, 15.0f);
        y.a.k(pathBuilderT, -0.9f, -2.1f, 0.9f);
        v.a.C(pathBuilderT, 19.5f, 11.5f, -2.0f, -1.0f);
        b.B(pathBuilderT, 2.0f, 1.0f, 6.5f, 11.0f);
        pathBuilderT.horizontalLineToRelative(-2.0f);
        pathBuilderT.lineTo(4.5f, 9.0f);
        v.a.h(pathBuilderT, 3.0f, 9.0f, 6.0f, 1.5f);
        c.w(pathBuilderT, -2.5f, 2.0f, 6.5f, 15.0f);
        pathBuilderT.lineTo(8.0f, 15.0f);
        pathBuilderT.lineTo(8.0f, 9.0f);
        d.f(pathBuilderT, 6.5f, 9.0f, 2.0f);
        pathBuilderT.moveTo(13.0f, 9.0f);
        pathBuilderT.lineTo(9.5f, 9.0f);
        pathBuilderT.verticalLineToRelative(6.0f);
        pathBuilderT.lineTo(13.0f, 15.0f);
        pathBuilderT.curveToRelative(0.8f, 0.0f, 1.5f, -0.7f, 1.5f, -1.5f);
        pathBuilderT.verticalLineToRelative(-3.0f);
        pathBuilderT.curveToRelative(0.0f, -0.8f, -0.7f, -1.5f, -1.5f, -1.5f);
        b.w(pathBuilderT, 13.0f, 13.5f, -2.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.s(pathBuilderT, 2.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
