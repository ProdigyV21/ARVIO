package kotlin.text;

/* JADX INFO: loaded from: classes5.dex */
public enum n implements d {
    IGNORE_CASE(2),
    /* JADX INFO: Fake field, exist only in values array */
    MULTILINE(8),
    /* JADX INFO: Fake field, exist only in values array */
    LITERAL(16),
    /* JADX INFO: Fake field, exist only in values array */
    UNIX_LINES(1),
    /* JADX INFO: Fake field, exist only in values array */
    COMMENTS(4),
    DOT_MATCHES_ALL(32),
    /* JADX INFO: Fake field, exist only in values array */
    CANON_EQ(128);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f19955i;

    n(int i10) {
        this.f19955i = i10;
    }

    @Override // kotlin.text.d
    public final int getValue() {
        return this.f19955i;
    }
}
