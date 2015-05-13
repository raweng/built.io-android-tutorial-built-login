package com.buitio.builtlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.facebook.LoggingBehavior;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.raweng.built.BuiltError;
import com.raweng.built.BuiltResultCallBack;
import com.raweng.built.BuiltUser;
import com.raweng.built.userInterface.BuiltUILoginController;
import com.raweng.built.utilities.BuiltConstant;

/**
 * This is built.io android tutorial.
 * 
 * Short introduction of some classes with some methods.
 * Contain classes: 
 * 1. BuiltUILoginController 
 * 2. BuiltUISignUpController 
 * 3. BuiltUser
 * 
 * For quick start with built.io refer "http://docs.built.io/quickstart/index.html#android"
 * 
 * @author raw engineering, Inc
 *
 */
public class SocialLoginActivity extends BuiltUILoginController{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/// BuiltUILoginController can be used by extending user class.
		/// To use BuiltUILoginController
		/// Note : Add required tags in manifest for reference check "http://docs.built.io/quickstart/index.html#android"
		
		/*
		 * BuiltUILoginController provides the layout control public for user for customization.
		 */
		emailEditText.setVisibility(View.GONE);
		passwordEditText.setVisibility(View.GONE);
		loginButton.setVisibility(View.GONE);
		signUpButton.setVisibility(View.GONE);
		forgetPasswordImageView.setVisibility(View.GONE);
		signUpInfoTextView.setVisibility(View.GONE);
		signUpButton.setVisibility(View.GONE);

		/*
		 * set your twitter application credential for login with twitter.
		 */
		setUpTwitter("YOUR_CONSUMER_KEY", "YOUR_CONSUMER_SECRET");

	}

	@Override
	public void loginSuccess(BuiltUser user) {
		
		/// After login successful BuiltUser object provided in success callback.
		
		Intent detailsIntent = new Intent(SocialLoginActivity.this, UserDetailActivity.class);
		startActivity(detailsIntent);
		finish();
	}

	@Override
	public void loginError(BuiltError error) {
		
		/// there is an error while login.
		/// builtErrorObject contains more details of error.
		Toast.makeText(SocialLoginActivity.this, "error :"+error.getErrorMessage(), Toast.LENGTH_SHORT).show();
	}
}
