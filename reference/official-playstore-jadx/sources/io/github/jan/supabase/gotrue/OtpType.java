package io.github.jan.supabase.gotrue;

import g7.a;
import g7.b;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/OtpType;", "", LinkHeader.Parameters.Type, "", "getType", "()Ljava/lang/String;", "Email", "Phone", "Lio/github/jan/supabase/gotrue/OtpType$Email;", "Lio/github/jan/supabase/gotrue/OtpType$Phone;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface OtpType {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lio/github/jan/supabase/gotrue/OtpType$Email;", "", "Lio/github/jan/supabase/gotrue/OtpType;", LinkHeader.Parameters.Type, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "MAGIC_LINK", "SIGNUP", "INVITE", "RECOVERY", "EMAIL_CHANGE", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Email implements OtpType {
        private static final /* synthetic */ a $ENTRIES;
        private static final /* synthetic */ Email[] $VALUES;
        private final String type;
        public static final Email MAGIC_LINK = new Email("MAGIC_LINK", 0, "magiclink");
        public static final Email SIGNUP = new Email("SIGNUP", 1, "signup");
        public static final Email INVITE = new Email("INVITE", 2, "invite");
        public static final Email RECOVERY = new Email("RECOVERY", 3, "recovery");
        public static final Email EMAIL_CHANGE = new Email("EMAIL_CHANGE", 4, "email_change");

        private static final /* synthetic */ Email[] $values() {
            return new Email[]{MAGIC_LINK, SIGNUP, INVITE, RECOVERY, EMAIL_CHANGE};
        }

        static {
            Email[] emailArr$values = $values();
            $VALUES = emailArr$values;
            $ENTRIES = new b(emailArr$values);
        }

        private Email(String str, int i10, String str2) {
            this.type = str2;
        }

        public static a<Email> getEntries() {
            return $ENTRIES;
        }

        public static Email valueOf(String str) {
            return (Email) Enum.valueOf(Email.class, str);
        }

        public static Email[] values() {
            return (Email[]) $VALUES.clone();
        }

        @Override // io.github.jan.supabase.gotrue.OtpType
        public String getType() {
            return this.type;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/gotrue/OtpType$Phone;", "", "Lio/github/jan/supabase/gotrue/OtpType;", LinkHeader.Parameters.Type, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "SMS", "PHONE_CHANGE", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Phone implements OtpType {
        private static final /* synthetic */ a $ENTRIES;
        private static final /* synthetic */ Phone[] $VALUES;
        private final String type;
        public static final Phone SMS = new Phone("SMS", 0, "sms");
        public static final Phone PHONE_CHANGE = new Phone("PHONE_CHANGE", 1, "phone_change");

        private static final /* synthetic */ Phone[] $values() {
            return new Phone[]{SMS, PHONE_CHANGE};
        }

        static {
            Phone[] phoneArr$values = $values();
            $VALUES = phoneArr$values;
            $ENTRIES = new b(phoneArr$values);
        }

        private Phone(String str, int i10, String str2) {
            this.type = str2;
        }

        public static a<Phone> getEntries() {
            return $ENTRIES;
        }

        public static Phone valueOf(String str) {
            return (Phone) Enum.valueOf(Phone.class, str);
        }

        public static Phone[] values() {
            return (Phone[]) $VALUES.clone();
        }

        @Override // io.github.jan.supabase.gotrue.OtpType
        public String getType() {
            return this.type;
        }
    }

    String getType();
}
