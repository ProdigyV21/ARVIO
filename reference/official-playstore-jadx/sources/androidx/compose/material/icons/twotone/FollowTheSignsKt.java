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
import v.b;
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_followTheSigns", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FollowTheSigns", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFollowTheSigns$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getFollowTheSigns", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FollowTheSignsKt {
    private static ImageVector _followTheSigns;

    public static final ImageVector getFollowTheSigns(Icons.TwoTone twoTone) {
        ImageVector imageVector = _followTheSigns;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FollowTheSigns", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(17.64f, 7.75f, 6.0f, -3.51f, 4.5f);
        a.o(pathBuilderM, 3.51f, 2.75f, 2.49f, 2.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderM, 17.64f, 7.75f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.12f, 5.25f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(8.02f, 5.25f, 9.12f, 5.25f);
        v.a.z(pathBuilderA, 5.38f, 8.65f, -2.75f, 14.1f);
        pathBuilderA.horizontalLineToRelative(2.1f);
        pathBuilderA.lineToRelative(1.75f, -8.0f);
        b.u(pathBuilderA, 2.15f, 2.0f, 6.0f, 2.0f);
        pathBuilderA.verticalLineTo(15.2f);
        pathBuilderA.lineToRelative(-2.05f, -2.05f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.3f, 1.6f, 3.25f, 2.6f, 5.45f, 2.6f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-1.85f, 0.0f, -3.45f, -1.0f, -4.35f, -2.45f);
        pathBuilderA.lineTo(9.32f, 6.7f);
        pathBuilderA.curveToRelative(-0.35f, -0.6f, -1.0f, -0.95f, -1.7f, -0.95f);
        pathBuilderA.curveToRelative(-0.25f, 0.0f, -0.5f, 0.05f, -0.75f, 0.15f);
        v.a.h(pathBuilderA, 1.62f, 8.05f, 4.7f, 2.0f);
        pathBuilderA.verticalLineTo(9.4f);
        pathBuilderA.lineTo(5.38f, 8.65f);
        c.m(pathBuilderA, 12.62f, 1.75f, 7.0f, 3.75f);
        b.z(pathBuilderA, 14.0f, 1.5f, -14.0f, 3.75f);
        e.y(pathBuilderA, -7.0f, 12.62f, 17.64f, 7.75f);
        d.A(pathBuilderA, 6.0f, -3.51f, 4.5f, 3.51f);
        pathBuilderA.verticalLineTo(2.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 2.49f, 2.5f, 17.64f, 7.75f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _followTheSigns = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getFollowTheSigns$annotations(Icons.TwoTone twoTone) {
    }
}
