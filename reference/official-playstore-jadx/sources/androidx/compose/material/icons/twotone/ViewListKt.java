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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_viewList", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewList", "Landroidx/compose/material/icons/Icons$TwoTone;", "getViewList$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getViewList", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewListKt {
    private static ImageVector _viewList;

    public static final ImageVector getViewList(Icons.TwoTone twoTone) {
        ImageVector imageVector = _viewList;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ViewList", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = f.b(7.0f, 7.0f, 2.0f, 5.0f, 7.0f);
        f.l(pathBuilderB, 7.0f, 5.0f, 13.0f, -2.0f);
        a.q(pathBuilderB, 2.0f, 2.0f, 5.0f);
        v.a.C(pathBuilderB, 5.0f, 15.0f, 2.0f, 2.0f);
        c.l(pathBuilderB, 5.0f, 15.0f, 19.0f, 17.0f);
        c.k(pathBuilderB, 9.0f, -2.0f, 10.0f, 17.0f);
        v.a.A(pathBuilderB, 19.0f, 13.0f, 9.0f, -2.0f);
        c.D(pathBuilderB, 10.0f, 13.0f, 19.0f, 9.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.i(pathBuilderB, 9.0f, 7.0f, 10.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = w.a.q(3.0f, 5.0f, 14.0f, 18.0f, 5.0f);
        f.l(pathBuilderQ, 3.0f, 7.0f, 7.0f, 2.0f);
        f.f(pathBuilderQ, 5.0f, 7.0f, 7.0f);
        c.m(pathBuilderQ, 5.0f, 13.0f, -2.0f, 2.0f);
        e.y(pathBuilderQ, 2.0f, 5.0f, 5.0f, 15.0f);
        d.z(pathBuilderQ, 2.0f, 2.0f, 5.0f, 15.0f);
        v.a.A(pathBuilderQ, 19.0f, 17.0f, 9.0f, -2.0f);
        c.D(pathBuilderQ, 10.0f, 17.0f, 19.0f, 13.0f);
        c.k(pathBuilderQ, 9.0f, -2.0f, 10.0f, 13.0f);
        c.B(pathBuilderQ, 19.0f, 9.0f, 9.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 10.0f, 9.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewList = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getViewList$annotations(Icons.TwoTone twoTone) {
    }
}
