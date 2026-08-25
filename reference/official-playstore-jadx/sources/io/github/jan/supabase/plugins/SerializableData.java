package io.github.jan.supabase.plugins;

import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.annotations.SupabaseInternal;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/plugins/SerializableData;", "", "serializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer$annotations", "()V", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SerializableData {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @SupabaseInternal
        public static /* synthetic */ void getSerializer$annotations() {
        }
    }

    SupabaseSerializer getSerializer();
}
