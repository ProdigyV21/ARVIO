package g8;

import io.ktor.client.utils.CacheControl;

/* JADX INFO: loaded from: classes5.dex */
public final class i1 extends k1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i1 f15507d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final i1 f15508e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final i1 f15509f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final i1 f15510g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final i1 f15511h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i1 f15512i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final i1 f15513j;
    public static final i1 k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i1 f15514l = new i1("unknown", false, 8);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f15515c;

    static {
        boolean z = false;
        f15507d = new i1("inherited", z, 0);
        f15508e = new i1("internal", z, 1);
        f15509f = new i1("invisible_fake", z, 2);
        f15510g = new i1("local", z, 3);
        f15511h = new i1(CacheControl.PRIVATE, z, 4);
        f15512i = new i1("private_to_this", z, 5);
        boolean z5 = true;
        f15513j = new i1("protected", z5, 6);
        k = new i1(CacheControl.PUBLIC, z5, 7);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i1(String str, boolean z, int i10) {
        super(str, z);
        this.f15515c = i10;
    }

    @Override // g8.k1
    public String b() {
        switch (this.f15515c) {
            case 5:
                return "private/*private to this*/";
            default:
                return super.b();
        }
    }
}
