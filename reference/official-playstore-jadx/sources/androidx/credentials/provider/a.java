package androidx.credentials.provider;

import androidx.credentials.provider.PendingIntentHandler;
import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import j$.util.function.Function$CC;
import java.util.function.Function;
import org.jsoup.nodes.Node;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2401b;

    public /* synthetic */ a(Object obj, int i10) {
        this.f2400a = i10;
        this.f2401b = obj;
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen */
    public /* synthetic */ Function mo7027andThen(Function function) {
        int i10 = this.f2400a;
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.f2400a) {
            case 0:
                return PendingIntentHandler.Companion.retrieveProviderGetCredentialRequest$lambda$1((l) this.f2401b, obj);
            case 1:
                return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$5((l) this.f2401b, obj);
            case 2:
                return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$7((l) this.f2401b, obj);
            case 3:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$14((l) this.f2401b, obj);
            case 4:
                return BeginGetCredentialUtil.Companion.convertToFrameworkRequest$lambda$5((l) this.f2401b, obj);
            case 5:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$6((l) this.f2401b, obj);
            case 6:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$8((l) this.f2401b, obj);
            case 7:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$9((l) this.f2401b, obj);
            case 8:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$11((l) this.f2401b, obj);
            case 9:
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$12((l) this.f2401b, obj);
            case 10:
                return ((l) this.f2401b).invoke(obj);
            default:
                return ((Class) this.f2401b).cast((Node) obj);
        }
    }

    public /* synthetic */ Function compose(Function function) {
        int i10 = this.f2400a;
        return Function$CC.$default$compose(this, function);
    }
}
