package androidx.compose.ui.text;

import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a%\u0010\u0005\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\t\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\t\u0010\n\u001a<\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e0\rH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a%\u0010\u0014\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0014\u0010\u0006\u001a4\u0010\u0017\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\rH\u0082\b¢\u0006\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"", "Landroidx/compose/ui/text/ParagraphInfo;", "paragraphInfoList", "", "index", "findParagraphByIndex", "(Ljava/util/List;I)I", "", "y", "findParagraphByY", "(Ljava/util/List;F)I", "Landroidx/compose/ui/text/TextRange;", "range", "Lkotlin/Function1;", "Lx6/t0;", "action", "findParagraphsByRange-Sb-Bc2M", "(Ljava/util/List;JLr7/l;)V", "findParagraphsByRange", "lineIndex", "findParagraphByLineIndex", "T", "comparison", "fastBinarySearch", "(Ljava/util/List;Lr7/l;)I", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultiParagraphKt {
    private static final <T> int fastBinarySearch(List<? extends T> list, l<? super T, Integer> lVar) {
        int size = list.size() - 1;
        int i10 = 0;
        while (i10 <= size) {
            int i11 = (i10 + size) >>> 1;
            int iIntValue = ((Number) lVar.invoke(list.get(i11))).intValue();
            if (iIntValue < 0) {
                i10 = i11 + 1;
            } else {
                if (iIntValue <= 0) {
                    return i11;
                }
                size = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    public static final int findParagraphByIndex(List<ParagraphInfo> list, int i10) {
        int size = list.size() - 1;
        int i11 = 0;
        while (i11 <= size) {
            int i12 = (i11 + size) >>> 1;
            ParagraphInfo paragraphInfo = list.get(i12);
            byte b10 = paragraphInfo.getStartIndex() > i10 ? (byte) 1 : paragraphInfo.getEndIndex() <= i10 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i11 = i12 + 1;
            } else {
                if (b10 <= 0) {
                    return i12;
                }
                size = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    public static final int findParagraphByLineIndex(List<ParagraphInfo> list, int i10) {
        int size = list.size() - 1;
        int i11 = 0;
        while (i11 <= size) {
            int i12 = (i11 + size) >>> 1;
            ParagraphInfo paragraphInfo = list.get(i12);
            byte b10 = paragraphInfo.getStartLineIndex() > i10 ? (byte) 1 : paragraphInfo.getEndLineIndex() <= i10 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i11 = i12 + 1;
            } else {
                if (b10 <= 0) {
                    return i12;
                }
                size = i12 - 1;
            }
        }
        return -(i11 + 1);
    }

    public static final int findParagraphByY(List<ParagraphInfo> list, float f10) {
        int size = list.size() - 1;
        int i10 = 0;
        while (i10 <= size) {
            int i11 = (i10 + size) >>> 1;
            ParagraphInfo paragraphInfo = list.get(i11);
            byte b10 = paragraphInfo.getTop() > f10 ? (byte) 1 : paragraphInfo.getBottom() <= f10 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i10 = i11 + 1;
            } else {
                if (b10 <= 0) {
                    return i11;
                }
                size = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    /* JADX INFO: renamed from: findParagraphsByRange-Sb-Bc2M, reason: not valid java name */
    public static final void m5077findParagraphsByRangeSbBc2M(List<ParagraphInfo> list, long j10, l<? super ParagraphInfo, t0> lVar) {
        int size = list.size();
        for (int iFindParagraphByIndex = findParagraphByIndex(list, TextRange.m5190getMinimpl(j10)); iFindParagraphByIndex < size; iFindParagraphByIndex++) {
            ParagraphInfo paragraphInfo = list.get(iFindParagraphByIndex);
            if (paragraphInfo.getStartIndex() >= TextRange.m5189getMaximpl(j10)) {
                return;
            }
            if (paragraphInfo.getStartIndex() != paragraphInfo.getEndIndex()) {
                lVar.invoke(paragraphInfo);
            }
        }
    }
}
