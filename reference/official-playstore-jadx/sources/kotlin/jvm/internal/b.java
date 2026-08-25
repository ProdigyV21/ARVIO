package kotlin.jvm.internal;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.collections.e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19732i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19733l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Cloneable f19734m;

    public /* synthetic */ b(Cloneable cloneable, int i10) {
        this.f19732i = i10;
        this.f19734m = cloneable;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f19732i) {
            case 0:
                if (this.f19733l < ((int[]) this.f19734m).length) {
                }
                break;
            default:
                if (this.f19733l < ((t.m0) this.f19734m).f()) {
                }
                break;
        }
        return false;
    }

    @Override // kotlin.collections.e0
    public final int nextInt() {
        switch (this.f19732i) {
            case 0:
                try {
                    int[] iArr = (int[]) this.f19734m;
                    int i10 = this.f19733l;
                    this.f19733l = i10 + 1;
                    return iArr[i10];
                } catch (ArrayIndexOutOfBoundsException e5) {
                    this.f19733l--;
                    throw new NoSuchElementException(e5.getMessage());
                }
            default:
                t.m0 m0Var = (t.m0) this.f19734m;
                int i11 = this.f19733l;
                this.f19733l = i11 + 1;
                return m0Var.d(i11);
        }
    }
}
