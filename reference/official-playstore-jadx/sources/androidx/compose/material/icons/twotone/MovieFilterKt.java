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
import v.a;
import v.b;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_movieFilter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MovieFilter", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMovieFilter", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MovieFilterKt {
    private static ImageVector _movieFilter;

    public static final ImageVector getMovieFilter(Icons.TwoTone twoTone) {
        ImageVector imageVector = _movieFilter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MovieFilter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.63f, 11.37f, 18.0f, 12.0f);
        pathBuilderJ.lineToRelative(-1.37f, 0.63f);
        pathBuilderJ.lineTo(16.0f, 14.0f);
        pathBuilderJ.lineToRelative(-0.63f, -1.37f);
        pathBuilderJ.lineTo(14.0f, 12.0f);
        pathBuilderJ.lineToRelative(1.37f, -0.63f);
        pathBuilderJ.lineTo(16.0f, 10.0f);
        pathBuilderJ.lineTo(5.77f, 10.0f);
        pathBuilderJ.lineTo(4.01f, 6.47f);
        b.A(pathBuilderJ, 4.01f, 18.0f, 16.0f, -8.0f);
        d.l(pathBuilderJ, 16.0f, 10.0f, 0.63f, 1.37f);
        pathBuilderJ.moveTo(10.94f, 14.94f);
        pathBuilderJ.lineTo(10.0f, 17.0f);
        pathBuilderJ.lineToRelative(-0.94f, -2.06f);
        pathBuilderJ.lineTo(7.0f, 14.0f);
        pathBuilderJ.lineToRelative(2.06f, -0.94f);
        pathBuilderJ.lineTo(10.0f, 11.0f);
        pathBuilderJ.lineToRelative(0.94f, 2.06f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 13.0f, 14.0f, -2.06f, 0.94f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.0f, 11.0f, -0.94f, 2.06f);
        pathBuilderR.lineTo(7.0f, 14.0f);
        pathBuilderR.lineToRelative(2.06f, 0.94f);
        pathBuilderR.lineTo(10.0f, 17.0f);
        pathBuilderR.lineToRelative(0.94f, -2.06f);
        d.l(pathBuilderR, 13.0f, 14.0f, -2.06f, -0.94f);
        pathBuilderR.moveTo(18.01f, 4.0f);
        pathBuilderR.lineToRelative(2.0f, 4.0f);
        e.C(pathBuilderR, -3.0f, -2.0f, -4.0f, -2.0f);
        pathBuilderR.lineToRelative(2.0f, 4.0f);
        e.C(pathBuilderR, -3.0f, -2.0f, -4.0f, -2.0f);
        pathBuilderR.lineToRelative(2.0f, 4.0f);
        e.C(pathBuilderR, -3.0f, -2.0f, -4.0f, -1.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderR.lineToRelative(-0.01f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(16.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        f.n(pathBuilderR, 22.0f, 4.0f, -3.99f);
        pathBuilderR.moveTo(20.01f, 18.0f);
        pathBuilderR.horizontalLineToRelative(-16.0f);
        pathBuilderR.lineTo(4.01f, 6.47f);
        pathBuilderR.lineTo(5.77f, 10.0f);
        pathBuilderR.lineTo(16.0f, 10.0f);
        pathBuilderR.lineToRelative(-0.63f, 1.37f);
        pathBuilderR.lineTo(14.0f, 12.0f);
        pathBuilderR.lineToRelative(1.37f, 0.63f);
        pathBuilderR.lineTo(16.0f, 14.0f);
        pathBuilderR.lineToRelative(0.63f, -1.37f);
        pathBuilderR.lineTo(18.0f, 12.0f);
        pathBuilderR.lineToRelative(-1.37f, -0.63f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderR, 16.0f, 10.0f, 4.01f, 8.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _movieFilter = imageVectorBuild;
        return imageVectorBuild;
    }
}
