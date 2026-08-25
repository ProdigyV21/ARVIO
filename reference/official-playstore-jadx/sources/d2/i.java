package d2;

import android.database.sqlite.SQLiteStatement;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends h implements c2.g {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SQLiteStatement f14681l;

    public i(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.f14681l = sQLiteStatement;
    }

    @Override // c2.g
    public final long Z() {
        return this.f14681l.executeInsert();
    }

    @Override // c2.g
    public final int r() {
        return this.f14681l.executeUpdateDelete();
    }
}
