package androidx.activity.result;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class j extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k f1002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f1003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g.a f1004c;

    public j(k kVar, String str, g.a aVar) {
        this.f1002a = kVar;
        this.f1003b = str;
        this.f1004c = aVar;
    }

    public final void a(Object obj) {
        k kVar = this.f1002a;
        ArrayList arrayList = kVar.f1008d;
        LinkedHashMap linkedHashMap = kVar.f1006b;
        String str = this.f1003b;
        Object obj2 = linkedHashMap.get(str);
        g.a aVar = this.f1004c;
        if (obj2 == null) {
            throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + aVar + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
        }
        int iIntValue = ((Number) obj2).intValue();
        arrayList.add(str);
        try {
            kVar.b(iIntValue, aVar, obj);
        } catch (Exception e5) {
            arrayList.remove(str);
            throw e5;
        }
    }

    public final void b() {
        this.f1002a.f(this.f1003b);
    }
}
