package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerProfileConfig;", "", "connections", "", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<init>", "(Ljava/util/List;)V", "getConnections", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class HomeServerProfileConfig {
    private final List<HomeServerConnection> connections;

    /* JADX WARN: Multi-variable type inference failed */
    public HomeServerProfileConfig() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeServerProfileConfig copy$default(HomeServerProfileConfig homeServerProfileConfig, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = homeServerProfileConfig.connections;
        }
        return homeServerProfileConfig.copy(list);
    }

    public final List<HomeServerConnection> component1() {
        return this.connections;
    }

    public final HomeServerProfileConfig copy(List<HomeServerConnection> connections) {
        return new HomeServerProfileConfig(connections);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HomeServerProfileConfig) && kotlin.jvm.internal.p.a(this.connections, ((HomeServerProfileConfig) other).connections);
    }

    public final List<HomeServerConnection> getConnections() {
        return this.connections;
    }

    public int hashCode() {
        return this.connections.hashCode();
    }

    public String toString() {
        return "HomeServerProfileConfig(connections=" + this.connections + ")";
    }

    public HomeServerProfileConfig(List<HomeServerConnection> list) {
        this.connections = list;
    }

    public /* synthetic */ HomeServerProfileConfig(List list, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? kotlin.collections.z.f19728i : list);
    }
}
