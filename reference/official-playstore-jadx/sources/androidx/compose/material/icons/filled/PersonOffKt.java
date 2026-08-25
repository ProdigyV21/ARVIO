package androidx.compose.material.icons.filled;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_personOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PersonOff", "Landroidx/compose/material/icons/Icons$Filled;", "getPersonOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersonOffKt {
    private static ImageVector _personOff;

    public static final ImageVector getPersonOff(Icons.Filled filled) {
        ImageVector imageVector = _personOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PersonOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.65f, 5.82f);
        pathBuilderA.curveTo(9.36f, 4.72f, 10.6f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.4f, -0.72f, 2.64f, -1.82f, 3.35f);
        c.z(pathBuilderA, 8.65f, 5.82f, 20.0f, 17.17f);
        pathBuilderA.curveToRelative(-0.02f, -1.1f, -0.63f, -2.11f, -1.61f, -2.62f);
        pathBuilderA.curveToRelative(-0.54f, -0.28f, -1.13f, -0.54f, -1.77f, -0.76f);
        c.z(pathBuilderA, 20.0f, 17.17f, 21.19f, 21.19f);
        pathBuilderA.lineTo(2.81f, 2.81f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(8.89f, 8.89f);
        pathBuilderA.curveToRelative(-1.81f, 0.23f, -3.39f, 0.79f, -4.67f, 1.45f);
        pathBuilderA.curveTo(4.61f, 15.07f, 4.0f, 16.1f, 4.0f, 17.22f);
        b.l(pathBuilderA, 20.0f, 13.17f, 2.61f, 2.61f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 21.19f, 21.19f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _personOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
