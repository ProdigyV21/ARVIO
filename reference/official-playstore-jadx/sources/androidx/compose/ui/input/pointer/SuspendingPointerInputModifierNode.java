package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.PointerInputModifierNode;
import d7.d;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004R=\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0001\u0001\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Lx6/t0;", "resetPointerInputHandler", "()V", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Ld7/d;", "", "Lx6/n;", "getPointerInputHandler", "()Lr7/p;", "setPointerInputHandler", "(Lr7/p;)V", "pointerInputHandler", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SuspendingPointerInputModifierNode extends PointerInputModifierNode {
    p<PointerInputScope, d<? super t0>, Object> getPointerInputHandler();

    void resetPointerInputHandler();

    void setPointerInputHandler(p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar);
}
