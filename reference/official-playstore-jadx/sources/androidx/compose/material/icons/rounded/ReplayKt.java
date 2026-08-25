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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_replay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Replay", "Landroidx/compose/material/icons/Icons$Rounded;", "getReplay", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReplayKt {
    private static ImageVector _replay;

    public static final ImageVector getReplay(Icons.Rounded rounded) {
        ImageVector imageVector = _replay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Replay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(12.0f, 5.0f, 2.21f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderA.lineToRelative(-3.8f, 3.79f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderA.lineToRelative(3.79f, 3.79f);
        pathBuilderA.curveToRelative(0.32f, 0.31f, 0.86f, 0.09f, 0.86f, -0.36f);
        pathBuilderA.verticalLineTo(7.0f);
        pathBuilderA.curveToRelative(3.73f, 0.0f, 6.68f, 3.42f, 5.86f, 7.29f);
        pathBuilderA.curveToRelative(-0.47f, 2.27f, -2.31f, 4.1f, -4.57f, 4.57f);
        pathBuilderA.curveToRelative(-3.57f, 0.75f, -6.75f, -1.7f, -7.23f, -5.01f);
        pathBuilderA.curveToRelative(-0.07f, -0.48f, -0.49f, -0.85f, -0.98f, -0.85f);
        pathBuilderA.curveToRelative(-0.6f, 0.0f, -1.08f, 0.53f, -1.0f, 1.13f);
        pathBuilderA.curveToRelative(0.62f, 4.39f, 4.8f, 7.64f, 9.53f, 6.72f);
        pathBuilderA.curveToRelative(3.12f, -0.61f, 5.63f, -3.12f, 6.24f, -6.24f);
        pathBuilderA.curveTo(20.84f, 9.48f, 16.94f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _replay = imageVectorBuild;
        return imageVectorBuild;
    }
}
