import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Container, Form, FormControl, FormGroup} from "react-bootstrap";
import axios from "axios";
import {useDispatch} from "react-redux";
import {setUser} from "../redux/userReducer";
import store from "../redux/userStore";

const Login = () => {
    const [numberPhone, setNumberPhone] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleNumberPhoneChange = (event) => {
        setNumberPhone(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault()

        axios.post('http://localhost:9000/user/authentication', {numberPhone, password}).then(response => localStorage.setItem('token', response.data))
        store.dispatch(setUser(true))

        navigate('/')
    };

    return (
            <div style={{justifyContent: "center", display: "grid", alignItems: "center", height: "100vh"}}>
                <Form onSubmit={handleSubmit}>
                    <h3>Авторизация</h3>
                    <FormGroup>
                        <Form.Label>Номер телефона</Form.Label>
                        <FormControl
                            type="text"
                            placeholder="Введите номер телефона"
                            value={numberPhone}
                            onChange={handleNumberPhoneChange}/>
                    </FormGroup>

                    <FormGroup controlId="formBasicPassword">
                        <Form.Label>Пароль</Form.Label>
                        <FormControl
                            type="password"
                            placeholder="Введите пароль"
                            value={password}
                            onChange={handlePasswordChange}/>
                    </FormGroup>
                    <Button variant="primary" type="submit">
                        Войти
                    </Button>
                </Form>
            </div>
    );
};

export default Login;