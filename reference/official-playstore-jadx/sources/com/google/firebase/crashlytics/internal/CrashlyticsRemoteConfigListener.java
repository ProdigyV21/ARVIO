package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import kotlin.Metadata;
import v5.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/google/firebase/crashlytics/internal/CrashlyticsRemoteConfigListener;", "Lv5/b;", "Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;", "userMetadata", "<init>", "(Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;)V", "Lv5/a;", "rolloutsState", "Lx6/t0;", "onRolloutsStateChanged", "(Lv5/a;)V", "Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CrashlyticsRemoteConfigListener implements b {
    private final UserMetadata userMetadata;

    public CrashlyticsRemoteConfigListener(UserMetadata userMetadata) {
        this.userMetadata = userMetadata;
    }

    public void onRolloutsStateChanged(v5.a rolloutsState) {
        throw null;
    }
}
