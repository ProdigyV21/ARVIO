package j$.time.zone;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements PrivilegedAction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ List f18416a;

    public h(List list) {
        this.f18416a = list;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        String property = System.getProperty("java.time.zone.DefaultZoneRulesProvider");
        if (property != null) {
            try {
                i iVar = (i) i.class.cast(Class.forName(property, true, i.class.getClassLoader()).newInstance());
                i.b(iVar);
                ((ArrayList) this.f18416a).add(iVar);
                return null;
            } catch (Exception e5) {
                throw new Error(e5);
            }
        }
        i.b(new i());
        return null;
    }
}
