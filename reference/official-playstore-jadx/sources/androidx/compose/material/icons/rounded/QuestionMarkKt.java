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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_questionMark", "Landroidx/compose/ui/graphics/vector/ImageVector;", "QuestionMark", "Landroidx/compose/material/icons/Icons$Rounded;", "getQuestionMark", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QuestionMarkKt {
    private static ImageVector _questionMark;

    public static final ImageVector getQuestionMark(Icons.Rounded rounded) {
        ImageVector imageVector = _questionMark;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.QuestionMark", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.92f, 7.54f);
        pathBuilderA.curveTo(7.12f, 7.2f, 6.78f, 6.21f, 7.26f, 5.49f);
        pathBuilderA.curveTo(8.23f, 4.05f, 9.85f, 3.0f, 11.99f, 3.0f);
        pathBuilderA.curveToRelative(2.35f, 0.0f, 3.96f, 1.07f, 4.78f, 2.41f);
        pathBuilderA.curveToRelative(0.7f, 1.15f, 1.11f, 3.3f, 0.03f, 4.9f);
        pathBuilderA.curveToRelative(-1.2f, 1.77f, -2.35f, 2.31f, -2.97f, 3.45f);
        pathBuilderA.curveToRelative(-0.15f, 0.27f, -0.24f, 0.49f, -0.3f, 0.94f);
        pathBuilderA.curveToRelative(-0.09f, 0.73f, -0.69f, 1.3f, -1.43f, 1.3f);
        pathBuilderA.curveToRelative(-0.87f, 0.0f, -1.58f, -0.75f, -1.48f, -1.62f);
        pathBuilderA.curveToRelative(0.06f, -0.51f, 0.18f, -1.04f, 0.46f, -1.54f);
        pathBuilderA.curveToRelative(0.77f, -1.39f, 2.25f, -2.21f, 3.11f, -3.44f);
        pathBuilderA.curveToRelative(0.91f, -1.29f, 0.4f, -3.7f, -2.18f, -3.7f);
        pathBuilderA.curveToRelative(-1.17f, 0.0f, -1.93f, 0.61f, -2.4f, 1.34f);
        pathBuilderA.curveTo(9.26f, 7.61f, 8.53f, 7.79f, 7.92f, 7.54f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 14.0f, 18.9f, 14.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _questionMark = imageVectorBuild;
        return imageVectorBuild;
    }
}
