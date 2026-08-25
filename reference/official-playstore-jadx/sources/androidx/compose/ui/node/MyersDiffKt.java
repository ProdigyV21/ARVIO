package androidx.compose.ui.node;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\u001a'\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a'\u0010\r\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\r\u0010\u000e\u001aR\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001aZ\u0010\u0014\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aZ\u0010\u0015\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001e\u001a?\u0010&\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0016H\u0000¢\u0006\u0004\b&\u0010'\u001a#\u0010*\u001a\u00020\n*\u00020\u00162\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0000H\u0002¢\u0006\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"", "oldSize", "newSize", "Landroidx/compose/ui/node/DiffCallback;", "cb", "Landroidx/compose/ui/node/IntStack;", "calculateDiff", "(IILandroidx/compose/ui/node/DiffCallback;)Landroidx/compose/ui/node/IntStack;", "diagonals", "callback", "Lx6/t0;", "applyDiff", "(Landroidx/compose/ui/node/IntStack;Landroidx/compose/ui/node/DiffCallback;)V", "executeDiff", "(IILandroidx/compose/ui/node/DiffCallback;)V", "oldStart", "oldEnd", "newStart", "newEnd", "Landroidx/compose/ui/node/CenteredArray;", "forward", "backward", "", "snake", "", "midPoint-q5eDKzI", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[I[I)Z", "midPoint", "d", "forward-4l5_RBY", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[II[I)Z", "backward-4l5_RBY", "startX", "startY", "endX", "endY", "reverse", "data", "fillSnake", "(IIIIZ[I)V", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "j", "swap", "([III)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MyersDiffKt {
    private static final void applyDiff(IntStack intStack, DiffCallback diffCallback) {
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i10 < intStack.getSize()) {
            int i13 = i10 + 2;
            int i14 = intStack.get(i10) - intStack.get(i13);
            int i15 = intStack.get(i10 + 1) - intStack.get(i13);
            int i16 = intStack.get(i13);
            i10 += 3;
            while (i11 < i14) {
                diffCallback.remove(i12, i11);
                i11++;
            }
            while (i12 < i15) {
                diffCallback.insert(i12);
                i12++;
            }
            while (true) {
                int i17 = i16 - 1;
                if (i16 > 0) {
                    diffCallback.same(i11, i12);
                    i11++;
                    i12++;
                    i16 = i17;
                }
            }
        }
    }

    /* JADX INFO: renamed from: backward-4l5_RBY, reason: not valid java name */
    private static final boolean m4796backward4l5_RBY(int i10, int i11, int i12, int i13, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i14, int[] iArr3) {
        int iM4716getimpl;
        int i15;
        int i16;
        int i17 = (i11 - i10) - (i13 - i12);
        boolean z = i17 % 2 == 0;
        int i18 = -i14;
        for (int i19 = i18; i19 <= i14; i19 += 2) {
            if (i19 == i18 || (i19 != i14 && CenteredArray.m4716getimpl(iArr2, i19 + 1) < CenteredArray.m4716getimpl(iArr2, i19 - 1))) {
                iM4716getimpl = CenteredArray.m4716getimpl(iArr2, i19 + 1);
                i15 = iM4716getimpl;
            } else {
                iM4716getimpl = CenteredArray.m4716getimpl(iArr2, i19 - 1);
                i15 = iM4716getimpl - 1;
            }
            int i20 = i13 - ((i11 - i15) - i19);
            int i21 = (i14 == 0 || i15 != iM4716getimpl) ? i20 : i20 + 1;
            while (i15 > i10 && i20 > i12) {
                if (!diffCallback.areItemsTheSame(i15 - 1, i20 - 1)) {
                    break;
                }
                i15--;
                i20--;
            }
            CenteredArray.m4719setimpl(iArr2, i19, i15);
            if (z && (i16 = i17 - i19) >= i18 && i16 <= i14) {
                if (CenteredArray.m4716getimpl(iArr, i16) >= i15) {
                    fillSnake(i15, i20, iM4716getimpl, i21, true, iArr3);
                    return true;
                }
            }
        }
        return false;
    }

    private static final IntStack calculateDiff(int i10, int i11, DiffCallback diffCallback) {
        int i12 = ((i10 + i11) + 1) / 2;
        IntStack intStack = new IntStack(i12 * 3);
        IntStack intStack2 = new IntStack(i12 * 4);
        intStack2.pushRange(0, i10, 0, i11);
        int i13 = (i12 * 2) + 1;
        int[] iArrM4713constructorimpl = CenteredArray.m4713constructorimpl(new int[i13]);
        int[] iArrM4713constructorimpl2 = CenteredArray.m4713constructorimpl(new int[i13]);
        int[] iArrM4903constructorimpl = Snake.m4903constructorimpl(new int[5]);
        while (intStack2.isNotEmpty()) {
            int iPop = intStack2.pop();
            int iPop2 = intStack2.pop();
            int iPop3 = intStack2.pop();
            int iPop4 = intStack2.pop();
            DiffCallback diffCallback2 = diffCallback;
            if (m4798midPointq5eDKzI(iPop4, iPop3, iPop2, iPop, diffCallback2, iArrM4713constructorimpl, iArrM4713constructorimpl2, iArrM4903constructorimpl)) {
                if (Snake.m4906getDiagonalSizeimpl(iArrM4903constructorimpl) > 0) {
                    Snake.m4901addDiagonalToStackimpl(iArrM4903constructorimpl, intStack);
                }
                intStack2.pushRange(iPop4, Snake.m4911getStartXimpl(iArrM4903constructorimpl), iPop2, Snake.m4912getStartYimpl(iArrM4903constructorimpl));
                intStack2.pushRange(Snake.m4907getEndXimpl(iArrM4903constructorimpl), iPop3, Snake.m4908getEndYimpl(iArrM4903constructorimpl), iPop);
            }
            diffCallback = diffCallback2;
        }
        intStack.sortDiagonals();
        intStack.pushDiagonal(i10, i11, 0);
        return intStack;
    }

    public static final void executeDiff(int i10, int i11, DiffCallback diffCallback) {
        applyDiff(calculateDiff(i10, i11, diffCallback), diffCallback);
    }

    public static final void fillSnake(int i10, int i11, int i12, int i13, boolean z, int[] iArr) {
        iArr[0] = i10;
        iArr[1] = i11;
        iArr[2] = i12;
        iArr[3] = i13;
        iArr[4] = z ? 1 : 0;
    }

    /* JADX INFO: renamed from: forward-4l5_RBY, reason: not valid java name */
    private static final boolean m4797forward4l5_RBY(int i10, int i11, int i12, int i13, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i14, int[] iArr3) {
        int iM4716getimpl;
        int i15;
        int i16 = (i11 - i10) - (i13 - i12);
        boolean z = Math.abs(i16) % 2 == 1;
        int i17 = -i14;
        for (int i18 = i17; i18 <= i14; i18 += 2) {
            if (i18 == i17 || (i18 != i14 && CenteredArray.m4716getimpl(iArr, i18 + 1) > CenteredArray.m4716getimpl(iArr, i18 - 1))) {
                iM4716getimpl = CenteredArray.m4716getimpl(iArr, i18 + 1);
                i15 = iM4716getimpl;
            } else {
                iM4716getimpl = CenteredArray.m4716getimpl(iArr, i18 - 1);
                i15 = iM4716getimpl + 1;
            }
            int i19 = ((i15 - i10) + i12) - i18;
            int i20 = (i14 == 0 || i15 != iM4716getimpl) ? i19 : i19 - 1;
            while (i15 < i11 && i19 < i13) {
                if (!diffCallback.areItemsTheSame(i15, i19)) {
                    break;
                }
                i15++;
                i19++;
            }
            CenteredArray.m4719setimpl(iArr, i18, i15);
            if (z) {
                int i21 = i16 - i18;
                if (i21 >= i17 + 1 && i21 <= i14 - 1) {
                    if (CenteredArray.m4716getimpl(iArr2, i21) <= i15) {
                        fillSnake(iM4716getimpl, i20, i15, i19, false, iArr3);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: midPoint-q5eDKzI, reason: not valid java name */
    private static final boolean m4798midPointq5eDKzI(int i10, int i11, int i12, int i13, DiffCallback diffCallback, int[] iArr, int[] iArr2, int[] iArr3) {
        int i14 = i11 - i10;
        int i15 = i13 - i12;
        if (i14 >= 1 && i15 >= 1) {
            int i16 = ((i14 + i15) + 1) / 2;
            int[] iArr4 = iArr;
            CenteredArray.m4719setimpl(iArr4, 1, i10);
            int[] iArr5 = iArr2;
            CenteredArray.m4719setimpl(iArr5, 1, i11);
            int i17 = 0;
            while (i17 < i16) {
                if (m4797forward4l5_RBY(i10, i11, i12, i13, diffCallback, iArr4, iArr5, i17, iArr3) || m4796backward4l5_RBY(i10, i11, i12, i13, diffCallback, iArr, iArr2, i17, iArr3)) {
                    return true;
                }
                i17++;
                iArr4 = iArr;
                iArr5 = iArr2;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(int[] iArr, int i10, int i11) {
        int i12 = iArr[i10];
        iArr[i10] = iArr[i11];
        iArr[i11] = i12;
    }
}
