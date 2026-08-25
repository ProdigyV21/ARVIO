package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0014\n\u0002\b\u0005\u001a!\u0010\u0004\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a_\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a_\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0014\u0010\u001d\u001a\u00020\u0007*\u00020\u0007H\u0082\b¢\u0006\u0004\b\u001d\u0010\u001e\"\u001a\u0010 \u001a\u00020\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"", "Landroidx/compose/ui/graphics/vector/PathNode;", "Landroidx/compose/ui/graphics/Path;", "target", "toPath", "(Ljava/util/List;Landroidx/compose/ui/graphics/Path;)Landroidx/compose/ui/graphics/Path;", TtmlNode.TAG_P, "", "x0", "y0", "x1", "y1", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "theta", "", "isMoreThanHalf", "isPositiveArc", "Lx6/t0;", "drawArc", "(Landroidx/compose/ui/graphics/Path;DDDDDDDZZ)V", "cx", "cy", "e1x", "e1y", TtmlNode.START, "sweep", "arcToBezier", "(Landroidx/compose/ui/graphics/Path;DDDDDDDDD)V", "toRadians", "(D)D", "", "EmptyArray", "[F", "getEmptyArray", "()[F", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PathParserKt {
    private static final float[] EmptyArray = new float[0];

    private static final void arcToBezier(Path path, double d4, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        double d18 = 4;
        int iCeil = (int) Math.ceil(Math.abs((d17 * d18) / 3.141592653589793d));
        double dCos = Math.cos(d15);
        double dSin = Math.sin(d15);
        double dCos2 = Math.cos(d16);
        double dSin2 = Math.sin(d16);
        double d19 = -d11;
        double d20 = d19 * dCos;
        double d21 = d12 * dSin;
        double d22 = (d20 * dSin2) - (d21 * dCos2);
        double d23 = d19 * dSin;
        double d24 = d12 * dCos;
        double d25 = (dCos2 * d24) + (dSin2 * d23);
        double d26 = d17 / ((double) iCeil);
        double d27 = d25;
        double d28 = d22;
        int i10 = 0;
        double d29 = d13;
        double d30 = d14;
        double d31 = d16;
        while (i10 < iCeil) {
            double d32 = d31 + d26;
            double dSin3 = Math.sin(d32);
            double dCos3 = Math.cos(d32);
            int i11 = i10;
            double d33 = (((d11 * dCos) * dCos3) + d4) - (d21 * dSin3);
            double d34 = d18;
            double d35 = (d24 * dSin3) + (d11 * dSin * dCos3) + d10;
            double d36 = (d20 * dSin3) - (d21 * dCos3);
            double d37 = (dCos3 * d24) + (dSin3 * d23);
            double d38 = d32 - d31;
            int i12 = iCeil;
            double dTan = Math.tan(d38 / ((double) 2));
            double dSqrt = ((Math.sqrt(((3.0d * dTan) * dTan) + d34) - ((double) 1)) * Math.sin(d38)) / ((double) 3);
            path.cubicTo((float) ((d28 * dSqrt) + d29), (float) ((d27 * dSqrt) + d30), (float) (d33 - (dSqrt * d36)), (float) (d35 - (dSqrt * d37)), (float) d33, (float) d35);
            dSin = dSin;
            d26 = d26;
            d29 = d33;
            d30 = d35;
            i10 = i11 + 1;
            d31 = d32;
            d27 = d37;
            iCeil = i12;
            d28 = d36;
            dCos = dCos;
            d18 = d34;
        }
    }

    private static final void drawArc(Path path, double d4, double d10, double d11, double d12, double d13, double d14, double d15, boolean z, boolean z5) {
        double d16;
        double d17;
        double d18 = (d15 / ((double) 180)) * 3.141592653589793d;
        double dCos = Math.cos(d18);
        double dSin = Math.sin(d18);
        double d19 = ((d10 * dSin) + (d4 * dCos)) / d13;
        double d20 = ((d10 * dCos) + ((-d4) * dSin)) / d14;
        double d21 = ((d12 * dSin) + (d11 * dCos)) / d13;
        double d22 = ((d12 * dCos) + ((-d11) * dSin)) / d14;
        double d23 = d19 - d21;
        double d24 = d20 - d22;
        double d25 = 2;
        double d26 = (d19 + d21) / d25;
        double d27 = (d20 + d22) / d25;
        double d28 = (d24 * d24) + (d23 * d23);
        if (d28 == 0.0d) {
            return;
        }
        double d29 = (1.0d / d28) - 0.25d;
        if (d29 < 0.0d) {
            double dSqrt = (float) (Math.sqrt(d28) / 1.99999d);
            drawArc(path, d4, d10, d11, d12, d13 * dSqrt, d14 * dSqrt, d15, z, z5);
            return;
        }
        double dSqrt2 = Math.sqrt(d29);
        double d30 = d23 * dSqrt2;
        double d31 = dSqrt2 * d24;
        if (z == z5) {
            d16 = d26 - d31;
            d17 = d27 + d30;
        } else {
            d16 = d26 + d31;
            d17 = d27 - d30;
        }
        double dAtan2 = Math.atan2(d20 - d17, d19 - d16);
        double dAtan22 = Math.atan2(d22 - d17, d21 - d16) - dAtan2;
        if (z5 != (dAtan22 >= 0.0d)) {
            dAtan22 = dAtan22 > 0.0d ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
        }
        double d32 = d16 * d13;
        double d33 = d17 * d14;
        arcToBezier(path, (d32 * dCos) - (d33 * dSin), (d33 * dCos) + (d32 * dSin), d13, d14, d4, d10, d18, dAtan2, dAtan22);
    }

    public static final float[] getEmptyArray() {
        return EmptyArray;
    }

    public static final Path toPath(List<? extends PathNode> list, Path path) {
        int i10;
        float f10;
        int i11;
        PathNode pathNode;
        float f11;
        float f12;
        float x12;
        float y12;
        float x22;
        float y22;
        float dy2;
        float f13;
        float f14;
        float dx1;
        float dy1;
        float dx2;
        float dy22;
        float y32;
        float y10;
        float x;
        float x10;
        float y11;
        List<? extends PathNode> list2 = list;
        Path path2 = path;
        int iMo3369getFillTypeRgk1Os = path2.mo3369getFillTypeRgk1Os();
        path2.rewind();
        path2.mo3371setFillTypeoQ8Xj4U(iMo3369getFillTypeRgk1Os);
        PathNode pathNode2 = list2.isEmpty() ? PathNode.Close.INSTANCE : list2.get(0);
        int size = list2.size();
        float f15 = 0.0f;
        int i12 = 0;
        float arcStartX = 0.0f;
        float arcStartY = 0.0f;
        float f16 = 0.0f;
        float f17 = 0.0f;
        float x11 = 0.0f;
        float y13 = 0.0f;
        while (i12 < size) {
            PathNode pathNode3 = list2.get(i12);
            if (pathNode3 instanceof PathNode.Close) {
                path2.close();
                path2.moveTo(f16, f17);
                i10 = size;
                f10 = f15;
                i11 = i12;
                arcStartX = f16;
                x11 = arcStartX;
                arcStartY = f17;
                y13 = arcStartY;
            } else {
                if (pathNode3 instanceof PathNode.RelativeMoveTo) {
                    PathNode.RelativeMoveTo relativeMoveTo = (PathNode.RelativeMoveTo) pathNode3;
                    x10 = relativeMoveTo.getDx() + x11;
                    y11 = relativeMoveTo.getDy() + y13;
                    path2.relativeMoveTo(relativeMoveTo.getDx(), relativeMoveTo.getDy());
                } else if (pathNode3 instanceof PathNode.MoveTo) {
                    PathNode.MoveTo moveTo = (PathNode.MoveTo) pathNode3;
                    x10 = moveTo.getX();
                    y11 = moveTo.getY();
                    path2.moveTo(moveTo.getX(), moveTo.getY());
                } else {
                    if (pathNode3 instanceof PathNode.RelativeLineTo) {
                        PathNode.RelativeLineTo relativeLineTo = (PathNode.RelativeLineTo) pathNode3;
                        path2.relativeLineTo(relativeLineTo.getDx(), relativeLineTo.getDy());
                        x22 = relativeLineTo.getDx() + x11;
                        dy2 = relativeLineTo.getDy();
                    } else {
                        if (pathNode3 instanceof PathNode.LineTo) {
                            PathNode.LineTo lineTo = (PathNode.LineTo) pathNode3;
                            path2.lineTo(lineTo.getX(), lineTo.getY());
                            x22 = lineTo.getX();
                            y22 = lineTo.getY();
                        } else {
                            if (pathNode3 instanceof PathNode.RelativeHorizontalTo) {
                                PathNode.RelativeHorizontalTo relativeHorizontalTo = (PathNode.RelativeHorizontalTo) pathNode3;
                                path2.relativeLineTo(relativeHorizontalTo.getDx(), f15);
                                x = relativeHorizontalTo.getDx() + x11;
                            } else if (pathNode3 instanceof PathNode.HorizontalTo) {
                                PathNode.HorizontalTo horizontalTo = (PathNode.HorizontalTo) pathNode3;
                                path2.lineTo(horizontalTo.getX(), y13);
                                x = horizontalTo.getX();
                            } else {
                                if (pathNode3 instanceof PathNode.RelativeVerticalTo) {
                                    PathNode.RelativeVerticalTo relativeVerticalTo = (PathNode.RelativeVerticalTo) pathNode3;
                                    path2.relativeLineTo(f15, relativeVerticalTo.getDy());
                                    y10 = relativeVerticalTo.getDy() + y13;
                                } else if (pathNode3 instanceof PathNode.VerticalTo) {
                                    PathNode.VerticalTo verticalTo = (PathNode.VerticalTo) pathNode3;
                                    path2.lineTo(x11, verticalTo.getY());
                                    y10 = verticalTo.getY();
                                } else {
                                    if (pathNode3 instanceof PathNode.RelativeCurveTo) {
                                        PathNode.RelativeCurveTo relativeCurveTo = (PathNode.RelativeCurveTo) pathNode3;
                                        path2.relativeCubicTo(relativeCurveTo.getDx1(), relativeCurveTo.getDy1(), relativeCurveTo.getDx2(), relativeCurveTo.getDy2(), relativeCurveTo.getDx3(), relativeCurveTo.getDy3());
                                        dx1 = relativeCurveTo.getDx2() + x11;
                                        dy1 = relativeCurveTo.getDy2() + y13;
                                        dx2 = relativeCurveTo.getDx3() + x11;
                                        dy22 = relativeCurveTo.getDy3();
                                    } else if (pathNode3 instanceof PathNode.CurveTo) {
                                        PathNode.CurveTo curveTo = (PathNode.CurveTo) pathNode3;
                                        path.cubicTo(curveTo.getX1(), curveTo.getY1(), curveTo.getX2(), curveTo.getY2(), curveTo.getX3(), curveTo.getY3());
                                        dx1 = curveTo.getX2();
                                        dy1 = curveTo.getY2();
                                        dx2 = curveTo.getX3();
                                        y32 = curveTo.getY3();
                                        x11 = dx2;
                                        y13 = y32;
                                        i10 = size;
                                        f10 = f15;
                                        i11 = i12;
                                        pathNode = pathNode3;
                                        arcStartX = dx1;
                                        arcStartY = dy1;
                                        i12 = i11 + 1;
                                        path2 = path;
                                        pathNode2 = pathNode;
                                        size = i10;
                                        f15 = f10;
                                        list2 = list;
                                    } else if (pathNode3 instanceof PathNode.RelativeReflectiveCurveTo) {
                                        if (pathNode2.getIsCurve()) {
                                            float f18 = x11 - arcStartX;
                                            f14 = y13 - arcStartY;
                                            f13 = f18;
                                        } else {
                                            f13 = f15;
                                            f14 = f13;
                                        }
                                        PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo = (PathNode.RelativeReflectiveCurveTo) pathNode3;
                                        path.relativeCubicTo(f13, f14, relativeReflectiveCurveTo.getDx1(), relativeReflectiveCurveTo.getDy1(), relativeReflectiveCurveTo.getDx2(), relativeReflectiveCurveTo.getDy2());
                                        dx1 = relativeReflectiveCurveTo.getDx1() + x11;
                                        dy1 = relativeReflectiveCurveTo.getDy1() + y13;
                                        dx2 = relativeReflectiveCurveTo.getDx2() + x11;
                                        dy22 = relativeReflectiveCurveTo.getDy2();
                                    } else {
                                        if (pathNode3 instanceof PathNode.ReflectiveCurveTo) {
                                            if (pathNode2.getIsCurve()) {
                                                float f19 = 2;
                                                x11 = (x11 * f19) - arcStartX;
                                                y13 = (f19 * y13) - arcStartY;
                                            }
                                            PathNode.ReflectiveCurveTo reflectiveCurveTo = (PathNode.ReflectiveCurveTo) pathNode3;
                                            path.cubicTo(x11, y13, reflectiveCurveTo.getX1(), reflectiveCurveTo.getY1(), reflectiveCurveTo.getX2(), reflectiveCurveTo.getY2());
                                            x12 = reflectiveCurveTo.getX1();
                                            y12 = reflectiveCurveTo.getY1();
                                            float x23 = reflectiveCurveTo.getX2();
                                            float y23 = reflectiveCurveTo.getY2();
                                            x11 = x23;
                                            y13 = y23;
                                        } else if (pathNode3 instanceof PathNode.RelativeQuadTo) {
                                            PathNode.RelativeQuadTo relativeQuadTo = (PathNode.RelativeQuadTo) pathNode3;
                                            path.relativeQuadraticBezierTo(relativeQuadTo.getDx1(), relativeQuadTo.getDy1(), relativeQuadTo.getDx2(), relativeQuadTo.getDy2());
                                            arcStartX = relativeQuadTo.getDx1() + x11;
                                            arcStartY = relativeQuadTo.getDy1() + y13;
                                            x22 = relativeQuadTo.getDx2() + x11;
                                            dy2 = relativeQuadTo.getDy2();
                                        } else if (pathNode3 instanceof PathNode.QuadTo) {
                                            PathNode.QuadTo quadTo = (PathNode.QuadTo) pathNode3;
                                            path.quadraticBezierTo(quadTo.getX1(), quadTo.getY1(), quadTo.getX2(), quadTo.getY2());
                                            arcStartX = quadTo.getX1();
                                            arcStartY = quadTo.getY1();
                                            x22 = quadTo.getX2();
                                            y22 = quadTo.getY2();
                                        } else if (pathNode3 instanceof PathNode.RelativeReflectiveQuadTo) {
                                            if (pathNode2.getIsQuad()) {
                                                f11 = x11 - arcStartX;
                                                f12 = y13 - arcStartY;
                                            } else {
                                                f11 = f15;
                                                f12 = f11;
                                            }
                                            PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo = (PathNode.RelativeReflectiveQuadTo) pathNode3;
                                            path.relativeQuadraticBezierTo(f11, f12, relativeReflectiveQuadTo.getDx(), relativeReflectiveQuadTo.getDy());
                                            x12 = f11 + x11;
                                            y12 = f12 + y13;
                                            float dx = relativeReflectiveQuadTo.getDx() + x11;
                                            y13 = relativeReflectiveQuadTo.getDy() + y13;
                                            x11 = dx;
                                        } else {
                                            if (pathNode3 instanceof PathNode.ReflectiveQuadTo) {
                                                if (pathNode2.getIsQuad()) {
                                                    float f20 = 2;
                                                    x11 = (x11 * f20) - arcStartX;
                                                    y13 = (f20 * y13) - arcStartY;
                                                }
                                                PathNode.ReflectiveQuadTo reflectiveQuadTo = (PathNode.ReflectiveQuadTo) pathNode3;
                                                path.quadraticBezierTo(x11, y13, reflectiveQuadTo.getX(), reflectiveQuadTo.getY());
                                                float f21 = x11;
                                                x11 = reflectiveQuadTo.getX();
                                                arcStartX = f21;
                                                i10 = size;
                                                f10 = f15;
                                                i11 = i12;
                                                arcStartY = y13;
                                                pathNode = pathNode3;
                                                y13 = reflectiveQuadTo.getY();
                                            } else if (pathNode3 instanceof PathNode.RelativeArcTo) {
                                                PathNode.RelativeArcTo relativeArcTo = (PathNode.RelativeArcTo) pathNode3;
                                                float arcStartDx = relativeArcTo.getArcStartDx() + x11;
                                                float arcStartDy = relativeArcTo.getArcStartDy() + y13;
                                                f10 = f15;
                                                i11 = i12;
                                                pathNode = pathNode3;
                                                i10 = size;
                                                drawArc(path, x11, y13, arcStartDx, arcStartDy, relativeArcTo.getHorizontalEllipseRadius(), relativeArcTo.getVerticalEllipseRadius(), relativeArcTo.getTheta(), relativeArcTo.isMoreThanHalf(), relativeArcTo.isPositiveArc());
                                                f16 = f16;
                                                f17 = f17;
                                                arcStartY = arcStartDy;
                                                y13 = arcStartY;
                                                arcStartX = arcStartDx;
                                                x11 = arcStartX;
                                            } else {
                                                i10 = size;
                                                f10 = f15;
                                                i11 = i12;
                                                float f22 = f16;
                                                float f23 = f17;
                                                pathNode = pathNode3;
                                                if (pathNode instanceof PathNode.ArcTo) {
                                                    PathNode.ArcTo arcTo = (PathNode.ArcTo) pathNode;
                                                    drawArc(path, x11, y13, arcTo.getArcStartX(), arcTo.getArcStartY(), arcTo.getHorizontalEllipseRadius(), arcTo.getVerticalEllipseRadius(), arcTo.getTheta(), arcTo.isMoreThanHalf(), arcTo.isPositiveArc());
                                                    arcStartX = arcTo.getArcStartX();
                                                    x11 = arcStartX;
                                                    arcStartY = arcTo.getArcStartY();
                                                    y13 = arcStartY;
                                                }
                                                f16 = f22;
                                                f17 = f23;
                                            }
                                            i12 = i11 + 1;
                                            path2 = path;
                                            pathNode2 = pathNode;
                                            size = i10;
                                            f15 = f10;
                                            list2 = list;
                                        }
                                        i10 = size;
                                        f10 = f15;
                                        i11 = i12;
                                        pathNode = pathNode3;
                                        arcStartY = y12;
                                        arcStartX = x12;
                                        i12 = i11 + 1;
                                        path2 = path;
                                        pathNode2 = pathNode;
                                        size = i10;
                                        f15 = f10;
                                        list2 = list;
                                    }
                                    y32 = dy22 + y13;
                                    x11 = dx2;
                                    y13 = y32;
                                    i10 = size;
                                    f10 = f15;
                                    i11 = i12;
                                    pathNode = pathNode3;
                                    arcStartX = dx1;
                                    arcStartY = dy1;
                                    i12 = i11 + 1;
                                    path2 = path;
                                    pathNode2 = pathNode;
                                    size = i10;
                                    f15 = f10;
                                    list2 = list;
                                }
                                y13 = y10;
                                i10 = size;
                                f10 = f15;
                                i11 = i12;
                            }
                            x11 = x;
                            i10 = size;
                            f10 = f15;
                            i11 = i12;
                        }
                        y13 = y22;
                        x11 = x22;
                        i10 = size;
                        f10 = f15;
                        i11 = i12;
                    }
                    y22 = dy2 + y13;
                    y13 = y22;
                    x11 = x22;
                    i10 = size;
                    f10 = f15;
                    i11 = i12;
                }
                f16 = x10;
                x11 = f16;
                f17 = y11;
                y13 = f17;
                i10 = size;
                f10 = f15;
                i11 = i12;
            }
            pathNode = pathNode3;
            i12 = i11 + 1;
            path2 = path;
            pathNode2 = pathNode;
            size = i10;
            f15 = f10;
            list2 = list;
        }
        return path;
    }

    public static /* synthetic */ Path toPath$default(List list, Path path, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return toPath(list, path);
    }

    private static final double toRadians(double d4) {
        return (d4 / ((double) 180)) * 3.141592653589793d;
    }
}
