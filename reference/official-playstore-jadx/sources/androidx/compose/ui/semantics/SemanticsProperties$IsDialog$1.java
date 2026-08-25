package androidx.compose.ui.semantics;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0010\u0005\u001a\u0004\u0018\u00010\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lx6/t0;", "<anonymous parameter 0>", "<anonymous parameter 1>", "invoke", "(Lx6/t0;Lx6/t0;)Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SemanticsProperties$IsDialog$1 extends r implements p<t0, t0, t0> {
    public static final SemanticsProperties$IsDialog$1 INSTANCE = new SemanticsProperties$IsDialog$1();

    public SemanticsProperties$IsDialog$1() {
        super(2);
    }

    @Override // r7.p
    public final t0 invoke(t0 t0Var, t0 t0Var2) {
        throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
    }
}
