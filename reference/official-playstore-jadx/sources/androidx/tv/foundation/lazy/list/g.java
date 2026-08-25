package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements ModifierLocalProvider, BeyondBoundsLayout {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final d f5390p = new d();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f5391i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i2.i f5392l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f5393m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LayoutDirection f5394n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Orientation f5395o;

    public g(h hVar, i2.i iVar, boolean z, LayoutDirection layoutDirection, Orientation orientation) {
        this.f5391i = hVar;
        this.f5392l = iVar;
        this.f5393m = z;
        this.f5394n = layoutDirection;
        this.f5395o = orientation;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(i2.h r6, int r7) {
        /*
            r5 = this;
            androidx.compose.ui.layout.BeyondBoundsLayout$LayoutDirection$Companion r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.INSTANCE
            int r1 = r0.m4615getAbovehoxUOeE()
            boolean r1 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r1)
            r2 = 1
            if (r1 == 0) goto Lf
            r1 = r2
            goto L17
        Lf:
            int r1 = r0.m4618getBelowhoxUOeE()
            boolean r1 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r1)
        L17:
            androidx.compose.foundation.gestures.Orientation r3 = r5.f5395o
            r4 = 0
            if (r1 == 0) goto L24
            androidx.compose.foundation.gestures.Orientation r0 = androidx.compose.foundation.gestures.Orientation.Horizontal
            if (r3 != r0) goto L22
        L20:
            r0 = r2
            goto L56
        L22:
            r0 = r4
            goto L56
        L24:
            int r1 = r0.m4619getLefthoxUOeE()
            boolean r1 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r1)
            if (r1 == 0) goto L30
            r1 = r2
            goto L38
        L30:
            int r1 = r0.m4620getRighthoxUOeE()
            boolean r1 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r1)
        L38:
            if (r1 == 0) goto L3f
            androidx.compose.foundation.gestures.Orientation r0 = androidx.compose.foundation.gestures.Orientation.Vertical
            if (r3 != r0) goto L22
            goto L20
        L3f:
            int r1 = r0.m4617getBeforehoxUOeE()
            boolean r1 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r1)
            if (r1 == 0) goto L4b
            r0 = r2
            goto L53
        L4b:
            int r0 = r0.m4616getAfterhoxUOeE()
            boolean r0 = androidx.compose.ui.layout.BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(r7, r0)
        L53:
            if (r0 == 0) goto L71
            goto L22
        L56:
            if (r0 == 0) goto L59
            goto L70
        L59:
            boolean r7 = r5.b(r7)
            if (r7 == 0) goto L6b
            int r6 = r6.f16051b
            androidx.tv.foundation.lazy.list.h r7 = r5.f5391i
            int r7 = r7.getItemCount()
            int r7 = r7 - r2
            if (r6 >= r7) goto L70
            goto L6f
        L6b:
            int r6 = r6.f16050a
            if (r6 <= 0) goto L70
        L6f:
            return r2
        L70:
            return r4
        L71:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Lazy list does not support beyond bounds layout for the specified direction"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.g.a(i2.h, int):boolean");
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean all(r7.l lVar) {
        return androidx.compose.ui.b.a(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean any(r7.l lVar) {
        return androidx.compose.ui.b.b(this, lVar);
    }

    public final boolean b(int i10) {
        BeyondBoundsLayout.LayoutDirection.Companion companion = BeyondBoundsLayout.LayoutDirection.INSTANCE;
        if (BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4617getBeforehoxUOeE())) {
            return false;
        }
        if (!BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4616getAfterhoxUOeE())) {
            boolean zM4611equalsimpl0 = BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4615getAbovehoxUOeE());
            boolean z = this.f5393m;
            if (!zM4611equalsimpl0) {
                if (!BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4618getBelowhoxUOeE())) {
                    boolean zM4611equalsimpl02 = BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4619getLefthoxUOeE());
                    LayoutDirection layoutDirection = this.f5394n;
                    if (zM4611equalsimpl02) {
                        int i11 = e.f5383a[layoutDirection.ordinal()];
                        if (i11 != 1) {
                            if (i11 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            if (z) {
                                return false;
                            }
                        }
                    } else {
                        if (!BeyondBoundsLayout.LayoutDirection.m4611equalsimpl0(i10, companion.m4620getRighthoxUOeE())) {
                            throw new IllegalStateException("Lazy list does not support beyond bounds layout for the specified direction");
                        }
                        int i12 = e.f5383a[layoutDirection.ordinal()];
                        if (i12 != 1) {
                            if (i12 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                        } else if (z) {
                            return false;
                        }
                    }
                } else if (z) {
                    return false;
                }
            }
            return z;
        }
        return true;
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldIn(Object obj, r7.p pVar) {
        return androidx.compose.ui.b.c(this, obj, pVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldOut(Object obj, r7.p pVar) {
        return androidx.compose.ui.b.d(this, obj, pVar);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final ProvidableModifierLocal getKey() {
        return BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public final Object getValue() {
        return this;
    }

    @Override // androidx.compose.ui.layout.BeyondBoundsLayout
    /* JADX INFO: renamed from: layout-o7g1Pn8 */
    public final Object mo687layouto7g1Pn8(int i10, r7.l lVar) {
        h hVar = this.f5391i;
        if (hVar.getItemCount() <= 0 || !hVar.getHasVisibleItems()) {
            return lVar.invoke(f5390p);
        }
        int lastPlacedIndex = b(i10) ? hVar.getLastPlacedIndex() : hVar.getFirstPlacedIndex();
        kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
        i2.i iVar = this.f5392l;
        iVar.getClass();
        MutableVector mutableVector = iVar.f16052a;
        i2.h hVar2 = new i2.h(lastPlacedIndex, lastPlacedIndex);
        mutableVector.add(hVar2);
        k0Var.f19746i = hVar2;
        Object objInvoke = null;
        while (objInvoke == null && a((i2.h) k0Var.f19746i, i10)) {
            i2.h hVar3 = (i2.h) k0Var.f19746i;
            int i11 = hVar3.f16050a;
            int i12 = hVar3.f16051b;
            if (b(i10)) {
                i12++;
            } else {
                i11--;
            }
            i2.h hVar4 = new i2.h(i11, i12);
            mutableVector.add(hVar4);
            mutableVector.remove((i2.h) k0Var.f19746i);
            k0Var.f19746i = hVar4;
            hVar.remeasure();
            objInvoke = lVar.invoke(new f(this, k0Var, i10));
        }
        mutableVector.remove((i2.h) k0Var.f19746i);
        hVar.remeasure();
        return objInvoke;
    }

    @Override // androidx.compose.ui.Modifier
    public final /* synthetic */ Modifier then(Modifier modifier) {
        return androidx.compose.ui.a.a(this, modifier);
    }
}
