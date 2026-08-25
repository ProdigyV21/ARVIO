package i4;

/* JADX INFO: loaded from: classes4.dex */
public enum n implements a {
    /* JADX INFO: Fake field, exist only in values array */
    ED256(-260),
    /* JADX INFO: Fake field, exist only in values array */
    ED512(-261),
    /* JADX INFO: Fake field, exist only in values array */
    ED25519(-8),
    /* JADX INFO: Fake field, exist only in values array */
    ES256(-7),
    /* JADX INFO: Fake field, exist only in values array */
    ECDH_HKDF_256(-25),
    /* JADX INFO: Fake field, exist only in values array */
    ES384(-35),
    /* JADX INFO: Fake field, exist only in values array */
    ES512(-36);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16141i;

    n(int i10) {
        this.f16141i = i10;
    }

    @Override // i4.a
    public final int a() {
        return this.f16141i;
    }
}
