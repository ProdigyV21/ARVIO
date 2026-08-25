package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f14357b;

    public /* synthetic */ a(Object obj, int i10) {
        this.f14356a = i10;
        this.f14357b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f14356a) {
            case 0:
                return ((UserMetadata.SerializeableKeysMap) this.f14357b).lambda$scheduleSerializationTaskIfNeeded$0();
            default:
                return ((UserMetadata) this.f14357b).lambda$setUserId$0();
        }
    }
}
