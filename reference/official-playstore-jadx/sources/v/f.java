package v;

import androidx.compose.ui.graphics.vector.PathBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static void A(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineTo(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineTo(f13);
        pathBuilder.close();
    }

    public static /* synthetic */ String B(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "OUT_VARIANCE" : "IN_VARIANCE" : "INVARIANT";
    }

    public static PathBuilder a(float f10, float f11, float f12, float f13) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineToRelative(f13);
        return pathBuilder;
    }

    public static PathBuilder b(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.verticalLineTo(f14);
        return pathBuilder;
    }

    public static List c(PathBuilder pathBuilder, float f10, float f11) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List d(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List e(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.lineTo(f11, f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void f(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.verticalLineTo(f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.close();
    }

    public static void g(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineTo(f13);
    }

    public static void h(StringBuilder sb2, boolean z, String str, boolean z5, String str2) {
        sb2.append(z);
        sb2.append(str);
        sb2.append(z5);
        sb2.append(str2);
    }

    public static List i(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.lineTo(f11, f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List j(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void k(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.close();
    }

    public static void l(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.close();
        pathBuilder.moveTo(f11, f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static List m(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void n(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.close();
    }

    public static void o(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.close();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineTo(f13);
    }

    public static void p(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.close();
    }

    public static void q(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.lineToRelative(f11, f12);
        pathBuilder.horizontalLineToRelative(f13);
    }

    public static void r(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.close();
        pathBuilder.moveTo(f11, f12);
    }

    public static void s(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.moveTo(f11, f12);
        pathBuilder.horizontalLineTo(f13);
    }

    public static void t(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineTo(f12);
    }

    public static void u(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineTo(f13);
    }

    public static void v(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.close();
    }

    public static void w(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineTo(f13);
        pathBuilder.close();
    }

    public static void x(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.close();
        pathBuilder.moveTo(f11, f12);
    }

    public static void y(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.lineToRelative(f12, f13);
    }

    public static void z(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.close();
    }
}
