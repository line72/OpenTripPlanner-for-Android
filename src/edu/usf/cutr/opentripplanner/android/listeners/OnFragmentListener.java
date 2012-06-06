/*
 * Copyright 2012 University of South Florida
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package edu.usf.cutr.opentripplanner.android.listeners;

import java.util.List;

import org.opentripplanner.api.model.Leg;
import org.osmdroid.util.GeoPoint;

import edu.usf.cutr.opentripplanner.android.model.OTPBundle;
import edu.usf.cutr.opentripplanner.android.sqlite.ServersDataSource;

import android.support.v4.app.Fragment;

/**
 * @author Khoa Tran
 *
 */

public interface OnFragmentListener {
	public void onItinerarySelected(List<Leg> l);
	
	public List<Leg> getCurrentItinerary();
	
	public void onDirectionFragmentSwitched();
	
	public void onMainFragmentSwitched(Fragment f);
	
	public OTPBundle getOTPBundle();
	
	public void setOTPBundle(OTPBundle b);
	
	public ServersDataSource getDatasource();

	public void setDatasource(ServersDataSource datasource);
	
	public void setCurrentRequestString(String url);
	
	public String getCurrentRequestString();
	
	public void zoomToLocation(GeoPoint p);
	
	public void setMarker(GeoPoint p, boolean isStartMarker);
	
}