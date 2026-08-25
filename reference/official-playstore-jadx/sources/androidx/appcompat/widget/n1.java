package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class n1 extends m1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ o1 f1669l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n1(o1 o1Var) {
        super(o1Var);
        this.f1669l = o1Var;
    }

    @Override // androidx.appcompat.widget.m1, androidx.appcompat.widget.l1
    public final void c(int i10) {
        super/*android.widget.TextView*/.setLastBaselineToBottomHeight(i10);
    }

    @Override // androidx.appcompat.widget.m1, androidx.appcompat.widget.l1
    public final void l(int i10) {
        super/*android.widget.TextView*/.setFirstBaselineToTopHeight(i10);
    }
}
