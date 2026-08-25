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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_disabledByDefault", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DisabledByDefault", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDisabledByDefault", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DisabledByDefaultKt {
    private static ImageVector _disabledByDefault;

    public static final ImageVector getDisabledByDefault(Icons.TwoTone twoTone) {
        ImageVector imageVector = _disabledByDefault;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DisabledByDefault", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(5.0f, 5.0f, 14.0f, 14.0f, 5.0f);
        v.a.x(pathBuilderQ, 5.0f, 17.0f, 15.59f);
        pathBuilderQ.lineTo(15.59f, 17.0f);
        pathBuilderQ.lineTo(12.0f, 13.41f);
        pathBuilderQ.lineTo(8.41f, 17.0f);
        pathBuilderQ.lineTo(7.0f, 15.59f);
        pathBuilderQ.lineTo(10.59f, 12.0f);
        pathBuilderQ.lineTo(7.0f, 8.41f);
        pathBuilderQ.lineTo(8.41f, 7.0f);
        pathBuilderQ.lineTo(12.0f, 10.59f);
        pathBuilderQ.lineTo(15.59f, 7.0f);
        pathBuilderQ.lineTo(17.0f, 8.41f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderQ, 13.41f, 12.0f, 17.0f, 15.59f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = v.a.c(19.0f, 19.0f, 5.0f, 5.0f, 14.0f);
        a.k(pathBuilderC, 19.0f, 3.0f, 3.0f, 18.0f);
        a.o(pathBuilderC, 18.0f, 3.0f, 3.0f);
        pathBuilderC.moveTo(17.0f, 15.59f);
        pathBuilderC.lineTo(15.59f, 17.0f);
        pathBuilderC.lineTo(12.0f, 13.41f);
        pathBuilderC.lineTo(8.41f, 17.0f);
        pathBuilderC.lineTo(7.0f, 15.59f);
        pathBuilderC.lineTo(10.59f, 12.0f);
        pathBuilderC.lineTo(7.0f, 8.41f);
        pathBuilderC.lineTo(8.41f, 7.0f);
        pathBuilderC.lineTo(12.0f, 10.59f);
        pathBuilderC.lineTo(15.59f, 7.0f);
        pathBuilderC.lineTo(17.0f, 8.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderC, 13.41f, 12.0f, 17.0f, 15.59f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _disabledByDefault = imageVectorBuild;
        return imageVectorBuild;
    }
}
