package f6;

/* JADX INFO: loaded from: classes4.dex */
public enum a {
    /* JADX INFO: Fake field, exist only in values array */
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    /* JADX INFO: Fake field, exist only in values array */
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    /* JADX INFO: Fake field, exist only in values array */
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    /* JADX INFO: Fake field, exist only in values array */
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    /* JADX INFO: Fake field, exist only in values array */
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    /* JADX INFO: Fake field, exist only in values array */
    HANZI(new int[]{8, 10, 12}, 13);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f15283i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f15284l;

    a(int[] iArr, int i10) {
        this.f15283i = iArr;
        this.f15284l = i10;
    }

    public final int a(b bVar) {
        int i10 = bVar.f15286a;
        return this.f15283i[i10 <= 9 ? (char) 0 : i10 <= 26 ? (char) 1 : (char) 2];
    }
}
