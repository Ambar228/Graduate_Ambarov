export const setUser = (isAuth) => ({
    type: 'login',
    payload: isAuth
});

export const clearUser = () => ({
    type: 'logout'
});

const initState = {
    isAuth: false
}

const userReducer = (state = initState, action) => {
    switch (action.type) {
        case 'login':
            return {
                ...state,
                isAuth: action.payload
            };
        case 'logout':
            return {
                ...state,
                isAuth: false
            };
        default:
            return state;
    }
}

export default userReducer;