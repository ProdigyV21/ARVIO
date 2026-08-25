package x5;

import com.google.firebase.crashlytics.internal.common.CrashlyticsAppQualitySessionsSubscriber;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ua.d f22559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CrashlyticsAppQualitySessionsSubscriber f22560b = null;

    public a(ua.d dVar) {
        this.f22559a = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f22559a.equals(aVar.f22559a) && p.a(this.f22560b, aVar.f22560b);
    }

    public final int hashCode() {
        int iHashCode = this.f22559a.hashCode() * 31;
        CrashlyticsAppQualitySessionsSubscriber crashlyticsAppQualitySessionsSubscriber = this.f22560b;
        return iHashCode + (crashlyticsAppQualitySessionsSubscriber == null ? 0 : crashlyticsAppQualitySessionsSubscriber.hashCode());
    }

    public final String toString() {
        return "Dependency(mutex=" + this.f22559a + ", subscriber=" + this.f22560b + ')';
    }
}
