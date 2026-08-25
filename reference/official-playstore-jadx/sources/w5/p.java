package w5;

/* JADX INFO: loaded from: classes4.dex */
public enum p implements i5.f {
    /* JADX INFO: Fake field, exist only in values array */
    LOG_ENVIRONMENT_UNKNOWN(0),
    /* JADX INFO: Fake field, exist only in values array */
    LOG_ENVIRONMENT_AUTOPUSH(1),
    /* JADX INFO: Fake field, exist only in values array */
    LOG_ENVIRONMENT_STAGING(2),
    LOG_ENVIRONMENT_PROD(3);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22479i;

    p(int i10) {
        this.f22479i = i10;
    }

    @Override // i5.f
    public final int getNumber() {
        return this.f22479i;
    }
}
