package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

/* JADX INFO: loaded from: classes4.dex */
public interface h2 extends IInterface {
    Bundle H(Account account, String str, Bundle bundle);

    AccountChangeEventsResponse N(AccountChangeEventsRequest accountChangeEventsRequest);

    Bundle Q(Account account);

    Bundle R(Bundle bundle, String str);

    Bundle Y(String str);
}
