package l4;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f20024a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SharedPreferences f20025b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f20026c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f20027d;

    public /* synthetic */ a(SharedPreferences sharedPreferences, String str, Object obj, int i10) {
        this.f20024a = i10;
        this.f20025b = sharedPreferences;
        this.f20026c = str;
        this.f20027d = obj;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        switch (this.f20024a) {
            case 0:
                return Boolean.valueOf(this.f20025b.getBoolean(this.f20026c, ((Boolean) this.f20027d).booleanValue()));
            case 1:
                return Integer.valueOf(this.f20025b.getInt(this.f20026c, ((Integer) this.f20027d).intValue()));
            case 2:
                return Long.valueOf(this.f20025b.getLong(this.f20026c, ((Long) this.f20027d).longValue()));
            default:
                return this.f20025b.getString(this.f20026c, (String) this.f20027d);
        }
    }
}
