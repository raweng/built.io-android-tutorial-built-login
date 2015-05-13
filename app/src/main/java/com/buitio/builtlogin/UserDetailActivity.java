package com.buitio.builtlogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.raweng.built.BuiltError;
import com.raweng.built.BuiltResultCallBack;
import com.raweng.built.BuiltUser;
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
public class UserDetailActivity extends Activity {

	EditText uidEditText;
	EditText userNameEditText;
	EditText emailEditText;
	EditText authtokenEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);

		uidEditText		  = (EditText) findViewById(R.id.uidEditText);
		userNameEditText  = (EditText) findViewById(R.id.userEditText);
		emailEditText	  = (EditText) findViewById(R.id.emailEditText);
		authtokenEditText = (EditText) findViewById(R.id.authTokenEditText);

		/*
		 * Getting user who's session is saved on disc.
		 */
		BuiltUser userObject = LoginTutorialApplication.builtApplication.getCurrentUser();

		/*
		 * Extracting data from BuiltUser object.
		 */
		uidEditText.setText(userObject.getUserUid());
		userNameEditText.setText(userObject.getUserName());
		emailEditText.setText(userObject.getEmailId());
		authtokenEditText.setText(userObject.getAuthToken());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout: {

			/*
			 * logout call which log out user from application which will clear session on disc.
			 */
			LoginTutorialApplication.builtApplication.getCurrentUser().logoutInBackground(new BuiltResultCallBack() {

				@Override
				public void onCompletion(BuiltConstant.ResponseType responseType, BuiltError builtError) {

					if(builtError == null){
						/// After logout successful this success block called.
						finish();
						Toast.makeText(UserDetailActivity.this, "Logout successfully...", Toast.LENGTH_SHORT).show();
					}else{
						/// there is an error while signUp.
						/// builtErrorObject contains more details of error.

						Toast.makeText(UserDetailActivity.this, "error :"+builtError.getErrorMessage(), Toast.LENGTH_SHORT).show();
					}
				}

			});
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}
}
