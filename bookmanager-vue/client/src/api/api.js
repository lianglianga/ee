import axios from 'axios';

// user
export const requestLogin = params => { return axios.post(`/api/login/userLogin`, params).then(res => res.data); };

export const getUserById = params => { return axios.post(`/api/user/getUserById`, params)};

export const updateUser = params => { return axios.post(`/api/user/updateUser`, params)};

export const updateUserPoints = params => { return axios.post(`/api/user/updateUserPoints`, params)};

export const addUser = params => { return axios.post(`/api/user/addUser`, params)};

//book
export const getSearchBook = params => { return axios.post(`/api/book/getInitBookList`, params)};

export const getBookList = params => { return axios.post(`/api/book/getBookList`, params)};

export const getBookInfoById = params => { return axios.post(`/api/book/getBookInfoById`, params)};

export const updateBook = params => { return axios.post(`/api/book/updateBook`, params)};

export const updateBookState = params => { return axios.post(`/api/book/updateBookState`, params)};

//type
export const getTypeList = params => { return axios.post(`/api/type/getTypeList`, params)};

//order
export const getSearchOrder = params => { return axios.post(`/api/order/getSearchOrder`, params)};

export const deleteOrder = params => { return axios.post(`/api/order/deleteOrder`, params)};

export const addOrder = params => { return axios.post(`/api/order/addOrder`, params)};

export const updateOrder = params => { return axios.post(`/api/order/updateOrder`, params)};
