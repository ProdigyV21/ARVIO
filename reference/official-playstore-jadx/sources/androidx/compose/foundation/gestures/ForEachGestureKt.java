package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.google.android.gms.cast.MediaError;
import d7.j;
import java.util.List;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a=\u0010\u0007\u001a\u00020\u0003*\u00020\u00002'\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001¢\u0006\u0002\b\u0005H\u0087@¢\u0006\u0004\b\u0007\u0010\b\u001a\u0013\u0010\u000b\u001a\u00020\n*\u00020\tH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0014\u0010\r\u001a\u00020\u0003*\u00020\u0000H\u0080@¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\r\u001a\u00020\u0003*\u00020\tH\u0080@¢\u0006\u0004\b\r\u0010\u000f\u001a=\u0010\u0010\u001a\u00020\u0003*\u00020\u00002'\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001¢\u0006\u0002\b\u0005H\u0086@¢\u0006\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lkotlin/Function2;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "block", "forEachGesture", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "", "allPointersUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)Z", "awaitAllPointersUp", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Ld7/d;)Ljava/lang/Object;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Ld7/d;)Ljava/lang/Object;", "awaitEachGesture", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForEachGestureKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$2", f = "ForEachGesture.kt", l = {77}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.label = 1;
                Object objAwaitAllPointersUp = ForEachGestureKt.awaitAllPointersUp(awaitPointerEventScope, this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitAllPointersUp == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.ForEachGestureKt", f = "ForEachGesture.kt", l = {86}, m = "awaitAllPointersUp")
    public static final class AnonymousClass3 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d7.d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ForEachGestureKt.awaitAllPointersUp((AwaitPointerEventScope) null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitEachGesture$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ForEachGestureKt$awaitEachGesture$2", f = "ForEachGesture.kt", l = {MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED, 107, 112}, m = "invokeSuspend")
    public static final class C03162 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ p<AwaitPointerEventScope, d7.d<? super t0>, Object> $block;
        final /* synthetic */ j $currentContext;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03162(j jVar, p<? super AwaitPointerEventScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super C03162> dVar) {
            super(2, dVar);
            this.$currentContext = jVar;
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03162 c03162 = new C03162(this.$currentContext, this.$block, dVar);
            c03162.L$0 = obj;
            return c03162;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((C03162) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0057, code lost:
        
            if (r8 != r4) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x006e, code lost:
        
            if (r8 == r4) goto L34;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v22 */
        /* JADX WARN: Type inference failed for: r0v23 */
        /* JADX WARN: Type inference failed for: r0v3, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r8v12 */
        /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r8v8 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0057 -> B:12:0x0026). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x006e -> B:12:0x0026). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                int r0 = r7.label
                r1 = 3
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L32
                if (r0 == r3) goto L2a
                if (r0 == r2) goto L1f
                if (r0 != r1) goto L17
                java.lang.Object r0 = r7.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r8)
                goto L26
            L17:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1f:
                java.lang.Object r0 = r7.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r8)     // Catch: java.util.concurrent.CancellationException -> L28
            L26:
                r8 = r0
                goto L39
            L28:
                r8 = move-exception
                goto L5e
            L2a:
                java.lang.Object r0 = r7.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r8)     // Catch: java.util.concurrent.CancellationException -> L28
                goto L4f
            L32:
                k2.c.G(r8)
                java.lang.Object r8 = r7.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r8 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r8
            L39:
                d7.j r0 = r7.$currentContext
                boolean r0 = ka.m0.m(r0)
                if (r0 == 0) goto L72
                r7.p<androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d<? super x6.t0>, java.lang.Object> r0 = r7.$block     // Catch: java.util.concurrent.CancellationException -> L5a
                r7.L$0 = r8     // Catch: java.util.concurrent.CancellationException -> L5a
                r7.label = r3     // Catch: java.util.concurrent.CancellationException -> L5a
                java.lang.Object r0 = r0.invoke(r8, r7)     // Catch: java.util.concurrent.CancellationException -> L5a
                if (r0 != r4) goto L4e
                goto L70
            L4e:
                r0 = r8
            L4f:
                r7.L$0 = r0     // Catch: java.util.concurrent.CancellationException -> L28
                r7.label = r2     // Catch: java.util.concurrent.CancellationException -> L28
                java.lang.Object r8 = androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp(r0, r7)     // Catch: java.util.concurrent.CancellationException -> L28
                if (r8 != r4) goto L26
                goto L70
            L5a:
                r0 = move-exception
                r6 = r0
                r0 = r8
                r8 = r6
            L5e:
                d7.j r5 = r7.$currentContext
                boolean r5 = ka.m0.m(r5)
                if (r5 == 0) goto L71
                r7.L$0 = r0
                r7.label = r1
                java.lang.Object r8 = androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp(r0, r7)
                if (r8 != r4) goto L26
            L70:
                return r4
            L71:
                throw r8
            L72:
                x6.t0 r8 = x6.t0.f22605a
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.C03162.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.ForEachGestureKt", f = "ForEachGesture.kt", l = {48, 51, 56}, m = "forEachGesture")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ForEachGestureKt.forEachGesture(null, null, this);
        }
    }

    public static final boolean allPointersUp(AwaitPointerEventScope awaitPointerEventScope) {
        List<PointerInputChange> changes = awaitPointerEventScope.getCurrentEvent().getChanges();
        int size = changes.size();
        boolean z = false;
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                break;
            }
            if (changes.get(i10).getPressed()) {
                z = true;
                break;
            }
            i10++;
        }
        return !z;
    }

    public static final Object awaitAllPointersUp(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new AnonymousClass2(null), dVar);
        return objAwaitPointerEventScope == e7.a.f15033i ? objAwaitPointerEventScope : t0.f22605a;
    }

    public static final Object awaitEachGesture(PointerInputScope pointerInputScope, p<? super AwaitPointerEventScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C03162(dVar.getContext(), pVar, null), dVar);
        return objAwaitPointerEventScope == e7.a.f15033i ? objAwaitPointerEventScope : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x008f, code lost:
    
        if (r10 != r5) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a9, code lost:
    
        if (r10 == r5) goto L40;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v6, types: [d7.j, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [androidx.compose.ui.input.pointer.PointerInputScope] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2, types: [d7.j, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x008f -> B:18:0x0050). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00a9 -> B:18:0x0050). Please report as a decompilation issue!!! */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object forEachGesture(androidx.compose.ui.input.pointer.PointerInputScope r8, r7.p<? super androidx.compose.ui.input.pointer.PointerInputScope, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r9, d7.d<? super x6.t0> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L65
            if (r1 == r4) goto L55
            if (r1 == r3) goto L41
            if (r1 != r2) goto L39
            java.lang.Object r8 = r0.L$2
            d7.j r8 = (d7.j) r8
            java.lang.Object r9 = r0.L$1
            r7.p r9 = (r7.p) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r1 = (androidx.compose.ui.input.pointer.PointerInputScope) r1
            k2.c.G(r10)
            goto L50
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L41:
            java.lang.Object r8 = r0.L$2
            d7.j r8 = (d7.j) r8
            java.lang.Object r9 = r0.L$1
            r7.p r9 = (r7.p) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r1 = (androidx.compose.ui.input.pointer.PointerInputScope) r1
            k2.c.G(r10)     // Catch: java.util.concurrent.CancellationException -> L53
        L50:
            r10 = r8
            r8 = r1
            goto L6c
        L53:
            r10 = move-exception
            goto L97
        L55:
            java.lang.Object r8 = r0.L$2
            d7.j r8 = (d7.j) r8
            java.lang.Object r9 = r0.L$1
            r7.p r9 = (r7.p) r9
            java.lang.Object r1 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputScope r1 = (androidx.compose.ui.input.pointer.PointerInputScope) r1
            k2.c.G(r10)     // Catch: java.util.concurrent.CancellationException -> L53
            goto L83
        L65:
            k2.c.G(r10)
            d7.j r10 = r0.getContext()
        L6c:
            boolean r1 = ka.m0.m(r10)
            if (r1 == 0) goto Lad
            r0.L$0 = r8     // Catch: java.util.concurrent.CancellationException -> L92
            r0.L$1 = r9     // Catch: java.util.concurrent.CancellationException -> L92
            r0.L$2 = r10     // Catch: java.util.concurrent.CancellationException -> L92
            r0.label = r4     // Catch: java.util.concurrent.CancellationException -> L92
            java.lang.Object r1 = r9.invoke(r8, r0)     // Catch: java.util.concurrent.CancellationException -> L92
            if (r1 != r5) goto L81
            goto Lab
        L81:
            r1 = r8
            r8 = r10
        L83:
            r0.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> L53
            r0.L$1 = r9     // Catch: java.util.concurrent.CancellationException -> L53
            r0.L$2 = r8     // Catch: java.util.concurrent.CancellationException -> L53
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L53
            java.lang.Object r10 = awaitAllPointersUp(r1, r0)     // Catch: java.util.concurrent.CancellationException -> L53
            if (r10 != r5) goto L50
            goto Lab
        L92:
            r1 = move-exception
            r7 = r1
            r1 = r8
            r8 = r10
            r10 = r7
        L97:
            boolean r6 = ka.m0.m(r8)
            if (r6 == 0) goto Lac
            r0.L$0 = r1
            r0.L$1 = r9
            r0.L$2 = r8
            r0.label = r2
            java.lang.Object r10 = awaitAllPointersUp(r1, r0)
            if (r10 != r5) goto L50
        Lab:
            return r5
        Lac:
            throw r10
        Lad:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.forEachGesture(androidx.compose.ui.input.pointer.PointerInputScope, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
    
        if (allPointersUp(r5) == false) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0046 -> B:19:0x0049). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, d7.d<? super x6.t0> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
            k2.c.G(r6)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            boolean r6 = allPointersUp(r5)
            if (r6 != 0) goto L66
        L3a:
            androidx.compose.ui.input.pointer.PointerEventPass r6 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r5.awaitPointerEvent(r6, r0)
            e7.a r1 = e7.a.f15033i
            if (r6 != r1) goto L49
            return r1
        L49:
            androidx.compose.ui.input.pointer.PointerEvent r6 = (androidx.compose.ui.input.pointer.PointerEvent) r6
            java.util.List r6 = r6.getChanges()
            int r1 = r6.size()
            r3 = 0
        L54:
            if (r3 >= r1) goto L66
            java.lang.Object r4 = r6.get(r3)
            androidx.compose.ui.input.pointer.PointerInputChange r4 = (androidx.compose.ui.input.pointer.PointerInputChange) r4
            boolean r4 = r4.getPressed()
            if (r4 == 0) goto L63
            goto L3a
        L63:
            int r3 = r3 + 1
            goto L54
        L66:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d):java.lang.Object");
    }
}
