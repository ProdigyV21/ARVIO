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
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noBackpack", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoBackpack", "Landroidx/compose/material/icons/Icons$Outlined;", "getNoBackpack", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoBackpackKt {
    private static ImageVector _noBackpack;

    public static final ImageVector getNoBackpack(Icons.Outlined outlined) {
        ImageVector imageVector = _noBackpack;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NoBackpack", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.98f, 4.15f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.01f, -0.01f, 0.02f, -0.01f);
        b.r(pathBuilderA, 2.0f, 3.0f, 2.0f, 4.0f);
        a.j(pathBuilderA, 2.0f, 3.0f, 2.14f);
        pathBuilderA.curveToRelative(1.72f, 0.45f, 3.0f, 2.0f, 3.0f, 3.86f);
        a0.a.l(pathBuilderA, 9.17f, -2.0f, -2.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.w(pathBuilderA, 8.83f, 6.98f, 4.15f);
        pathBuilderA.moveTo(14.83f, 12.0f);
        y.a.h(pathBuilderA, 1.67f, 1.67f, 12.0f, 14.83f);
        pathBuilderA.moveTo(19.78f, 22.61f);
        pathBuilderA.lineToRelative(-0.85f, -0.85f);
        pathBuilderA.curveTo(18.65f, 21.91f, 18.34f, 22.0f, 18.0f, 22.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.36f, 0.06f, -0.69f, 0.15f, -1.02f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderA, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderA.moveTo(17.17f, 20.0f);
        e.h(pathBuilderA, -6.0f, -6.0f, 7.5f, -2.0f);
        a0.b.x(pathBuilderA, 1.67f, 6.0f, 8.83f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderA, 17.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noBackpack = imageVectorBuild;
        return imageVectorBuild;
    }
}
