package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\u0017\n\u0002\b\u0004\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0004\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000f\u001a\u00020\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\t\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b\u000b\u0010 \u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006#"}, d2 = {"Landroidx/compose/ui/graphics/Vertices;", "", "Landroidx/compose/ui/graphics/VertexMode;", "vertexMode", "", "Landroidx/compose/ui/geometry/Offset;", "positions", "textureCoordinates", "Landroidx/compose/ui/graphics/Color;", "colors", "", "indices", "<init>", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/internal/h;)V", "", "encodeColorList", "(Ljava/util/List;)[I", "points", "", "encodePointList", "(Ljava/util/List;)[F", "I", "getVertexMode-c2xauaI", "()I", "[F", "getPositions", "()[F", "getTextureCoordinates", "[I", "getColors", "()[I", "", "[S", "getIndices", "()[S", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Vertices {
    private final int[] colors;
    private final short[] indices;
    private final float[] positions;
    private final float[] textureCoordinates;
    private final int vertexMode;

    public /* synthetic */ Vertices(int i10, List list, List list2, List list3, List list4, kotlin.jvm.internal.h hVar) {
        this(i10, list, list2, list3, list4);
    }

    private final int[] encodeColorList(List<Color> colors) {
        int size = colors.size();
        int[] iArr = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = ColorKt.m3526toArgb8_81llA(colors.get(i10).m3482unboximpl());
        }
        return iArr;
    }

    private final float[] encodePointList(List<Offset> points) {
        int size = points.size() * 2;
        float[] fArr = new float[size];
        for (int i10 = 0; i10 < size; i10++) {
            long packedValue = points.get(i10 / 2).getPackedValue();
            fArr[i10] = i10 % 2 == 0 ? Offset.m3236getXimpl(packedValue) : Offset.m3237getYimpl(packedValue);
        }
        return fArr;
    }

    public final int[] getColors() {
        return this.colors;
    }

    public final short[] getIndices() {
        return this.indices;
    }

    public final float[] getPositions() {
        return this.positions;
    }

    public final float[] getTextureCoordinates() {
        return this.textureCoordinates;
    }

    /* JADX INFO: renamed from: getVertexMode-c2xauaI, reason: not valid java name and from getter */
    public final int getVertexMode() {
        return this.vertexMode;
    }

    private Vertices(int i10, List<Offset> list, List<Offset> list2, List<Color> list3, List<Integer> list4) {
        this.vertexMode = i10;
        Vertices$outOfBounds$1 vertices$outOfBounds$1 = new Vertices$outOfBounds$1(list);
        if (list2.size() != list.size()) {
            throw new IllegalArgumentException("positions and textureCoordinates lengths must match.");
        }
        if (list3.size() != list.size()) {
            throw new IllegalArgumentException("positions and colors lengths must match.");
        }
        int size = list4.size();
        for (int i11 = 0; i11 < size; i11++) {
            if (((Boolean) vertices$outOfBounds$1.invoke(list4.get(i11))).booleanValue()) {
                throw new IllegalArgumentException("indices values must be valid indices in the positions list.");
            }
        }
        this.positions = encodePointList(list);
        this.textureCoordinates = encodePointList(list2);
        this.colors = encodeColorList(list3);
        int size2 = list4.size();
        short[] sArr = new short[size2];
        for (int i12 = 0; i12 < size2; i12++) {
            sArr[i12] = (short) list4.get(i12).intValue();
        }
        this.indices = sArr;
    }
}
