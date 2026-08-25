package y;

import androidx.compose.ui.graphics.vector.PathBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static PathBuilder a(float f10, float f11, float f12, float f13) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineTo(f13);
        return pathBuilder;
    }

    public static PathBuilder b(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.lineToRelative(f12, f13);
        pathBuilder.horizontalLineTo(f14);
        return pathBuilder;
    }

    public static List c(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.horizontalLineTo(f14);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List d(PathBuilder pathBuilder, float f10, float f11) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineTo(f11);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List e(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.lineToRelative(f11, f12);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static List f(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineTo(f13);
        pathBuilder.close();
        return pathBuilder.getNodes();
    }

    public static void g(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.horizontalLineTo(f11);
        pathBuilder.verticalLineToRelative(f12);
    }

    public static void h(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.close();
    }

    public static void i(StringBuilder sb2, String str, String str2, String str3, String str4) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
    }

    public static PathBuilder j(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.verticalLineToRelative(f14);
        return pathBuilder;
    }

    public static void k(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.lineToRelative(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.close();
    }

    public static void l(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineTo(f10);
        pathBuilder.horizontalLineToRelative(f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineTo(f13);
    }

    public static PathBuilder m(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineToRelative(f13);
        pathBuilder.verticalLineTo(f14);
        return pathBuilder;
    }

    public static void n(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.lineTo(f11, f12);
        pathBuilder.close();
    }

    public static void o(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineTo(f11);
        pathBuilder.lineToRelative(f12, f13);
    }

    public static PathBuilder p(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.lineTo(f13, f14);
        return pathBuilder;
    }

    public static void q(PathBuilder pathBuilder, float f10, float f11, float f12) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.close();
    }

    public static void r(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineToRelative(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
        pathBuilder.close();
    }

    public static PathBuilder s(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineTo(f13);
        pathBuilder.horizontalLineTo(f14);
        return pathBuilder;
    }

    public static void t(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineTo(f10);
        pathBuilder.verticalLineTo(f11);
        pathBuilder.horizontalLineToRelative(f12);
        pathBuilder.verticalLineToRelative(f13);
    }

    public static PathBuilder u(float f10, float f11, float f12, float f13, float f14) {
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(f10, f11);
        pathBuilder.verticalLineToRelative(f12);
        pathBuilder.horizontalLineToRelative(f13);
        pathBuilder.verticalLineToRelative(f14);
        return pathBuilder;
    }
}
