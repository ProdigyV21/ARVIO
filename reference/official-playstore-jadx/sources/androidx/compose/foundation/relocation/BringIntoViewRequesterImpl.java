package androidx.compose.foundation.relocation;

import androidx.compose.runtime.collection.MutableVector;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096@¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewRequesterImpl;", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "<init>", "()V", "Landroidx/compose/ui/geometry/Rect;", "rect", "Lx6/t0;", "bringIntoView", "(Landroidx/compose/ui/geometry/Rect;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/relocation/BringIntoViewRequesterNode;", "modifiers", "Landroidx/compose/runtime/collection/MutableVector;", "getModifiers", "()Landroidx/compose/runtime/collection/MutableVector;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BringIntoViewRequesterImpl implements BringIntoViewRequester {
    private final MutableVector<BringIntoViewRequesterNode> modifiers = new MutableVector<>(new BringIntoViewRequesterNode[16], 0);

    /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.relocation.BringIntoViewRequesterImpl", f = "BringIntoViewRequester.kt", l = {119}, m = "bringIntoView")
    public static final class AnonymousClass1 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BringIntoViewRequesterImpl.this.bringIntoView(null, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
    
        if (r8 < r1) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0062 -> B:20:0x0065). Please report as a decompilation issue!!! */
    @Override // androidx.compose.foundation.relocation.BringIntoViewRequester
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object bringIntoView(androidx.compose.ui.geometry.Rect r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = (androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = new androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            int r8 = r0.I$1
            int r1 = r0.I$0
            java.lang.Object r3 = r0.L$1
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            java.lang.Object r4 = r0.L$0
            androidx.compose.ui.geometry.Rect r4 = (androidx.compose.ui.geometry.Rect) r4
            k2.c.G(r9)
            r9 = r4
            goto L65
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            k2.c.G(r9)
            androidx.compose.runtime.collection.MutableVector<androidx.compose.foundation.relocation.BringIntoViewRequesterNode> r9 = r7.modifiers
            int r1 = r9.getSize()
            if (r1 <= 0) goto L68
            java.lang.Object[] r9 = r9.getContent()
            r3 = 0
            r6 = r9
            r9 = r8
            r8 = r3
            r3 = r6
        L4e:
            r4 = r3[r8]
            androidx.compose.foundation.relocation.BringIntoViewRequesterNode r4 = (androidx.compose.foundation.relocation.BringIntoViewRequesterNode) r4
            r0.L$0 = r9
            r0.L$1 = r3
            r0.I$0 = r1
            r0.I$1 = r8
            r0.label = r2
            java.lang.Object r4 = r4.bringIntoView(r9, r0)
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L65
            return r5
        L65:
            int r8 = r8 + r2
            if (r8 < r1) goto L4e
        L68:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.bringIntoView(androidx.compose.ui.geometry.Rect, d7.d):java.lang.Object");
    }

    public final MutableVector<BringIntoViewRequesterNode> getModifiers() {
        return this.modifiers;
    }
}
