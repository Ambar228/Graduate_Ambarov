import React, {useState} from "react";
import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";

const CreateSponsor = (({show, onHide}) => {
    const [name, setName] = useState('')
    const [advertisingInformation, setAdvertisingInformation] = useState('')
    const [contacts, setContacts] = useState('')
    const [logo, setLogo] = useState('')

    const selectFile = e => {
        setLogo(e.target.files[0])
    }

    const addCenter = () => {
        const formData = new FormData()
        formData.append('logo', logo)
        formData.append('name', name)
        formData.append('advertisingInformation', advertisingInformation)
        formData.append('contacts', contacts)
        axios.post("http://localhost:9000/sponsor", formData, {
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
                    Добавить спонсора
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Control
                        value={name}
                        onChange={e => setName(e.target.value)}
                        className="mt-3"
                        placeholder="Введите название спонсора"
                    />
                    <Form.Control
                        value={advertisingInformation}
                        onChange={e => setAdvertisingInformation(e.target.value)}
                        className="mt-3"
                        placeholder="Введите рекламную информацию"
                        type="text"
                    />
                    <Form.Control
                        value={contacts}
                        onChange={e => setContacts(e.target.value)}
                        className="mt-3"
                        placeholder="Введите контакты"
                        type="text"
                    />
                    <Form.Control
                        className="mt-3"
                        type="file"
                        onChange={selectFile}
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

export default CreateSponsor