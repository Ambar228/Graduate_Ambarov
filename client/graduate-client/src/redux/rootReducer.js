import { combineReducers } from 'redux';
import userReducer from "./userReducer";

const rootReducer = combineReducers({
    isAuth: userReducer
});

export default rootReducer;