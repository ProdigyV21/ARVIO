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
import v.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_merge", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Merge", "Landroidx/compose/material/icons/Icons$Outlined;", "getMerge", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MergeKt {
    private static ImageVector _merge;

    public static final ImageVector getMerge(Icons.Outlined outlined) {
        ImageVector imageVector = _merge;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Merge", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.41f, 21.0f, 5.0f, 19.59f);
        pathBuilderJ.lineToRelative(4.83f, -4.83f);
        pathBuilderJ.curveToRelative(0.75f, -0.75f, 1.17f, -1.77f, 1.17f, -2.83f);
        pathBuilderJ.verticalLineToRelative(-5.1f);
        pathBuilderJ.lineTo(9.41f, 8.41f);
        pathBuilderJ.lineTo(8.0f, 7.0f);
        pathBuilderJ.lineToRelative(4.0f, -4.0f);
        pathBuilderJ.lineToRelative(4.0f, 4.0f);
        pathBuilderJ.lineToRelative(-1.41f, 1.41f);
        pathBuilderJ.lineTo(13.0f, 6.83f);
        pathBuilderJ.verticalLineToRelative(5.1f);
        pathBuilderJ.curveToRelative(0.0f, 1.06f, 0.42f, 2.08f, 1.17f, 2.83f);
        pathBuilderJ.lineTo(19.0f, 19.59f);
        pathBuilderJ.lineTo(17.59f, 21.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 12.0f, 15.41f, 6.41f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _merge = imageVectorBuild;
        return imageVectorBuild;
    }
}
