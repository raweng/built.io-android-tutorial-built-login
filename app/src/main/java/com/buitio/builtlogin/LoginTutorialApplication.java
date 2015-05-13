package com.buitio.builtlogin;

import android.app.Application;

import com.raweng.built.Built;
import com.raweng.built.BuiltApplication;

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
public class LoginTutorialApplication extends Application {

	public static BuiltApplication builtApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		
		/*
		 * Initialize the application for a project using built.io Application credentials "Application Api Key" 
		 * and "Application UID".
		 * 
		 */
		try {
			//Built.initializeWithApiKey(LoginTutorialApplication.this, "blt9f2f3c1d77c907e0", "geofencing");

			builtApplication = Built.application(this , "YOUR_API_KEY");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
