package i4;

/* JADX INFO: loaded from: classes4.dex */
public enum z implements a {
    /* JADX INFO: Fake field, exist only in values array */
    RS256(-257),
    /* JADX INFO: Fake field, exist only in values array */
    RS384(-258),
    /* JADX INFO: Fake field, exist only in values array */
    RS512(-259),
    /* JADX INFO: Fake field, exist only in values array */
    LEGACY_RS1(-262),
    /* JADX INFO: Fake field, exist only in values array */
    PS256(-37),
    /* JADX INFO: Fake field, exist only in values array */
    PS384(-38),
    /* JADX INFO: Fake field, exist only in values array */
    PS512(-39),
    RS1(-65535);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16214i;

    z(int i10) {
        this.f16214i = i10;
    }

    @Override // i4.a
    public final int a() {
        return this.f16214i;
    }
}
