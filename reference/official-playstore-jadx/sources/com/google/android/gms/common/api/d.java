package com.google.android.gms.common.api;

import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/* JADX INFO: loaded from: classes4.dex */
public interface d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.e f12682a = new com.google.android.gms.common.api.e();

    public interface a extends c, InterfaceC0221d {
        Account getAccount();
    }

    public interface b extends c {
        GoogleSignInAccount c();
    }

    public interface c extends d {
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.d$d, reason: collision with other inner class name */
    public interface InterfaceC0221d extends d {
    }

    public interface e extends c, InterfaceC0221d {
    }
}
