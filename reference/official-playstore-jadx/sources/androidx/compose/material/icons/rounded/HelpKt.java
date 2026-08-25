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
import v.b;
import v.c;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_help", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Help", "Landroidx/compose/material/icons/Icons$Rounded;", "getHelp$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getHelp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HelpKt {
    private static ImageVector _help;

    public static final ImageVector getHelp(Icons.Rounded rounded) {
        ImageVector imageVector = _help;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Help", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        b.w(pathBuilderA, 13.0f, 19.0f, -2.0f, -2.0f);
        b.B(pathBuilderA, 2.0f, 2.0f, 15.07f, 11.25f);
        pathBuilderA.lineToRelative(-0.9f, 0.92f);
        pathBuilderA.curveToRelative(-0.5f, 0.51f, -0.86f, 0.97f, -1.04f, 1.69f);
        pathBuilderA.curveToRelative(-0.08f, 0.32f, -0.13f, 0.68f, -0.13f, 1.14f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(-0.5f);
        pathBuilderA.curveToRelative(0.0f, -0.46f, 0.08f, -0.9f, 0.22f, -1.31f);
        pathBuilderA.curveToRelative(0.2f, -0.58f, 0.53f, -1.1f, 0.95f, -1.52f);
        pathBuilderA.lineToRelative(1.24f, -1.26f);
        pathBuilderA.curveToRelative(0.46f, -0.44f, 0.68f, -1.1f, 0.55f, -1.8f);
        pathBuilderA.curveToRelative(-0.13f, -0.72f, -0.69f, -1.33f, -1.39f, -1.53f);
        pathBuilderA.curveToRelative(-1.11f, -0.31f, -2.14f, 0.32f, -2.47f, 1.27f);
        pathBuilderA.curveToRelative(-0.12f, 0.37f, -0.43f, 0.65f, -0.82f, 0.65f);
        pathBuilderA.horizontalLineToRelative(-0.3f);
        pathBuilderA.curveTo(8.4f, 9.0f, 8.0f, 8.44f, 8.16f, 7.88f);
        pathBuilderA.curveToRelative(0.43f, -1.47f, 1.68f, -2.59f, 3.23f, -2.83f);
        pathBuilderA.curveToRelative(1.52f, -0.24f, 2.97f, 0.55f, 3.87f, 1.8f);
        pathBuilderA.curveToRelative(1.18f, 1.63f, 0.83f, 3.38f, -0.19f, 4.4f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _help = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getHelp$annotations(Icons.Rounded rounded) {
    }
}
