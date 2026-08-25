package androidx.compose.material.icons.twotone;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signpost", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Signpost", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSignpost", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignpostKt {
    private static ImageVector _signpost;

    public static final ImageVector getSignpost(Icons.TwoTone twoTone) {
        ImageVector imageVector = _signpost;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Signpost", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(6.0f, 6.0f, 11.17f, 1.0f, 1.0f);
        pathBuilderG.lineToRelative(-1.0f, 1.0f);
        pathBuilderG.horizontalLineTo(6.0f);
        pathBuilderG.verticalLineTo(6.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(18.0f, 16.0f);
        pathBuilderG.horizontalLineTo(6.83f);
        pathBuilderG.lineToRelative(-1.0f, -1.0f);
        pathBuilderG.lineToRelative(1.0f, -1.0f);
        pathBuilderG.horizontalLineTo(18.0f);
        pathBuilderG.verticalLineTo(16.0f);
        pathBuilderG.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG2 = c.g(13.0f, 10.0f, 5.0f, 3.0f, -3.0f);
        a.p(pathBuilderG2, -3.0f, -3.0f, -5.0f, 2.0f);
        a.y(pathBuilderG2, -2.0f, 2.0f, 4.0f, 6.0f);
        a.i(pathBuilderG2, 7.0f, 2.0f, 6.0f);
        pathBuilderG2.lineToRelative(-3.0f, 3.0f);
        a.m(pathBuilderG2, 3.0f, 3.0f, 5.0f, 4.0f);
        v.a.o(pathBuilderG2, 2.0f, -4.0f, 7.0f, -6.0f);
        c.D(pathBuilderG2, -7.0f, 10.0f, 6.0f, 6.0f);
        pathBuilderG2.horizontalLineToRelative(11.17f);
        pathBuilderG2.lineToRelative(1.0f, 1.0f);
        pathBuilderG2.lineToRelative(-1.0f, 1.0f);
        pathBuilderG2.horizontalLineTo(6.0f);
        pathBuilderG2.verticalLineTo(6.0f);
        pathBuilderG2.close();
        pathBuilderG2.moveTo(18.0f, 16.0f);
        pathBuilderG2.horizontalLineTo(6.83f);
        pathBuilderG2.lineToRelative(-1.0f, -1.0f);
        pathBuilderG2.lineToRelative(1.0f, -1.0f);
        pathBuilderG2.horizontalLineTo(18.0f);
        pathBuilderG2.verticalLineTo(16.0f);
        pathBuilderG2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _signpost = imageVectorBuild;
        return imageVectorBuild;
    }
}
