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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tapAndPlay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TapAndPlay", "Landroidx/compose/material/icons/Icons$Rounded;", "getTapAndPlay", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TapAndPlayKt {
    private static ImageVector _tapAndPlay;

    public static final ImageVector getTapAndPlay(Icons.Rounded rounded) {
        ImageVector imageVector = _tapAndPlay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TapAndPlay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(3.14f, 16.09f);
        pathBuilderA.curveToRelative(-0.6f, -0.1f, -1.14f, 0.39f, -1.14f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.49f, 0.36f, 0.9f, 0.85f, 0.98f);
        pathBuilderA.curveToRelative(2.08f, 0.36f, 3.72f, 2.0f, 4.08f, 4.08f);
        pathBuilderA.curveToRelative(0.08f, 0.49f, 0.49f, 0.85f, 0.98f, 0.85f);
        pathBuilderA.curveToRelative(0.61f, 0.0f, 1.09f, -0.54f, 1.0f, -1.14f);
        pathBuilderA.curveToRelative(-0.48f, -2.95f, -2.81f, -5.29f, -5.77f, -5.77f);
        c.y(pathBuilderA, 2.0f, 20.0f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.11f, 12.06f);
        pathBuilderA.curveToRelative(-0.59f, -0.06f, -1.11f, 0.4f, -1.11f, 0.99f);
        pathBuilderA.curveToRelative(0.0f, 0.5f, 0.37f, 0.94f, 0.87f, 0.99f);
        pathBuilderA.curveToRelative(4.27f, 0.41f, 7.67f, 3.81f, 8.08f, 8.08f);
        pathBuilderA.curveToRelative(0.05f, 0.5f, 0.48f, 0.88f, 0.99f, 0.88f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.06f, -0.51f, 1.0f, -1.1f);
        pathBuilderA.curveToRelative(-0.51f, -5.2f, -4.63f, -9.32f, -9.83f, -9.84f);
        a.p(pathBuilderA, 17.0f, 1.01f, 7.0f, 1.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(7.37f);
        pathBuilderA.curveToRelative(0.69f, 0.16f, 1.36f, 0.37f, 2.0f, 0.64f);
        b.A(pathBuilderA, 7.0f, 5.0f, 10.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(-3.03f);
        pathBuilderA.curveToRelative(0.52f, 1.25f, 0.84f, 2.59f, 0.95f, 4.0f);
        pathBuilderA.lineTo(17.0f, 22.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineTo(19.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -1.99f, -2.0f, -1.99f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tapAndPlay = imageVectorBuild;
        return imageVectorBuild;
    }
}
