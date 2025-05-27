import {
    GET_INGREDIENTS,
    UPDATE_STOCK,
    CREATE_INGREDIENT_REQUEST,  
    CREATE_INGREDIENT_SUCCESS,
    CREATE_INGREDIENT_FAILURE,
    CREATE_INGREDIENTS_CATEGORY_REQUEST,
    CREATE_INGREDIENTS_CATEGORY_SUCCESS,
    CREATE_INGREDIENTS_CATEGORY_FAILURE,
    GET_INGREDIENTS_CATEGORY_REQUEST,
    GET_INGREDIENTS_CATEGORY_SUCCESS,
    GET_INGREDIENTS_CATEGORY_FAILURE,
} from './ActionTypes';
import { API_URL, api } from '../../../config/api';

export const getIngredientsOfRestaurant = ({id,jwt}) => {
    return async (dispatch) => {
        try {
            const response = await api.get(`/api/admin/ingredients/restaurant/${id}`, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log('get all ingredients', response.data);
            dispatch({
                type: GET_INGREDIENTS,
                payload: response.data,
            });
        } catch (error) {
            console.log('error', error);
        }
    };
}

export const createIngredient = ({data, jwt}) => {
    return async (dispatch) => {
        dispatch({ type: CREATE_INGREDIENT_REQUEST });
        try {
            const response = await api.post('/api/admin/ingredients', data, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log('create ingredient', response.data);
            dispatch({
                type: CREATE_INGREDIENT_SUCCESS,
                payload: response.data,
            });
        } catch (error) {
            console.log('error', error);
        }
    };
}

export const createIngredientCategory = ({data, jwt}) => {
    return async (dispatch) => {
        dispatch({ type: CREATE_INGREDIENTS_CATEGORY_REQUEST });
        try {
            const response = await api.post('/api/admin/ingredients/category', data, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log('create ingredients category', response.data);
            dispatch({
                type: CREATE_INGREDIENTS_CATEGORY_SUCCESS,
                payload: response.data,
            });
        } catch (error) {
            console.log('error', error);
        }
    };
}

export const getIngredientCategory = ({id,jwt}) => {
    return async (dispatch) => {
        dispatch({ type: GET_INGREDIENTS_CATEGORY_REQUEST });
        try {
            const response = await api.get(`/api/admin/ingredients/restaurant/${id}/category`, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log('get all ingredients category', response.data);
            dispatch({
                type: GET_INGREDIENTS_CATEGORY_SUCCESS,
                payload: response.data,
            });
        } catch (error) {
            console.log('error', error);
        }
    };
}

export const updateStockOfIngredient = ({id, jwt}) => {
    return async (dispatch) => {
        try {
            const {data} = await api.put(`/api/admin/ingredients/${id}/stock`, {}, {
                headers: {
                    Authorization: `Bearer ${jwt}`,
                },
            });
            console.log('update stock', data);
            dispatch({
                type: UPDATE_STOCK,
                payload: data,
            });
        } catch (error) {
            console.log('error', error);
        }
    };
}