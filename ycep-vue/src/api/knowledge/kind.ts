import { request } from '../../utils/request';

export function getList(itemID: any, pageNum: any, pageSize: any) {
    return request({
        url: `/main/knowledge/${itemID}?pageNum=${pageNum}&pageSize=${pageSize}`,
        method: 'get',
    })
}

export function getCategory() {
    return request({
        url: `/main/knowledge`,
        method: 'get',
    })
}

export function getAllList(kindID: any, pageNum: any, pageSize: any) {
    return request({
        url: `/main/knowledge/all/${kindID}?pageNum=${pageNum}&pageSize=${pageSize}`,
        method: 'get',
    })
}
