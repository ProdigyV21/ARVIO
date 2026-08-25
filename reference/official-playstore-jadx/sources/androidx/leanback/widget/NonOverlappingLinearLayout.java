package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class NonOverlappingLinearLayout extends LinearLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2972i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2973l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f2974m;

    public NonOverlappingLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f2972i = false;
        this.f2974m = new ArrayList();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void focusableViewAvailable(View view) {
        int iIndexOfChild;
        if (!this.f2973l) {
            super.focusableViewAvailable(view);
            return;
        }
        for (View view2 = view; view2 != this && view2 != null; view2 = (View) view2.getParent()) {
            if (view2.getParent() == this) {
                iIndexOfChild = indexOfChild(view2);
                break;
            }
        }
        iIndexOfChild = -1;
        if (iIndexOfChild != -1) {
            ((ArrayList) this.f2974m.get(iIndexOfChild)).add(view);
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b3  */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r10, int r11, int r12, int r13, int r14) throws java.lang.Throwable {
        /*
            r9 = this;
            java.util.ArrayList r1 = r9.f2974m
            r2 = 0
            boolean r0 = r9.f2972i     // Catch: java.lang.Throwable -> L98
            r3 = 1
            if (r0 == 0) goto L1b
            int r0 = r9.getOrientation()     // Catch: java.lang.Throwable -> L16
            if (r0 != 0) goto L1b
            int r0 = r9.getLayoutDirection()     // Catch: java.lang.Throwable -> L16
            if (r0 != r3) goto L1b
            r0 = r3
            goto L1c
        L16:
            r0 = move-exception
            r10 = r0
            r3 = r9
            goto L9b
        L1b:
            r0 = r2
        L1c:
            r9.f2973l = r0     // Catch: java.lang.Throwable -> L98
            if (r0 == 0) goto L46
        L20:
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L16
            int r4 = r9.getChildCount()     // Catch: java.lang.Throwable -> L16
            if (r0 <= r4) goto L33
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L16
            int r0 = r0 - r3
            r1.remove(r0)     // Catch: java.lang.Throwable -> L16
            goto L20
        L33:
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L16
            int r3 = r9.getChildCount()     // Catch: java.lang.Throwable -> L16
            if (r0 >= r3) goto L46
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L16
            r0.<init>()     // Catch: java.lang.Throwable -> L16
            r1.add(r0)     // Catch: java.lang.Throwable -> L16
            goto L33
        L46:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r8 = r14
            super.onLayout(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L79
            boolean r10 = r3.f2973l     // Catch: java.lang.Throwable -> L79
            if (r10 == 0) goto L7f
            r10 = r2
        L54:
            int r11 = r1.size()     // Catch: java.lang.Throwable -> L79
            if (r10 >= r11) goto L7f
            r11 = r2
        L5b:
            java.lang.Object r12 = r1.get(r10)     // Catch: java.lang.Throwable -> L79
            java.util.ArrayList r12 = (java.util.ArrayList) r12     // Catch: java.lang.Throwable -> L79
            int r12 = r12.size()     // Catch: java.lang.Throwable -> L79
            if (r11 >= r12) goto L7c
            java.lang.Object r12 = r1.get(r10)     // Catch: java.lang.Throwable -> L79
            java.util.ArrayList r12 = (java.util.ArrayList) r12     // Catch: java.lang.Throwable -> L79
            java.lang.Object r12 = r12.get(r11)     // Catch: java.lang.Throwable -> L79
            android.view.View r12 = (android.view.View) r12     // Catch: java.lang.Throwable -> L79
            super.focusableViewAvailable(r12)     // Catch: java.lang.Throwable -> L79
            int r11 = r11 + 1
            goto L5b
        L79:
            r0 = move-exception
        L7a:
            r10 = r0
            goto L9b
        L7c:
            int r10 = r10 + 1
            goto L54
        L7f:
            boolean r10 = r3.f2973l
            if (r10 == 0) goto L97
            r3.f2973l = r2
        L85:
            int r10 = r1.size()
            if (r2 >= r10) goto L97
            java.lang.Object r10 = r1.get(r2)
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            r10.clear()
            int r2 = r2 + 1
            goto L85
        L97:
            return
        L98:
            r0 = move-exception
            r3 = r9
            goto L7a
        L9b:
            boolean r11 = r3.f2973l
            if (r11 == 0) goto Lb3
            r3.f2973l = r2
        La1:
            int r11 = r1.size()
            if (r2 >= r11) goto Lb3
            java.lang.Object r11 = r1.get(r2)
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            r11.clear()
            int r2 = r2 + 1
            goto La1
        Lb3:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.NonOverlappingLinearLayout.onLayout(boolean, int, int, int, int):void");
    }

    public void setFocusableViewAvailableFixEnabled(boolean z) {
        this.f2972i = z;
    }
}
