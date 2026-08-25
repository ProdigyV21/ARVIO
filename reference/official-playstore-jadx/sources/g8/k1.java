package g8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15521b;

    public k1(String str, boolean z) {
        this.f15520a = str;
        this.f15521b = z;
    }

    public Integer a(k1 k1Var) {
        z6.f fVar = j1.f15518a;
        if (this == k1Var) {
            return 0;
        }
        z6.f fVar2 = j1.f15518a;
        Integer num = (Integer) fVar2.get(this);
        Integer num2 = (Integer) fVar2.get(k1Var);
        if (num == null || num2 == null || num.equals(num2)) {
            return null;
        }
        return Integer.valueOf(num.intValue() - num2.intValue());
    }

    public String b() {
        return this.f15520a;
    }

    public final String toString() {
        return b();
    }

    public k1 c() {
        return this;
    }
}
