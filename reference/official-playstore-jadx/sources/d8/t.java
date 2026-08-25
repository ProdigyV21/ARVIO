package d8;

/* JADX INFO: loaded from: classes5.dex */
public enum t {
    /* JADX INFO: Fake field, exist only in values array */
    UBYTE(f9.b.e("kotlin/UByte", false)),
    /* JADX INFO: Fake field, exist only in values array */
    USHORT(f9.b.e("kotlin/UShort", false)),
    /* JADX INFO: Fake field, exist only in values array */
    UINT(f9.b.e("kotlin/UInt", false)),
    /* JADX INFO: Fake field, exist only in values array */
    ULONG(f9.b.e("kotlin/ULong", false));


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f9.b f14780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f9.f f14781l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f9.b f14782m;

    t(f9.b bVar) {
        this.f14780i = bVar;
        f9.f fVarI = bVar.i();
        this.f14781l = fVarI;
        this.f14782m = new f9.b(bVar.g(), f9.f.j(fVarI.d() + "Array"));
    }
}
