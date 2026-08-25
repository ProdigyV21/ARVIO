package d2;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends r implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c2.f f14657i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c2.f fVar) {
        super(4);
        this.f14657i = fVar;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        SQLiteQuery sQLiteQuery = (SQLiteQuery) obj4;
        this.f14657i.j(new h(sQLiteQuery));
        return new SQLiteCursor((SQLiteCursorDriver) obj2, (String) obj3, sQLiteQuery);
    }
}
