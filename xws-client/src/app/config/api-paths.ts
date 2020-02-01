const API_BASE = 'http://localhost:8080';

// Users
export const API_LOGIN = API_BASE + '/auth/login';
export const API_REGISTER_USER = API_BASE + '/api/users/public/add-user';
export const API_VERIFY_ACCOUNT = API_BASE + '/api/users/public/verify-account';

// Publications
export const API_PUBLICATION = API_BASE + '/api/publications/public';
export const API_PUBLICATION_ALL = API_BASE + '/api/publications/public/all';
export const API_MY_PUBLICATIONS = API_BASE + '/api/publications/by-user';
export const API_SEARCH_PUBLICATIONS = API_BASE + '/api/publications/public/filter';

// Business process
export const API_BUSINESS_PROCESSES = API_BASE + '/api/process';
export const API_SHOW_MY_REVIEW_REQUESTS = API_BASE + '/api/process/my-review-requests';
export const API_ACCEPT_REVIEW_REQUEST = API_BASE + '/api/process/accept-review-request';
export const API_DECLINE_REVIEW_REQUEST = API_BASE + '/api/process/decline-review-request';
export const API_ACCEPT_PUBLICATION = API_BASE + '/api/process/accept-publication';
export const API_DECLINE_PUBLICATION = API_BASE + '/api/process/decline-publication';
export const API_RECOMMEND_REVIEWERS = API_BASE + '/api/process/recommend-reviewers';
export const API_ADD_REVIEWERS = API_BASE + '/api/process/add-reviewers';

// Show publications URLs
export const API_SHOW_HTML_PUBLICATION = API_BASE + '/api/publications/public/html';
export const API_SHOW_PDF_PUBLICATION = API_BASE + '/api/publications/public/pdf';
export const API_SHOW_XML_PUBLICATION = API_BASE + '/api/publications/public/xml';
