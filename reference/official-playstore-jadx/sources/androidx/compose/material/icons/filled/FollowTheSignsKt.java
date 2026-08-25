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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_followTheSigns", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FollowTheSigns", "Landroidx/compose/material/icons/Icons$Filled;", "getFollowTheSigns$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getFollowTheSigns", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FollowTheSignsKt {
    private static ImageVector _followTheSigns;

    public static final ImageVector getFollowTheSigns(Icons.Filled filled) {
        ImageVector imageVector = _followTheSigns;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FollowTheSigns", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(8.4f, 5.5f, 9.5f, 5.5f);
        a.p(pathBuilderA, 5.75f, 8.9f, 3.0f, 23.0f);
        pathBuilderA.horizontalLineToRelative(2.1f);
        pathBuilderA.lineToRelative(1.75f, -8.0f);
        a.h(pathBuilderA, 9.0f, 17.0f, 6.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(-7.55f);
        pathBuilderA.lineTo(8.95f, 13.4f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveTo(10.85f, 12.0f, 12.8f, 13.0f, 15.0f, 13.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-1.85f, 0.0f, -3.45f, -1.0f, -4.35f, -2.45f);
        pathBuilderA.lineTo(9.7f, 6.95f);
        pathBuilderA.curveTo(9.35f, 6.35f, 8.7f, 6.0f, 8.0f, 6.0f);
        pathBuilderA.curveTo(7.75f, 6.0f, 7.5f, 6.05f, 7.25f, 6.15f);
        b.n(pathBuilderA, 2.0f, 8.3f, 13.0f, 2.0f);
        pathBuilderA.verticalLineTo(9.65f);
        pathBuilderA.lineTo(5.75f, 8.9f);
        c.m(pathBuilderA, 13.0f, 2.0f, 7.0f, 3.75f);
        c.p(pathBuilderA, 14.0f, 1.5f, 9.0f, 22.0f);
        c.n(pathBuilderA, 2.0f, 13.0f, 18.01f, 8.0f);
        c.o(pathBuilderA, 6.25f, 14.5f, -1.5f, 3.51f);
        pathBuilderA.verticalLineTo(3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 2.49f, 2.5f, 18.01f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _followTheSigns = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getFollowTheSigns$annotations(Icons.Filled filled) {
    }
}
