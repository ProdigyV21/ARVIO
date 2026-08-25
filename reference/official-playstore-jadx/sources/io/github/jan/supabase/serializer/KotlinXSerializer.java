package io.github.jan.supabase.serializer;

import db.b;
import fb.d;
import io.github.jan.supabase.SupabaseSerializer;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.reflect.b0;
import kotlin.reflect.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\f\u001a\u00020\u000b\"\b\b\u0000\u0010\u0007*\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\f\u0010\rJ)\u0010\u000e\u001a\u00028\u0000\"\b\b\u0000\u0010\u0007*\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/github/jan/supabase/serializer/KotlinXSerializer;", "Lio/github/jan/supabase/SupabaseSerializer;", "Ldb/b;", "json", "<init>", "(Ldb/b;)V", "", "T", "Lkotlin/reflect/q;", LinkHeader.Parameters.Type, "value", "", "encode", "(Lkotlin/reflect/q;Ljava/lang/Object;)Ljava/lang/String;", "decode", "(Lkotlin/reflect/q;Ljava/lang/String;)Ljava/lang/Object;", "Ldb/b;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class KotlinXSerializer implements SupabaseSerializer {
    private final b json;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinXSerializer() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // io.github.jan.supabase.SupabaseSerializer
    public <T> T decode(q type, String value) {
        return (T) this.json.c(value, b0.C(d.f15400a, type));
    }

    @Override // io.github.jan.supabase.SupabaseSerializer
    public <T> String encode(q type, T value) {
        return this.json.b(b0.C(d.f15400a, type), value);
    }

    public KotlinXSerializer(b bVar) {
        this.json = bVar;
    }

    public /* synthetic */ KotlinXSerializer(b bVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? b.f14877d : bVar);
    }
}
