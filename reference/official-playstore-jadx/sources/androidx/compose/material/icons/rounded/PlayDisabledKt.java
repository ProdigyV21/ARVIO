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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_playDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlayDisabled", "Landroidx/compose/material/icons/Icons$Rounded;", "getPlayDisabled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlayDisabledKt {
    private static ImageVector _playDisabled;

    public static final ImageVector getPlayDisabled(Icons.Rounded rounded) {
        ImageVector imageVector = _playDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PlayDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.1f, 3.51f, 2.1f, 3.51f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(5.9f, 5.9f);
        pathBuilderJ.verticalLineToRelative(6.35f);
        pathBuilderJ.curveToRelative(0.0f, 0.79f, 0.87f, 1.27f, 1.54f, 0.84f);
        pathBuilderJ.lineToRelative(3.45f, -2.2f);
        pathBuilderJ.lineToRelative(6.08f, 6.08f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineTo(3.51f, 3.51f);
        pathBuilderJ.curveTo(3.12f, 3.12f, 2.49f, 3.12f, 2.1f, 3.51f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(17.68f, 12.84f);
        pathBuilderJ.curveToRelative(0.62f, -0.39f, 0.62f, -1.29f, 0.0f, -1.69f);
        pathBuilderJ.lineTo(9.54f, 5.98f);
        pathBuilderJ.curveTo(9.27f, 5.81f, 8.97f, 5.79f, 8.7f, 5.87f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 7.75f, 7.75f, 17.68f, 12.84f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
