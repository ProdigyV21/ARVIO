package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DelegatingNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B0\u0012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nRC\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragSourceNodeWithDefaultPainter;", "Landroidx/compose/ui/node/DelegatingNode;", "Lkotlin/Function2;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "dragAndDropSourceHandler", "<init>", "(Lr7/p;)V", "Lr7/p;", "getDragAndDropSourceHandler", "()Lr7/p;", "setDragAndDropSourceHandler", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DragSourceNodeWithDefaultPainter extends DelegatingNode {
    private p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> dragAndDropSourceHandler;

    /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragSourceNodeWithDefaultPainter$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<DrawScope, t0> {
        final /* synthetic */ CacheDrawScopeDragShadowCallback $cacheDrawScopeDragShadowCallback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CacheDrawScopeDragShadowCallback cacheDrawScopeDragShadowCallback) {
            super(1);
            this.$cacheDrawScopeDragShadowCallback = cacheDrawScopeDragShadowCallback;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return t0.f22605a;
        }

        public final void invoke(DrawScope drawScope) {
            this.$cacheDrawScopeDragShadowCallback.drawDragShadow(drawScope);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragSourceNodeWithDefaultPainter$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.draganddrop.DragSourceNodeWithDefaultPainter$2", f = "AndroidDragAndDropSource.android.kt", l = {101}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<DragAndDropSourceScope, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = DragSourceNodeWithDefaultPainter.this.new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(DragAndDropSourceScope dragAndDropSourceScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(dragAndDropSourceScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                DragAndDropSourceScope dragAndDropSourceScope = (DragAndDropSourceScope) this.L$0;
                p<DragAndDropSourceScope, d<? super t0>, Object> dragAndDropSourceHandler = DragSourceNodeWithDefaultPainter.this.getDragAndDropSourceHandler();
                this.label = 1;
                Object objInvoke = dragAndDropSourceHandler.invoke(dragAndDropSourceScope, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    public DragSourceNodeWithDefaultPainter(p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.dragAndDropSourceHandler = pVar;
        CacheDrawScopeDragShadowCallback cacheDrawScopeDragShadowCallback = new CacheDrawScopeDragShadowCallback();
        delegate(DrawModifierKt.CacheDrawModifierNode(new DragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1(cacheDrawScopeDragShadowCallback)));
        delegate(new DragAndDropSourceNode(new AnonymousClass1(cacheDrawScopeDragShadowCallback), new AnonymousClass2(null)));
    }

    public final p<DragAndDropSourceScope, d<? super t0>, Object> getDragAndDropSourceHandler() {
        return this.dragAndDropSourceHandler;
    }

    public final void setDragAndDropSourceHandler(p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.dragAndDropSourceHandler = pVar;
    }
}
