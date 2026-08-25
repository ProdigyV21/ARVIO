package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f5002a;

    public o(r rVar) {
        this.f5002a = rVar;
    }

    @Override // androidx.recyclerview.widget.c1
    public final void a(RecyclerView recyclerView) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        r rVar = this.f5002a;
        int i10 = rVar.f5015a;
        int iComputeVerticalScrollRange = rVar.f5032s.computeVerticalScrollRange();
        int i11 = rVar.f5031r;
        rVar.f5033t = iComputeVerticalScrollRange - i11 > 0 && i11 >= i10;
        int iComputeHorizontalScrollRange = rVar.f5032s.computeHorizontalScrollRange();
        int i12 = rVar.f5030q;
        boolean z = iComputeHorizontalScrollRange - i12 > 0 && i12 >= i10;
        rVar.f5034u = z;
        boolean z5 = rVar.f5033t;
        if (!z5 && !z) {
            if (rVar.f5035v != 0) {
                rVar.f(0);
                return;
            }
            return;
        }
        if (z5) {
            float f10 = i11;
            rVar.f5025l = (int) ((((f10 / 2.0f) + iComputeVerticalScrollOffset) * f10) / iComputeVerticalScrollRange);
            rVar.k = Math.min(i11, (i11 * i11) / iComputeVerticalScrollRange);
        }
        if (rVar.f5034u) {
            float f11 = iComputeHorizontalScrollOffset;
            float f12 = i12;
            rVar.f5028o = (int) ((((f12 / 2.0f) + f11) * f12) / iComputeHorizontalScrollRange);
            rVar.f5027n = Math.min(i12, (i12 * i12) / iComputeHorizontalScrollRange);
        }
        int i13 = rVar.f5035v;
        if (i13 == 0 || i13 == 1) {
            rVar.f(1);
        }
    }
}
