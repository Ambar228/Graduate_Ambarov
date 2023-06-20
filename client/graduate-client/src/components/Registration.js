import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Button, Container, Form, FormControl, FormGroup} from "react-bootstrap";
import axios from "axios";

const Registration = () => {
    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');
    const [numberPhone, setNumberPhone] = useState('');
    const [password, setPassword] = useState('');

    const navigation = useNavigate();

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleLastNameChange = (event) => {
        setLastname(event.target.value);
    };

    const handleNumberPhoneChange = (event) => {
        setNumberPhone(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:9000/user/registration', {
            name,
            lastname,
            numberPhone,
            password
        }).then(response => localStorage.setItem('token', response.data))
        console.log(localStorage.getItem('token'))
        navigation('/')
    };

    return (
        <div style={{justifyContent: "center", display: "grid", alignItems: "center", height: "100vh"}}>
            <Form onSubmit={handleSubmit}>
                <h3>Регистрация</h3>
                <FormGroup>
                    <Form.Label>Имя</Form.Label>
                    <FormControl
                        type="text"
                        placeholder="Введите имя"
                        value={name}
                        onChange={handleNameChange}/>
                </FormGroup>
                <FormGroup>
                    <Form.Label>Фамилия</Form.Label>
                    <FormControl
                        type="text"
                        placeholder="Введите фамилию"
                        value={lastname}
                        onChange={handleLastNameChange}/>
                </FormGroup>
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
                    Зарегистрироваться
                </Button>
            </Form>
        </div>
    );
};

export default Registration;