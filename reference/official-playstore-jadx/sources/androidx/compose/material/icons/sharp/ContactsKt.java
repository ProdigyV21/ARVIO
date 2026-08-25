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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contacts", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Contacts", "Landroidx/compose/material/icons/Icons$Sharp;", "getContacts", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactsKt {
    private static ImageVector _contacts;

    public static final ImageVector getContacts(Icons.Sharp sharp) {
        ImageVector imageVector = _contacts;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Contacts", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(20.0f, 0.0f, 4.0f, 2.0f, 16.0f);
        a.D(pathBuilderH, 0.0f, 4.0f, 24.0f, 16.0f);
        pathBuilderH.verticalLineToRelative(-2.0f);
        pathBuilderH.horizontalLineTo(4.0f);
        pathBuilderH.verticalLineToRelative(2.0f);
        pathBuilderH.close();
        a.A(pathBuilderH, 22.0f, 4.0f, 2.0f, 16.0f);
        c.D(pathBuilderH, 20.0f, 4.0f, 12.0f, 6.75f);
        pathBuilderH.curveToRelative(1.24f, 0.0f, 2.25f, 1.01f, 2.25f, 2.25f);
        pathBuilderH.reflectiveCurveToRelative(-1.01f, 2.25f, -2.25f, 2.25f);
        pathBuilderH.reflectiveCurveTo(9.75f, 10.24f, 9.75f, 9.0f);
        pathBuilderH.reflectiveCurveTo(10.76f, 6.75f, 12.0f, 6.75f);
        c.C(pathBuilderH, 17.0f, 17.0f, 7.0f, -1.5f);
        pathBuilderH.curveToRelative(0.0f, -1.67f, 3.33f, -2.5f, 5.0f, -2.5f);
        pathBuilderH.reflectiveCurveToRelative(5.0f, 0.83f, 5.0f, 2.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderH, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contacts = imageVectorBuild;
        return imageVectorBuild;
    }
}
