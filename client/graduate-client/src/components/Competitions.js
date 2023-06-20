import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";

const Competitions = ({competitions, searchTournament}) => {

    const [name, setName] = useState('')

    const navigate = useNavigate();

    const handleNavigateToPage = (id) => {
        navigate('/competitions/' + id)
    }

    const findByName = () => {

        console.log(name)
        axios.get('http://localhost:9000/event/search?name=' + name).then(response => {
                searchTournament(response.data)
            }
        )

        setName('')
    }


    return (
        <>
            <div style={{display: 'flex', justifyContent: "center", marginRight: 110}}>
                <input value={name} onChange={(e) => setName(e.target.value)} style={{width: '300px', marginBottom: 10}}
                       placeholder={'Введите название соревнования'}/>
                <button style={{height: 30, marginLeft: 5}} onClick={findByName}>Найти</button>
            </div>
            {Array.isArray(competitions) ? competitions.map((competition) => (
                    <div onClick={() => handleNavigateToPage(competition.id)} key={competition.id}
                         style={{width: '900px', borderWidth: '1px', borderStyle: 'solid', marginBottom: 20}}>
                        <img src={`data:image/png;base64,${competition.picture}`} alt={competition.title}
                             style={{width: '900px', height: '550px'}}/>
                        <h3 style={{textAlign: "center"}}>{competition.name}</h3>
                        <p style={{fontSize: 28}}>Место проведения: {competition.location}</p>
                        <p style={{fontSize: 28}}>Дата: {new Date(competition.date).toLocaleDateString()}</p>
                    </div>
                ))
                :
                <div onClick={() => handleNavigateToPage(competitions.id)} key={competitions.id}
                     style={{width: '900px', borderWidth: '1px', borderStyle: 'solid', marginBottom: 20}}>
                    <img src={`data:image/png;base64,${competitions.picture}`} alt={competitions.title}
                         style={{maxWidth: '100%', maxHeight: '80%'}}/>
                    <h3 style={{textAlign: "center"}}>{competitions.name}</h3>
                    <p style={{fontSize: 28}}>Место проведения: {competitions.location}</p>
                    <p style={{fontSize: 28}}>Дата: {new Date(competitions.date).toLocaleDateString()}</p>
                </div>
            }
        </>
    )
}

export default Competitions;