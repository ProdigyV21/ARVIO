package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.DpRect;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JN\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00020\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "", "width", "height", "", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLines", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "placementBlock", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "(IILjava/util/Map;Lr7/l;)Landroidx/compose/ui/layout/MeasureResult;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MeasureScope extends IntrinsicMeasureScope {

    /* JADX INFO: renamed from: androidx.compose.ui.layout.MeasureScope$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static MeasureResult a(MeasureScope measureScope, int i10, int i11, Map map, r7.l lVar) {
            if ((i10 & (-16777216)) == 0 && ((-16777216) & i11) == 0) {
                return new MeasureResult(i10, i11, map, measureScope, lVar) { // from class: androidx.compose.ui.layout.MeasureScope.layout.1
                    final /* synthetic */ r7.l<Placeable.PlacementScope, t0> $placementBlock;
                    final /* synthetic */ int $width;
                    private final Map<AlignmentLine, Integer> alignmentLines;
                    private final int height;
                    final /* synthetic */ MeasureScope this$0;
                    private final int width;

                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        this.$width = i10;
                        this.this$0 = measureScope;
                        this.$placementBlock = lVar;
                        this.width = i10;
                        this.height = i11;
                        this.alignmentLines = map;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return this.alignmentLines;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getHeight, reason: from getter */
                    public int get$height() {
                        return this.height;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getWidth, reason: from getter */
                    public int get$width() {
                        return this.width;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() {
                        MeasureScope measureScope2 = this.this$0;
                        if (measureScope2 instanceof LookaheadCapablePlaceable) {
                            this.$placementBlock.invoke(((LookaheadCapablePlaceable) measureScope2).getPlacementScope());
                        } else {
                            this.$placementBlock.invoke(new SimplePlacementScope(this.$width, this.this$0.getLayoutDirection()));
                        }
                    }
                };
            }
            throw new IllegalStateException(androidx.compose.foundation.c.s("Size(", i10, " x ", i11, ") is out of range. Each dimension must be between 0 and 16777215.").toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MeasureResult q(MeasureScope measureScope, int i10, int i11, Map map, r7.l lVar, int i12, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: layout");
            }
            if ((i12 & 4) != 0) {
                map = a0.f19683i;
            }
            return measureScope.layout(i10, i11, map, lVar);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isLookingAhead(MeasureScope measureScope) {
            return b.a(measureScope);
        }

        @Deprecated
        public static MeasureResult layout(MeasureScope measureScope, int i10, int i11, Map<AlignmentLine, Integer> map, r7.l<? super Placeable.PlacementScope, t0> lVar) {
            return CC.a(measureScope, i10, i11, map, lVar);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4649roundToPxR2X_6o(MeasureScope measureScope, long j10) {
            return androidx.compose.ui.unit.a.a(measureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4650roundToPx0680j_4(MeasureScope measureScope, float f10) {
            return androidx.compose.ui.unit.a.b(measureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4651toDpGaN1DYA(MeasureScope measureScope, long j10) {
            return androidx.compose.ui.unit.b.a(measureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4652toDpu2uoSUM(MeasureScope measureScope, float f10) {
            return androidx.compose.ui.unit.a.c(measureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4654toDpSizekrfVVM(MeasureScope measureScope, long j10) {
            return androidx.compose.ui.unit.a.e(measureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4655toPxR2X_6o(MeasureScope measureScope, long j10) {
            return androidx.compose.ui.unit.a.f(measureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4656toPx0680j_4(MeasureScope measureScope, float f10) {
            return androidx.compose.ui.unit.a.g(measureScope, f10);
        }

        @Deprecated
        public static Rect toRect(MeasureScope measureScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(measureScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4657toSizeXkaWNTQ(MeasureScope measureScope, long j10) {
            return androidx.compose.ui.unit.a.i(measureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4658toSp0xMU5do(MeasureScope measureScope, float f10) {
            return androidx.compose.ui.unit.b.b(measureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4659toSpkPz2Gy4(MeasureScope measureScope, float f10) {
            return androidx.compose.ui.unit.a.j(measureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4653toDpu2uoSUM(MeasureScope measureScope, int i10) {
            return androidx.compose.ui.unit.a.d(measureScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4660toSpkPz2Gy4(MeasureScope measureScope, int i10) {
            return androidx.compose.ui.unit.a.k(measureScope, i10);
        }
    }

    MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, r7.l<? super Placeable.PlacementScope, t0> placementBlock);
}
