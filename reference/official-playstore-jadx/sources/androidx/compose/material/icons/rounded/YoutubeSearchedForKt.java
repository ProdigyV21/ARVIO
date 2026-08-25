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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_youtubeSearchedFor", "Landroidx/compose/ui/graphics/vector/ImageVector;", "YoutubeSearchedFor", "Landroidx/compose/material/icons/Icons$Rounded;", "getYoutubeSearchedFor", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class YoutubeSearchedForKt {
    private static ImageVector _youtubeSearchedFor;

    public static final ImageVector getYoutubeSearchedFor(Icons.Rounded rounded) {
        ImageVector imageVector = _youtubeSearchedFor;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.YoutubeSearchedFor", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(17.01f, 14.0f, -0.8f, -0.27f, -0.27f);
        pathBuilderG.curveToRelative(1.15f, -1.34f, 1.76f, -3.14f, 1.51f, -5.09f);
        pathBuilderG.curveTo(17.11f, 6.0f, 15.1f, 3.78f, 12.5f, 3.18f);
        pathBuilderG.curveTo(8.26f, 2.2f, 4.51f, 5.53f, 4.51f, 9.5f);
        pathBuilderG.horizontalLineToRelative(-2.1f);
        pathBuilderG.curveToRelative(-0.47f, 0.0f, -0.68f, 0.59f, -0.31f, 0.89f);
        pathBuilderG.lineToRelative(3.4f, 2.75f);
        pathBuilderG.curveToRelative(0.19f, 0.2f, 0.51f, 0.21f, 0.71f, 0.01f);
        pathBuilderG.lineToRelative(2.9f, -2.79f);
        pathBuilderG.curveToRelative(0.32f, -0.31f, 0.1f, -0.86f, -0.35f, -0.86f);
        pathBuilderG.horizontalLineTo(6.51f);
        pathBuilderG.curveToRelative(0.0f, -2.49f, 2.0f, -4.48f, 4.46f, -4.5f);
        pathBuilderG.curveToRelative(2.44f, -0.02f, 4.54f, 2.05f, 4.54f, 4.49f);
        pathBuilderG.curveToRelative(0.0f, 2.48f, -2.02f, 4.51f, -4.5f, 4.51f);
        pathBuilderG.curveToRelative(-0.45f, 0.0f, -0.89f, -0.07f, -1.3f, -0.19f);
        pathBuilderG.curveToRelative(-0.34f, -0.1f, -0.71f, 0.0f, -0.96f, 0.26f);
        pathBuilderG.curveToRelative(-0.53f, 0.53f, -0.32f, 1.45f, 0.39f, 1.66f);
        pathBuilderG.curveToRelative(0.59f, 0.17f, 1.22f, 0.27f, 1.87f, 0.27f);
        pathBuilderG.curveToRelative(1.61f, 0.0f, 3.08f, -0.59f, 4.22f, -1.57f);
        pathBuilderG.lineToRelative(0.27f, 0.27f);
        pathBuilderG.verticalLineToRelative(0.79f);
        pathBuilderG.lineToRelative(4.27f, 4.25f);
        pathBuilderG.curveToRelative(0.41f, 0.41f, 1.07f, 0.41f, 1.48f, 0.0f);
        pathBuilderG.curveToRelative(0.41f, -0.41f, 0.41f, -1.08f, 0.0f, -1.49f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderG, 17.01f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _youtubeSearchedFor = imageVectorBuild;
        return imageVectorBuild;
    }
}
