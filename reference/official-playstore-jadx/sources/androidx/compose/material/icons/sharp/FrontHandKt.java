package androidx.compose.material.icons.sharp;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_frontHand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FrontHand", "Landroidx/compose/material/icons/Icons$Sharp;", "getFrontHand", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FrontHandKt {
    private static ImageVector _frontHand;

    public static final ImageVector getFrontHand(Icons.Sharp sharp) {
        ImageVector imageVector = _frontHand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.FrontHand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.5f, 8.0f);
        pathBuilder.verticalLineToRelative(7.0f);
        pathBuilder.horizontalLineTo(18.0f);
        pathBuilder.curveToRelative(-1.65f, 0.0f, -3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilder.horizontalLineToRelative(-1.0f);
        pathBuilder.curveToRelative(0.0f, -2.04f, 1.53f, -3.72f, 3.5f, -3.97f);
        c.o(pathBuilder, 2.0f, 15.0f, 9.0f, -1.0f);
        b.r(pathBuilder, 0.0f, -2.5f, 11.0f, -1.0f);
        pathBuilder.verticalLineTo(1.5f);
        pathBuilder.horizontalLineTo(8.0f);
        pathBuilder.verticalLineTo(12.0f);
        pathBuilder.horizontalLineTo(7.0f);
        a.s(pathBuilder, 4.5f, 4.5f, 11.25f);
        pathBuilder.curveToRelative(0.0f, 4.56f, 3.69f, 8.25f, 8.25f, 8.25f);
        pathBuilder.reflectiveCurveTo(21.0f, 20.31f, 21.0f, 15.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilder, 8.0f, 18.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _frontHand = imageVectorBuild;
        return imageVectorBuild;
    }
}
