import React, {useState} from "react";
import axios from "axios";
import {Button, Dropdown, Form, Modal} from "react-bootstrap";

const CreateTournament = (({show, onHide}) => {
    const [name, setName] = useState('')

    const addCenter = () => {

        axios.post("http://localhost:9000/tournament", {name}, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                "Content-Type": "multipart/form-data",
            }
        }).then()
    }

    return (
        <Modal
            show={show}
            onHide={onHide}
            centered
        >
            <Modal.Header>
                <Modal.Title id="contained-modal-title-vcenter">
                    Добавить турнир
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Control
                        value={name}
                        onChange={e => setName(e.target.value)}
                        className="mt-3"
                        placeholder="Введите название турнира"
                    />

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="outline-danger" onClick={onHide}>Закрыть</Button>
                <Button variant="outline-success" onClick={addCenter}>Добавить</Button>
            </Modal.Footer>
        </Modal>
    )
});

export default CreateTournament