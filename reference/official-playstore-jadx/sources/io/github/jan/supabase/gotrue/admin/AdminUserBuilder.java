package io.github.jan.supabase.gotrue.admin;

import ab.g;
import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.e;
import db.b0;
import db.c0;
import db.n;
import db.s;
import f4.f;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import r7.l;
import x6.t0;
import xc.d;
import ya.h;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \"2\u00020\u0001:\u0003\"#$B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\t\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJ&\u0010\u000b\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u000b\u0010\nR$\u0010\t\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\r\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\"\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u0082\u0001\u0002%&¨\u0006'"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;", "", "<init>", "()V", "Lkotlin/Function1;", "Ldb/c0;", "Lx6/t0;", "Lx6/n;", TtmlNode.TAG_METADATA, "userMetadata", "(Lr7/l;)V", "appMetadata", "Ldb/b0;", "Ldb/b0;", "getUserMetadata", "()Ldb/b0;", "setUserMetadata", "(Ldb/b0;)V", "getAppMetadata", "setAppMetadata", "", "autoConfirm", "Z", "getAutoConfirm", "()Z", "setAutoConfirm", "(Z)V", "", "password", "Ljava/lang/String;", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "Companion", "Email", "Phone", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Email;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Phone;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r(with = Companion.class)
public abstract class AdminUserBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final g descriptor = d.I("io.github.jan.supabase.gotrue.admin.UserBuilder", new g[0], AdminUserBuilder$Companion$descriptor$1.INSTANCE);
    private b0 appMetadata;
    private boolean autoConfirm;
    private String password;
    private b0 userMetadata;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Companion;", "Lya/h;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;", "<init>", "()V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;)V", "serializer", "()Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements h<AdminUserBuilder> {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        @Override // ya.s, ya.c
        public g getDescriptor() {
            return AdminUserBuilder.descriptor;
        }

        public final h<AdminUserBuilder> serializer() {
            return AdminUserBuilder.INSTANCE;
        }

        private Companion() {
        }

        @Override // ya.c
        public AdminUserBuilder deserialize(bb.d decoder) {
            throw new IllegalStateException("This serializer is only used for serialization");
        }

        @Override // ya.s
        public void serialize(e encoder, AdminUserBuilder value) {
            s sVar = (s) encoder;
            if (o.h0(value.getPassword())) {
                throw new IllegalArgumentException("Password must not be blank");
            }
            boolean z = value instanceof Email;
            if (z && o.h0(((Email) value).getEmail())) {
                throw new IllegalArgumentException("Email must not be blank");
            }
            boolean z5 = value instanceof Phone;
            if (z5 && o.h0(((Phone) value).getPhone())) {
                throw new IllegalArgumentException("Phone number must not be blank");
            }
            c0 c0Var = new c0();
            f.r(c0Var, "password", value.getPassword());
            b0 userMetadata = value.getUserMetadata();
            if (userMetadata != null) {
                c0Var.b(userMetadata, "user_metadata");
            }
            b0 appMetadata = value.getAppMetadata();
            if (appMetadata != null) {
                c0Var.b(appMetadata, "app_metadata");
            }
            if (z) {
                f.r(c0Var, "email", ((Email) value).getEmail());
                c0Var.b(n.a(Boolean.valueOf(value.getAutoConfirm())), "email_confirm");
            } else if (z5) {
                f.r(c0Var, "phone", ((Phone) value).getPhone());
                c0Var.b(n.a(Boolean.valueOf(value.getAutoConfirm())), "phone_confirm");
            }
            sVar.l(c0Var.a());
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Email;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;", "email", "", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Email extends AdminUserBuilder {
        private String email;

        /* JADX WARN: Multi-variable type inference failed */
        public Email() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Email copy$default(Email email, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = email.email;
            }
            return email.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        public final Email copy(String email) {
            return new Email(email);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Email) && p.a(this.email, ((Email) other).email);
        }

        public final String getEmail() {
            return this.email;
        }

        public int hashCode() {
            return this.email.hashCode();
        }

        public final void setEmail(String str) {
            this.email = str;
        }

        public String toString() {
            return c.u(new StringBuilder("Email(email="), this.email, ')');
        }

        public Email(String str) {
            super(null);
            this.email = str;
        }

        public /* synthetic */ Email(String str, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? "" : str);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Phone;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder;", "phone", "", "(Ljava/lang/String;)V", "getPhone", "()Ljava/lang/String;", "setPhone", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Phone extends AdminUserBuilder {
        private String phone;

        /* JADX WARN: Multi-variable type inference failed */
        public Phone() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Phone copy$default(Phone phone, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = phone.phone;
            }
            return phone.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPhone() {
            return this.phone;
        }

        public final Phone copy(String phone) {
            return new Phone(phone);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Phone) && p.a(this.phone, ((Phone) other).phone);
        }

        public final String getPhone() {
            return this.phone;
        }

        public int hashCode() {
            return this.phone.hashCode();
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public String toString() {
            return c.u(new StringBuilder("Phone(phone="), this.phone, ')');
        }

        public Phone(String str) {
            super(null);
            this.phone = str;
        }

        public /* synthetic */ Phone(String str, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? "" : str);
        }
    }

    public /* synthetic */ AdminUserBuilder(kotlin.jvm.internal.h hVar) {
        this();
    }

    public final void appMetadata(l<? super c0, t0> metadata) {
        c0 c0Var = new c0();
        metadata.invoke(c0Var);
        this.appMetadata = c0Var.a();
    }

    public final b0 getAppMetadata() {
        return this.appMetadata;
    }

    public final boolean getAutoConfirm() {
        return this.autoConfirm;
    }

    public final String getPassword() {
        return this.password;
    }

    public final b0 getUserMetadata() {
        return this.userMetadata;
    }

    public final void setAppMetadata(b0 b0Var) {
        this.appMetadata = b0Var;
    }

    public final void setAutoConfirm(boolean z) {
        this.autoConfirm = z;
    }

    public final void setPassword(String str) {
        this.password = str;
    }

    public final void setUserMetadata(b0 b0Var) {
        this.userMetadata = b0Var;
    }

    public final void userMetadata(l<? super c0, t0> metadata) {
        c0 c0Var = new c0();
        metadata.invoke(c0Var);
        this.userMetadata = c0Var.a();
    }

    private AdminUserBuilder() {
        this.password = "";
    }
}
