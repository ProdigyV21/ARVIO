package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004*\u00020\u0006H&¢\u0006\u0004\b\u0007\u0010\bR0\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t8\u0010@\u0010X\u0090\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0001\u0003\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/graphics/vector/VNode;", "", "<init>", "()V", "Lx6/t0;", "invalidate", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "Lkotlin/Function1;", "invalidateListener", "Lr7/l;", "getInvalidateListener$ui_release", "()Lr7/l;", "setInvalidateListener$ui_release", "(Lr7/l;)V", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "Landroidx/compose/ui/graphics/vector/PathComponent;", "Landroidx/compose/ui/graphics/vector/VectorComponent;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class VNode {
    public static final int $stable = 8;
    private l<? super VNode, t0> invalidateListener;

    public /* synthetic */ VNode(h hVar) {
        this();
    }

    public abstract void draw(DrawScope drawScope);

    public l<VNode, t0> getInvalidateListener$ui_release() {
        return this.invalidateListener;
    }

    public final void invalidate() {
        l<VNode, t0> invalidateListener$ui_release = getInvalidateListener$ui_release();
        if (invalidateListener$ui_release != null) {
            invalidateListener$ui_release.invoke(this);
        }
    }

    public void setInvalidateListener$ui_release(l<? super VNode, t0> lVar) {
        this.invalidateListener = lVar;
    }

    private VNode() {
    }
}
