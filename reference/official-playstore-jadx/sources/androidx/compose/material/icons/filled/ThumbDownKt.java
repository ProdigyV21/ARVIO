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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbDown", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbDown", "Landroidx/compose/material/icons/Icons$Filled;", "getThumbDown", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbDownKt {
    private static ImageVector _thumbDown;

    public static final ImageVector getThumbDown(Icons.Filled filled) {
        ImageVector imageVector = _thumbDown;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ThumbDown", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.0f, 3.0f, 6.0f, 3.0f);
        pathBuilderJ.curveToRelative(-0.83f, 0.0f, -1.54f, 0.5f, -1.84f, 1.22f);
        pathBuilderJ.lineToRelative(-3.02f, 7.05f);
        pathBuilderJ.curveToRelative(-0.09f, 0.23f, -0.14f, 0.47f, -0.14f, 0.73f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(6.31f);
        pathBuilderJ.lineToRelative(-0.95f, 4.57f);
        pathBuilderJ.lineToRelative(-0.03f, 0.32f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, 0.17f, 0.79f, 0.44f, 1.06f);
        pathBuilderJ.lineTo(9.83f, 23.0f);
        pathBuilderJ.lineToRelative(6.59f, -6.59f);
        pathBuilderJ.curveToRelative(0.36f, -0.36f, 0.58f, -0.86f, 0.58f, -1.41f);
        pathBuilderJ.lineTo(17.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        c.y(pathBuilderJ, 19.0f, 3.0f, 12.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderJ, 23.0f, 3.0f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbDown = imageVectorBuild;
        return imageVectorBuild;
    }
}
