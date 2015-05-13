package com.buitio.builtlogin;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raweng.built.BuiltUser;

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
public class ListActivity extends android.app.ListActivity{

	private ArrayList<String> LoginType;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LoginType = new ArrayList<String>();
		LoginType.add("Login Using built.io");
		LoginType.add("Login Using Google and Twitter");
		LoginType.add("Login Using Facebook");

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LoginType);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		switch (position) {
			case 0:
				/*
			 	 * Checking user already logged in.
			 	 * Checking user data is on disc.
			 	 */
				if(LoginTutorialApplication.builtApplication.getCurrentUser() != null){
					Intent detailsIntent = new Intent(ListActivity.this, UserDetailActivity.class);
					startActivity(detailsIntent);
				}else{
					Intent loginIntent = new Intent(ListActivity.this, LoginActivity.class);
					startActivity(loginIntent);
				}
				break;
			case 1:
				/*
			 	 * Checking user already logged in.
			 	 * Checking user data is on disc.
			 	 */
				if(LoginTutorialApplication.builtApplication.getCurrentUser() != null){
					Intent detailsIntent = new Intent(ListActivity.this, UserDetailActivity.class);
					startActivity(detailsIntent);
				}else{
					Intent socialIntent = new Intent(ListActivity.this, SocialLoginActivity.class);
					startActivity(socialIntent);
				}
				break;
			case 2:
				/*
			     * Checking user already logged in.
			     * Checking user data is on disc.
			     */
				if(LoginTutorialApplication.builtApplication.getCurrentUser() != null){
					Intent detailsIntent = new Intent(ListActivity.this, UserDetailActivity.class);
					startActivity(detailsIntent);
				}else{
					Intent socialIntent = new Intent(ListActivity.this, FacebookLoginActivity.class);
					startActivity(socialIntent);
				}
				break;

			default:
				break;
		}

	}


}
