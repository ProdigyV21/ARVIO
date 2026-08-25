package androidx.compose.material.icons.filled;

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
import v.a;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bookOnline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BookOnline", "Landroidx/compose/material/icons/Icons$Filled;", "getBookOnline", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BookOnlineKt {
    private static ImageVector _bookOnline;

    public static final ImageVector getBookOnline(Icons.Filled filled) {
        ImageVector imageVector = _bookOnline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.BookOnline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.0f, 1.0f, 7.0f);
        pathBuilderQ.curveTo(5.9f, 1.0f, 5.0f, 1.9f, 5.0f, 3.0f);
        pathBuilderQ.verticalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(3.0f);
        pathBuilderQ.curveTo(19.0f, 1.9f, 18.1f, 1.0f, 17.0f, 1.0f);
        b.t(pathBuilderQ, 7.0f, 18.0f, 6.0f, 10.0f);
        e.y(pathBuilderQ, 12.0f, 7.0f, 16.0f, 11.0f);
        pathBuilderQ.verticalLineTo(9.14f);
        pathBuilderQ.curveTo(16.0f, 8.51f, 15.55f, 8.0f, 15.0f, 8.0f);
        pathBuilderQ.horizontalLineTo(9.0f);
        pathBuilderQ.curveTo(8.45f, 8.0f, 8.0f, 8.51f, 8.0f, 9.14f);
        pathBuilderQ.lineToRelative(0.0f, 1.96f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.lineToRelative(0.0f, 1.76f);
        pathBuilderQ.curveTo(8.0f, 15.49f, 8.45f, 16.0f, 9.0f, 16.0f);
        pathBuilderQ.horizontalLineToRelative(6.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, -0.51f, 1.0f, -1.14f);
        pathBuilderQ.verticalLineTo(13.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.curveTo(15.0f, 11.45f, 15.45f, 11.0f, 16.0f, 11.0f);
        v.b.w(pathBuilderQ, 12.5f, 14.5f, -1.0f, -1.0f);
        c.D(pathBuilderQ, 1.0f, 14.5f, 12.5f, 12.5f);
        f.w(pathBuilderQ, -1.0f, -1.0f, 1.0f, 12.5f);
        a.C(pathBuilderQ, 12.5f, 10.5f, -1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderQ, 1.0f, 10.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bookOnline = imageVectorBuild;
        return imageVectorBuild;
    }
}
