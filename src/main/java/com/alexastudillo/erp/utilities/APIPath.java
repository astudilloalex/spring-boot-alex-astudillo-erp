package com.alexastudillo.erp.utilities;

/**
 * All REST API URL paths.
 * 
 * @author Alex Astudillo
 *
 */
public class APIPath {
	public static final String API_V1_URL = "/api/v1";

	// API CRUD basic operations
	public static final String DELETE = "/delete";
	public static final String DELETE_ALL = "/delete-all";
	public static final String DELETE_ALL_BY_ID = "/delete-all-by-id";
	public static final String DELETE_BY_ID = "/delete-by-id";
	public static final String FIND_ALL = "/find-all";
	public static final String FIND_ALL_BY_ID = "/find-all-by-id";
	public static final String FIND_ALL_BY_PAGE = "/find-all-by-page";
	public static final String SAVE = "/save";
	public static final String SAVE_ALL = "/save-all";
	public static final String UPDATE = "/update";
	public static final String UPDATE_ALL = "/update-all";

	// City URL paths
	public static final String CITIES = "/cities";

	// Company URL paths
	public static final String COMPANIES = "/companies";
	public static final String COMPANY = "/company";

	// Establishment URL paths
	public static final String ESTABLISHMENTS = "/establishments";

	// API Path variables
	public static final String ID = "/{id}";

	// API CRUD commons operations.
	public static final String MY_DATA = "/my-data";

	// Country URL paths
	public static final String COUNTRIES = "/countries";

	// Person URL paths
	public static final String PERSONS = "/persons";
	public static final String PERSON = "/person";

	// Phone URL paths
	public static final String PHONES = "/phones";

	// Privileges paths
	public static final String PRIVILEGES = "/privileges";

	// Roles URL paths.
	public static final String ROLES = "/roles";

	// Users URL paths
	public static final String USERS = "/users";
}
