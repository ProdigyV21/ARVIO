package z2;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f23160a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23161b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f23162c;

    public a(Integer num, Object obj, c cVar) {
        this.f23160a = num;
        if (obj == null) {
            throw new NullPointerException("Null payload");
        }
        this.f23161b = obj;
        this.f23162c = cVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            Integer num = aVar.f23160a;
            Integer num2 = this.f23160a;
            if (num2 != null ? num2.equals(num) : num == null) {
                if (this.f23161b.equals(aVar.f23161b) && this.f23162c.equals(aVar.f23162c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Integer num = this.f23160a;
        return (((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.f23161b.hashCode()) * 1000003) ^ this.f23162c.hashCode();
    }

    public final String toString() {
        return "Event{code=" + this.f23160a + ", payload=" + this.f23161b + ", priority=" + this.f23162c + "}";
    }
}
