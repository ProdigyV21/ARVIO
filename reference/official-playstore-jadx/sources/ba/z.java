package ba;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final z f7338d = new z("must have no value parameters", 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f7339e = new z("must have a single value parameter", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7340c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(String str, int i10) {
        super(str, 1);
        this.f7340c = i10;
    }

    @Override // ba.e
    public final boolean c(r8.g gVar) {
        switch (this.f7340c) {
            case 0:
                return gVar.e().isEmpty();
            default:
                return gVar.e().size() == 1;
        }
    }
}
