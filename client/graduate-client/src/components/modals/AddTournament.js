import React, {useEffect, useState} from "react";
import axios from "axios";
import {Button, Dropdown, Form, Modal} from "react-bootstrap";

const AddTournament = (({show, onHide, id}) => {

    const [tournaments, setTournaments] = useState([])
    const [selectedTournament, setSelected] = useState('')

    const add = () => {
        axios.post('http://localhost:9000/event/' + id + '/tournament?tournamentId=' + selectedTournament).then()
    }

    useEffect(() => {
        axios.get('http://localhost:9000/tournament').then(response => {
                setTournaments(response.data)
            }
        )
    }, [])

    return (
        <Modal
            show={show}
            onHide={onHide}
            centered
        >
            <Modal.Header>
                <Modal.Title id="contained-modal-title-vcenter">
                    Добавить в турнир
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Dropdown className="mt-2 mb-2">
                        <Dropdown.Toggle>{"Выберите турнир"}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {tournaments.map(tournament =>
                                <Dropdown.Item
                                    onClick={() => setSelected(tournament.id)}
                                    key={tournament.id}
                                >
                                    {tournament.name}
                                </Dropdown.Item>
                            )}
                        </Dropdown.Menu>
                    </Dropdown>
                </Form>

            </Modal.Body>
            <Modal.Footer>
                <Button variant="outline-danger" onClick={onHide}>Закрыть</Button>
                <Button variant="outline-success" onClick={add}>Добавить</Button>
            </Modal.Footer>
        </Modal>
    )
})

export default AddTournament