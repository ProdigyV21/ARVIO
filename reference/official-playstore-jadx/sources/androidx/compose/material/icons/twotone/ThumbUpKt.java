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
import v.a;
import v.b;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thumbUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ThumbUp", "Landroidx/compose/material/icons/Icons$TwoTone;", "getThumbUp", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThumbUpKt {
    private static ImageVector _thumbUp;

    public static final ImageVector getThumbUp(Icons.TwoTone twoTone) {
        ImageVector imageVector = _thumbUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ThumbUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(21.0f, 12.0f, -2.0f, -9.0f);
        pathBuilderA.lineToRelative(1.34f, -5.34f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.j(pathBuilderA, 9.0f, 9.0f, 10.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(9.0f, 21.0f, 9.0f);
        pathBuilderA2.curveToRelative(0.83f, 0.0f, 1.54f, -0.5f, 1.84f, -1.22f);
        pathBuilderA2.lineToRelative(3.02f, -7.05f);
        pathBuilderA2.curveToRelative(0.09f, -0.23f, 0.14f, -0.47f, 0.14f, -0.73f);
        pathBuilderA2.verticalLineToRelative(-2.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA2.horizontalLineToRelative(-6.31f);
        pathBuilderA2.lineToRelative(0.95f, -4.57f);
        pathBuilderA2.lineToRelative(0.03f, -0.32f);
        pathBuilderA2.curveToRelative(0.0f, -0.41f, -0.17f, -0.79f, -0.44f, -1.06f);
        pathBuilderA2.lineTo(14.17f, 1.0f);
        pathBuilderA2.lineTo(7.58f, 7.59f);
        pathBuilderA2.curveTo(7.22f, 7.95f, 7.0f, 8.45f, 7.0f, 9.0f);
        pathBuilderA2.verticalLineToRelative(10.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.z(pathBuilderA2, 9.0f, 9.0f, 4.34f, -4.34f);
        b.A(pathBuilderA2, 12.0f, 10.0f, 9.0f, 2.0f);
        pathBuilderA2.lineToRelative(-3.0f, 7.0f);
        pathBuilderA2.horizontalLineTo(9.0f);
        pathBuilderA2.verticalLineTo(9.0f);
        pathBuilderA2.close();
        a.C(pathBuilderA2, 1.0f, 9.0f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA2, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thumbUp = imageVectorBuild;
        return imageVectorBuild;
    }
}
