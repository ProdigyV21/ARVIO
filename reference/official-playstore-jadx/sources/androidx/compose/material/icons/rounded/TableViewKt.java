package androidx.compose.material.icons.rounded;

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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tableView", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TableView", "Landroidx/compose/material/icons/Icons$Rounded;", "getTableView", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TableViewKt {
    private static ImageVector _tableView;

    public static final ImageVector getTableView(Icons.Rounded rounded) {
        ImageVector imageVector = _tableView;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TableView", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 7.0f, 9.0f);
        pathBuilderQ.curveTo(7.9f, 7.0f, 7.0f, 7.9f, 7.0f, 9.0f);
        pathBuilderQ.verticalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(9.0f);
        pathBuilderQ.curveTo(21.0f, 7.9f, 20.1f, 7.0f, 19.0f, 7.0f);
        a.p(pathBuilderQ, 19.0f, 10.0f, 19.0f, 10.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-8.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(8.0f);
        pathBuilderQ.curveTo(18.55f, 9.0f, 19.0f, 9.45f, 19.0f, 10.0f);
        c.y(pathBuilderQ, 13.0f, 15.0f, -2.0f, 2.0f);
        e.y(pathBuilderQ, 2.0f, 13.0f, 15.0f, 17.0f);
        b.f(pathBuilderQ, 2.0f, -2.0f, -2.0f, 15.0f);
        a.A(pathBuilderQ, 11.0f, 15.0f, 9.0f, -2.0f);
        c.D(pathBuilderQ, 2.0f, 15.0f, 17.0f, 13.0f);
        f.w(pathBuilderQ, 2.0f, 2.0f, -2.0f, 13.0f);
        a.C(pathBuilderQ, 9.0f, 17.0f, 2.0f, 2.0f);
        c.l(pathBuilderQ, 9.0f, 17.0f, 17.0f, 19.0f);
        b.f(pathBuilderQ, -2.0f, 2.0f, 2.0f, 17.0f);
        pathBuilderQ.moveTo(6.0f, 17.0f);
        pathBuilderQ.horizontalLineTo(5.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        c.p(pathBuilderQ, 1.0f, -2.0f, 5.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderQ, 10.0f, 1.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tableView = imageVectorBuild;
        return imageVectorBuild;
    }
}
