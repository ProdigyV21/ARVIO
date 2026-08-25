package k3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f19477i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f19478l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f19479m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ c[] f19480n;

    static {
        c cVar = new c("NETWORK_UNMETERED", 0);
        f19477i = cVar;
        c cVar2 = new c("DEVICE_IDLE", 1);
        f19478l = cVar2;
        c cVar3 = new c("DEVICE_CHARGING", 2);
        f19479m = cVar3;
        f19480n = new c[]{cVar, cVar2, cVar3};
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f19480n.clone();
    }
}
