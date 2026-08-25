package n1;

import androidx.compose.runtime.ProduceStateScope;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements na.k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20428i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ProduceStateScope f20429l;

    public /* synthetic */ a(ProduceStateScope produceStateScope, int i10) {
        this.f20428i = i10;
        this.f20429l = produceStateScope;
    }

    @Override // na.k
    public final Object emit(Object obj, d7.d dVar) {
        switch (this.f20428i) {
            case 0:
                this.f20429l.setValue(obj);
                break;
            default:
                this.f20429l.setValue(obj);
                break;
        }
        return t0.f22605a;
    }
}
