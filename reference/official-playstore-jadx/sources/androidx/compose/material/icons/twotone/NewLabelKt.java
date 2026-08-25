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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_newLabel", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NewLabel", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNewLabel", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NewLabelKt {
    private static ImageVector _newLabel;

    public static final ImageVector getNewLabel(Icons.TwoTone twoTone) {
        ImageVector imageVector = _newLabel;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NewLabel", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 17.0f, 0.0f, -4.0f);
        pathBuilderR.lineToRelative(-3.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -3.0f);
        pathBuilderR.lineToRelative(-4.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -3.0f);
        pathBuilderR.lineToRelative(10.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 3.55f, 5.0f, -3.55f, 5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(21.0f, 12.0f, -4.37f, 6.16f);
        pathBuilderR2.curveTo(16.26f, 18.68f, 15.65f, 19.0f, 15.0f, 19.0f);
        e.C(pathBuilderR2, -3.0f, 0.0f, -2.0f, 3.0f);
        pathBuilderR2.lineToRelative(3.55f, -5.0f);
        b.m(pathBuilderR2, 15.0f, 7.0f, 5.0f, 3.0f);
        pathBuilderR2.horizontalLineTo(3.0f);
        pathBuilderR2.verticalLineTo(7.0f);
        pathBuilderR2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR2.horizontalLineToRelative(10.0f);
        pathBuilderR2.curveToRelative(0.65f, 0.0f, 1.26f, 0.31f, 1.63f, 0.84f);
        c.z(pathBuilderR2, 21.0f, 12.0f, 10.0f, 15.0f);
        w.a.A(pathBuilderR2, 7.0f, -3.0f, 5.0f, 3.0f);
        b.q(pathBuilderR2, 2.0f, 2.0f, 3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderR2, 2.0f, -3.0f, 3.0f, 15.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _newLabel = imageVectorBuild;
        return imageVectorBuild;
    }
}
