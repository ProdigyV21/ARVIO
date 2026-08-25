package com.arflix.tv.ui.screens.settings.telegram;

import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import com.arflix.tv.data.telegram.TelegramAuthState;
import com.arflix.tv.data.telegram.TelegramRepository;
import f7.j;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\bJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000eJ\u0015\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u000eJ\r\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\bJ\r\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u00168\u0006¢\u0006\f\n\u0004\b \u0010\u0019\u001a\u0004\b!\u0010\u001b¨\u0006\""}, d2 = {"Lcom/arflix/tv/ui/screens/settings/telegram/TelegramSettingsViewModel;", "Landroidx/lifecycle/d1;", "Lcom/arflix/tv/data/telegram/TelegramRepository;", "repository", "<init>", "(Lcom/arflix/tv/data/telegram/TelegramRepository;)V", "Lx6/t0;", "refreshCacheSize", "()V", "startAuth", "startQrAuth", "", "phone", "submitPhone", "(Ljava/lang/String;)V", "code", "submitCode", "password", "submitPassword", "disconnect", "clearCache", "Lcom/arflix/tv/data/telegram/TelegramRepository;", "Lna/h1;", "Lcom/arflix/tv/data/telegram/TelegramAuthState;", "authState", "Lna/h1;", "getAuthState", "()Lna/h1;", "Lna/q0;", "", "_cacheSizeBytes", "Lna/q0;", "cacheSizeBytes", "getCacheSizeBytes", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramSettingsViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<Long> _cacheSizeBytes;
    private final h1<TelegramAuthState> authState;
    private final h1<Long> cacheSizeBytes;
    private final TelegramRepository repository;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel$clearCache$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel$clearCache$1", f = "TelegramSettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramSettingsViewModel.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            TelegramSettingsViewModel.this.repository.clearCache();
            TelegramSettingsViewModel.this._cacheSizeBytes.setValue(new Long(TelegramSettingsViewModel.this.repository.getCacheSize()));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel$refreshCacheSize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel$refreshCacheSize$1", f = "TelegramSettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C15991 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C15991(d7.d<? super C15991> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramSettingsViewModel.this.new C15991(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            TelegramSettingsViewModel.this._cacheSizeBytes.setValue(new Long(TelegramSettingsViewModel.this.repository.getCacheSize()));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C15991) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public TelegramSettingsViewModel(TelegramRepository telegramRepository) {
        this.repository = telegramRepository;
        this.authState = telegramRepository.getAuthState();
        j1 j1VarB = y0.b(0L);
        this._cacheSizeBytes = j1VarB;
        this.cacheSizeBytes = y0.e(j1VarB);
        refreshCacheSize();
    }

    private final void refreshCacheSize() {
        m0.p(z0.h(this), x0.f19655d, 0, new C15991(null), 2);
    }

    public final void clearCache() {
        m0.p(z0.h(this), x0.f19655d, 0, new AnonymousClass1(null), 2);
    }

    public final void disconnect() {
        this.repository.disconnect();
        this._cacheSizeBytes.setValue(0L);
    }

    public final h1<TelegramAuthState> getAuthState() {
        return this.authState;
    }

    public final h1<Long> getCacheSizeBytes() {
        return this.cacheSizeBytes;
    }

    public final void startAuth() {
        this.repository.startAuth();
    }

    public final void startQrAuth() {
        this.repository.requestQrCode();
    }

    public final void submitCode(String code) {
        this.repository.submitCode(code);
    }

    public final void submitPassword(String password) {
        this.repository.submitPassword(password);
    }

    public final void submitPhone(String phone) {
        this.repository.submitPhone(phone);
    }
}
