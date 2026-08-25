package io.github.jan.supabase.gotrue.providers.builtin;

import ab.g;
import bb.e;
import db.b0;
import db.s;
import io.github.jan.supabase.annotations.SupabaseInternal;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import xc.d;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/CaptchaTokenSerializer;", "Lya/h;", "", "<init>", "()V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Ljava/lang/String;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Ljava/lang/String;)V", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SupabaseInternal
public final class CaptchaTokenSerializer implements h<String> {
    public static final CaptchaTokenSerializer INSTANCE = new CaptchaTokenSerializer();
    private static final g descriptor = d.I("CaptchaTokenSerializer", new g[0], CaptchaTokenSerializer$descriptor$1.INSTANCE);

    private CaptchaTokenSerializer() {
    }

    @Override // ya.s, ya.c
    public g getDescriptor() {
        return descriptor;
    }

    @Override // ya.c
    public String deserialize(bb.d decoder) {
        throw new UnsupportedOperationException();
    }

    @Override // ya.s
    public void serialize(e encoder, String value) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ((s) encoder).l(new b0(linkedHashMap));
    }
}
