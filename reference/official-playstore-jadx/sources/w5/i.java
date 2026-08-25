package w5;

/* JADX INFO: loaded from: classes4.dex */
public enum i implements i5.f {
    /* JADX INFO: Fake field, exist only in values array */
    COLLECTION_UNKNOWN(0),
    COLLECTION_SDK_NOT_INSTALLED(1),
    COLLECTION_ENABLED(2),
    COLLECTION_DISABLED(3),
    /* JADX INFO: Fake field, exist only in values array */
    COLLECTION_DISABLED_REMOTE(4),
    /* JADX INFO: Fake field, exist only in values array */
    COLLECTION_SAMPLED(5);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22453i;

    i(int i10) {
        this.f22453i = i10;
    }

    @Override // i5.f
    public final int getNumber() {
        return this.f22453i;
    }
}
