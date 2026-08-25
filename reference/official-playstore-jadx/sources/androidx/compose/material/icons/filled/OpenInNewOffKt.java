package androidx.compose.material.icons.filled;

import a0.c;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_openInNewOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "OpenInNewOff", "Landroidx/compose/material/icons/Icons$Filled;", "getOpenInNewOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OpenInNewOffKt {
    private static ImageVector _openInNewOff;

    public static final ImageVector getOpenInNewOff(Icons.Filled filled) {
        ImageVector imageVector = _openInNewOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.OpenInNewOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(16.79f, 5.8f, 14.0f, 3.0f, 7.0f);
        pathBuilderT.verticalLineToRelative(7.0f);
        pathBuilderT.lineToRelative(-2.79f, -2.8f);
        pathBuilderT.lineToRelative(-4.09f, 4.09f);
        d.C(pathBuilderT, -1.41f, -1.41f, 16.79f, 5.8f);
        pathBuilderT.moveTo(19.0f, 12.0f);
        a0.a.l(pathBuilderT, 4.17f, 2.0f, 2.0f, 12.0f);
        v.a.x(pathBuilderT, 19.0f, 19.78f, 22.61f);
        pathBuilderT.lineTo(18.17f, 21.0f);
        pathBuilderT.horizontalLineTo(5.0f);
        pathBuilderT.curveToRelative(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderT.verticalLineTo(5.83f);
        pathBuilderT.lineTo(1.39f, 4.22f);
        pathBuilderT.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderT, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderT.moveTo(16.17f, 19.0f);
        pathBuilderT.lineToRelative(-4.88f, -4.88f);
        pathBuilderT.lineTo(9.7f, 15.71f);
        pathBuilderT.lineTo(8.29f, 14.3f);
        pathBuilderT.lineToRelative(1.59f, -1.59f);
        c.z(pathBuilderT, 5.0f, 7.83f, 19.0f, 16.17f);
        v.c.B(pathBuilderT, 7.83f, 5.0f, 12.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderT, 5.83f, 7.83f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _openInNewOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
