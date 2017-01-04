/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.linkedinprofile;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;

import android.app.Activity;

import com.linkedin.platform.DeepLinkHelper;
import com.linkedin.platform.errors.LIDeepLinkError;
import com.linkedin.platform.listeners.DeepLinkListener;

;

@Kroll.module(name = "Linkedinprofile", id = "de.appwerft.linkedinprofile")
public class LinkedinprofileModule extends KrollModule {
	private final class LinkedInResultHandler implements DeepLinkListener {
		@Override
		public void onDeepLinkSuccess() {
			// callback
			if (onsuccess != null) {
				onsuccess.call(getKrollObject(), new KrollDict());
			}
			// eventListeners
			if (hasListeners("onsuccess")) {
				fireEvent("onsuccess", new KrollDict());
			}
		}

		@Override
		public void onDeepLinkError(LIDeepLinkError error) {
			KrollDict kd = new KrollDict();
			kd.put("error", error.toString());
			// callback
			if (onerror != null) {
				onerror.call(getKrollObject(), kd);
			}
			// eventListeners
			if (hasListeners("onerror")) {
				fireEvent("onerror", kd);
			}

			// TODO Auto-generated method stub

		}
	}

	// Standard Debugging variables
	private static final String LCAT = "Linkedinprofile";

	private String id;
	KrollFunction onsuccess;
	KrollFunction onerror;
	private String targetID = "";
	private DeepLinkHelper deepLinkHelper;
	private Activity activity;

	public LinkedinprofileModule() {
		super();
		deepLinkHelper = DeepLinkHelper.getInstance();
		activity = TiApplication.getAppRootOrCurrentActivity();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created
	}

	@Kroll.method
	public void openOtherProfile(KrollDict options) {
		if (options.containsKeyAndNotNull("id")) {
			targetID = options.getString("id");
		} else {
			Log.e(LCAT,
					"the module needs a paramter id, this is the profile id of user.");
		}
		if (options.containsKeyAndNotNull("onsuccess")) {
			Object o = options.get("onsuccess");
			if (o instanceof KrollFunction) {
				this.onsuccess = (KrollFunction) o;
			} else {
				Log.e(LCAT, "parameter 'onsuccess' must be a function");
			}

		} else {
			Log.e(LCAT, "paramter 'onsuccess' is mandatory");
		}
		if (options.containsKeyAndNotNull("onerror")) {
			Object o = options.get("onerror");
			if (o instanceof KrollFunction) {
				this.onsuccess = (KrollFunction) o;
			} else {
				Log.e(LCAT, "parameter 'onerror' must be a function");
			}

		} else {
			Log.e(LCAT, "paramter 'onerror' is mandatory");
		}
		// all is imported
		deepLinkHelper.openOtherProfile(activity, targetID,
				new LinkedInResultHandler());
	}

	@Kroll.method
	public void openCurrentProfile(KrollDict options) {

		if (options.containsKeyAndNotNull("onsuccess")) {
			Object o = options.get("onsuccess");
			if (o instanceof KrollFunction) {
				this.onsuccess = (KrollFunction) o;
			} else {
				Log.e(LCAT, "parameter 'onsuccess' must be a function");
			}

		} else {
			Log.e(LCAT, "paramter 'onsuccess' is mandatory");
		}
		if (options.containsKeyAndNotNull("onerror")) {
			Object o = options.get("onerror");
			if (o instanceof KrollFunction) {
				this.onsuccess = (KrollFunction) o;
			} else {
				Log.e(LCAT, "parameter 'onerror' must be a function");
			}

		} else {
			Log.e(LCAT, "paramter 'onerror' is mandatory");
		}
		deepLinkHelper
				.openCurrentProfile(activity, new LinkedInResultHandler());
	}

}
