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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_viewQuilt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewQuilt", "Landroidx/compose/material/icons/Icons$TwoTone;", "getViewQuilt$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getViewQuilt", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewQuiltKt {
    private static ImageVector _viewQuilt;

    public static final ImageVector getViewQuilt(Icons.TwoTone twoTone) {
        ImageVector imageVector = _viewQuilt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ViewQuilt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(8.33f, 17.0f, 5.0f, 7.0f, 3.33f);
        a.D(pathBuilderC, 17.0f, 13.67f, 17.0f, -3.33f);
        a.t(pathBuilderC, -4.0f, 3.33f, 17.0f);
        a.C(pathBuilderC, 19.0f, 17.0f, -3.33f, -4.0f);
        c.l(pathBuilderC, 19.0f, 17.0f, 19.0f, 11.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.d(pathBuilderC, -8.67f, 7.0f, 19.0f, 11.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = w.a.q(3.0f, 5.0f, 14.0f, 18.0f, 5.0f);
        w.a.s(pathBuilderQ, 3.0f, 8.33f, 17.0f, 5.0f);
        f.k(pathBuilderQ, 7.0f, 3.33f, 17.0f);
        a.C(pathBuilderQ, 13.67f, 17.0f, -3.33f, -4.0f);
        c.D(pathBuilderQ, 3.33f, 17.0f, 19.0f, 17.0f);
        d.z(pathBuilderQ, -3.33f, -4.0f, 19.0f, 17.0f);
        e.m(pathBuilderQ, 19.0f, 11.0f, -8.67f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderQ, 19.0f, 11.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewQuilt = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getViewQuilt$annotations(Icons.TwoTone twoTone) {
    }
}
