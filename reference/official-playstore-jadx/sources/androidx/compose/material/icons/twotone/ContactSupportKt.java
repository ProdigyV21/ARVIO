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
import v.f;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_contactSupport", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactSupport", "Landroidx/compose/material/icons/Icons$TwoTone;", "getContactSupport$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getContactSupport", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactSupportKt {
    private static ImageVector _contactSupport;

    public static final ImageVector getContactSupport(Icons.TwoTone twoTone) {
        ImageVector imageVector = _contactSupport;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ContactSupport", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.5f, 3.0f);
        pathBuilderA.curveTo(7.36f, 3.0f, 4.0f, 6.36f, 4.0f, 10.5f);
        pathBuilderA.reflectiveCurveTo(7.36f, 18.0f, 11.5f, 18.0f);
        pathBuilderA.lineTo(13.0f, 18.0f);
        pathBuilderA.verticalLineToRelative(2.3f);
        pathBuilderA.curveToRelative(3.64f, -2.3f, 6.0f, -6.08f, 6.0f, -9.8f);
        pathBuilderA.curveTo(19.0f, 6.36f, 15.64f, 3.0f, 11.5f, 3.0f);
        b.w(pathBuilderA, 12.5f, 16.5f, -2.0f, -2.0f);
        b.B(pathBuilderA, 2.0f, 2.0f, 12.5f, 13.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -3.25f, 3.0f, -3.0f, 3.0f, -5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 2.5f, -3.0f, 2.75f, -3.0f, 5.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(11.5f, 1.0f);
        pathBuilderA2.curveTo(6.26f, 1.0f, 2.0f, 5.26f, 2.0f, 10.5f);
        pathBuilderA2.curveToRelative(0.0f, 5.07f, 3.99f, 9.23f, 9.0f, 9.49f);
        pathBuilderA2.verticalLineToRelative(3.6f);
        pathBuilderA2.lineToRelative(1.43f, -0.69f);
        pathBuilderA2.curveTo(17.56f, 20.43f, 21.0f, 15.45f, 21.0f, 10.5f);
        pathBuilderA2.curveTo(21.0f, 5.26f, 16.74f, 1.0f, 11.5f, 1.0f);
        a.p(pathBuilderA2, 13.0f, 20.3f, 13.0f, 18.0f);
        pathBuilderA2.horizontalLineToRelative(-1.5f);
        pathBuilderA2.curveTo(7.36f, 18.0f, 4.0f, 14.64f, 4.0f, 10.5f);
        pathBuilderA2.reflectiveCurveTo(7.36f, 3.0f, 11.5f, 3.0f);
        pathBuilderA2.reflectiveCurveTo(19.0f, 6.36f, 19.0f, 10.5f);
        pathBuilderA2.curveToRelative(0.0f, 3.73f, -2.36f, 7.51f, -6.0f, 9.8f);
        b.w(pathBuilderA2, 10.5f, 14.5f, 2.0f, 2.0f);
        f.x(pathBuilderA2, -2.0f, 11.5f, 4.0f);
        pathBuilderA2.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.0f, -3.0f, 1.75f, -3.0f, 5.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.25f, 3.0f, -2.5f, 3.0f, -5.0f);
        pathBuilderA2.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactSupport = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getContactSupport$annotations(Icons.TwoTone twoTone) {
    }
}
