package com.arflix.tv.data.local;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import z0.g;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/local/ProfileDataStoreFactory;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "profileId", "", "feature", "Lz0/g;", "Lc1/g;", "get", "(ILjava/lang/String;)Lz0/g;", "Landroid/content/Context;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileDataStoreFactory {
    public static final int $stable = 8;
    private final Context context;

    @Inject
    public ProfileDataStoreFactory(@ApplicationContext Context context) {
        this.context = context;
    }

    public final g<c1.g> get(int profileId, String feature) {
        return ProfileDataStoreFactoryKt.getPluginDataStore(this.context);
    }
}
