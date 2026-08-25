package io.ktor.server.config;

import a0.c;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.e0;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.text.o;
import kotlin.text.u;
import qb.d;
import t7.a;
import x7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0001)B%\b\u0012\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B#\b\u0016\u0012\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t0\b¢\u0006\u0004\b\u0006\u0010\u000bB5\b\u0016\u0012*\u0010\n\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t0\f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\u0006\u0010\rB\t\b\u0016¢\u0006\u0004\b\u0006\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013¢\u0006\u0004\b\u0011\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001a\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010!0 H\u0016¢\u0006\u0004\b\"\u0010#R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00028\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b%\u0010#R\u001a\u0010\u0005\u001a\u00020\u00038\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lio/ktor/server/config/MapApplicationConfig;", "Lio/ktor/server/config/ApplicationConfig;", "", "", "map", "path", "<init>", "(Ljava/util/Map;Ljava/lang/String;)V", "", "Lx6/x;", "values", "(Ljava/util/List;)V", "", "([Lx6/x;)V", "()V", "value", "Lx6/t0;", "put", "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;Ljava/lang/Iterable;)V", "Lio/ktor/server/config/ApplicationConfigValue;", "property", "(Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfigValue;", "configList", "(Ljava/lang/String;)Ljava/util/List;", "propertyOrNull", "config", "(Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfig;", "", UserMetadata.KEYDATA_FILENAME, "()Ljava/util/Set;", "", "", "toMap", "()Ljava/util/Map;", "Ljava/util/Map;", "getMap", "Ljava/lang/String;", "getPath", "()Ljava/lang/String;", "MapApplicationConfigValue", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class MapApplicationConfig implements ApplicationConfig {
    private final Map<String, String> map;
    private final String path;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lio/ktor/server/config/MapApplicationConfig$MapApplicationConfigValue;", "Lio/ktor/server/config/ApplicationConfigValue;", "map", "", "", "path", "(Ljava/util/Map;Ljava/lang/String;)V", "getMap", "()Ljava/util/Map;", "getPath", "()Ljava/lang/String;", "getList", "", "getString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MapApplicationConfigValue implements ApplicationConfigValue {
        private final Map<String, String> map;
        private final String path;

        public MapApplicationConfigValue(Map<String, String> map, String str) {
            this.map = map;
            this.path = str;
        }

        @Override // io.ktor.server.config.ApplicationConfigValue
        public List<String> getList() throws ApplicationConfigurationException {
            String str = this.map.get(MapApplicationConfigKt.combine(this.path, ContentDisposition.Parameters.Size));
            if (str == null) {
                throw new ApplicationConfigurationException(c.p(new StringBuilder("Property "), this.path, ".size not found."));
            }
            i iVarX = d.X(0, Integer.parseInt(str));
            ArrayList arrayList = new ArrayList(s.U(iVarX, 10));
            Iterator it = iVarX.iterator();
            while (it.hasNext()) {
                arrayList.add(this.map.get(MapApplicationConfigKt.combine(this.path, String.valueOf(((e0) it).nextInt()))));
            }
            return arrayList;
        }

        public final Map<String, String> getMap() {
            return this.map;
        }

        public final String getPath() {
            return this.path;
        }

        @Override // io.ktor.server.config.ApplicationConfigValue
        public String getString() {
            return this.map.get(this.path);
        }
    }

    private MapApplicationConfig(Map<String, String> map, String str) {
        this.map = map;
        this.path = str;
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfig config(String path) {
        return new MapApplicationConfig(this.map, MapApplicationConfigKt.combine(this.path, path));
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public List<ApplicationConfig> configList(String path) throws ApplicationConfigurationException {
        String strCombine = MapApplicationConfigKt.combine(this.path, path);
        String str = this.map.get(MapApplicationConfigKt.combine(strCombine, ContentDisposition.Parameters.Size));
        if (str == null) {
            throw new ApplicationConfigurationException(c.l("Property ", strCombine, ".size not found."));
        }
        i iVarX = d.X(0, Integer.parseInt(str));
        ArrayList arrayList = new ArrayList(s.U(iVarX, 10));
        Iterator it = iVarX.iterator();
        while (it.hasNext()) {
            arrayList.add(new MapApplicationConfig(this.map, MapApplicationConfigKt.combine(strCombine, String.valueOf(((e0) it).nextInt()))));
        }
        return arrayList;
    }

    public final Map<String, String> getMap() {
        return this.map;
    }

    public final String getPath() {
        return this.path;
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public Set<String> keys() {
        Set<String> set;
        String strD0;
        Object next;
        boolean z = this.path.length() == 0;
        Set<String> setKeySet = this.map.keySet();
        if (z) {
            set = setKeySet;
        } else {
            ArrayList arrayList = new ArrayList();
            for (Object obj : setKeySet) {
                if (u.P((String) obj, this.path + '.', false)) {
                    arrayList.add(obj);
                }
            }
            set = arrayList;
        }
        Iterable<String> iterable = set;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : iterable) {
            if (o.T((String) obj2, ".size", false)) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList(s.U(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(o.H0((String) it.next(), ".size"));
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList4 = new ArrayList();
        for (String str : iterable) {
            Iterator it2 = arrayList3.iterator();
            while (true) {
                strD0 = null;
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                if (u.P(str, (String) next, false)) {
                    break;
                }
            }
            String str2 = (String) next;
            if (str2 != null && !linkedHashSet.contains(str2)) {
                linkedHashSet.add(str2);
                str = str2;
            } else if (str2 != null) {
                str = null;
            }
            if (z) {
                strD0 = str;
            } else if (str != null) {
                strD0 = o.D0(str, this.path + '.', str);
            }
            if (strD0 != null) {
                arrayList4.add(strD0);
            }
        }
        return x.g1(arrayList4);
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfigValue property(String path) throws ApplicationConfigurationException {
        ApplicationConfigValue applicationConfigValuePropertyOrNull = propertyOrNull(path);
        if (applicationConfigValuePropertyOrNull != null) {
            return applicationConfigValuePropertyOrNull;
        }
        throw new ApplicationConfigurationException("Property " + MapApplicationConfigKt.combine(this.path, path) + " not found.");
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfigValue propertyOrNull(String path) {
        String strCombine = MapApplicationConfigKt.combine(this.path, path);
        if (this.map.containsKey(strCombine) || this.map.containsKey(MapApplicationConfigKt.combine(strCombine, ContentDisposition.Parameters.Size))) {
            return new MapApplicationConfigValue(this.map, strCombine);
        }
        return null;
    }

    public final void put(String path, String value) {
        this.map.put(MapApplicationConfigKt.combine(this.path, path), value);
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public Map<String, Object> toMap() throws ApplicationConfigurationException {
        x6.x xVar;
        Set<String> setKeySet = this.map.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (u.P((String) obj, this.path, false)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) x.m0(o.x0(o.V(this.path.length() == 0 ? 0 : this.path.length() + 1, (String) it.next()), new char[]{'.'}, 6)));
        }
        List<String> listG0 = x.g0(arrayList2);
        int iQ0 = i0.q0(s.U(listG0, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (String str : listG0) {
            String strCombine = MapApplicationConfigKt.combine(this.path, str);
            if (this.map.containsKey(strCombine)) {
                xVar = new x6.x(str, this.map.get(strCombine));
            } else if (!this.map.containsKey(MapApplicationConfigKt.combine(strCombine, ContentDisposition.Parameters.Size))) {
                xVar = new x6.x(str, config(str).toMap());
            } else if (this.map.containsKey(MapApplicationConfigKt.combine(strCombine, "0"))) {
                xVar = new x6.x(str, property(strCombine).getList());
            } else {
                List<ApplicationConfig> listConfigList = configList(strCombine);
                ArrayList arrayList3 = new ArrayList(s.U(listConfigList, 10));
                Iterator<T> it2 = listConfigList.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((ApplicationConfig) it2.next()).toMap());
                }
                xVar = new x6.x(str, arrayList3);
            }
            linkedHashMap.put(xVar.f22608i, xVar.f22609l);
        }
        return linkedHashMap;
    }

    public final void put(String path, Iterable<String> values) {
        int i10 = 0;
        int i11 = 0;
        for (String str : values) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                a.Q();
                throw null;
            }
            put(MapApplicationConfigKt.combine(path, String.valueOf(i11)), str);
            i10++;
            i11 = i12;
        }
        put(MapApplicationConfigKt.combine(path, ContentDisposition.Parameters.Size), String.valueOf(i10));
    }

    public MapApplicationConfig(List<x6.x> list) {
        this(new LinkedHashMap(h0.A0(list)), "");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            MapApplicationConfigKt.findListElements((String) ((x6.x) it.next()).f22608i, linkedHashMap);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            this.map.put(androidx.compose.material3.d.m((String) entry.getKey(), ".size"), String.valueOf(((Number) entry.getValue()).intValue()));
        }
    }

    public MapApplicationConfig(x6.x... xVarArr) {
        this(h0.u0((x6.x[]) Arrays.copyOf(xVarArr, xVarArr.length)), "");
    }

    public MapApplicationConfig() {
        this(new LinkedHashMap(), "");
    }
}
