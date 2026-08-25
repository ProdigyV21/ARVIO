package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import y1.c0;
import y1.d0;
import y1.o;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/room/MultiInstanceInvalidationService;", "Landroid/app/Service;", "<init>", "()V", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@o
public final class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5132i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final LinkedHashMap f5133l = new LinkedHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d0 f5134m = new d0(this);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c0 f5135n = new c0(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f5135n;
    }
}
