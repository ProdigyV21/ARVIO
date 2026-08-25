package androidx.compose.material.icons.rounded;

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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pianoOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PianoOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getPianoOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PianoOffKt {
    private static ImageVector _pianoOff;

    public static final ImageVector getPianoOff(Icons.Rounded rounded) {
        ImageVector imageVector = _pianoOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PianoOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.49f, 21.9f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(3.51f, 3.51f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineTo(3.0f, 5.83f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(13.17f);
        pathBuilderA.lineToRelative(0.9f, 0.9f);
        pathBuilderA.curveTo(19.46f, 22.29f, 20.09f, 22.29f, 20.49f, 21.9f);
        f.o(pathBuilderA, 8.25f, 19.0f, 5.0f, 7.83f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(3.67f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.D(pathBuilderA, 0.25f, 19.0f, 9.75f, 19.0f);
        pathBuilderA.verticalLineToRelative(-4.5f);
        pathBuilderA.horizontalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.46f, 0.0f, 0.82f, -0.31f, 0.94f, -0.73f);
        a.h(pathBuilderA, 3.31f, 3.31f, 19.0f, 9.75f);
        pathBuilderA.moveTo(11.0f, 8.17f);
        pathBuilderA.lineTo(5.83f, 3.0f);
        pathBuilderA.horizontalLineTo(19.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        a0.a.l(pathBuilderA, 13.17f, -2.0f, -2.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(8.5f);
        pathBuilderA.curveToRelative(0.0f, 0.19f, -0.07f, 0.36f, -0.16f, 0.51f);
        b.n(pathBuilderA, 13.0f, 10.17f, 5.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 8.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pianoOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
