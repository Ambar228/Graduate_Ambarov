import React, {useEffect, useState} from "react";
import axios from "axios";

const Tournaments = () => {
    const [tournaments, setTournaments] = useState([])

    useEffect(() => {
        axios.get('http://localhost:9000/tournament').then(response => setTournaments(response.data))
    })

    return (
        <>
            {tournaments.map((tournament) => (
                <div key={tournament.id}
                     style={{width: '900px', borderWidth: '1px', borderStyle: 'solid', marginBottom: 20}}>
                    <h3 style={{textAlign: "center"}}>{tournament.name}</h3>
                </div>
            ))}
        </>
    )
}

export default Tournaments