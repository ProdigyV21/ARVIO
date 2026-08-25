package ab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f778b = z.f19728i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f779c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet f780d = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f781e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f782f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f783g = new ArrayList();

    public a(String str) {
        this.f777a = str;
    }

    public final void a(String str, g gVar, boolean z) {
        if (!this.f780d.add(str)) {
            StringBuilder sbT = a0.c.t("Element with name '", str, "' is already registered in ");
            sbT.append(this.f777a);
            throw new IllegalArgumentException(sbT.toString().toString());
        }
        this.f779c.add(str);
        this.f781e.add(gVar);
        this.f782f.add(z.f19728i);
        this.f783g.add(Boolean.valueOf(z));
    }
}
