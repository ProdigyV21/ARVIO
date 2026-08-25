package g8;

import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 implements z0, q9.f, v0, w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final y0 f15559l = new y0(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15560i;

    public /* synthetic */ y0(int i10) {
        this.f15560i = i10;
    }

    @Override // q9.f
    public v9.w getType() {
        switch (this.f15560i) {
            case 1:
                throw new IllegalStateException("This method should not be called");
            case 2:
                throw new IllegalStateException("This method should not be called");
            default:
                throw new IllegalStateException("This method should not be called");
        }
    }

    public String toString() {
        switch (this.f15560i) {
            case 5:
                return "NO_SOURCE";
            default:
                return super.toString();
        }
    }

    @Override // g8.z0
    public Collection c(Collection collection) {
        return collection;
    }
}
