import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {Button, ButtonToolbar, DropdownButton, Dropdown, Container} from "react-bootstrap";
import Competitions from "./Competitions";
import Sponsors from "./Sponsors";
import Result from "./Result";
import axios from "axios";
import Tournaments from "./Tournaments";


const Home = () => {

    const [activePage, setActivePage] = useState('page1');

    const [selectedOption, setSelectedOption] = useState('Список соревнований');
    const [sponsors, setSponsors] = useState('')

    const [showComponent1, setShowComponent1] = useState(true);
    const [showComponent2, setShowComponent2] = useState(false);
    const [showComponent3, setShowComponent3] = useState(false);
    const [showComponent4, setShowComponent4] = useState(false);

    const [competitions, setCompetitions] = useState([{}])

    const [filteredCompetitions, setFilteredCompetitions] = useState(competitions)

    const [role, setRole] = useState('')

    const navigate = useNavigate()

    useEffect(() => {
        axios.get('http://localhost:9000/event').then(response => {
                setCompetitions(response.data)
                setFilteredCompetitions(response.data)
            }
        )
        axios.get('http://localhost:9000/sponsor').then(response => {
                setSponsors(response.data)
            }
        )
        axios.get("http://localhost:9000/user/check", {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        }).then((response) => setRole(response.data))
    }, [])

    const handleSelect = (eventKey) => {
        if (eventKey === 'Автогонки') {
            setFilteredCompetitions(competitions.filter(c => c.type.name === 'Автомобильные соревнования'));
        } else if (eventKey === 'Мотогонки') {
            setFilteredCompetitions(competitions.filter(c => c.type.name === 'Мотоциклетные соревнования'));
        } else {
            setFilteredCompetitions(competitions)
        }
        setSelectedOption(eventKey)
    };

    const handleClick1 = (page) => {
        setShowComponent1(true);
        setShowComponent2(false);
        setShowComponent3(false);
        setShowComponent4(false);
        setActivePage(page);
    };

    const handleClick2 = (page) => {
        setShowComponent1(false);
        setShowComponent2(true);
        setShowComponent3(false);
        setShowComponent4(false);
        setActivePage(page);
    };

    const handleClick3 = (page) => {
        setShowComponent1(false);
        setShowComponent2(false);
        setShowComponent3(true);
        setShowComponent4(false);
        setActivePage(page);
    };

    const handleClick4 = (page) => {
        setShowComponent1(false);
        setShowComponent2(false);
        setShowComponent3(false);
        setShowComponent4(true);
        setActivePage(page);
    };

    const goToAdminPage = () => {
        navigate('/admin')
    }

    const searchTournament = (tournaments) => {
        setFilteredCompetitions(tournaments)
    }

    return (
        <Container>
            <ButtonToolbar style={{paddingBottom: 20, width: 1200}}>
                <Button style={{width: '200px', height: '50px'}}
                        variant={activePage === 'page1' ? 'primary' : 'secondary'}
                        onClick={() => handleClick1('page1')}
                        className={activePage === 'page1' ? 'btn-primary' : 'btn-secondary'}>
                    {selectedOption}</Button>
                <Button style={{width: '200px', height: '50px'}}
                        variant={activePage === 'page2' ? 'primary' : 'secondary'}
                        onClick={() => handleClick2('page2')}
                        className={activePage === 'page2' ? 'btn-primary' : 'btn-secondary'}>
                    Спонсоры</Button>
                <Button style={{width: '200px', height: '50px'}}
                        variant={activePage === 'page3' ? 'primary' : 'secondary'}
                        onClick={() => handleClick3('page3')}
                        className={activePage === 'page3' ? 'btn-primary' : 'btn-secondary'}>Результаты</Button>
                <Button style={{width: '200px', height: '50px'}}
                        variant={activePage === 'page4' ? 'primary' : 'secondary'}
                        onClick={() => handleClick4('page4')}
                        className={activePage === 'page4' ? 'btn-primary' : 'btn-secondary'}>Турниры</Button>
                <DropdownButton title={"Выбрать тип"} onSelect={handleSelect} className={"pl-5"}>
                    <Dropdown.Item eventKey="Список соревнований">Список соревнований</Dropdown.Item>
                    <Dropdown.Item eventKey="Автогонки">Автогонки</Dropdown.Item>
                    <Dropdown.Item eventKey="Мотогонки">Мотогонки</Dropdown.Item>
                </DropdownButton>
                {
                    role === 'ADMIN' &&
                    <Button style={{width: '200px', height: '40px', marginLeft: 10}} onClick={goToAdminPage}>
                        Администратора панель
                    </Button>
                }
            </ButtonToolbar>
            <div>
                {showComponent1 &&
                    <Competitions competitions={filteredCompetitions} searchTournament={searchTournament}/>}
                {showComponent2 && <Sponsors sponsors={sponsors}/>}
                {showComponent3 && <Result/>}
                {showComponent4 && <Tournaments/>}
            </div>
        </Container>
    )

};

export default Home;