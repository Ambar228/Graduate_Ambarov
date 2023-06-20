import {Button, Dropdown, Form, Modal} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import axios from "axios";

const AddRacer = (({show, onHide, id}) => {

    const [racers, setRacers] = useState([])
    const [selectedRacer, setSelected] = useState('')

    const add = () => {
        axios.post('http://localhost:9000/event/' + id + '/racer?racerId=' + selectedRacer).then()
    }

    useEffect(() => {
        axios.get('http://localhost:9000/user/racers').then(response => {
                setRacers(response.data)
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
                    Добавить гонщика
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Dropdown className="mt-2 mb-2">
                        <Dropdown.Toggle>{"Выберите гонщика"}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {racers.map(racer =>
                                <Dropdown.Item
                                    onClick={() => setSelected(racer.id)}
                                    key={racer.id}
                                >
                                    {racer.name + ' ' + racer.lastname}
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

export default AddRacer