package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004H\u0082\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0003J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0018\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fR$\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00160 j\b\u0012\u0004\u0012\u00020\u0016`!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\"R\u0016\u0010#\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathParser;", "", "<init>", "()V", "", "dataCount", "Lx6/t0;", "resizeNodeData", "(I)V", "", "cmd", "", "args", "count", "addNodes", "(C[FI)V", "clear", "", "pathData", "parsePathString", "(Ljava/lang/String;)Landroidx/compose/ui/graphics/vector/PathParser;", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "nodes", "addPathNodes", "(Ljava/util/List;)Landroidx/compose/ui/graphics/vector/PathParser;", "toNodes", "()Ljava/util/List;", "Landroidx/compose/ui/graphics/Path;", "target", "toPath", "(Landroidx/compose/ui/graphics/Path;)Landroidx/compose/ui/graphics/Path;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Ljava/util/ArrayList;", "nodeData", "[F", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PathParser {
    private final ArrayList<PathNode> nodes = new ArrayList<>();
    private float[] nodeData = new float[64];

    private final void addNodes(char cmd, float[] args, int count) {
        PathNodeKt.addPathNodes(cmd, this.nodes, args, count);
    }

    private final void resizeNodeData(int dataCount) {
        float[] fArr = this.nodeData;
        if (dataCount >= fArr.length) {
            float[] fArr2 = new float[dataCount * 2];
            this.nodeData = fArr2;
            System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        }
    }

    public static /* synthetic */ Path toPath$default(PathParser pathParser, Path path, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return pathParser.toPath(path);
    }

    public final PathParser addPathNodes(List<? extends PathNode> nodes) {
        this.nodes.addAll(nodes);
        return this;
    }

    public final void clear() {
        this.nodes.clear();
    }

    public final PathParser parsePathString(String pathData) {
        int i10;
        char cCharAt;
        int i11;
        this.nodes.clear();
        int length = pathData.length();
        int i12 = 0;
        while (i12 < length && p.c(pathData.charAt(i12), 32) <= 0) {
            i12++;
        }
        while (length > i12 && p.c(pathData.charAt(length - 1), 32) <= 0) {
            length--;
        }
        int i13 = 0;
        while (i12 < length) {
            while (true) {
                i10 = i12 + 1;
                cCharAt = pathData.charAt(i12);
                int i14 = cCharAt | ' ';
                if ((i14 - 122) * (i14 - 97) <= 0 && i14 != 101) {
                    break;
                }
                if (i10 >= length) {
                    cCharAt = 0;
                    break;
                }
                i12 = i10;
            }
            if (cCharAt != 0) {
                if ((cCharAt | ' ') != 122) {
                    i13 = 0;
                    while (true) {
                        if (i10 >= length || p.c(pathData.charAt(i10), 32) > 0) {
                            long jNextFloat = FastFloatParserKt.nextFloat(pathData, i10, length);
                            i11 = (int) (jNextFloat >>> 32);
                            float fIntBitsToFloat = Float.intBitsToFloat((int) (jNextFloat & 4294967295L));
                            if (!Float.isNaN(fIntBitsToFloat)) {
                                float[] fArr = this.nodeData;
                                int i15 = i13 + 1;
                                fArr[i13] = fIntBitsToFloat;
                                if (i15 >= fArr.length) {
                                    float[] fArr2 = new float[i15 * 2];
                                    this.nodeData = fArr2;
                                    System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                                }
                                i13 = i15;
                            }
                            while (i11 < length && pathData.charAt(i11) == ',') {
                                i11++;
                            }
                            if (i11 >= length || Float.isNaN(fIntBitsToFloat)) {
                                break;
                            }
                            i10 = i11;
                        } else {
                            i10++;
                        }
                    }
                    i10 = i11;
                }
                PathNodeKt.addPathNodes(cCharAt, this.nodes, this.nodeData, i13);
            }
            i12 = i10;
        }
        return this;
    }

    public final List<PathNode> toNodes() {
        return this.nodes;
    }

    public final Path toPath(Path target) {
        return PathParserKt.toPath(this.nodes, target);
    }
}
