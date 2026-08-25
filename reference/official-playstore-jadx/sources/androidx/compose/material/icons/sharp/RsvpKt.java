package androidx.compose.material.icons.sharp;

import a0.b;
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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rsvp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rsvp", "Landroidx/compose/material/icons/Icons$Sharp;", "getRsvp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RsvpKt {
    private static ImageVector _rsvp;

    public static final ImageVector getRsvp(Icons.Sharp sharp) {
        ImageVector imageVector = _rsvp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Rsvp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(16.0f, 9.0f, 1.5f, -1.75f, 6.0f);
        a.f(pathBuilderG, -1.5f, 12.5f, 9.0f, 14.0f);
        d.C(pathBuilderG, 1.0f, 3.43f, 16.0f, 9.0f);
        pathBuilderG.moveTo(5.14f, 13.0f);
        pathBuilderG.lineTo(6.0f, 15.0f);
        b.j(pathBuilderG, 4.5f, -0.85f, -2.0f, 2.5f);
        e.A(pathBuilderG, 2.0f, 1.0f, 9.0f, 5.0f);
        y.a.n(pathBuilderG, 4.0f, 5.14f, 13.0f);
        v.a.C(pathBuilderG, 4.5f, 10.5f, -2.0f, 1.0f);
        c.D(pathBuilderG, 2.0f, 10.5f, 23.0f, 13.0f);
        d.y(pathBuilderG, -3.5f, 2.0f, 18.0f, 9.0f);
        c.D(pathBuilderG, 5.0f, 13.0f, 21.5f, 10.5f);
        f.w(pathBuilderG, -2.0f, 1.0f, 2.0f, 10.5f);
        c.m(pathBuilderG, 11.5f, 9.0f, 1.5f, -3.0f);
        c.p(pathBuilderG, 0.75f, 3.0f, 15.0f, 7.0f);
        f.u(pathBuilderG, -1.5f, 3.0f, -0.75f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderG, 9.0f, 11.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rsvp = imageVectorBuild;
        return imageVectorBuild;
    }
}
