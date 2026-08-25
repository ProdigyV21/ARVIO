package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.util.SparseArray;
import android.view.KeyEvent;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f2657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f2658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f2659c;

    public z(e0 e0Var, a1.a aVar, d dVar, Set set) {
        this.f2657a = aVar;
        this.f2658b = e0Var;
        this.f2659c = dVar;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            c(str, 0, str.length(), 1, true, new x(str));
        }
    }

    public static boolean a(Editable editable, KeyEvent keyEvent, boolean z) {
        j0[] j0VarArr;
        if (KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (j0VarArr = (j0[]) editable.getSpans(selectionStart, selectionEnd, j0.class)) != null && j0VarArr.length > 0) {
                for (j0 j0Var : j0VarArr) {
                    int spanStart = editable.getSpanStart(j0Var);
                    int spanEnd = editable.getSpanEnd(j0Var);
                    if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                        editable.delete(spanStart, spanEnd);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean b(CharSequence charSequence, int i10, int i11, i0 i0Var) {
        if ((i0Var.f2618c & 3) == 0) {
            i iVar = this.f2659c;
            androidx.emoji2.text.flatbuffer.a aVarB = i0Var.b();
            int iA = aVarB.a(8);
            if (iA != 0) {
                ((ByteBuffer) aVarB.f2241n).getShort(iA + aVarB.f2238i);
            }
            boolean zA = iVar.a(charSequence, i10, i11);
            int i12 = i0Var.f2618c & 4;
            i0Var.f2618c = zA ? i12 | 2 : i12 | 1;
        }
        return (i0Var.f2618c & 3) == 2;
    }

    public final Object c(CharSequence charSequence, int i10, int i11, int i12, boolean z, v vVar) {
        int i13;
        char c10;
        y yVar = new y((d0) this.f2658b.f2602n);
        int iCodePointAt = Character.codePointAt(charSequence, i10);
        int i14 = 0;
        boolean zB = true;
        int iCharCount = i10;
        loop0: while (true) {
            i13 = iCharCount;
            while (iCharCount < i11 && i14 < i12 && zB) {
                SparseArray sparseArray = ((d0) yVar.f2655e).f2597a;
                d0 d0Var = sparseArray == null ? null : (d0) sparseArray.get(iCodePointAt);
                if (yVar.f2651a == 2) {
                    if (d0Var != null) {
                        yVar.f2655e = d0Var;
                        yVar.f2653c++;
                    } else {
                        if (iCodePointAt == 65038) {
                            yVar.b();
                        } else if (iCodePointAt != 65039) {
                            d0 d0Var2 = (d0) yVar.f2655e;
                            if (d0Var2.f2598b != null) {
                                if (yVar.f2653c != 1) {
                                    yVar.f2656f = d0Var2;
                                    yVar.b();
                                } else if (yVar.c()) {
                                    yVar.f2656f = (d0) yVar.f2655e;
                                    yVar.b();
                                } else {
                                    yVar.b();
                                }
                                c10 = 3;
                            } else {
                                yVar.b();
                            }
                        }
                        c10 = 1;
                    }
                    c10 = 2;
                } else if (d0Var == null) {
                    yVar.b();
                    c10 = 1;
                } else {
                    yVar.f2651a = 2;
                    yVar.f2655e = d0Var;
                    yVar.f2653c = 1;
                    c10 = 2;
                }
                yVar.f2652b = iCodePointAt;
                if (c10 == 1) {
                    iCharCount = Character.charCount(Character.codePointAt(charSequence, i13)) + i13;
                    if (iCharCount < i11) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount);
                    }
                } else if (c10 == 2) {
                    int iCharCount2 = Character.charCount(iCodePointAt) + iCharCount;
                    if (iCharCount2 < i11) {
                        iCodePointAt = Character.codePointAt(charSequence, iCharCount2);
                    }
                    iCharCount = iCharCount2;
                } else if (c10 == 3) {
                    if (z || !b(charSequence, i13, iCharCount, ((d0) yVar.f2656f).f2598b)) {
                        zB = vVar.b(charSequence, i13, iCharCount, ((d0) yVar.f2656f).f2598b);
                        i14++;
                    }
                }
            }
            break loop0;
        }
        if (yVar.f2651a == 2 && ((d0) yVar.f2655e).f2598b != null && ((yVar.f2653c > 1 || yVar.c()) && i14 < i12 && zB && (z || !b(charSequence, i13, iCharCount, ((d0) yVar.f2655e).f2598b)))) {
            vVar.b(charSequence, i13, iCharCount, ((d0) yVar.f2655e).f2598b);
        }
        return vVar.getResult();
    }
}
