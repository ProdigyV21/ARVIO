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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rssFeed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RssFeed", "Landroidx/compose/material/icons/Icons$Rounded;", "getRssFeed", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RssFeedKt {
    private static ImageVector _rssFeed;

    public static final ImageVector getRssFeed(Icons.Rounded rounded) {
        ImageVector imageVector = _rssFeed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RssFeed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(6.18f, 17.82f, -2.18f, 0.0f);
        pathBuilderB.arcToRelative(2.18f, 2.18f, 0.0f, true, true, 4.36f, 0.0f);
        pathBuilderB.arcToRelative(2.18f, 2.18f, 0.0f, true, true, -4.36f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.59f, 10.23f);
        pathBuilderA.curveToRelative(-0.84f, -0.14f, -1.59f, 0.55f, -1.59f, 1.4f);
        pathBuilderA.curveToRelative(0.0f, 0.71f, 0.53f, 1.28f, 1.23f, 1.4f);
        pathBuilderA.curveToRelative(2.92f, 0.51f, 5.22f, 2.82f, 5.74f, 5.74f);
        pathBuilderA.curveToRelative(0.12f, 0.7f, 0.69f, 1.23f, 1.4f, 1.23f);
        pathBuilderA.curveToRelative(0.85f, 0.0f, 1.54f, -0.75f, 1.41f, -1.59f);
        pathBuilderA.curveToRelative(-0.68f, -4.2f, -3.99f, -7.51f, -8.19f, -8.18f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.56f, 4.52f);
        pathBuilderA.curveTo(4.73f, 4.43f, 4.0f, 5.1f, 4.0f, 5.93f);
        pathBuilderA.curveToRelative(0.0f, 0.73f, 0.55f, 1.33f, 1.27f, 1.4f);
        pathBuilderA.curveToRelative(6.01f, 0.6f, 10.79f, 5.38f, 11.39f, 11.39f);
        pathBuilderA.curveToRelative(0.07f, 0.73f, 0.67f, 1.28f, 1.4f, 1.28f);
        pathBuilderA.curveToRelative(0.84f, 0.0f, 1.5f, -0.73f, 1.42f, -1.56f);
        pathBuilderA.curveToRelative(-0.73f, -7.34f, -6.57f, -13.19f, -13.92f, -13.92f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rssFeed = imageVectorBuild;
        return imageVectorBuild;
    }
}
