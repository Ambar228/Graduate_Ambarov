import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {Button, Nav, Navbar} from "react-bootstrap";
import store from "../redux/userStore";
import {clearUser} from "../redux/userReducer";
import {useDispatch, useSelector} from "react-redux";

const Navigation = () => {

    const isAuth = useSelector(state => state.isAuth.isAuth);

    const dispatch = useDispatch();

    const logOut = () => {
        localStorage.removeItem('token')
        dispatch(clearUser())
    }

    return (
        <Navbar bg="light">
            <Navbar.Brand href="/" className="ml-3">My App</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="ml-auto">
                    {isAuth === true
                        ? <Button variant="primary" className="mr-3" onClick={logOut}>Выйти</Button>
                        : <Button variant="primary" href={"/login"} className="mr-3">Войти</Button>
                    }
                    <Button variant="outline-primary" href={"/register"} className="mr-3">Регистрация</Button>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
};

export default Navigation;