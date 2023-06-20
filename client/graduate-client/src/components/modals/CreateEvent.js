import {Button, Dropdown, Form, Modal} from "react-bootstrap";
import {useState} from "react";
import React from "react";
import axios from "axios";

const types = ['Автомобильные соревнования', 'Мотоциклетные соревнования']

const CreateEvent = (({show, onHide}) => {
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [location, setLocation] = useState('')
    const [picture, setPicture] = useState('')
    const [date, setDate] = useState('')
    const [type, setType] = useState('')

    const selectFile = e => {
        setPicture(e.target.files[0])
    }

    const addCenter = () => {
        const formData = new FormData()
        formData.append('name', name)
        formData.append('description', description)
        formData.append('location', location)
        formData.append('picture', picture)
        formData.append('date', date)
        formData.append('state', 'PLANNED')
        formData.append('type', type)
        axios.post("http://localhost:9000/event", formData, {
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
                    Добавить событие
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Dropdown className="mt-2 mb-2">
                        <Dropdown.Toggle>{"Выберите тип"}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {types.map(type =>
                                <Dropdown.Item
                                    onClick={() => setType(type)}
                                    key={type.id}
                                >
                                    {type}
                                </Dropdown.Item>
                            )}
                        </Dropdown.Menu>
                    </Dropdown>
                    <Form.Control
                        value={name}
                        onChange={e => setName(e.target.value)}
                        className="mt-3"
                        placeholder="Введите название события"
                    />
                    <Form.Control
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                        className="mt-3"
                        placeholder="Введите описание события"
                        type="text"
                    />
                    <Form.Control
                        value={location}
                        onChange={e => setLocation(e.target.value)}
                        className="mt-3"
                        placeholder="Введите локацию"
                        type="text"
                    />
                    <Form.Control
                        value={date}
                        onChange={e => setDate(new Date(e.target.value).toISOString().split('T')[0])}
                        className="mt-3"
                        placeholder="Введите дату события"
                        type="date"
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

export default CreateEvent