package androidx.compose.ui.autofill;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.i0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\"2\u0010\u0000\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0007\u001a\u00020\u0003*\u00020\u00028@X\u0081\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidAutofillTypes", "Ljava/util/HashMap;", "Landroidx/compose/ui/autofill/AutofillType;", "", "Lkotlin/collections/HashMap;", "getAndroidAutofillTypes$annotations", "()V", "androidType", "getAndroidType$annotations", "(Landroidx/compose/ui/autofill/AutofillType;)V", "getAndroidType", "(Landroidx/compose/ui/autofill/AutofillType;)Ljava/lang/String;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidAutofillType_androidKt {
    private static final HashMap<AutofillType, String> androidAutofillTypes;

    static {
        x[] xVarArr = {new x(AutofillType.EmailAddress, "emailAddress"), new x(AutofillType.Username, "username"), new x(AutofillType.Password, "password"), new x(AutofillType.NewUsername, "newUsername"), new x(AutofillType.NewPassword, "newPassword"), new x(AutofillType.PostalAddress, "postalAddress"), new x(AutofillType.PostalCode, "postalCode"), new x(AutofillType.CreditCardNumber, "creditCardNumber"), new x(AutofillType.CreditCardSecurityCode, "creditCardSecurityCode"), new x(AutofillType.CreditCardExpirationDate, "creditCardExpirationDate"), new x(AutofillType.CreditCardExpirationMonth, "creditCardExpirationMonth"), new x(AutofillType.CreditCardExpirationYear, "creditCardExpirationYear"), new x(AutofillType.CreditCardExpirationDay, "creditCardExpirationDay"), new x(AutofillType.AddressCountry, "addressCountry"), new x(AutofillType.AddressRegion, "addressRegion"), new x(AutofillType.AddressLocality, "addressLocality"), new x(AutofillType.AddressStreet, "streetAddress"), new x(AutofillType.AddressAuxiliaryDetails, "extendedAddress"), new x(AutofillType.PostalCodeExtended, "extendedPostalCode"), new x(AutofillType.PersonFullName, "personName"), new x(AutofillType.PersonFirstName, "personGivenName"), new x(AutofillType.PersonLastName, "personFamilyName"), new x(AutofillType.PersonMiddleName, "personMiddleName"), new x(AutofillType.PersonMiddleInitial, "personMiddleInitial"), new x(AutofillType.PersonNamePrefix, "personNamePrefix"), new x(AutofillType.PersonNameSuffix, "personNameSuffix"), new x(AutofillType.PhoneNumber, "phoneNumber"), new x(AutofillType.PhoneNumberDevice, "phoneNumberDevice"), new x(AutofillType.PhoneCountryCode, "phoneCountryCode"), new x(AutofillType.PhoneNumberNational, "phoneNational"), new x(AutofillType.Gender, "gender"), new x(AutofillType.BirthDateFull, "birthDateFull"), new x(AutofillType.BirthDateDay, "birthDateDay"), new x(AutofillType.BirthDateMonth, "birthDateMonth"), new x(AutofillType.BirthDateYear, "birthDateYear"), new x(AutofillType.SmsOtpCode, "smsOTPCode")};
        HashMap<AutofillType, String> map = new HashMap<>(i0.q0(36));
        h0.y0(map, xVarArr);
        androidAutofillTypes = map;
    }

    private static /* synthetic */ void getAndroidAutofillTypes$annotations() {
    }

    public static final String getAndroidType(AutofillType autofillType) {
        String str = androidAutofillTypes.get(autofillType);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Unsupported autofill type");
    }

    public static /* synthetic */ void getAndroidType$annotations(AutofillType autofillType) {
    }
}
