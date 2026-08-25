package androidx.compose.material.icons.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_domainDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DomainDisabled", "Landroidx/compose/material/icons/Icons$Sharp;", "getDomainDisabled", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DomainDisabledKt {
    private static ImageVector _domainDisabled;

    public static final ImageVector getDomainDisabled(Icons.Sharp sharp) {
        ImageVector imageVector = _domainDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DomainDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(1.41f, 1.69f, 0.0f, 3.1f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        pathBuilderJ.lineTo(2.0f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(15.9f);
        pathBuilderJ.lineToRelative(3.0f, 3.0f);
        b.D(pathBuilderJ, 1.41f, -1.41f, -20.9f, -20.9f);
        pathBuilderJ.moveTo(6.0f, 19.0f);
        a.h(pathBuilderJ, 4.0f, 19.0f, -2.0f, 2.0f);
        b.f(pathBuilderJ, 2.0f, 6.0f, 15.0f);
        a.h(pathBuilderJ, 4.0f, 15.0f, -2.0f, 2.0f);
        b.f(pathBuilderJ, 2.0f, 4.0f, 11.0f);
        b.A(pathBuilderJ, 4.0f, 9.0f, 2.0f, 2.0f);
        c.z(pathBuilderJ, 4.0f, 11.0f, 10.0f, 19.0f);
        a.h(pathBuilderJ, 8.0f, 19.0f, -2.0f, 2.0f);
        e.q(pathBuilderJ, 2.0f, 8.0f, 15.0f, -2.0f);
        d.m(pathBuilderJ, 2.0f, 2.0f, 8.0f, 15.0f);
        c.m(pathBuilderJ, 12.0f, 19.0f, -2.0f, 1.9f);
        d.C(pathBuilderJ, 2.0f, 2.0f, 12.0f, 19.0f);
        a.C(pathBuilderJ, 8.0f, 5.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(-0.45f);
        pathBuilderJ.lineTo(12.0f, 9.45f);
        b.A(pathBuilderJ, 12.0f, 9.0f, 8.0f, 8.45f);
        pathBuilderJ.lineToRelative(2.0f, 2.0f);
        pathBuilderJ.lineTo(22.0f, 7.0f);
        pathBuilderJ.lineTo(12.0f, 7.0f);
        pathBuilderJ.lineTo(12.0f, 3.0f);
        b.y(pathBuilderJ, 5.55f, 3.0f, 8.0f, 5.45f);
        a.C(pathBuilderJ, 16.0f, 11.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderJ, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _domainDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
