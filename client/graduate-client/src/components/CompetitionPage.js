import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import Comment from "./Comment";
import axios from "axios";
import {Button, Container} from "react-bootstrap";
import AddRacer from "./modals/AddRacer";
import AddSponsor from "./modals/AddSponsor";
import AddTournament from "./modals/AddTournament";

const CompetitionPage = () => {
    const {id} = useParams();

    const [competition, setCompetition] = useState({tournament: {name: ''}})
    const [comments, setComments] = useState([]);
    const [role, setRole] = useState('')
    const [addracerVisible, setAddracerVisible] = useState(false)
    const [addsponsorVisible, setAddSponsorVisible] = useState(false)
    const [addtournamentVisible, setAddTournamentVisible] = useState(false)

    const handleAddComment = (text) => {
        const newComment = {
            id: comments.length + 1,
            text,
        };
        setComments([...comments, newComment]);
    };

    useEffect(() => {
        axios.get('http://localhost:9000/event/' + id).then(response => {
                setCompetition(response.data)
            }
        )
        axios.get("http://localhost:9000/user/check", {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        }).then((response) => setRole(response.data))
    }, [])


    return (
        <div className="center_block">
            <div key={competition.id}>
                <h3>{competition.name}</h3>
                <img src={`data:image/png;base64,${competition.picture}`} alt={competition.name}
                     style={{maxWidth: "100%", maxHeight: '100%'}}/>
                {role === 'ADMIN' &&
                    <Container className={'d-flex flex-column'}>
                        <Button style={{width: 250}} variant={"outline-dark"} className="mt-4 p-2"
                                onClick={() => setAddracerVisible(true)}>Добавить
                            гонщика
                        </Button>
                        <Button style={{width: 250}} variant={"outline-dark"} className="mt-4 p-2"
                                onClick={() => setAddSponsorVisible(true)}>Добавить
                            спонсора
                        </Button>
                        <Button style={{width: 250}} variant={"outline-dark"} className="mt-4 p-2"
                                onClick={() => setAddTournamentVisible(true)}>Добавить
                            турнир
                        </Button>
                        <AddRacer id={id} show={addracerVisible} onHide={() => setAddracerVisible(false)}/>
                        <AddSponsor id={id} show={addsponsorVisible} onHide={() => setAddSponsorVisible(false)}/>
                        <AddTournament id={id} show={addtournamentVisible}
                                       onHide={() => setAddTournamentVisible(false)}/>
                    </Container>
                }
                {competition.sponsors && competition.sponsors.map((sponsor) => (
                    <div>
                        <h3>Спонсоры:</h3>
                        <p style={{fontSize: 18}}>{sponsor.name}</p>
                        <img src={`data:image/png;base64,${sponsor.logo}`} alt={sponsor.name}
                             style={{maxWidth: '10%', maxHeight: '10%'}}/>
                        <p style={{fontSize: 18}}>Рекламная информация: {sponsor.advertisingInformation}</p>
                        <p style={{fontSize: 18}}>Контакты: {sponsor.contacts}</p>
                    </div>
                ))}
                {
                    competition.tournament !== null
                        ?
                        <>
                            <h3>Турнир</h3>
                            <p style={{fontSize: 18}}>{competition.tournament.name}</p>
                        </>
                        :
                        <>
                        </>
                }
                {competition.racers && competition.racers.map((racer) => (
                    <div>
                        <h3>Список участников:</h3>
                        <span style={{fontSize: 18}}>Имя: {racer.name}</span>
                        <span style={{fontSize: 18}}> Фамилия: {racer.lastname}</span>
                        <br/>
                    </div>
                ))}
                <p style={{fontSize: 24}}>Место проведения: {competition.location}</p>
                <p style={{fontSize: 24}}>Дата: {new Date(competition.date).toLocaleDateString()}</p>
                <Comment comments={competition.comments} onAddComment={handleAddComment} eventId={id}/>
            </div>
        </div>
    );
};

export default CompetitionPage;