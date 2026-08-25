package h9;

/* JADX INFO: loaded from: classes5.dex */
public enum a {
    NO_ARGUMENTS(3),
    /* JADX INFO: Fake field, exist only in values array */
    UNLESS_EMPTY(2),
    /* JADX INFO: Fake field, exist only in values array */
    ALWAYS_PARENTHESIZED(true, true);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f15893i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f15894l;

    /* synthetic */ a(int i10) {
        this((i10 & 1) == 0, false);
    }

    a(boolean z, boolean z5) {
        this.f15893i = z;
        this.f15894l = z5;
    }
}
