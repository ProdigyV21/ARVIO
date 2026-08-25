package io.github.jan.supabase.gotrue;

import io.github.jan.supabase.SupabaseSerializer;
import io.sentry.util.l;
import ka.f0;
import ka.x0;
import kotlin.Metadata;
import kotlin.reflect.b0;
import kotlin.time.c;
import kotlin.time.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R(\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010'\u001a\u00020&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0004\u0018\u0001048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010;\u001a\u0004\u0018\u0001048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u00106\u001a\u0004\b<\u00108\"\u0004\b=\u0010:R$\u0010?\u001a\u0004\u0018\u00010>8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006E"}, d2 = {"Lio/github/jan/supabase/gotrue/AuthConfigDefaults;", "", "<init>", "()V", "Lkotlin/time/c;", "retryDelay", "J", "getRetryDelay-UwyO8pc", "()J", "setRetryDelay-LRDsOJo", "(J)V", "", "alwaysAutoRefresh", "Z", "getAlwaysAutoRefresh", "()Z", "setAlwaysAutoRefresh", "(Z)V", "autoLoadFromStorage", "getAutoLoadFromStorage", "setAutoLoadFromStorage", "autoSaveToStorage", "getAutoSaveToStorage", "setAutoSaveToStorage", "Lio/github/jan/supabase/gotrue/SessionManager;", "sessionManager", "Lio/github/jan/supabase/gotrue/SessionManager;", "getSessionManager", "()Lio/github/jan/supabase/gotrue/SessionManager;", "setSessionManager", "(Lio/github/jan/supabase/gotrue/SessionManager;)V", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "codeVerifierCache", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "getCodeVerifierCache", "()Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "setCodeVerifierCache", "(Lio/github/jan/supabase/gotrue/CodeVerifierCache;)V", "Lka/f0;", "coroutineDispatcher", "Lka/f0;", "getCoroutineDispatcher", "()Lka/f0;", "setCoroutineDispatcher", "(Lka/f0;)V", "Lio/github/jan/supabase/gotrue/FlowType;", "flowType", "Lio/github/jan/supabase/gotrue/FlowType;", "getFlowType", "()Lio/github/jan/supabase/gotrue/FlowType;", "setFlowType", "(Lio/github/jan/supabase/gotrue/FlowType;)V", "", "customUrl", "Ljava/lang/String;", "getCustomUrl", "()Ljava/lang/String;", "setCustomUrl", "(Ljava/lang/String;)V", "jwtToken", "getJwtToken", "setJwtToken", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "setSerializer", "(Lio/github/jan/supabase/SupabaseSerializer;)V", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class AuthConfigDefaults {
    private boolean alwaysAutoRefresh;
    private boolean autoLoadFromStorage;
    private boolean autoSaveToStorage;
    private CodeVerifierCache codeVerifierCache;
    private f0 coroutineDispatcher;
    private String customUrl;
    private FlowType flowType;
    private String jwtToken;
    private long retryDelay;
    private SupabaseSerializer serializer;
    private SessionManager sessionManager;

    public AuthConfigDefaults() {
        l lVar = c.f19956l;
        this.retryDelay = b0.H(10, e.SECONDS);
        this.alwaysAutoRefresh = true;
        this.autoLoadFromStorage = true;
        this.autoSaveToStorage = true;
        this.coroutineDispatcher = x0.f19653b;
        this.flowType = FlowType.IMPLICIT;
    }

    public final boolean getAlwaysAutoRefresh() {
        return this.alwaysAutoRefresh;
    }

    public final boolean getAutoLoadFromStorage() {
        return this.autoLoadFromStorage;
    }

    public final boolean getAutoSaveToStorage() {
        return this.autoSaveToStorage;
    }

    public final CodeVerifierCache getCodeVerifierCache() {
        return this.codeVerifierCache;
    }

    public final f0 getCoroutineDispatcher() {
        return this.coroutineDispatcher;
    }

    public final String getCustomUrl() {
        return this.customUrl;
    }

    public final FlowType getFlowType() {
        return this.flowType;
    }

    public final String getJwtToken() {
        return this.jwtToken;
    }

    /* JADX INFO: renamed from: getRetryDelay-UwyO8pc, reason: not valid java name and from getter */
    public final long getRetryDelay() {
        return this.retryDelay;
    }

    public final SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    public final SessionManager getSessionManager() {
        return this.sessionManager;
    }

    public final void setAlwaysAutoRefresh(boolean z) {
        this.alwaysAutoRefresh = z;
    }

    public final void setAutoLoadFromStorage(boolean z) {
        this.autoLoadFromStorage = z;
    }

    public final void setAutoSaveToStorage(boolean z) {
        this.autoSaveToStorage = z;
    }

    public final void setCodeVerifierCache(CodeVerifierCache codeVerifierCache) {
        this.codeVerifierCache = codeVerifierCache;
    }

    public final void setCoroutineDispatcher(f0 f0Var) {
        this.coroutineDispatcher = f0Var;
    }

    public final void setCustomUrl(String str) {
        this.customUrl = str;
    }

    public final void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public final void setJwtToken(String str) {
        this.jwtToken = str;
    }

    /* JADX INFO: renamed from: setRetryDelay-LRDsOJo, reason: not valid java name */
    public final void m6633setRetryDelayLRDsOJo(long j10) {
        this.retryDelay = j10;
    }

    public final void setSerializer(SupabaseSerializer supabaseSerializer) {
        this.serializer = supabaseSerializer;
    }

    public final void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
