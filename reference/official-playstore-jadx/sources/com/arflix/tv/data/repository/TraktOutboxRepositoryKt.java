package com.arflix.tv.data.repository;

import android.content.Context;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\"%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Landroid/content/Context;", "Lz0/g;", "Lc1/g;", "traktOutboxDataStore$delegate", "Lu7/c;", "getTraktOutboxDataStore", "(Landroid/content/Context;)Lz0/g;", "traktOutboxDataStore", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TraktOutboxRepositoryKt {
    static final /* synthetic */ kotlin.reflect.m<Object>[] $$delegatedProperties = {kotlin.jvm.internal.l0.f19747a.g(new kotlin.jvm.internal.b0(TraktOutboxRepositoryKt.class, "traktOutboxDataStore", "getTraktOutboxDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;"))};
    private static final u7.c traktOutboxDataStore$delegate = r0.B("trakt_outbox");

    /* JADX INFO: Access modifiers changed from: private */
    public static final z0.g<c1.g> getTraktOutboxDataStore(Context context) {
        return (z0.g) traktOutboxDataStore$delegate.getValue(context, $$delegatedProperties[0]);
    }
}
