package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_movieCreation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MovieCreation", "Landroidx/compose/material/icons/Icons$Outlined;", "getMovieCreation", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MovieCreationKt {
    private static ImageVector _movieCreation;

    public static final ImageVector getMovieCreation(Icons.Outlined outlined) {
        ImageVector imageVector = _movieCreation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MovieCreation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = b.b(5.76f, 10.0f, 20.0f, 8.0f, 4.0f);
        pathBuilderB.verticalLineTo(6.47f);
        pathBuilderB.moveTo(22.0f, 4.0f);
        pathBuilderB.horizontalLineToRelative(-4.0f);
        pathBuilderB.lineToRelative(2.0f, 4.0f);
        e.C(pathBuilderB, -3.0f, -2.0f, -4.0f, -2.0f);
        pathBuilderB.lineToRelative(2.0f, 4.0f);
        a.n(pathBuilderB, -3.0f, -2.0f, -4.0f, 8.0f);
        pathBuilderB.lineToRelative(2.0f, 4.0f);
        b.q(pathBuilderB, 7.0f, 5.0f, 4.0f, 4.0f);
        pathBuilderB.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderB.verticalLineToRelative(12.0f);
        pathBuilderB.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderB.horizontalLineToRelative(16.0f);
        pathBuilderB.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderB, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _movieCreation = imageVectorBuild;
        return imageVectorBuild;
    }
}
