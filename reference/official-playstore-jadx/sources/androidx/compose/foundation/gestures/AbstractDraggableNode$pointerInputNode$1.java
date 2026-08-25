package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1", f = "Draggable.kt", l = {456}, m = "invokeSuspend")
public final class AbstractDraggableNode$pointerInputNode$1 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AbstractDraggableNode this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1", f = "Draggable.kt", l = {458}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AbstractDraggableNode this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1", f = "Draggable.kt", l = {460, 475}, m = "invokeSuspend")
        public static final class C00151 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            final /* synthetic */ k0 $$this$coroutineScope;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ AbstractDraggableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00151(k0 k0Var, AbstractDraggableNode abstractDraggableNode, d7.d<? super C00151> dVar) {
                super(2, dVar);
                this.$$this$coroutineScope = k0Var;
                this.this$0 = abstractDraggableNode;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00151 c00151 = new C00151(this.$$this$coroutineScope, this.this$0, dVar);
                c00151.L$0 = obj;
                return c00151;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((C00151) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Can't wrap try/catch for region: R(10:25|84|28|29|82|30|31|90|32|(1:92)(10:35|86|36|37|(0)(0)|45|46|64|18|(2:78|79)(0))) */
            /* JADX WARN: Can't wrap try/catch for region: R(11:25|(1:27)|84|28|29|82|30|31|90|32|(1:92)(10:35|86|36|37|(0)(0)|45|46|64|18|(2:78|79)(0))) */
            /* JADX WARN: Code restructure failed: missing block: B:49:0x011c, code lost:
            
                r0 = th;
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x011d, code lost:
            
                r3 = r3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:51:0x0120, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x0121, code lost:
            
                r4 = r2;
                r3 = r3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x0124, code lost:
            
                r2 = r17;
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:0x012b, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x012c, code lost:
            
                r1 = r2;
                r17 = r4;
                r10 = r9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:58:0x0132, code lost:
            
                r4 = r1;
             */
            /* JADX WARN: Code restructure failed: missing block: B:59:0x0134, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x0135, code lost:
            
                r1 = r2;
                r17 = r4;
                r10 = r6;
             */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00e8  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x010f  */
            /* JADX WARN: Removed duplicated region for block: B:63:0x0142  */
            /* JADX WARN: Removed duplicated region for block: B:65:0x015e A[Catch: all -> 0x015f, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x015f, blocks: (B:61:0x013c, B:65:0x015e), top: B:88:0x013c }] */
            /* JADX WARN: Removed duplicated region for block: B:69:0x0170  */
            /* JADX WARN: Removed duplicated region for block: B:74:0x0197  */
            /* JADX WARN: Removed duplicated region for block: B:77:0x01a1  */
            /* JADX WARN: Removed duplicated region for block: B:78:0x01a7  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00cc -> B:86:0x00d2). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x0142 -> B:46:0x0118). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x01a1 -> B:18:0x004d). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 426
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1.AnonymousClass1.C00151.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PointerInputScope pointerInputScope, AbstractDraggableNode abstractDraggableNode, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
            this.this$0 = abstractDraggableNode;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$SuspendingPointerInputModifierNode, this.this$0, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [ka.k0] */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v6 */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ?? r02 = this.label;
            try {
                if (r02 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    PointerInputScope pointerInputScope = this.$$this$SuspendingPointerInputModifierNode;
                    C00151 c00151 = new C00151(k0Var, this.this$0, null);
                    this.L$0 = k0Var;
                    this.label = 1;
                    Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c00151, this);
                    e7.a aVar = e7.a.f15033i;
                    r02 = aVar;
                    if (objAwaitPointerEventScope == aVar) {
                        return aVar;
                    }
                } else {
                    if (r02 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k0 k0Var2 = (k0) this.L$0;
                    k2.c.G(obj);
                    r02 = k0Var2;
                }
            } catch (CancellationException e5) {
                if (!l0.d(r02)) {
                    throw e5;
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractDraggableNode$pointerInputNode$1(AbstractDraggableNode abstractDraggableNode, d7.d<? super AbstractDraggableNode$pointerInputNode$1> dVar) {
        super(2, dVar);
        this.this$0 = abstractDraggableNode;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        AbstractDraggableNode$pointerInputNode$1 abstractDraggableNode$pointerInputNode$1 = new AbstractDraggableNode$pointerInputNode$1(this.this$0, dVar);
        abstractDraggableNode$pointerInputNode$1.L$0 = obj;
        return abstractDraggableNode$pointerInputNode$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
        return ((AbstractDraggableNode$pointerInputNode$1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            if (!this.this$0.getEnabled()) {
                return t0Var;
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(pointerInputScope, this.this$0, null);
            this.label = 1;
            Object objC = l0.c(anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objC == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0Var;
    }
}
