package com.arflix.tv.ui.screens.login;

import android.content.Context;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.container.MdtaMetadataEntry;
import com.arflix.tv.R;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.util.AuthEmailValidator;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.text.o;
import na.h1;
import na.j1;
import na.k;
import na.q0;
import na.y0;
import r7.p;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0011J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u000f¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\f¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010 R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010!R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020'0*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/arflix/tv/ui/screens/login/LoginViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/StreamRepository;Lcom/arflix/tv/data/repository/CloudSyncRepository;)V", "", "email", "password", "Lx6/t0;", "signIn", "(Ljava/lang/String;Ljava/lang/String;)V", "signUp", "Landroidx/credentials/GetCredentialRequest;", "getGoogleSignInRequest", "()Landroidx/credentials/GetCredentialRequest;", "Landroidx/credentials/GetCredentialResponse;", "result", "handleGoogleSignInResult", "(Landroidx/credentials/GetCredentialResponse;)V", "onLoginNavigationHandled", "()V", "error", "handleGoogleSignInError", "(Ljava/lang/String;)V", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lcom/arflix/tv/data/repository/StreamRepository;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "", "lastSignUpAttemptMs", "J", "Lna/q0;", "Lcom/arflix/tv/ui/screens/login/LoginUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LoginViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<LoginUiState> _uiState;
    private final AuthRepository authRepository;
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;
    private long lastSignUpAttemptMs;
    private final StreamRepository streamRepository;
    private final h1<LoginUiState> uiState;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.login.LoginViewModel$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.login.LoginViewModel$1", f = "LoginViewModel.kt", l = {46}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LoginViewModel.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                h1<AuthState> authState = LoginViewModel.this.authRepository.getAuthState();
                final LoginViewModel loginViewModel = LoginViewModel.this;
                k kVar = new k() { // from class: com.arflix.tv.ui.screens.login.LoginViewModel.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((AuthState) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(AuthState authState2, d7.d<? super t0> dVar) {
                        q0 q0Var = loginViewModel._uiState;
                        while (true) {
                            Object value = q0Var.getValue();
                            AuthState authState3 = authState2;
                            if (q0Var.e(value, LoginUiState.copy$default((LoginUiState) value, false, null, authState3, null, false, 27, null))) {
                                return t0.f22605a;
                            }
                            authState2 = authState3;
                        }
                    }
                };
                this.label = 1;
                Object objCollect = authState.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.login.LoginViewModel$handleGoogleSignInResult$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.login.LoginViewModel$handleGoogleSignInResult$1", f = "LoginViewModel.kt", l = {142, 145, 146}, m = "invokeSuspend", v = 2)
    public static final class C14021 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ GetCredentialResponse $result;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14021(GetCredentialResponse getCredentialResponse, d7.d<? super C14021> dVar) {
            super(2, dVar);
            this.$result = getCredentialResponse;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14021 c14021 = LoginViewModel.this.new C14021(this.$result, dVar);
            c14021.L$0 = obj;
            return c14021;
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x00a9, code lost:
        
            if (r2.syncAddonsFromCloud(r19) != r8) goto L52;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00c8  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 225
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.login.LoginViewModel.C14021.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14021) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.login.LoginViewModel$signIn$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.login.LoginViewModel$signIn$3", f = "LoginViewModel.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32, 75, 76}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $normalizedEmail;
        final /* synthetic */ String $password;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(String str, String str2, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$normalizedEmail = str;
            this.$password = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = LoginViewModel.this.new AnonymousClass3(this.$normalizedEmail, this.$password, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x00ab, code lost:
        
            if (r2.syncAddonsFromCloud(r19) != r8) goto L52;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c4  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ca  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 227
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.login.LoginViewModel.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.login.LoginViewModel$signUp$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.login.LoginViewModel$signUp$5", f = "LoginViewModel.kt", l = {117}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass5 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $normalizedEmail;
        final /* synthetic */ String $password;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(String str, String str2, d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
            this.$normalizedEmail = str;
            this.$password = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LoginViewModel.this.new AnonymousClass5(this.$normalizedEmail, this.$password, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object objM6110signUp0E7RQCE;
            Object value2;
            LoginUiState loginUiState;
            Throwable thA;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                q0 q0Var = LoginViewModel.this._uiState;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, LoginUiState.copy$default((LoginUiState) value, true, null, null, null, false, 28, null)));
                AuthRepository authRepository = LoginViewModel.this.authRepository;
                String str = this.$normalizedEmail;
                String str2 = this.$password;
                this.label = 1;
                objM6110signUp0E7RQCE = authRepository.m6110signUp0E7RQCE(str, str2, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6110signUp0E7RQCE == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6110signUp0E7RQCE = ((d0) obj).f22580i;
            }
            Object obj2 = objM6110signUp0E7RQCE;
            q0 q0Var2 = LoginViewModel.this._uiState;
            do {
                value2 = q0Var2.getValue();
                loginUiState = (LoginUiState) value2;
                thA = d0.a(obj2);
            } while (!q0Var2.e(value2, LoginUiState.copy$default(loginUiState, false, thA != null ? thA.getMessage() : null, null, null, false, 28, null)));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public LoginViewModel(@ApplicationContext Context context, AuthRepository authRepository, StreamRepository streamRepository, CloudSyncRepository cloudSyncRepository) {
        this.context = context;
        this.authRepository = authRepository;
        this.streamRepository = streamRepository;
        this.cloudSyncRepository = cloudSyncRepository;
        j1 j1VarB = y0.b(new LoginUiState(false, null, null, null, false, 31, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        m0.p(z0.h(this), null, 0, new AnonymousClass1(null), 3);
    }

    public final GetCredentialRequest getGoogleSignInRequest() {
        return this.authRepository.getGoogleSignInRequest();
    }

    public final h1<LoginUiState> getUiState() {
        return this.uiState;
    }

    public final void handleGoogleSignInError(String error) {
        q0<LoginUiState> q0Var = this._uiState;
        while (true) {
            Object value = q0Var.getValue();
            String str = error;
            if (q0Var.e(value, LoginUiState.copy$default((LoginUiState) value, false, str, null, null, false, 28, null))) {
                return;
            } else {
                error = str;
            }
        }
    }

    public final void handleGoogleSignInResult(GetCredentialResponse result) {
        m0.p(z0.h(this), null, 0, new C14021(result, null), 3);
    }

    public final void onLoginNavigationHandled() {
        Object value;
        q0<LoginUiState> q0Var = this._uiState;
        do {
            value = q0Var.getValue();
        } while (!q0Var.e(value, LoginUiState.copy$default((LoginUiState) value, false, null, null, null, false, 15, null)));
    }

    public final void signIn(String email, String password) {
        Object value;
        Object value2;
        AuthEmailValidator authEmailValidator = AuthEmailValidator.INSTANCE;
        String strNormalize = authEmailValidator.normalize(email);
        Integer numValidate = authEmailValidator.validate(strNormalize, false);
        if (numValidate != null) {
            String string = this.context.getString(numValidate.intValue());
            q0<LoginUiState> q0Var = this._uiState;
            do {
                value2 = q0Var.getValue();
            } while (!q0Var.e(value2, LoginUiState.copy$default((LoginUiState) value2, false, string, null, null, false, 29, null)));
            return;
        }
        if (!o.h0(password)) {
            m0.p(z0.h(this), null, 0, new AnonymousClass3(strNormalize, password, null), 3);
            return;
        }
        q0<LoginUiState> q0Var2 = this._uiState;
        do {
            value = q0Var2.getValue();
        } while (!q0Var2.e(value, LoginUiState.copy$default((LoginUiState) value, false, this.context.getString(R.string.login_error_enter_password), null, null, false, 29, null)));
    }

    public final void signUp(String email, String password) {
        Object value;
        Object value2;
        Object value3;
        AuthEmailValidator authEmailValidator = AuthEmailValidator.INSTANCE;
        String strNormalize = authEmailValidator.normalize(email);
        Integer numValidate$default = AuthEmailValidator.validate$default(authEmailValidator, strNormalize, false, 2, null);
        if (numValidate$default == null) {
            if (o.h0(password)) {
                q0<LoginUiState> q0Var = this._uiState;
                do {
                    value3 = q0Var.getValue();
                } while (!q0Var.e(value3, LoginUiState.copy$default((LoginUiState) value3, false, this.context.getString(R.string.login_error_enter_password), null, null, false, 29, null)));
                return;
            }
            if (password.length() < 6) {
                q0<LoginUiState> q0Var2 = this._uiState;
                do {
                    value2 = q0Var2.getValue();
                } while (!q0Var2.e(value2, LoginUiState.copy$default((LoginUiState) value2, false, this.context.getString(R.string.login_error_password_short), null, null, false, 29, null)));
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j10 = jCurrentTimeMillis - this.lastSignUpAttemptMs;
            if (60000 - j10 <= 0) {
                this.lastSignUpAttemptMs = jCurrentTimeMillis;
                m0.p(z0.h(this), null, 0, new AnonymousClass5(strNormalize, password, null), 3);
                return;
            }
            long j11 = (60999 - j10) / 1000;
            long j12 = j11 < 1 ? 1L : j11;
            q0<LoginUiState> q0Var3 = this._uiState;
            do {
                value = q0Var3.getValue();
            } while (!q0Var3.e(value, LoginUiState.copy$default((LoginUiState) value, false, this.context.getString(R.string.login_error_wait_seconds, Long.valueOf(j12)), null, null, false, 29, null)));
            return;
        }
        String string = this.context.getString(numValidate$default.intValue());
        q0<LoginUiState> q0Var4 = this._uiState;
        while (true) {
            Object value4 = q0Var4.getValue();
            String str = string;
            if (q0Var4.e(value4, LoginUiState.copy$default((LoginUiState) value4, false, string, null, null, false, 29, null))) {
                return;
            } else {
                string = str;
            }
        }
    }
}
