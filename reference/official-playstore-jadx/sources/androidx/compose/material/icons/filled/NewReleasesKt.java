package androidx.compose.material.icons.filled;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_newReleases", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NewReleases", "Landroidx/compose/material/icons/Icons$Filled;", "getNewReleases", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NewReleasesKt {
    private static ImageVector _newReleases;

    public static final ImageVector getNewReleases(Icons.Filled filled) {
        ImageVector imageVector = _newReleases;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NewReleases", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(23.0f, 12.0f, -2.44f, -2.78f);
        pathBuilderR.lineToRelative(0.34f, -3.68f);
        pathBuilderR.lineToRelative(-3.61f, -0.82f);
        pathBuilderR.lineToRelative(-1.89f, -3.18f);
        pathBuilderR.lineTo(12.0f, 3.0f);
        pathBuilderR.lineTo(8.6f, 1.54f);
        pathBuilderR.lineTo(6.71f, 4.72f);
        pathBuilderR.lineToRelative(-3.61f, 0.81f);
        pathBuilderR.lineToRelative(0.34f, 3.68f);
        pathBuilderR.lineTo(1.0f, 12.0f);
        pathBuilderR.lineToRelative(2.44f, 2.78f);
        pathBuilderR.lineToRelative(-0.34f, 3.69f);
        pathBuilderR.lineToRelative(3.61f, 0.82f);
        pathBuilderR.lineToRelative(1.89f, 3.18f);
        pathBuilderR.lineTo(12.0f, 21.0f);
        pathBuilderR.lineToRelative(3.4f, 1.46f);
        pathBuilderR.lineToRelative(1.89f, -3.18f);
        pathBuilderR.lineToRelative(3.61f, -0.82f);
        d.C(pathBuilderR, -0.34f, -3.68f, 23.0f, 12.0f);
        a.C(pathBuilderR, 13.0f, 17.0f, -2.0f, -2.0f);
        b.B(pathBuilderR, 2.0f, 2.0f, 13.0f, 13.0f);
        c.A(pathBuilderR, -2.0f, 11.0f, 7.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderR, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _newReleases = imageVectorBuild;
        return imageVectorBuild;
    }
}
