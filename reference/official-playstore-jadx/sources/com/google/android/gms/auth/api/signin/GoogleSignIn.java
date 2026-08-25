package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.c0;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.auth.api.signin.internal.zbn;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.tasks.h;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class GoogleSignIn {
    private GoogleSignIn() {
    }

    public static GoogleSignInAccount getAccountForExtension(Context context, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        t.j(context, "please provide a valid Context object");
        t.j(googleSignInOptionsExtension, "please provide valid GoogleSignInOptionsExtension");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.createDefault();
        }
        return lastSignedInAccount.requestExtraScopes(zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static GoogleSignInAccount getAccountForScopes(Context context, Scope scope, Scope... scopeArr) {
        t.j(context, "please provide a valid Context object");
        t.j(scope, "please provide at least one valid scope");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.createDefault();
        }
        lastSignedInAccount.requestExtraScopes(scope);
        lastSignedInAccount.requestExtraScopes(scopeArr);
        return lastSignedInAccount;
    }

    public static GoogleSignInClient getClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        t.i(googleSignInOptions);
        return new GoogleSignInClient(activity, googleSignInOptions);
    }

    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return zbn.zbc(context).zba();
    }

    public static h getSignedInAccountFromIntent(Intent intent) {
        GoogleSignInResult googleSignInResultZbd = zbm.zbd(intent);
        GoogleSignInAccount signInAccount = googleSignInResultZbd.getSignInAccount();
        return (!googleSignInResultZbd.getStatus().d() || signInAccount == null) ? l.m(t.m(googleSignInResultZbd.getStatus())) : l.n(signInAccount);
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        t.j(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        return hasPermissions(googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(Activity activity, int i10, GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        t.j(activity, "Please provide a non-null Activity");
        t.j(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(activity, i10, googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    private static Intent zba(Activity activity, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder();
        if (scopeArr.length > 0) {
            builder.requestScopes(scopeArr[0], scopeArr);
        }
        if (googleSignInAccount != null && !TextUtils.isEmpty(googleSignInAccount.getEmail())) {
            String email = googleSignInAccount.getEmail();
            t.i(email);
            builder.setAccountName(email);
        }
        return new GoogleSignInClient(activity, builder.build()).getSignInIntent();
    }

    private static Scope[] zbb(List list) {
        return list == null ? new Scope[0] : (Scope[]) list.toArray(new Scope[list.size()]);
    }

    public static GoogleSignInClient getClient(Context context, GoogleSignInOptions googleSignInOptions) {
        t.i(googleSignInOptions);
        return new GoogleSignInClient(context, googleSignInOptions);
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        if (googleSignInAccount == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, scopeArr);
        return googleSignInAccount.getGrantedScopes().containsAll(hashSet);
    }

    public static void requestPermissions(Activity activity, int i10, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        t.j(activity, "Please provide a non-null Activity");
        t.j(scopeArr, "Please provide at least one scope");
        activity.startActivityForResult(zba(activity, googleSignInAccount, scopeArr), i10);
    }

    public static void requestPermissions(c0 c0Var, int i10, GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        t.j(c0Var, "Please provide a non-null Fragment");
        t.j(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(c0Var, i10, googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(c0 c0Var, int i10, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        t.j(c0Var, "Please provide a non-null Fragment");
        t.j(scopeArr, "Please provide at least one scope");
        c0Var.startActivityForResult(zba(c0Var.getActivity(), googleSignInAccount, scopeArr), i10);
    }
}
