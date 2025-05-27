import {
    CREATE_ORDER_FAILURE,
    CREATE_ORDER_REQUEST,
    CREATE_ORDER_SUCCESS,
    GET_USERS_ORDERS_FAILURE,
    GET_USERS_ORDERS_REQUEST,
    GET_USERS_ORDERS_SUCCESS,
    GET_USERES_NOTIFICATION_FAILURE,
    GET_USERES_NOTIFICATION_REQUEST,
    GET_USERES_NOTIFICATION_SUCCESS,
} from "./ActionTypes";
import { api } from "../../../config/api";

export const createOrder = (reqData) => {
    return async (dispatch) => {
        dispatch({ type: CREATE_ORDER_REQUEST });
        try {
            const {data} = await api.post(`/api/order`, reqData.order,{
                headers: {
                    Authorization: `Bearer ${reqData.jwt}`,
                },
            });
            console.log("Order created successfully", data);
            dispatch({ type: CREATE_ORDER_SUCCESS, payload:data });
        } catch (error) {
            console.log("Error creating order", error);
            dispatch({ type: CREATE_ORDER_FAILURE, payload: error });
        }
    };
}

export const getUsersOrders = (jwt) => {
    return async (dispatch) => {
        dispatch({ type: GET_USERS_ORDERS_REQUEST });
        try {
            const {data} = await api.get(`/api/order/user`, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log("users order", data);
            dispatch({ type: GET_USERS_ORDERS_SUCCESS, payload:data });
        } catch (error) {
            dispatch({ type: GET_USERS_ORDERS_FAILURE, payload: error });
        }
    };
}