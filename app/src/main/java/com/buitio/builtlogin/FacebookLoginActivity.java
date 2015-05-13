package com.buitio.builtlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.raweng.built.BuiltError;
import com.raweng.built.BuiltResultCallBack;
import com.raweng.built.BuiltUser;
import com.raweng.built.utilities.BuiltConstant;

/**
 * Created by chinmay on 13/5/15.
 */
public class FacebookLoginActivity extends Activity {

    private Session.StatusCallback statusCallback = new SessionStatusCallback();
    private Button facebookLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login);

        facebookLoginButton = (Button)findViewById(R.id.facebookLogin);

        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this);
            }
            Session.setActiveSession(session);
            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
            }
        }

        updateView();
    }

    /**
     * Login on built.io using facebook access token.
     *
     *
     * @param facebookAccessToken
     */
    public void builtFacebookLogin(String facebookAccessToken){

		/*
		 * BuiltUser class provides various logins.
		 * Here's user can login on built with facebook account.
		 */
        final BuiltUser user = LoginTutorialApplication.builtApplication.user();
        user.loginInBackgroundWithFacebookAccessToken(facebookAccessToken, new BuiltResultCallBack() {

            @Override
            public void onCompletion(BuiltConstant.ResponseType responseType, BuiltError builtError) {

                if (builtError == null) {
                    /// After login successful BuiltUser object provided in success callback.

                    Intent detailsIntent = new Intent(FacebookLoginActivity.this, UserDetailActivity.class);
                    startActivity(detailsIntent);
                    finish();
                } else {

                    /// there is an error while login.
                    /// builtErrorObject contains more details of error.
                    Toast.makeText(FacebookLoginActivity.this, "error :" + builtError.getErrorMessage(), Toast.LENGTH_SHORT).show();

                }

                onClickLogout();
            }
        });
    }

    private void onClickLogin() {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
        } else {
            Session.openActiveSession(this, true, statusCallback);
        }
    }

    private void onClickLogout() {
        Session session = Session.getActiveSession();
        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(statusCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(statusCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }

    private void updateView() {
        Session session = Session.getActiveSession();
        if (session.isOpened()) {

            builtFacebookLogin(session.getAccessToken());

			facebookLoginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    onClickLogout();
                }
            });
        } else {

			facebookLoginButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) { onClickLogin(); }
			});
        }
    }

    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            updateView();
        }
    }
}
