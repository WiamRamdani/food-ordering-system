import { authReducer } from "./Authentication/Authentication/Reducer";
import {applyMiddleware, combineReducers, legacy_createStore} from "redux";
import {thunk} from "redux-thunk";
import cartReducer from "./Cart/Reducer";
import menuItemReducer from "./Menu/Reducer";
import orderReducer from "./Order/Reducer";
import ingredientsReducer from "./Ingredients/Reducer";
import restaurantsOrderReducer from "./Restaurant Order/Reducer";
import restaurantReducer from "./Restaurant/Reducer";

const rootReducer=combineReducers({
    auth:authReducer,
    restaurant:restaurantReducer,
    menu:menuItemReducer,
    cart: cartReducer,
    order:orderReducer,
    restaurantOrder:restaurantsOrderReducer,
    ingredients:ingredientsReducer,
});

export const store=legacy_createStore(rootReducer,applyMiddleware(thunk));