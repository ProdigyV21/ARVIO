package io.github.jan.supabase.gotrue.providers.builtin;

import ab.g;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.c;
import bb.d;
import bb.e;
import cb.b1;
import cb.l1;
import db.b0;
import f4.f;
import g7.a;
import g7.b;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.UtilsKt;
import io.github.jan.supabase.exceptions.SupabaseEncodingException;
import io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider;
import io.github.jan.supabase.gotrue.user.UserSession;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlinx.serialization.MissingFieldException;
import q7.n;
import r7.l;
import x6.s;
import x6.t0;
import ya.h;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001e\u001f B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ(\u0010\u000e\u001a\u00020\u00062\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u00108\u0016X\u0096D¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u0012¨\u0006!"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone;", "Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;", "<init>", "()V", "Ldb/b0;", "json", "decodeResult", "(Ldb/b0;)Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "credentials", "encodeCredentials", "(Lr7/l;)Ldb/b0;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "grantType", "Ljava/lang/String;", "getGrantType", "Channel", "Config", "Result", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class Phone implements DefaultAuthProvider<Config, Result> {
    public static final Phone INSTANCE = new Phone();
    private static final String grantType = "password";

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "SMS", "WHATSAPP", "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r(with = Companion.class)
    public static final class Channel {
        private static final /* synthetic */ a $ENTRIES;
        private static final /* synthetic */ Channel[] $VALUES;
        private static final s<h<Object>> $cachedSerializer$delegate;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        public static final Channel SMS = new Channel("SMS", 0, "sms");
        public static final Channel WHATSAPP = new Channel("WHATSAPP", 1, "whatsapp");
        private static final g descriptor;
        private final String value;

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel$Companion;", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "<init>", "()V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;)V", "serializer", "()Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion implements h<Channel> {

            /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.providers.builtin.Phone$Channel$Companion$1, reason: invalid class name */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.a<h<Object>> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                public AnonymousClass1() {
                    super(0);
                }

                @Override // r7.a
                public final h<Object> invoke() {
                    return Channel.INSTANCE;
                }
            }

            public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
                this();
            }

            private final /* synthetic */ h get$cachedSerializer() {
                return (h) Channel.$cachedSerializer$delegate.getValue();
            }

            @Override // ya.s, ya.c
            public g getDescriptor() {
                return Channel.descriptor;
            }

            public final h<Channel> serializer() {
                return get$cachedSerializer();
            }

            private Companion() {
            }

            @Override // ya.c
            public Channel deserialize(d decoder) {
                for (Channel channel : Channel.getEntries()) {
                    if (p.a(channel.getValue(), decoder.v())) {
                        return channel;
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }

            @Override // ya.s
            public void serialize(e encoder, Channel value) {
                encoder.E(value.getValue());
            }
        }

        private static final /* synthetic */ Channel[] $values() {
            return new Channel[]{SMS, WHATSAPP};
        }

        static {
            Channel[] channelArr$values = $values();
            $VALUES = channelArr$values;
            $ENTRIES = new b(channelArr$values);
            INSTANCE = new Companion(null);
            descriptor = xc.d.F("Channel");
            $cachedSerializer$delegate = f.o(2, Companion.AnonymousClass1.INSTANCE);
        }

        private Channel(String str, int i10, String str2) {
            this.value = str2;
        }

        public static a<Channel> getEntries() {
            return $ENTRIES;
        }

        public static Channel valueOf(String str) {
            return (Channel) Enum.valueOf(Channel.class, str);
        }

        public static Channel[] values() {
            return (Channel[]) $VALUES.clone();
        }

        public final String getValue() {
            return this.value;
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u0000 52\u00020\u0001:\u000265B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bBQ\b\u0011\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0007\u0010\u0010J(\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014HÁ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ.\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b!\u0010\u001bJ\u0010\u0010\"\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003¢\u0006\u0004\b'\u0010(R(\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0003\u0010)\u0012\u0004\b-\u0010.\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010,R\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010)\u001a\u0004\b/\u0010\u001b\"\u0004\b0\u0010,R\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u00101\u001a\u0004\b2\u0010\u001e\"\u0004\b3\u00104¨\u00067"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config;", "", "phone", "password", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "channel", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;)V", "", "seen1", "captchaToken", "Ldb/b0;", "data", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ldb/b0;Ljava/lang/String;Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "copy", "(Ljava/lang/String;Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;)Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getPhone", "setPhone", "(Ljava/lang/String;)V", "getPhone$annotations", "()V", "getPassword", "setPassword", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;", "getChannel", "setChannel", "(Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Channel;)V", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static final /* data */ class Config extends DefaultAuthProvider.Config {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private Channel channel;
        private String password;
        private String phone;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
                this();
            }

            public final h<Config> serializer() {
                return Phone$Config$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public Config() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, Channel channel, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = config.phone;
            }
            if ((i10 & 2) != 0) {
                str2 = config.password;
            }
            if ((i10 & 4) != 0) {
                channel = config.channel;
            }
            return config.copy(str, str2, channel);
        }

        @q("phone")
        public static /* synthetic */ void getPhone$annotations() {
        }

        @n
        public static final /* synthetic */ void write$Self$gotrue_kt_release(Config self, c output, g serialDesc) {
            DefaultAuthProvider.Config.write$Self(self, output, serialDesc);
            if (output.C() || !p.a(self.phone, "")) {
                output.x(serialDesc, 2, self.phone);
            }
            if (output.C() || !p.a(self.password, "")) {
                output.x(serialDesc, 3, self.password);
            }
            if (!output.C() && self.channel == Channel.SMS) {
                return;
            }
            output.j(serialDesc, 4, Channel.INSTANCE, self.channel);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPhone() {
            return this.phone;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Channel getChannel() {
            return this.channel;
        }

        public final Config copy(String phone, String password, Channel channel) {
            return new Config(phone, password, channel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return p.a(this.phone, config.phone) && p.a(this.password, config.password) && this.channel == config.channel;
        }

        public final Channel getChannel() {
            return this.channel;
        }

        public final String getPassword() {
            return this.password;
        }

        public final String getPhone() {
            return this.phone;
        }

        public int hashCode() {
            return this.channel.hashCode() + androidx.compose.foundation.c.c(this.phone.hashCode() * 31, 31, this.password);
        }

        public final void setChannel(Channel channel) {
            this.channel = channel;
        }

        public final void setPassword(String str) {
            this.password = str;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public String toString() {
            return "Config(phone=" + this.phone + ", password=" + this.password + ", channel=" + this.channel + ')';
        }

        @x6.e
        public /* synthetic */ Config(int i10, @r(with = CaptchaTokenSerializer.class) @q("gotrue_meta_security") String str, b0 b0Var, @q("phone") String str2, String str3, Channel channel, l1 l1Var) {
            super(i10, str, b0Var, l1Var);
            if ((i10 & 4) == 0) {
                this.phone = "";
            } else {
                this.phone = str2;
            }
            if ((i10 & 8) == 0) {
                this.password = "";
            } else {
                this.password = str3;
            }
            if ((i10 & 16) == 0) {
                this.channel = Channel.SMS;
            } else {
                this.channel = channel;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Config(String str, String str2, Channel channel) {
            super((String) null, (b0) (0 == true ? 1 : 0), 3, (kotlin.jvm.internal.h) (0 == true ? 1 : 0));
            this.phone = str;
            this.password = str2;
            this.channel = channel;
        }

        public /* synthetic */ Config(String str, String str2, Channel channel, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? Channel.SMS : channel);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0087\b\u0018\u0000 42\u00020\u0001:\u000254B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nBS\b\u0011\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000fJ(\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013HÁ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001dJB\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\"\u0010\u001aJ\u0010\u0010#\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b+\u0010\u001aR \u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010,\u0012\u0004\b.\u0010/\u001a\u0004\b-\u0010\u001dR \u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010,\u0012\u0004\b1\u0010/\u001a\u0004\b0\u0010\u001dR \u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\b\u0010,\u0012\u0004\b3\u0010/\u001a\u0004\b2\u0010\u001d¨\u00066"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;", "", "", TtmlNode.ATTR_ID, "phone", "Lwa/c;", "confirmationSentAt", "createdAt", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lwa/c;", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;)Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getPhone", "Lwa/c;", "getConfirmationSentAt", "getConfirmationSentAt$annotations", "()V", "getCreatedAt", "getCreatedAt$annotations", "getUpdatedAt", "getUpdatedAt$annotations", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static final /* data */ class Result {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final wa.c confirmationSentAt;
        private final wa.c createdAt;
        private final String id;
        private final String phone;
        private final wa.c updatedAt;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/Phone$Result;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
                this();
            }

            public final h<Result> serializer() {
                return Phone$Result$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        @x6.e
        public /* synthetic */ Result(int i10, String str, String str2, @q("confirmation_sent_at") wa.c cVar, @q("created_at") wa.c cVar2, @q("updated_at") wa.c cVar3, l1 l1Var) {
            if (31 != (i10 & 31)) {
                b1.h(i10, 31, Phone$Result$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
            this.id = str;
            this.phone = str2;
            this.confirmationSentAt = cVar;
            this.createdAt = cVar2;
            this.updatedAt = cVar3;
        }

        public static /* synthetic */ Result copy$default(Result result, String str, String str2, wa.c cVar, wa.c cVar2, wa.c cVar3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.id;
            }
            if ((i10 & 2) != 0) {
                str2 = result.phone;
            }
            if ((i10 & 4) != 0) {
                cVar = result.confirmationSentAt;
            }
            if ((i10 & 8) != 0) {
                cVar2 = result.createdAt;
            }
            if ((i10 & 16) != 0) {
                cVar3 = result.updatedAt;
            }
            wa.c cVar4 = cVar3;
            wa.c cVar5 = cVar;
            return result.copy(str, str2, cVar5, cVar2, cVar4);
        }

        @q("confirmation_sent_at")
        public static /* synthetic */ void getConfirmationSentAt$annotations() {
        }

        @q("created_at")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @q("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @n
        public static final /* synthetic */ void write$Self$gotrue_kt_release(Result self, c output, g serialDesc) {
            output.x(serialDesc, 0, self.id);
            output.x(serialDesc, 1, self.phone);
            xa.a aVar = xa.a.f22712a;
            output.j(serialDesc, 2, aVar, self.confirmationSentAt);
            output.j(serialDesc, 3, aVar, self.createdAt);
            output.j(serialDesc, 4, aVar, self.updatedAt);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPhone() {
            return this.phone;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final wa.c getConfirmationSentAt() {
            return this.confirmationSentAt;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final wa.c getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final wa.c getUpdatedAt() {
            return this.updatedAt;
        }

        public final Result copy(String id, String phone, wa.c confirmationSentAt, wa.c createdAt, wa.c updatedAt) {
            return new Result(id, phone, confirmationSentAt, createdAt, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return p.a(this.id, result.id) && p.a(this.phone, result.phone) && p.a(this.confirmationSentAt, result.confirmationSentAt) && p.a(this.createdAt, result.createdAt) && p.a(this.updatedAt, result.updatedAt);
        }

        public final wa.c getConfirmationSentAt() {
            return this.confirmationSentAt;
        }

        public final wa.c getCreatedAt() {
            return this.createdAt;
        }

        public final String getId() {
            return this.id;
        }

        public final String getPhone() {
            return this.phone;
        }

        public final wa.c getUpdatedAt() {
            return this.updatedAt;
        }

        public int hashCode() {
            return this.updatedAt.f22539i.hashCode() + ((this.createdAt.f22539i.hashCode() + ((this.confirmationSentAt.f22539i.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.phone)) * 31)) * 31);
        }

        public String toString() {
            return "Result(id=" + this.id + ", phone=" + this.phone + ", confirmationSentAt=" + this.confirmationSentAt + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
        }

        public Result(String str, String str2, wa.c cVar, wa.c cVar2, wa.c cVar3) {
            this.id = str;
            this.phone = str2;
            this.confirmationSentAt = cVar;
            this.createdAt = cVar2;
            this.updatedAt = cVar3;
        }
    }

    private Phone() {
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public b0 encodeCredentials(l<? super Config, t0> credentials) {
        db.b supabaseJson = UtilsKt.getSupabaseJson();
        Config config = new Config(null, null, null, 7, null);
        credentials.invoke(config);
        supabaseJson.getClass();
        return db.n.f(supabaseJson.g(Config.INSTANCE.serializer(), config));
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof Phone);
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public String getGrantType() {
        return grantType;
    }

    public int hashCode() {
        return -2074974865;
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object login(SupabaseClient supabaseClient, r7.p<? super UserSession, ? super d7.d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d7.d<? super t0> dVar) {
        return DefaultAuthProvider.DefaultImpls.login(this, supabaseClient, pVar, str, lVar, dVar);
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object signUp(SupabaseClient supabaseClient, r7.p<? super UserSession, ? super d7.d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d7.d<? super Result> dVar) {
        return DefaultAuthProvider.DefaultImpls.signUp(this, supabaseClient, pVar, str, lVar, dVar);
    }

    public String toString() {
        return "Phone";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public Result decodeResult(b0 json) throws SupabaseEncodingException {
        try {
            db.b supabaseJson = UtilsKt.getSupabaseJson();
            supabaseJson.getClass();
            return (Result) supabaseJson.f(Result.INSTANCE.serializer(), json);
        } catch (MissingFieldException unused) {
            throw new SupabaseEncodingException("Couldn't decode sign up phone result. Input: " + json);
        }
    }
}
