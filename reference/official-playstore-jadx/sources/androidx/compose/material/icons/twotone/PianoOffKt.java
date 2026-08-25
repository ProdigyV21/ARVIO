package androidx.compose.material.icons.twotone;

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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pianoOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PianoOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPianoOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PianoOffKt {
    private static ImageVector _pianoOff;

    public static final ImageVector getPianoOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _pianoOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PianoOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(8.25f, 19.0f, 5.0f, 7.83f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(3.67f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.D(pathBuilderA, 0.25f, 19.0f, 9.75f, 19.0f);
        pathBuilderA.verticalLineToRelative(-4.5f);
        pathBuilderA.horizontalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.46f, 0.0f, 0.82f, -0.31f, 0.94f, -0.73f);
        a.h(pathBuilderA, 3.31f, 3.31f, 19.0f, 9.75f);
        e.o(pathBuilderA, 13.0f, 10.17f, 5.0f, -2.0f);
        a.n(pathBuilderA, 3.17f, 13.0f, 10.17f);
        e.o(pathBuilderA, 19.0f, 16.17f, 5.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(8.5f);
        pathBuilderA.curveToRelative(0.0f, 0.19f, -0.07f, 0.36f, -0.16f, 0.51f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 19.0f, 16.17f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineTo(3.0f, 5.83f);
        pathBuilderJ.verticalLineTo(19.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(13.17f);
        d.C(pathBuilderJ, 1.61f, 1.61f, 21.19f, 21.19f);
        c.B(pathBuilderJ, 8.25f, 19.0f, 5.0f, 7.83f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(3.67f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.D(pathBuilderJ, 0.25f, 19.0f, 9.75f, 19.0f);
        pathBuilderJ.verticalLineToRelative(-4.5f);
        pathBuilderJ.horizontalLineTo(10.0f);
        pathBuilderJ.curveToRelative(0.46f, 0.0f, 0.82f, -0.31f, 0.94f, -0.73f);
        a.h(pathBuilderJ, 3.31f, 3.31f, 19.0f, 9.75f);
        pathBuilderJ.moveTo(11.0f, 8.17f);
        pathBuilderJ.lineTo(5.83f, 3.0f);
        pathBuilderJ.horizontalLineTo(19.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        a0.a.l(pathBuilderJ, 13.17f, -2.0f, -2.0f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.verticalLineToRelative(8.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.19f, -0.07f, 0.36f, -0.16f, 0.51f);
        b.n(pathBuilderJ, 13.0f, 10.17f, 5.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderJ, 8.17f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pianoOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
