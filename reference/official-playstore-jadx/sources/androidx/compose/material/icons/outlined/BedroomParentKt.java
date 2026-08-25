package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.b;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bedroomParent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BedroomParent", "Landroidx/compose/material/icons/Icons$Outlined;", "getBedroomParent", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BedroomParentKt {
    private static ImageVector _bedroomParent;

    public static final ImageVector getBedroomParent(Icons.Outlined outlined) {
        ImageVector imageVector = _bedroomParent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.BedroomParent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(18.35f, 11.45f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(13.0f);
        pathBuilderA.curveToRelative(-0.37f, 0.0f, -0.72f, 0.12f, -1.0f, 0.32f);
        pathBuilderA.curveTo(11.72f, 7.12f, 11.37f, 7.0f, 11.0f, 7.0f);
        pathBuilderA.horizontalLineTo(7.65f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(2.45f);
        pathBuilderA.curveTo(5.25f, 11.91f, 5.0f, 12.51f, 5.0f, 13.17f);
        b.r(pathBuilderA, 17.0f, 1.5f, -1.5f, 11.0f);
        a.s(pathBuilderA, 17.0f, 19.0f, -3.83f);
        pathBuilderA.curveTo(19.0f, 12.51f, 18.75f, 11.91f, 18.35f, 11.45f);
        b.w(pathBuilderA, 16.75f, 10.5f, -4.0f, -2.0f);
        c.D(pathBuilderA, 4.0f, 10.5f, 7.25f, 8.5f);
        f.w(pathBuilderA, 4.0f, 2.0f, -4.0f, 8.5f);
        v.a.C(pathBuilderA, 17.5f, 14.0f, -11.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(9.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        w.a.k(pathBuilderA, 14.0f, 20.0f, 4.0f, 16.0f);
        a0.b.i(pathBuilderA, 4.0f, 4.0f, 20.0f);
        pathBuilderA.moveTo(20.0f, 2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bedroomParent = imageVectorBuild;
        return imageVectorBuild;
    }
}
