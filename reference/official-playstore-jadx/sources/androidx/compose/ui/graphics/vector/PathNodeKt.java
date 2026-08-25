package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.vector.PathNode;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b&\u001a;\u0010\n\u001a\u00020\t*\u00020\u00002\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001ap\u0010\u0014\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000728\b\u0004\u0010\u0013\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00020\u000eH\u0082\b¢\u0006\u0004\b\u0014\u0010\u0015\"\u0014\u0010\u0016\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0018\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017\"\u0014\u0010\u0019\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0017\"\u0014\u0010\u001a\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0017\"\u0014\u0010\u001b\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001b\u0010\u0017\"\u0014\u0010\u001c\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0017\"\u0014\u0010\u001d\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001d\u0010\u0017\"\u0014\u0010\u001e\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001e\u0010\u0017\"\u0014\u0010\u001f\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001f\u0010\u0017\"\u0014\u0010 \u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b \u0010\u0017\"\u0014\u0010!\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\u0017\"\u0014\u0010\"\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\"\u0010\u0017\"\u0014\u0010#\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b#\u0010\u0017\"\u0014\u0010$\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b$\u0010\u0017\"\u0014\u0010%\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b%\u0010\u0017\"\u0014\u0010&\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b&\u0010\u0017\"\u0014\u0010'\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b'\u0010\u0017\"\u0014\u0010(\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b(\u0010\u0017\"\u0014\u0010)\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b)\u0010\u0017\"\u0014\u0010*\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b*\u0010\u0017\"\u0014\u0010+\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b+\u0010,\"\u0014\u0010-\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010,\"\u0014\u0010.\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010,\"\u0014\u0010/\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b/\u0010,\"\u0014\u00100\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b0\u0010,\"\u0014\u00101\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b1\u0010,\"\u0014\u00102\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u0010,\"\u0014\u00103\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b3\u0010,\"\u0014\u00104\u001a\u00020\u00078\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u0010,¨\u00065"}, d2 = {"", "Ljava/util/ArrayList;", "Landroidx/compose/ui/graphics/vector/PathNode;", "Lkotlin/collections/ArrayList;", "nodes", "", "args", "", "count", "Lx6/t0;", "addPathNodes", "(CLjava/util/ArrayList;[FI)V", "", "numArgs", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "subArray", TtmlNode.START, "nodeFor", "pathNodesFromArgs", "(Ljava/util/List;[FIILr7/p;)V", "RelativeCloseKey", "C", "CloseKey", "RelativeMoveToKey", "MoveToKey", "RelativeLineToKey", "LineToKey", "RelativeHorizontalToKey", "HorizontalToKey", "RelativeVerticalToKey", "VerticalToKey", "RelativeCurveToKey", "CurveToKey", "RelativeReflectiveCurveToKey", "ReflectiveCurveToKey", "RelativeQuadToKey", "QuadToKey", "RelativeReflectiveQuadToKey", "ReflectiveQuadToKey", "RelativeArcToKey", "ArcToKey", "NUM_MOVE_TO_ARGS", "I", "NUM_LINE_TO_ARGS", "NUM_HORIZONTAL_TO_ARGS", "NUM_VERTICAL_TO_ARGS", "NUM_CURVE_TO_ARGS", "NUM_REFLECTIVE_CURVE_TO_ARGS", "NUM_QUAD_TO_ARGS", "NUM_REFLECTIVE_QUAD_TO_ARGS", "NUM_ARC_TO_ARGS", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PathNodeKt {
    private static final char ArcToKey = 'A';
    private static final char CloseKey = 'Z';
    private static final char CurveToKey = 'C';
    private static final char HorizontalToKey = 'H';
    private static final char LineToKey = 'L';
    private static final char MoveToKey = 'M';
    private static final int NUM_ARC_TO_ARGS = 7;
    private static final int NUM_CURVE_TO_ARGS = 6;
    private static final int NUM_HORIZONTAL_TO_ARGS = 1;
    private static final int NUM_LINE_TO_ARGS = 2;
    private static final int NUM_MOVE_TO_ARGS = 2;
    private static final int NUM_QUAD_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_CURVE_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_QUAD_TO_ARGS = 2;
    private static final int NUM_VERTICAL_TO_ARGS = 1;
    private static final char QuadToKey = 'Q';
    private static final char ReflectiveCurveToKey = 'S';
    private static final char ReflectiveQuadToKey = 'T';
    private static final char RelativeArcToKey = 'a';
    private static final char RelativeCloseKey = 'z';
    private static final char RelativeCurveToKey = 'c';
    private static final char RelativeHorizontalToKey = 'h';
    private static final char RelativeLineToKey = 'l';
    private static final char RelativeMoveToKey = 'm';
    private static final char RelativeQuadToKey = 'q';
    private static final char RelativeReflectiveCurveToKey = 's';
    private static final char RelativeReflectiveQuadToKey = 't';
    private static final char RelativeVerticalToKey = 'v';
    private static final char VerticalToKey = 'V';

    public static final void addPathNodes(char c10, ArrayList<PathNode> arrayList, float[] fArr, int i10) {
        if (c10 == 'z' || c10 == 'Z') {
            arrayList.add(PathNode.Close.INSTANCE);
            return;
        }
        int i11 = 0;
        if (c10 == 'm') {
            int i12 = i10 - 2;
            while (i11 <= i12) {
                int i13 = i11 + 1;
                PathNode relativeMoveTo = new PathNode.RelativeMoveTo(fArr[i11], fArr[i13]);
                if (i11 > 0) {
                    relativeMoveTo = new PathNode.RelativeLineTo(fArr[i11], fArr[i13]);
                }
                arrayList.add(relativeMoveTo);
                i11 += 2;
            }
            return;
        }
        if (c10 == 'M') {
            int i14 = i10 - 2;
            while (i11 <= i14) {
                int i15 = i11 + 1;
                PathNode moveTo = new PathNode.MoveTo(fArr[i11], fArr[i15]);
                if (i11 > 0) {
                    moveTo = new PathNode.LineTo(fArr[i11], fArr[i15]);
                }
                arrayList.add(moveTo);
                i11 += 2;
            }
            return;
        }
        if (c10 == 'l') {
            int i16 = i10 - 2;
            while (i11 <= i16) {
                arrayList.add(new PathNode.RelativeLineTo(fArr[i11], fArr[i11 + 1]));
                i11 += 2;
            }
            return;
        }
        if (c10 == 'L') {
            int i17 = i10 - 2;
            while (i11 <= i17) {
                arrayList.add(new PathNode.LineTo(fArr[i11], fArr[i11 + 1]));
                i11 += 2;
            }
            return;
        }
        if (c10 == 'h') {
            int i18 = i10 - 1;
            while (i11 <= i18) {
                arrayList.add(new PathNode.RelativeHorizontalTo(fArr[i11]));
                i11++;
            }
            return;
        }
        if (c10 == 'H') {
            int i19 = i10 - 1;
            while (i11 <= i19) {
                arrayList.add(new PathNode.HorizontalTo(fArr[i11]));
                i11++;
            }
            return;
        }
        if (c10 == 'v') {
            int i20 = i10 - 1;
            while (i11 <= i20) {
                arrayList.add(new PathNode.RelativeVerticalTo(fArr[i11]));
                i11++;
            }
            return;
        }
        if (c10 == 'V') {
            int i21 = i10 - 1;
            while (i11 <= i21) {
                arrayList.add(new PathNode.VerticalTo(fArr[i11]));
                i11++;
            }
            return;
        }
        if (c10 == 'c') {
            int i22 = i10 - 6;
            while (i11 <= i22) {
                arrayList.add(new PathNode.RelativeCurveTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3], fArr[i11 + 4], fArr[i11 + 5]));
                i11 += 6;
            }
            return;
        }
        if (c10 == 'C') {
            int i23 = i10 - 6;
            while (i11 <= i23) {
                arrayList.add(new PathNode.CurveTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3], fArr[i11 + 4], fArr[i11 + 5]));
                i11 += 6;
            }
            return;
        }
        if (c10 == 's') {
            int i24 = i10 - 4;
            while (i11 <= i24) {
                arrayList.add(new PathNode.RelativeReflectiveCurveTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3]));
                i11 += 4;
            }
            return;
        }
        if (c10 == 'S') {
            int i25 = i10 - 4;
            while (i11 <= i25) {
                arrayList.add(new PathNode.ReflectiveCurveTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3]));
                i11 += 4;
            }
            return;
        }
        if (c10 == 'q') {
            int i26 = i10 - 4;
            while (i11 <= i26) {
                arrayList.add(new PathNode.RelativeQuadTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3]));
                i11 += 4;
            }
            return;
        }
        if (c10 == 'Q') {
            int i27 = i10 - 4;
            while (i11 <= i27) {
                arrayList.add(new PathNode.QuadTo(fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3]));
                i11 += 4;
            }
            return;
        }
        if (c10 == 't') {
            int i28 = i10 - 2;
            while (i11 <= i28) {
                arrayList.add(new PathNode.RelativeReflectiveQuadTo(fArr[i11], fArr[i11 + 1]));
                i11 += 2;
            }
            return;
        }
        if (c10 == 'T') {
            int i29 = i10 - 2;
            while (i11 <= i29) {
                arrayList.add(new PathNode.ReflectiveQuadTo(fArr[i11], fArr[i11 + 1]));
                i11 += 2;
            }
            return;
        }
        if (c10 == 'a') {
            int i30 = i10 - 7;
            for (int i31 = 0; i31 <= i30; i31 += 7) {
                arrayList.add(new PathNode.RelativeArcTo(fArr[i31], fArr[i31 + 1], fArr[i31 + 2], Float.compare(fArr[i31 + 3], 0.0f) != 0, Float.compare(fArr[i31 + 4], 0.0f) != 0, fArr[i31 + 5], fArr[i31 + 6]));
            }
            return;
        }
        if (c10 != 'A') {
            throw new IllegalArgumentException("Unknown command for: " + c10);
        }
        int i32 = i10 - 7;
        for (int i33 = 0; i33 <= i32; i33 += 7) {
            arrayList.add(new PathNode.ArcTo(fArr[i33], fArr[i33 + 1], fArr[i33 + 2], Float.compare(fArr[i33 + 3], 0.0f) != 0, Float.compare(fArr[i33 + 4], 0.0f) != 0, fArr[i33 + 5], fArr[i33 + 6]));
        }
    }

    private static final void pathNodesFromArgs(List<PathNode> list, float[] fArr, int i10, int i11, p<? super float[], ? super Integer, ? extends PathNode> pVar) {
        int i12 = i10 - i11;
        int i13 = 0;
        while (i13 <= i12) {
            PathNode relativeLineTo = (PathNode) pVar.invoke(fArr, Integer.valueOf(i13));
            if ((relativeLineTo instanceof PathNode.MoveTo) && i13 > 0) {
                relativeLineTo = new PathNode.LineTo(fArr[i13], fArr[i13 + 1]);
            } else if ((relativeLineTo instanceof PathNode.RelativeMoveTo) && i13 > 0) {
                relativeLineTo = new PathNode.RelativeLineTo(fArr[i13], fArr[i13 + 1]);
            }
            list.add(relativeLineTo);
            i13 += i11;
        }
    }
}
