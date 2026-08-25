package ba;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m f7297d = new m("must be a member function", 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f7298e = new m("must be a member or an extension function", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7299c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(String str, int i10) {
        super(str, 0);
        this.f7299c = i10;
    }

    @Override // ba.e
    public final boolean c(r8.g gVar) {
        switch (this.f7299c) {
            case 0:
                return gVar.f19382t != null;
            default:
                return (gVar.f19382t == null && gVar.f19381s == null) ? false : true;
        }
    }
}
