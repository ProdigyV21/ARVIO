package retrofit2;

/* JADX INFO: loaded from: classes5.dex */
public class HttpException extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f21464i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f21465l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient r0 f21466m;

    public HttpException(r0 r0Var) {
        StringBuilder sb2 = new StringBuilder("HTTP ");
        gb.p0 p0Var = r0Var.f21578a;
        int i10 = p0Var.f15783n;
        sb2.append(i10);
        sb2.append(" ");
        String str = p0Var.f15782m;
        sb2.append(str);
        super(sb2.toString());
        this.f21464i = i10;
        this.f21465l = str;
        this.f21466m = r0Var;
    }
}
