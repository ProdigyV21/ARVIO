package androidx.compose.material.icons.twotone;

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
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_quiz", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Quiz", "Landroidx/compose/material/icons/Icons$TwoTone;", "getQuiz", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class QuizKt {
    private static ImageVector _quiz;

    public static final ImageVector getQuiz(Icons.TwoTone twoTone) {
        ImageVector imageVector = _quiz;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Quiz", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(8.0f, 4.0f, 12.0f, 12.0f, 4.0f);
        v.a.x(pathBuilderQ, 8.0f, 14.74f, 14.69f);
        pathBuilderQ.curveTo(14.54f, 14.9f, 14.3f, 15.0f, 14.01f, 15.0f);
        pathBuilderQ.curveToRelative(-0.29f, 0.0f, -0.54f, -0.1f, -0.74f, -0.31f);
        pathBuilderQ.curveToRelative(-0.21f, -0.21f, -0.31f, -0.45f, -0.31f, -0.74f);
        pathBuilderQ.curveToRelative(0.0f, -0.29f, 0.1f, -0.54f, 0.31f, -0.74f);
        pathBuilderQ.curveToRelative(0.21f, -0.2f, 0.45f, -0.3f, 0.74f, -0.3f);
        pathBuilderQ.curveToRelative(0.29f, 0.0f, 0.54f, 0.1f, 0.74f, 0.3f);
        pathBuilderQ.curveToRelative(0.2f, 0.2f, 0.3f, 0.45f, 0.3f, 0.74f);
        pathBuilderQ.curveTo(15.05f, 14.24f, 14.94f, 14.49f, 14.74f, 14.69f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(16.51f, 8.83f);
        pathBuilderQ.curveToRelative(-0.23f, 0.34f, -0.54f, 0.69f, -0.92f, 1.06f);
        pathBuilderQ.curveToRelative(-0.3f, 0.27f, -0.51f, 0.52f, -0.64f, 0.75f);
        pathBuilderQ.curveToRelative(-0.12f, 0.23f, -0.18f, 0.49f, -0.18f, 0.78f);
        e.l(pathBuilderQ, 0.4f, -1.52f, -0.56f);
        pathBuilderQ.curveToRelative(0.0f, -0.42f, 0.09f, -0.78f, 0.26f, -1.09f);
        pathBuilderQ.curveTo(13.69f, 9.85f, 14.0f, 9.5f, 14.46f, 9.1f);
        pathBuilderQ.curveToRelative(0.32f, -0.29f, 0.55f, -0.54f, 0.69f, -0.74f);
        pathBuilderQ.curveToRelative(0.14f, -0.2f, 0.21f, -0.44f, 0.21f, -0.72f);
        pathBuilderQ.curveToRelative(0.0f, -0.36f, -0.12f, -0.65f, -0.36f, -0.87f);
        pathBuilderQ.curveToRelative(-0.24f, -0.23f, -0.57f, -0.34f, -0.99f, -0.34f);
        pathBuilderQ.curveToRelative(-0.4f, 0.0f, -0.72f, 0.12f, -0.97f, 0.36f);
        pathBuilderQ.curveToRelative(-0.25f, 0.24f, -0.42f, 0.53f, -0.53f, 0.87f);
        pathBuilderQ.lineToRelative(-1.37f, -0.57f);
        pathBuilderQ.curveToRelative(0.18f, -0.55f, 0.52f, -1.03f, 1.0f, -1.45f);
        pathBuilderQ.curveTo(12.63f, 5.21f, 13.25f, 5.0f, 13.99f, 5.0f);
        pathBuilderQ.curveToRelative(0.56f, 0.0f, 1.05f, 0.11f, 1.49f, 0.33f);
        pathBuilderQ.curveToRelative(0.44f, 0.22f, 0.78f, 0.53f, 1.02f, 0.93f);
        pathBuilderQ.curveToRelative(0.24f, 0.4f, 0.36f, 0.84f, 0.36f, 1.33f);
        pathBuilderQ.curveTo(16.86f, 8.08f, 16.75f, 8.49f, 16.51f, 8.83f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
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
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _quiz = imageVectorBuild;
        return imageVectorBuild;
    }
}
