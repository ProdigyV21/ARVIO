package io.github.jan.supabase.postgrest;

import com.google.firebase.installations.d;
import io.github.jan.supabase.PlatformTarget;
import io.github.jan.supabase.PlatformTargetKt;
import kotlin.Metadata;
import kotlin.reflect.o;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bæ\u0080\u0001\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J \u0010\u0005\u001a\u00020\u00042\u000e\u0010\u0003\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002H¦\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "", "Lkotlin/reflect/o;", "property", "", "invoke", "(Lkotlin/reflect/o;)Ljava/lang/String;", "Companion", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface PropertyConversionMethod {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0013\u0010\t\u001a\u00020\u00048F¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/postgrest/PropertyConversionMethod$Companion;", "", "()V", "CAMEL_CASE_TO_SNAKE_CASE", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getCAMEL_CASE_TO_SNAKE_CASE", "()Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "NONE", "getNONE", "SERIAL_NAME", "getSERIAL_NAME", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final PropertyConversionMethod SERIAL_NAME = new d(3);
        private static final PropertyConversionMethod CAMEL_CASE_TO_SNAKE_CASE = new d(4);
        private static final PropertyConversionMethod NONE = new d(5);

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String CAMEL_CASE_TO_SNAKE_CASE$lambda$1(o oVar) {
            return UtilsKt.camelToSnakeCase(oVar.getName());
        }

        public final PropertyConversionMethod getCAMEL_CASE_TO_SNAKE_CASE() {
            return CAMEL_CASE_TO_SNAKE_CASE;
        }

        public final PropertyConversionMethod getNONE() {
            return NONE;
        }

        public final PropertyConversionMethod getSERIAL_NAME() {
            if (a.E(PlatformTarget.JVM, PlatformTarget.ANDROID).contains(PlatformTargetKt.getCurrentPlatformTarget())) {
                return SERIAL_NAME;
            }
            throw new IllegalStateException("SerialName PropertyConversionMethod is only available on the JVM and ANDROID due to limited reflection on other targets. Use CAMEL_CASE_TO_SNAKE_CASE instead.");
        }
    }

    String invoke(o<?, ?> property);
}
