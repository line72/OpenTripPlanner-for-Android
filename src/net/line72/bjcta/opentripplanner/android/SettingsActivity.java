/* -*- Mode: java; c-basic-offset: 8; indent-tabs-mode: t -*- */
/*
 * Copyright 2011 Marcy Gordon
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package net.line72.bjcta.opentripplanner.android;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import net.line72.bjcta.opentripplanner.android.R;
import net.line72.bjcta.opentripplanner.android.model.Server;
import net.line72.bjcta.opentripplanner.android.sqlite.ServersDataSource;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.util.Log;
import android.widget.Toast;
import static net.line72.bjcta.opentripplanner.android.OTPApp.*;

/*
 * Modified by Khoa Tran
 */
public class SettingsActivity extends PreferenceActivity {
	private ListPreference mapTileProvider;
	private PreferenceCategory routingOptions;
	private Preference providerFeedbackButton;
	private Preference serverRefreshButton;
	private ListPreference geocoderProvider;
	
	private final String TAG = "OTP";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);

		mapTileProvider = (ListPreference) findPreference(PREFERENCE_KEY_MAP_TILE_SOURCE);
		geocoderProvider = (ListPreference) findPreference(PREFERENCE_KEY_GEOCODER_PROVIDER);
		routingOptions = (PreferenceCategory) findPreference(PREFERENCE_KEY_ROUTING_OPTIONS);

		ArrayList<CharSequence> names = new ArrayList<CharSequence>();
		ArrayList<ITileSource> tiles = TileSourceFactory.getTileSources();

		for (ITileSource iTileSource : tiles) {
			names.add(iTileSource.name());
		}

		mapTileProvider.setEntries(names.toArray(new CharSequence[names.size()]));
		mapTileProvider.setEntryValues(names.toArray(new CharSequence[names.size()]));
		mapTileProvider.setDefaultValue("Mapnik");
		
		String[] availableGeocoderProviders = getResources().getStringArray(R.array.available_geocoder_providers);
		geocoderProvider.setEntries(availableGeocoderProviders);
		geocoderProvider.setEntryValues(availableGeocoderProviders);
		geocoderProvider.setDefaultValue(availableGeocoderProviders[0]);

		providerFeedbackButton = (Preference)findPreference(PREFERENCE_KEY_OTP_PROVIDER_FEEDBACK);
		providerFeedbackButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference arg0) {
				Log.v(TAG, "Provider Feedback Button clicked");
				String recipient = getString(R.string.email_otp_android_developer);
	        	
	        	String uriText = "mailto:"+recipient;
	        	
	        	String subject = "";
	            subject += "Android BJCTA Trip Planner user report problem(s) ";
	            Date d = Calendar.getInstance().getTime(); 
	            subject += "[" + d.toString() + "]";
	            uriText += "?subject=" + subject;

	        	Uri uri = Uri.parse(uriText);

	        	Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
	        	sendIntent.setData(uri);
	        	startActivity(Intent.createChooser(sendIntent, "Send email")); 

				return true;
			}
		});		
	}
}
