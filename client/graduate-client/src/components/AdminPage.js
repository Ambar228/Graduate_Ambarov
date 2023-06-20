import {Button, Container} from "react-bootstrap";
import CreateEvent from "./modals/CreateEvent";
import React, {useEffect, useState} from "react";
import CreateTournament from "./modals/CreateTournament";
import CreateSponsor from "./modals/CreateSponsor";
import axios from "axios";

const AdminPage = () => {

    const [eventVisible, setEventVisible] = useState(false)
    const [sponsorVisible, setSponsorVisible] = useState(false)
    const [tournamentVisible, setTournamentVisible] = useState(false)

    const [listUsersVisibleForRacers, setListUsersVisibleForRacers] = useState(false)
    const [listUsersVisibleForModerators, setListUsersVisibleForModerators] = useState(false)


    const [users, setUsers] = useState([])

    useEffect(() => {
        axios.get('http://localhost:9000/user/users').then(response => {
                setUsers(response.data)
            }
        )
    }, [])

    const handleSetListUsersVisible = () => {
        setListUsersVisibleForRacers(!listUsersVisibleForRacers)
    }

    const handleSetListUsersVisibleForModerators = () => {
        setListUsersVisibleForModerators(!listUsersVisibleForModerators)
    }

    const addRacer = (userId) => {
        axios.get('http://localhost:9000/user/' + userId).then()
    }

    const addModerator = (userId) => {
        axios.get('http://localhost:9000/user/moderator/' + userId).then()
    }

    return (
        <Container className="d-flex flex-column">
            <Button variant={"outline-dark"}
                    className="mt-4 p-2"
                    onClick={() => setEventVisible(true)}
            >
                Добавить событие
            </Button>
            <Button variant={"outline-dark"}
                    className="mt-4 p-2"
                    onClick={() => setSponsorVisible(true)}
            >
                Добавить спонсора
            </Button>
            <Button variant={"outline-dark"}
                    className="mt-4 p-2"
                    onClick={() => setTournamentVisible(true)}
            >
                Добавить турнир
            </Button>
            <div className="center_block">
                <Button style={{marginTop: 20}} variant={"outline-dark"} onClick={handleSetListUsersVisible}>Добавить гонщика</Button>
                {
                    listUsersVisibleForRacers && users.length !== 0 &&
                    users.map((user, index) => (
                        <div>
                            <h3>Список пользователей</h3>
                            <div>
                                <span style={{fontSize: 18}}>{user.name}</span>
                                <span style={{fontSize: 18}}> {user.lastname} </span>
                                <Button variant={"outline-dark"} onClick={() => addRacer(index + 1)}>Сделать
                                    гонщиком</Button>
                            </div>
                        </div>
                    ))
                }
            </div>
            <div className="center_block">
                <Button style={{marginTop: 20}} variant={"outline-dark"} onClick={handleSetListUsersVisibleForModerators}>Добавить модертора</Button>
                {
                    listUsersVisibleForModerators && users.length !== 0 &&
                    users.map((user, index) => (
                        <div>
                            <h3>Список пользователей</h3>
                            <div>
                                <span style={{fontSize: 18}}>{user.name}</span>
                                <span style={{fontSize: 18}}> {user.lastname} </span>
                                <Button variant={"outline-dark"} onClick={() => addModerator(index + 1)}>Сделать
                                    модератором</Button>
                            </div>
                        </div>
                    ))
                }
            </div>
            <CreateEvent show={eventVisible} onHide={() => setEventVisible(false)}/>
            <CreateSponsor show={sponsorVisible} onHide={() => setSponsorVisible(false)}/>
            <CreateTournament show={tournamentVisible} onHide={() => setTournamentVisible(false)}/>
        </Container>
    )
}

export default AdminPage