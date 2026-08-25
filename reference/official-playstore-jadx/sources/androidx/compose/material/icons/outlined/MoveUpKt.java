package androidx.compose.material.icons.outlined;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_moveUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MoveUp", "Landroidx/compose/material/icons/Icons$Outlined;", "getMoveUp", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MoveUpKt {
    private static ImageVector _moveUp;

    public static final ImageVector getMoveUp(Icons.Outlined outlined) {
        ImageVector imageVector = _moveUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MoveUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, -2.45f, 1.76f, -4.47f, 4.08f, -4.91f);
        pathBuilderA.lineTo(5.59f, 9.59f);
        pathBuilderA.lineTo(7.0f, 11.0f);
        pathBuilderA.lineToRelative(4.0f, -4.01f);
        pathBuilderA.lineTo(7.0f, 3.0f);
        pathBuilderA.lineTo(5.59f, 4.41f);
        pathBuilderA.lineToRelative(1.58f, 1.58f);
        pathBuilderA.lineToRelative(0.0f, 0.06f);
        pathBuilderA.curveTo(3.7f, 6.46f, 1.0f, 9.42f, 1.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 3.87f, 3.13f, 7.0f, 7.0f, 7.0f);
        a.i(pathBuilderA, 3.0f, -2.0f, 8.0f);
        pathBuilderA.curveTo(5.24f, 18.0f, 3.0f, 15.76f, 3.0f, 13.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = y.a.u(13.0f, 13.0f, 7.0f, 9.0f, -7.0f);
        w.a.v(pathBuilderU, 13.0f, 20.0f, 18.0f, -5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderU, -3.0f, 5.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(13.0f, 4.0f, 9.0f, 7.0f, -9.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _moveUp = imageVectorBuild;
        return imageVectorBuild;
    }
}
