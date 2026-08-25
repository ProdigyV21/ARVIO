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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nearMeDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NearMeDisabled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNearMeDisabled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NearMeDisabledKt {
    private static ImageVector _nearMeDisabled;

    public static final ImageVector getNearMeDisabled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _nearMeDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NearMeDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(16.1f, 10.44f, 1.5f, -4.05f);
        d.C(pathBuilderR, -4.05f, 1.5f, 16.1f, 10.44f);
        pathBuilderR.moveTo(14.57f, 14.57f);
        pathBuilderR.lineTo(9.43f, 9.43f);
        pathBuilderR.lineToRelative(-2.71f, 1.01f);
        pathBuilderR.lineToRelative(4.89f, 1.95f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 1.95f, 4.89f, 14.57f, 14.57f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.0f, 6.34f, 21.0f, 3.0f);
        pathBuilderJ.lineToRelative(-3.34f, 9.0f);
        pathBuilderJ.lineToRelative(-1.56f, -1.56f);
        pathBuilderJ.lineToRelative(1.5f, -4.05f);
        d.C(pathBuilderJ, -4.05f, 1.5f, 12.0f, 6.34f);
        pathBuilderJ.moveTo(21.19f, 21.19f);
        pathBuilderJ.lineToRelative(-5.07f, -5.07f);
        pathBuilderJ.lineTo(14.31f, 21.0f);
        pathBuilderJ.horizontalLineTo(12.9f);
        pathBuilderJ.lineToRelative(-2.83f, -7.07f);
        pathBuilderJ.lineTo(3.0f, 11.1f);
        pathBuilderJ.verticalLineTo(9.69f);
        pathBuilderJ.lineToRelative(4.88f, -1.81f);
        pathBuilderJ.lineTo(2.81f, 2.81f);
        pathBuilderJ.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderJ, 18.38f, 18.38f, 21.19f, 21.19f);
        pathBuilderJ.moveTo(14.57f, 14.57f);
        pathBuilderJ.lineTo(9.43f, 9.43f);
        pathBuilderJ.lineToRelative(-2.71f, 1.01f);
        pathBuilderJ.lineToRelative(4.89f, 1.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, 1.95f, 4.89f, 14.57f, 14.57f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nearMeDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
