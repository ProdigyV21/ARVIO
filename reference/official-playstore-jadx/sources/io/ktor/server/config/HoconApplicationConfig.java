package io.ktor.server.config;

import a0.c;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import l6.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0003\u0010\u000fJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017¨\u0006\u0019"}, d2 = {"Lio/ktor/server/config/HoconApplicationConfig;", "Lio/ktor/server/config/ApplicationConfig;", "Ll6/a;", "config", "<init>", "(Ll6/a;)V", "", "path", "Lio/ktor/server/config/ApplicationConfigValue;", "property", "(Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfigValue;", "propertyOrNull", "", "configList", "(Ljava/lang/String;)Ljava/util/List;", "(Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfig;", "", UserMetadata.KEYDATA_FILENAME, "()Ljava/util/Set;", "", "", "toMap", "()Ljava/util/Map;", "Ll6/a;", "HoconApplicationConfigValue", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class HoconApplicationConfig implements ApplicationConfig {
    private final a config;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"Lio/ktor/server/config/HoconApplicationConfig$HoconApplicationConfigValue;", "Lio/ktor/server/config/ApplicationConfigValue;", "Ll6/a;", "config", "", "path", "<init>", "(Ll6/a;Ljava/lang/String;)V", "getString", "()Ljava/lang/String;", "", "getList", "()Ljava/util/List;", "Ll6/a;", "getConfig", "()Ll6/a;", "Ljava/lang/String;", "getPath", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class HoconApplicationConfigValue implements ApplicationConfigValue {
        private final a config;
        private final String path;

        public HoconApplicationConfigValue(a aVar, String str) {
            this.config = aVar;
            this.path = str;
        }

        public final a getConfig() {
            return this.config;
        }

        @Override // io.ktor.server.config.ApplicationConfigValue
        public List<String> getList() {
            return this.config.e(this.path);
        }

        public final String getPath() {
            return this.path;
        }

        @Override // io.ktor.server.config.ApplicationConfigValue
        public String getString() {
            return this.config.g(this.path);
        }
    }

    public HoconApplicationConfig(a aVar) {
        this.config = aVar;
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfig config(String path) {
        return new HoconApplicationConfig(this.config.c(path));
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public List<ApplicationConfig> configList(String path) {
        ArrayList arrayListM = this.config.m(path);
        ArrayList arrayList = new ArrayList(s.U(arrayListM, 10));
        Iterator it = arrayListM.iterator();
        while (it.hasNext()) {
            arrayList.add(new HoconApplicationConfig((a) it.next()));
        }
        return arrayList;
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public Set<String> keys() {
        Set setEntrySet = this.config.entrySet();
        ArrayList arrayList = new ArrayList(s.U(setEntrySet, 10));
        Iterator it = setEntrySet.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Map.Entry) it.next()).getKey());
        }
        return x.g1(arrayList);
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfigValue property(String path) throws ApplicationConfigurationException {
        if (this.config.q(path)) {
            return new HoconApplicationConfigValue(this.config, path);
        }
        throw new ApplicationConfigurationException(c.l("Property ", path, " not found."));
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public ApplicationConfigValue propertyOrNull(String path) {
        if (this.config.q(path)) {
            return new HoconApplicationConfigValue(this.config, path);
        }
        return null;
    }

    @Override // io.ktor.server.config.ApplicationConfig
    public Map<String, Object> toMap() {
        return this.config.root().a();
    }
}
