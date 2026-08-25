package androidx.credentials;

import android.app.PendingIntent;
import android.content.Context;
import android.os.CancellationSignal;
import androidx.credentials.PrepareGetCredentialResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import java.util.concurrent.Executor;
import ka.l;
import kotlin.Metadata;
import q7.n;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 -2\u00020\u0001:\u0001-J \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0097@¢\u0006\u0004\b\u0007\u0010\u000bJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0097@¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000fH\u0096@¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0013H\u0096@¢\u0006\u0004\b\u0015\u0010\u0016JE\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c0\u001bH&¢\u0006\u0004\b\u001e\u0010\u001fJE\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c0\u001bH'¢\u0006\u0004\b\u001e\u0010 J=\u0010!\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u001c0\u001bH'¢\u0006\u0004\b!\u0010\"JE\u0010$\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020#0\u001bH&¢\u0006\u0004\b$\u0010%J?\u0010(\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0014\u0010\u001d\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010&\u0012\u0004\u0012\u00020'0\u001bH&¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H'¢\u0006\u0004\b+\u0010,ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006.À\u0006\u0001"}, d2 = {"Landroidx/credentials/CredentialManager;", "", "Landroid/content/Context;", "context", "Landroidx/credentials/GetCredentialRequest;", "request", "Landroidx/credentials/GetCredentialResponse;", "getCredential", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Ld7/d;)Ljava/lang/Object;", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "pendingGetCredentialHandle", "(Landroid/content/Context;Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;Ld7/d;)Ljava/lang/Object;", "Landroidx/credentials/PrepareGetCredentialResponse;", "prepareGetCredential", "(Landroidx/credentials/GetCredentialRequest;Ld7/d;)Ljava/lang/Object;", "Landroidx/credentials/CreateCredentialRequest;", "Landroidx/credentials/CreateCredentialResponse;", "createCredential", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Ld7/d;)Ljava/lang/Object;", "Landroidx/credentials/ClearCredentialStateRequest;", "Lx6/t0;", "clearCredentialState", "(Landroidx/credentials/ClearCredentialStateRequest;Ld7/d;)Ljava/lang/Object;", "Landroid/os/CancellationSignal;", "cancellationSignal", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/exceptions/GetCredentialException;", "callback", "getCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "(Landroid/content/Context;Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "prepareGetCredentialAsync", "(Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/exceptions/CreateCredentialException;", "createCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "clearCredentialStateAsync", "(Landroidx/credentials/ClearCredentialStateRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/app/PendingIntent;", "createSettingsPendingIntent", "()Landroid/app/PendingIntent;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface CredentialManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: androidx.credentials.CredentialManager$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        static {
            Companion companion = CredentialManager.INSTANCE;
        }

        public static Object a(CredentialManager credentialManager, ClearCredentialStateRequest clearCredentialStateRequest, d7.d dVar) {
            return f(credentialManager, clearCredentialStateRequest, dVar);
        }

        public static Object b(CredentialManager credentialManager, Context context, CreateCredentialRequest createCredentialRequest, d7.d dVar) {
            return h(credentialManager, context, createCredentialRequest, dVar);
        }

        public static Object c(CredentialManager credentialManager, Context context, GetCredentialRequest getCredentialRequest, d7.d dVar) {
            return i(credentialManager, context, getCredentialRequest, dVar);
        }

        public static Object d(CredentialManager credentialManager, Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, d7.d dVar) {
            return j(credentialManager, context, pendingGetCredentialHandle, dVar);
        }

        public static Object e(CredentialManager credentialManager, GetCredentialRequest getCredentialRequest, d7.d dVar) {
            return k(credentialManager, getCredentialRequest, dVar);
        }

        public static /* synthetic */ Object f(CredentialManager credentialManager, ClearCredentialStateRequest clearCredentialStateRequest, d7.d dVar) {
            final l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            CancellationSignal cancellationSignal = new CancellationSignal();
            lVar.c(new CredentialManager$clearCredentialState$2$1(cancellationSignal));
            credentialManager.clearCredentialStateAsync(clearCredentialStateRequest, cancellationSignal, new a(0), new CredentialManagerCallback<Void, ClearCredentialException>() { // from class: androidx.credentials.CredentialManager$clearCredentialState$2$callback$1
                @Override // androidx.credentials.CredentialManagerCallback
                public void onError(ClearCredentialException e5) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(new c0(e5));
                    }
                }

                @Override // androidx.credentials.CredentialManagerCallback
                public void onResult(Void result) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(t0.f22605a);
                    }
                }
            });
            Object objR = lVar.r();
            return objR == e7.a.f15033i ? objR : t0.f22605a;
        }

        public static CredentialManager g(Context context) {
            return CredentialManager.INSTANCE.create(context);
        }

        public static /* synthetic */ Object h(CredentialManager credentialManager, Context context, CreateCredentialRequest createCredentialRequest, d7.d dVar) {
            final l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            CancellationSignal cancellationSignal = new CancellationSignal();
            lVar.c(new CredentialManager$createCredential$2$1(cancellationSignal));
            credentialManager.createCredentialAsync(context, createCredentialRequest, cancellationSignal, new a(0), new CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException>() { // from class: androidx.credentials.CredentialManager$createCredential$2$callback$1
                @Override // androidx.credentials.CredentialManagerCallback
                public void onError(CreateCredentialException e5) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(new c0(e5));
                    }
                }

                @Override // androidx.credentials.CredentialManagerCallback
                public void onResult(CreateCredentialResponse result) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(result);
                    }
                }
            });
            return lVar.r();
        }

        public static /* synthetic */ Object i(CredentialManager credentialManager, Context context, GetCredentialRequest getCredentialRequest, d7.d dVar) {
            final l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            CancellationSignal cancellationSignal = new CancellationSignal();
            lVar.c(new CredentialManager$getCredential$2$1(cancellationSignal));
            credentialManager.getCredentialAsync(context, getCredentialRequest, cancellationSignal, new a(0), new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() { // from class: androidx.credentials.CredentialManager$getCredential$2$callback$1
                @Override // androidx.credentials.CredentialManagerCallback
                public void onError(GetCredentialException e5) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(new c0(e5));
                    }
                }

                @Override // androidx.credentials.CredentialManagerCallback
                public void onResult(GetCredentialResponse result) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(result);
                    }
                }
            });
            return lVar.r();
        }

        public static /* synthetic */ Object j(CredentialManager credentialManager, Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, d7.d dVar) {
            final l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            CancellationSignal cancellationSignal = new CancellationSignal();
            lVar.c(new CredentialManager$getCredential$4$1(cancellationSignal));
            credentialManager.getCredentialAsync(context, pendingGetCredentialHandle, cancellationSignal, new a(0), new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() { // from class: androidx.credentials.CredentialManager$getCredential$4$callback$1
                @Override // androidx.credentials.CredentialManagerCallback
                public void onError(GetCredentialException e5) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(new c0(e5));
                    }
                }

                @Override // androidx.credentials.CredentialManagerCallback
                public void onResult(GetCredentialResponse result) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(result);
                    }
                }
            });
            return lVar.r();
        }

        public static /* synthetic */ Object k(CredentialManager credentialManager, GetCredentialRequest getCredentialRequest, d7.d dVar) {
            final l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            CancellationSignal cancellationSignal = new CancellationSignal();
            lVar.c(new CredentialManager$prepareGetCredential$2$1(cancellationSignal));
            credentialManager.prepareGetCredentialAsync(getCredentialRequest, cancellationSignal, new a(0), new CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException>() { // from class: androidx.credentials.CredentialManager$prepareGetCredential$2$callback$1
                @Override // androidx.credentials.CredentialManagerCallback
                public void onError(GetCredentialException e5) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(new c0(e5));
                    }
                }

                @Override // androidx.credentials.CredentialManagerCallback
                public void onResult(PrepareGetCredentialResponse result) {
                    if (lVar.isActive()) {
                        lVar.resumeWith(result);
                    }
                }
            });
            return lVar.r();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CredentialManager$Companion;", "", "()V", "create", "Landroidx/credentials/CredentialManager;", "context", "Landroid/content/Context;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @n
        public final CredentialManager create(Context context) {
            return new CredentialManagerImpl(context);
        }
    }

    Object clearCredentialState(ClearCredentialStateRequest clearCredentialStateRequest, d7.d<? super t0> dVar);

    void clearCredentialStateAsync(ClearCredentialStateRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> callback);

    Object createCredential(Context context, CreateCredentialRequest createCredentialRequest, d7.d<? super CreateCredentialResponse> dVar);

    void createCredentialAsync(Context context, CreateCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> callback);

    PendingIntent createSettingsPendingIntent();

    Object getCredential(Context context, GetCredentialRequest getCredentialRequest, d7.d<? super GetCredentialResponse> dVar);

    Object getCredential(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, d7.d<? super GetCredentialResponse> dVar);

    void getCredentialAsync(Context context, GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback);

    void getCredentialAsync(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback);

    Object prepareGetCredential(GetCredentialRequest getCredentialRequest, d7.d<? super PrepareGetCredentialResponse> dVar);

    void prepareGetCredentialAsync(GetCredentialRequest request, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> callback);
}
