package androidx.credentials.provider.utils;

import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import j$.util.function.Predicate$CC;
import java.util.function.Predicate;
import org.jsoup.nodes.Node;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Predicate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2403b;

    public /* synthetic */ a(Object obj, int i10) {
        this.f2402a = i10;
        this.f2403b = obj;
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        int i10 = this.f2402a;
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ Predicate negate() {
        switch (this.f2402a) {
        }
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        int i10 = this.f2402a;
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.f2402a) {
            case 0:
                return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$6((l) this.f2403b, obj);
            case 1:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$13((l) this.f2403b, obj);
            case 2:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$7((l) this.f2403b, obj);
            case 3:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$10((l) this.f2403b, obj);
            default:
                return ((Class) this.f2403b).isInstance((Node) obj);
        }
    }
}
