package g3;

/* JADX INFO: loaded from: classes4.dex */
public enum c implements j5.c {
    REASON_UNKNOWN(0),
    MESSAGE_TOO_OLD(1),
    CACHE_FULL(2),
    PAYLOAD_TOO_BIG(3),
    MAX_RETRIES_REACHED(4),
    INVALID_PAYLOD(5),
    SERVER_ERROR(6);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f15474i;

    c(int i10) {
        this.f15474i = i10;
    }

    @Override // j5.c
    public final int getNumber() {
        return this.f15474i;
    }
}
