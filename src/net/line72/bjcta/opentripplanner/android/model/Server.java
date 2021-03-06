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

package net.line72.bjcta.opentripplanner.android.model;

import java.util.Date;

import android.location.Location;

/**
 * Modified by Khoa Tran
 *
 */

public class Server {

	private long id;
	private Date date;
	private String region;
	private String baseURL;
	private String bounds;
	private String language;
	private String contactName;
	private String contactEmail;

	private double lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude;

	private double centerLatitude, centerLongitude;  //for Google Places

	public Server() {
		super();
	}

	public Server(Date d, String region, String baseURL, String bounds,
			String language, String contactName, String contactEmail) {
		super();
		setDate(d);
		setRegion(region);
		setBaseURL(baseURL);
		setBounds(bounds);  // do extra string processing to set lowerleft and upperright
		setLanguage(language);
		setContactName(contactName);
		setContactEmail(contactEmail);
	}
	
	public Server(String region, String baseURL, String bounds,
			String language, String contactName, String contactEmail) {
		super();
		setRegion(region);
		setBaseURL(baseURL);
		setBounds(bounds);  // do extra string processing to set lowerleft and upperright
		setLanguage(language);
		setContactName(contactName);
		setContactEmail(contactEmail);
	}

	/*
	 * Constructor for server with a custom URL
	 */
	public Server(String baseURL) {
		super();
		this.baseURL = baseURL;
		this.region = "Unknown Region";
		this.bounds = "Unknown Bounds";
		this.language = "Unknown Language";
		this.contactName = "Unknown Contact Name";
		this.contactEmail = "Unknown Contact Email";
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBounds() {
		return bounds;
	}

	public void setBounds(String bounds) {
		this.bounds = bounds;
		String[] tokens = bounds.split(",");

		setLowerLeftLatitude(Double.parseDouble(tokens[0]));
		setLowerLeftLongitude(Double.parseDouble(tokens[1]));
		setUpperRightLatitude(Double.parseDouble(tokens[2]));
		setUpperRightLongitude(Double.parseDouble(tokens[3]));
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String toString() {
		return region;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the lowerLeftLatitude
	 */
	public double getLowerLeftLatitude() {
		return lowerLeftLatitude;
	}

	/**
	 * @param lowerLeftLatitude the lowerLeftLatitude to set
	 */
	public void setLowerLeftLatitude(double lowerLeftLatitude) {
		this.lowerLeftLatitude = lowerLeftLatitude;
	}

	/**
	 * @return the lowerLeftLongitude
	 */
	public double getLowerLeftLongitude() {
		return lowerLeftLongitude;
	}

	/**
	 * @param lowerLeftLongitude the lowerLeftLongitude to set
	 */
	public void setLowerLeftLongitude(double lowerLeftLongitude) {
		this.lowerLeftLongitude = lowerLeftLongitude;
	}

	/**
	 * @return the upperRightLatitude
	 */
	public double getUpperRightLatitude() {
		return upperRightLatitude;
	}

	/**
	 * @param upperRightLatitude the upperRightLatitude to set
	 */
	public void setUpperRightLatitude(double upperRightLatitude) {
		this.upperRightLatitude = upperRightLatitude;
	}

	/**
	 * @return the upperRightLongitude
	 */
	public double getUpperRightLongitude() {
		return upperRightLongitude;
	}

	/**
	 * @param upperRightLongitude the upperRightLongitude to set
	 */
	public void setUpperRightLongitude(double upperRightLongitude) {
		this.upperRightLongitude = upperRightLongitude;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		float[] results = new float[3];
		Location.distanceBetween(centerLatitude, centerLongitude, lowerLeftLatitude, lowerLeftLongitude, results);
		return results[0];
	}

	/**
	 * @return the centerLatitude
	 */
	public double getCenterLatitude() {
		centerLatitude = (lowerLeftLatitude + upperRightLatitude)/2;
		return centerLatitude;
	}

	/**
	 * @return the centerLongitude
	 */
	public double getCenterLongitude() {
		centerLongitude = (lowerLeftLongitude + upperRightLongitude)/2;
		return centerLongitude;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
}
