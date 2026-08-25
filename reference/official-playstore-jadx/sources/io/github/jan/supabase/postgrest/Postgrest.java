package io.github.jan.supabase.postgrest;

import androidx.compose.foundation.c;
import d7.d;
import e7.a;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.SupabaseClientBuilder;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.plugins.CustomSerializationConfig;
import io.github.jan.supabase.plugins.CustomSerializationPlugin;
import io.github.jan.supabase.plugins.MainConfig;
import io.github.jan.supabase.plugins.MainPlugin;
import io.github.jan.supabase.plugins.SupabasePluginProvider;
import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder;
import io.ktor.client.utils.CacheControl;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002\n\u000bJ\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0011\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0002J\u0019\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0002\u0082\u0001\u0001\f¨\u0006\r"}, d2 = {"Lio/github/jan/supabase/postgrest/Postgrest;", "Lio/github/jan/supabase/plugins/MainPlugin;", "Lio/github/jan/supabase/postgrest/Postgrest$Config;", "Lio/github/jan/supabase/plugins/CustomSerializationPlugin;", "from", "Lio/github/jan/supabase/postgrest/query/PostgrestQueryBuilder;", "table", "", "schema", "get", "Companion", "Config", "Lio/github/jan/supabase/postgrest/PostgrestImpl;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Postgrest extends MainPlugin<Config>, CustomSerializationPlugin {
    public static final int API_VERSION = 1;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00022\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/postgrest/Postgrest$Companion;", "Lio/github/jan/supabase/plugins/SupabasePluginProvider;", "Lio/github/jan/supabase/postgrest/Postgrest$Config;", "Lio/github/jan/supabase/postgrest/Postgrest;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "init", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/postgrest/Postgrest$Config;", "Lio/github/jan/supabase/SupabaseClient;", "supabaseClient", "config", "create", "(Lio/github/jan/supabase/SupabaseClient;Lio/github/jan/supabase/postgrest/Postgrest$Config;)Lio/github/jan/supabase/postgrest/Postgrest;", "", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "", "API_VERSION", "I", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements SupabasePluginProvider<Config, Postgrest> {
        public static final int API_VERSION = 1;
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String key = "rest";

        private Companion() {
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public String getKey() {
            return key;
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public void setup(SupabaseClientBuilder supabaseClientBuilder, Config config) {
            SupabasePluginProvider.DefaultImpls.setup(this, supabaseClientBuilder, config);
        }

        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public Postgrest create(SupabaseClient supabaseClient, Config config) {
            return new PostgrestImpl(supabaseClient, config);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.github.jan.supabase.plugins.SupabasePluginProvider
        public Config createConfig(l<? super Config, t0> init) {
            Config config = new Config(null, null, null, null, 15, null);
            init.invoke(config);
            return config;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J5\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lio/github/jan/supabase/postgrest/Postgrest$Config;", "Lio/github/jan/supabase/plugins/MainConfig;", "Lio/github/jan/supabase/plugins/CustomSerializationConfig;", "customUrl", "", "jwtToken", "defaultSchema", "propertyConversionMethod", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/github/jan/supabase/postgrest/PropertyConversionMethod;)V", "getCustomUrl", "()Ljava/lang/String;", "setCustomUrl", "(Ljava/lang/String;)V", "getDefaultSchema", "setDefaultSchema", "getJwtToken", "setJwtToken", "getPropertyConversionMethod", "()Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "setPropertyConversionMethod", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;)V", "serializer", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "setSerializer", "(Lio/github/jan/supabase/SupabaseSerializer;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Config implements MainConfig, CustomSerializationConfig {
        private String customUrl;
        private String defaultSchema;
        private String jwtToken;
        private PropertyConversionMethod propertyConversionMethod;
        private SupabaseSerializer serializer;

        public Config() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, String str3, PropertyConversionMethod propertyConversionMethod, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = config.customUrl;
            }
            if ((i10 & 2) != 0) {
                str2 = config.jwtToken;
            }
            if ((i10 & 4) != 0) {
                str3 = config.defaultSchema;
            }
            if ((i10 & 8) != 0) {
                propertyConversionMethod = config.propertyConversionMethod;
            }
            return config.copy(str, str2, str3, propertyConversionMethod);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCustomUrl() {
            return this.customUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getJwtToken() {
            return this.jwtToken;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDefaultSchema() {
            return this.defaultSchema;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final PropertyConversionMethod getPropertyConversionMethod() {
            return this.propertyConversionMethod;
        }

        public final Config copy(String customUrl, String jwtToken, String defaultSchema, PropertyConversionMethod propertyConversionMethod) {
            return new Config(customUrl, jwtToken, defaultSchema, propertyConversionMethod);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return p.a(this.customUrl, config.customUrl) && p.a(this.jwtToken, config.jwtToken) && p.a(this.defaultSchema, config.defaultSchema) && p.a(this.propertyConversionMethod, config.propertyConversionMethod);
        }

        @Override // io.github.jan.supabase.plugins.MainConfig
        public String getCustomUrl() {
            return this.customUrl;
        }

        public final String getDefaultSchema() {
            return this.defaultSchema;
        }

        @Override // io.github.jan.supabase.plugins.MainConfig
        public String getJwtToken() {
            return this.jwtToken;
        }

        public final PropertyConversionMethod getPropertyConversionMethod() {
            return this.propertyConversionMethod;
        }

        @Override // io.github.jan.supabase.plugins.CustomSerializationConfig
        public SupabaseSerializer getSerializer() {
            return this.serializer;
        }

        public int hashCode() {
            String str = this.customUrl;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.jwtToken;
            return this.propertyConversionMethod.hashCode() + c.c((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31, 31, this.defaultSchema);
        }

        @Override // io.github.jan.supabase.plugins.MainConfig
        public void setCustomUrl(String str) {
            this.customUrl = str;
        }

        public final void setDefaultSchema(String str) {
            this.defaultSchema = str;
        }

        @Override // io.github.jan.supabase.plugins.MainConfig
        public void setJwtToken(String str) {
            this.jwtToken = str;
        }

        public final void setPropertyConversionMethod(PropertyConversionMethod propertyConversionMethod) {
            this.propertyConversionMethod = propertyConversionMethod;
        }

        @Override // io.github.jan.supabase.plugins.CustomSerializationConfig
        public void setSerializer(SupabaseSerializer supabaseSerializer) {
            this.serializer = supabaseSerializer;
        }

        public String toString() {
            return "Config(customUrl=" + this.customUrl + ", jwtToken=" + this.jwtToken + ", defaultSchema=" + this.defaultSchema + ", propertyConversionMethod=" + this.propertyConversionMethod + ')';
        }

        public Config(String str, String str2, String str3, PropertyConversionMethod propertyConversionMethod) {
            this.customUrl = str;
            this.jwtToken = str2;
            this.defaultSchema = str3;
            this.propertyConversionMethod = propertyConversionMethod;
        }

        public /* synthetic */ Config(String str, String str2, String str3, PropertyConversionMethod propertyConversionMethod, int i10, h hVar) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? CacheControl.PUBLIC : str3, (i10 & 8) != 0 ? PropertyConversionMethod.INSTANCE.getCAMEL_CASE_TO_SNAKE_CASE() : propertyConversionMethod);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object close(Postgrest postgrest, d<? super t0> dVar) {
            Object objClose = MainPlugin.DefaultImpls.close(postgrest, dVar);
            return objClose == a.f15033i ? objClose : t0.f22605a;
        }

        public static PostgrestQueryBuilder get(Postgrest postgrest, String str, String str2) {
            return postgrest.from(str, str2);
        }

        @SupabaseInternal
        public static void init(Postgrest postgrest) {
            MainPlugin.DefaultImpls.init(postgrest);
        }

        public static String resolveUrl(Postgrest postgrest, String str) {
            return MainPlugin.DefaultImpls.resolveUrl(postgrest, str);
        }

        public static PostgrestQueryBuilder get(Postgrest postgrest, String str) {
            return postgrest.from(str);
        }
    }

    PostgrestQueryBuilder from(String table);

    PostgrestQueryBuilder from(String schema, String table);

    PostgrestQueryBuilder get(String table);

    PostgrestQueryBuilder get(String schema, String table);
}
