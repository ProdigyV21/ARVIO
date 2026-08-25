package com.arflix.tv.data.local;

import android.content.Context;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.reflect.m;
import u7.c;
import z0.g;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\"%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Landroid/content/Context;", "Lz0/g;", "Lc1/g;", "pluginDataStore$delegate", "Lu7/c;", "getPluginDataStore", "(Landroid/content/Context;)Lz0/g;", "pluginDataStore", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ProfileDataStoreFactoryKt {
    static final /* synthetic */ m<Object>[] $$delegatedProperties = {l0.f19747a.g(new b0(ProfileDataStoreFactoryKt.class, "pluginDataStore", "getPluginDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;"))};
    private static final c pluginDataStore$delegate = r0.B("plugin_settings");

    /* JADX INFO: Access modifiers changed from: private */
    public static final g<c1.g> getPluginDataStore(Context context) {
        return (g) pluginDataStore$delegate.getValue(context, $$delegatedProperties[0]);
    }
}
