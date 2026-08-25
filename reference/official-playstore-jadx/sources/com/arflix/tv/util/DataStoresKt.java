package com.arflix.tv.util;

import android.content.Context;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;
import kotlin.jvm.internal.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.m;
import u7.c;
import z0.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\"%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"%\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\"%\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"%\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0014"}, d2 = {"Landroid/content/Context;", "Lz0/g;", "Lc1/g;", "settingsDataStore$delegate", "Lu7/c;", "getSettingsDataStore", "(Landroid/content/Context;)Lz0/g;", "settingsDataStore", "traktDataStore$delegate", "getTraktDataStore", "traktDataStore", "profilesDataStore$delegate", "getProfilesDataStore", "profilesDataStore", "authDataStore$delegate", "getAuthDataStore", "authDataStore", "telegramDataStore$delegate", "getTelegramDataStore", "telegramDataStore", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DataStoresKt {
    static final /* synthetic */ m<Object>[] $$delegatedProperties;
    private static final c authDataStore$delegate;
    private static final c profilesDataStore$delegate;
    private static final c settingsDataStore$delegate;
    private static final c telegramDataStore$delegate;
    private static final c traktDataStore$delegate;

    static {
        b0 b0Var = new b0(DataStoresKt.class, "settingsDataStore", "getSettingsDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;");
        m0 m0Var = l0.f19747a;
        $$delegatedProperties = new m[]{m0Var.g(b0Var), m0Var.g(new b0(DataStoresKt.class, "traktDataStore", "getTraktDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;")), m0Var.g(new b0(DataStoresKt.class, "profilesDataStore", "getProfilesDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;")), m0Var.g(new b0(DataStoresKt.class, "authDataStore", "getAuthDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;")), m0Var.g(new b0(DataStoresKt.class, "telegramDataStore", "getTelegramDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;"))};
        settingsDataStore$delegate = r0.B("settings_prefs");
        traktDataStore$delegate = r0.B("trakt_prefs");
        profilesDataStore$delegate = r0.B("profiles_prefs");
        authDataStore$delegate = r0.B("auth_prefs");
        telegramDataStore$delegate = r0.B("telegram_prefs");
    }

    public static final g<c1.g> getAuthDataStore(Context context) {
        return (g) authDataStore$delegate.getValue(context, $$delegatedProperties[3]);
    }

    public static final g<c1.g> getProfilesDataStore(Context context) {
        return (g) profilesDataStore$delegate.getValue(context, $$delegatedProperties[2]);
    }

    public static final g<c1.g> getSettingsDataStore(Context context) {
        return (g) settingsDataStore$delegate.getValue(context, $$delegatedProperties[0]);
    }

    public static final g<c1.g> getTelegramDataStore(Context context) {
        return (g) telegramDataStore$delegate.getValue(context, $$delegatedProperties[4]);
    }

    public static final g<c1.g> getTraktDataStore(Context context) {
        return (g) traktDataStore$delegate.getValue(context, $$delegatedProperties[1]);
    }
}
