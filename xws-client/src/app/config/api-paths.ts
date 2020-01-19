const API_BASE = 'http://localhost:8080';

export const API_LOGIN = API_BASE + '/auth/login';
export const API_REGISTER_USER = API_BASE + '/api/users/public/add-user';
export const API_VERIFY_ACCOUNT = API_BASE + '/api/users/public/verify-account';
export const API_MY_PUBLICATIONS = API_BASE + '/api/publications/by-user';

export const API_SHOW_HTML_PUBLICATION = API_BASE + '/api/publications/public/html';
export const API_SHOW_PDF_PUBLICATION = API_BASE + '/api/publications/public/pdf';
export const API_SHOW_XML_PUBLICATION = API_BASE + '/api/publications/public/xml';
