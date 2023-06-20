import React, {useEffect, useState} from "react";
import axios from "axios";
import {Button, Dropdown, Form, Modal} from "react-bootstrap";

const AddSponsor = (({show, onHide, id}) => {

    const [sponsors, setSponsors] = useState([])
    const [selectedSponsor, setSelected] = useState('')

    const add = () => {
        axios.post('http://localhost:9000/event/' + id + '/sponsor?sponsorId=' + selectedSponsor).then()
    }

    useEffect(() => {
        axios.get('http://localhost:9000/sponsor').then(response => {
                setSponsors(response.data)
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
                            {sponsors.map(sponsor =>
                                <Dropdown.Item
                                    onClick={() => setSelected(sponsor.id)}
                                    key={sponsor.id}
                                >
                                    {sponsor.name}
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

export default AddSponsor