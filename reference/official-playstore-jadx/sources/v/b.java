package v;

import androidx.compose.ui.graphics.vector.PathBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static void A(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static void B(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.close();
        pathBuilder.moveTo(f12, f13);
    }

    public static void C(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.close();
        pathBuilder.moveTo(f12, f13);
    }

    public static void D(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.lineToRelative(f12, f13);
        pathBuilder.close();
    }

    public static PathBuilder a(float f10, float f11, float f12, float f13) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
        return pathBuilder;
    }

    public static PathBuilder b(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.lineToRelative(f13, f14);
        return pathBuilder;
    }

    public static List c(PathBuilder pathBuilder, float f10) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List d(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List e(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.lineTo(f12, f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void f(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.close();
        pathBuilder.moveTo(f11, f12);
    }

    public static void g(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.close();
    }

    public static PathBuilder h(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.horizontalLineToRelative(f14);
        return pathBuilder;
    }

    public static List i(PathBuilder pathBuilder, float f10) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List j(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List k(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void l(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.close();
    }

    public static void m(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static void n(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineToRelative(f13);
    }

    public static void o(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineToRelative(f13);
    }

    public static void p(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineTo(f13);
    }

    public static void q(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static void r(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
    }

    public static List s(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.lineToRelative(f11, f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List t(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.lineToRelative(f12, f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void u(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
    }

    public static List v(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.lineToRelative(f12, f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void w(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.close();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static List x(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void y(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.lineTo(f12, f13);
        pathBuilder.close();
    }

    public static void z(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
    }
}
