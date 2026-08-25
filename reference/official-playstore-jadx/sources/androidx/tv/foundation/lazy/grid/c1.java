package androidx.tv.foundation.lazy.grid;

/* JADX INFO: loaded from: classes3.dex */
public final class c1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5189i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5190l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5191m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c1(int i10, int i11, int i12) {
        super(0);
        this.f5189i = i12;
        this.f5190l = i10;
        this.f5191m = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f5189i) {
            case 0:
                return new b1(this.f5190l, this.f5191m);
            default:
                return new androidx.tv.foundation.lazy.list.m0(this.f5190l, this.f5191m);
        }
    }
}
