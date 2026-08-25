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
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_starPurple500", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StarPurple500", "Landroidx/compose/material/icons/Icons$Rounded;", "getStarPurple500", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarPurple500Kt {
    private static ImageVector _starPurple500;

    public static final ImageVector getStarPurple500(Icons.Rounded rounded) {
        ImageVector imageVector = _starPurple500;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.StarPurple500", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(12.0f, 8.89f, 12.94f, 12.0f, 2.82f);
        pathBuilderT.lineToRelative(-2.27f, 1.62f);
        pathBuilderT.lineToRelative(0.93f, 3.01f);
        pathBuilderT.lineTo(12.0f, 14.79f);
        pathBuilderT.lineToRelative(-2.42f, 1.84f);
        pathBuilderT.lineToRelative(0.93f, -3.01f);
        pathBuilderT.lineTo(8.24f, 12.0f);
        pathBuilderT.horizontalLineToRelative(2.82f);
        pathBuilderT.lineTo(12.0f, 8.89f);
        pathBuilderT.moveTo(9.58f, 10.0f);
        pathBuilderT.horizontalLineTo(5.12f);
        pathBuilderT.curveToRelative(-0.97f, 0.0f, -1.37f, 1.25f, -0.58f, 1.81f);
        pathBuilderT.lineToRelative(3.64f, 2.6f);
        pathBuilderT.lineToRelative(-1.43f, 4.61f);
        pathBuilderT.curveToRelative(-0.29f, 0.93f, 0.79f, 1.68f, 1.56f, 1.09f);
        pathBuilderT.lineTo(12.0f, 17.31f);
        pathBuilderT.lineToRelative(3.69f, 2.81f);
        pathBuilderT.curveToRelative(0.77f, 0.59f, 1.85f, -0.16f, 1.56f, -1.09f);
        pathBuilderT.lineToRelative(-1.43f, -4.61f);
        pathBuilderT.lineToRelative(3.64f, -2.6f);
        pathBuilderT.curveToRelative(0.79f, -0.57f, 0.39f, -1.81f, -0.58f, -1.81f);
        pathBuilderT.horizontalLineToRelative(-4.46f);
        pathBuilderT.lineToRelative(-1.47f, -4.84f);
        pathBuilderT.curveToRelative(-0.29f, -0.95f, -1.63f, -0.95f, -1.91f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderT, 9.58f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _starPurple500 = imageVectorBuild;
        return imageVectorBuild;
    }
}
