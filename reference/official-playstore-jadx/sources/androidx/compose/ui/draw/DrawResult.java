package androidx.compose.ui.draw;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\"\b\u0000\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\bR3\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u00058\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Landroidx/compose/ui/draw/DrawResult;", "", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "Lx6/n;", "block", "<init>", "(Lr7/l;)V", "Lr7/l;", "getBlock$ui_release", "()Lr7/l;", "setBlock$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DrawResult {
    public static final int $stable = 8;
    private l<? super ContentDrawScope, t0> block;

    public DrawResult(l<? super ContentDrawScope, t0> lVar) {
        this.block = lVar;
    }

    public final l<ContentDrawScope, t0> getBlock$ui_release() {
        return this.block;
    }

    public final void setBlock$ui_release(l<? super ContentDrawScope, t0> lVar) {
        this.block = lVar;
    }
}
