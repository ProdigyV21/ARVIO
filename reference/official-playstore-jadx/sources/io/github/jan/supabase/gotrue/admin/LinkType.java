package io.github.jan.supabase.gotrue.admin;

import ab.g;
import bb.c;
import cb.l1;
import db.b0;
import db.e0;
import io.github.jan.supabase.annotations.SupabaseInternal;
import io.github.jan.supabase.gotrue.admin.LinkType.Config;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import r7.l;
import x6.e;
import x6.t0;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0007\u000e\u000f\u0010\u0011\u0012\u0013\u0014J(\u0010\b\u001a\u00028\u00002\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H'¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0006\u0015\u0016\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "C", "", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "getType", "()Ljava/lang/String;", LinkHeader.Parameters.Type, "Config", "EmailChangeCurrent", "EmailChangeNew", "Invite", "MagicLink", "RecoveryLink", "Signup", "Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent;", "Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeNew;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Invite;", "Lio/github/jan/supabase/gotrue/admin/LinkType$MagicLink;", "Lio/github/jan/supabase/gotrue/admin/LinkType$RecoveryLink;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Signup;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface LinkType<C extends Config> {

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u001a"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "Config", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class EmailChangeCurrent implements LinkType<Config> {
        public static final EmailChangeCurrent INSTANCE = new EmailChangeCurrent();
        private static final String type = "email_change_current";

        @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002'&B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0011\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\u000bJ(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fHÁ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0016J\u0010\u0010\u001a\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cHÖ\u0003¢\u0006\u0004\b\u001f\u0010 R(\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0003\u0010!\u0012\u0004\b$\u0010%\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0005¨\u0006("}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "newEmail", "<init>", "(Ljava/lang/String;)V", "", "seen1", "email", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNewEmail", "setNewEmail", "getNewEmail$annotations", "()V", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @r
        public static final /* data */ class Config extends Config {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private String newEmail;

            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(h hVar) {
                    this();
                }

                public final ya.h<Config> serializer() {
                    return LinkType$EmailChangeCurrent$Config$$serializer.INSTANCE;
                }

                private Companion() {
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Config() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public static /* synthetic */ Config copy$default(Config config, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = config.newEmail;
                }
                return config.copy(str);
            }

            @q("new_email")
            public static /* synthetic */ void getNewEmail$annotations() {
            }

            @n
            public static final /* synthetic */ void write$Self$gotrue_kt_release(Config self, c output, g serialDesc) {
                Config.write$Self(self, output, serialDesc);
                if (!output.C() && p.a(self.newEmail, "")) {
                    return;
                }
                output.x(serialDesc, 1, self.newEmail);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getNewEmail() {
                return this.newEmail;
            }

            public final Config copy(String newEmail) {
                return new Config(newEmail);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Config) && p.a(this.newEmail, ((Config) other).newEmail);
            }

            public final String getNewEmail() {
                return this.newEmail;
            }

            public int hashCode() {
                return this.newEmail.hashCode();
            }

            public final void setNewEmail(String str) {
                this.newEmail = str;
            }

            public String toString() {
                return androidx.compose.foundation.c.u(new StringBuilder("Config(newEmail="), this.newEmail, ')');
            }

            @e
            public /* synthetic */ Config(int i10, String str, @q("new_email") String str2, l1 l1Var) {
                super(i10, str, l1Var);
                if ((i10 & 2) == 0) {
                    this.newEmail = "";
                } else {
                    this.newEmail = str2;
                }
            }

            public /* synthetic */ Config(String str, int i10, h hVar) {
                this((i10 & 1) != 0 ? "" : str);
            }

            public Config(String str) {
                this.newEmail = str;
            }
        }

        private EmailChangeCurrent() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public /* bridge */ /* synthetic */ Config createConfig(l lVar) {
            return createConfig((l<? super Config, t0>) lVar);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof EmailChangeCurrent);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return 912063776;
        }

        public String toString() {
            return "EmailChangeCurrent";
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public Config createConfig(l<? super Config, t0> config) {
            Config config2 = new Config(null, 1, 0 == true ? 1 : 0);
            config.invoke(config2);
            return config2;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeNew;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$EmailChangeCurrent$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class EmailChangeNew implements LinkType<EmailChangeCurrent.Config> {
        public static final EmailChangeNew INSTANCE = new EmailChangeNew();
        private static final String type = "email_change_new";

        private EmailChangeNew() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public /* bridge */ /* synthetic */ Config createConfig(l lVar) {
            return createConfig((l<? super EmailChangeCurrent.Config, t0>) lVar);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof EmailChangeNew);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return 127587527;
        }

        public String toString() {
            return "EmailChangeNew";
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public EmailChangeCurrent.Config createConfig(l<? super EmailChangeCurrent.Config, t0> config) {
            EmailChangeCurrent.Config config2 = new EmailChangeCurrent.Config(null, 1, 0 == true ? 1 : 0);
            config.invoke(config2);
            return config2;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Invite;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Invite implements LinkType<Config> {
        public static final Invite INSTANCE = new Invite();
        private static final String type = "invite";

        private Invite() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public Config createConfig(l<? super Config, t0> config) {
            Config config2 = new Config();
            config.invoke(config2);
            return config2;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Invite);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return -647199940;
        }

        public String toString() {
            return "Invite";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$MagicLink;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class MagicLink implements LinkType<Config> {
        public static final MagicLink INSTANCE = new MagicLink();
        private static final String type = "magiclink";

        private MagicLink() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public Config createConfig(l<? super Config, t0> config) {
            Config config2 = new Config();
            config.invoke(config2);
            return config2;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof MagicLink);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return -890795468;
        }

        public String toString() {
            return "MagicLink";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$RecoveryLink;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class RecoveryLink implements LinkType<Config> {
        public static final RecoveryLink INSTANCE = new RecoveryLink();
        private static final String type = "recovery";

        private RecoveryLink() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public Config createConfig(l<? super Config, t0> config) {
            Config config2 = new Config();
            config.invoke(config2);
            return config2;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof RecoveryLink);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return 1227354914;
        }

        public String toString() {
            return "RecoveryLink";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\t\u001a\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0017¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u000b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\r¨\u0006\u001a"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Signup;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "createConfig", "(Lr7/l;)Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", LinkHeader.Parameters.Type, "Ljava/lang/String;", "getType", "Config", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Signup implements LinkType<Config> {
        public static final Signup INSTANCE = new Signup();
        private static final String type = "signup";

        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002.-B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0011\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\rJ(\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011HÁ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ&\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0018J\u0010\u0010\u001e\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010 HÖ\u0003¢\u0006\u0004\b#\u0010$R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010(R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010)\u001a\u0004\b*\u0010\u001a\"\u0004\b+\u0010,¨\u0006/"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "password", "Ldb/b0;", "data", "<init>", "(Ljava/lang/String;Ldb/b0;)V", "", "seen1", "email", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ldb/b0;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Ldb/b0;", "copy", "(Ljava/lang/String;Ldb/b0;)Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getPassword", "setPassword", "(Ljava/lang/String;)V", "Ldb/b0;", "getData", "setData", "(Ldb/b0;)V", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @r
        public static final /* data */ class Config extends Config {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private b0 data;
            private String password;

            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Signup$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(h hVar) {
                    this();
                }

                public final ya.h<Config> serializer() {
                    return LinkType$Signup$Config$$serializer.INSTANCE;
                }

                private Companion() {
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Config() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            public static /* synthetic */ Config copy$default(Config config, String str, b0 b0Var, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = config.password;
                }
                if ((i10 & 2) != 0) {
                    b0Var = config.data;
                }
                return config.copy(str, b0Var);
            }

            @n
            public static final /* synthetic */ void write$Self$gotrue_kt_release(Config self, c output, g serialDesc) {
                Config.write$Self(self, output, serialDesc);
                if (output.C() || !p.a(self.password, "")) {
                    output.x(serialDesc, 1, self.password);
                }
                if (!output.C() && self.data == null) {
                    return;
                }
                output.m(serialDesc, 2, e0.f14890a, self.data);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getPassword() {
                return this.password;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final b0 getData() {
                return this.data;
            }

            public final Config copy(String password, b0 data) {
                return new Config(password, data);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Config)) {
                    return false;
                }
                Config config = (Config) other;
                return p.a(this.password, config.password) && p.a(this.data, config.data);
            }

            public final b0 getData() {
                return this.data;
            }

            public final String getPassword() {
                return this.password;
            }

            public int hashCode() {
                int iHashCode = this.password.hashCode() * 31;
                b0 b0Var = this.data;
                return iHashCode + (b0Var == null ? 0 : b0Var.f14881i.hashCode());
            }

            public final void setData(b0 b0Var) {
                this.data = b0Var;
            }

            public final void setPassword(String str) {
                this.password = str;
            }

            public String toString() {
                return "Config(password=" + this.password + ", data=" + this.data + ')';
            }

            @e
            public /* synthetic */ Config(int i10, String str, String str2, b0 b0Var, l1 l1Var) {
                super(i10, str, l1Var);
                if ((i10 & 2) == 0) {
                    this.password = "";
                } else {
                    this.password = str2;
                }
                if ((i10 & 4) == 0) {
                    this.data = null;
                } else {
                    this.data = b0Var;
                }
            }

            public /* synthetic */ Config(String str, b0 b0Var, int i10, h hVar) {
                this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? null : b0Var);
            }

            public Config(String str, b0 b0Var) {
                this.password = str;
                this.data = b0Var;
            }
        }

        private Signup() {
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public /* bridge */ /* synthetic */ Config createConfig(l lVar) {
            return createConfig((l<? super Config, t0>) lVar);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Signup);
        }

        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        public String getType() {
            return type;
        }

        public int hashCode() {
            return -365968053;
        }

        public String toString() {
            return "Signup";
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.github.jan.supabase.gotrue.admin.LinkType
        @SupabaseInternal
        public Config createConfig(l<? super Config, t0> config) {
            Config config2 = new Config(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            config.invoke(config2);
            return config2;
        }
    }

    @SupabaseInternal
    C createConfig(l<? super C, t0> config);

    String getType();

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0002\u0019\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B%\b\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0002\u0010\nJ(\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eHÇ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "", "<init>", "()V", "", "seen1", "", "email", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self", "(Lio/github/jan/supabase/gotrue/admin/LinkType$Config;Lbb/c;Lab/g;)V", "Ljava/lang/String;", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static class Config {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String email;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final ya.h<Config> serializer() {
                return LinkType$Config$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public Config() {
            this.email = "";
        }

        @n
        public static final /* synthetic */ void write$Self(Config self, c output, g serialDesc) {
            if (!output.C() && p.a(self.email, "")) {
                return;
            }
            output.x(serialDesc, 0, self.email);
        }

        public final String getEmail() {
            return this.email;
        }

        public final void setEmail(String str) {
            this.email = str;
        }

        @e
        public /* synthetic */ Config(int i10, String str, l1 l1Var) {
            if ((i10 & 1) == 0) {
                this.email = "";
            } else {
                this.email = str;
            }
        }
    }
}
