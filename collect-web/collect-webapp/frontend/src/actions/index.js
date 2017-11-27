import ServiceFactory from 'services/ServiceFactory'
import { Survey } from 'model/Survey';
import User from 'model/User';

export const REQUEST_APPLICATION_INFO = 'REQUEST_APPLICATION_INFO'
export const RECEIVE_APPLICATION_INFO = 'RECEIVE_APPLICATION_INFO'

export const LOG_IN_PENDING = 'LOG_IN_PENDING'
export const LOG_IN_SUCCESS = 'LOG_IN_SUCCESS'
export const LOG_IN_FAILED = 'LOG_IN_FAILED'

export const REQUEST_CURRENT_USER = 'REQUEST_CURRENT_USER'
export const RECEIVE_CURRENT_USER = 'RECEIVE_CURRENT_USER'

export const SELECT_PREFERRED_SURVEY = 'SELECT_PREFERRED_SURVEY'
export const REQUEST_FULL_PREFERRED_SURVEY = 'REQUEST_FULL_PREFERRED_SURVEY'
export const RECEIVE_FULL_PREFERRED_SURVEY = 'RECEIVE_FULL_PREFERRED_SURVEY'
export const INVALIDATE_PREFERRED_SURVEY = 'INVALIDATE_PREFERRED_SURVEY'

export const REQUEST_USER_GROUPS = 'REQUEST_USER_GROUPS'
export const RECEIVE_USER_GROUPS = 'RECEIVE_USER_GROUPS'
export const RECEIVE_USER_GROUP = 'RECEIVE_USER_GROUP'
export const INVALIDATE_USER_GROUPS = 'INVALIDATE_USER_GROUPS'

export const RECORD_DELETED = 'RECORD_DELETED'
export const RECORDS_DELETED = 'RECORDS_DELETED'

//APPLICATION INFO
function requestApplicationInfo() {
	return {
		type: REQUEST_APPLICATION_INFO
	}
}

export function fetchApplicationInfo() {
	return function (dispatch) {
		dispatch(requestApplicationInfo())
		ServiceFactory.applicationInfoService.fetchInfo().then(json => {
			dispatch(receiveApplicationInfo(json));
		})
	}
}

function receiveApplicationInfo(info) {
	return {
		type: RECEIVE_APPLICATION_INFO,
		info: info
	}
}

//LOGIN
function loginPending() {
	return {
		type: LOG_IN_PENDING
	}
}

function loginSuccess() {  
	return {
		type: LOG_IN_SUCCESS
	}
}

function loginFailed() {  
	return {
		type: LOG_IN_FAILED
	}
}

export function logInUser(credentials) {  
	return function(dispatch) {
		dispatch(loginPending());

		function handleErrors(response) {
			if (!response.ok) {
				throw Error(response.statusText);
			}
			return response;
		}
		
		ServiceFactory.userService.login(credentials)
			.then(handleErrors)
			.then(response => {
				if (!response.ok || response.url.indexOf("login_error") > 0) {
					dispatch(loginFailed());
				} else {
					dispatch(loginSuccess());
					dispatch(fetchCurrentUser());
				}
			})
	};
}

function requestCurrentUser() {
	return {
		type: REQUEST_CURRENT_USER
	}
}

export function fetchCurrentUser() {
	return function (dispatch) {
		dispatch(requestCurrentUser())
		ServiceFactory.sessionService.fetchCurrentUser().then(json => {
			dispatch(receiveCurrentUser(json));
		})
	}
}

function receiveCurrentUser(json) {
	return {
	    type: RECEIVE_CURRENT_USER,
	    user: new User(json)
	}
}

//PREFERRED SURVEY
export function selectPreferredSurvey(preferredSurveySummary) {
	let surveyId = preferredSurveySummary.id;
	return function (dispatch) {
		dispatch(requestFullPreferredSurvey(surveyId))
		ServiceFactory.surveyService.fetchById(surveyId).then(json => 
			dispatch(receiveFullPreferredSurvey(json))
		)
	}
}


export function receiveFullPreferredSurvey(json) {
	return {
		type: RECEIVE_FULL_PREFERRED_SURVEY,
		survey: new Survey(json),
		receivedAt: Date.now()
	}
}

export function requestFullPreferredSurvey(surveyId) {
	return {
		type: REQUEST_FULL_PREFERRED_SURVEY,
		surveyId,
		receivedAt: Date.now()
	}
}

export function invalidatePreferredSurvey(preferredSurvey) {
	return {
		type: INVALIDATE_PREFERRED_SURVEY,
		preferredSurvey
	}
}

//USER GROUPS
function requestUserGroups() {
	return {
		type: REQUEST_USER_GROUPS
	}
}

function receiveUserGroups(json) {
	return {
	    type: RECEIVE_USER_GROUPS,
	    userGroups: json.map(g => {
			if (g.parentId != null) {
				g.parent = json.find(g2 => g2.id === g.parentId);
			}
			return g;
		}), //TODO map into UserGroup object
	    receivedAt: Date.now()
	}
}

export function receiveUserGroup(json) {
	return {
	    type: RECEIVE_USER_GROUP,
	    userGroup: json,
	    receivedAt: Date.now()
	}
}

export function fetchUserGroups() {
	return function (dispatch) {
		dispatch(requestUserGroups())
		ServiceFactory.userGroupService.fetchAllAvailableGroups().then(json => dispatch(receiveUserGroups(json)))
	}
}

export function invalidateUserGroups(userGroups) {
	return {
		type: INVALIDATE_USER_GROUPS,
		userGroups
	}
}

//RECORDS
export function recordDeleted(record) {
	return {
		type: RECORD_DELETED,
		record: record
	}
}

export function recordsDeleted(records) {
	return {
		type: RECORDS_DELETED,
		records: records
	}
}
