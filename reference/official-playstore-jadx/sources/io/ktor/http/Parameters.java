package io.ktor.http;

import io.ktor.util.StringValues;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/Parameters;", "Lio/ktor/util/StringValues;", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Parameters extends StringValues {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\n\u001a\u00020\t2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"Lio/ktor/http/Parameters$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Lio/ktor/http/ParametersBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/ktor/http/Parameters;", "build", "(Lr7/l;)Lio/ktor/http/Parameters;", "Empty", "Lio/ktor/http/Parameters;", "getEmpty", "()Lio/ktor/http/Parameters;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Parameters Empty = EmptyParameters.INSTANCE;

        private Companion() {
        }

        public final Parameters build(l<? super ParametersBuilder, t0> builder) {
            ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
            builder.invoke(parametersBuilderParametersBuilder$default);
            return parametersBuilderParametersBuilder$default.build();
        }

        public final Parameters getEmpty() {
            return Empty;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean contains(Parameters parameters, String str) {
            return StringValues.DefaultImpls.contains(parameters, str);
        }

        public static void forEach(Parameters parameters, p<? super String, ? super List<String>, t0> pVar) {
            StringValues.DefaultImpls.forEach(parameters, pVar);
        }

        public static String get(Parameters parameters, String str) {
            return StringValues.DefaultImpls.get(parameters, str);
        }

        public static boolean contains(Parameters parameters, String str, String str2) {
            return StringValues.DefaultImpls.contains(parameters, str, str2);
        }
    }
}
