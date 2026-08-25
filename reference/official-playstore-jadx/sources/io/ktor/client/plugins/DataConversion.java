package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import io.ktor.util.converters.DataConversion;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/DataConversion;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/util/converters/DataConversion$Configuration;", "Lio/ktor/util/converters/DataConversion;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/util/converters/DataConversion;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/util/converters/DataConversion;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DataConversion implements HttpClientPlugin<DataConversion.Configuration, io.ktor.util.converters.DataConversion> {
    public static final DataConversion INSTANCE = new DataConversion();
    private static final AttributeKey<io.ktor.util.converters.DataConversion> key = new AttributeKey<>("DataConversion");

    private DataConversion() {
    }

    @Override // io.ktor.client.plugins.HttpClientPlugin
    public AttributeKey<io.ktor.util.converters.DataConversion> getKey() {
        return key;
    }

    @Override // io.ktor.client.plugins.HttpClientPlugin
    public void install(io.ktor.util.converters.DataConversion plugin, HttpClient scope) {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.ktor.client.plugins.HttpClientPlugin
    public io.ktor.util.converters.DataConversion prepare(l<? super DataConversion.Configuration, t0> block) {
        DataConversion.Configuration configuration = new DataConversion.Configuration();
        block.invoke(configuration);
        return new io.ktor.util.converters.DataConversion(configuration);
    }
}
