package zb;

/* JADX INFO: loaded from: classes5.dex */
public enum c {
    /* JADX INFO: Fake field, exist only in values array */
    BLACK(0, "BLACK"),
    RED(1, "RED"),
    GREEN(2, "GREEN"),
    YELLOW(3, "YELLOW"),
    /* JADX INFO: Fake field, exist only in values array */
    BLUE(4, "BLUE"),
    /* JADX INFO: Fake field, exist only in values array */
    MAGENTA(5, "MAGENTA"),
    CYAN(6, "CYAN"),
    /* JADX INFO: Fake field, exist only in values array */
    WHITE(7, "WHITE"),
    /* JADX INFO: Fake field, exist only in values array */
    DEFAULT(9, "DEFAULT");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23280i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f23281l;

    c(int i10, String str) {
        this.f23280i = i10;
        this.f23281l = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f23281l;
    }
}
