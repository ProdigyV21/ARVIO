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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_quiz", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Quiz", "Landroidx/compose/material/icons/Icons$Outlined;", "getQuiz", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QuizKt {
    private static ImageVector _quiz;

    public static final ImageVector getQuiz(Icons.Outlined outlined) {
        ImageVector imageVector = _quiz;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Quiz", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(4.0f, 6.0f, 2.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        d.z(pathBuilderA, 14.0f, -2.0f, 4.0f, 6.0f);
        pathBuilderA.moveTo(20.0f, 2.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.curveTo(6.9f, 2.0f, 6.0f, 2.9f, 6.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        f.o(pathBuilderA, 20.0f, 16.0f, 8.0f, 4.0f);
        c.D(pathBuilderA, 12.0f, 16.0f, 13.51f, 10.16f);
        pathBuilderA.curveToRelative(0.41f, -0.73f, 1.18f, -1.16f, 1.63f, -1.8f);
        pathBuilderA.curveToRelative(0.48f, -0.68f, 0.21f, -1.94f, -1.14f, -1.94f);
        pathBuilderA.curveToRelative(-0.88f, 0.0f, -1.32f, 0.67f, -1.5f, 1.23f);
        pathBuilderA.lineToRelative(-1.37f, -0.57f);
        pathBuilderA.curveTo(11.51f, 5.96f, 12.52f, 5.0f, 13.99f, 5.0f);
        pathBuilderA.curveToRelative(1.23f, 0.0f, 2.08f, 0.56f, 2.51f, 1.26f);
        pathBuilderA.curveToRelative(0.37f, 0.6f, 0.58f, 1.73f, 0.01f, 2.57f);
        pathBuilderA.curveToRelative(-0.63f, 0.93f, -1.23f, 1.21f, -1.56f, 1.81f);
        pathBuilderA.curveToRelative(-0.13f, 0.24f, -0.18f, 0.4f, -0.18f, 1.18f);
        pathBuilderA.horizontalLineToRelative(-1.52f);
        pathBuilderA.curveTo(13.26f, 11.41f, 13.19f, 10.74f, 13.51f, 10.16f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.95f, 13.95f);
        pathBuilderA.curveToRelative(0.0f, -0.59f, 0.47f, -1.04f, 1.05f, -1.04f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.04f, 0.45f, 1.04f, 1.04f);
        pathBuilderA.curveToRelative(0.0f, 0.58f, -0.44f, 1.05f, -1.04f, 1.05f);
        pathBuilderA.curveTo(13.42f, 15.0f, 12.95f, 14.53f, 12.95f, 13.95f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _quiz = imageVectorBuild;
        return imageVectorBuild;
    }
}
