package io.github.jan.supabase;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.reflect.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J)\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\t\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/SupabaseSerializer;", "", "T", "Lkotlin/reflect/q;", LinkHeader.Parameters.Type, "value", "", "encode", "(Lkotlin/reflect/q;Ljava/lang/Object;)Ljava/lang/String;", "decode", "(Lkotlin/reflect/q;Ljava/lang/String;)Ljava/lang/Object;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SupabaseSerializer {
    <T> T decode(q type, String value);

    <T> String encode(q type, T value);
}
