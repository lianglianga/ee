import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/book/list',
    method: 'get',
    params: query
  })
}

export function fetchBook() {
  return request({
    url: '/book/detail',
    method: 'get'
  })
}

export function fetchPv(pv) {
  return request({
    url: '/book/pv',
    method: 'get',
    params: { pv }
  })
}

export function createBook(data) {
  return request({
    url: '/book/create',
    method: 'post',
    data
  })
}

export function updateBook(data) {
  return request({
    url: '/book/update',
    method: 'post',
    data
  })
}