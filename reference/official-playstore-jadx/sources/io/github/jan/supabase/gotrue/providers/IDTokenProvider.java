package io.github.jan.supabase.gotrue.providers;

import ab.g;
import bb.e;
import kotlin.Metadata;
import xc.d;
import ya.h;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0004\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "()V", "Companion", "Lio/github/jan/supabase/gotrue/providers/Apple;", "Lio/github/jan/supabase/gotrue/providers/Azure;", "Lio/github/jan/supabase/gotrue/providers/Facebook;", "Lio/github/jan/supabase/gotrue/providers/Google;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r(with = Companion.class)
public abstract class IDTokenProvider extends OAuthProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final g descriptor = d.F("IDTokenProvider");

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/IDTokenProvider$Companion;", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "<init>", "()V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;)V", "serializer", "()Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements h<IDTokenProvider> {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        @Override // ya.s, ya.c
        public g getDescriptor() {
            return IDTokenProvider.descriptor;
        }

        public final h<IDTokenProvider> serializer() {
            return IDTokenProvider.INSTANCE;
        }

        private Companion() {
        }

        @Override // ya.c
        public IDTokenProvider deserialize(bb.d decoder) {
            throw new UnsupportedOperationException();
        }

        @Override // ya.s
        public void serialize(e encoder, IDTokenProvider value) {
            encoder.E(value.getName());
        }
    }

    public /* synthetic */ IDTokenProvider(kotlin.jvm.internal.h hVar) {
        this();
    }

    private IDTokenProvider() {
    }
}
