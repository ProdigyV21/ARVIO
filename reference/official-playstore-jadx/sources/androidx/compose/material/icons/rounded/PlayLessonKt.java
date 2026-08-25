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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_playLesson", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PlayLesson", "Landroidx/compose/material/icons/Icons$Rounded;", "getPlayLesson", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PlayLessonKt {
    private static ImageVector _playLesson;

    public static final ImageVector getPlayLesson(Icons.Rounded rounded) {
        ImageVector imageVector = _playLesson;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PlayLesson", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.0f, 11.0f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.67f, 0.03f, 1.0f, 0.08f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 2.0f, 3.0f, 2.9f, 3.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(7.26f);
        pathBuilderA.curveTo(11.47f, 20.87f, 11.0f, 19.49f, 11.0f, 18.0f);
        pathBuilderA.curveTo(11.0f, 14.13f, 14.13f, 11.0f, 18.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.76f, 10.55f);
        pathBuilderA.curveTo(7.42f, 10.75f, 7.0f, 10.51f, 7.0f, 10.12f);
        a.j(pathBuilderA, 4.0f, 5.0f, 6.12f);
        pathBuilderA.curveToRelative(0.0f, 0.39f, -0.42f, 0.63f, -0.76f, 0.43f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderA, 9.5f, 9.5f, 7.76f, 10.55f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(18.0f, 13.0f);
        pathBuilderA2.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA2.reflectiveCurveTo(20.76f, 13.0f, 18.0f, 13.0f);
        d.k(pathBuilderA2, 16.75f, 19.6f, -3.2f);
        pathBuilderA2.curveToRelative(0.0f, -0.39f, 0.43f, -0.63f, 0.76f, -0.42f);
        pathBuilderA2.lineToRelative(2.56f, 1.6f);
        pathBuilderA2.curveToRelative(0.31f, 0.2f, 0.31f, 0.65f, 0.0f, 0.85f);
        pathBuilderA2.lineToRelative(-2.56f, 1.6f);
        pathBuilderA2.curveTo(17.18f, 20.23f, 16.75f, 19.99f, 16.75f, 19.6f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _playLesson = imageVectorBuild;
        return imageVectorBuild;
    }
}
