package androidx.compose.material.icons.filled;

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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_listAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ListAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getListAlt$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getListAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListAltKt {
    private static ImageVector _listAlt;

    public static final ImageVector getListAlt(Icons.Filled filled) {
        ImageVector imageVector = _listAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ListAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(19.0f, 5.0f, 14.0f, 5.0f, 19.0f);
        pathBuilderS.lineTo(5.0f, 5.0f);
        pathBuilderS.horizontalLineToRelative(14.0f);
        pathBuilderS.moveToRelative(1.1f, -2.0f);
        pathBuilderS.lineTo(3.9f, 3.0f);
        pathBuilderS.curveToRelative(-0.5f, 0.0f, -0.9f, 0.4f, -0.9f, 0.9f);
        pathBuilderS.verticalLineToRelative(16.2f);
        pathBuilderS.curveToRelative(0.0f, 0.4f, 0.4f, 0.9f, 0.9f, 0.9f);
        pathBuilderS.horizontalLineToRelative(16.2f);
        pathBuilderS.curveToRelative(0.4f, 0.0f, 0.9f, -0.5f, 0.9f, -0.9f);
        pathBuilderS.lineTo(21.0f, 3.9f);
        pathBuilderS.curveToRelative(0.0f, -0.5f, -0.5f, -0.9f, -0.9f, -0.9f);
        b.w(pathBuilderS, 11.0f, 7.0f, 6.0f, 2.0f);
        d.q(pathBuilderS, -6.0f, 11.0f, 7.0f);
        a.C(pathBuilderS, 11.0f, 11.0f, 6.0f, 2.0f);
        b.B(pathBuilderS, -6.0f, -2.0f, 11.0f, 15.0f);
        d.x(pathBuilderS, 6.0f, 2.0f, -6.0f);
        a.C(pathBuilderS, 7.0f, 7.0f, 2.0f, 2.0f);
        c.z(pathBuilderS, 7.0f, 9.0f, 7.0f, 11.0f);
        d.m(pathBuilderS, 2.0f, 2.0f, 7.0f, 13.0f);
        a.C(pathBuilderS, 7.0f, 15.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 7.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _listAlt = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getListAlt$annotations(Icons.Filled filled) {
    }
}
