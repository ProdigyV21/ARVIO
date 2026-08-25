package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public class AccountTransferException extends ApiException {
    public AccountTransferException(Status status) {
        super(status);
    }
}
